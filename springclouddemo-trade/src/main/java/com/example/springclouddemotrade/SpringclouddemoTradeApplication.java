package com.example.springclouddemotrade;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.springclouddemotrade.common.constants.Parameters;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.client.http.JestHttpClient;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableFeignClients
@EnableEurekaClient
public class SpringclouddemoTradeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringclouddemoTradeApplication.class, args);
	}

	@Autowired
	private Parameters parameters;

	/**
	 * 能用war进行部署
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringclouddemoTradeApplication.class);
	}


    /**
     * 消息转换传输
     * @return
     */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
	}

    /**
     * 用于properties文件占位符解析
     * @return
     */
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIndev() {
	    return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 创建elastic search客户端
     * @return
     */
    @Bean
    public JestHttpClient getESClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://" + parameters.getEsHost())
                .multiThreaded(true)
                .readTimeout(1000)
                .build());
        JestHttpClient client = (JestHttpClient) factory.getObject();
        return client;
    }

}
