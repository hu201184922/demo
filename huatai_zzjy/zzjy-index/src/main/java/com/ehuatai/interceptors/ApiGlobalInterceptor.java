package com.ehuatai.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ehuatai.biz.domain.User;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;

public class ApiGlobalInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User user = RequestHeaderUtil.getUser(request);
		if(user == null) {
			//解析用户信息失败
			sendJSON(response, RestResult.getAuthenUserError());
			return false;
		}

		//可以直接在Controller中根据Consts.REQ_USER获取用户信息
//		request.setAttribute(Consts.REQ_USER, user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		
	}
	
	private void sendJSON(HttpServletResponse response,Object result) throws IOException {
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(JSON.toJSONString(result));
	}

}
