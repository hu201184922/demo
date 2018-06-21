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
import com.fairyland.jdp.framework.security.filterchain.service.BizFilterChainDefinitionService;

@Component("bizShiroFilter")
public class BizShiroFilterFactoryBean extends ShiroFilterFactoryBean{
	@Value("${spring.fairyland.shiro.biz.redirectUrl}")
	private  String redirectUrl;
	
	@Value("${spring.fairyland.shiro.biz.authcKeyAttribute}")
	private  String authcKeyAttribute;
	
	@Value("${spring.fairyland.shiro.biz.logoutKeyAttribute}")
	private  String logoutKeyAttribute;
	
	@Value("${spring.fairyland.shiro.biz.sessionKeyAttribute}")
	private  String sessionKeyAttribute;
	
	@Autowired
    private CaptchaAuthenticationFilter captchaAuthenticationFilter;
	@Autowired
    private OnlineSessionFilter onlineSessionFilter;
	@Resource(name="bizfilterChainDefinitionService")
    private  BizFilterChainDefinitionService filterChainDefinitionService;
	
	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
        super.setSecurityManager(securityManager);
    }
	
	@Value("${spring.fairyland.shiro.biz.loginUrl}")
	public void setLoginUrl(String loginUrl) {
        super.setLoginUrl(loginUrl);
    }
	
	@Value("${spring.fairyland.shiro.biz.successUrl}")
	public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl(successUrl);
    }
	
	@Value("${spring.fairyland.shiro.biz.unauthorizedUrl}")
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
		super.setFilters(filters);
		
	}
	
	public  Filter getAdminLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl(redirectUrl);
		return logoutFilter;
	}
}
