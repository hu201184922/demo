package com.ehuatai.biz.plate.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehuatai.ret.RestResult;

public interface PlateService {

	/**
	 * 1、板块菜单接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult blockNavs(Map<String, Object> reqParams);

	/**
	 * 2、板块指标值接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult blockTarget(Map<String, Object> reqParams);

	/**
	 * 3、板块机构指标值接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult blockOrgTarget(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.1、基本接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendBase(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.2、常用指标接口（common）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendCommon(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.3、一级指标区域接口（main）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendMain(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.4、二级指标区域接口（sub）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendSub(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.5、分公司/机构区域接口（dist）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendDist(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.6、机构列表区域接口（org）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendOrg(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.7、保费部（torg）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendTorg(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.8、特殊指标区域接口（spec）
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendSpec(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.9、指标设为默认接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult setTrendDef(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.10 区间统计
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendRegion(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.11 机构对比 ->4.11.1 获取机构对比的参数
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendOrgGet(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.11 机构对比 ->4.11.2 机构对比接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendOrgCompare(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.12 导出清单接口->4.12.1 导出清单列表接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public RestResult getTrendReportList(Map<String, Object> reqParams);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.12 导出清单接口->4.12.2 导出清单列表下载接口
	 * 
	 * @param reqParams
	 * @param request 
	 * @return
	 */
	public RestResult getTrendReportDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 5、我的板块接口
	 * 
	 * @return
	 */
	public RestResult blockMyApi(Map<String, Object> reqParams);

	/**
	 * 5、我的板块接口 -> 5.1、删除自定义板块
	 * 
	 * @return
	 */
	public RestResult blockMyDelete(Map<String, Object> reqParams);

	/**
	 * 5、我的板块接口 -> 5.2、新增板块 || 5.3、编辑板块
	 * 
	 * @return
	 */
	public RestResult blockMyEditTarget(Map<String, Object> reqParams);

	/**
	 * 5、我的板块接口 -> 5.2、新增板块 || 5.3、编辑板块 -> 5.2.1、保存操作接口
	 * 
	 * @return
	 */
	public RestResult blockMyEditSave(Map<String, Object> reqParams);

	public RestResult blockOrgSearch(Map<String, Object> reqParams);
}
