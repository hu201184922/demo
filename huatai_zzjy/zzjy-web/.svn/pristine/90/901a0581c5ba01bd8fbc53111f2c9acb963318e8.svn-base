package com.huatai.web.job.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.sql.CommonSqlService;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.thrift.service.CommonService;

/**
 * @功能 {sql参数处理器}
 * @作者 MaxBill
 * @时间 2017年9月15日 上午9:39:42
 */
@Component
public class RedisSqlParameter {

	private CommonUtil commonUtil;
	private CommonService commonService;
	private CommonSqlService commonSqlService;

	public RedisSqlParameter() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		commonService = SpringUtil.getBean(CommonService.class);
		commonSqlService = SpringUtil.getBean(CommonSqlService.class);
	}

	/**
	 * @状态
	 * @功能 {分析管理SQL参数处理}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 * @备注 2=2对应在目标表HT_RISK_TARGET_TB
	 */
	public String fxglRedisSqlParam(String sqlcode, String targetCode, Integer trsId, String queryDimCode,
			List<String> queryDimValList) {
		// 查询表别名
		List<SqlAlias> sqlAliasList = commonService.findSqlAliasByTrsId(trsId);
		if (null != sqlAliasList && !sqlAliasList.isEmpty()) {
			for (SqlAlias sqlAlias : sqlAliasList) {
				switch (sqlAlias.getTableName()) {
				// 目标表2=2情况处理
				case "HT_RISK_TARGET_TB":
					sqlcode = sqlcode.replace("2 = 2", "2=2");
					if (sqlcode.contains("2=2")) {
						String[] sqlArray02 = sqlcode.split("2=2");
						String tempSql02 = "GRADETYPECODE IN(";
						for (String param02 : queryDimValList) {
							if (param02.contains("#")) {
								String[] param01Array = param02.split("#");
								String tempParm = "";
								for (String parm : param01Array) {
									tempParm = tempParm + "'" + parm + "', ";
								}
								tempSql02 = tempSql02 + tempParm;
							} else {
								tempSql02 = tempSql02 + "'" + param02 + "', ";
							}
						}
						if (!tempSql02.equals("GRADETYPECODE IN(")) {
							tempSql02 = " 2=2 AND " + tempSql02.substring(0, tempSql02.length() - 2) + ") ";
							sqlcode = sqlArray02[0] + tempSql02 + sqlArray02[1];
						} else {
							sqlcode = sqlArray02[0] + " 2=2 " + sqlArray02[1];
						}
					}
					break;
				default:
					sqlcode = sqlcode.replace("1 = 1", "1=1");
					if (sqlcode.contains("1=1")) {
						String tempSql01 = "";
						String[] sqlArray01 = sqlcode.split("1=1");
						// 查询接口表
						Inter inter01 = commonService.findInterByTableName(sqlAlias.getTableName(), queryDimCode);
						// 1.判断维度是否存在
						if (commonSqlService.fxglHasQuery(targetCode, queryDimCode)) {
							// 2.查询维度的表字段
							InterField interField01 = commonService.findInterFieldByInterAndDim(inter01.getInterId(),
									queryDimCode);
							// 3.拼装查询维度参数
							tempSql01 = tempSql01 + sqlAlias.getAliasName() + "." + interField01.getFieldCode()
									+ " IN(";
							for (String param01 : queryDimValList) {
								if (param01.contains("#")) {
									String[] param01Array = param01.split("#");
									String tempParm = "";
									for (String parm : param01Array) {
										tempParm = tempParm + "'" + parm + "', ";
									}
									tempSql01 = tempSql01 + tempParm;
								} else {
									tempSql01 = tempSql01 + "'" + param01 + "', ";
								}
							}
						}
						if (!tempSql01.equals("")) {
							tempSql01 = " 1=1 AND " + tempSql01.substring(0, tempSql01.length() - 2) + ") ";
						} else {
							tempSql01 = " 1=1 ";
						}
						sqlcode = sqlArray01[0] + tempSql01 + sqlArray01[1];
					}
					break;
				}
			}
		}
		return sqlcode;
	}

	/**
	 * @状态
	 * @功能 {达时报SQL参数处理}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 */
	public String zdsbRedisSqlParam(String sqlcode, String targetCode, Integer trsId, String queryDimCode,
			List<String> queryDimValList) {
		sqlcode = sqlcode.replace("1 = 1", "1=1");
		if (sqlcode.contains("1=1")) {
			String[] sqlArray01 = sqlcode.split("1=1");
			String tempSql01 = "GRADETYPECODE IN(";
			for (String param01 : queryDimValList) {
				if (param01.contains("#")) {
					String[] param01Array = param01.split("#");
					String tempParm = "";
					for (String parm : param01Array) {
						tempParm = tempParm + "'" + parm + "', ";
					}
					tempSql01 = tempSql01 + tempParm;
				} else {
					tempSql01 = tempSql01 + "'" + param01 + "', ";
				}
			}
			if (!tempSql01.equals("GRADETYPECODE IN(")) {
				tempSql01 = " 1=1 AND " + tempSql01.substring(0, tempSql01.length() - 2) + ") ";
				sqlcode = sqlArray01[0] + tempSql01 + sqlArray01[1];
			} else {
				sqlcode = sqlArray01[0] + " 1=1 " + sqlArray01[1];
			}
		}
		sqlcode = sqlcode.replace("2 = 2", "2=2");
		if (sqlcode.contains("2=2")) {
			String[] sqlArray02 = sqlcode.split("2=2");
			String tempSql02 = "GRADETYPECODE IN(";
			for (String param02 : queryDimValList) {
				if (param02.contains("#")) {
					String[] param01Array = param02.split("#");
					String tempParm = "";
					for (String parm : param01Array) {
						tempParm = tempParm + "'" + parm + "', ";
					}
					tempSql02 = tempSql02 + tempParm;
				} else {
					tempSql02 = tempSql02 + "'" + param02 + "', ";
				}
			}
			if (!tempSql02.equals("GRADETYPECODE IN(")) {
				tempSql02 = " 2=2 AND " + tempSql02.substring(0, tempSql02.length() - 2) + ") ";
				sqlcode = sqlArray02[0] + tempSql02 + sqlArray02[1];
			} else {
				sqlcode = sqlArray02[0] + " 2=2 " + sqlArray02[1];
			}
		}
		return sqlcode;
	}

	/**
	 * @状态
	 * @功能 {分析管理导出列表SQL参数处理}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 * @备注 2=2对应在目标表HT_RISK_TARGET_TB
	 */
	public String dcqdRedisSqlParam(String sqlcode, String targetCode, Integer trsId, String queryDimCode,
			List<String> queryDimValList) {
		String finalSqlcode = "";
		// 查询表别名
		List<SqlAlias> sqlAliasList = commonService.findSqlAliasByTrsId(trsId);
		if (null != sqlAliasList && !sqlAliasList.isEmpty()) {
			for (SqlAlias sqlAlias : sqlAliasList) {
				switch (sqlAlias.getTableName()) {
				// 目标表2=2情况处理
				case "HT_RISK_TARGET_TB":
					sqlcode = sqlcode.replace("2 = 2", "2=2");
					if (sqlcode.contains("2=2")) {
						String[] sqlArray02 = sqlcode.split("2=2");
						String tempSql02 = "GRADETYPECODE IN(";
						for (String param02 : queryDimValList) {
							if (param02.contains("#")) {
								String[] param01Array = param02.split("#");
								String tempParm = "";
								for (String parm : param01Array) {
									tempParm = tempParm + "'" + parm + "', ";
								}
								tempSql02 = tempSql02 + tempParm;
							} else {
								tempSql02 = tempSql02 + "'" + param02 + "', ";
							}
						}
						if (!tempSql02.equals("GRADETYPECODE IN(")) {
							tempSql02 = " 2=2 AND " + tempSql02.substring(0, tempSql02.length() - 2) + ") ";
							finalSqlcode = sqlArray02[0] + tempSql02 + sqlArray02[1];
						} else {
							finalSqlcode = sqlArray02[0] + " 2=2 " + sqlArray02[1];
						}
					}
					break;
				default:
					sqlcode = sqlcode.replace("1 = 1", "1=1");
					if (sqlcode.contains("1=1")) {
						String tempSql01 = "";
						String[] sqlArray01 = sqlcode.split("1=1");
						// 查询接口表
						Inter inter01 = commonService.findInterByTableName(sqlAlias.getTableName(), queryDimCode);
						// 1.判断维度是否存在
						if (commonSqlService.fxglHasQuery(targetCode, queryDimCode)) {
							// 2.查询维度的表字段
							InterField interField01 = commonService.findInterFieldByInterAndDim(inter01.getInterId(),
									queryDimCode);
							// 3.拼装查询维度参数
							tempSql01 = tempSql01 + sqlAlias.getAliasName() + "." + interField01.getFieldCode()
									+ " IN(";
							for (String param01 : queryDimValList) {
								if (param01.contains("#")) {
									String[] param01Array = param01.split("#");
									String tempParm = "";
									for (String parm : param01Array) {
										tempParm = tempParm + "'" + parm + "', ";
									}
									tempSql01 = tempSql01 + tempParm;
								} else {
									tempSql01 = tempSql01 + "'" + param01 + "', ";
								}
							}
						}
						if (!tempSql01.equals("")) {
							tempSql01 = " 1=1 AND " + tempSql01.substring(0, tempSql01.length() - 2) + ") ";
						} else {
							tempSql01 = " 1=1 ";
						}
						for (int i = 0; i < sqlArray01.length; i++) {
							if (i == sqlArray01.length - 1) {
								finalSqlcode += sqlArray01[i];
							} else {
								finalSqlcode += (sqlArray01[i] + tempSql01);
							}
						}
					}
					break;
				}
			}
		}
		return finalSqlcode;
	}

	/**
	 * @状态
	 * @功能 {分析管理排名SQL参数处理:四级机构时}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 */
	public String rankSqlParamWithFourOrg(String sqlcode, String targetCode, String orgCode, String deptCode,
			String queryDimCode, List<String> queryDimValList) {
		String finalSqlcode = "";
		if (orgCode.length() > 5 && commonUtil.isGeYeAndPeiXunDept(deptCode)) {
			if (sqlcode.contains("WHERE") && sqlcode.contains("D_ORGANIZATION")
					&& targetCode.substring(0, 3).equals("T01")) {
				String[] sqlArray = sqlcode.split("WHERE");
				String tempSql = " AND CHANNELFLAG IN(";
				if (commonSqlService.fxglHasQuery(targetCode, queryDimCode)) {
					if (queryDimCode.equals("BFDA_CHANNEL_TYPE")) {
						for (String param : queryDimValList) {
							if (param.contains("#")) {
								String[] param01Array = param.split("#");
								String tempParm = "";
								for (String parm : param01Array) {
									tempParm = tempParm + "'" + parm + "', ";
								}
								tempSql = tempSql + tempParm;
							} else {
								tempSql = tempSql + "'" + param + "', ";
							}
						}
					}
				}
				if (!tempSql.equals(" AND CHANNELFLAG IN(")) {
					tempSql = tempSql.substring(0, tempSql.length() - 2) + ") WHERE ";
				} else {
					tempSql = " WHERE ";
				}
				for (int i = 0; i < sqlArray.length; i++) {
					if (i == sqlArray.length - 1) {
						finalSqlcode += sqlArray[i];
					} else {
						finalSqlcode += (sqlArray[i] + tempSql);
					}
				}
			}
		}
		return finalSqlcode;
	}
}
