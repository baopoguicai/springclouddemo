package com.example.springclouddemotrade.trade.feign;

import com.example.springclouddemotrade.common.constants.Constants;
import com.example.springclouddemotrade.common.resp.ApiResult;
import com.example.springclouddemotrade.trade.entity.StockReduce;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/25
 **/
public class StockServiceFallback implements StockServiceClient{
    @Override
    public ApiResult<Map<Long, Integer>> reduceStock(List<StockReduce> stockReduceList) {
        ApiResult<Map<Long,Integer>> result = new ApiResult<>(Constants.RESP_STATUS_INTERNAL_ERROR,"请求失败，请稍后重试");
        result.setData(Collections.emptyMap());
        return result;
    }
}
