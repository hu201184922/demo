package com.fairyland.jdp.framework.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fairyland.jdp.framework.security.Constants;
import com.fairyland.jdp.framework.security.domain.User;
@Component
public class FirstLogonChangePswInterceptor extends HandlerInterceptorAdapter {
	private boolean resetFirstPwd = true;

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user == null||modelAndView==null)
			return;

		String path = WebUtils
				.getPathWithinApplication((HttpServletRequest) request);
		/**	if (path.indexOf(Constants.CHANGE_PSW_URL) == -1) {
			if (!BooleanUtils.isTrue(user.getFirstLogined())
					|| user.getFirstLoginedTime() == null) {
//				WebUtils.issueRedirect(request, response, Constants.CHANGE_PSW_URL + "/"
//						+ user.getId());
				modelAndView.setViewName("redirect:"+Constants.CHANGE_PSW_URL + "/"
						+ user.getId());
			}
		}*/
		if(isResetFirstPwd()){
			if(path.indexOf(Constants.CHANGE_PSW_URL)==-1){
				if(!BooleanUtils.isTrue(user.getFirstLogined())
					||user.getFirstLoginedTime() == null){
//					modelAndView.setView(new RedirectView("redirect:"+Constants.CHANGE_PSW_URL +"?id=" +user.getId(),false));
					modelAndView.setViewName("redirect:"+Constants.CHANGE_PSW_URL +"?id=" +user.getId()+"&currentPath="+path);
				}
			}
		}
	}

	public boolean isResetFirstPwd() {
		return resetFirstPwd;
	}

	public void setResetFirstPwd(boolean resetFirstPwd) {
		this.resetFirstPwd = resetFirstPwd;
	}
	
	
}
