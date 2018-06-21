package com.fairyland.jdp.framework.security.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录接口
 * @author Administrator
 *
 */
@RequestMapping("login")
@Controller
public class LoginController {
	/**
	 * 登录提交接口
	 * @return 首页或登录页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		SecurityUtils.getSubject().getSession(true);
		System.out.println("---------------Open Login Page-----------------");
		if(SecurityUtils.getSubject().isAuthenticated()){
			return "redirect:/index";
		}
		return "/login";
	}
	
	/**
	 * 登录失败接口
	 * @return 登录页
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			HttpServletResponse response,Model model) {
		System.out.println("---------------Login Failed-----------------");
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				userName);
		return "/login";
	}
}
