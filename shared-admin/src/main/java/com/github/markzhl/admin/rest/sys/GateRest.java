package com.github.markzhl.admin.rest.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.entity.sys.GateClient;
import com.github.markzhl.admin.service.sys.BaseResourceService;
import com.github.markzhl.admin.service.sys.GateClientService;
import com.github.markzhl.vo.authority.PermissionInfo;
import com.github.markzhl.vo.gate.ClientInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author mark
 */
@Controller
@Slf4j
@RequestMapping("grcapi")
public class GateRest {
	@Autowired
	private GateClientService gateClientService;
	@Autowired
	private BaseResourceService baseResourceService;
//	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@HystrixCommand(fallbackMethod = "getGateClientInfoError")
	@RequestMapping(value = "/gate/clientid/{clientid}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ClientInfo getGateClientInfo(@PathVariable("clientid") String clientid) {
		Wrapper<GateClient> wrapper = new EntityWrapper<GateClient>(new GateClient());
		wrapper.where("code={0}", clientid);
		ClientInfo info = new ClientInfo();
		GateClient gateClient = gateClientService.selectOne(wrapper);
		BeanUtils.copyProperties(gateClient, info);
		info.setLocked(CommonConstant.BOOLEAN_NUMBER_TRUE.equals(gateClient.getLocked()));
//		info.setSecret(encoder.encode(info.getSecret()));
		return info;
	}
	
	public ClientInfo getGateClientInfoError(String clientid){
		log.error("根据clientid获取ClientInfo失败。clientid={}",clientid);
		return new ClientInfo();
	}
	
	
	
	@HystrixCommand(fallbackMethod = "getGateServiceInfoError")
	@RequestMapping(value = "/gate/permissions", method = RequestMethod.GET)
	public @ResponseBody List<PermissionInfo> getGateServiceInfo() {
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		Wrapper<BaseResource> wrapper = new EntityWrapper<BaseResource>(new BaseResource());
		wrapper.where("menu_id={0}", "-1");
		List<BaseResource> resources = baseResourceService.selectList(wrapper);
		convert(infos, resources);
		return infos;
	}
	
	public List<PermissionInfo> getGateServiceInfoError(){
		log.error("调用getGateServiceInfo失败。");
		return new ArrayList<PermissionInfo>();
	}
	

	@HystrixCommand(fallbackMethod = "getGateServiceInfoError")
	@RequestMapping(value = "/gate/ci/{clientid}/permissions", method = RequestMethod.GET)
	public @ResponseBody List<PermissionInfo> getGateServiceInfo(@PathVariable("clientid") String clientid) {
		Wrapper<GateClient> wrapper = new EntityWrapper<GateClient>(new GateClient());
		wrapper.where("code={0}", clientid);
		GateClient gateClient = gateClientService.selectOne(wrapper);
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		List<BaseResource> resources = gateClientService.getClientServices(gateClient.getId());
		convert(infos, resources);
		return infos;
	}
	
	public List<PermissionInfo> getGateServiceInfoError(String clientid) {
		log.error("调用getGateServiceInfo失败。参数：clientid={}", clientid);
		return new ArrayList<PermissionInfo>();
	}

	private void convert(List<PermissionInfo> infos, List<BaseResource> resources) {
		PermissionInfo info;
		for (BaseResource resource : resources) {
			info = new PermissionInfo();
			info.setCode(resource.getCode());
			info.setType(resource.getType());
			info.setUri(resource.getUri());
			info.setMethod(resource.getMethod());
			info.setName(resource.getName());
			infos.add(info);
		}
	}
}
