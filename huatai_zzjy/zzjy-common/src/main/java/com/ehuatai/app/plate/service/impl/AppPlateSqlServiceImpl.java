package com.ehuatai.app.plate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ehuatai.app.plate.service.AppPlateSqlService;
import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class AppPlateSqlServiceImpl implements AppPlateSqlService {
	@Autowired
	private SQLMapper sqlMapper;

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

	@RedisCacheable(keyGenerator = "keyGenerator00")
	public List<Map<String, Object>> blockpages(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> getTrendMain(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator02")
	public List<Map<String, Object>> getTrendSub(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

}
