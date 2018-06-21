package com.ehuatai.app.analysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.app.analysis.service.AppAnalysisSQLCacheService;
import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class AppAnalysisSQLCacheServiceImpl implements AppAnalysisSQLCacheService {

	@Autowired
	private SQLMapper SQLMapper;

	private static final String dateType1 = "DAY";

	private static final String dateType2 = "MONTH";

	private static final String dateType3 = "YEAR";

	/**
	 * @功能 {分析管理虚拟区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator00(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String redisKey = RedisConstants.FXGL_VIRT + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理一级区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator01(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		String redisKey = "";
		switch (groupbyDate) {
		case dateType1:
			redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType1;
			break;
		case dateType2:
			redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType2;
			break;
		case dateType3:
			redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType3;
			break;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	// public String keyGenerator01(Map<String, Object> reqParams) {
	// String roleOrg = (String) reqParams.get("roleOrg");
	// String targetCode = (String) reqParams.get("targetCode");
	// String groupbyDate = (String) reqParams.get("groupbyDate");
	// Object filters = reqParams.get("filters");
	// String redisKey = "";
	// if (null != filters && !filters.equals("")) {
	// // 解析筛选参数集
	// JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
	// if (!filterArray.isEmpty()) {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType1
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType2
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType3
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// }
	// } else {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType3;
	// break;
	// }
	// }
	// } else {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType3;
	// break;
	// }
	// }
	// System.err.println(">>>>>>>>>>>>:" + redisKey);
	// return redisKey;
	// }

	/**
	 * @功能 {分析管理二级区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator02(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		String redisKey = "";
		switch (groupbyDate) {
		case dateType1:
			redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType1;
			break;
		case dateType2:
			redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType2;
			break;
		case dateType3:
			redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType3;
			break;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	// public String keyGenerator02(Map<String, Object> reqParams) {
	// String roleOrg = (String) reqParams.get("roleOrg");
	// String targetCode = (String) reqParams.get("targetCode");
	// String groupbyDate = (String) reqParams.get("groupbyDate");
	// Object filters = reqParams.get("filters");
	// String redisKey = "";
	// if (null != filters && !filters.equals("")) {
	// // 解析筛选参数集
	// JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
	// if (!filterArray.isEmpty()) {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType1
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType2
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType3
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// }
	// } else {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType3;
	// break;
	// }
	// }
	// } else {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg +
	// "-" + dateType3;
	// break;
	// }
	// }
	// System.err.println(">>>>>>>>>>>>:" + redisKey);
	// return redisKey;
	// }

	/**
	 * @功能 {分析管理特殊区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator03(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		String redisKey = "";
		switch (groupbyDate) {
		case dateType1:
			redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
			break;
		case dateType2:
			redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
			break;
		case dateType3:
			redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
			break;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	// public String keyGenerator03(Map<String, Object> reqParams) {
	// String roleOrg = (String) reqParams.get("roleOrg");
	// String targetCode = (String) reqParams.get("targetCode");
	// String groupbyDate = (String) reqParams.get("groupbyDate");
	// Object filters = reqParams.get("filters");
	// String redisKey = "";
	// if (null != filters && !filters.equals("")) {
	// // 解析筛选参数集
	// JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
	// if (!filterArray.isEmpty()) {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType1
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType2
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType3
	// + SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
	// break;
	// }
	// } else {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType3;
	// break;
	// }
	// }
	// } else {
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType3;
	// break;
	// }
	// }
	// System.err.println(">>>>>>>>>>>>:" + redisKey);
	// return redisKey;
	// }

	@Override
	public List<Map<String, Object>> appAnalysisNavsSQL(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator00")
	public List<Map<String, Object>> appAnalysisTargetSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> appAnalysisTargetMainSQL(@RedisCacheKey Map<String, Object> reqParams,
			String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator02")
	public List<Map<String, Object>> appAnalysisTargetSubSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator03")
	public List<Map<String, Object>> appAnalysisTargetSpecSQL(@RedisCacheKey Map<String, Object> reqParams,
			String sql) {
		return SQLMapper.findBySQL(sql);
	}
}
