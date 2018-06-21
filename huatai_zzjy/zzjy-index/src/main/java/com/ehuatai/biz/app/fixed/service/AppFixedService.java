package com.ehuatai.biz.app.fixed.service;

import java.util.Map;

import com.ehuatai.ret.RestResult;

public interface AppFixedService {

	/**
	 * 首页->2、固定指标
	 * @param reqParams
	 * @return
	 */
	public RestResult fixed(Map<String, Object>reqParams);	

	
	
	/**
	 * 首页->2、固定指标->2.1 主要指标接口
	 * @param reqParams
	 * @return
	 */
	public RestResult fixedMain(Map<String, Object>reqParams);
	
	/**
	 * 首页->2、固定指标->2.1 主要指标接口 -> 2.1.1 主要指标下钻接口
	 * @param reqParams
	 * @return
	 */
	public RestResult fixedMainDrilldown(Map<String, Object>reqParams);
	
	
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口
	 * @param reqParams
	 * @return
	 */
	public RestResult fixedMcount(Map<String, Object>reqParams);
	
	
	
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口 ->2.2.1、经营信息接口
	 * @param reqParams
	 * @return
	 */
	public RestResult fixedMcountInfo(Map<String, Object>reqParams);
	
	
	
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口 ->2.2.2、月度排行榜接口
	 * @param reqParams
	 * @return
	 */
	public RestResult fixedMcountRanking(Map<String, Object>reqParams);
	
	
	
	/**
	 * 首页->2、固定指标-> 2.3、月度指标列表(核心指标统计)接口
	 * @param reqParams
	 * @return
	 */
	public RestResult fixedCore(Map<String, Object>reqParams);
}
