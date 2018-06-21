package com.ehuatai.biz.index.dashbord.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.index.dashbord.service.DSBCacheService;
import com.ehuatai.biz.index.dashbord.service.DSBService;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.DashbordService;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.DashbordService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.DateUtils;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Service
public class DSBServiceImpl implements DSBService {

	@Autowired
	private DSBCacheService dsbSQLCacheService;
	
	protected static String sql02 = "SELECT XYZ.RCODE AS CODE,B.RISKABBR AS NAME   FROM ("
			+ "  SELECT XY.RCODE,XY.VAL FROM ( SELECT "
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
	
	protected static String sql03 = "SELECT X.RISK_CODE AS CODE, B.RISKABBR AS NAME"
			+ "  FROM (SELECT A.RISK_CODE, DECODE(NVL(SUM(A.LIFE_COV_NATURAL_NUM), 0), 0, 0,"
			+ "          NVL(SUM(A.LIFE_COV_STAD_PREM), 0) /"
			+ "                SUM(A.LIFE_COV_NATURAL_NUM)) AS ACTIVE_LV,"
			+ "                ROW_NUMBER() OVER(ORDER BY DECODE(NVL(SUM(A.LIFE_COV_NATURAL_NUM), 0), 0, 0, "
			+ "                    NVL(SUM(A.LIFE_COV_STAD_PREM), 0) / SUM(A.LIFE_COV_NATURAL_NUM)) DESC) RN"
			+ "			FROM HT_CAPACITY_DAY_TB A" + "      LEFT JOIN D_ORGANIZATION D ON A.GROUP_ID = D.AGENTGROUPID"
			+ "     WHERE 1 = 1" + "       AND {orgcode_condition}"
			+ "       AND A.DATE_CODE = TO_CHAR(SYSDATE - 1, 'YYYY-MM-DD')" + "      GROUP BY A.RISK_CODE) X"
			+ "		LEFT JOIN D_RISK B ON X.RISK_CODE = B.RISKCODE" + "		WHERE X.RN <= 3";

