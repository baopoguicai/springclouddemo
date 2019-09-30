package com.example.springclouddemoscheduler.stock.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @Description 库存流水
 * @Author hnuya
 * @Date 2019/9/26
 **/
@Data
public class StockFlow {

    private Long id;

    private Long orderNo;

    private Long skuId;

    private Integer stockBefore;

    private Integer stockAfter;

    private Integer stockChange;

    private Integer lockStockBefore;

    private Integer lockStockAfter;

    private Integer lockStockChange;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;
}
