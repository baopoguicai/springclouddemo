package com.example.springclouddemoscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringclouddemoSchedulerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringclouddemoSchedulerApplication.class, args);
	}

	/**
	 * 能放web容器启动
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringclouddemoSchedulerApplication.class);
	}



}
