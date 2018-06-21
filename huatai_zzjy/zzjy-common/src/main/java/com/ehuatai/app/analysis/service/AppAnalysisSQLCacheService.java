package com.ehuatai.app.analysis.service;

import java.util.List;
import java.util.Map;

public interface AppAnalysisSQLCacheService {

	/**
	 * 三 分析管理-> 1.主题菜单
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> appAnalysisNavsSQL(Map<String, Object> reqParams,String sql);
	/**
	 * 三  分析管理 -> 2.指标页面接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> appAnalysisTargetSQL(Map<String, Object> reqParams,String sql);
	/**
	 * 三  分析管理 -> 2.指标页面接口 -> 2.1、一级指标趋势图接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> appAnalysisTargetMainSQL(Map<String, Object> reqParams,String sql);
	/**
	 * 三  分析管理 -> 2.指标页面接口 -> 2.2、二级指标趋势图接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> appAnalysisTargetSubSQL(Map<String, Object> reqParams,String sql);
	/**
	 * 三  分析管理 -> 2.指标页面接口 -> 2.3、特殊指标趋势图接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> appAnalysisTargetSpecSQL(Map<String, Object> reqParams,String sql);
}
