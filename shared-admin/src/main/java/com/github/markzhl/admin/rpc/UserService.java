package com.github.markzhl.admin.rpc;

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
import com.github.markzhl.admin.biz.ElementBiz;
import com.github.markzhl.admin.biz.MenuBiz;
import com.github.markzhl.admin.biz.UserBiz;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Element;
import com.github.markzhl.admin.entity.Menu;
import com.github.markzhl.admin.entity.User;
import com.github.markzhl.admin.vo.MenuTree;
import com.github.markzhl.api.vo.authority.PermissionInfo;
import com.github.markzhl.api.vo.gate.ClientInfo;
import com.github.markzhl.api.vo.user.UserInfo;
import com.github.markzhl.common.constant.UserConstant;
import com.github.markzhl.common.util.TreeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author mark
 * @create 2017-06-21 8:15
 */
@Controller
@RequestMapping("api")
@Slf4j
public class UserService {
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private ElementBiz elementBiz;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT);
    
    @RequestMapping(value = "/user/username/{username}",method = RequestMethod.GET, produces="application/json")
    @HystrixCommand(fallbackMethod = "getUserByUsernameError")
    public  @ResponseBody UserInfo getUserByUsername(@PathVariable("username")String username) {
        UserInfo info = new UserInfo();
        User user = userBiz.getUserByUsername(username);
        BeanUtils.copyProperties(user,info);
        info.setId(user.getId().toString());
        return info;
    }
    
    public UserInfo getUserByUsernameError(String username){
		log.error("根据username获取UserInfo失败。username={}",username);
		return new UserInfo();
	}
    
    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public @ResponseBody List<PermissionInfo> getAllPermission(){
        List<Menu> menus = menuBiz.selectListAll();
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementBiz.selectListAll();
        element2permission(result, elements);
        return result;
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

    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    public @ResponseBody List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
        User user = userBiz.getUserByUsername(username);
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus,result);
        List<Element> elements = elementBiz.getAuthorityElementByUserId(user.getId()+"");
        element2permission(result, elements);
        return result;
    }

    private void element2permission(List<PermissionInfo> result, List<Element> elements) {
        PermissionInfo info;
        for(Element element:elements){
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            result.add(info);
        }
    }

    @RequestMapping(value = "/user/un/{username}/system", method = RequestMethod.GET)
    @ResponseBody
    public String getSystemsByUsername(@PathVariable("username") String username){
        int userId = userBiz.getUserByUsername(username).getId();
        return JSONObject.toJSONString(menuBiz.getUserAuthoritySystemByUserId(userId));
    }
    
    @RequestMapping(value = "/user/un/{username}/menu/parent/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public String getMenusByUsername(@PathVariable("username") String username,@PathVariable("parentId")Integer parentId){
        int userId = userBiz.getUserByUsername(username).getId();
        try {
            if (parentId == null||parentId<0) {
                parentId = menuBiz.getUserAuthoritySystemByUserId(userId).get(0).getId();
            }
        } catch (Exception e) {
            return JSONObject.toJSONString(new ArrayList<MenuTree>());
        }
        return JSONObject.toJSONString(getMenuTree(menuBiz.getUserAuthorityMenuByUserId(userId), parentId));
    }
    
    @RequestMapping(value = "/user/un/{username}/pw/{pasword}/match", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isMatchByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("pasword") String pasword){
        String encodedPassword = userBiz.getUserByUsername(username).getPassword();
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
