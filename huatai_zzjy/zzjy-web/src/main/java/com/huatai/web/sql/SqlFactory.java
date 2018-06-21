package com.huatai.web.sql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.FieldInterBean;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.thrift.service.CommonService;

/**
 * @功能 {SQL通用处理工厂}
 * @作者 MaxBill
 * @时间 2017年8月21日 上午10:45:52
 */
@Component
public class SqlFactory {

	private CommonService commonService;

	private CommonUtil commonUtil;

	public SqlFactory() {
		commonService = SpringUtil.getBean(CommonService.class);
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * @功能 {固定指标SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午7:35:36
	 */
	public String getGdzbSql(String sqlConstants, TarRegSql tarRegSql, String roleOrg, String deptCode, String group,
			Integer nextOrg) {
		// sql基础处理
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 替换代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		switch (sqlConstants.toString()) {
		case "":
			break;
		case SqlConstants.GDZB_01:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_code = "PROVCOMCODE";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				break;
			// 分公司
			case 3:
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_code = "COUNTCOMCODE";
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case 10:
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			break;
		case SqlConstants.GDZB_02:
			switch (group) {
			case "ORG_GROUP":
				switch (roleOrg.length()) {
				// 总公司
				case 1:
					orgcode_condition = "HEADCOMCODE = '1'";
					orgcode_group_name = "TEAMCOMSHORTNAME";
					orgcode_group_code = "PROVCOMCODE";
					break;
				// 分公司
				case 3:
					if (roleOrg.equals("101")) {
						orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
					} else {
						orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
					}
					orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
					orgcode_group_code = "COUNTCOMCODE";
					break;
				// 中支
				case 5:
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
					if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
						orgcode_group_name = "TEAMCOMNAME";
						orgcode_group_code = "TEAMCOMCODE";
					} else {
						orgcode_group_name = "COST_CENTERDEVNAME";
						orgcode_group_code = "COST_CENTERSTDCODE";
					}
					break;
				// 成本中心
				case 10:
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_group_name = "HEADGROUPNAME";
					orgcode_group_code = "HEADGROUPCODE";
					break;
				}
				sql = sql.replace("{orgcode_condition}", orgcode_condition);
				sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
				sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
				break;
			case "DATE_GROUP":
				switch (nextOrg) {
				// 分公司参数
				case 3:
					orgcode_condition = "PROVCOMCODE = '" + roleOrg + "'";
					break;
				// 中支参数
				case 5:
					orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
					break;
				// 营销服务部
				case 10:
					if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
						orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					} else {
						orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					}
					break;
				// 营销服务部
				case 11:
					orgcode_condition = "HEADGROUPCODE ='" + roleOrg + "'";
					break;
				}
				sql = sql.replace("{orgcode_condition}", orgcode_condition);
				break;
			}
			break;
		case SqlConstants.GDZB_03:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case 10:
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	/**
	 * @功能 {固定指标SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午7:35:36
	 */
	public String getGdzbSqlWithAll(TarRegSql tarRegSql, String roleOrg, String deptCode) {
		// sql基础处理
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 替换代码条件
		String orgcode_condition = "";
		switch (roleOrg.length()) {
		// 总公司参数
		case 1:
			orgcode_condition = "HEADCOMCODE = '" + roleOrg + "'";
			break;
		// 分公司参数
		case 3:
			orgcode_condition = "PROVCOMCODE = '" + roleOrg + "'";
			break;
		// 中支参数
		case 5:
			orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
			break;
		// 营销服务部
		case 10:
			orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			break;
		case 11:
			orgcode_condition = "HEADGROUPCODE = '" + roleOrg + "'";
			break;
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		return sql;
	}

	/**
	 * @功能 {固定指标数据下转SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月28日 上午9:36:50
	 */
	public String getGdzbDownSqlWithAll(TarRegSql tarRegSql, String roleOrg, String deptCode, String dateType,
			String value) {
		// sql基础处理
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		// 替换代码条件
		String orgcode_condition = "";
		switch (roleOrg.length()) {
		// 总公司参数
		case 1:
			orgcode_condition = "HEADCOMCODE = '" + roleOrg + "'";
			break;
		// 分公司参数
		case 3:
			orgcode_condition = "PROVCOMCODE = '" + roleOrg + "'";
			break;
		// 中支参数
		case 5:
			orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
			break;
		case 10:
			orgcode_condition = "COST_CENTERSTDCODE = '" + roleOrg + "'";
			break;
		case 11:
			orgcode_condition = "HEADGROUPCODE = '" + roleOrg + "'";
			break;
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		if (dateType.equals("MONTH")) {
			if (null != value && value.length() == 1) {
				value = LocalDate.now().getYear() + "-0" + value;
			} else if (null != value && value.length() == 2) {
				value = LocalDate.now().getYear() + "-" + value;
			}
			sql = sql.replace("{month_condition}", "'" + value + "'");
		}
		return sql;
	}

	/**
	 * @功能 {固定指标经营分析的SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年9月14日 上午11:25:59
	 */
	public String getOperateInfo(String sqlCode, String roleOrg, String deptCode, String org) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		// 替换代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		switch (roleOrg.length()) {
		// 总公司
		case 1:
			orgcode_condition = "HEADCOMCODE = '1'";
			orgcode_group_name = "TEAMCOMSHORTNAME";
			orgcode_group_code = "PROVCOMCODE";
			break;
		// 分公司
		case 3:
			orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			break;
		// 中支
		case 5:
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			break;
		// 营销服务部
		case 10:
			orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			break;
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
		sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
		if (null != org && !org.equals("")) {
			sql = sql.replace("{org_code}", org);
		}
		return sql;
	}

	/**
	 * @功能 {固定指标数据下转SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月28日 上午9:36:50
	 */
	public String getGdzbDownSql(TarRegSql tarRegSql, String roleOrg, String deptCode, String group, String dateType,
			String value, Integer nextOrg) {
		// sql基础处理
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		// 替换代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		switch (group) {
		case "ORG_GROUP":
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + value + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + value + "' AND COUNTCOMCODE<>'" + value + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				// 特殊处理北京
				if (value.equals("10101")) {
					orgcode_condition = "'BJ'='BJ' AND PROVCOMCODE='101'";
				} else {
					orgcode_condition = "COUNTCOMCODE ='" + value + "'";
				}
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;

			// 营销服务部
			default:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case "DATE_GROUP":
			switch (nextOrg) {
			// 分公司参数
			case 3:
				orgcode_condition = "PROVCOMCODE = '" + roleOrg + "'";
				break;
			// 中支参数
			case 5:
				orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
				break;
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE = '" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE = '" + roleOrg + "'";
				}
				break;
			case 11:
				orgcode_condition = "HEADGROUPCODE = '" + roleOrg + "'";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			if (dateType.equals("MONTH")) {
				if (null != value && value.length() == 1) {
					value = LocalDate.now().getYear() + "-0" + value;
				} else if (null != value && value.length() == 2) {
					value = LocalDate.now().getYear() + "-" + value;
				}
				sql = sql.replace("{month_condition}", "'" + value + "'");
			}
			break;
		}
		return sql;
	}

	/**
	 * @功能 {按用户机构分析管理SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getZtfxSql(String sqlConstants, String sqlCode, String roleOrg, String deptCode) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String orgcode_field = "";
		switch (sqlConstants.toString()) {
		case SqlConstants.FXGL_01:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_field = "HEADCOMCODE";
				break;
			// 分公司
			case 3:
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 成本中心
			case 10:
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			break;
		case SqlConstants.FXGL_02:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_field = "HEADCOMCODE";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_field = "PROVCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";

				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			case 10:
				orgcode_field = "COST_CENTERSTDCODE";
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_03:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_field = "HEADCOMCODE";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司参数
			case 3:
				orgcode_field = "PROVCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支参数
			case 5:
				orgcode_field = "COUNTCOMCODE";
				// 北京特殊处理
				if (roleOrg.equals("10101")) {
					orgcode_condition = " 'BJ'='BJ' AND PROVCOMCODE = '101'";
				} else {
					orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
				}
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			default:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_04:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_field = "HEADCOMCODE";
				break;
			// 分公司
			case 3:
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 营销服务部
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			default:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			break;
		case SqlConstants.FXGL_05:
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";

			} else {
				orgcode_field = "COST_CENTERSTDCODE";
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_06:
			orgcode_condition = "HEADGROUPCODE ='" + roleOrg + "'";
			orgcode_field = "HEADGROUPCODE";
			orgcode_group_name = "PROVGROUPNAME";
			orgcode_group_code = "PROVGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	/**
	 * @功能 {分析管理机构对比}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getFxglJgdbSql(String sqlCode, String roleOrg, String orgCode, String deptCode, String orgType) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + orgCode + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_field = "";
		if (StringUtils.isEmpty(orgType)) {
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				if (orgCode.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + orgCode + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + orgCode + "' AND COUNTCOMCODE<>'" + orgCode + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_condition = "COUNTCOMCODE ='" + orgCode + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + orgCode + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + orgCode + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case 10:
				orgcode_condition = "HEADGROUPCODE ='" + orgCode + "'";
				orgcode_field = "HEADGROUPCODE";
				break;
			}
		} else {
			switch (orgType) {
			// 分公司
			case "7":
				orgcode_condition = "COUNTCOMCODE ='" + orgCode + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 中支
			case "6":
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + orgCode + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + orgCode + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case "501":
				orgcode_condition = "HEADGROUPCODE ='" + orgCode + "'";
				orgcode_field = "HEADGROUPCODE";
				break;
			// 成本中心
			case "502":
				orgcode_condition = "HEADGROUPCODE ='" + orgCode + "'";
				orgcode_field = "HEADGROUPCODE";
				break;
			}
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		sql = sql.replace("{orgcode_field}", orgcode_field);
		return sql;
	}

	/**
	 * @功能 {板块分析机构对比}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getBkfxJgdbSql(String sqlCode, String roleOrg, String orgCode, Boolean isServer, String orgType) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + orgCode + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_field = "";
		if (StringUtils.isEmpty(orgType)) {
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				if (orgCode.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + orgCode + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + orgCode + "' AND COUNTCOMCODE<>'" + orgCode + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_condition = "COUNTCOMCODE ='" + orgCode + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + orgCode + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + orgCode + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case 10:
				orgcode_condition = "HEADGROUPCODE ='" + orgCode + "'";
				orgcode_field = "HEADGROUPCODE";
				break;
			}
		} else {
			switch (orgType) {
			// 分公司
			case "7":
				orgcode_condition = "COUNTCOMCODE ='" + orgCode + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 中支
			case "6":
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + orgCode + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + orgCode + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case "501":
				orgcode_condition = "HEADGROUPCODE ='" + orgCode + "'";
				orgcode_field = "HEADGROUPCODE";
				break;
			// 成本中心
			case "502":
				orgcode_condition = "HEADGROUPCODE ='" + orgCode + "'";
				orgcode_field = "HEADGROUPCODE";
				break;
			}
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		sql = sql.replace("{orgcode_field}", orgcode_field);
		return sql;
	}

	/**
	 * @功能 {按机构搜索分析管理SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getZtfxSqlByOrg(String sqlConstants, String sqlCode, String roleOrg, String deptCode,
			String orgType) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String orgcode_field = "";
		switch (sqlConstants.toString()) {
		case SqlConstants.FXGL_ORG0101:
			switch (orgType) {
			// 分公司
			case "7":
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 中支
			case "6":
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 四级机构
			default:
				if (orgType.equals("501")) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_field = "TEAMCOMCODE";
				}
				if (orgType.equals("502")) {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			break;
		case SqlConstants.FXGL_ORG0201:
			orgcode_field = "PROVCOMCODE";
			if (roleOrg.equals("101")) {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
			}
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0202:
			orgcode_field = "COUNTCOMCODE";
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0203:
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_field = "TEAMCOMCODE";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0301:
			orgcode_field = "COUNTCOMCODE";
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0302:
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_field = "TEAMCOMCODE";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0303:
			orgcode_condition = "HEADGROUPCODE ='" + roleOrg + "'";
			orgcode_field = "HEADGROUPCODE";
			orgcode_group_name = "PROVGROUPNAME";
			orgcode_group_code = "PROVGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0401:
			orgcode_field = "PROVCOMCODE";
			if (roleOrg.equals("101")) {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
			}
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0402:
			orgcode_field = "COUNTCOMCODE";
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.FXGL_ORG0403:
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_field = "TEAMCOMCODE";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	public String getBkfwBaseSql(String sqlCode, String roleOrg, Boolean isServer, Boolean hasOnlyThree,
			Boolean hasOnlyFour) {
		// 去除sql显示样式
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		if (sql.contains("{orgcode_field}")) {
			sql = sql.replace("{orgcode_field}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String teamcom_fields = "";
		switch (roleOrg.length()) {
		// 总公司
		case 1:
			orgcode_condition = "HEADCOMCODE = '1'";
			orgcode_group_name = "TEAMCOMSHORTNAME";
			orgcode_group_code = "PROVCOMCODE";
			break;
		// 分公司
		case 3:
			// 判断是否是北京
			if (roleOrg.equals("101")) {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<> '" + roleOrg + "'";
			}
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			break;
		// 中支
		case 5:
			if (hasOnlyThree) {
				orgcode_condition = "1=0";
				orgcode_group_name = "'1'";
				orgcode_group_code = "'0'";
			} else {
				if (isServer) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' ";
					teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' AND COST_CENTERSTDCODE IS NOT NULL ";
				}
			}
			break;
		// 营销服务部
		case 10:
			if (hasOnlyFour) {
				orgcode_condition = "1=0";
				orgcode_group_name = "'1'";
				orgcode_group_code = "'0'";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
			}
			break;
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
		sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
		sql = sql.replace("{teamcom_fields}", teamcom_fields);
		return sql;
	}

	/**
	 * @功能 {板块服务SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午11:22:28
	 */
	public String getBkfwSql(String sqlConstants, String sqlCode, String roleOrg, Boolean isServer) {
		// 去除sql显示样式
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		if (sql.contains("{orgcode_field}")) {
			sql = sql.replace("{orgcode_field}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String teamcom_fields = "";
		switch (sqlConstants.toString()) {
		case SqlConstants.BKFW_01:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				break;
			// 分公司
			case 3:
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			case 10:
				/*
				 * if (isServer) { orgcode_condition = "TEAMCOMCODE ='" +
				 * roleOrg + "'"; teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				 * } else { orgcode_condition = "COST_CENTERSTDCODE ='" +
				 * roleOrg + "'"; }
				 */
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			default:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{teamcom_fields}", teamcom_fields);
			break;
		case SqlConstants.BKFW_02:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				// 判断是否是北京
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<> '" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				if (isServer) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' ";
					teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' AND COST_CENTERSTDCODE IS NOT NULL ";
				}
				break;
			// 成本中心
			case 10:
				// if (isServer) {
				// orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				// teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				// } else {
				// orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				// }
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			default:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			sql = sql.replace("{teamcom_fields}", teamcom_fields);
			break;
		case SqlConstants.BKFW_03:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				break;
			// 分公司
			case 3:
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			case 10:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			// 营销服务部
			default:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			}
			orgcode_group_name = "'1'";
			orgcode_group_code = "'0'";
			sql = sql.replace("1=1", "1=0");
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			sql = sql.replace("{teamcom_fields}", teamcom_fields);
			break;
		case SqlConstants.BKFW_04:
			if (isServer) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";

			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	/**
	 * @功能 {板块指标下转SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月28日 上午9:36:50
	 */
	public String getBkfwDownSql(String sqlConstants, String sqlCode, String roleOrg, Boolean isServer) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 替换代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String orgcode_field = "";
		String teamcom_fields = "";
		switch (sqlConstants.toString()) {
		case SqlConstants.BKFW_DOWN01:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_field = "HEADCOMCODE";
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_field = "PROVCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				// 北京特殊处理
				if (roleOrg.equals("10101")) {
					if (isServer) {
						orgcode_group_name = "TEAMCOMNAME";
						orgcode_group_code = "TEAMCOMCODE";
						orgcode_condition = " 'BJ'='BJ' AND PROVCOMCODE ='101' ";
						teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
					} else {
						orgcode_group_name = "COST_CENTERDEVNAME";
						orgcode_group_code = "COST_CENTERSTDCODE";
						orgcode_condition = " 'BJ'='BJ' AND PROVCOMCODE ='101' AND COST_CENTERSTDCODE IS NOT NULL ";
					}
				} else {
					if (isServer) {
						orgcode_group_name = "TEAMCOMNAME";
						orgcode_group_code = "TEAMCOMCODE";
						orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' ";
						teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
					} else {
						orgcode_group_name = "COST_CENTERDEVNAME";
						orgcode_group_code = "COST_CENTERSTDCODE";
						orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'  AND COST_CENTERSTDCODE IS NOT NULL ";
					}
				}
				break;
			// 营销服务部
			case 10:
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				break;
			// 营销服务部
			default:
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{teamcom_fields}", teamcom_fields);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFW_DOWN02:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_field = "HEADCOMCODE";
				orgcode_condition = "HEADCOMCODE = '1'";
				break;
			// 分公司
			case 3:
				orgcode_field = "PROVCOMCODE";
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			case 10:
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				break;
			// 营销服务部
			default:
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				teamcom_fields = "TEAMCOMCODE, TEAMCOMNAME,";
				break;
			}
			orgcode_group_name = "'1'";
			orgcode_group_code = "'0'";
			sql = sql.replace("1=1", "1=0");
			sql = sql.replace("{teamcom_fields}", teamcom_fields);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFW_DOWN03:
			if (isServer) {
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_field = "COST_CENTERSTDCODE";
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFW_DOWN04:
			orgcode_condition = "1=0";
			orgcode_group_name = "'1'";
			orgcode_group_code = "'0'";
			sql = sql.replace("1=1", "1=0");
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	/**
	 * @功能 {板块分析SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getBkfxSql(String sqlConstants, String sql, String roleOrg, Boolean isServer) {
		// sql基础处理
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_field = "";
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		switch (sqlConstants.toString()) {
		case SqlConstants.BKFX_00101:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_field = "HEADCOMCODE";
				orgcode_condition = "HEADCOMCODE = '1'";
				break;
			// 分公司
			case 3:
				orgcode_field = "PROVCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			case 10:
				orgcode_field = "COST_CENTERSTDCODE";
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			break;
		case SqlConstants.BKFX_00102:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_field = "HEADCOMCODE";
				orgcode_condition = "HEADCOMCODE = '1'";
				break;
			// 分公司
			case 3:
				orgcode_field = "PROVCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			case 10:
				if (isServer) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			// 营销服务部
			default:
				if (isServer) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			break;
		case SqlConstants.BKFX_00201:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_field = "HEADCOMCODE";
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_field = "HEADCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				if (isServer) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			// 成本中心
			case 10:
				orgcode_field = "COST_CENTERSTDCODE";
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_00202:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_field = "HEADCOMCODE";
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_field = "HEADCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_field = "COUNTCOMCODE";
				// 北京特殊处理
				if (roleOrg.equals("10101")) {
					orgcode_condition = " 'BJ'='BJ' AND PROVCOMCODE = '101'";
				} else {
					orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
				}
				if (isServer) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			case 10:
				if (isServer) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			// 营销服务部
			default:
				if (isServer) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_00301:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_field = "HEADCOMCODE";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司参数
			case 3:
				orgcode_field = "PROVCOMCODE";
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支参数
			case 5:
				orgcode_field = "COUNTCOMCODE";
				// 北京特殊处理
				if (roleOrg.equals("10101")) {
					orgcode_condition = " 'BJ'='BJ' AND PROVCOMCODE = '101'";
				} else {
					orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
				}
				if (isServer) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
				}
				break;
			// 中支参数
			case 10:
				if (isServer) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			// 中支参数
			default:
				if (isServer) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_field = "COST_CENTERSTDCODE";
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_00401:
			if (isServer) {
				orgcode_field = "TEAMCOMCODE";
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_field = "COST_CENTERSTDCODE";
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_00501:
			orgcode_condition = "1=0";
			orgcode_group_name = "'1'";
			orgcode_group_code = "'0'";
			sql = sql.replace("1=1", "1=0");
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_00601:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_field = "HEADCOMCODE";
				break;
			// 分公司
			case 3:
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 营销服务部
			case 10:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			default:
				if (isServer) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_field = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			break;
		case SqlConstants.BKFX_00701:
			orgcode_field = "HEADGROUPCODE";
			orgcode_condition = "HEADGROUPCODE ='" + roleOrg + "'";
			orgcode_group_name = "PROVGROUPNAME";
			orgcode_group_code = "PROVGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_00801:
			orgcode_field = "PROVGROUPCODE";
			orgcode_condition = "PROVGROUPCODE ='" + roleOrg + "'";
			orgcode_group_name = "COUNTGROUPNAME";
			orgcode_group_code = "COUNTGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	/**
	 * @功能 {按机构搜索板块分析SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getBkfxSqlByOrg(String sqlConstants, String sqlCode, String roleOrg, Boolean isServer,
			String orgType) {
		// sql基础处理
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String orgcode_field = "";
		switch (sqlConstants.toString()) {
		case SqlConstants.BKFX_ORG0101:
			switch (orgType) {
			// 分公司
			case "7":
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_field = "PROVCOMCODE";
				break;
			// 中支
			case "6":
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				orgcode_field = "COUNTCOMCODE";
				break;
			// 四级机构
			default:
				if (orgType.equals("501")) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_field = "TEAMCOMCODE";
				}
				if (orgType.equals("502")) {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_field = "COST_CENTERSTDCODE";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_field}", orgcode_field);
			break;
		case SqlConstants.BKFX_ORG0201:
			orgcode_field = "PROVCOMCODE";
			if (roleOrg.equals("101")) {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
			}
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0202:
			orgcode_field = "COUNTCOMCODE";
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (isServer) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0203:
			if (isServer) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_field = "TEAMCOMCODE";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0301:
			orgcode_field = "COUNTCOMCODE";
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (isServer) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0302:
			if (isServer) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_field = "TEAMCOMCODE";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0303:
			orgcode_condition = "HEADGROUPCODE ='" + roleOrg + "'";
			orgcode_field = "HEADGROUPCODE";
			orgcode_group_name = "PROVGROUPNAME";
			orgcode_group_code = "PROVGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0401:
			orgcode_field = "PROVCOMCODE";
			if (roleOrg.equals("101")) {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
			}
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0402:
			orgcode_field = "COUNTCOMCODE";
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (isServer) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		case SqlConstants.BKFX_ORG0403:
			if (isServer) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_field = "TEAMCOMCODE";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				orgcode_field = "COST_CENTERSTDCODE";
			}
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			break;
		}
		return sql;
	}

	/**
	 * @功能 {浏览清单sql处理}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 下午5:02:01
	 */
	public String getLlqdSql(String sql, String orgType, List<String> orgCodeList, Boolean isServer) {
		// 去除sql显示样式
		String sqlcode = sql.replace("\r\n", " ");
		sqlcode = sqlcode.replace("\n", " ");
		sqlcode = sqlcode.replace("\t", " ");
		// 机构代码条件
		String orgcode_condition = "";
		String orgCode = orgCodeList.get(orgCodeList.size() - 1);
		switch (orgType) {
		// 总公司
		case "1":
			orgcode_condition = "HEADCOMCODE = '1'";
			sqlcode = sqlcode.replace("{orgcode_field}", "PROVCOMCODE");
			sqlcode = sqlcode.replace("{orgcode_group_code}", "PROVCOMCODE");
			break;
		// 分公司
		case "2":
			orgcode_condition = "PROVCOMCODE ='" + orgCode + "'";
			sqlcode = sqlcode.replace("{orgcode_field}", "COUNTCOMCODE");
			sqlcode = sqlcode.replace("{orgcode_group_code}", "COUNTCOMCODE");
			break;
		// 中支
		case "3":
			if (null != orgCodeList) {
				orgcode_condition = "PROVCOMCODE ='" + orgCodeList.get(0) + "' AND COUNTCOMCODE ='" + orgCodeList.get(1)
						+ "'";
			} else {
				orgcode_condition = "COUNTCOMCODE ='" + orgCode + "'";
			}
			if (isServer) {
				sqlcode = sqlcode.replace("{orgcode_field}", "TEAMCOMCODE");
				sqlcode = sqlcode.replace("{orgcode_group_code}", "TEAMCOMCODE");
			} else {
				sqlcode = sqlcode.replace("{orgcode_field}", "COST_CENTERSTDCODE");
				sqlcode = sqlcode.replace("{orgcode_group_code}", "COST_CENTERSTDCODE");
			}
			break;
		// 营销服务部或成本中心
		case "4":
			if (isServer) {
				if (null != orgCodeList) {
					orgcode_condition = "PROVCOMCODE ='" + orgCodeList.get(0) + "' AND COUNTCOMCODE ='"
							+ orgCodeList.get(1) + "' AND TEAMCOMCODE='" + orgCodeList.get(2) + "'";
				} else {
					orgcode_condition = "TEAMCOMCODE ='" + orgCode + "'";
				}
			} else {
				if (null != orgCodeList) {
					orgcode_condition = "PROVCOMCODE ='" + orgCodeList.get(0) + "' AND COUNTCOMCODE ='"
							+ orgCodeList.get(1) + "' AND COST_CENTERSTDCODE='" + orgCodeList.get(2) + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + orgCode + "'";
				}
			}
			sqlcode = sqlcode.replace("{orgcode_field}", "HEADGROUPCODE");
			sqlcode = sqlcode.replace("{orgcode_group_code}", "HEADGROUPCODE");
			break;
		// 区
		case "5":
			if (isServer) {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND TEAMCOMCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='" + orgCodeList.get(3)
						+ "'";
			} else {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND COST_CENTERSTDCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='"
						+ orgCodeList.get(3) + "'";
			}
			break;
		// 部
		case "6":
			if (isServer) {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND TEAMCOMCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='" + orgCodeList.get(3)
						+ "' AND PROVGROUPCODE='" + orgCodeList.get(4) + "'";
			} else {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND COST_CENTERSTDCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='"
						+ orgCodeList.get(3) + "' AND PROVGROUPCODE='" + orgCodeList.get(4) + "'";
			}
			break;
		// 组
		case "7":
			if (isServer) {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND TEAMCOMCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='" + orgCodeList.get(3)
						+ "' AND PROVGROUPCODE='" + orgCodeList.get(4) + "' AND COUNTGROUPCODE='" + orgCodeList.get(5)
						+ "'";

			} else {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND COST_CENTERSTDCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='"
						+ orgCodeList.get(3) + "' AND PROVGROUPCODE='" + orgCodeList.get(4) + "' AND COUNTGROUPCODE='"
						+ orgCodeList.get(5) + "'";
			}
			break;
		// 代理人
		case "8":
			if (isServer) {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND TEAMCOMCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='" + orgCodeList.get(3)
						+ "' AND PROVGROUPCODE='" + orgCodeList.get(4) + "' AND COUNTGROUPCODE='" + orgCodeList.get(5)
						+ "' AND AGENT_ID='" + orgCodeList.get(6) + "'";
			} else {
				orgcode_condition = "PROVCOMCODE='" + orgCodeList.get(0) + "' AND COUNTCOMCODE='" + orgCodeList.get(1)
						+ "' AND COST_CENTERSTDCODE='" + orgCodeList.get(2) + "' AND HEADGROUPCODE='"
						+ orgCodeList.get(3) + "' AND PROVGROUPCODE='" + orgCodeList.get(4) + "' AND COUNTGROUPCODE='"
						+ orgCodeList.get(5) + "' AND AGENT_ID='" + orgCodeList.get(6) + "'";
			}
		}
		sqlcode = sqlcode.replace("{orgcode_condition}", orgcode_condition);
		return sqlcode;
	}

	/**
	 * @功能 {预警sql处理}
	 * @时间 2017年9月1日 下午3:02:01
	 */
	public String getGjfwSql(String sql, String roleOrg) {
		// 去除sql显示样式
		String sqlcode = sql.replace("\r\n", " ");
		sqlcode = sqlcode.replace("\n", " ");
		sqlcode = sqlcode.replace("\t", " ");
		// 机构代码条件
		String orgcode_condition = "";
		// 机构代码条件
		String orgcode_field = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		switch (roleOrg.length()) {
		// 总公司
		case 1:
			orgcode_condition = "HEADCOMCODE = '1'";
			orgcode_field = "HEADCOMCODE";
			orgcode_group_name = "TEAMCOMSHORTNAME";
			orgcode_group_code = "PROVCOMCODE";
			break;
		// 分公司
		case 3:
			if (roleOrg.equals("101")) {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
			}
			orgcode_field = "PROVCOMCODE";
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			break;
		// 中支
		case 5:
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			orgcode_field = "COUNTCOMCODE";
			orgcode_group_name = "TEAMCOMNAME";
			orgcode_group_code = "TEAMCOMCODE";
			break;
		// 营销服务部
		case 10:
			orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			orgcode_field = "COST_CENTERSTDCODE";
			orgcode_group_name = "HEADGROUPNAME";
			orgcode_group_code = "HEADGROUPCODE";
			break;
		}
		sql = sql.replace("{orgcode_field}", orgcode_field);
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
		sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
		return sql;
	}

	/**
	 * @param roleOrg2
	 * @Describtion 获得达时报SQL
	 * @return 字符串
	 */
	public String getDashbordSql(String targetCode, String dept, TarRegSql tarRegSql, String roleOrg, int orgType,
			JSONObject channel, String riskCode, String regCode) {
		String sql = tarRegSql.getSqlCode();
		// 去除sql显示样式
		String sqlcode = sql.replace("\r\n", " ");
		sqlcode = sqlcode.replace("\n", " ");
		sqlcode = sqlcode.replace("\t", " ");
		// 机构代码条件
		String orgcode_field = "";
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		if (sql.contains("{orgcode_condition}")) {
			switch (orgType) {
			// 总公司
			case 8:
				orgcode_field = "PROVCOMCODE";
				orgcode_condition = " HEADCOMCODE = '1' AND PROVCOMCODE <> '1'";
				break;
			// 分公司
			case 7:
				orgcode_field = "COUNTCOMCODE";
				if ("101".equals(roleOrg)) {
					orgcode_condition = " PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = " PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE <> '" + roleOrg + "'";
				}
				break;
			// 中支
			case 6:
				if (commonUtil.isGeYeAndPeiXunDept(dept)) {
					orgcode_field = "TEAMCOMCODE";
					orgcode_condition = " COUNTCOMCODE ='" + roleOrg + "' AND TEAMCOMCODE <> '" + roleOrg + "'";
				} else {
					//机构
					orgcode_field = "COST_CENTERSTDCODE";
					if (("DAS0106G01").equals(regCode) || ("DAS0106005").equals(targetCode)) {
						orgcode_condition = "  COUNTCOMCODE ='" + roleOrg + "'  ";
					} else {
						orgcode_condition = " COUNTCOMCODE ='" + roleOrg + "' AND COST_CENTERSTDCODE <> '" + roleOrg
								+ "'";
					}
				}
				break;
			// 营销服务部
			// 总->分->中支->成本中心->销服务部-->区
			// HEADGROUPCODE 区代码
			// TEAMCOMCODE 销服务部代码
			// COUNTCOMCODE 中支代码
			// COST_CENTERSTDCODE 成本中心
			// COST_CENTERDEVNAME 成本中心名称
			case 5:
				orgcode_field = "HEADGROUPCODE";
				orgcode_condition = " COST_CENTERSTDCODE ='" + roleOrg + "'";
				/*
				 * if (commonUtil.isGeYeAndPeiXunDept(dept)) { orgcode_condition
				 * = " COST_CENTERSTDCODE ='" + roleOrg + "'"; } else {
				 * orgcode_condition = " TEAMCOMCODE ='" + roleOrg + "'"; }
				 */
				break;
			}
			sql = sql.replace("{orgcode_field}", orgcode_field);
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
		}
		if (sql.contains("{orgcode_group_name}")) {
			switch (orgType) {
			// 总公司
			case 8:
				orgcode_group_name = " TEAMCOMSHORTNAME";
				break;
			// 分公司
			case 7:
				orgcode_group_name = " ACTUTEAMCOMSHORTNAME";
				break;
			// 中支
			case 6:
				// 判断该指标是哪个部门的，如果是机构发展部和保费部，显示成本中心名称；如果是个业部和培训部，显示营销服务部名称
				if (dept.equals("130101") || dept.equals("130105")) {
					orgcode_group_name = " COST_CENTERDEVNAME";
				} else {
					orgcode_group_name = " TEAMCOMNAME";
				}
				break;
			// 营销服务部
			case 5:
				orgcode_group_name = " HEADGROUPNAME";
				break;
			}
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
		}
		if (sql.contains("{orgcode_group_code}")) {
			switch (orgType) {
			// 总公司
			case 8:
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 7:
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 6:
				// 判断该指标是哪个部门的，如果是机构发展部和保费部，显示成本中心名称；如果是个业部和培训部，显示营销服务部名称
				if (dept.equals("130101") || dept.equals("130105")) {
					orgcode_group_code = "COST_CENTERSTDCODE";
				} else {
					orgcode_group_code = " TEAMCOMCODE";
				}
				break;
			// 营销服务部
			case 5:
				orgcode_group_code = " HEADGROUPCODE";
				break;
			}
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
		}
		String aliasName = ""; // 别名
		String interName = "";
		if (StringUtil.isNotNull(channel)) {
			String channelCode = channel.getString("code");
			if (commonService.findTQDByChannelCode(channelCode)) {
				// 如果该指标有channelCode这个指标，进一步去匹配传入的值
				// 1. 先通过指标找到去渠道这个查询条件在业务表对应的字段
				Integer trsId = tarRegSql.getTrsId();
				List<SqlAlias> sas = commonService.findSqlAliasByTrsId(trsId);
				if (sas != null && sas.size() > 0) {
					aliasName = sas.get(0).getAliasName();
					interName = sas.get(0).getTableName();
					List<FieldInterBean> bean1List = commonService.findTIByTarChanCode(targetCode, channelCode,
							interName);
					String fieldCode = "";
					if (bean1List != null && bean1List.size() > 0) {
						fieldCode = bean1List.get(0).getFieldCode();
						if (StringUtil.isNotNull(aliasName)) {
							JSONArray lists = channel.getJSONArray("list");
							if (lists.size() > 0) {
								String aliStr = aliasName + "." + fieldCode + " IN (";
								for (Object list : lists) {
									String listStr = "";
									if (list.toString().contains("#")) {
										String[] str = list.toString().split("#");
										listStr = str[0] + "','" + str[1];
									} else {
										listStr = list.toString();
									}
									aliStr += "'" + listStr + "',";
								}
								aliStr = aliStr.substring(0, aliStr.length() - 1) + ") ";
								if (sql.contains("1=1")) {
									sql = sql.replace("1=1", aliStr);
								}
							}
						}
					}
				}
			}
		}
		if (sql.contains("{riskcode_condition}")) {
			if (StringUtil.isNotNull(riskCode) && !"0".equals(riskCode)) {
				sql = sql.replace("{riskcode_condition}", " A.RISK_CODE = " + riskCode);
			} else {
				sql = sql.replace("{riskcode_condition}", " 123 = 123 ");
			}
		}
		return sql;
	}

	/**
	 * @Describtion 替换组织字符串
	 * @param sql
	 * @param roleOrg
	 * @return
	 */
	public String getAppFxglSql(String sql, String roleDept, String roleOrg) {
		// 去除sql显示样式
		String sqlcode = sql.replace("\r\n", " ");
		sqlcode = sqlcode.replace("\n", " ");
		sqlcode = sqlcode.replace("\t", " ");
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		int len = roleOrg.length();

		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		if (sql.contains("{orgcode_condition}")) {
			switch (len) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				break;
			// 分公司
			case 3:
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				break;
			// 营销服务部
			default:
				if (commonUtil.isGeYeAndPeiXunDept(roleDept)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
		}
		if (sql.contains("{orgcode_group_name}")) {
			switch (len) {
			// 总公司
			case 1:
				orgcode_group_name = " TEAMCOMSHORTNAME";
				break;
			// 分公司
			case 3:
				orgcode_group_name = " ACTUTEAMCOMSHORTNAME";
				break;
			// 中支
			case 5:
				if (roleDept.equals("130101") || roleDept.equals("130105")) {
					orgcode_group_name = " COST_CENTERDEVNAME";
				} else {
					orgcode_group_name = " TEAMCOMNAME";
				}
				break;
			// 营销服务部
			default:
				orgcode_group_name = " HEADGROUPNAME";
				break;
			}
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
		}
		if (sql.contains("{orgcode_group_code}")) {
			switch (len) {
			// 总公司
			case 1:
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司
			case 3:
				orgcode_group_code = "COUNTCOMCODE";
				break;
			// 中支
			case 5:
				// 判断该指标是哪个部门的，如果是机构发展部和保费部，显示成本中心名称；如果是个业部和培训部，显示营销服务部名称
				if (roleDept.equals("130101") || roleDept.equals("130105")) {
					orgcode_group_code = "COST_CENTERSTDCODE";
				} else {
					orgcode_group_code = " TEAMCOMCODE";
				}
				break;
			}
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
		}
		return sql;
	}
}
