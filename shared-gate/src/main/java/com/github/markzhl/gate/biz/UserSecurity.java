package com.github.markzhl.gate.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.github.markzhl.gate.client.IUserClient;
import com.github.markzhl.vo.user.UserInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 *
 * @author mark
 */
@Service
public class UserSecurity {
    @Lazy
    @Autowired
    private IUserClient userService;

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public UserInfo getUserByUsername(String username){
        return userService.getUserByUsername(username);
    }
    public UserInfo fallbackMethod(String username){
        return new UserInfo();
    }
}
