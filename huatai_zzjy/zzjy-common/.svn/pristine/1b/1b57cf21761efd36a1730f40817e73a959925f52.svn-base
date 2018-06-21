package com.ehuatai.app.analysis.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.app.analysis.service.AppAnalysisSQLCacheService;
import com.ehuatai.app.analysis.service.AppAnalysisService;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.conts.Consts;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.AppZtfxService;
import com.ehuatai.thrift.AppZtfxService.Client;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.DateUtil;
import com.ehuatai.util.DateUtils;

@Service
public class AppAnalysisServiceImpl implements AppAnalysisService {

	@Autowired
	private Module module;

	@Autowired
	private AppAnalysisSQLCacheService sqlCacheService;

	@Autowired
	private BaseMapper baseMapper;

	private Logger log = LoggerFactory.getLogger(getClass());

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(AppZtfxService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	/**
	 * 
	 * 主题、板块菜单
	 */
	@Override
	public RestResult appAnalysisNavs(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getMenu(reqParamsJSON);
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

	/**
	 * 
	 * 指标页面接口
	 */
	@Override
	public RestResult appAnalysisTarget(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		List<Object> list = new ArrayList<>();
		HashMap<String, Object> resultMap = new HashMap<>();
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getPage(reqParamsJSON);
			String resultJSONString = dto.getJson();
			JSONObject resultObject = JSONObject.parseObject(resultJSONString);
			// 一级指标
			JSONArray oneTar = resultObject.getJSONArray("oneTargetBean");
			if (null != oneTar) {
				for (int i = 0; i < oneTar.size(); i++) {
					JSONObject obj = oneTar.getJSONObject(i);
					String sql = obj.getString("sql");
					System.out.println(obj.getString("name") + obj.getString("code"));
					reqParams.put("targetCode", obj.getString("code"));
					if (null != sql && "" != sql) {
						List<Map<String, Object>> lists = sqlCacheService.appAnalysisTargetSQL(reqParams, sql);
						Object value = (lists == null || lists.isEmpty()) ? "" : lists.get(0).get("VAL");
						obj.put("sql", value);
					} else {
						obj.put("sql", null);
					}

				}
			}

			// 二级指标
			JSONArray twoTar = resultObject.getJSONArray("twoTargetList");
			if (null != twoTar) {
				for (int i = 0; i < twoTar.size(); i++) {
					JSONObject obj = twoTar.getJSONObject(i);
					JSONArray twoTargets = obj.getJSONArray("target");
					if (null != twoTargets) {
						for (int j = 0; j < twoTargets.size(); j++) {
							JSONObject twoTarget = twoTargets.getJSONObject(j);
							String sql = twoTarget.getString("sql");
							System.out.println(twoTarget.getString("name") + twoTarget.getString("code") + sql);
							reqParams.put("targetCode", twoTarget.getString("code"));
							if (null != sql && "" != sql) {
								List<Map<String, Object>> lists = sqlCacheService.appAnalysisTargetSQL(reqParams, sql);
								Object value = (lists == null || lists.isEmpty()) ? "" : lists.get(0).get("VAL");
								twoTarget.put("sql", value);
							} else {
								twoTarget.put("sql", null);
							}

						}
					}
				}
			}
			// 特殊指标
			JSONArray specialTar = resultObject.getJSONArray("specialTaregt");
			// 查询时间指标
			JSONArray timeReqs = resultObject.getJSONArray("timeReqs");
			list.add(oneTar);
			list.add(twoTar);
			list.add(specialTar);
			resultMap.put("timeReqs", timeReqs);
			resultMap.put("TargetList", list);
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

	/**
	 * 
	 * 一级指标趋势图
	 */
	@Override
	public RestResult appAnalysisTargetMain(Map<String, Object> reqParams) {
		Client client = this.getClient();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			// 获取选中的一级指标
			List<String> checkedTargets = JSON.parseArray(JSONArray.toJSONString(reqParams.get("target1")),
					String.class);
			if (checkedTargets == null || checkedTargets.isEmpty()) {
				return RestResult.getError(RestCode.EMPTY);
			}
			List<Object> series = new ArrayList<>();

			for (int i = 0; i < checkedTargets.size(); i++) {
				String targetCode = checkedTargets.get(i).toString();
				reqParams.put("targetCode", targetCode);
				reqParamsJSON = JSON.toJSONString(reqParams);
				dto = client.getMainData(reqParamsJSON);
				JSONArray jsArr = JSONArray.parseArray(dto.getJson());
				if (null != jsArr && !jsArr.isEmpty()) {
					JSONObject obj = JSONObject.parseObject(jsArr.get(0).toString());
					// 获取日期分组依据
					String groupbyDate = reqParams.get("groupbyDate").toString();
					Map<String, Object> map = new HashMap<>();
					map.put("type", obj.getString("type"));
					map.put("name", obj.getString("name"));
					map.put("code", obj.getString("code"));
					map.put("unit", obj.getString("unit"));
					map.put("stack", obj.getString("stack"));
					map.put("color", obj.getString("color"));
					String sql = obj.getString("sql");
					if (null != sql) {
						if (i == 0) {
							resultMap.put("stacking", "");
							resultMap.put("xAxis", null);
							resultMap.put("name", obj.getString("title"));
						}
						List<Map<String, Object>> lists = sqlCacheService.appAnalysisTargetMainSQL(reqParams, sql);
						List<List<Object>> data = getTrendTargetData(lists, groupbyDate);
						data = dealTargetTimeData(groupbyDate, data);
						map.put("data", data);
						series.add(map);
						resultMap.put("series", series);
					} else {
						continue;
					}
				}
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

	/**
	 * 
	 * 二级指标趋势图
	 */
	@Override
	public RestResult appAnalysisTargetSub(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			// 获取选中的二级指标
			List<String> checkedTargets = JSON.parseArray(JSONArray.toJSONString(reqParams.get("target2")),
					String.class);
			if (checkedTargets == null || checkedTargets.isEmpty()) {

				return RestResult.getError(RestCode.EMPTY);
			}

			for (String checkedTarget : checkedTargets) {
				String targetCode = checkedTarget.toString();
				reqParams.put("targetCode", targetCode);
				reqParamsJSON = JSON.toJSONString(reqParams);
				dto = client.getSubData(reqParamsJSON);
				// 获取日期分组依据
				String groupbyDate = reqParams.get("groupbyDate").toString();
				JSONArray jsArr = JSONArray.parseArray(dto.getJson());
				if (jsArr.size() == 0) {
					log.warn("结果为空");
					return RestResult.getError(RestCode.EMPTY);
				}
				JSONObject obj = JSONObject.parseObject(jsArr.get(0).toString());
				Map<String, Object> resultMap = new HashMap<>();
				Map<String, Object> map = new HashMap<>();
				map.put("type", obj.getString("type"));
				map.put("name", obj.getString("name"));
				map.put("code", obj.getString("code"));
				map.put("unit", obj.getString("unit"));
				map.put("stack", obj.getString("stack"));
				map.put("color", obj.getString("color"));
				String sql = obj.getString("sql");
				if (null != sql) {
					resultMap.put("name", obj.getString("title"));
					resultMap.put("stacking", "");
					resultMap.put("xAxis", null);
					List<Map<String, Object>> lists = sqlCacheService.appAnalysisTargetSubSQL(reqParams, sql);
					List<List<Object>> data = getTrendTargetData(lists, groupbyDate);
					data = dealTargetTimeData(groupbyDate, data);
					map.put("data", data);
					List<Object> series = new ArrayList<>();
					series.add(map);
					resultMap.put("series", series);
				} else {
					continue;
				}

				resultList.add(resultMap);
			}

		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(resultList)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(resultList);
	}

	/**
	 * 
	 * 特殊指标趋势图
	 */
	@Override
	public RestResult appAnalysisTargetSpec(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			// 获取选中的特级指标
			List<String> checkedTargets = JSON.parseArray(JSONArray.toJSONString(reqParams.get("target")),
					String.class);
			if (checkedTargets == null || checkedTargets.isEmpty()) {

				return RestResult.getError(RestCode.EMPTY);
			}
			for (String checkedTarget : checkedTargets) {
				String targetCode = checkedTarget.toString();
				reqParams.put("target", targetCode);
				reqParamsJSON = JSON.toJSONString(reqParams);
				dto = client.getSpecData(reqParamsJSON);
				String resultJSONString = dto.getJson();
				JSONArray jsArr = JSONArray.parseArray(resultJSONString);
				if (jsArr.size() == 0) {
					log.warn("结果为空");
					return RestResult.getError(RestCode.EMPTY);
				}
				JSONObject obj = JSONObject.parseObject(jsArr.get(0).toString());
				for (Object oneTar : jsArr) {
					Map<String, Object> resultMap = new HashMap<>();
					JSONObject target = JSON.parseObject(oneTar.toString());
					Map<String, Object> map = new HashMap<>();
					map.put("type", target.getString("type"));
					map.put("name", target.getString("name"));
					map.put("code", target.getString("code"));
					map.put("unit", target.getString("unit"));
					map.put("stack", target.getString("stack"));
					map.put("color", target.getString("color"));
					String sql = target.getString("sql");
					if (null != sql) {
						resultMap.put("name", obj.getString("title"));
						resultMap.put("stacking", "");
						resultMap.put("xAxis", null);
						List<Map<String, Object>> lists = sqlCacheService.appAnalysisTargetSpecSQL(reqParams, sql);
						Object object = handleSpecSQLData(lists, target.getString("type"));
						map.put("data", object);
						List<Object> series = new ArrayList<>();
						series.add(map);
						resultMap.put("series", series);
					} else {
						continue;
					}
					resultList.add(resultMap);
				}
			}

		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(resultList)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(resultList);
	}

	/**
	 * 
	 * 实时指标趋势图
	 */
	@Override
	public RestResult appAnalysisTargetRealtime(Map<String, Object> reqParams) {

		return null;
	}

	/**
	 * 获取指标数据
	 * 
	 * @param results
	 * @return
	 */
	private List<List<Object>> getTrendTargetData(List<Map<String, Object>> results, String groupbyDate) {
		List<List<Object>> data = new ArrayList<List<Object>>();
		if (results == null || results.isEmpty())
			return data;
		String pattern = "yyyy-MM-dd HH";
		String lowerGroupbyDate = groupbyDate.toLowerCase();
		if (lowerGroupbyDate.equals("day")) {
			pattern = "yyyy-MM-dd";
		} else if (lowerGroupbyDate.equals("month")) {
			pattern = "yyyy-MM";
		} else {
			pattern = "yyyy-MM-dd HH";
		}
		for (Map<String, Object> map : results) {
			Object yAxis = map.get("Y_VAL");
			String xAxis = map.get("X_VAL").toString();
			if (lowerGroupbyDate.equals("year")) {
				xAxis = xAxis + "-01-01 00";
			}
			List<Object> objList = new ArrayList<Object>();
			Long xAxisTime = DateUtil.getTime(xAxis, pattern);
			objList.add(xAxisTime);
			objList.add(Double.parseDouble(yAxis.toString()));
			data.add(objList);
		}
		return data;
	}

	/**
	 * @功能 {一级二级指标的时间处理}
	 * @作者 MaxBill
	 * @时间 2017年9月25日 下午1:20:22
	 */
	public List<List<Object>> dealTargetTimeData(String groupbyDate, List<List<Object>> dataList) {
		List<List<Object>> tempDataList = null;
		try {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			switch (groupbyDate) {
			case "DAY":
				tempDataList = new ArrayList<List<Object>>();
				SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, -1);
				String currDay = formatDay.format(calendar.getTime());
				String lateDay = year - 1 + "-01-01";
				List<String> dateDayList = DateUtils.getDateBetweenByDay(lateDay, currDay);
				if (null != dataList) {
					for (String dataStr : dateDayList) {
						boolean dayFlag = false;
						List<Object> objListTemp = null;
						Long time = DateUtil.getTime(dataStr, "yyyy-MM-dd");
						for (List<Object> objList : dataList) {
							if (objList.get(0).equals(time)) {
								dayFlag = true;
								objListTemp = objList;
							}
						}
						if (dayFlag) {
							tempDataList.add(objListTemp);
						} else {
							List<Object> tempList = new ArrayList<Object>();
							tempList.add(time);
							tempList.add(0);
							tempDataList.add(tempList);
						}
					}
				} else {
					for (String dataStr : dateDayList) {
						Long time = DateUtil.getTime(dataStr, "yyyy-MM-dd");
						List<Object> tempList = new ArrayList<Object>();
						tempList.add(time);
						tempList.add(0);
						tempDataList.add(tempList);
					}
				}
				break;
			case "MONTH":
				tempDataList = new ArrayList<List<Object>>();
				if (day == 1) {
					month = month - 1;
				}
				List<String> dateMonthList = DateUtils.getDateBetweenByMonth(year - 1 + "-01", year + "-0" + month);
				if (null != dataList && dataList.size() != 0) {
					for (String dataStr : dateMonthList) {
						Long time = DateUtil.getTime(dataStr, "yyyy-MM");
						boolean monthFlag = false;
						List<Object> objListTemp = null;
						for (List<Object> objList : dataList) {
							if (objList.get(0).equals(time)) {
								monthFlag = true;
								objListTemp = objList;
							}
						}
						if (monthFlag) {
							tempDataList.add(objListTemp);
						} else {
							List<Object> tempList = new ArrayList<Object>();
							tempList.add(time);
							tempList.add(0);
							tempDataList.add(tempList);
						}
					}
				}/* else {
					for (String dataStr : dateMonthList) {
						Long time = DateUtil.getTime(dataStr, "yyyy-MM");
						List<Object> tempList = new ArrayList<Object>();
						tempList.add(time);
						tempList.add(0);
						tempDataList.add(tempList);
					}
				}*/
				break;
			case "YEAR":
				tempDataList = dataList;
				break;
			}
			dataList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempDataList;
	}

	/**
	 * 处理特殊区域的数据
	 * 
	 * @param lists
	 * @param serie
	 * @return
	 */
	private Object handleSpecSQLData(List<Map<String, Object>> lists, String type) {

		List<Object> data = new ArrayList<Object>();
		// 根据图形类型处理
		type = type.toLowerCase();// scatter pie
		if (type.equals(Consts.SCATTER)) {
			// 散点图
			for (Map<String, Object> item : lists) {
				String name = item.get("P_VAL").toString();
				String x = item.get("X_VAL").toString();
				String y = item.get("Y_VAL").toString();

				Map<String, Object> itemMap = new HashMap<String, Object>();

				itemMap.put("name", name);
				itemMap.put("x", Double.parseDouble(x));
				itemMap.put("y", Double.parseDouble(y));

				data.add(itemMap);
			}
		} else if (type.equals(Consts.PIE)) {
			// 饼图
			for (Map<String, Object> item : lists) {
				String x = item.get("X_VAL").toString();
				String y = item.get("Y_VAL").toString();

				Object itemObj = new Object[] { x, Double.parseDouble(y) };

				data.add(itemObj);
			}
		}

		return data;
	}

}
