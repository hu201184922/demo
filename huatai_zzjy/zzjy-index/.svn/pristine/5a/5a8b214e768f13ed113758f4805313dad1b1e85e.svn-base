package com.fairyland.jdp.framework.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.JsonResult;
import com.fairyland.jdp.framework.security.domain.User;

@RestController
@RequestMapping("auth")
public class AuthController {
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
//	@Autowired
//	private CacheService cacheService;
	
	@RequestMapping("getUserInfo")
	public JsonResult<User> getCurrentUserInfo(){
		User user = SessionContextUtils.getCurrentUser();
		return new JsonResult<User>(user);
	}
}
