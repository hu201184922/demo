package com.ehuatai.biz.plate.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.orm.Pager;

public interface PlateSQLCacheService {

	/**
	 * 2、板块指标值接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> blockTarget(Map<String, Object> reqParams, String sql);

	/**
	 * 3、板块机构指标值接口
	 * 
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> blockOrgTarget(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.2、常用指标接口（common）
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendCommon(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.3、一级指标区域接口（main）
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendMain(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 4、板块趋势图接口-> 4.4、二级指标区域接口（sub）
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendSub(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.6、机构列表区域接口（org）
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendDist(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.6、机构列表区域接口（org）
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendOrg(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 4、板块趋势图接口->4.8、特殊指标区域接口（spec）
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendSpec(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 区间统计
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendRegion(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 机构对比
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendCompare(Map<String, Object> reqParams, String sql);

	/**
	 * 四、板块接口-> 导出清单
	 * 
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getTrendReportList(Map<String, Object> reqParams, String sql);
}
