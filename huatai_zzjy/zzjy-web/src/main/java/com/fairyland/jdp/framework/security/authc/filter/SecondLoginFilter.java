package com.fairyland.jdp.framework.security.authc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.framework.security.domain.User;

public class SecondLoginFilter extends AccessControlFilter {
	private Logger log = LoggerFactory.getLogger(getClass());
	static public final String CHANGE_PSW_URL = "/admin/sec/user/changepsw";

//	@Override
//	protected void doFilterInternal(ServletRequest request,
//			ServletResponse response, FilterChain chain)
//			throws ServletException, IOException {
//		Subject subject = SecurityUtils.getSubject();
//		User user = (User) subject.getPrincipal();
//		if (user == null)
//			return;
//
//		String path = WebUtils
//				.getPathWithinApplication((HttpServletRequest) request);
//		if (path.indexOf(CHANGE_PSW_URL) == -1) {
//			if (!BooleanUtils.isTrue(user.getFirstLogined())
//					|| user.getFirstLoginedTime() == null) {
//				WebUtils.saveRequest(request);
//				WebUtils.issueRedirect(request, response, CHANGE_PSW_URL + "/"
//						+ user.getId());
//			}
//		}
//		chain.doFilter(request, response);
//	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		log.debug("=======Second"+mappedValue);
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
