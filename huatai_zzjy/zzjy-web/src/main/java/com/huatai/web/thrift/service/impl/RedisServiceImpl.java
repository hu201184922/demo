package com.huatai.web.thrift.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.mapper.BaseMapper;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.QueryDimDetailMapper;
import com.huatai.web.mapper.QueryDimMapper;
import com.huatai.web.mapper.SQLMapper;
import com.huatai.web.mapper.TarInitSqlMapper;
import com.huatai.web.mapper.TarQueryDimMapper;
import com.huatai.web.mapper.TarRegMapper;
import com.huatai.web.mapper.TarRegQueMapper;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.mapper.TemRegMapper;
import com.huatai.web.mapper.UserSetWarnMapper;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetailExample;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarQueryDimExample;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TargetExample;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;
import com.huatai.web.model.UserSetWarn;
import com.huatai.web.model.UserSetWarnExample;
import com.huatai.web.thrift.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private SQLMapper sqlMapper;

	@Autowired
	private TarRegMapper tarRegMapper;

	@Autowired
	private TemRegMapper temRegMapper;

	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;

	@Autowired
	private TargetMapper targetMapper;

	@Autowired
	private UserSetWarnMapper userSetWarnMapper;

	@Autowired
	private TarInitSqlMapper tarInitSqlMapper;

	@Autowired
	private GroDimDetailMapper groDimDetailMapper;

	@Autowired
	private TarQueryDimMapper tarQueryDimMapper;

	@Autowired
	private QueryDimMapper queryDimMapper;

	@Autowired
	private QueryDimDetailMapper queryDimDetailMapper;

	@Autowired
	private TarRegQueMapper tarRegQueMapper;

	List<String> subList = null;

	public RedisServiceImpl() {
		subList = new ArrayList<>();
		subList.add("T07");
		subList.add("P01");
	}

	/**
	 * @功能 {sql通用查询}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:31:32
	 */
	public List<HashMap<String, Object>> getSqlData(String sql) {
		return this.baseMapper.getDataResult(sql);
	}

	/**
	 * @功能 {查询最低机构的机构列表}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:01:25
	 */
	public List<HashMap<String, String>> getOrgList(String subCode, String orgType) {
		return baseMapper.getAllManageAndCompany();
	}

	/**
	 * @功能 {查询主页机构列表}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:01:25
	 */
	public List<HashMap<String, String>> getOrgListByIndex(String subCode, String orgType) {
		if (subList.contains(subCode)) {
			// 查询到分公司，分组到中支
			return baseMapper.getAllManageAndCompany();
		} else {
			// 查询到中支，分组到四级机构
			return baseMapper.getAllManageAndCompanyAndBranch();
		}
	}

	/**
	 * @功能 {查询最低机构的机构列表}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:01:25
	 */
	public List<HashMap<String, String>> getOrgListBack(String orgType) {
		List<HashMap<String, String>> orgList = null;
		switch (orgType) {
		case "8":
			orgList = baseMapper.getAllManage();
			break;
		case "7":
			orgList = baseMapper.getAllManageAndCompany();
			break;
		case "6":
			orgList = baseMapper.getAllManageAndCompanyAndBranch();
			break;
		default:
			orgList = baseMapper.getAllManageAndCompanyAndBranch();
			break;
		}
		return orgList;
	}

	/**
	 * @功能 {查询全部的营销服务部}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:01:25
	 */
	public List<HashMap<String, String>> getServerOrgList() {
		return baseMapper.getAllServerOrg();
	}

	/**
	 * @功能 {查询全部的成本中心}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:01:25
	 */
	public List<HashMap<String, String>> getCenterOrgList() {
		return baseMapper.getAllCenterOrg();
	}

	/**
	 * @功能 {查询产品的前三名}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 上午11:01:25
	 */
	public List<Map<String, Object>> getTopThreeProduct(String parentCode, String orgCode) {
		// 配置前三名产品
		List<Map<String, Object>> productList = new ArrayList<>();
		String sql = "";
		switch (parentCode) {
		case "DAS0103G02":
			sql = "SELECT XYZ.RCODE AS CODE,B.RISKABBR AS NAME   FROM (" + "  SELECT XY.RCODE,XY.VAL FROM ( SELECT "
					+ "     X.RCODE,DECODE(NVL(Y.VAL1,0),0,-9999, X.VAL1/Y.VAL1*100 ) AS VAL"
					+ "      FROM( SELECT A.RISK_CODE AS RCODE, NVL(SUM(A.BILLING_MANPOW), 0) AS VAL1"
					+ "       FROM HT_CAPACITY_DAY_TB A "
					+ " 	JOIN D_RISK B ON A.RISK_CODE = B.RISKCODE AND B.DIMEN10CODE='01' "
					+ "  	AND B.DIMEN1CODE <> '03' AND B.DIMEN9CODE = '01' "
					+ "		LEFT JOIN D_ORGANIZATION D ON A.GROUP_ID = D.AGENTGROUPID"
					+ "             WHERE 1 = 1  AND {orgcode_condition}"
					+ "              AND A.DATE_CODE = TO_CHAR(SYSDATE - 1, 'YYYY-MM-DD')"
					+ "              GROUP BY A.RISK_CODE" + "           ) X ,"
					+ "         ( SELECT  NVL(SUM(A.EFFECTIVE_MANPOW), 0) AS VAL1"
					+ "             FROM HT_CAPACITY_DAY_TB A "
					+ "             LEFT JOIN D_ORGANIZATION D ON A.GROUP_ID = D.AGENTGROUPID"
					+ "              WHERE 1 = 1        " + "               AND {orgcode_condition}"
					+ "               AND A.DATE_CODE = TO_CHAR(SYSDATE - 1, 'YYYY-MM-DD')"
					+ "          ) Y ORDER BY VAL DESC " + "       ) XY WHERE ROWNUM<=3 " + "    ) XYZ"
					+ "     JOIN D_RISK B ON XYZ.RCODE = B.RISKCODE  AND B.DIMEN10CODE = '01'"
					+ "		AND B.DIMEN1CODE <> '03' AND B.DIMEN9CODE = '01' ";
			break;
		case "DAS0103G03":
			sql = "SELECT X.RISK_CODE AS CODE, B.RISKABBR AS NAME"
					+ "  FROM (SELECT A.RISK_CODE, DECODE(NVL(SUM(A.LIFE_COV_NATURAL_NUM), 0), 0, 0,"
					+ "          NVL(SUM(A.LIFE_COV_STAD_PREM), 0) /"
					+ "                SUM(A.LIFE_COV_NATURAL_NUM)) AS ACTIVE_LV,"
					+ "                ROW_NUMBER() OVER(ORDER BY DECODE(NVL(SUM(A.LIFE_COV_NATURAL_NUM), 0), 0, 0, "
					+ "                    NVL(SUM(A.LIFE_COV_STAD_PREM), 0) / SUM(A.LIFE_COV_NATURAL_NUM)) DESC) RN"
					+ "			FROM HT_CAPACITY_DAY_TB A"
					+ "      LEFT JOIN D_ORGANIZATION D ON A.GROUP_ID = D.AGENTGROUPID" + "     WHERE 1 = 1"
					+ "       AND {orgcode_condition}" + "       AND A.DATE_CODE = TO_CHAR(SYSDATE - 1, 'YYYY-MM-DD')"
					+ "      GROUP BY A.RISK_CODE) X" + "		LEFT JOIN D_RISK B ON X.RISK_CODE = B.RISKCODE"
					+ "		WHERE X.RN <= 3";
			break;
		default:
			break;
		}
		if (!sql.equals("") && sql.contains("{orgcode_condition}")) {
			String orgcode_condition = "";
			int len = orgCode.length();
			switch (len) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '" + orgCode + "'";
				break;
			// 分公司
			case 3:
				orgcode_condition = "PROVCOMCODE ='" + orgCode + "'";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + orgCode + "'";
				break;
			// 营销服务部
			default:
				orgcode_condition = "TEAMCOMCODE ='" + orgCode + "'";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
		}
		if (!sql.equals("")) {
			List<Map<String, Object>> vals = sqlMapper.findBySQL(sql);
			if (null != vals && !vals.isEmpty()) {
				for (Map<String, Object> val : vals) {
					if (StringUtil.isNotNull(val)) {
						Map<String, Object> product = new HashMap<>();
						product.put("NAME", val.get("NAME"));
						product.put("CODE", val.get("CODE"));
						productList.add(product);
					}
				}
			}
		}
		return productList;
	}

	/**
	 * @功能 {按照主题和区域查询}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午5:53:42
	 */
	public List<TarReg> findTarRegsBySubAndReg(String sub, Integer regId) {
		TarRegExample tarRegExample = new TarRegExample();
		if (null == sub || sub.equals("")) {
			tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId);
		} else {
			tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId).andSubCodeEqualTo(sub);
		}
		return this.tarRegMapper.selectByExample(tarRegExample);
	}

	/**
	 * @功能 {按code查询区域}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:16:19
	 */
	public TemReg findRegionByCode(String regCode) {
		TemRegExample temRegExample = new TemRegExample();
		temRegExample.createCriteria().andOpTypeNotEqualTo("D").andRegCodeEqualTo(regCode);
		List<TemReg> temRegs = this.temRegMapper.selectByExample(temRegExample);
		if (null != temRegs && temRegs.size() == 1) {
			return temRegs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按指标、区域、维度查询TarRegSql}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:34:58
	 */
	public TarRegSql findTarRegSql(String target, Integer regId, String groupType, String groupDetail,
			String dateType) {
		List<TarRegSql> tarRegSqls = this.tarRegSqlMapper.getRedisTarRegSql(target, regId, groupType, groupDetail,
				dateType);
		if (null != tarRegSqls && tarRegSqls.size() == 1) {
			return tarRegSqls.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按code查询指标}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 下午4:50:20
	 */
	public Target findTargetByCode(String code) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(code);
		List<Target> targets = this.targetMapper.selectByExample(targetExample);
		if (null != targets && targets.size() == 1) {
			return targets.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询已开启的}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 上午11:50:29
	 */
	public List<UserSetWarn> getUserSetWarnListByOn() {
		UserSetWarnExample userSetWarnExample = new UserSetWarnExample();
		userSetWarnExample.createCriteria().andOpTypeNotEqualTo("D").andWarnStatusEqualTo("1");
		return this.userSetWarnMapper.selectByExample(userSetWarnExample);
	}

	/**
	 * @功能 {查询基础 sql}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:24:03
	 */
	public Object getTarInitSql(String fun, String targetCode, String groupType, String groupDetail, String dateType) {
		List<TarInitSql> tarInitSqlList = this.tarInitSqlMapper.getTarInitSql(fun, targetCode, groupType, groupDetail,
				dateType);
		if (null != tarInitSqlList && tarInitSqlList.size() > 0) {
			return tarInitSqlList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {根据指标查询分组维度}
	 * @作者 MaxBill
	 * @时间 2017年9月12日 上午11:10:50
	 */
	public List<GroDimDetail> findGroDimDetailListByTarget(String groupType, String target) {
		return this.groDimDetailMapper.findGroDetailListByTarget(groupType, target);
	}

	/**
	 * @功能 {按指标查询}
	 * @作者 MaxBill
	 * @时间 2017年7月17日 上午10:52:20
	 */
	public List<TarQueryDim> findTarQueryDimByTar(String tar) {
		TarQueryDimExample tarQueryDimExample = new TarQueryDimExample();
		tarQueryDimExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar);
		tarQueryDimExample.setOrderByClause("QD_ID ASC");
		return this.tarQueryDimMapper.selectByExample(tarQueryDimExample);
	}

	/**
	 * @功能 {按指标查询}
	 * @作者 MaxBill
	 * @时间 2017年7月17日 上午10:52:20
	 */
	public QueryDim findQueryDimById(Integer id) {
		QueryDim queryDim = this.queryDimMapper.selectByPrimaryKey(id);
		if (null != queryDim) {
			QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
			queryDimDetailExample.createCriteria().andOpTypeNotEqualTo("D").andQdIdEqualTo(id);
			queryDim.setQueryDimDetail(this.queryDimDetailMapper.selectByExample(queryDimDetailExample));
			return queryDim;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按主题、模板、区域、指标查询TarRegQue}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午3:13:14
	 */
	public List<TarRegQue> findTarRegQueBySubAndTempAndRegAndTar(String sub, String temp, Integer regId, String tar) {
		return this.tarRegQueMapper.findTarRegQueBySubAndTempAndRegAndTar(sub, temp, regId, tar);
	}

}
