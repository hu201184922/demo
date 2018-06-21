package com.fairyland.jdp.springboot.config.shiro.cas.factory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fairyland.jdp.framework.security.authc.filter.CaptchaAuthenticationFilter;
import com.fairyland.jdp.framework.security.authc.filter.OnlineSessionFilter;
import com.fairyland.jdp.framework.security.filterchain.service.DefaultFilterChainDefinitionService;
import com.fairyland.jdp.springboot.cas.filter.FairylandCasFilter;

//@Component("adminShiroFilter")
public class FairylandCasShiroFilterFactoryBean extends ShiroFilterFactoryBean{
//	@Value("${spring.fairyland.shiro.cas.waf.loginUrl}")
//	private String wafLoginUrl;
	@Value("${spring.fairyland.shiro.cas.error}")
	private  String casErrorUrl;
	
	@Value("${cas.server.address}/logout?sysCode=${spring.fairyland.project.sysCode}&service=${server.host}${cas.logout.path}")
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
	@Autowired
    private  DefaultFilterChainDefinitionService filterChainDefinitionService;
//	@Resource(name="casLogoutFilter")
//	private LogoutFilter logoutFilter;
//	@Resource(name="casFilter")
//	private Filter casFilter;
	
	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
        super.setSecurityManager(securityManager);
    }
	
//	@Value("${spring.fairyland.shiro.loginUrl}")
//	@Value("${cas.login.url}")
	@Value("${cas.server.address}/login?sysCode=${spring.fairyland.project.sysCode}&service=${server.host}${cas.shirocas.path}&redirectUrl=${server.host}${cas.logout.path}")
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
		filters.put("cas", getCasFilter());
		super.setFilters(filters);
		
	}
	
	public  Filter getAdminLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl(redirectUrl);
		return logoutFilter;
	}
	
	private Filter getCasFilter() {
		FairylandCasFilter casFilter = new FairylandCasFilter();
		casFilter.setFailureUrl(casErrorUrl);
//		casFilter.setWafLoginUrl(wafLoginUrl);
		casFilter.setSuccessUrl(this.getSuccessUrl());
		return casFilter;
	}
}
