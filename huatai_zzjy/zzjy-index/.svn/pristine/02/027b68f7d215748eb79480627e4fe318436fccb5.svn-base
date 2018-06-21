package com.ehuatai.biz.index.fixed.service;

import java.util.List;
import java.util.Map;


public interface FixedSQLCacheService {

	/**
	 * 首页->2、固定指标->2.1 主要指标接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>>  fixedMain(Map<String, Object>reqParams,String sql);
	/**
	 * 首页->2、固定指标->2.1 主要指标接口 -> 2.1.1 主要指标下钻接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>>  fixedMainDrilldown(Map<String, Object>reqParams,String sql);
	
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> fixedMcount(Map<String, Object>reqParams,String sql);
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口 -> 2.2.2、月度排行榜接口 经营分析
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> fixedMcountInfo(Map<String, Object>reqParams,String sql);
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口 -> 2.2.2、月度排行榜接口 地图
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> fixedMcountRankingMap(Map<String, Object>reqParams,String sql);
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口 -> 2.2.2、月度排行榜接口 光荣榜
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> fixedMcountRankingHonor(Map<String, Object>reqParams,String sql);
	/**
	 * 首页->2、固定指标->2.2 月度指标统计接口 -> 2.2.2、月度排行榜接口 排行榜
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> fixedMcountRankingRank(Map<String, Object>reqParams,String sql);
	/**
	 * 首页->2、固定指标-> 2.3、月度指标列表(核心指标统计)接口
	 * @param reqParams
	 * @return
	 */
	public List<Map<String, Object>> fixedCore(Map<String, Object> reqParams,String sql);
}
