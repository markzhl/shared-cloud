package com.github.markzhl.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.markzhl.gate.agent.feign.FeignInterceptor;

/**
 * 
 * @author mark
 */
@Configuration
public class FeignApiConfig {
    @Value("${gate.client.clientId}")
    private String clientId;
    @Value("${gate.client.secret}")
    private String secret;
    @Value("${gate.api.authHeader}")
    private String authHeader;
    @Value("${gate.api.authHost}")
    private String authHost;
    @Value("${gate.client.tokenHead}")
    private String tokenHead;

    @Bean
    public FeignInterceptor authenticationInterceptor() {
        return new FeignInterceptor(clientId, secret, authHeader, authHost, tokenHead);
    }

}
