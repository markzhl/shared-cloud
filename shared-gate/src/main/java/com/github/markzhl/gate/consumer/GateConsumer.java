package com.github.markzhl.gate.consumer;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.github.markzhl.api.IGateApi;

/**
 * 
 *
 * @author mark
 */
@FeignClient("admin-back")
public interface GateConsumer extends IGateApi{

}
