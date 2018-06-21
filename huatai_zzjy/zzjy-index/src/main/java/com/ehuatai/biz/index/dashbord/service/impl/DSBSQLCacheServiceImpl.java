package com.ehuatai.biz.index.dashbord.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.index.dashbord.service.DSBCacheService;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class DSBSQLCacheServiceImpl implements DSBCacheService {

	@Autowired
	private SQLMapper SQLMapper;

	/**
	 * @功能 {产品前三名区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGeneratorTop01(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String subCode = (String) params.get("subCode");
		String redisKey = RedisConstants.DSB_PT + "-" + subCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {产品前三名区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGeneratorTop02(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String subCode = (String) params.get("target");
		String redisKey = RedisConstants.DSB_PT + "-" + subCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {新单保费图形区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator01(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = "";
		if (StringUtils.isEmpty(org)) {
			redisKey = RedisConstants.DSB_TX + "-" + targetCode + "-" + roleOrg;
		} else {
			redisKey = RedisConstants.DSB_TX + "-" + targetCode + "-" + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {新单保费排名区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator02(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = "";
		if (StringUtils.isEmpty(org)) {
			redisKey = RedisConstants.DSB_PM + "-" + targetCode + "-" + roleOrg;
		} else {
			redisKey = RedisConstants.DSB_PM + "-" + targetCode + "-" + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {人力区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator03(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = "";
		if (StringUtils.isEmpty(org)) {
			redisKey = RedisConstants.DSB_RL + "-" + targetCode + "-" + roleOrg;
		} else {
			redisKey = RedisConstants.DSB_RL + "-" + targetCode + "-" + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {产品区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator04(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String riskCode = (String) params.get("riskCode");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.DSB_CP + "-" + targetCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		if (!StringUtils.isEmpty(riskCode)) {
			redisKey = redisKey + "-" + riskCode;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {绩优区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator05(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.DSB_JY + "-" + targetCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {投产区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator06(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = "";
		if (StringUtils.isEmpty(org)) {
			redisKey = RedisConstants.DSB_TC + "-" + targetCode + "-" + roleOrg;
		} else {
			redisKey = RedisConstants.DSB_TC + "-" + targetCode + "-" + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {机构区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator07(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.DSB_JG + "-" + targetCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {主管区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator08(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.DSB_ZG + "-" + targetCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {K2区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator09(Map<String, Object> params) {
		String org = (String) params.get("org");
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.DSB_K2 + "-" + targetCode + "-";
		if (StringUtils.isEmpty(org)) {
			redisKey = redisKey + roleOrg;
		} else {
			redisKey = redisKey + org;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	@RedisCacheable(keyGenerator = "keyGeneratorTop01")
	public List<Map<String, Object>> selectTopThreeSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGeneratorTop02")
	public List<Map<String, Object>> selectTopThreeDetail(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> selectPremPicSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator02")
	public List<Map<String, Object>> selectPremRankSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator03")
	public List<Map<String, Object>> selectOrtherPeoSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator04")
	public List<Map<String, Object>> selectOrtherProSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator05")
	public List<Map<String, Object>> selectOrtherGoodSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator06")
	public List<Map<String, Object>> selectOrtherRateSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator07")
	public List<Map<String, Object>> selectOrtherDeptSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator08")
	public List<Map<String, Object>> selectOrtherLeadSQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator09")
	public List<Map<String, Object>> selectOrtherK2SQL(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

}
