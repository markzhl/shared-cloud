package com.github.markzhl.gate.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.markzhl.vo.authority.PermissionInfo;
import com.github.markzhl.vo.gate.ClientInfo;

/**
 * 
 *
 * @author mark
 */
@FeignClient("admin-back")
@RequestMapping("grcapi")
public interface IGateClient {
	@RequestMapping(value = "/gate/clientid/{clientid}",method = RequestMethod.GET, produces="application/json")
	public ClientInfo getGateClientInfo(@PathVariable("clientid") String clientid);
	
	@RequestMapping(value = "/gate/permissions", method = RequestMethod.GET)
	public List<PermissionInfo> getGateServiceInfo();
	
	@RequestMapping(value = "/gate/ci/{clientid}/permissions", method = RequestMethod.GET)
	public List<PermissionInfo> getGateServiceInfo(@PathVariable("clientid") String clientid);
}
