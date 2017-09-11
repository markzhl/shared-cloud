package com.github.markzhl.admin.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.markzhl.admin.biz.ElementBiz;
import com.github.markzhl.admin.biz.GateClientBiz;
import com.github.markzhl.admin.constant.CommonConstant;
import com.github.markzhl.admin.entity.Element;
import com.github.markzhl.admin.entity.GateClient;
import com.github.markzhl.api.vo.authority.PermissionInfo;
import com.github.markzhl.api.vo.gate.ClientInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 *
 * @author mark
 * @create 2017-07-02 19:23
 */
@Controller
@RequestMapping("api")
@Slf4j
public class GateService {
	@Autowired
	private GateClientBiz gateClientBiz;
	@Autowired
	private ElementBiz elmentBiz;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@RequestMapping(value = "/gate/clientid/{clientid}",method = RequestMethod.GET, produces="application/json")
	@HystrixCommand(fallbackMethod = "getGateClientInfoError")
	public @ResponseBody ClientInfo getGateClientInfo(@PathVariable("clientid") String clientid) {
		Example example = new Example(GateClient.class);
		example.createCriteria().andEqualTo("code", clientid);
		ClientInfo info = new ClientInfo();
		GateClient gateClient = gateClientBiz.selectByExample(example).get(0);
		BeanUtils.copyProperties(gateClient, info);
		info.setLocked(CommonConstant.BOOLEAN_NUMBER_TRUE.equals(gateClient.getLocked()));
//		info.setSecret(encoder.encode(info.getSecret()));
		return info;
	}
	
	public ClientInfo getGateClientInfoError(String clientid){
		log.error("根据clientid获取ClientInfo失败。clientid={}",clientid);
		return new ClientInfo();
	}
	
	
	
	@RequestMapping(value = "/gate/permissions", method = RequestMethod.GET)
	public @ResponseBody List<PermissionInfo> getGateServiceInfo() {
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		Example example = new Example(Element.class);
		example.createCriteria().andEqualTo("menuId", "-1");
		List<Element> elements = elmentBiz.selectByExample(example);
		convert(infos, elements);
		return infos;
	}

	@RequestMapping(value = "/gate/ci/{clientid}/permissions", method = RequestMethod.GET)
	public @ResponseBody List<PermissionInfo> getGateServiceInfo(@PathVariable("clientid") String clientid) {
		GateClient gateClient = new GateClient();
		gateClient.setCode(clientid);
		gateClient = gateClientBiz.selectOne(gateClient);
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		List<Element> elements = gateClientBiz.getClientServices(gateClient.getId());
		convert(infos, elements);
		return infos;
	}

	private void convert(List<PermissionInfo> infos, List<Element> elements) {
		PermissionInfo info;
		for (Element element : elements) {
			info = new PermissionInfo();
			info.setCode(element.getCode());
			info.setType(element.getType());
			info.setUri(element.getUri());
			info.setMethod(element.getMethod());
			info.setName(element.getName());
			infos.add(info);
		}
	}
}
