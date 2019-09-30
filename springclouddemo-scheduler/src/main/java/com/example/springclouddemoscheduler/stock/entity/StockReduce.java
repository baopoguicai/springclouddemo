package com.example.springclouddemoscheduler.stock.entity;

import lombok.Data;

/**
 * @Description 库存增减
 * @Author hnuya
 * @Date 2019/9/26
 **/
@Data
public class StockReduce {

    private Long orderNo;

    private Long skuId;

    private Integer reduceCount;//正数为增加/释放库存  负数为扣减/减少库存

}
