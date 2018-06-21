package com.fairyland.jdp.framework.security.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.security.domain.JsonResult;
import com.fairyland.jdp.framework.security.service.TokenService;
import com.fairyland.jdp.framework.security.util.AuthUtil;

@RequestMapping("logout")
@RestController
public class LogoutController {
	@Autowired
	private TokenService tokenService;
	@RequestMapping
	public JsonResult<AuthInfo> auth(HttpServletRequest request,HttpServletResponse response){
		String accessToken = SessionContextUtils.getAccessToken();
		tokenService.deleteAuthInfo(accessToken);
		Cookie cookie = new Cookie(AuthUtil.AuthInfo,null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return new JsonResult();
	}
}
