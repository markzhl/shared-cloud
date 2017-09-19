package com.github.markzhl.admin.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Resource;
import com.github.markzhl.admin.entity.GateClient;
import com.github.markzhl.admin.service.ResourceService;
import com.github.markzhl.admin.service.GateClientService;
import com.github.markzhl.api.IGateApi;
import com.github.markzhl.vo.authority.PermissionInfo;
import com.github.markzhl.vo.gate.ClientInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 */
@Controller
@Slf4j
public class GateProvider implements IGateApi{
	@Autowired
	private GateClientService gateClientService;
	@Autowired
	private ResourceService elmentService;
//	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@HystrixCommand(fallbackMethod = "getGateClientInfoError")
	public @ResponseBody ClientInfo getGateClientInfo(@PathVariable("clientid") String clientid) {
		Example example = new Example(GateClient.class);
		example.createCriteria().andEqualTo("code", clientid);
		ClientInfo info = new ClientInfo();
		GateClient gateClient = gateClientService.selectByExample(example).get(0);
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
	public @ResponseBody List<PermissionInfo> getGateServiceInfo() {
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		Example example = new Example(Resource.class);
		example.createCriteria().andEqualTo("menuId", "-1");
		List<Resource> resources = elmentService.selectByExample(example);
		convert(infos, resources);
		return infos;
	}
	
	public List<PermissionInfo> getGateServiceInfoError(){
		log.error("调用getGateServiceInfo失败。");
		return new ArrayList<PermissionInfo>();
	}
	

	@HystrixCommand(fallbackMethod = "getGateServiceInfoError")
	public @ResponseBody List<PermissionInfo> getGateServiceInfo(@PathVariable("clientid") String clientid) {
		GateClient gateClient = new GateClient();
		gateClient.setCode(clientid);
		gateClient = gateClientService.selectOne(gateClient);
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		List<Resource> resources = gateClientService.getClientServices(gateClient.getId());
		convert(infos, resources);
		return infos;
	}
	
	public List<PermissionInfo> getGateServiceInfoError(String clientid) {
		log.error("调用getGateServiceInfo失败。参数：clientid={}", clientid);
		return new ArrayList<PermissionInfo>();
	}

	private void convert(List<PermissionInfo> infos, List<Resource> resources) {
		PermissionInfo info;
		for (Resource resource : resources) {
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
