package com.github.markzhl.gate;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import com.github.markzhl.gate.utils.DBLog;

/**
 * 
 * @author mark
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableZuulProxy
//使用redis做httpsession存储
//@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
//不使用redis做httpsession存储
@EnableSpringHttpSession
public class GateBootstrap {
    public static void main(String[] args) {
        DBLog.getInstance().start();
        SpringApplication.run(GateBootstrap.class, args);
    }
    @Bean
    public MapSessionRepository sessionRepository() {
            return new MapSessionRepository();
    }
}
