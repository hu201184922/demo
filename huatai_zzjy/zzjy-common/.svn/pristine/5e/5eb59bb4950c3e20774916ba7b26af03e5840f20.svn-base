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
		String errMsg = e.getCause()==null?null:e.getCause().getMessage();
		errMsg = (errMsg == null || "".equals(errMsg))?e.getMessage():errMsg;
		errMsg = (errMsg == null || "".equals(errMsg)) ? "服务器异常":errMsg;
		e.printStackTrace();
		RestResult rest = new RestResult("E5000", errMsg, false, null);
		return rest;
	}
}
