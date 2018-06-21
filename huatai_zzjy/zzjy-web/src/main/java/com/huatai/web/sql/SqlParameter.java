package com.huatai.web.sql;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.thrift.service.CommonService;

/**
 * @功能 {sql参数处理器}
 * @作者 MaxBill
 * @时间 2017年9月15日 上午9:39:42
 */
@Component
public class SqlParameter {

	private CommonUtil commonUtil;
	private CommonService commonService;
	private CommonSqlService commonSqlService;

	public SqlParameter() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
		commonService = SpringUtil.getBean(CommonService.class);
		commonSqlService = SpringUtil.getBean(CommonSqlService.class);
	}

	Logger log = Logger.getLogger(SqlParameter.class);

	/**
	 * @状态
	 * @功能 {分析管理SQL参数处理}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 */
	public String fxglSqlParam(String sqlcode, String targetCode, Integer trsId, String filters) {
		// 筛选维度不为空
		if (null != filters && !filters.equals("")) {
			// 解析筛选参数集
			JSONArray filterArray = JSON.parseArray(filters);
			if (!filterArray.isEmpty()) {
				List<JSONObject> filterList = JSON.parseArray(filterArray.toString(), JSONObject.class);
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
								for (JSONObject filterJSONObject02 : filterList) {
									// 解析筛选维度编码
									String queryDimCode02 = filterJSONObject02.getString("group");
									if (queryDimCode02.equals("CHANNEL_TYPE")) {
										String queryDimVals02 = filterJSONObject02.getString("value");
										List<String> queryDimValList02 = JSON.parseArray(queryDimVals02, String.class);
										for (String param02 : queryDimValList02) {
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
								// 分隔sql语句
								String[] sqlArray01 = sqlcode.split("1=1");
								// 查询表别名
								for (JSONObject filterJSONObject01 : filterList) {
									// 1.解析筛选维度编码
									String queryDimCode01 = filterJSONObject01.getString("group");
									// 查询接口表
									Inter inter01 = commonService.findInterByTableName(sqlAlias.getTableName(),
											queryDimCode01);
									// 2.判断维度是否存在
									if (commonSqlService.fxglHasQuery(targetCode, queryDimCode01)) {
										// 3.解析筛选维度的值
										String queryDimVals01 = filterJSONObject01.getString("value");
										List<String> queryDimValList01 = JSON.parseArray(queryDimVals01, String.class);
										// 4.查询维度的表字段
										InterField interField01 = commonService
												.findInterFieldByInterAndDim(inter01.getInterId(), queryDimCode01);
										// 5.拼装查询维度参数
										tempSql01 = tempSql01 + sqlAlias.getAliasName() + "."
												+ interField01.getFieldCode() + " IN(";
										for (String param01 : queryDimValList01) {
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
										tempSql01 = tempSql01.substring(0, tempSql01.length() - 2) + ") AND ";
									}
								}
								if (!tempSql01.equals("")) {
									tempSql01 = " 1=1 AND " + tempSql01.substring(0, tempSql01.length() - 5);
								} else {
									tempSql01 = " 1=1 ";
								}
								sqlcode = "";
								for (int i = 0; i < sqlArray01.length; i++) {
									if (i == sqlArray01.length - 1) {
										sqlcode += sqlArray01[i];
									} else {
										sqlcode += (sqlArray01[i] + tempSql01);
									}
								}
							}
							break;
						}
					}
				}
			}
		}
		return sqlcode;
	}

	/**
	 * @状态
	 * @功能 {分析管理SQL参数处理：只有分析管理和板块分析四级机构时才使用}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 */
	public String rankSqlParam(String sqlcode, String targetCode, String filters, String deptCode) {
		String finalSqlcode = "";
		if (commonUtil.isGeYeAndPeiXunDept(deptCode)) {
			// 筛选维度不为空
			if (null != filters && !filters.equals("")) {
				// 解析筛选参数集
				JSONArray filterArray = JSON.parseArray(filters);
				if (!filterArray.isEmpty()) {
					List<JSONObject> filterList = JSON.parseArray(filterArray.toString(), JSONObject.class);
					if (sqlcode.contains("WHERE") && sqlcode.contains("D_ORGANIZATION")
							&& targetCode.substring(0, 3).equals("T01")) {
						String[] sqlArray = sqlcode.split("WHERE");
						String tempSql = " AND CHANNELFLAG IN(";
						for (JSONObject filterJSONObject : filterList) {
							// 解析筛选维度编码
							String queryDimCode = filterJSONObject.getString("group");
							if (commonSqlService.fxglHasQuery(targetCode, queryDimCode)) {
								if (queryDimCode.equals("BFDA_CHANNEL_TYPE")) {
									String queryDimVals = filterJSONObject.getString("value");
									List<String> queryDimValList = JSON.parseArray(queryDimVals, String.class);
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
			}
		}
		if (finalSqlcode.equals("")) {
			return sqlcode;
		} else {
			return finalSqlcode;
		}
	}

	/**
	 * @状态
	 * @功能 {固定指标SQL参数处理}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 */
	public String gdzbCoreSqlParam(String sqlcode, String targetCode, String orgCode, String deptCode) {
		if (orgCode.length() == 5 && commonUtil.isGeYeAndPeiXunDept(deptCode)) {
			if (sqlcode.contains("WHERE") && sqlcode.contains("D_ORGANIZATION")
					&& targetCode.substring(0, 3).equals("T01")) {
				String[] sqlArray = sqlcode.split("WHERE");
				String tempSql = " AND CHANNELFLAG IN('1','5')";
				sqlcode = sqlArray[0] + tempSql + " WHERE " + sqlArray[1];
			}
		}
		return sqlcode;
	}
	
	/**
	 * 实时指标SQL替换
	 * @return String
	 */
	public String getSszbSqlByRole(String sqlCode, String roleOrg) {
		String sql = sqlCode.replace("\r\n", " ");
		sql = sql.replace("\n", " ");
		sql = sql.replace("\t", " ");
		
		String org_table_link = " ";
		String rt_org_condition = " ";
		String org_group_name = " ";
		String org_group_code = " ";
		String org_table_group_link = " ";
		String rt_org_group_condition = " ";
		String org_query = " ";
	
		switch (roleOrg.length()) {
		// 总公司
		case 1:
			if (sql.contains("{org_table_link}")) {
				org_table_link = " ";
			}
			
			if (sql.contains("{rt_org_condition}")) {
				rt_org_condition = " ";
			} 
			
			if (sql.contains("{org_group_name}")) {
				org_group_name = " PROVCOMNAME ";
			} 
			
			if (sql.contains("{org_group_code}")) {
				org_group_code = " PROVCOMCODE ";
			}

			if (sql.contains("{org_table_group_link}")) {
				org_table_group_link = " LEFT JOIN ( SELECT X1.ABBR_NAME PROVCOMNAME, X1.ORGAN_ID PROVCOMCODE,  "
						+ "  X2.ABBR_NAME COUNTCOMNAME, X2.ORGAN_ID COUNTCOMCODE FROM ( SELECT ABBR_NAME, ORGAN_ID FROM ODATA.OD_COMPANY_ORGAN O  "
						+ "  WHERE STATUS = '1' AND CLASS_ID = '2' AND ORGAN_ID <> '100' ) X1 LEFT JOIN ( SELECT ABBR_NAME, ORGAN_ID,PARENT_ID  "
						+ "  FROM ODATA.OD_COMPANY_ORGAN O WHERE STATUS = '1' AND CLASS_ID = '3' UNION ALL SELECT '北京中支','101','101' ) X2 ON "
						+ "  X1.ORGAN_ID=X2.PARENT_ID ) D ON A.MANAGECOMCODE =D.COUNTCOMCODE ";
			}

			if (sql.contains("{rt_org_group_condition}")) {
				rt_org_group_condition = " AND D.PROVCOMCODE IS NOT NULL ";
			}
			
			if (sql.contains("{org_query}")) {
				org_query = "  SELECT O.ABBR_NAME AS NAME, O.ORGAN_ID AS CODE FROM ODATA.OD_COMPANY_ORGAN O "
						+ "  WHERE O.STATUS = '1' AND  O.CLASS_ID = '2' AND O.ORGAN_ID <> '100' ";
			}
			break;
		// 分公司
		case 3:
			if (sql.contains("{org_table_link}")) {
				org_table_link = " ";
			}
			
			if (sql.contains("{rt_org_condition}")) {
				rt_org_condition = " AND A.MANAGECOMCODE LIKE '" + roleOrg + "%' ";
			} 
			
			if (sql.contains("{org_group_name}")) {
				org_group_name = " COUNTCOMNAME ";
			} 
			
			if (sql.contains("{org_group_code}")) {
				org_group_code = " COUNTCOMCODE ";
			}

			if (sql.contains("{org_table_group_link}")) {
				org_table_group_link = " LEFT JOIN ( SELECT X2.ABBR_NAME COUNTCOMNAME, X2.ORGAN_ID COUNTCOMCODE " + 
						"	FROM ( SELECT ABBR_NAME, ORGAN_ID,PARENT_ID FROM ODATA.OD_COMPANY_ORGAN O WHERE STATUS = '1' AND CLASS_ID = '3' " + 
						"   UNION ALL SELECT '北京中支','101','101' ) X2 ) D ON A.MANAGECOMCODE=D.COUNTCOMCODE ";
			}
			if (sql.contains("{rt_org_group_condition}")) {
				rt_org_group_condition = " AND D.COUNTCOMCODE IS NOT NULL AND A.MANAGECOMCODE LIKE '"
						+ roleOrg + "%' ";
			}
			
			if (sql.contains("{org_query}")) {
				if ("101".equals(roleOrg)) {
					// AND D.DEPT_CATE='1'
					org_query = " SELECT DISTINCT D.DEPT_NAME AS NAME, D.DEPT_ID AS CODE "
							+ "    FROM ODATA.OD_DEPT D INNER JOIN ODATA.OD_DEPT D1 ON D.DEPT_ID = D1.BUSI_DEPT_ID "
							+ "  WHERE D.STATUS ='1' AND D.ORGAN_ID = '101'  ";
				} else {
					org_query = " SELECT O.ABBR_NAME AS NAME, O.ORGAN_ID AS CODE FROM ODATA.OD_COMPANY_ORGAN O WHERE O.STATUS = '1' AND O.CLASS_ID = '3' "
							+ " AND O.PARENT_ID = '" + roleOrg + "'  ";
				}
			}
			break;
		// 中支
		case 5:
			if (sql.contains("{org_table_link}")) {
				org_table_link = " ";
			}
			
			if (sql.contains("{rt_org_condition}")) {
				rt_org_condition = " AND A.MANAGECOMCODE = '" + roleOrg + "' ";
			} 
			
			if (sql.contains("{org_group_name}")) {
				org_group_name = " TEAMCOMNAME ";
			} 
			
			if (sql.contains("{org_group_code}")) {
				org_group_code = " TEAMCOMCODE ";
			}
			
			if (sql.contains("{org_table_group_link}")) {
				org_table_group_link = " LEFT JOIN (SELECT DISTINCT TEAMCOMNAME,TEAMCOMCODE,AGENTGROUPCODE FROM PDATA.PD_DEPT_TMP) D ON A.AGENTGROUPCODE = D.AGENTGROUPCODE ";
			}
			
			if (sql.contains("{org_query}")) {
				// AND D.DEPT_CATE='1'
				org_query = " SELECT DISTINCT D.DEPT_NAME AS NAME, D.DEPT_ID AS CODE  "
						+ "    FROM ODATA.OD_DEPT D INNER JOIN ODATA.OD_DEPT D1 ON D.DEPT_ID = D1.BUSI_DEPT_ID  "
						+ "  WHERE D.STATUS ='1' AND D.ORGAN_ID = '" + roleOrg + "'  ";
			}
			if(sql.contains("{rt_org_group_condition}")) {
				//AND D.TEAMCOMCODE IS NOT NULL AND A.MANAGECOMCODE = '10403'
				rt_org_group_condition = "  AND D.TEAMCOMCODE IS NOT NULL AND A.MANAGECOMCODE = '" + roleOrg + "'  ";
			}
			break;
		// 营销服务部
		case 10:
			if (sql.contains("{org_table_link}")) {
				org_table_link = "  LEFT JOIN ( " + 
						"   SELECT DISTINCT TEAMCOMCODE,AGENTGROUPCODE FROM PDATA.PD_DEPT_TMP " + 
						" ) D ON A.AGENTGROUPCODE = D.AGENTGROUPCODE ";
			}
			
			if (sql.contains("{rt_org_condition}")) {
				rt_org_condition = " AND D.TEAMCOMCODE in ( "
						+ " SELECT DISTINCT D.DEPT_ID AS TEAMCOMCODE " + 
						"  FROM ODATA.OD_DEPT D " + 
						" INNER JOIN ODATA.OD_DEPT D1 " + 
						"    ON D.DEPT_ID = D1.BUSI_DEPT_ID " + 
						"    WHERE D.STATUS ='1' " + 
						"    AND D.COST_CENTER='" + roleOrg + "') ";
			} 
			
			if (sql.contains("{org_group_name}")) {
				org_group_name = " HEADGROUPNAME ";
			} 
			
			if (sql.contains("{org_group_code}")) {
				org_group_code = " HEADGROUPCODE ";
			}
			
			if (sql.contains("{org_table_group_link}")) {
				org_table_group_link = "  LEFT JOIN ( SELECT DISTINCT HEADGROUPNAME,HEADGROUPCODE,AGENTGROUPCODE FROM PDATA.PD_DEPT_TMP ) D ON A.AGENTGROUPCODE = D.AGENTGROUPCODE ";
			}
			
			if (sql.contains("{rt_org_group_condition}")) {
				rt_org_group_condition = " AND D.HEADGROUPCODE IS NOT NULL AND D.TEAMCOMCODE in ( "
						+ " SELECT DISTINCT D.DEPT_ID AS TEAMCOMCODE " + 
						"  FROM ODATA.OD_DEPT D " + 
						" INNER JOIN ODATA.OD_DEPT D1 " + 
						"    ON D.DEPT_ID = D1.BUSI_DEPT_ID " + 
						"    WHERE D.STATUS ='1' " + 
						"    AND D.COST_CENTER='" + roleOrg + "') ";
			}
			break;
		}
		sql = sql.replace("{org_table_link}", org_table_link);
		sql = sql.replace("{rt_org_condition}", rt_org_condition);
		sql = sql.replace("{org_group_name}", org_group_name);
		sql = sql.replace("{org_group_code}", org_group_code);
		sql = sql.replace("{org_table_group_link}", org_table_group_link);
		sql = sql.replace("{rt_org_group_condition}", rt_org_group_condition);
		sql = sql.replace("{org_query}", org_query);
		return sql;
	}
}
