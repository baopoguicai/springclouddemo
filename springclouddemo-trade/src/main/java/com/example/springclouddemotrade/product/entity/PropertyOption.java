package com.example.springclouddemotrade.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @Description 属性选项
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class PropertyOption {

    private Long id;

    private Long propertyId;

    private String optionName;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;

}
