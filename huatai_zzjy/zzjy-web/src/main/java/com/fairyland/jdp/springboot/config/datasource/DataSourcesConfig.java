package com.fairyland.jdp.springboot.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourcesConfig {
//	@Bean(name="dataSource1")
//	@Primary
//	public DataSource dataSource1(){
//		return buildDataSource("spring.datasource");
//	}
//	@Bean(name="dataSource2")
//	public DataSource dataSource2(){
//		return buildDataSource("spring.datasource2");
//	}
	@Primary
	@Bean(name="dataSource1")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource1(){
//		return DataSourceBuilder.create().build();
		DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource; 
	}
	@Bean(name="dataSource2")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource dataSource2(){
//		return DataSourceBuilder.create().build();
		DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource; 
	}
//	@Bean(name="dataSource3")
//	@ConfigurationProperties(prefix = "spring.datasource3")
//	public DataSource dataSource3(){
//		DruidDataSource druidDataSource = new DruidDataSource();  
//        return druidDataSource;
//	}
//	private DataSource buildDataSource(String prefix){
//		String jndi = PropsUtil.get(prefix+".jndi-name");
//		if(jndi!=null){
//			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//			DataSource dataSource = dataSourceLookup.getDataSource(jndi);
//			return dataSource;
//		}
//		else{
//			String url = PropsUtil.get(prefix+".url");
//			String username = PropsUtil.get(prefix+".username");
//			String password = PropsUtil.get(prefix+".password");
//			String driverClassName = PropsUtil.get(prefix+".driverClassName");
//			return DataSourceBuilder.create().url(url).username(username).password(password).driverClassName(driverClassName).build();
//		}
//	}
}
