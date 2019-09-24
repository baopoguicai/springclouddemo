package com.example.springclouddemouser.config;

import com.example.springclouddemouser.common.constants.Parameters;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class HttpSessionConfig {

    @Autowired
    private Parameters parameters;

    /**
     * 默认头header x-auth-token
     * @return
     */
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        String redisHost = parameters.getRedisNode().split(":")[0];
        int redisPort = Integer.valueOf(parameters.getRedisNode().split(":")[1]);
        connectionFactory.setTimeout(2000);
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(redisPort);
        connectionFactory.setPassword(parameters.getRedisAuth());
        return connectionFactory;
    }
}
