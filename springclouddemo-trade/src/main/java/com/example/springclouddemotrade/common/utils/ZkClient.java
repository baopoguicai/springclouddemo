package com.example.springclouddemotrade.common.utils;

import com.example.springclouddemotrade.common.constants.Parameters;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description zookeeper客户端curator
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Component
public class ZkClient {

    @Autowired
    private Parameters parameters;

    @Bean
    CuratorFramework getZkClient(){
        CuratorFrameworkFactory.Builder builder= CuratorFrameworkFactory.builder()
                .connectString(parameters.getZkHost())
                .connectionTimeoutMs(3000)
                .retryPolicy(new RetryNTimes(5, 10));
        CuratorFramework framework = builder.build();
        framework.start();
        return framework;

    }



}
