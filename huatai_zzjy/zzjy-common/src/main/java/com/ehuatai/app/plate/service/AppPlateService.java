package com.ehuatai.app.plate.service;

import java.util.Map;

import com.ehuatai.ret.RestResult;

public interface AppPlateService {

	/**
	 * 1、板块菜单接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult blockNavs(Map<String, Object> reqParams);

	/**
	 * 2 、板块页面接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult blockpages(Map<String, Object> reqParams);

	/**
	 * 3 、一级指标趋势图
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendMain(Map<String, Object> reqParams);

	/**
	 * 4 、二级指标趋势图
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendSub(Map<String, Object> reqParams);
}
