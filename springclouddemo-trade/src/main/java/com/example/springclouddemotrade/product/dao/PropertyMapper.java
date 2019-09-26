package com.example.springclouddemotrade.product.dao;

import com.example.springclouddemotrade.product.entity.Property;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Property record);

    int insertSelective(Property record);

    Property selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}
