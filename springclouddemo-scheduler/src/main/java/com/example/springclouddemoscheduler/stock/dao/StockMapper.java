package com.example.springclouddemoscheduler.stock.dao;

import com.example.springclouddemostock.stock.entity.Stock;
import com.example.springclouddemostock.stock.entity.StockReduce;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    int reduceStock(StockReduce stockReduce);

    Stock selectBySkuId(Long skuId);

}
