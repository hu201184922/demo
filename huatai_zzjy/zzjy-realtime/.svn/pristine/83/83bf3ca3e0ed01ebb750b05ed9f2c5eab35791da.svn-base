package com.fairyland.jdp.springboot.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourcesConfig {
	@Primary
	@Bean(name="dataSource1")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource1(){
		DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource;
	}
	@Bean(name="dataSource2")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource dataSource2(){
		DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource;
	}
	@Bean(name="dataSource3")
	@ConfigurationProperties(prefix = "spring.datasource3")
	public DataSource dataSource3(){
		DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource;
	}
}
