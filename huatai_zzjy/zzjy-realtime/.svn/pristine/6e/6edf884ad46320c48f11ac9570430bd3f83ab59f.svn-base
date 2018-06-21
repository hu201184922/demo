package com.ehuatai.biz.index.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.index.service.BoardRealService;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value="/app/index/dashbord", name="APP打湿报")
public class BoardAppController {
	@Autowired
	private BoardRealService boardRealService;
	
	@RequestMapping(value="/realtime")
	public Object DSBshortCut(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return boardRealService.getDSBrealTime(reqParams);
	}
}
