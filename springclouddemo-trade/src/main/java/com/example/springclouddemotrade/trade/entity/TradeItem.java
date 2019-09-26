package com.example.springclouddemotrade.trade.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 交易对象内容
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class TradeItem {

    private Long id;

    private Long userUuid;

    private Long tradeNo;

    private Long skuId;

    private String skuName;

    private String skuImageUrl;

    private BigDecimal currentPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;


}
