package com.fairyland.jdp.springboot.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fairyland.jdp.orm.mybatis.util.RoutingDataSource;

@Configuration
public class RdDataSourceConfig {
	@Resource(name="dataSource1")
	private DataSource dataSource1;
	@Resource(name="dataSource2")
	private DataSource dataSource2;
//	@Resource(name = "dataSource3")
//	private DataSource dataSource3;
	@Bean
	public RoutingDataSource rddatasource(){
		RoutingDataSource routingDataSource = new RoutingDataSource();
		Map<Object,Object> targetDataSources = new HashMap<Object,Object>();
//		DataSource defaultTargetDataSource = dataSource1();
		targetDataSources.put("dataSource", dataSource1);
		targetDataSources.put("dataSource2", dataSource2);
//		targetDataSources.put("dataSource3", dataSource3);
		routingDataSource.setTargetDataSources(targetDataSources);
		routingDataSource.setDefaultTargetDataSource(dataSource1);
		return routingDataSource;
	}
	
}
