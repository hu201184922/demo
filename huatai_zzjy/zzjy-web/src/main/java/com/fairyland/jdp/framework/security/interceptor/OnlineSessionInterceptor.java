package com.fairyland.jdp.framework.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fairyland.jdp.framework.security.Constants;
@Component
public class OnlineSessionInterceptor extends HandlerInterceptorAdapter {

	@Value("${spring.fairyland.shiro.redirectUrl}")
	private String loginUrl;

	@Value("${spring.fairyland.waf.onlineSessionAddPathPatterns}")
	private String addPathPatterns;

	public String getAddPathPatterns() {
		return addPathPatterns;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		Subject subject = SecurityUtils.getSubject();
//		Session session = subject.getSession();
//		if (session.getAttribute(Constants.FORCE_LOGOUT_KEY) != null) {
//			try {
//				subject.logout();
//				request.setAttribute("errorMessage", "您的会话已被系统强制结束");    
//				WebUtils.saveRequest(request);
//				WebUtils.issueRedirect(request, response, loginUrl);
//                return false;    
//			} catch (Exception e) {
//				// ignore
//			}
//		}
//		return true;
//	}
	
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
//		Subject subject = SecurityUtils.getSubject();
//		Session session = subject.getSession();
//		if (session.getAttribute(Constants.FORCE_LOGOUT_KEY) != null) {
//			try {
//				subject.logout();
//				request.setAttribute("errorMessage", "您的会话已被系统强制结束");    
//				WebUtils.saveRequest(request);
//				modelAndView.setViewName("forward:"+loginUrl);
//				WebUtils.issueRedirect(request, response, loginUrl);
//                request.getRequestDispatcher("/").forward(request, response);    
//			} catch (Exception e) {
//				// ignore
//			}
//		}
		
	}
}
