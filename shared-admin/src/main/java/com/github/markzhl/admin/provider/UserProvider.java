package com.github.markzhl.admin.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Resource;
import com.github.markzhl.admin.entity.Menu;
import com.github.markzhl.admin.entity.User;
import com.github.markzhl.admin.service.ResourceService;
import com.github.markzhl.admin.service.MenuService;
import com.github.markzhl.admin.service.UserService;
import com.github.markzhl.admin.vo.MenuTree;
import com.github.markzhl.api.IUserApi;
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
public class UserProvider implements IUserApi{
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourceService resourceService;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT);
    
    @HystrixCommand(fallbackMethod = "getUserByUsernameError")
    public  @ResponseBody UserInfo getUserByUsername(@PathVariable("username")String username) {
        UserInfo info = new UserInfo();
        User user = userService.getUserByUsername(username);
        BeanUtils.copyProperties(user,info);
        info.setId(user.getId().toString());
        return info;
    }
    
    public UserInfo getUserByUsernameError(String username){
		log.error("调用getUserByUsername失败。参数：username={}",username);
		return new UserInfo();
	}
    
    @HystrixCommand(fallbackMethod = "getAllPermissionError")
    public @ResponseBody List<PermissionInfo> getAllPermission(){
        List<Menu> menus = menuService.selectListAll();
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Resource> resources = resourceService.selectListAll();
        resource2permission(result, resources);
        return result;
    }
    
    public List<PermissionInfo> getAllPermissionError(){
		log.error("调用getAllPermission失败。");
		return new ArrayList<PermissionInfo>();
	}

    private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for(Menu menu:menus){
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
    public @ResponseBody List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
        User user = userService.getUserByUsername(username);
        List<Menu> menus = menuService.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus,result);
        List<Resource> resources = resourceService.getAuthorityResourceByUserId(user.getId()+"");
        resource2permission(result, resources);
        return result;
    }
    
    public List<PermissionInfo> getPermissionByUsernameError(String username){
    	log.error("调用getPermissionByUsername失败。参数：username={}", username);
    	return new ArrayList<PermissionInfo>();
    }

    private void resource2permission(List<PermissionInfo> result, List<Resource> resources) {
        PermissionInfo info;
        for(Resource resource:resources){
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

    @ResponseBody
    @HystrixCommand(fallbackMethod = "getSystemsByUsernameError")
    public String getSystemsByUsername(@PathVariable("username") String username){
        int userId = userService.getUserByUsername(username).getId();
        return JSONObject.toJSONString(menuService.getUserAuthoritySystemByUserId(userId));
    }
    
    public String getSystemsByUsernameError(String username){
    	log.error("调用getSystemsByUsername失败。参数：username={}", username);
    	return JSONObject.toJSONString(new ArrayList<Menu>());
    }
    
    @ResponseBody
    @HystrixCommand(fallbackMethod = "getMenusByUsernameError")
    public String getMenusByUsername(@PathVariable("username") String username,@PathVariable("parentId")Integer parentId){
        int userId = userService.getUserByUsername(username).getId();
        try {
            if (parentId == null||parentId<0) {
                parentId = menuService.getUserAuthoritySystemByUserId(userId).get(0).getId();
            }
        } catch (Exception e) {
            return JSONObject.toJSONString(new ArrayList<MenuTree>());
        }
        return JSONObject.toJSONString(getMenuTree(menuService.getUserAuthorityMenuByUserId(userId), parentId));
    }
    
    public String getMenusByUsernameError(String username, Integer parentId){
    	log.error("调用getMenusByUsername失败。参数：username={}，parentId={}", username, parentId);
    	return JSONObject.toJSONString(new ArrayList<Menu>());
    }
    
    @ResponseBody
    public Boolean isMatchByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("pasword") String pasword){
        String encodedPassword = userService.getUserByUsername(username).getPassword();
        if(encoder.matches(pasword, encodedPassword)){
        	return true;
        }else {
        	return false;
        }
    }

    private List<MenuTree> getMenuTree(List<Menu> menus,int root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root) ;
    }
    
}
