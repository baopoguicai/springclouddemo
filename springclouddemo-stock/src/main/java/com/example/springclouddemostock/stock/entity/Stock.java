package com.example.springclouddemostock.stock.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @Description 库存
 * @Author hnuya
 * @Date 2019/9/26
 **/
@Data
public class Stock {

    private Long id;

    private Long skuId;

    private Integer stock;

    private Integer locKStock;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;

}
