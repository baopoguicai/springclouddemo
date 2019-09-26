package com.example.springclouddemotrade.trade.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 交易单
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class Trade {

    private Long id;

    private Long tradeNo;

    private Long userUuid;

    private BigDecimal payment;

    private Byte paymentType;

    private Byte status;

    private Date paymentTime;

    private Date closeTime;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;
}
