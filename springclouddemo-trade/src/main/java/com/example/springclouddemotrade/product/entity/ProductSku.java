package com.example.springclouddemotrade.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 商品sku
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class ProductSku {

    private Long id;

    private Long skuId;

    private String skuName;

    private BigDecimal skuPrice;

    private String imgUrl;

    private Byte enableFlag;

    private Byte status;

    private List<SkuPropertyOption> skuPropertyOptions;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;
}
