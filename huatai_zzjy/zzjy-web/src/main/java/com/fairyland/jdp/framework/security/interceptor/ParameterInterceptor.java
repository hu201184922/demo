package com.fairyland.jdp.framework.security.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ParameterInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null){
			Map<String, Object> map = modelAndView.getModel();
			//modelAndView.addAllObjects((Map<String, ?>) CommonUtil.transFormBack(map));
		}
	}

}
