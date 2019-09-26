package com.example.springclouddemotrade.trade.feign;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/25
 **/
public class KeyGeneratorServiceFallback implements KeyGeneratorServiceClient{
    @Override
    public String keyGenerator() {
        return null;
    }
}
