package com.ehuatai.biz.tool.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.biz.tool.service.ToolSQLCacheService;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class ToolSQLCacheServiceImpl implements ToolSQLCacheService {

	@Autowired
	private SQLMapper SQLMapper;

	/**
	 * @功能 {预警生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator(Map<String, Object> params) {
		String roleOrg = (String) params.get("roleOrg");
		String targetCode = (String) params.get("targetCode");
		String redisKey = RedisConstants.WARN + "-" + targetCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	@RedisCacheable(keyGenerator = "keyGenerator")
	public List<Map<String, Object>> getActualVal(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

}
