package com.example.springclouddemotrade.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @Description sku属性选项
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class SkuPropertyOption {

    private Long id;

    private Long skuId;

    private Long propertyId;

    private Long propertyOptionId;

    private Byte enableFlag;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;
}
