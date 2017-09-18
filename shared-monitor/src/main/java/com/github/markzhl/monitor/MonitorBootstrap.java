package com.github.markzhl.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * 
 *
 * @author mark
 */
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableAdminServer
@EnableDiscoveryClient
@EnableAutoConfiguration
public class MonitorBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(MonitorBootstrap.class, args);
    }
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }
}
