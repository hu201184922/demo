package com.ehuatai.app.plate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.ehuatai.app.plate.service.AppPlateService;
import com.ehuatai.app.plate.service.AppPlateSqlService;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.AppBkfwService;
import com.ehuatai.thrift.AppBkfwService.Client;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.DateUtil;
import com.ehuatai.util.DateUtils;

@Service
public class AppPlateServiceImpl implements AppPlateService {
	@Autowired
	private AppPlateSqlService appPlateSqlService;

	private Logger log = LoggerFactory.getLogger(getClass());

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(AppBkfwService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public RestResult blockNavs(Map<String, Object> reqParams) {
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
		if (dto.getSuccess().equals("false")) {

			log.error(dto.getErrMsgs());

			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {

			log.warn("数据为空");

			return RestResult.getError(RestCode.EMPTY);
		}
		JSONObject resultObject = JSONObject.parseObject(resultJSONString);
		return RestResult.getSuccess(resultObject);
	}

	@Override
	public RestResult blockpages(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		HashMap<String, Object> resultMap = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getPage(reqParamsJSON);
			String resultJSONString = dto.getJson();
			if (StringUtils.isEmpty(resultJSONString)) {
				log.warn("数据为空");
				return RestResult.getError(RestCode.EMPTY);
			}
			JSONObject resultObject = JSONObject.parseObject(resultJSONString);
			// 一级指标
			JSONArray oneTargetList = resultObject.getJSONArray("targetBean");
			if (null != oneTargetList) {
				for (int i = 0; i < oneTargetList.size(); i++) {
					JSONObject jsonResult = oneTargetList.getJSONObject(i);
					String sql = jsonResult.getString("value");
					if (null != sql && sql != "") {
						List<Map<String, Object>> lists = appPlateSqlService.blockpages(reqParams, sql);
						Object value = (lists == null || lists.isEmpty()) ? "" : lists.get(0).get("VAL");
						jsonResult.put("value", value);
					} else {
						jsonResult.put("value", "");
					}
				}
			}
			// 二级指标
			JSONArray twoTargetList = resultObject.getJSONArray("targetList");
			if (null != twoTargetList) {
				for (int i = 0; i < twoTargetList.size(); i++) {
					JSONObject jsonResult = twoTargetList.getJSONObject(i);
					JSONArray jsons = jsonResult.getJSONArray("target");
					if (null != jsons) {
						for (int j = 0; j < jsons.size(); j++) {
							JSONObject jsonValue = jsons.getJSONObject(j);
							String sql = jsonValue.getString("value");
							if (null != sql && sql != "") {
								List<Map<String, Object>> lists = appPlateSqlService.blockpages(reqParams, sql);
								Object value = (lists == null || lists.isEmpty()) ? "" : lists.get(0).get("VAL");
								jsonValue.put("value", value);
							} else {
								jsonValue.put("value", "");
							}

						}
					}
				}
			}
			// 查询时间指标
			JSONArray timeReqs = resultObject.getJSONArray("timeReqs");
			list.add(oneTargetList);
			list.add(twoTargetList);
			resultMap.put("timeReqs", timeReqs);
			resultMap.put("TargetList", list);

		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (dto.getSuccess().equals("false")) {

			log.error(dto.getErrMsgs());

			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}

		return RestResult.getSuccess(resultMap);
	}

	@Override
	public RestResult getTrendMain(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		Map<String, Object> resultMap = new HashMap<>();
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
				// 获取日期分组依据
				String groupbyDate = reqParams.get("groupbyDate").toString();
				dto = client.getMainData(reqParamsJSON);
				JSONArray jsArr = JSONArray.parseArray(dto.getJson());
				if (null != jsArr && !jsArr.isEmpty()) {
					JSONObject obj = JSONObject.parseObject(jsArr.get(0).toString());
					Map<String, Object> map = new HashMap<>();
					map.put("type", obj.getString("type"));
					map.put("name", obj.getString("name"));
					map.put("code", obj.getString("code"));
					map.put("unit", obj.getString("unit"));
					map.put("stack", obj.getString("stack"));
					map.put("color", obj.getString("color"));
					String sql = obj.getString("sql");
					if (null != sql && "" != sql) {
						if (i == 0) {
							resultMap.put("name", obj.getString("title"));
							resultMap.put("stacking", "");
							resultMap.put("xAxis", null);
						}
						List<Map<String, Object>> lists = appPlateSqlService.getTrendMain(reqParams, sql);
						List<List<Object>> data = getTrendTargetData(lists, groupbyDate);
						data = dealTargetTimeData(groupbyDate, data);
						map.put("data", data);
						series.add(map);
						resultMap.put("series", series);
					} else {
						continue;
					}
				} else {
					log.warn("数据为空");
					return RestResult.getError(RestCode.EMPTY);
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

	@Override
	public RestResult getTrendSub(Map<String, Object> reqParams) {
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
				JSONArray jsArr = JSONArray.parseArray(dto.getJson());
				// 获取日期分组依据
				String groupbyDate = reqParams.get("groupbyDate").toString();
				if (null != jsArr && !jsArr.isEmpty()) {
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
					if (null != sql && "" != sql) {
						resultMap.put("name", obj.getString("title"));
						resultMap.put("stacking", "");
						resultMap.put("xAxis", null);
						List<Map<String, Object>> lists = appPlateSqlService.getTrendSub(reqParams, sql);
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
				} else {
					log.warn("数据为空");
					return RestResult.getError(RestCode.EMPTY);
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
				if (null != dataList) {
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
				} else {
					for (String dataStr : dateMonthList) {
						Long time = DateUtil.getTime(dataStr, "yyyy-MM");
						List<Object> tempList = new ArrayList<Object>();
						tempList.add(time);
						tempList.add(0);
						tempDataList.add(tempList);
					}
				}
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
}