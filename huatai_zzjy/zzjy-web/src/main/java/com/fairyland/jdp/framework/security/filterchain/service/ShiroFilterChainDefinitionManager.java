package com.fairyland.jdp.framework.security.filterchain.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Aspect
@Component
public class ShiroFilterChainDefinitionManager {

	private final static Logger log = LoggerFactory
			.getLogger(ShiroFilterChainDefinitionManager.class);
	
	@Resource
	private FilterChainStatusManager filterChainStatusManager;
	@Resource(name="adminShiroFilter")
	private AbstractShiroFilter shiroFilter;

	@Resource
	private FilterChainDefinitionService filterChainDefinitionService;

	@AfterReturning("@annotation(com.fairyland.jdp.framework.security.annotation.InitFilterChain)")
	public void updateFilterChains() {
		try {
			if(shiroFilter == null){
				throw new RuntimeException("shiroFilter must be set");
			}
			// 同步更新
//			synchronized (shiroFilter) {
			
				filterChainStatusManager.updatingFilterChain();
				
				FilterChainResolver filterChainResolver0 = shiroFilter.getFilterChainResolver();
				// 获取过滤管理器
				PathMatchingFilterChainResolver filterChainResolver = 
						(PathMatchingFilterChainResolver) filterChainResolver0;
				DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager) filterChainResolver
						.getFilterChainManager();

				// 获取URL Filter
				Section chains = filterChainDefinitionService.loadFilterChains();

				// 删除以前老的filter chain并注册默认的
				filterChainManager.getFilterChains().clear();
				
//				try {
//					int i = 0;
//					if(i==0){
//						throw new RuntimeException("test exception");
//					}
//					
//				} catch (Exception e) {
//					log.error("Get Filter Chains Failed", e);
//					throw new RuntimeException(e);
//				}

				// 循环URL Filter 注册filter chain
				if (!CollectionUtils.isEmpty(chains)) {
					for (Map.Entry<String, String> entry : chains.entrySet()) {
						String url = entry.getKey();
						String chainDefinition = entry.getValue();
						filterChainManager.createChain(url, chainDefinition);
					}
				}

				filterChainStatusManager.updatedFilterChain();
//			}
		} catch (Exception e) {
			filterChainStatusManager.errorFilterChain();
			log.error("Update Filter Chains Failed",e);
			
			throw new RuntimeException("Update Filter Chains Failed", e);
		}

	}

	public FilterChainStatusManager getFilterChainStatusManager() {
		return filterChainStatusManager;
	}

	public void setFilterChainStatusManager(FilterChainStatusManager filterChainStatusManager) {
		this.filterChainStatusManager = filterChainStatusManager;
	}  

	public FilterChainDefinitionService getFilterChainDefinitionService() {
		return filterChainDefinitionService;
	}

	public void setFilterChainDefinitionService(
			FilterChainDefinitionService filterChainDefinitionService) {
		this.filterChainDefinitionService = filterChainDefinitionService;
	}

//	public AbstractShiroFilter getShiroFilter() {
//		return shiroFilter;
//	}
//
//	@Required
//	public void setShiroFilter(AbstractShiroFilter shiroFilter) {
//		this.shiroFilter = shiroFilter;
//	}

}
