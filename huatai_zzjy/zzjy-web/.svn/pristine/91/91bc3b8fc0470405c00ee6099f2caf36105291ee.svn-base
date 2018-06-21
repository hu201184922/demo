/**
 * Copyright : http://www.chinaums.com, 2016-2017
 * Project : WAF
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-2-4 下午12:40:27 
 * JDK version used: JDK1.6
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao      * @date 2016-2-4 下午12:40:27       Initailized
 */
package com.fairyland.jdp.springboot.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fairyland.jdp.framework.log.interceptor.LogInterceptor;
import com.fairyland.jdp.framework.menu.interceptor.MenuInterceptor;
import com.fairyland.jdp.framework.security.interceptor.FirstLogonChangePswInterceptor;
import com.fairyland.jdp.framework.security.interceptor.LoginInterceptor;
import com.fairyland.jdp.framework.security.interceptor.OnlineSessionInterceptor;
import com.fairyland.jdp.framework.security.interceptor.UrlLoadingStatusInterceptor;

/**
 * @Description: 此类是配置spring mvc拦截器使用
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-2-4 下午12:40:27 
 * @version V1.0 Some methods in this class Controll the view and Call the
 *          service
 */
@Configuration
public class FairylandInterceptorConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private MenuInterceptor menuInterceptor;
	@Autowired
	private FirstLogonChangePswInterceptor firstLogonChangePswInterceptor;
	@Autowired
	private OnlineSessionInterceptor onlineSessionInterceptor;
//	@Autowired
//	private LogInterceptor logInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
//	@Autowired
//	private LogInterceptor logInterceptor;
	
//	@Autowired
//	private UrlLoadingStatusInterceptor urlLoadingStatusInterceptor;
	
	
	/**
	  *  多个拦截器组成一个拦截器链
	  *  addPathPatterns 用于添加拦截规则
	  *  excludePathPatterns 用户排除拦截
	* <p>Title: addInterceptors</p> 
	* <p>Description: </p> 
	* @param registry 
	* @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	/**
    	 * 2.菜单拦截器
    	 */
    	InterceptorRegistration menuinterceptorRegistration = registry.addInterceptor(menuInterceptor);
    	menuinterceptorRegistration.excludePathPatterns(menuInterceptor.getExcludePathPatterns().split(","));
    	
    	/**
    	 * 3. 首次登录需要修改密码拦截器
    	 */
    	registry.addInterceptor(firstLogonChangePswInterceptor);

    	/**
    	 * 4.会话强制退出拦截器
    	 */
    	InterceptorRegistration onlineSessionRregistry =  registry.addInterceptor(onlineSessionInterceptor);
    	onlineSessionRregistry.addPathPatterns(onlineSessionInterceptor.getAddPathPatterns().split(","));
    	
    	
    	/**
    	 * 6.登录拦截器
    	 */
    	InterceptorRegistration loginInterceptorRregistry =  registry.addInterceptor(loginInterceptor);
    	loginInterceptorRregistry.addPathPatterns(loginInterceptor.getAddPathPatterns().split(","));
    	
    	/**
    	 * 7.权限加载拦截器
    	 */
//    	registry.addInterceptor(urlLoadingStatusInterceptor);
    	
        super.addInterceptors(registry);
    }
}
