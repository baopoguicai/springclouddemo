package com.example.springclouddemouser.message.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/24
 **/
@EnableBinding(Sink.class)
public class MessageRecieveService {

    @StreamListener(Sink.INPUT)
    public void recieveMsg(Object payload) {
        System.out.println("支付信息：" + payload);
    }
}
