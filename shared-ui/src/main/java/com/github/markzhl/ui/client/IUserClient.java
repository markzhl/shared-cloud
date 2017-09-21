package com.github.markzhl.ui.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = "/user/un/{username}/system", method = RequestMethod.GET)
	public String getSystemsByUsername(@PathVariable("username") String username);
	@RequestMapping(value = "/user/un/{username}/menu/parent/{parentId}", method = RequestMethod.GET)
	public String getMenusByUsername(@PathVariable("username") String username,@PathVariable("parentId") Long parentId);
	@RequestMapping(value = "/user/un/{username}/pw/{pasword}/match", method = RequestMethod.GET)
	public Boolean isMatchByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("pasword") String pasword);
	
}
