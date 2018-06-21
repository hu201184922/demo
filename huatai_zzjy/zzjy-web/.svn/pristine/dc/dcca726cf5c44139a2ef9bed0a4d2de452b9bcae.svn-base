package com.fairyland.jdp.framework.exception.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.authz.UnauthorizedException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.exception.ExceptionResponse;

@ControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger log = LoggerFactory
			.getLogger(DefaultExceptionHandler.class);
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	/**
	 * 没有权限 异常 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView handleUnauthenticatedException(
			NativeWebRequest request, UnauthorizedException e) {
		log.error("用户权限验证失败", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.from(e);

		createExLog(request, e);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", exceptionResponse);
		
		mv.setViewName("jdp-framework/error/error");

		return mv;
	}
	
	/**
	 * 没有权限 异常 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({Exception.class })
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ModelAndView handleException(
			NativeWebRequest request, Exception e) {
		log.error("服务器异常", e);
		ExceptionResponse exceptionResponse = ExceptionResponse.from(e);
		
		//createExLog(request, e);
		
		ModelAndView mv = new ModelAndView();
		String htmlStr = e.getMessage();
		Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
		Matcher m_script = p_script.matcher(htmlStr);  
	      htmlStr = m_script.replaceAll("");
	      mv.addObject("error", htmlStr);

		mv.setViewName("jdp-framework/error/error");
		//exceptionResponse.getMessage();
		return mv;
	}
	
	private void createExLog(NativeWebRequest request, Exception e){
		ExceptionResponse exceptionResponse1 = ExceptionResponse.from1(e);

		String s = exceptionResponse1.getMessage()+"   "+exceptionResponse1.getException()+"    "+exceptionResponse1.getStackTrace();
		s = s.substring(0, 499);
//		boolean isDbConnectionException=isConnectionException(e);
//		if(!isDbConnectionException){
//			exLogService.createLog(request,s,e.toString());	
//		}
	}
	
	/**
	 * 是否为数据库连接异常
	 * @param e
	 * @return
	 */
	private boolean isConnectionException(Throwable e){
		if(e instanceof JDBCConnectionException){
			return true;
		}
		if(e.getCause() !=null){
			return isConnectionException(e.getCause());
		}
		return false;
	}
	
}
