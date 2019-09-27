package com.example.springclouddemostock.stock.service;

import com.example.springclouddemostock.stock.entity.StockReduce;

import java.util.List;
import java.util.Map;

public interface StockService {

    Map<Long,Integer> reduceStock(List<StockReduce> stockReduceList);

    int queryStock(long skuId);
}
