package com.ehuatai.biz.plate.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.constants.SqlParameter;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.biz.plate.service.PlateSQLCacheService;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class PlateSQLCacheServiceImpl implements PlateSQLCacheService {

	@Autowired
	private SQLMapper sqlMapper;

	private static final String dateType1 = "DAY";

	private static final String dateType2 = "MONTH";

	private static final String dateType3 = "YEAR";

	/**
	 * @功能 {分析管理核心区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator01(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理一级区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator02(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理二级区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator03(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理排名区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator04(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理列表区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator05(Map<String, Object> reqParams) {
		String distOrg = (String) reqParams.get("distOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + distOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理特殊区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator06(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {分析管理保费区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator07(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {机构对比区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator08(Map<String, Object> reqParams) {
		String compareOrg = (String) reqParams.get("compareOrg");
		String targetCode = (String) reqParams.get("target");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_JGDB + "-" + targetCode + "-" + compareOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {导出清单区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator09(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String orgCode = (String) reqParams.get("orgCode");
		String roleOrg = (String) reqParams.get("roleOrg");
		String blockCode = (String) reqParams.get("blockCode");
		String targetCode = blockCode + "001";
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String queOrg = "";
		if (StringUtils.isEmpty(orgCode)) {
			if (StringUtils.isEmpty(ptOrg)) {
				queOrg = roleOrg;
			} else {
				queOrg = ptOrg;
			}
		} else {
			queOrg = orgCode;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + queOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {板块上部区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGeneratorOver(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String subCode = (String) reqParams.get("blockCode");
		String redisKey = RedisConstants.BKFW_OVER + "-" + subCode + "-" + roleOrg;
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	/**
	 * @功能 {板块下钻区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGeneratorDown(Map<String, Object> reqParams) {
		String ptOrg = (String) reqParams.get("ptOrg");
		String roleOrg = (String) reqParams.get("roleOrg");
		String subCode = (String) reqParams.get("blockCode");
		String redisKey = "";
		if (StringUtils.isEmpty(ptOrg)) {
			redisKey = RedisConstants.BKFW_DOWN + "-" + subCode + "-" + roleOrg;
		} else {
			redisKey = RedisConstants.BKFW_DOWN + "-" + subCode + "-" + ptOrg;
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	@RedisCacheable(keyGenerator = "keyGeneratorOver")
	public List<Map<String, Object>> blockTarget(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGeneratorDown")
	public List<Map<String, Object>> blockOrgTarget(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> getTrendCommon(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator02")
	public List<Map<String, Object>> getTrendMain(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator03")
	public List<Map<String, Object>> getTrendSub(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator04")
	public List<Map<String, Object>> getTrendDist(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator05")
	public List<Map<String, Object>> getTrendOrg(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator06")
	public List<Map<String, Object>> getTrendSpec(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator07")
	public List<Map<String, Object>> getTrendTOrg(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> getTrendRegion(Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator08")
	public List<Map<String, Object>> getTrendCompare(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator09")
	public List<Map<String, Object>> getTrendReportList(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return sqlMapper.findBySQL(sql);
	}

}
