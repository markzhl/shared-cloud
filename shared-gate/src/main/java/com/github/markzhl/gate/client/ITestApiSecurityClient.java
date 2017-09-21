package com.github.markzhl.gate.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.markzhl.vo.user.UserInfo;

@FeignClient("admin-back")
@RequestMapping("grcapi")
public interface ITestApiSecurityClient {
	@RequestMapping(value = "/testsec/username/{username}",method = RequestMethod.GET)
	public UserInfo testApiGateSecurity(@PathVariable("username")String username);
}
