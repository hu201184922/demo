package com.ehuatai.biz.index.fixed.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.index.fixed.service.FixedSQLCacheService;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class FixedSQLCacheServiceImpl implements FixedSQLCacheService {

	@Autowired
	private SQLMapper sqlMapper;

	/**
	 * @功能 {核心指标生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator01(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String code = (String) params.get("code");
		String parent = (String) params.get("parent");
		String by = (String) params.get("by");
		String targetCode = (String) params.get("targetCode");
		String dateType = (String) params.get("dateType");
		String redisKey = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		int year = calendar.get(Calendar.YEAR);
		if (null == targetCode) {
			targetCode = (String) params.get("target");
		}
		switch (by) {
		case "org":
			if (null == parent) {
				redisKey = RedisConstants.GDZB_CORE + "-" + targetCode + "-G1-" + roleOrg + "-" + code;
			} else {
				redisKey = RedisConstants.GDZB_CORE + "-" + targetCode + "-G1-" + parent + "-" + code;
			}
			break;
		case "date":
			if (code.startsWith("ALL-")) {
				code = roleOrg;
			}
			switch (dateType) {
			case "YEAR":
				redisKey = RedisConstants.GDZB_CORE + "-" + targetCode + "-G2" + "-MONTH-" + code + "-" + year;
				break;
			case "MONTH":
				if (parent.length() == 1) {
					redisKey = RedisConstants.GDZB_CORE + "-" + targetCode + "-G2" + "-DAY-" + code + "-0" + parent;
				} else {
					redisKey = RedisConstants.GDZB_CORE + "-" + targetCode + "-G2" + "-DAY-" + code + "-" + parent;
				}
				break;
			}
			break;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {月度统计指标生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator02(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.GDZB_MONT + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {核心地图指标生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator03(Map<String, Object> params) {
		String targetCode = (String) params.get("monthTarget");
		String redisKey = RedisConstants.GDZB_MAPS + "-" + targetCode;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {光荣指标生成策略生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator04(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("monthTarget");
		String redisKey = RedisConstants.GDZB_HONO + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {排行指标生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator05(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("monthTarget");
		String redisKey = RedisConstants.GDZB_RANK + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {核心统计指标生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator06(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.GDZB_LIST + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {经营信息指标生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator07(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("jyfx");
		String redisKey = RedisConstants.GDZB_JYFX + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> fixedMain(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> fixedMainDrilldown(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator02")
	public List<Map<String, Object>> fixedMcount(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator03")
	public List<Map<String, Object>> fixedMcountRankingMap(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator04")
	public List<Map<String, Object>> fixedMcountRankingHonor(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator05")
	public List<Map<String, Object>> fixedMcountRankingRank(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator06")
	public List<Map<String, Object>> fixedCore(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator07")
	public List<Map<String, Object>> fixedMcountInfo(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}
}
