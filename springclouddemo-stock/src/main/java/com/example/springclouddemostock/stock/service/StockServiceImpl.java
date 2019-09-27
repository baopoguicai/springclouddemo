package com.example.springclouddemostock.stock.service;

import com.example.springclouddemostock.common.constants.Constants;
import com.example.springclouddemostock.common.utils.RedisUtils;
import com.example.springclouddemostock.stock.dao.StockFlowMapper;
import com.example.springclouddemostock.stock.dao.StockMapper;
import com.example.springclouddemostock.stock.entity.Stock;
import com.example.springclouddemostock.stock.entity.StockFlow;
import com.example.springclouddemostock.stock.entity.StockReduce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/27
 **/
@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockFlowMapper stockFlowMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 扣减库存
     * @param stockReduceList
     * @return
     */
    @Transactional
    @Override
    public Map<Long, Integer> reduceStock(List<StockReduce> stockReduceList) {
        //以订单ID价格缓存锁  防止程序短时间重试 重复扣减库存 不用解锁 自己超时
        Long orderNo = stockReduceList.get(0).getOrderNo();
        boolean lockResult = redisUtils.distributeLock(Constants.ORDER_RETRY_LOCK+orderNo.toString(),orderNo.toString(),300000);
        if(!lockResult) {
            //锁定失败，重复提交
            return Collections.emptyMap();
        }
        Map<Long,Integer> resultMap = new HashMap<>();
        //扣减每个商品的redis库存，扣减流水插入流水表
        stockReduceList.stream().forEach(param ->{
            String stockKey = Constants.CACHE_PRODUCT_STOCK + ":"+ param.getSkuId();
            String stockLockKey = Constants.CACHE_PRODUCT_STOCK_LOCK +":"+ param.getSkuId();
            Object result = redisUtils.reduceStock(stockKey,stockLockKey,param.getReduceCount().toString(),String.valueOf(Math.abs(param.getReduceCount())));
            if(result instanceof  Long) {
                //库存不存在或者不足，扣减失败 sku下单失败 记录下来
                resultMap.put(param.getSkuId(),-1);
            } else if(result instanceof  List) {
                //扣减成功 记录扣减流水
                List resultList = (List) result;
                int stockAfterChange = ((Long)resultList.get(0)).intValue();
                int stockLockAfterChange = ((Long)resultList.get(1)).intValue();
                StockFlow stockFlow = new StockFlow();
                stockFlow.setOrderNo(param.getOrderNo());
                stockFlow.setSkuId(param.getSkuId());
                stockFlow.setLockStockAfter(stockLockAfterChange);
                stockFlow.setLockStockBefore(stockLockAfterChange + param.getReduceCount());
                stockFlow.setLockStockChange(Math.abs(param.getReduceCount()));

                stockFlow.setStockAfter(stockAfterChange);
                stockFlow.setStockBefore(stockAfterChange +  Math.abs(param.getReduceCount()));
                stockFlow.setStockChange(param.getReduceCount());

                stockFlowMapper.insertSelective(stockFlow);
                resultMap.put(param.getSkuId(),-1);
            }
        });
        return resultMap;
    }


    /**
     * 查询库存
     * @param skuId
     * @return
     */
    @Override
    public int queryStock(long skuId) {
        //先查Redis
        Stock stock;
        String stockKey = Constants.CACHE_PRODUCT_STOCK + ":" + skuId;
        String stockLockKey = Constants.CACHE_PRODUCT_STOCK_LOCK + ":" + skuId;
        Object stockObj = redisTemplate.opsForValue().get(stockKey);
        Integer stockInRedis = null;
        if(stockObj != null) {
            stockInRedis = Integer.valueOf(stockObj.toString());
        }
        if(stockInRedis == null) {
            //redis中为空，初始化一次
            stock = stockMapper.selectBySkuId(skuId);
            redisUtils.skuStockInit(stockKey,stockLockKey,stock.getStock().toString(),stock.getLocKStock().toString());
        } else {
            return  stockInRedis;
        }
        return stock.getStock();
    }
}
