package com.fairyland.jdp.framework.log.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fairyland.jdp.framework.log.service.HyLogService;

public class LogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HyLogService hyLogService;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		hyLogService.createLog(request, handler.toString(), ex, null);
		System.out.println(request.getRequestURI());
	}
}
