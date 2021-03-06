package com.github.markzhl.gate.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

/**
 * 
 *
 * @author mark
 */
@Configuration
public class SessionConfig {
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
