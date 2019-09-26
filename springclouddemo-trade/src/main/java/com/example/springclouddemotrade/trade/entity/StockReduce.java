package com.example.springclouddemotrade.trade.entity;

import lombok.Data;

/**
 * @Description 库存增减
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class StockReduce {

    private Long orderNo;

    private Long skuId;

    private Integer reduceCount;//正数为增加/释放库存  负数为扣减
}
