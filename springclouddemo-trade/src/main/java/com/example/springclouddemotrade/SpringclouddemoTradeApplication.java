package com.example.springclouddemotrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableFeignClients
@EnableEurekaClient
public class SpringclouddemoTradeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringclouddemoTradeApplication.class, args);
	}

	/**
	 * 能用war进行部署
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringclouddemoTradeApplication.class);
	}



}
