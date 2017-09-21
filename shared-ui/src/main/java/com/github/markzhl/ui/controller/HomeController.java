package com.github.markzhl.ui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.markzhl.ui.client.IUserClient;

/**
 * 
 *
 * @author mark
 */
@Controller
@RequestMapping("")
public class HomeController extends BaseController{
    @Autowired
    private IUserClient userService;


    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        map.put("baseUser",userService.getUserByUsername(getCurrentUserName()));
        return "index";
    }
    @RequestMapping(value = "about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }
    @RequestMapping(value = "sys/baseUser",method = RequestMethod.GET)
    public String user(){
        return "sys/baseUser/list";
    }
    @RequestMapping(value = "sys/baseUser/edit",method = RequestMethod.GET)
    public String userEdit(){
        return "sys/baseUser/edit";
    }
    @RequestMapping(value = "sys/baseMenu",method = RequestMethod.GET)
    public String menu(){
        return "sys/baseMenu/list";
    }
    @RequestMapping(value = "sys/baseMenu/edit",method = RequestMethod.GET)
    public String menuEdit(){
        return "sys/baseMenu/edit";
    }
    @RequestMapping(value = "sys/baseGroup",method = RequestMethod.GET)
    public String group(){
        return "sys/baseGroup/list";
    }
    @RequestMapping(value = "sys/baseGroup/baseUser",method = RequestMethod.GET)
    public String groupUser(){
        return "sys/baseGroup/baseUser";
    }
    @RequestMapping(value = "sys/baseGroup/baseAuthority",method = RequestMethod.GET)
    public String groupAuthority(){
        return "sys/baseGroup/baseAuthority";
    }
    @RequestMapping(value = "sys/baseGroup/edit",method = RequestMethod.GET)
    public String groupEdit(){
        return "sys/baseGroup/edit";
    }
    @RequestMapping(value = "sys/baseGroupType",method = RequestMethod.GET)
    public String groupType(){
        return "sys/baseGroupType/list";
    }
    @RequestMapping(value = "sys/baseGroupType/edit",method = RequestMethod.GET)
    public String groupTypeEdit(){
        return "sys/baseGroupType/edit";
    }
    @RequestMapping(value="sys/baseResource/edit",method = RequestMethod.GET)
    public String resourceEdit(){
        return "sys/baseResource/edit";
    }
    @RequestMapping(value = "sys/gateClient",method = RequestMethod.GET)
    public String gateClient(){
        return "sys/gateClient/list";
    }
    @RequestMapping(value = "sys/gateClient/edit",method = RequestMethod.GET)
    public String gateClientEdit(){
        return "sys/gateClient/edit";
    }
    @RequestMapping(value = "sys/gateClient/baseAuthority",method = RequestMethod.GET)
    public String gateClientAuthority(){
        return "sys/gateClient/baseAuthority";
    }
    @RequestMapping(value = "sys/gateLog",method = RequestMethod.GET)
    public String gateLog(){
        return "sys/gateLog/list";
    }
    @RequestMapping(value = "sys/gateService",method = RequestMethod.GET)
    public String service(){
        return "sys/gateService/list";
    }
    
    @RequestMapping(value = "sys/baseForm",method = RequestMethod.GET)
    public String form(){
        return "sys/baseForm/list";
    }
    
    @RequestMapping(value = "sys/baseForm/design",method = RequestMethod.GET)
    public String design(){
        return "sys/baseForm/design";
    }
    @RequestMapping(value="sys/baseForm/edit",method = RequestMethod.GET)
    public String formEdit(){
        return "sys/baseForm/edit";
    }
    
    

}
