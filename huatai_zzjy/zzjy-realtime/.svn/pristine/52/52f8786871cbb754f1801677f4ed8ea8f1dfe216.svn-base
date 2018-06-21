package com.fairyland.jdp.framework.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;

/**
 * 用户注册的Controller.
 * 
 */
@RestController
@RequestMapping(value = "/admin/register")
public class RegisterController {

	@Autowired
	private UserService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "jdp-framework/security/account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user, RedirectAttributes redirectAttributes) {
		accountService.registerUser(user);
		redirectAttributes.addFlashAttribute("username", user.getAccount());
		return "redirect:/login";
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (accountService.findUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
}
