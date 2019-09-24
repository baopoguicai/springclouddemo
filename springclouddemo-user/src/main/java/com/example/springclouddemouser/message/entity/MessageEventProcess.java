package com.example.springclouddemouser.message.entity;

import lombok.Data;
import scala.Serializable;
import scala.util.control.Exception;

import java.util.Date;

/**
 * @Description 消息事件体
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Data
public class MessageEventProcess implements Serializable{

    private Long id;

    private Byte status;

    private String payload;

    private Date createTime;

    private Byte type;

}
