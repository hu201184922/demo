package com.fairyland.jdp.framework.security.authc.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.Constants;

@Component
public class OnlineSessionFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		if (session.getAttribute(Constants.FORCE_LOGOUT_KEY) != null) {
			request.setAttribute("error", "您的会话已被系统强制结束");
			WebUtils.saveRequest(request);
			try {
				subject.logout();
			} catch (Exception e) {
				// ignore
			}
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
