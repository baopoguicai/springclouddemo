package com.example.springclouddemotrade.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @Description 商品属性
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class Property {

    private Long id;

    private Long categoryId;

    private String propertyName;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;
}
