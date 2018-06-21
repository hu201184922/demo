package com.fairyland.jdp.framework.security.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 登录拦截器
 * @author Administrator
 *
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Value("${spring.fairyland.waf.loginAddPathPatterns}")
	private String addPathPatterns;
	
	public String getAddPathPatterns() {
		return addPathPatterns;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object args, ModelAndView modelAndView)
			throws Exception {
		String requestURL = request.getRequestURI();
		String ctx = request.getContextPath();

		SavedRequest  savedRequest= WebUtils.getSavedRequest(request);
		
		if (savedRequest != null
				&& savedRequest.getMethod().equalsIgnoreCase(
						AccessControlFilter.GET_METHOD)) {
			String requestBase = null, savedBase = null;

			String savedUrl = savedRequest.getRequestUrl();
			
//			log.debug("=========savedUrl:"+savedUrl);

			if (StringUtils.isNotEmpty(savedUrl)) {
				Pattern pattern = Pattern.compile("(?<=" + ctx
						+ "/)[.\\s\\S]*?(?=/|$)");

				Matcher reqMatcher = pattern.matcher(requestURL);
				Matcher savedMatcher = pattern.matcher(savedUrl);

				if (reqMatcher.find()) {
					requestBase = reqMatcher.group();
//					log.debug(requestBase);
				}
				if (savedMatcher.find()) {
					savedBase = savedMatcher.group();
//					log.debug(savedBase);
				}

				if (!StringUtils.equals(requestBase, savedBase)) {
					WebUtils.getAndClearSavedRequest(request);
				}
			}
		}
	}
}
