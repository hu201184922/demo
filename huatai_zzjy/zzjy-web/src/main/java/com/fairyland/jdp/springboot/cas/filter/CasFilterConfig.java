/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by zhuxinwei at * @date 2016年3月2日 上午9:47:49 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * zhuxinwei  email:zhuxinwei@fulan.com.cn    * @date 2016年3月2日 上午9:47:49       Initailized
 */
package com.fairyland.jdp.springboot.cas.filter;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 该过滤器用于实现单点登出功能，可选配置
 * @author zhuxinwei
 * @email zhuxinwei@fulan.com.cn
 * @date * @date 2016年3月2日 上午9:47:49 
 * @version V1.0.0 
 */

@Configuration 
public class CasFilterConfig {
	
	 @Bean
	 public FilterRegistrationBean singleSignOutFilter() {
		  FilterRegistrationBean bean = new FilterRegistrationBean();
		  bean.setFilter(new SingleSignOutFilter());
		  bean.setName("CAS Single Sign Out Filter");
		  bean.addUrlPatterns("/admin/shiro-cas");
		  return bean;
		}
	

}
