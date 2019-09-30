package com.example.springclouddemoscheduler.stock.dao;

import com.example.springclouddemostock.stock.entity.StockFlow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockFlowMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockFlow record);

    int insertSelective(StockFlow record);

    StockFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockFlow record);

    int updateByPrimaryKey(StockFlow record);

}
