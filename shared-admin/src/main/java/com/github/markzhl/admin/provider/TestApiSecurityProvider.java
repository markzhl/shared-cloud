package com.github.markzhl.admin.provider;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.User;
import com.github.markzhl.admin.service.UserService;
import com.github.markzhl.api.ITestApiSecurityApi;
import com.github.markzhl.gate.agent.rest.ApiGateSecurity;
import com.github.markzhl.vo.user.UserInfo;

@Controller
@ApiGateSecurity
public class TestApiSecurityProvider implements ITestApiSecurityApi{
    @Autowired
    private UserService userService;
    
    public @ResponseBody UserInfo testApiGateSecurity(@PathVariable("username")String username) {
        UserInfo info = new UserInfo();
        User user = userService.getUserByUsername(username);
        assertNotNull(user);
        BeanUtils.copyProperties(user,info);
        info.setId(user.getId().toString());
        return info;
    }

}
