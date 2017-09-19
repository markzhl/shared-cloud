package com.github.markzhl.ui.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.github.markzhl.api.IUserApi;


/**
 * 
 *
 * @author mark
 */
@FeignClient("admin-back")
public interface UserClient extends IUserApi{
	
}
