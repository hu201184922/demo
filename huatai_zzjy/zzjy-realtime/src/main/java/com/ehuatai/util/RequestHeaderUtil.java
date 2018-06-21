package com.ehuatai.util;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ehuatai.biz.domain.Header;
import com.ehuatai.biz.domain.User;
import com.fairyland.jdp.framework.security.util.AuthUtil;
import com.fairyland.jdp.framework.util.Base64Util;

public class RequestHeaderUtil {

	/**
	 * 获取请求的Header
	 * @param request
	 * @return
	 */
	public static Header getHeaders(HttpServletRequest request) {
		Header header = new Header();
		String authen = request.getHeader(AuthUtil.AuthInfo);
		header.setAuthen(authen);
		return header;
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {
		User user = null;
		String authen = request.getHeader(AuthUtil.AuthInfo);
		if(StringUtils.isEmpty(authen)) return null;
		String userStr = Base64Util.decode(authen);
		if(StringUtils.isEmpty(userStr)) return null;
		user = JSON.parseObject(userStr, User.class);
		return user;
	}
	
	/**
	 * 设置用户信息
	 * @param reqParams
	 * @param request
	 */
	public static void setUserRequestParamter(Map<String, Object>reqParams,HttpServletRequest request) {
		User user = getUser(request);
		String username = "",role = "", roleDept = "",roleOrg = "";
		if(user != null) {
			username = user.getUsername();
			role = user.getRole();
			roleDept = user.getRoleDept();
			roleOrg = user.getRoleOrg();
		}
		reqParams.put("username", username);
		reqParams.put("role", role);
		reqParams.put("roleDept", roleDept);
		reqParams.put("roleOrg", roleOrg);
	}
}
