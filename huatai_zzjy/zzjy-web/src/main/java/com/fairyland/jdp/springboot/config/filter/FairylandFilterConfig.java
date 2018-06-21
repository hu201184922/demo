package com.fairyland.jdp.springboot.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-2-4 上午12:23:51 
 * @version V1.0 Some methods in this class Controll the view and Call the
 *          service
 */
@Configuration
public class FairylandFilterConfig extends SpringBootServletInitializer{
 
	@Autowired
	private CustomSiteMeshFilter customSiteMeshFilter;
 
	@Bean
	public CustomSiteMeshFilter customSiteMeshFilter(){
		CustomSiteMeshFilter customSiteMeshFilter = new CustomSiteMeshFilter();
		return customSiteMeshFilter;
	}
 @Bean
  public FilterRegistrationBean sitemeshFilter() {
	  FilterRegistrationBean bean = new FilterRegistrationBean();
	  bean.setFilter(customSiteMeshFilter);
//	  bean.setName("sitemeshFilter");
	  bean.addUrlPatterns("/*");
	  return bean;
	}
}
