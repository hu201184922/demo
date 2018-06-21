package com.fairyland.jdp.framework.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.JsonResult;
import com.fairyland.jdp.framework.security.domain.User;

@RestController
@RequestMapping("user")
public class UserController {
	@RequestMapping("getUserInfo")
	public JsonResult<User> getCurrentUserInfo(){
		User user = SessionContextUtils.getCurrentUser();
		return new JsonResult<User>(user);
	}
}
