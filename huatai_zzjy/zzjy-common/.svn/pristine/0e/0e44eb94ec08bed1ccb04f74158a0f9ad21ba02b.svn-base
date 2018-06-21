package com.ehuatai.app.analysis.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.app.analysis.service.AppAnalysisService;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;

/**
 *@描述 App 
 */
@RestController
@RequestMapping(value="/app/analysis", name="分析管理")
public class AppAnalysisController {

	@Autowired
	private AppAnalysisService appAnalysisServiceImpl;
	
	@Autowired
	private Module module;
	
	/**
	 * 1、主题、板块菜单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/navs")
	public Object analysisNavsApp(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = appAnalysisServiceImpl.appAnalysisNavs(reqParams);
		return retResult;
	}
	/**
	 * 2、指标页面接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/target")
	public Object analysisTargetApp(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		request.setAttribute("subject", reqParams.get("subject"));
		RestResult retResult = appAnalysisServiceImpl.appAnalysisTarget(reqParams);
		return retResult;
	}
	/**
	 *2、指标页面接口 -> 2.1、一级指标趋势图接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/target/main")
	public Object analysisTargetMainApp(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = appAnalysisServiceImpl.appAnalysisTargetMain(reqParams);
		return retResult;
	}
	/**
	 * 2、指标页面接口-> 2.2、二级指标趋势图接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/target/sub")
	public Object analysisTargetSubApp(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = appAnalysisServiceImpl.appAnalysisTargetSub(reqParams);
		return retResult;
	}
	/**
	 * 2、指标页面接口-> 2.3、特殊指标趋势图接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/target/spec")
	public Object analysisTargetSpecApp(@RequestBody Map<String, Object>reqParams,HttpServletRequest request){
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = appAnalysisServiceImpl.appAnalysisTargetSpec(reqParams);
		return retResult;
	}
	
}
