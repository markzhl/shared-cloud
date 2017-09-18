package com.github.markzhl.ui;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 
 * @author mark
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@ServletComponentScan("com.github.markzhl.ui.ueditor")
public class UIBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UIBootstrap.class).web(true).run(args);    }
}
