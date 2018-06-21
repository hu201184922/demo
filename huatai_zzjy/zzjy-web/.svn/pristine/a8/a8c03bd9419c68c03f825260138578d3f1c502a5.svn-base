package com.fairyland.jdp.framework.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.filterchain.service.FilterChainStatusManager;

/**
 * url权限加载状态过滤器
 * @author XiongMiao
 *
 */
public class UrlLoadingStatusInterceptor extends HandlerInterceptorAdapter {
	
	private String viewName;
	
	@Autowired
	private FilterChainStatusManager filterChainStatusManager;
	
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		User user = SessionContextUtils.getCurrentUser();
		if(user == null){
			return;
		}
		if(filterChainStatusManager.getFilterChainStatus() == FilterChainStatusManager.FILTER_CHAIN_NORMAL){
			return;
		}

		if(BooleanUtils.isTrue(user.getIsAdmin())){
			return;
		}
		
		if(modelAndView != null){
			modelAndView.setViewName(viewName);
		}
	}
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
}
