package com.example.springclouddemotrade.product.entity;

import lombok.Data;

/**
 * @Description 分页搜索
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class PageSearch {

    private int pageNum;

    private int pageSize;

    private String searchContent;
}
