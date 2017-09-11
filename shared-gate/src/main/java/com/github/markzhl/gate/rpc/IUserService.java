package com.github.markzhl.gate.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.api.vo.authority.PermissionInfo;
import com.github.markzhl.api.vo.user.UserInfo;


/**
 * 
 *
 * @author mark
 * @create 2017-06-21 8:11
 */
@FeignClient("admin-back")
@RequestMapping("api")
public interface IUserService {
  @RequestMapping(value = "/user/username/{username}", method = RequestMethod.GET)
  public UserInfo getUserByUsername(@PathVariable("username") String username);
  @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
  public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);
  @RequestMapping(value = "/permissions", method = RequestMethod.GET)
  List<PermissionInfo> getAllPermissionInfo();
  
}
