/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-3 上午09:17:49 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-3 上午09:17:49       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.factorybean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.authc.filter.CaptchaAuthenticationFilter;
import com.fairyland.jdp.framework.security.authc.filter.OnlineSessionFilter;
import com.fairyland.jdp.framework.security.filterchain.service.DefaultFilterChainDefinitionService;
import com.fairyland.jdp.springboot.cas.filter.FairylandCasFilter;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-3 上午09:17:49 
 * @version V1.0.0 
 */
@Component("adminShiroFilter")
public class FairylandShiroFilterFactoryBean extends ShiroFilterFactoryBean{
//	@Value("${spring.fairyland.shiro.cas.waf.loginUrl}")
//	private String wafLoginUrl;
//	@Value("${spring.fairyland.shiro.cas.error}")
//	private  String casErrorUrl;
	
	@Value("${spring.fairyland.shiro.redirectUrl}")
	private  String redirectUrl;
	
	@Value("${spring.fairyland.shiro.authcKeyAttribute}")
	private  String authcKeyAttribute;
	
	@Value("${spring.fairyland.shiro.logoutKeyAttribute}")
	private  String logoutKeyAttribute;
	
	@Value("${spring.fairyland.shiro.sessionKeyAttribute}")
	private  String sessionKeyAttribute;
	
	@Autowired
    private CaptchaAuthenticationFilter captchaAuthenticationFilter;
	@Autowired
    private OnlineSessionFilter onlineSessionFilter;
	@Resource(name="filterChainDefinitionService")
    private  DefaultFilterChainDefinitionService filterChainDefinitionService;
	
	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
        super.setSecurityManager(securityManager);
    }
	
	@Value("${spring.fairyland.shiro.loginUrl}")
	public void setLoginUrl(String loginUrl) {
        super.setLoginUrl(loginUrl);
    }
	
	@Value("${spring.fairyland.shiro.successUrl}")
	public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl(successUrl);
    }
	
	@Value("${spring.fairyland.shiro.unauthorizedUrl}")
	public void setUnauthorizedUrl(String unauthorizedUrl) {
        super.setUnauthorizedUrl(unauthorizedUrl);
    }
	
	@PostConstruct
	void innitMethod(){
		super.setFilterChainDefinitionMap(filterChainDefinitionService.loadFilterChains());
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put(authcKeyAttribute,captchaAuthenticationFilter);
		filters.put(logoutKeyAttribute, getAdminLogoutFilter());
		filters.put(sessionKeyAttribute, onlineSessionFilter);
//		filters.put("cas", getCasFilter());
		super.setFilters(filters);
		
	}
	
//	private Filter getCasFilter() {
//		FairylandCasFilter casFilter = new FairylandCasFilter();
//		casFilter.setFailureUrl(casErrorUrl);
//		casFilter.setWafLoginUrl(wafLoginUrl);
//		casFilter.setSuccessUrl(this.getSuccessUrl());
//		return casFilter;
//	}
	
	public  Filter getAdminLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl(redirectUrl);
		return logoutFilter;
	}
}
