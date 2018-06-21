package com.ehuatai.biz.analysis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuatai.biz.analysis.service.AnalysisService;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestResult;
import com.ehuatai.util.RequestHeaderUtil;

@RestController
@RequestMapping(value = "/api/analysis", name = "分析管理")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisServiveImpl;

	@Autowired
	private Module module;

	/**
	 * 主题、板块菜单快捷键
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/navs")
	public Object analysisNavsApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {

		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		RestResult retResult = analysisServiveImpl.analysisNavs(reqParams);

		return retResult;
	}

	/**
	 * 基本接口
	 * 
	 * @param reqParams
	 *            { keywords:'', subject:'PFDC' }
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/base")
	public Object baseApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {

		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		RestResult retResult = analysisServiveImpl.analysisBase(reqParams);

		return retResult;
	}

	/**
	 * 常用指标接口（common）
	 * 
	 * 分析管理下----蜜蜂图案数据统计处
	 * 
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/common")
	public Object commonApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {

		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		request.setAttribute("subject", reqParams.get("subject"));
		// 区域参数
		reqParams.put("module", module.analysisCommon);

		RestResult retResult = analysisServiveImpl.analysisCommon(reqParams);

		return retResult;
	}

	/**
	 * 一级指标区域接口（main）
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * 第一个线性统计图
	 */
	@RequestMapping(value = "/main")
	public Object mainApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		// 添加区域参数
		reqParams.put("module", module.analysisMain);
		// 转换为JSON字符串给thrift调用
		RestResult retResult = analysisServiveImpl.analysisMain(reqParams);
		return retResult;
	}

	/**
	 * 二级指标区域接口（sub）
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * 第二个线性统计图
	 */
	@RequestMapping(value = "/sub")
	public Object subApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		// 添加区域参数
		reqParams.put("module", module.analysisSub);

		// 转换为JSON字符串给thrift调用

		RestResult retResult = analysisServiveImpl.analysisSub(reqParams);
		return retResult;
	}

	/**
	 * 分公司/机构区域接口（dist）
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * 省份条形统计图---下图
	 */
	@RequestMapping(value = "/dist")
	public Object distApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		// 添加区域参数
		reqParams.put("module", module.analysisDist);
		RestResult retResult = analysisServiveImpl.analysisDist(reqParams);
		return retResult;
	}

	/**
	 * 特殊指标区域接口（spec）
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * 最下面的饼图
	 */
	@RequestMapping(value = "/spec")
	public Object specApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		// 添加区域参数
		reqParams.put("module", module.analysisSpec);
		RestResult retResult = analysisServiveImpl.analysisSpec(reqParams);
		return retResult;
	}

	/**
	 * 机构列表区域接口（org）
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * 省份条形统计图
	 */
	@RequestMapping(value = "/org")
	public Object orgApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		// 添加区域参数
		reqParams.put("module", module.analysisOrg);
		RestResult retResult = analysisServiveImpl.analysisOrg(reqParams);
		return retResult;
	}

	/**
	 * 保费部机构区域（torg）
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/torg")
	public Object torgApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		// 添加区域参数
		reqParams.put("module", module.analysisTorg);
		RestResult retResult = analysisServiveImpl.analysisTorg(reqParams);
		return retResult;
	}

	/**
	 * 指标设为默认接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/def")
	public Object setDefApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {

		RequestHeaderUtil.setUserRequestParamter(reqParams, request);

		RestResult retResult = analysisServiveImpl.analysisDef(reqParams);
		return retResult;

	}

	/**
	 * 区间统计接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/region")
	public Object regionApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisServiveImpl.analysisRegion(reqParams);
		return retResult;
	}

	/**
	 * 机构对比-> 获取机构比对的请求参数
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/org/get")
	public Object orgGetApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisServiveImpl.analysisOrgGet(reqParams);
		return retResult;
	}

	/**
	 * 机构对比-> 机构对比
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/org/compare")
	public Object orgCompareApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisServiveImpl.analysisOrgCompare(reqParams);
		return retResult;
	}

	/**
	 * 导出清单接口-> 导出清单列表接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/report/list")
	public Object reportListApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisServiveImpl.analysisReportList(reqParams);
		return retResult;
	}

	/**
	 * 导出清单接口-> 导出清单列表下载接口
	 * 
	 * @param reqParams
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/report/download")
	public Object reportDownloadApi(@RequestBody Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		return analysisServiveImpl.analysisReportDownload(reqParams, request, response);
	}

	@RequestMapping(value = "/org/search")
	public Object orgSearchApi(String orgKey, String subject, HttpServletRequest request) {
		Map<String, Object> reqParams = new HashMap<String, Object>();
		reqParams.put("orgKey", orgKey);
		reqParams.put("subject", subject);
		RequestHeaderUtil.setUserRequestParamter(reqParams, request);
		RestResult retResult = analysisServiveImpl.analysisOrgSearch(reqParams);
		return retResult;
	}
	
}
