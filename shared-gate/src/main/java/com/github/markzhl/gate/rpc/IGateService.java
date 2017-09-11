package com.github.markzhl.gate.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.api.vo.authority.PermissionInfo;
import com.github.markzhl.api.vo.gate.ClientInfo;

/**
 * 
 *
 * @author mark
 * @create 2017-07-02 19:23
 */
@FeignClient("admin-back")
@RequestMapping("api")
public interface IGateService {
	@RequestMapping(value = "/gate/clientid/{clientid}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ClientInfo getGateClientInfo(@PathVariable("clientid") String clientid);
	@RequestMapping(value = "/gate/permissions", method = RequestMethod.GET)
	public @ResponseBody List<PermissionInfo> getGateServiceInfo();
	@RequestMapping(value = "/gate/ci/{clientid}/permissions", method = RequestMethod.GET)
	public @ResponseBody List<PermissionInfo> getGateServiceInfo(@PathVariable("clientid") String clientid);
}
