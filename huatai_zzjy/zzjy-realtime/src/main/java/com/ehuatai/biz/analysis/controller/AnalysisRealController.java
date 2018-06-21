package com.ehuatai.biz.analysis.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.analysis.service.AnalysisRealService;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/api/analysis/realtime", name = "分析管理-实时指标")
public class AnalysisRealController {

	@Autowired
	private AnalysisRealService analysisRealServiceImpl;
	
	/**
	 * 实时指标分析主接口（一二级默认指标和特殊指标）
	 */
	@RequestMapping(value = "")
	public Object analysisRealtime(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		reqParams.put("key", "api");
		RestResult retResult = analysisRealServiceImpl.analysisRealtime(reqParams);
		return retResult;
	}
	
	/**
	 * 实时指标一级指标接口
	 */
	@RequestMapping(value = "/main")
	public Object analysisRealMain(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		reqParams.put("module", "TEMP03_REG02");
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisRealServiceImpl.analysisRealMain(reqParams);
		return retResult;
	}
	
	/**
	 * 实时指标二级指标接口
	 */
	@RequestMapping(value = "/sub")
	public Object analysisRealSub(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		reqParams.put("module", "TEMP03_REG03");
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisRealServiceImpl.analysisRealSub(reqParams);
		return retResult;
	}
	
	/**
	 * 实时指标特殊区域
	 */
	@RequestMapping(value = "/spec")
	public Object analysisRealSpec(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		reqParams.put("module", "TEMP03_REG06");
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		reqParams.put("key", "api");
		RestResult retResult = analysisRealServiceImpl.analysisRealSpec(reqParams);
		return retResult;
	}
	
	/**
	 * 实时指标列表接口
	 */
	@RequestMapping(value = "/list")
	public Object analysisRealList(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisRealServiceImpl.analysisRealList(reqParams);
		return retResult;
	}
	
	/**
	 * 实时指标列表下载接口
	 */
	@RequestMapping(value = "/list/download")
	public Object analysisRealDownload(@RequestBody Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisRealServiceImpl.analysisRealDownload(reqParams, request, response);
		return retResult;
	}
	
	/**
	 * 实时指标设为默认接口
	 */
	@RequestMapping(value = "/def")
	public Object analysisRealDef(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisRealServiceImpl.analysisRealDef(reqParams);
		return retResult;
	}
	
}
