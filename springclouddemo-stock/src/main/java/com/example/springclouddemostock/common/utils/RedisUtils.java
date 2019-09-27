package com.example.springclouddemostock.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Description Redis工具
 * @Author hnuya
 * @Date 2019/9/27
 **/
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK_SUCCESS = "ok";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long EXCUTE_SUCCESS = 1L;

    //lua脚本，在redis中lua脚本执行时串行的 原子的
    private static final String UNLOCK_LUA =
            "if redis.call('get',KEYS[1]) == ARGV[1] then" +
            "   return redis.call('del',KEYS[1]) " +
            "else" +
            "   return 0" +
            "end";

    /**
     * 扣减库存lua脚本
     * return 0 key不存在 错误；-1 库存不足 返回list；扣减成功
     */
    public static final String STOCK_REDUCE_LUA =
            "local stock = KEYS[1]\n" +
            "local stock_lock = KEYS[2]\n" +
            "local stock_change = tonumber(ARGV[1])" +
            "local stock_lock_change = tonumber(ARGV[2])\n" +
            "local is_exists = redis.call(\"EXISTS\", stock)\n" +
            "if is_exists == 1 then\n" +
            "    local stockAftChange = redis.call(\"INCRBY\", stock,stock_change)\n" +
            "    if(stockAftChange<0) then\n" +
            "        redis.call(\"DECRBY\", stock,stock_change)\n" +
            "        return -1\n" +
            "    else \n" +
            "        local stockLockAftChange = redis.call(\"INCRBY\", stock_lock,stock_lock_change)\n" +
            "        return {stockAftChange,stockLockAftChange}\n" +
            "    end " +
            "else \n" +
            "    return 0\n" +
            "end";

    /**
     * 库存缓存lua脚本
     */
    public static final String STOCK_CACHE_LUA =
            "local stock = KEYS[1] " +
            "local stock_lock = KEYS[2] " +
            "local stock_val = tonumber(ARGV[1]) " +
            "local stock_lock_val = tonumber(ARGV[2]) " +
            "local is_exists = redis.call(\"EXISTS\", stock) " +
            "if is_exists == 1  then " +
            "   return 0 " +
            "else  " +
            "   redis.call(\"SET\", stock, stock_val) " +
            "   redis.call(\"SET\", stock_lock, stock_lock_val) " +
            "   return 1 " +
            "end";

    /**
     * 获取分布式锁
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public boolean distributeLock(String lockKey,String requestId,int expireTime) {
        String result = (String) redisTemplate.execute((RedisCallback<String>) redisConnection -> {
            //获取redis连接
            JedisCommands commands = (JedisCommands) redisConnection.getNativeConnection();
            //redis执行set命令
            return commands.set(lockKey,requestId,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,expireTime);
        });
        if(LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean releaseDistributeLock(String lockKey,String requestId) {
        Object result = redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            //jedis执行lua脚本
            return jedis.eval(UNLOCK_LUA, Collections.singletonList(lockKey),Collections.singletonList(requestId));
        });
        if(EXCUTE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 缓存sku库存，锁定库存
     * @param stockKey
     * @param stockLockKey
     * @param stock
     * @param stockLock
     * @return
     */
    public boolean skuStockInit(String stockKey,String stockLockKey,String stock,String stockLock) {
        Object result = redisTemplate.execute((RedisCallback<Object>) redisConnection ->{
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            return jedis.eval(STOCK_CACHE_LUA,Collections.unmodifiableList(Arrays.asList(stockKey,stockLockKey)),
                    Collections.unmodifiableList(Arrays.asList(stock,stockLock)));
        } );
        if(EXCUTE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 扣减库存
     * @param stockKey
     * @param stockLockKey
     * @param stockChange
     * @param stockLochChange
     * @return
     */
    public Object reduceStock(String stockKey,String stockLockKey,String stockChange,String stockLochChange) {
        Object result =  redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            return  jedis.eval(STOCK_REDUCE_LUA,Collections.unmodifiableList(Arrays.asList(stockKey,stockLockKey)),
                    Collections.unmodifiableList(Arrays.asList(stockChange,stockLochChange)));
        });
        return result;
    }
}
