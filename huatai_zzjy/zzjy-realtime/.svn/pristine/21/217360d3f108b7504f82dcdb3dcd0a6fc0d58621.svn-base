package com.fairyland.jdp.springboot.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fairyland.jdp.framework.util.PropsUtil;

@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {
	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("tk.mybatis.springboot.mapper");
//        return mapperScannerConfigurer;
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setAnnotationClass(com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository.class);
		mapperScannerConfigurer.setBasePackage(PropsUtil.get("spring.mybatis.base-package"));
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
    }
}
