package com.example.springclouddemotrade.product.dao;

import com.example.springclouddemotrade.product.entity.PropertyOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PropertyOptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropertyOption record);

    int insertSelective(PropertyOption record);

    PropertyOption selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PropertyOption record);

    int updateByPrimaryKey(PropertyOption record);
}

