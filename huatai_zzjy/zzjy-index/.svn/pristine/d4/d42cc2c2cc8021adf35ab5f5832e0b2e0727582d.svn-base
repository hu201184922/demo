package com.ehuatai.biz.index.dashbord.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.index.dashbord.service.DSBService;
import com.ehuatai.conts.Module;
import com.ehuatai.util.RequestHeaderUtil;


@RestController
@RequestMapping(value="/api/index/dashbord")
public class DashbordController {

	@Autowired
	private DSBService dashbordService;
	
	@Autowired
	private Module module;
	
	/**
	 * 首页->1、Dashbord -> 1.2、快捷菜单（保险流程菜单）
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/shortcut")
	public Object DSBshortCut(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return dashbordService.DSBshortCut(reqParams);
	}
	/**
	 * 首页->1、Dashbord -> 1.2、快捷菜单（保险流程菜单）->1.2.1、保险流程详情接口
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/analysis")
	public Object DSBanalysis(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		//code=DAS01
		return dashbordService.DSBanalysis(reqParams);
	}
	/**
	 * 首页->1、Dashbord -> 1.2、快捷菜单（保险流程菜单） ->1.2.2、保费指标接口
	 * @param reqParams
	 * @param request
	 * @return
	 * 
	 * 新单保费的图形数据
	 */
	@RequestMapping(value="/analysis/premium")
	public Object DSBanalysisPrem(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return dashbordService.DSBanalysisPrem(reqParams);
	}
	/**
	 * 首页->1、Dashbord -> 1.2、快捷菜单（保险流程菜单）->1.2.3、其他指标
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/analysis/target")
	public Object DSBanalysisOrther(@RequestBody Map<String, Object>reqParams,HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return dashbordService.DSBanalysisOrther(reqParams);
	}
}
