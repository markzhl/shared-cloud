package com.github.markzhl.admin.rest.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.sys.BaseMenu;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.entity.sys.BaseUser;
import com.github.markzhl.admin.service.sys.BaseMenuService;
import com.github.markzhl.admin.service.sys.BaseResourceService;
import com.github.markzhl.admin.service.sys.BaseUserService;
import com.github.markzhl.admin.vo.MenuTree;
import com.github.markzhl.common.constant.UserConstant;
import com.github.markzhl.common.util.TreeUtil;
import com.github.markzhl.vo.authority.PermissionInfo;
import com.github.markzhl.vo.user.UserInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author mark
 */
@Controller
@Slf4j
@RequestMapping("grcapi")
public class UserRest {
    @Autowired
    private BaseUserService baseUserService;
    @Autowired
    private BaseMenuService baseMenuService;
    @Autowired
    private BaseResourceService baseResourceService;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT);
    
    
    @HystrixCommand(fallbackMethod = "getUserByUsernameError")
    @ResponseBody
    @RequestMapping(value = "/user/username/{username}", method = RequestMethod.GET)
    public UserInfo getUserByUsername(@PathVariable("username")String username) {
        UserInfo info = new UserInfo();
        BaseUser user = baseUserService.getUserByUsername(username);
        BeanUtils.copyProperties(user,info);
        info.setId(user.getId().toString());
        return info;
    }
    
    public UserInfo getUserByUsernameError(String username, Throwable e){
		log.error("调用getUserByUsername失败。参数：username={}，exception={}",username,e);
		return new UserInfo();
	}
    
    @HystrixCommand(fallbackMethod = "getAllPermissionError")
    @ResponseBody
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public  List<PermissionInfo> getAllPermission(){
        List<BaseMenu> menus = baseMenuService.selectList(null);
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<BaseResource> resources = baseResourceService.selectList(null);
        resource2permission(result, resources);
        return result;
    }
    
    public List<PermissionInfo> getAllPermissionError(Throwable e){
		log.error("调用getAllPermission失败。exception={}", e);
		return new ArrayList<PermissionInfo>();
	}

    private void menu2permission(List<BaseMenu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for(BaseMenu menu:menus){
            if(StringUtils.isBlank(menu.getHref()))
                continue;
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(CommonConstant.RESOURCE_TYPE_MENU);
            info.setName(CommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if(!uri.startsWith("/"))
                uri = "/"+uri;
            info.setUri(uri);
            info.setMethod(CommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info
            );
            info.setMenu(menu.getTitle());
        }
    }

    @HystrixCommand(fallbackMethod = "getPermissionByUsernameError")
    @ResponseBody
    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
    	BaseUser user = baseUserService.getUserByUsername(username);
        List<BaseMenu> menus = baseMenuService.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus,result);
        List<BaseResource> resources = baseResourceService.getAuthorityResourceByUserId(user.getId()+"");
        resource2permission(result, resources);
        return result;
    }
    
    public List<PermissionInfo> getPermissionByUsernameError(String username, Throwable e){
    	log.error("调用getPermissionByUsername失败。参数：username={}，exception={}", username,e);
    	return new ArrayList<PermissionInfo>();
    }

    private void resource2permission(List<PermissionInfo> result, List<BaseResource> resources) {
        PermissionInfo info;
        for(BaseResource resource:resources){
            info = new PermissionInfo();
            info.setCode(resource.getCode());
            info.setType(resource.getType());
            info.setUri(resource.getUri());
            info.setMethod(resource.getMethod());
            info.setName(resource.getName());
            info.setMenu(resource.getMenuId());
            result.add(info);
        }
    }

    @HystrixCommand(fallbackMethod = "getSystemsByUsernameError")
    @ResponseBody
    @RequestMapping(value = "/user/un/{username}/system", method = RequestMethod.GET)
    public String getSystemsByUsername(@PathVariable("username") String username){
    	Long userId = baseUserService.getUserByUsername(username).getId();
    	List<BaseMenu> baseMenuList = baseMenuService.getUserAuthoritySystemByUserId(userId);
        return JSONObject.toJSONString(baseMenuList);
    }
    
    public String getSystemsByUsernameError(String username, Throwable e){
    	log.error("调用getSystemsByUsername失败。参数：username={}，exception={}", username,e);
    	return JSONObject.toJSONString(new ArrayList<BaseMenu>());
    }
    
    @HystrixCommand(fallbackMethod = "getMenusByUsernameError")
    @ResponseBody
    @RequestMapping(value = "/user/un/{username}/menu/parent/{parentId}", method = RequestMethod.GET)
    public String getMenusByUsername(@PathVariable("username") String username,@PathVariable("parentId") Long parentId){
    	Long userId = baseUserService.getUserByUsername(username).getId();
        try {
            if (parentId == null||parentId<0) {
                parentId = baseMenuService.getUserAuthoritySystemByUserId(userId).get(0).getId();
            }
        } catch (Exception e) {
            return JSONObject.toJSONString(new ArrayList<MenuTree>());
        }
        return JSONObject.toJSONString(getMenuTree(baseMenuService.getUserAuthorityMenuByUserId(userId), parentId));
    }
    
    public String getMenusByUsernameError(String username, Long parentId, Throwable e){
    	log.error("调用getMenusByUsername失败。参数：username={}，parentId={}，exception={}", username, parentId, e);
    	return JSONObject.toJSONString(new ArrayList<BaseMenu>());
    }
    
    @ResponseBody
    @RequestMapping(value = "/user/un/{username}/pw/{pasword}/match", method = RequestMethod.GET)
    public Boolean isMatchByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("pasword") String pasword){
        String encodedPassword = baseUserService.getUserByUsername(username).getPassword();
        if(encoder.matches(pasword, encodedPassword)){
        	return true;
        }else {
        	return false;
        }
    }

    private List<MenuTree> getMenuTree(List<BaseMenu> menus, Long root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (BaseMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root) ;
    }
    
}
