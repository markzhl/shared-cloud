package com.github.markzhl.gate.filter;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.markzhl.api.vo.authority.PermissionInfo;
import com.github.markzhl.common.util.ClientUtil;
import com.github.markzhl.gate.rpc.IGateService;
import com.github.markzhl.gate.service.AuthService;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author mark
 * @create 2017-06-23 8:25
 */
@Component
@Slf4j
public class ApiAccessFilter extends ZuulFilter {

    @Autowired
    private AuthService authService;
    @Autowired
    private IGateService gateService;
    @Value("${gate.api.header}")
    private String tokenHead;
    @Value("${gate.oauth.prefix}")
    private String prefix;
    public ApiAccessFilter() {
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // TODO: 2017/7/9  黑白名单、ip限制、前端有效用户
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpSession httpSession = ctx.getRequest().getSession();
        HttpServletRequest request = ctx.getRequest();
        String requestUri = request.getRequestURI();
        final String method = request.getMethod();
        log.debug("IP：{}，访问资源：{}，请求方式：{}", ClientUtil.getClientIp(request),requestUri,method);
        requestUri = requestUri.substring(prefix.length()+1);
        final String  finalRequestUri = requestUri.substring(requestUri.indexOf("/"));
        List<PermissionInfo> serviceInfo = gateService.getGateServiceInfo();
        // 判断资源是否启用权限约束
        Collection<PermissionInfo> result = Collections2.filter(serviceInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(finalRequestUri).find() || finalRequestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        });
        if(result.size()>0){
            String token = request.getHeader(tokenHead);
            if(token == null || token.length() == 0){
            	setFailedRequest("No token",401);
            }
            if(!authService.validate(token,finalRequestUri+":"+method)){
                setFailedRequest("Unauthorized",401);
            }
        }
        return null;
    }



    /**
     * Reports an error message given a response body and code.
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
//            throw new RuntimeException("Code: " + code + ", " + body); //optional
        }
    }
}