package com.github.markzhl.admin.rest.sys;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.entity.sys.BaseUser;
import com.github.markzhl.admin.service.sys.BaseUserService;
import com.github.markzhl.vo.user.UserInfo;

@Controller
@RequestMapping("grcapi")
public class TestApiSecurityRest{
    @Autowired
    private BaseUserService baseUserService;
    
	@RequestMapping(value = "/testsec/username/{username}",method = RequestMethod.GET)
    public @ResponseBody UserInfo testApiGateSecurity(@PathVariable("username")String username) {
        UserInfo info = new UserInfo();
        BaseUser user = baseUserService.getUserByUsername(username);
        assertNotNull(user);
        BeanUtils.copyProperties(user,info);
        info.setId(user.getId().toString());
        return info;
    }

}
