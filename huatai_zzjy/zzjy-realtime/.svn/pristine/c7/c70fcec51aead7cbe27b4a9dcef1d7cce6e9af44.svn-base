package com.ehuatai.biz.analysis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehuatai.ret.RestResult;

public interface AnalysisRealService {


	/**
	 * 实时指标分析主接口
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealtime(Map<String, Object> reqParams);
	
	/**
	 * 实时指标一级指标接口
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealMain(Map<String, Object> reqParams);
	
	/**
	 * 实时指标二级指标接口
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealSub(Map<String, Object> reqParams);
	
	/**
	 * 实时指标特殊区域
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealSpec(Map<String, Object> reqParams);
	
	/**
	 * 实时指标列表接口
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealList(Map<String, Object> reqParams);
	
	/**
	 * 实时指标列表下载接口
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response);
	
	/**
	 * 实时指标设为默认接口
	 * @param reqParams
	 * @return
	 */
	public RestResult analysisRealDef(Map<String, Object> reqParams);
}
