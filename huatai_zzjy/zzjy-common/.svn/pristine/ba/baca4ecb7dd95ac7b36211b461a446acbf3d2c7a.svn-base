package com.ehuatai.app.plate.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ehuatai.app.plate.service.AppPlateService;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value="/app/block",name="板块")
public class AppPlateController {
	@Autowired
	private AppPlateService appPlateService;
	
	@Autowired
	private Module module;
	
	/**
	 * 1、板块菜单接口
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value="/navs")
	public RestResult blockNavs(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return appPlateService.blockNavs(reqParams);
	}
	/**
	 * 2、板块接口->  2、板块页面接口-> 2.1、板块页面接口
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value="/page")
	public RestResult getPage(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);				
		return appPlateService.blockpages(reqParams);
		
	}
	/**
	 * 2、板块接口->  2、板块趋势图接口-> 2.2、一级指标区域接口（main）
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value="/trend/main")
	public RestResult getTrendMain(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
			
		return appPlateService.getTrendMain(reqParams);
	}
	/**
	 * 2、板块接口->  2、板块趋势图接口-> 2.3、二级指标区域接口（sub）
	 * @param reqParams
	 * @return
	 */
	@RequestMapping(value="/trend/sub")
	public RestResult getTrendSub(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);		
		
		return appPlateService.getTrendSub(reqParams);
	}
}
