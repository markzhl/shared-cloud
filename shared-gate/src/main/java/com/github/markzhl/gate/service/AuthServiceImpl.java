package com.github.markzhl.gate.service;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.markzhl.gate.client.IUserClient;
import com.github.markzhl.gate.jwt.JwtTokenUtil;
import com.github.markzhl.vo.authority.PermissionInfo;
import com.github.markzhl.vo.user.UserInfo;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserClient userService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserClient userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }


    @Override
    public String login(String username, String password) {
        UserInfo info = userService.getUserByUsername(username);
        String token = "";
        if(encoder.matches(password,info.getPassword())) {
           token = jwtTokenUtil.generateToken(info);
        }
        return token;
    }

    @Override
    public String refresh(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo info = userService.getUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token,info.getUpdTime())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public Boolean validate(String token,String resource) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserInfo info = userService.getUserByUsername(username);
        return info.getUsername().equals(username)&&!jwtTokenUtil.isTokenExpired(token)&&validateResource(username,resource);
    }

    public Boolean validateResource(String username, String resource){
        String [] res = resource.split(":");
        final String requestUri = res[0];
        final String method = res[1];
        List<PermissionInfo> clientPermissionInfo = userService.getPermissionByUsername(username);
        Collection<PermissionInfo> result = Collections2.filter(clientPermissionInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        });
        if (result.size() <= 0) {
            return false;
        }
        return true;
    }
}
