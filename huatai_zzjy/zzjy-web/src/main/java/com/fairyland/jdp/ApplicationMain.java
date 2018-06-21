package com.fairyland.jdp;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@ImportResource(locations = "classpath:applicationContext-cache.xml")
@SpringBootApplication(scanBasePackages = { "com.fairyland.jdp", "com.huatai.web" })
@EnableDiscoveryClient
@MapperScan(basePackages = { "com.huatai.web.mapper" })
@EnableTransactionManagement
@ServletComponentScan(basePackages = {"com.fairyland.jdp"})
public class ApplicationMain extends SpringBootServletInitializer {

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
				SerializerFeature.DisableCircularReferenceDetect);
		// 处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationMain.class);
	}

	public static void main(String[] args) {
		// System.setProperty("http.proxySet", "true"); //设置使用网络代理
		// System.setProperty("http.proxyHost", "127.0.0.1"); //设置代理服务器地址
		// System.setProperty("http.proxyPort", "8888");
		SpringApplication.run(ApplicationMain.class, args);
		// ThriftServerProxy thriftServer = new ThriftServerProxy();
		// thriftServer.start();
	}

}
