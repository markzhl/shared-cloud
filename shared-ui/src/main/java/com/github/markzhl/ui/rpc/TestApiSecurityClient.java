package com.github.markzhl.ui.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.github.markzhl.api.ITestApiSecurityApi;

@FeignClient("admin-back")
public interface TestApiSecurityClient extends ITestApiSecurityApi{
	
}
