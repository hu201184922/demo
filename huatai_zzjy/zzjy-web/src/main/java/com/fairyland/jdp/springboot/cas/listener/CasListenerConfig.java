/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by zhuxinwei at * @date 2016年3月2日 上午9:48:33 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * zhuxinwei  email:zhuxinwei@fulan.com.cn    * @date 2016年3月2日 上午9:48:33       Initailized
 */
package com.fairyland.jdp.springboot.cas.listener;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 用于单点退出，该过滤器用于实现单点登出功能，可选配置
 * @author zhuxinwei
 * @email zhuxinwei@fulan.com.cn
 * @date * @date 2016年3月2日 上午9:48:33
 * @version V1.0.0
 */
@Configuration
public class CasListenerConfig {

	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listenerRegistrationBean() {
		SingleSignOutHttpSessionListener singleSignOutHttpSessionListener = new SingleSignOutHttpSessionListener();
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listenerRegistrationBean = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
		listenerRegistrationBean.setListener(singleSignOutHttpSessionListener);
		listenerRegistrationBean.setEnabled(true);
		return listenerRegistrationBean;
	}

}
