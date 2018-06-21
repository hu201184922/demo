package com.fairyland.jdp.springboot.config.mybatis;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.fairyland.jdp.orm.mybatis.SqlSessionFactoryBean;
import com.fairyland.jdp.orm.mybatis.dialect.Dialect;
import com.fairyland.jdp.orm.mybatis.dialect.DialectProxy;
import com.fairyland.jdp.orm.mybatis.interceptors.LimitInterceptor;
import com.fairyland.jdp.orm.mybatis.util.RoutingDataSource;
import com.fairyland.jdp.orm.mybatis.util.interceptor.MultiDataSourceInterceptor;
import com.fairyland.jdp.springboot.util.ResourceUtil;

@Configuration
@ComponentScan
public class MybatisConfig {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private RoutingDataSource rddatasource;
	
	@Value("${spring.mybatis.base-package-domain}")
	private String basePackageDomain;
	
	@Value("${spring.mybatis.base-package-mapper}")
	private String basePackageMapper;
	
	@Value("${spring.mybatis.data-source-types}")
	private String dataSourceTypes;
	
	@Qualifier("mybatis")
	@Bean
	public DataSourceTransactionManager mybatistransactionManager(){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}
	@Bean
	public DialectProxy dialectProxy(){
		DialectProxy dialectProxy = new DialectProxy();
		Map<String,Dialect> targetDialects = new HashMap<String,Dialect>();
		targetDialects.put("oracle", new com.fairyland.jdp.orm.mybatis.dialect.OraSQLDialect());
		targetDialects.put("mysql", new com.fairyland.jdp.orm.mybatis.dialect.MySQLDialect());
		dialectProxy.setTargetDialects(targetDialects);
		Map<String,String> dataSourceTypesMap = new HashMap<String,String>();
		String[] dataSourceTypesArr = dataSourceTypes.split(",");
		for (String dataSourceType : dataSourceTypesArr) {
			String[] keyValue = dataSourceType.split(":");
			dataSourceTypesMap.put(keyValue[0], keyValue[1]);
		}
		dialectProxy.setDataSourceTypes(dataSourceTypesMap);
		dialectProxy.setDefaultDialects("oracle");
		return dialectProxy;
	}
	@Bean
	public LimitInterceptor limitInterceptor(){
		LimitInterceptor limitInterceptor = new LimitInterceptor();
		limitInterceptor.setDialect(dialectProxy());
		return limitInterceptor;
	}
	@Bean
	public MultiDataSourceInterceptor multiDataSourceInterceptor(){
		MultiDataSourceInterceptor multiDataSourceInterceptor = new MultiDataSourceInterceptor();
		return multiDataSourceInterceptor;
	}
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setConfigLocation(
				new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
		sqlSessionFactoryBean.setDataSource(rddatasource);
		sqlSessionFactoryBean.setTypeAliasesPackage(basePackageDomain);
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{limitInterceptor(),multiDataSourceInterceptor()});
		String[] basePackageMapperArr = basePackageMapper.split(",");
		sqlSessionFactoryBean.setMapperLocations(
				ResourceUtil.getResources(basePackageMapperArr));
		return sqlSessionFactoryBean;
	}
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer(){
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setAnnotationClass(com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository.class);
//		mapperScannerConfigurer.setBasePackage("com.fairyland.jdp.**.mapper,com.metlife.ezt.**.mapper");
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		return mapperScannerConfigurer;
//	}
	
}
