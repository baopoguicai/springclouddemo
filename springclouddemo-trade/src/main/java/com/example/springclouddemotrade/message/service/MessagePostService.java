package com.example.springclouddemotrade.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;



/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/25
 **/
@EnableBinding(Source.class)
public class MessagePostService {

    @Autowired
    private Source source;

    public void postMsg(String message){
        source.output().send(MessageBuilder.withPayload(message).build());
    }

}
