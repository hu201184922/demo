package com.fairyland.jdp.framework.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 */
@Controller
@RequestMapping(value = "/admin/login")
public class AdminLoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletResponse response) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/admin/desktop";
		}
		response.setStatus(401);
		return "jdp-framework/security/account/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			HttpServletResponse response, HttpServletRequest request, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		// return "jdp-framework/security/account/login";
		return "redirect:/admin/login";
	}
}
