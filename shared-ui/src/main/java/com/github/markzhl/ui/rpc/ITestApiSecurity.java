package com.github.markzhl.ui.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.api.vo.user.UserInfo;

@FeignClient("admin-back")
@RequestMapping("apisec")
public interface ITestApiSecurity {
	@RequestMapping(value = "/user/usernameforapi/{username}",method = RequestMethod.GET, produces="application/json")
    public  @ResponseBody UserInfo getUserByUsernameForApi(@PathVariable("username")String username);
}
