package com.example.springclouddemouser.common.constants;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description 系统参数
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Component
@Data
public class Parameters {

    /**Redis config start**/
    @Value("${redis.node}")
    private String redisNode;

    @Value("${redis.password}")
    private String redisAuth;
    /**Redis config end**/

    /**zk config end**/
    @Value("${zk.host}")
    private String zkHost;

}
