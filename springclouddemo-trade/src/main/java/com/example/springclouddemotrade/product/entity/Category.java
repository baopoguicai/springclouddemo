package com.example.springclouddemotrade.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 类别，种类
 * @Author hnuya
 * @Date 2019/9/25
 **/
@Data
public class Category implements Serializable{

    private Long id;

    private Long parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;//分类顺序

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;

    private List<Category> children;

}
