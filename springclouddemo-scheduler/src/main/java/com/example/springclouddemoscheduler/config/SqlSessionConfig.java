package com.example.springclouddemoscheduler.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/27
 **/
@Configuration
@MapperScan(basePackages = "com.example.springclouddemo.stock.dao",sqlSessionFactoryRef = "stockSqlSessionFactory")
public class SqlSessionConfig {

    @Autowired
    @Qualifier("stockDataSource")
    private DataSource stockDataSource;
}
