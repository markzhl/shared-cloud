package com.github.markzhl.gate.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.markzhl.vo.authority.PermissionInfo;
import com.github.markzhl.vo.user.UserInfo;


/**
 * 
 *
 * @author mark
 */
@FeignClient("admin-back")
@RequestMapping("grcapi")
public interface IUserClient {
	@RequestMapping(value = "/user/username/{username}", method = RequestMethod.GET)
	public UserInfo getUserByUsername(@PathVariable("username") String username);
	@RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
	public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	List<PermissionInfo> getAllPermissionInfo();
	
}