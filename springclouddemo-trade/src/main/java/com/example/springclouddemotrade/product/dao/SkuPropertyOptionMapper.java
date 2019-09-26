package com.example.springclouddemotrade.product.dao;

import com.example.springclouddemotrade.product.entity.SkuPropertyOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkuPropertyOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuPropertyOption record);

    int insertSelective(SkuPropertyOption record);

    SkuPropertyOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuPropertyOption record);

    int updateByPrimaryKey(SkuPropertyOption record);
}
