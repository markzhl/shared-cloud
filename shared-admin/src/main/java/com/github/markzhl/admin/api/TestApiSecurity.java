package com.github.markzhl.admin.api;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.User;
import com.github.markzhl.admin.service.UserService;
import com.github.markzhl.api.vo.user.UserInfo;
import com.github.markzhl.gate.agent.rest.ApiGateSecurity;

@Controller
@RequestMapping("apisec")
@ApiGateSecurity
public class TestApiSecurity {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/user/usernameforapi/{username}",method = RequestMethod.GET, produces="application/json")
    public  @ResponseBody UserInfo getUserByUsernameForApi(@PathVariable("username")String username) {
        UserInfo info = new UserInfo();
        User user = userService.getUserByUsername(username);
        assertNotNull(user);
        BeanUtils.copyProperties(user,info);
        info.setId(user.getId().toString());
        return info;
    }

}
