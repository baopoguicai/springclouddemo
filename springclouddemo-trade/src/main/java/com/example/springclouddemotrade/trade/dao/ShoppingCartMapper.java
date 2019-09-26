package com.example.springclouddemotrade.trade.dao;

import com.example.springclouddemotrade.trade.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}
