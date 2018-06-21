package com.ehuatai.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehuatai.ret.RestResult;

//如果返回的为json数据或其它对象，添加该注解  

@ControllerAdvice  
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody 
	public RestResult exceptionHandler(RuntimeException e, HttpServletResponse response) {
		RestResult rest = new RestResult("E5000", e.getMessage(), false, null);
		return rest;
	}
}
