package com.ehuatai.biz.analysis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ehuatai.biz.analysis.service.AnalysisSQLCacheService;
import com.ehuatai.biz.constants.RedisConstants;
import com.ehuatai.biz.constants.SqlParameter;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;

@Service
public class AnalysisSQLCacheServiceImpl implements AnalysisSQLCacheService {

	@Autowired
	private SQLMapper SQLMapper;

	private static final String dateType1 = "DAY";

	private static final String dateType2 = "MONTH";

	private static final String dateType3 = "YEAR";

	// /**
	// * @功能 {分析管理核心区域生成策略}
	// * @作者 MaxBill
	// * @时间 2017年9月5日 上午11:29:00
	// */
	// public String keyGenerator01(Map<String, Object> reqParams) {
	// String roleOrg = (String) reqParams.get("roleOrg");
	// String targetCode = (String) reqParams.get("targetCode");
	// String groupbyDate = (String) reqParams.get("groupbyDate");
	// String org = (String) reqParams.get("orgCode");
	// if (null != org && !org.equals("")) {
	// roleOrg = org;
	// }
	// String redisKey = "";
	// switch (groupbyDate) {
	// case dateType1:
	// redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType1;
	// break;
	// case dateType2:
	// redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType2;
	// break;
	// case dateType3:
	// redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-"
	// + dateType3;
	// break;
	// }
	// System.err.println(">>>>>>>>>>>>:" + redisKey);
	// return redisKey;
	// }

	/**
	 * @功能 {分析管理核心区域生成策略}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午11:29:00
	 */
	public String keyGenerator01(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_CORE + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_MAIN + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
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
			}
		} else {
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_SUBS + "-" + targetCode + "-G2-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
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
			}
		} else {
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_RANK + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3;
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("pOrg");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_LIST + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3;
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_SPEC + "-" + targetCode + "-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
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
			}
		} else {
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String targetCode = (String) reqParams.get("targetCode");
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_TORG + "-" + targetCode + "-G1-" + roleOrg + "-" + dateType3;
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
		String roleOrg = (String) reqParams.get("roleOrg");
		String subject = (String) reqParams.get("subject");
		String targetCode = subject;
		if (subject.equals("T10")) {
			targetCode = targetCode + "100";
		} else {
			targetCode = targetCode + "001";
		}
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
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
	public String keyGenerator10(Map<String, Object> reqParams) {
		String roleOrg = (String) reqParams.get("roleOrg");
		String subject = (String) reqParams.get("subject");
		String targetCode = subject;
		if (subject.equals("T10")) {
			targetCode = targetCode + "100";
		} else {
			targetCode = targetCode + "001";
		}
		String groupbyDate = (String) reqParams.get("groupbyDate");
		Object filters = reqParams.get("filters");
		String org = (String) reqParams.get("orgCode");
		if (null != org && !org.equals("")) {
			roleOrg = org;
		}
		String redisKey = "";
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(JSON.toJSONString(filters));
			if (!filterArray.isEmpty()) {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType1
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType2
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType3
							+ SqlParameter.fxglSqlParam(JSON.toJSONString(filters));
					break;
				}
			} else {
				switch (groupbyDate) {
				case dateType1:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
					break;
				case dateType2:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
					break;
				case dateType3:
					redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
					break;
				}
			}
		} else {
			switch (groupbyDate) {
			case dateType1:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType1;
				break;
			case dateType2:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType2;
				break;
			case dateType3:
				redisKey = RedisConstants.FXGL_DCQD + "-" + targetCode + "-" + roleOrg + "-" + dateType3;
				break;
			}
		}
		System.err.println(">>>>>>>>>>>>:" + redisKey);
		return redisKey;
	}

	@RedisCacheable(keyGenerator = "keyGenerator01")
	public List<Map<String, Object>> analysisCommon(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator02")
	public List<Map<String, Object>> analysisMain(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator03")
	public List<Map<String, Object>> analysisSub(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator04")
	public List<Map<String, Object>> analysisDist(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator05")
	public List<Map<String, Object>> analysisOrg(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator06")
	public List<Map<String, Object>> analysisSpec(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator07")
	public List<Map<String, Object>> analysisTorg(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> analysisDef(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> analysisRegion(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> analysisOrgGet(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator08")
	public List<Map<String, Object>> analysisOrgCompare(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator09")
	public List<Map<String, Object>> analysisReportList(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@RedisCacheable(keyGenerator = "keyGenerator10")
	public List<Map<String, Object>> analysisReportDownload(@RedisCacheKey Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

}
