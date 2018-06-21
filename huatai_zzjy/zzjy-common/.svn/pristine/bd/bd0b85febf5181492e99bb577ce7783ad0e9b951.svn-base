package com.ehuatai.biz.analysis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehuatai.ret.RestResult;

public interface AnalysisService {

	/**
	 * 三 分析管理-> 1. 主题、板块菜单快捷键
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisNavs(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.1 基本接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisBase(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.2 常用指标接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisCommon(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.3 一级指标区域接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisMain(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.4 二级指标区域接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisSub(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.5 dist区域接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisDist(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.6 org区域接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisOrg(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.7 torg(保费部)区域接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisTorg(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.8 spec区域接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisSpec(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.9 指标设为默认接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisDef(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.10 区间统计
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRegion(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.11 机构对比-> 3.11.1 获取机构对比的参数
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisOrgGet(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.11 机构对比-> 3.11.2 机构对比
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisOrgCompare(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.12 导出清单-> 3.12.1 导出清单列表
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisReportList(Map<String, Object> reqParams);

	/**
	 * 三 分析管理 -> 3.主题接口 -> 3.12 导出清单-> 3.12.2 导出清单下载
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	public RestResult analysisReportDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response);

	RestResult analysisOrgSearch(Map<String, Object> reqParams);

}
