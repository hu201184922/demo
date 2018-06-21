package com.fairyland.jdp.framework.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;

/**
 * 用户修改自己资料的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/admin/profile")
public class ProfileController {

	@Autowired
	private UserService accountService;

	@RequestMapping( value="alterAdminPassword")
	public String updateForm(Model model) {
		Long id = SessionContextUtils.getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "/jdp-framework/security/account/alter-admin-password";
	}
	@RequestMapping( value="alterAdminSetting")
	public String alterAdminSetting(Model model) {
		Long id = SessionContextUtils.getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "/jdp-framework/security/account/alter-admin-setting";
	}

	@RequestMapping(value="modifyPassword",method=RequestMethod.POST)
	@ResponseBody
	public boolean modifyPassword(@RequestParam("userId") Long userId,@RequestParam("newPassword")String newPassword,
			@RequestParam("oldPassword")String oldPassword){
		
	      return accountService.modifyPassword(userId, oldPassword, newPassword);
	        	
	        }
		
	@RequestMapping(method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user) {
		accountService.updateUser(user);
		return "redirect:/";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id.longValue() != -1) {
			model.addAttribute("user", accountService.getUser(id));
		}
	}
	

	
	@RequestMapping(value = "settings/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveSettings(@Valid @ModelAttribute("user") User user) {
		accountService.updateProfileUser(user);
		return "true";
	}

}
