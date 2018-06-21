package com.ehuatai.app.analysis.service;

import java.util.Map;

import com.ehuatai.ret.RestResult;

public interface AppAnalysisService {

	/**
	 * 分析管理-> 1.主题菜单
	 * @param reqParams
	 * @return
	 */
	public RestResult appAnalysisNavs(Map<String, Object> reqParams);
	/**
	 * 分析管理 -> 2.指标页面接口
	 * @param reqParams
	 * @return
	 */
	public RestResult appAnalysisTarget(Map<String, Object> reqParams);
	/**
	 * 分析管理 -> 3.指标页面接口 -> 3.1、一级指标趋势图接口
	 * @param reqParams
	 * @return二级
	 */
	public RestResult appAnalysisTargetMain(Map<String, Object> reqParams);
	/**
	 * 分析管理 -> 3.指标页面接口 -> 3.2、指标趋势图接口
	 * @param reqParams
	 * @return
	 */
	public RestResult appAnalysisTargetSub(Map<String, Object> reqParams);
	/**
	 * 分析管理 -> 3.指标页面接口 -> 3.3、特殊指标趋势图接口
	 * @param reqParams
	 * @return
	 */
	public RestResult appAnalysisTargetSpec(Map<String, Object> reqParams);
	/**
	 * 分析管理 -> 3.指标页面接口 -> 3.4、实时指标趋势图接口
	 * @param reqParams
	 * @return
	 */
	public RestResult appAnalysisTargetRealtime(Map<String, Object> reqParams);
}
