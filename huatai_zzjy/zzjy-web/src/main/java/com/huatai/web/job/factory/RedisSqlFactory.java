package com.huatai.web.job.factory;

import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.sql.CommonUtil;

/**
 * @功能 {SQL通用处理工厂}
 * @作者 MaxBill
 * @时间 2017年8月21日 上午10:45:52
 */
@Component
public class RedisSqlFactory {

	private CommonUtil commonUtil;

	public RedisSqlFactory() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * @功能 {分析管理SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getGdzbSql(String sqlConstants, TarRegSql tarRegSql, String roleOrg, String deptCode, String group) {
		// sql基础处理
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		// 替换代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		switch (sqlConstants.toString()) {
		case "":
			break;
		case RedisSqlConstants.GDZB_01:
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
			sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
			sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
			break;
		case RedisSqlConstants.GDZB_02:
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
				switch (roleOrg.length()) {
				case 1:
					orgcode_condition = "HEADCOMCODE = '1'";
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
				default:
					if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
						orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					} else {
						orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					}
					break;
				}
				sql = sql.replace("{orgcode_condition}", orgcode_condition);
				break;
			}
			break;
		case RedisSqlConstants.GDZB_03:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				break;
			// 分公司
			case 3:
				orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
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
		}
		return sql;
	}

	/**
	 * @功能 {达时报SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getDsbSql(TarRegSql tarRegSql, String roleOrg, String deptCode) {
		// 去除sql显示样式
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		// 机构代码条件
		String orgcode_field = "";
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		switch (roleOrg.length()) {
		// 总公司
		case 1:
			orgcode_condition = "HEADCOMCODE = '1'";
			orgcode_group_name = "TEAMCOMSHORTNAME";
			orgcode_group_code = "PROVCOMCODE";
			orgcode_field = "PROVCOMCODE";
			break;
		// 分公司
		case 3:
			orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
			orgcode_group_name = "ACTUTEAMCOMSHORTNAME";
			orgcode_group_code = "COUNTCOMCODE";
			orgcode_field = "COUNTCOMCODE";
			break;
		// 中支
		case 5:
			orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_group_name = "TEAMCOMNAME";
				orgcode_group_code = "TEAMCOMCODE";
				orgcode_field = "COUNTCOMCODE";
			} else {
				orgcode_group_name = "COST_CENTERDEVNAME";
				orgcode_group_code = "COST_CENTERSTDCODE";
				orgcode_field = "COST_CENTERSTDCODE";
			}
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
			orgcode_field = "HEADGROUPCODE";
			break;
		}
		sql = sql.replace("{orgcode_field}", orgcode_field);
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		sql = sql.replace("{orgcode_group_name}", orgcode_group_name);
		sql = sql.replace("{orgcode_group_code}", orgcode_group_code);
		return sql;
	}

	/**
	 * @功能 {分析管理SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getZtfxSql(String sqlConstants, String tarRegSql, String roleOrg, String deptCode) {
		// sql基础处理
		String sql = tarRegSql.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		if (sql.contains("{org_code}")) {
			sql = sql.replace("{org_code}", "'" + roleOrg + "'");
		}
		// 机构代码条件
		String orgcode_condition = "";
		String orgcode_group_name = "";
		String orgcode_group_code = "";
		String orgcode_feild = "";
		switch (sqlConstants.toString()) {
		case RedisSqlConstants.FXGL_01:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_feild = "HEADCOMCODE";
				break;
			// 分公司
			case 3:
				if (roleOrg.equals("101")) {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "PROVCOMCODE ='" + roleOrg + "' AND COUNTCOMCODE<>'" + roleOrg + "'";
				}
				orgcode_feild = "PROVCOMCODE";
				break;
			// 中支
			case 5:
				orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "'";
				orgcode_feild = "COUNTCOMCODE";
				break;
			// 营销服务部
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_feild = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_feild = "COST_CENTERSTDCODE";
				}
				break;
			// 营销服务部
			default:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
					orgcode_feild = "TEAMCOMCODE";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
					orgcode_feild = "COST_CENTERSTDCODE";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			sql = sql.replace("{orgcode_field}", orgcode_feild);
			break;
		case RedisSqlConstants.FXGL_02:
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
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
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
		case RedisSqlConstants.FXGL_03:
			switch (roleOrg.length()) {
			// 总公司
			case 1:
				orgcode_condition = "HEADCOMCODE = '1'";
				orgcode_group_name = "TEAMCOMSHORTNAME";
				orgcode_group_code = "PROVCOMCODE";
				break;
			// 分公司参数
			case 3:
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
				orgcode_condition = "COUNTCOMCODE = '" + roleOrg + "'";
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
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
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
		}
		return sql;
	}

	/**
	 * @功能 {预警监控sql处理}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:33:03
	 */
	public String getYjjkSql(String sql, String roleOrg, String deptCode) {
		// 去除sql显示样式
		String sqlcode = sql.replace("\r\n", " ");
		sqlcode = sqlcode.replace("\n", " ");
		sqlcode = sqlcode.replace("\t", " ");
		// 机构代码条件
		String orgcode_condition = "";
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
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			}
			break;
		default:
			if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
			} else {
				orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
			}
			break;
		}
		sql = sql.replace("{orgcode_condition}", orgcode_condition);
		return sql;
	}

	/**
	 * @功能 {达时报SQL处理}
	 * @作者 MaxBill
	 * @时间 2017年8月21日 上午10:40:26
	 */
	public String getJyfxSql(TarRegSql tarRegSql, String roleOrg, String deptCode) {
		// 去除sql显示样式
		String sql = tarRegSql.getSqlCode().replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		// 机构代码条件
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
		return sql;
	}

	/**
	 * @功能 {板块服务sql处理}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:33:03
	 */
	public String getBkfwSql(String sqlConstants, String sqlCode, String roleOrg, String deptCode) {
		// sql基础处理
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
		switch (sqlConstants.toString()) {
		case RedisSqlConstants.BKFW_01:
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
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			// 营销服务部
			default:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				break;
			}
			sql = sql.replace("{orgcode_condition}", orgcode_condition);
			break;
		case RedisSqlConstants.BKFW_02:
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
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_group_name = "TEAMCOMNAME";
					orgcode_group_code = "TEAMCOMCODE";
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' ";
				} else {
					orgcode_group_name = "COST_CENTERDEVNAME";
					orgcode_group_code = "COST_CENTERSTDCODE";
					orgcode_condition = "COUNTCOMCODE ='" + roleOrg + "' AND COST_CENTERSTDCODE IS NOT NULL ";
				}
				break;
			// 营销服务部
			case 10:
				if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
					orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				} else {
					orgcode_condition = "COST_CENTERSTDCODE ='" + roleOrg + "'";
				}
				orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
				orgcode_group_name = "HEADGROUPNAME";
				orgcode_group_code = "HEADGROUPCODE";
				break;
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
		}
		return sql;
	}

	/**
	 * @功能 {达时报产品前三名替换规则}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:33:03
	 */
	public String replaceProduct(String sql, String riskCode) {
		if (sql.contains("{riskcode_condition}")) {
			if (StringUtil.isNotNull(riskCode)) {
				sql = sql.replace("{riskcode_condition}", " A.RISK_CODE = " + riskCode);
			} else {
				sql = sql.replace("{riskcode_condition}", " 123 = 123 ");
			}
		}
		return sql;
	}

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
