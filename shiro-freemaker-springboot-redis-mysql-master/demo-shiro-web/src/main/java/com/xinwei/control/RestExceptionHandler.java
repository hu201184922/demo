package com.xinwei.control;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public String requestNotReadable(HttpMessageNotReadableException ex){
        ex.printStackTrace();
        //json 数据读取失败
        JSONObject result = new JSONObject();
        result.put("code", 400);
        result.put("msg", "json data is error ");

        return result.toJSONString();
    }
}