	private Logger log = LoggerFactory.getLogger(getClass());

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(DashbordService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public RestResult DSBshortCut(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getShortCut(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}

		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {

			log.warn("结果为空");

			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parseObject(resultJSONString));
	}

	@Override
	public RestResult DSBanalysis(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONObject resultMap = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getDashAnalysis(reqParamsJSON);
			String resultJSONString = dto.getJson();
			if (StringUtils.isEmpty(resultJSONString)) {
				log.warn("结果为空");
				return RestResult.getError(RestCode.EMPTY);
			}
			resultMap = JSONObject.parseObject(resultJSONString);
			JSONArray modules = resultMap.getJSONArray("modules");
			for (int i = 0; i < modules.size(); i++) {
				JSONObject tarObj = modules.getJSONObject(i);
				if ("DAS0103".equals(tarObj.getString("code"))) {
					JSONArray target = tarObj.getJSONArray("target");
					for (int j = 0; j < target.size(); j++) {
						JSONObject sobj = target.getJSONObject(j);
						String tarC = sobj.getString("code");
						String sql = "";
						switch (tarC) {
						case "DAS0103G02":
							sql = sql02;
							break;
						case "DAS0103G03":
							sql = sql03;
							break;
						}
						String orgcode_condition = "";
						if (sql.contains("{orgcode_condition}")) {
							String roleOrg = reqParams.get("roleOrg").toString();
							int len = roleOrg.length();
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
								orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
								break;
							}
							sql = sql.replace("{orgcode_condition}", orgcode_condition);
						}
						if (!"".equals(sql)) {
							reqParams.put("subCode", tarC);
							List<Map<String, Object>> vals = dsbSQLCacheService.selectTopThreeSQL(reqParams, sql);
							if (null != vals && !vals.isEmpty()) {
								List<Map<String, Object>> top3 = new ArrayList<>();
								for (int x = 0; x < vals.size(); x++) {
									if (StringUtil.isNotNull(vals.get(x))) {
										Map<String, Object> star = new HashMap<>();
										String code = vals.get(x).get("CODE").toString();
										String name = vals.get(x).get("NAME").toString();
										if (StringUtil.isNotNull(code) && StringUtil.isNotNull(name)) {
											star.put("code", code);
											star.put("name", name);
											star.put("checked", false);
										}
										top3.add(star);
									}
								}
								sobj.put("top3", top3);
							}
						}
					}
				}
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		resultMap.put("endTime",DateUtils.getYesterDay());
		return RestResult.getSuccess(resultMap);
	}

	@Override
	public RestResult DSBanalysisPrem(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONObject resultMap;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getPremiumDetail(reqParamsJSON);
			if (dto.getSuccess().equals("true") && !"".equals(dto.getJson())) {
				// 获取json格式
				resultMap = JSON.parseObject(dto.getJson());
				// premium
				JSONArray premList = resultMap.getJSONArray("premium");

				if (premList.size() > 0) {
					Map<String, Object> premium = new HashMap<>();
					// 转换
					JSONObject pOne = premList.getJSONObject(0);
					List<String> xAxis = new ArrayList<>();
					// series
					premium.put("name", pOne.getString("title"));
					premium.put("stacking", "");

					List<Map<String, Object>> series = new ArrayList<>();
					// 获得每个节点下的值
					for (int i = 0; i < premList.size(); i++) {
						List<Double> data = new ArrayList<>();
						Map<String, Object> ser = new HashMap<>();
						JSONObject prem = premList.getJSONObject(i);
						reqParams.put("targetCode", prem.getString("targetCode"));
						String sqlCode = prem.getString("sqlCode");
						System.err.println(prem.getString("targetCode") + ":" + sqlCode);
						if (StringUtil.isNotNull(sqlCode)) {
							List<Map<String, Object>> vals = dsbSQLCacheService.selectPremPicSQL(reqParams, sqlCode);
							if (null != vals && !vals.isEmpty()) {
								for (int j = 0; j < vals.size(); j++) {
									Object x_val = vals.get(j).get("X_VAL");
//									Object p_val = vals.get(j).get("P_VAL");
									Object y_val = vals.get(j).get("Y_VAL");
									// 取第一条数据月份集合
									if (i == 0) {
										xAxis.add(x_val.toString());
									}
									String yalStr = y_val.toString();
									if (!StringUtils.isEmpty(yalStr)) {
										yalStr = yalStr.trim();
										data.add(Double.parseDouble(yalStr));
									} else {
										data.add(0.00);
									}
								}
							}
						}
						String type = prem.getString("type");
						String name = prem.getString("targetName");
						String code = prem.getString("targetCode");
						String unit = prem.getString("unit");
						String color = prem.getString("color");
						ser.put("type", type);
						ser.put("name", name);
						ser.put("code", code);
						ser.put("unit", unit);
						ser.put("stack", "");
						ser.put("color", color);
						ser.put("data", data);
						series.add(ser);
					}
					premium.put("xAxis", xAxis);
					premium.put("series", series);
					premList.clear();
					premList.add(premium);
					resultMap.replace("premium", premList.get(0));
				} else {
					resultMap.replace("premium", JSONObject.toJSON(new HashMap<>()));
				}
				// agent
				JSONArray agentList = resultMap.getJSONArray("agent");
				if (agentList.size() > 0) {
					Map<String, Object> agent = new HashMap<>();
					// 转换
					JSONObject pOneA = agentList.getJSONObject(0);
					List<String> xAxisA = new ArrayList<>();
					// series
					agent.put("name", pOneA.getString("title"));
					agent.put("stacking", "");

					List<Map<String, Object>> seriesA = new ArrayList<>();
					// 获得每个节点下的值
					for (int i = 0; i < agentList.size(); i++) {
						List<Double> dataA = new ArrayList<>();
						Map<String, Object> serA = new HashMap<>();
						JSONObject agen = agentList.getJSONObject(i);
						reqParams.put("targetCode", agen.getString("targetCode"));
						String sqlCode = agen.getString("sqlCode");
						System.err.println(agen.getString("targetCode") + ":" + sqlCode);
						if (StringUtil.isNotNull(sqlCode)) {
							List<Map<String, Object>> vals = dsbSQLCacheService.selectPremPicSQL(reqParams, sqlCode);
							if (null != vals && !vals.isEmpty()) {
								for (int j = 0; j < vals.size(); j++) {
									Object x_val = vals.get(j).get("X_VAL");
//									Object p_val = vals.get(j).get("P_VAL");
									Object y_val = vals.get(j).get("Y_VAL");
									// 取第一条数据月份集合
									if (i == 0) {
										xAxisA.add(x_val.toString());
									}
									String yalStr = y_val.toString();
									if (!StringUtils.isEmpty(yalStr)) {
										yalStr = yalStr.trim();
										dataA.add(Double.parseDouble(yalStr));
									} else {
										dataA.add(0.00);
									}
									// dataA.add(Double.parseDouble(yalStr));
								}
							}
						}
						String type = agen.getString("type");
						String name = agen.getString("targetName");
						String code = agen.getString("targetCode");
						String unit = agen.getString("unit");
						String color = agen.getString("color");
						serA.put("type", type);
						serA.put("name", name);
						serA.put("code", code);
						serA.put("unit", unit);
						serA.put("stack", "");
						serA.put("color", color);
						serA.put("data", dataA);
						seriesA.add(serA);
					}
					agent.put("xAxis", xAxisA);
					agent.put("series", seriesA);
					agentList.clear();
					agentList.add(agent);
					resultMap.replace("agent", agentList.get(0));
				} else {
					resultMap.replace("agent", JSONObject.toJSON(new HashMap<>()));
				}

				// renewal
				JSONArray renewalList = resultMap.getJSONArray("renewal");
				if (renewalList.size() > 0) {
					Map<String, Object> renewal = new HashMap<>();
					// 转换
					JSONObject pOneB = renewalList.getJSONObject(0);
					List<String> xAxisB = new ArrayList<>();
					// series
					renewal.put("name", pOneB.getString("title"));
					renewal.put("stacking", "");

					List<Map<String, Object>> seriesB = new ArrayList<>();
					// 获得每个节点下的值
					for (int i = 0; i < renewalList.size(); i++) {
						List<Double> dataB = new ArrayList<>();
						Map<String, Object> serB = new HashMap<>();
						JSONObject rene = renewalList.getJSONObject(i);
						reqParams.put("targetCode", rene.getString("targetCode"));
						String sqlCode = rene.getString("sqlCode");
						System.err.println(rene.getString("targetCode") + ":" + sqlCode);
						if (StringUtil.isNotNull(sqlCode)) {
							List<Map<String, Object>> vals = dsbSQLCacheService.selectPremPicSQL(reqParams, sqlCode);
							if (null != vals && !vals.isEmpty()) {
								for (int j = 0; j < vals.size(); j++) {
									Object x_val = vals.get(j).get("X_VAL");
//									Object p_val = vals.get(j).get("P_VAL");
									Object y_val = vals.get(j).get("Y_VAL");
									// 取第一条数据月份集合
									if (i == 0) {
										xAxisB.add(x_val.toString());
									}
									String yalStr = y_val.toString();
									if (!StringUtils.isEmpty(yalStr)) {
										yalStr = yalStr.trim();
										dataB.add(Double.parseDouble(yalStr));
									} else {
										dataB.add(0.00);
									}
								}
							}
						}
						String type = rene.getString("type");
						String name = rene.getString("targetName");
						String code = rene.getString("targetCode");
						String unit = rene.getString("unit");
						String color = rene.getString("color");
						serB.put("type", type);
						serB.put("name", name);
						serB.put("code", code);
						serB.put("unit", unit);
						serB.put("stack", "");
						serB.put("color", color);
						serB.put("data", dataB);
						seriesB.add(serB);
					}
					renewal.put("xAxis", xAxisB);
					renewal.put("series", seriesB);
					renewalList.clear();
					renewalList.add(renewal);
					resultMap.replace("renewal", renewalList.get(0));
				} else {
					resultMap.replace("renewal", JSONObject.toJSON(new HashMap<>()));
				}

				// rank
				JSONArray rankList = resultMap.getJSONArray("rank");
				if (null != rankList) {
					JSONObject rank = rankList.getJSONObject(0);
					String sqlCode = rank.getString("sqlCode");
					List<Map<String, Object>> rankL = new ArrayList<>();
					if (StringUtil.isNotNull(sqlCode)) {
						List<Map<String, Object>> vals = dsbSQLCacheService.selectPremRankSQL(reqParams, sqlCode);
						if (null != vals && !vals.isEmpty()) {
							for (Map<String, Object> val : vals) {
								Map<String, Object> record = new HashMap<>();
								String num = val.get("VAL0").toString();
								String orgName = val.get("VAL1").toString();
								String present = val.get("VAL2").toString();
								record.put("num", num);
								record.put("orgName", orgName);
								record.put("present", present);
								rankL.add(record);
							}
						}
					}
					resultMap.replace("rank", rankL);
				}
			} else {
				return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(resultMap)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(resultMap);
	}

	@Override
	public RestResult DSBanalysisOrther(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONObject resultMap;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getOrtherTarget(reqParamsJSON);
			if (dto.getSuccess().equals("true") && !"".equals(dto.getJson())) {
				// 获取json格式
				resultMap = JSON.parseObject(dto.getJson());
				// premium
				JSONArray targets = resultMap.getJSONArray("targets");
				String riskCode = resultMap.getString("riskCode");
				if (targets.size() > 0) {
					if(reqParams.get("org").toString().length()>5){
						JSONObject targetCodeNo = targets.getJSONObject(0);
						if(targetCodeNo.get("targetCode").equals("DAS0106005")){
							targets.remove(0);
						}
						if(reqParams.get("target").toString().equals("DAS0106G01")){
							targets.clear();
						}
					}
					if(targets.size()==0){
						return RestResult.getSuccess(null);
					}
					boolean isProSelect = false; // 判断是否是含有前三名的指标
					String targetCode = ""; // 含有前三名产品的指标
					Map<String, Object> tar = new HashMap<>();
					// 转换
					JSONObject pOneB = targets.getJSONObject(0);
					List<String> xAxisB = new ArrayList<>();
					// series
					tar.put("name", pOneB.getString("title"));
					tar.put("stacking", "");

					List<Map<String, Object>> seriesB = new ArrayList<>();
					// 获得每个节点下的值
					for (int i = 0; i < targets.size(); i++) {
						List<Object> dataB = new ArrayList<>();
						Map<String, Object> serB = new HashMap<>();
						JSONObject rene = targets.getJSONObject(i);
						reqParams.put("targetCode", rene.getString("targetCode"));
						String sqlCode = rene.getString("sqlCode");
						String type = rene.getString("type");
						if (StringUtil.isNotNull(sqlCode)) {
							String subject = reqParams.get("subject").toString();
							List<Map<String, Object>> vals = new ArrayList<>();
							switch (subject) {
							case "DAS0102":
								vals = dsbSQLCacheService.selectOrtherPeoSQL(reqParams, sqlCode);
								break;
							case "DAS0103":
								vals = dsbSQLCacheService.selectOrtherProSQL(reqParams, sqlCode);
								break;
							case "DAS0104":
								vals = dsbSQLCacheService.selectOrtherGoodSQL(reqParams, sqlCode);
								break;
							case "DAS0105":
								vals = dsbSQLCacheService.selectOrtherRateSQL(reqParams, sqlCode);
								break;
							case "DAS0106":
								vals = dsbSQLCacheService.selectOrtherDeptSQL(reqParams, sqlCode);
								break;
							case "DAS0107":
								vals = dsbSQLCacheService.selectOrtherLeadSQL(reqParams, sqlCode);
								break;
							case "DAS0108":
								vals = dsbSQLCacheService.selectOrtherK2SQL(reqParams, sqlCode);
								break;
							}
							if (null != vals && !vals.isEmpty()) {
								for (int j = 0; j < vals.size(); j++) {
									Object x_val = vals.get(j).get("X_VAL");
//									Object p_val = vals.get(j).get("P_VAL");
									Object y_val = vals.get(j).get("Y_VAL");
									if ("pie".equals(type)) {
										List<Object> pieVal = new ArrayList<>();
										pieVal.add(x_val.toString());
										String yalStr = y_val.toString();
										if (!StringUtils.isEmpty(yalStr)) {
											yalStr = yalStr.trim();
											pieVal.add(Double.parseDouble(yalStr));
										} else {
											pieVal.add(0.00);
										}
										dataB.add(pieVal);
									} else {
										// 取第一条数据月份集合
										if (i == 0) {
											xAxisB.add(x_val.toString());
										}
										String yalStr = y_val.toString();
										if (!StringUtils.isEmpty(yalStr)) {
											yalStr = yalStr.trim();
											dataB.add(Double.parseDouble(yalStr));
										} else {
											dataB.add(0.00);
										}
									}
								}
							}
						}
						String name = rene.getString("targetName");
						String code = rene.getString("targetCode");
						if (("DAS0103002".equalsIgnoreCase(code) || "DAS0103003".equalsIgnoreCase(code))
								&& StringUtil.isNotNull(riskCode)) {
							isProSelect = true;
							targetCode = code;
						}
						String unit = rene.getString("unit");
						String color = rene.getString("color");
						serB.put("type", type);
						serB.put("name", name);
						serB.put("code", code);
						serB.put("unit", unit);
						serB.put("stack", "");
						serB.put("color", color);
						serB.put("data", dataB);
						seriesB.add(serB);
					}
					tar.put("xAxis", xAxisB);
					tar.put("series", seriesB);
					targets.clear();
					targets.add(tar);
					resultMap.replace("targets", tar);
					if (isProSelect) {
						// 配置前三名产品
						List<Map<String, Object>> productList = new ArrayList<>();
						String sqlCode = "";
						switch (targetCode) {
						case "DAS0103002":
							sqlCode = sql02;
							break;
						case "DAS0103003":
							sqlCode = sql03;
							break;
						}
						String orgcode_condition = "";
						if (sqlCode.contains("{orgcode_condition}")) {
							String roleOrg = reqParams.get("roleOrg").toString();
							int len = roleOrg.length();
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
								orgcode_condition = "TEAMCOMCODE ='" + roleOrg + "'";
								break;
							}
							sqlCode = sqlCode.replace("{orgcode_condition}", orgcode_condition);
						}
						List<Map<String, Object>> vals = dsbSQLCacheService.selectTopThreeDetail(reqParams, sqlCode);
						if (null != vals && !vals.isEmpty()) {
							for (Map<String, Object> val : vals) {
								if (StringUtil.isNotNull(val)) {
									Map<String, Object> product = new HashMap<>();
									product.put("name", val.get("NAME"));
									product.put("code", val.get("CODE"));
									if (StringUtil.isNotNull(riskCode) && riskCode.equals(val.get("CODE"))) {
										product.put("checked", true);
									} else {
										product.put("checked", false);
									}
									productList.add(product);
								}
							}
						}
						resultMap.put("product", productList);
					}
				} else {
					resultMap.replace("targets", JSONObject.toJSON(new HashMap<>()));
					resultMap.replace("product", JSONObject.toJSON(new HashMap<>()));
				}
			} else {
				return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(resultMap);
	}
}
