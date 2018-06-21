package com.ehuatai.biz.analysis.service;

import java.util.List;
import java.util.Map;


public interface AnalysisSQLCacheService {
	
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.2 常用指标接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisCommon(Map<String, Object> reqParams,String sql);
	
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.3 一级指标区域接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisMain(Map<String, Object> reqParams,String sql);
	
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.4 二级指标区域接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisSub(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.5 dist区域接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisDist(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.6 org区域接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisOrg(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.7 torg(保费部)区域接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisTorg(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.8 spec区域接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisSpec(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.9 指标设为默认接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisDef(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.10 区间统计
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisRegion(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.11 机构对比-> 3.11.1 获取机构对比的参数
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisOrgGet(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.11 机构对比-> 3.11.2 机构对比
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisOrgCompare(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.12 导出清单-> 3.12.1 导出清单列表
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisReportList(Map<String, Object> reqParams,String sql);
	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.12 导出清单-> 3.12.2 导出清单下载
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> analysisReportDownload(Map<String, Object> reqParams,String sql);
}
