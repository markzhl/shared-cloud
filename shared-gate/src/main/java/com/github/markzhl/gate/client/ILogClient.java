package com.github.markzhl.gate.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.markzhl.vo.log.LogInfo;

/**
 * 
 *
 * @author mark
 */
@FeignClient("admin-back")
@RequestMapping("grcapi")
public interface ILogClient {
	@RequestMapping(value="/log/save",method = RequestMethod.POST)
	public void saveLog(LogInfo info);
}
