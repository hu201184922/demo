/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-3 上午01:14:43 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-3 上午01:14:43       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.filterbean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-3 上午01:14:43 
 * @version V1.0.0 
 */
@Component
public class FairylandShiroFilterRegistrationBean extends FilterRegistrationBean{
	@Value("${spring.fairyland.shiro.urlPatterns}")
	private String urlPatterns;
	
	@Value("${spring.fairyland.shiro.targetFilterLifecycle}")
	private String initParameter;
	
	@Value("${spring.fairyland.shiro.registrationEnabled}")
	public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
	
//	@Resource(name="adminShiroFilter")
//	public void setFilter(Filter filter) {
//        super.setFilter(filter);
//    }

	@PostConstruct
	public void innitMethod(){
		 super.addInitParameter("targetFilterLifecycle", initParameter);
		 super.addUrlPatterns(urlPatterns.split(System.lineSeparator()));
		 super.setFilter(new DelegatingFilterProxy("adminShiroFilter"));
	}
}
