package com.example.springclouddemostock.stock.controller;

import com.example.springclouddemostock.common.constants.Constants;
import com.example.springclouddemostock.common.resp.ApiResult;
import com.example.springclouddemostock.stock.entity.Stock;
import com.example.springclouddemostock.stock.entity.StockReduce;
import com.example.springclouddemostock.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description 库存
 * @Author hnuya
 * @Date 2019/9/27
 **/
@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    @Qualifier("stockServiceImpl")
    private StockService stockService;

    /**
     * 多商品扣减库存
     * @param stockReduceList
     * @return
     */
    @RequestMapping("/reduce")
    public ApiResult<Map<Long,Integer>> reduceStock(@RequestBody List<StockReduce> stockReduceList) {
        ApiResult result = new ApiResult(Constants.RESP_STATUS_OK,"库存扣减成功");
        Map<Long,Integer> resultMap = stockService.reduceStock(stockReduceList);
        result.setData(resultMap);
        return result;
    }

    /**
     * 查询单个商品库存
     * @param skuId
     * @return
     */
    @RequestMapping("/query/{skuId}")
    public ApiResult<Stock> queryStock(@PathVariable Long skuId) {
        ApiResult<Stock> result = new ApiResult<>(Constants.RESP_STATUS_OK,"查询库存成功");
        Stock stock = new Stock();
        stock.setSkuId(skuId);
        int stockCount = stockService.queryStock(skuId);
        stock.setStock(stockCount);
        result.setData(stock);
        return result;
    }

}
