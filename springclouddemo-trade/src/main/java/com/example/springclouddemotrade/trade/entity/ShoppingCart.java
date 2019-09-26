package com.example.springclouddemotrade.trade.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @Description 购物车
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class ShoppingCart {
    private Long id;

    private Long userUuid;

    private Long skuId;

    private String skuName;

    private Byte checkStatus;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;

}
