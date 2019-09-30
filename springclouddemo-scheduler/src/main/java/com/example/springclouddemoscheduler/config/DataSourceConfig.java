package com.example.springclouddemoscheduler.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/27
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "stockDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.stock")
    public DataSource  stockDataSource() {
        return DataSourceBuilder.create().build();
    }

}
