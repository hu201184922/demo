package com.fairyland.jdp.framework.security.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.util.Base64Util;

public class AuthUtil {
	private static Logger logger = LoggerFactory.getLogger(AuthUtil.class);
	public static final String AccessTokenKey = "accesstoken";
	public static final String AuthInfo = "authinfo";
	public static String getCookieAccessToken(HttpServletRequest request){
//		String accessToken = getCookieValue(request,AccessTokenKey);
		String authInfoBase64 = getCookieValue(request,AuthInfo);
		if(authInfoBase64!=null){
			String authInfoJson = Base64Util.decode(authInfoBase64);
			AuthInfo authInfo = JSON.parseObject(authInfoJson, AuthInfo.class);
			return authInfo.getAccessToken();
		}
		return null;
		
	}
	public static String getHeaderAccessToken(HttpServletRequest request){
//		String accessToken = request.getHeader(AccessTokenKey);
		String authInfoBase64 = request.getHeader(AuthInfo);
		logger.info("authInfoBase64="+authInfoBase64);
		if(authInfoBase64!=null && !"".equals(authInfoBase64)){
			String authInfoJson = Base64Util.decode(authInfoBase64);
			logger.info("authInfoJson="+authInfoJson);
			AuthInfo authInfo = JSON.parseObject(authInfoJson, AuthInfo.class);
			return authInfo.getAccessToken();
		}
		return null;
	}
	public static String getAccessToken(HttpServletRequest request){
		String accessToken = getHeaderAccessToken(request);
		if(accessToken==null){
			return getCookieAccessToken(request);
		}else{
			return accessToken;
		}
	}
	public static String getCookieValue(HttpServletRequest request,String key){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(key.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
