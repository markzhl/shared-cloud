package com.github.markzhl.gate.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.markzhl.api.vo.log.LogInfo;

/**
 * 
 *
 * @author mark
 * @create 2017-07-01 15:16
 */
@FeignClient("admin-back")
public interface ILogService {
  @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
  public void saveLog(LogInfo info);
}
