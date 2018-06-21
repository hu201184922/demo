package com.fairyland.jdp.framework.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;

/**
 * 用户修改自己资料的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/admin/settings")
public class SettingsController {

	@Autowired
	private UserService accountService;

		
	@RequestMapping(method = RequestMethod.GET)
	public String update(Model model) {
		Long id = SessionContextUtils.getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "jdp-framework/security/account/account-settings";
	}

/*
	@RequestMapping(value = "loadForm", method = RequestMethod.POST)
	public String loadDefinitionForm(Model model) {
		Long id = SessionContextUtils.getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "jdp-framework/security/account/account-settings";
	}*/
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveSettings(@Valid @ModelAttribute("user") User user) {
		accountService.updateUser(user);
		return "redirect:/";
	}

}
