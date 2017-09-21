package com.github.markzhl.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.common.msg.ObjectRestResponse;
import com.github.markzhl.ui.client.IUserClient;

/**
 * 
 *
 * @author mark
 */
@Controller
public class UserPermissionController  extends BaseController{
    @Autowired
    private IUserClient userService;

    @RequestMapping(value = "/user/system",method = RequestMethod.GET)
    @ResponseBody
    public String getUserSystem(){
       return userService.getSystemsByUsername(getCurrentUserName());
    }
    @RequestMapping(value = "/user/menu",method = RequestMethod.GET)
    @ResponseBody
    public String getUserMenu(@RequestParam(defaultValue = "-1") Long parentId){
        return userService.getMenusByUsername(getCurrentUserName(),parentId);
    }
    @RequestMapping(value = "/user/unlock", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse unlockUserByUsernameAndPassword(@RequestParam("password") String password){
    	if(userService.isMatchByUsernameAndPassword(getCurrentUserName(), password)){
    		return new ObjectRestResponse().rel(true);
    	}else {
    		return new ObjectRestResponse().rel(false).msg("密码错误，请重新输入！");
    	}
    }
}
