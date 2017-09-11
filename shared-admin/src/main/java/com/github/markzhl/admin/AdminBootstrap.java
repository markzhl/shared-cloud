package com.github.markzhl.admin;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 
 *
 * @author mark
 * @create 2017-05-25 12:44
 */
@EnableDiscoveryClient  //激活eureka中的DiscoveryClient实现
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
@ServletComponentScan("com.github.markzhl.admin.config.druid")
@EnableAutoConfiguration
//@EnableRedisHttpSession
public class AdminBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminBootstrap.class).web(true).run(args);    }
}
