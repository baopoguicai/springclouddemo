package com.example.springclouddemotrade.trade.service;

import com.example.springclouddemotrade.trade.entity.TradeItem;

import java.util.List;

public interface TradeService {
    List<TradeItem> createOrder(List<TradeItem> tradeItemList);

    void payOrder(String out_trade_no);
}
