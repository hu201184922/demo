package com.fairyland.jdp.framework.security.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.security.domain.JsonResult;
import com.fairyland.jdp.framework.security.service.UserAuthService;
import com.fairyland.jdp.framework.security.util.AuthUtil;
import com.fairyland.jdp.framework.util.Base64Util;

/**
 * 登录接口
 * @author Administrator
 *
 */
@RequestMapping("login")
@RestController
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private UserAuthService userAuthService;
	
	@RequestMapping
	public JsonResult<AuthInfo> auth(HttpServletRequest request,HttpServletResponse response,String username,String password){
		JsonResult<AuthInfo> result = userAuthService.userAuth(username, password);
//		if(result.getValue()!=null){
//			String authInfoJson = JSON.toJSONString(result.getValue());
//			String authInfoBase64 = Base64Util.encode(authInfoJson);
//			Cookie cookie = new Cookie(AuthUtil.AuthInfo,authInfoBase64);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//		}
			
		return result;
	}
}
