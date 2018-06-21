package com.ehuatai.biz.analysis.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.analysis.service.AnalysisRealService;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.conts.Consts;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.SsTargetService;
import com.ehuatai.thrift.SsTargetService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.DateUtil;
import com.ehuatai.util.DateUtils;
import com.ehuatai.util.ExcelUtils;
import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Service
public class AnalysisRealServiceImpl implements AnalysisRealService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SQLMapper sqlMapper;
	
	private List<Map<String, Object>> daylist = new ArrayList<>();
	private List<Map<String, Object>> monlist1 = new ArrayList<>();
	private List<Map<String, Object>> monlist2 = new ArrayList<>();
	private List<Map<String, Object>> yearlist = new ArrayList<>();
	private List<Map<String, Object>> headlist = new ArrayList<>();
	
	
	private List<Map<String, Object>> list1 = new ArrayList<>();
	private List<Map<String, Object>> list2 = new ArrayList<>();
	private List<Map<String, Object>> list3 = new ArrayList<>();
	private List<Map<String, Object>> list4 = new ArrayList<>();
	private List<Map<String, Object>> list5 = new ArrayList<>();
	private List<Map<String, Object>> list6 = new ArrayList<>();

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(SsTargetService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	public RestResult analysisRealtime(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONObject resObj = new JSONObject();
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.analysisRealtime(reqParamsJSON);
			if (reqParams.get("key").toString().equals("api")) {
				resObj = JSONObject.parseObject(dto.getJson());
			} else {
				String resultString = dto.getJson();
				resObj = JSONObject.parseObject(resultString);
				
				JSONArray sqls = resObj.getJSONArray("sqls");
				
				ExecutorService exe = Executors.newFixedThreadPool(8);
				
				exe.execute(new Runnable() {
					
					@Override
					public void run() {
						list1 = sqlMapper.findBySQL(sqls.getString(0));
					}
				});
				exe.execute(new Runnable() {
					
					@Override
					public void run() {
						list2 = sqlMapper.findBySQL(sqls.getString(1));
					}
				});
				exe.execute(new Runnable() {
					
					@Override
					public void run() {
						list3 = sqlMapper.findBySQL(sqls.getString(2));
					}
				});
				exe.execute(new Runnable() {
					
					@Override
					public void run() {
						list4 = sqlMapper.findBySQL(sqls.getString(3));
					}
				});
				exe.execute(new Runnable() {
					
					@Override
					public void run() {
						list5 = sqlMapper.findBySQL(sqls.getString(4));
					}
				});
				exe.execute(new Runnable() {
					
					@Override
					public void run() {
						list6 = sqlMapper.findBySQL(sqls.getString(5));
					}
				});
				exe.shutdown();
				while (true) {
					if (exe.isTerminated()) {
						for (int i = 0; i < 6; i++) {
							List<Map<String, Object>> resList = new ArrayList<>();
							resList = i==0?list1:(i==1?list2:(i==2?list3:(i==3?list4:(i==4?list5:(i==5?list6:null)))));
							if (resList.size() > 0 && StringUtil.isNotNull(resList)) {
								Map<String, Object> resMap = resList.get(0);
								Set<String> keys = resMap.keySet();
								// 一级指标
								JSONArray jArr1 = resObj.getJSONArray("target1");
								for (int j = 0; j < jArr1.size(); j++) {
									JSONObject jObj = jArr1.getJSONObject(j);
									String code = jObj.getString("code").toLowerCase();
									if (keys.contains(code)) {
										String valStr = resMap.get(code).toString();
										String unit = resMap.get(code + "_unit").toString();
										BigDecimal value = new BigDecimal(valStr);
										double val = value.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
										String valS = String.valueOf(val) + unit;
										jObj.put("value", valS);
									}
								}
								
								// 二级指标
								JSONArray jArr2 = resObj.getJSONArray("target2");
								
								for (int p = 0; p < jArr2.size(); p++) {
									JSONObject jObj = jArr2.getJSONObject(p);
									JSONArray jAr = jObj.getJSONArray("target");
									for (int q = 0; q < jAr.size(); q++) {
										JSONObject qO = jAr.getJSONObject(q);
										String code = qO.getString("code").toLowerCase();
										if (keys.contains(code)) {
											String valStr = resMap.get(code).toString();
											String unit = resMap.get(code + "_unit").toString();
											BigDecimal value = new BigDecimal(valStr);
											double val = value.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
											String valS = String.valueOf(val) + unit;
											qO.put("value", valS);
										}
									}
								}
							}
						}
						break;
					}
				}
				resObj.remove("sqls");
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(resObj)) {
			log.warn("结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(resObj);
	}

	public RestResult analysisRealMain(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);

			// 获取日期分组依据
			String groupbyDate = reqParams.get("groupbyDate").toString();
			// 获取所有的一级指标
			List<String> checkedTargets = JSON.parseArray(JSONArray.toJSONString(reqParams.get("target1")),
					String.class);
			// 获取所有的一级指标列表
			List<Map<String, Object>> targets = this.getMainTargets(reqParams, client, dto, checkedTargets);
			if (targets == null || targets.isEmpty()) {

				return RestResult.getError(RestCode.EMPTY);
			}
			// data
			Map<String, Object> restMap = new HashMap<String, Object>();

			// 指标数据
			Map<String, Object> targetData = new HashMap<String, Object>();

			// 图形数据列
			List<Object> series = new ArrayList<Object>();
			targetData.put("series", series);

			restMap.put("targets", targets);
			restMap.put("targetData", targetData);

			// 优先展示默认指标
			for (int i = 0, len = checkedTargets.size(); i < len; i++) {
				// 获取code
				String targetCode = checkedTargets.get(i);

				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				dto = client.analysisGetMainData(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {

					log.error("[{}],{}", targetCode, dto.getErrMsgs());

					continue;
				}

				String resultJSONString = dto.getJson();
				if (StringUtils.isEmpty(resultJSONString)) {

					log.error("[{}]指标thrift获取数据为空", targetCode);

					continue;
				}

				@SuppressWarnings("unchecked")
				Map<String, Map<String, Object>> itemMap = JSON.parseObject(resultJSONString, Map.class);

				Map<String, Object> seriesMap = itemMap.get("targetData");

				targetData.put("name", seriesMap.get("title"));
				targetData.put("xAxis", null);
				targetData.put("stacking", "");

				seriesMap.remove("title");

				series.add(seriesMap);

				if (seriesMap == null || seriesMap.isEmpty()) {

					// 移除SQL
					seriesMap.remove("sql");
					seriesMap.put("data", Collections.EMPTY_LIST);

					log.warn("从thrift获取的[{}]指标数据为空", targetCode);
					continue;
				}
				Object sql = seriesMap.get("sql");
				if (StringUtils.isEmpty(sql)) {
					seriesMap.remove("sql");
					seriesMap.put("data", Collections.EMPTY_LIST);
					log.warn("[{}]指标的SQL数据为空", targetCode);
					continue;
				}
				log.debug("[{}]=>获取数据", targetCode);
				List<Map<String, Object>> results = sqlMapper.findBySQL(sql.toString());
				if ((results == null || results.isEmpty()) && !groupbyDate.equals("DAY")) {
					seriesMap.remove("sql");
					seriesMap.put("data", Collections.EMPTY_LIST);
					log.warn("[{}]指标SQL获取的数据为空", targetCode);
					continue;
				}
				seriesMap.remove("sql");
				// 结果集时间处理
				List<List<Object>> data = handleSQLData(results, groupbyDate);
				// data = this.dealTargetTimeData(groupbyDate, data);
				System.err.println(JSON.toJSONString(data));
				seriesMap.put("data", data);

			}
			// 如果当前维度无默认指标，则选择可现的第一个指标展示
			if (series.size() == 0) {
				if (targets.size() > 0) {
					JSONObject obj = JSONObject.parseObject(targets.get(0).toString());
					String targetCode = obj.get("code").toString();
					reqParams.put("targetCode", targetCode);
					reqParamsJSON = JSON.toJSONString(reqParams);
					dto = client.analysisRealMain(reqParamsJSON);
					@SuppressWarnings("unchecked")
					Map<String, Map<String, Object>> itemMap = JSON.parseObject(dto.getJson(), Map.class);
					Map<String, Object> seriesMap = itemMap.get("targetData");
					targetData.put("name", seriesMap.get("title"));
					targetData.put("xAxis", null);
					targetData.put("stacking", "");
					seriesMap.remove("title");
					series.add(seriesMap);
					if (seriesMap == null || seriesMap.isEmpty()) {
						// 移除SQL
						seriesMap.put("data", Collections.EMPTY_LIST);
						log.warn("从thrift获取的[{}]指标数据为空", targetCode);
					}
					Object sql = seriesMap.get("sql");
					if (StringUtils.isEmpty(sql)) {
						seriesMap.put("data", Collections.EMPTY_LIST);
						log.warn("[{}]指标的SQL数据为空", targetCode);
					}
					log.debug("[{}]=>获取数据", targetCode);
					List<Map<String, Object>> results = sqlMapper.findBySQL(sql.toString());
					seriesMap.remove("sql");
					// 结果集时间处理
					List<List<Object>> data = handleSQLData(results, groupbyDate);
					data = this.dealTargetTimeData(groupbyDate, data);
					System.err.println(JSON.toJSONString(data));
					seriesMap.put("data", data);
				}
			}
			return RestResult.getSuccess(restMap);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}

	public RestResult analysisRealSub(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);

			// 日期分组 必须
			String groupbyDate = reqParams.get("groupbyDate").toString();

			// 二级指标分组 必传
			String level = reqParams.get("level").toString();

			List<String> targets = JSON.parseArray(JSON.toJSONString(reqParams.get("target2")), String.class);

			// 不能为空
			if (targets == null)
				targets = new ArrayList<String>();

			// 获取二级指标和二级指标组
			Map<String, List<Map<String, Object>>> rest = getSubTargets(dto, reqParamsJSON, client, level, targets);

			if (rest == null || rest.isEmpty()) {

				return RestResult.getError(RestCode.EMPTY);
			}

			// 指标数据
			List<Map<String, Object>> targetData = new ArrayList<Map<String, Object>>();

			rest.put("targetData", targetData);

			for (String targetCode : targets) {
				// 设置指标代码
				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				// 获取指标数据
				dto = client.analysisSubData(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {

					log.error("[{}], {}", targetCode, dto.getErrMsgs());

					continue;
				}

				String targetJSONString = dto.getJson();

				if (StringUtils.isEmpty(targetJSONString)) {

					log.error("从thrift获取的[{}]指标数据为空", targetCode);

					continue;
				}
				// 图形数据对象
				Map<String, Object> item = new HashMap<String, Object>();

				// 数据列数据
				List<Object> series = new ArrayList<Object>();

				targetData.add(item);
				item.put("stacking", "");
				item.put("xAxis", null);
				item.put("series", series);

				@SuppressWarnings("unchecked")
				Map<String, Map<String, Object>> targetDataMap = JSON.parseObject(targetJSONString, Map.class);
				Map<String, Object> _targetData = targetDataMap.get("targetData");

				series.add(_targetData);

				item.put("name", _targetData.get("title"));

				_targetData.remove("title");

				Object sql = _targetData.get("sql");

				if (StringUtils.isEmpty(sql)) {

					_targetData.remove("sql");
					_targetData.put("data", Collections.EMPTY_LIST);

					log.error("从thrift获取的SQL为空,指标代码为{}", targetCode);

					continue;

				}

				List<Map<String, Object>> results = sqlMapper.findBySQL(sql.toString());
				if (results == null || results.isEmpty()) {
					_targetData.remove("sql");
					_targetData.put("data", Collections.EMPTY_LIST);
					log.error("从thrift获取的SQL获取的数据为空,指标代码为{}", targetCode);
					continue;
				}
				List<List<Object>> data = handleSQLData(results, groupbyDate);
				// 结果集时间处理
				_targetData.remove("sql");
				_targetData.put("data", data);
			}
			if (targetData.size() == 1) {
				Map<String, Object> mData = targetData.get(0);
				List<Object> obj = (List<Object>) mData.get("series");
				Map<String, Object> o = (Map<String, Object>) obj.get(0);
				List<Object> data = (List<Object>) o.get("data");
				if (data.isEmpty()) {
					targetData.clear();
				}
			}
			return RestResult.getSuccess(rest);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}

	public RestResult analysisRealSpec(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {

			String reqParamsJSON = JSON.toJSONString(reqParams);

			List<String> targets = getSpecTargets(client, dto, reqParamsJSON);
			if (targets == null || targets.isEmpty()) {
				return RestResult.getError(RestCode.EMPTY);
			}

			List<Object> restList = new ArrayList<Object>();

			for (String targetCode : targets) {
				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				dto = client.analysisSpecData(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {
					log.error("[{}]指标出现异常,{}", dto.getErrMsgs());
					continue;
				}

				String resultJSONString = dto.getJson();
				if (StringUtils.isEmpty(resultJSONString)) {
					log.error("从thrift获取的指标[{}]数据为空", targetCode);

					continue;
				}
				// 图形对象
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("xAxis", null);
				itemMap.put("stacking", "");

				restList.add(itemMap);

				// 数据列
				List<Object> series = new ArrayList<Object>();

				itemMap.put("series", series);

				Map<String, Object> serie = JSON.parseObject(resultJSONString, Map.class);
				// 获取Data->sql
				Object sqlObj = serie.get("sql");

				serie.remove("sql");
				series.add(serie);

				// 图形类型
				String type = serie.get("type").toString();
				// 图形标题
				String name = serie.get("title").toString();

				itemMap.put("name", name);

				// 数据列中移除标题
				serie.remove("title");

				if (StringUtils.isEmpty(sqlObj)) {
					log.error("指标数据缺少SQL,[{}]", targetCode);
					continue;
				}
				// 获取指标数据
				List<Map<String, Object>> results = sqlMapper.findBySQL(sqlObj.toString());

				if (results == null || results.isEmpty()) {
					serie.put("data", Collections.EMPTY_LIST);

					log.warn("特殊区域SQL获取的数据为空,[{}]", targetCode);

					continue;
				}
				// 获取特殊区域的数据
				serie.put("data", handleSpecSQLData(results, type));
			}

			return RestResult.getSuccess(restList);

		} catch (TException e) {

			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}

	public RestResult analysisRealList(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.analysisRealList(reqParamsJSON);
			String resultJSONString = dto.getJson();
			JSONObject obj = JSONObject.parseObject(resultJSONString);
			String sqlD = obj.getString("day");
			JSONArray sqlMs = obj.getJSONArray("month");
			String sqlY = obj.getString("year");
			String head = obj.getString("head");
			Map<String, Object> restMap = new HashMap<String, Object>();
			List<Object> resultList = new ArrayList<Object>();
			// 结果集
			String[] title = { "指标", "当日", "当月", "当年" };
			resultList.add(0, Arrays.asList(title));

			ExecutorService exe = Executors.newFixedThreadPool(8);

			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					headlist = sqlMapper.findBySQL(head);
				}
			});
			exe.execute(new Runnable() {

				@Override
				public void run() {
					daylist = sqlMapper.findBySQL(sqlD);
				}
			});
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					monlist1 = sqlMapper.findBySQL(sqlMs.getString(0));
				}
			});
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					monlist2 = sqlMapper.findBySQL(sqlMs.getString(1));
				}
			});
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					yearlist = sqlMapper.findBySQL(sqlY);
				}
			});
			
			exe.shutdown();
			while (true) {
				if (exe.isTerminated()) {
					if (headlist != null && !headlist.isEmpty()) {
						Set<String> hs = headlist.get(0).keySet();
						hs = new TreeSet<>(hs);
						List<String> hes = new ArrayList<>(hs);
						Map<String, Object> monMap = monlist1.get(0);
						monMap.putAll(monlist2.get(0));
						for (int i = 0; i < headlist.get(0).size(); i++) {
							List<Object> rowList = new ArrayList<>();
							String row1 = headlist.get(0).get(hes.get(i)).toString();
							String row2 = daylist.get(0).get(hes.get(i)).toString();
							String row3 = monMap.get(hes.get(i)).toString();
							String row4 = yearlist.get(0).get(hes.get(i)).toString();
							rowList.add(row1);
							for (int k = 2; k < 5; k++) {
								String rStr = k == 2 ? row2 : (k == 3 ? row3 : (k == 4 ? row4 : "--"));
								if (!"--".equals(rStr) && StringUtil.isNotNull(rStr)) {
									BigDecimal value = new BigDecimal(rStr);
									double val = value.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									rStr = String.valueOf(val);
									if (rStr.indexOf("E") > 0) {
										BigDecimal bd = new BigDecimal(rStr);
										rStr = bd.toPlainString();
									}
								}
								rowList.add(rStr);
							}
							resultList.add(rowList);
						}
					}
					break;
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			restMap.put("endTime", sdf.format(new Date()));
			restMap.put("list", resultList);
			return RestResult.getSuccess(restMap);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}
	
	public RestResult analysisRealDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		String realname = "";
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
		}
		ResponseBeanDto dto = null;
		String resultJSONString = "";
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.analysisRealList(reqParamsJSON);
			resultJSONString = dto.getJson();
			JSONObject obj = JSONObject.parseObject(resultJSONString);
			String sqlD = obj.getString("day");
			JSONArray sqlMs = obj.getJSONArray("month");
			String sqlY = obj.getString("year");
			String head = obj.getString("head");
			List<Object> resultList = new ArrayList<Object>();
			// 结果集
			String[] title = { "指标", "当日", "当月", "当年" };
			resultList.add(0, Arrays.asList(title));

			ExecutorService exe = Executors.newFixedThreadPool(8);

			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					headlist = sqlMapper.findBySQL(head);
				}
			});
			exe.execute(new Runnable() {

				@Override
				public void run() {
					daylist = sqlMapper.findBySQL(sqlD);
				}
			});
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					monlist1 = sqlMapper.findBySQL(sqlMs.getString(0));
				}
			});
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					monlist2 = sqlMapper.findBySQL(sqlMs.getString(1));
				}
			});
			exe.execute(new Runnable() {
				
				@Override
				public void run() {
					yearlist = sqlMapper.findBySQL(sqlY);
				}
			});
			
			exe.shutdown();
			while (true) {
				if (exe.isTerminated()) {
					if (headlist != null && !headlist.isEmpty()) {
						Set<String> hs = headlist.get(0).keySet();
						hs = new TreeSet<>(hs);
						List<String> hes = new ArrayList<>(hs);
						Map<String, Object> monMap = monlist1.get(0);
						monMap.putAll(monlist2.get(0));
						for (int i = 0; i < headlist.get(0).size(); i++) {
							List<Object> rowList = new ArrayList<>();
							String row1 = headlist.get(0).get(hes.get(i)).toString();
							String row2 = daylist.get(0).get(hes.get(i)).toString();
							String row3 = monMap.get(hes.get(i)).toString();
							String row4 = yearlist.get(0).get(hes.get(i)).toString();
							rowList.add(row1);
							for (int k = 2; k < 5; k++) {
								String rStr = k == 2 ? row2 : (k == 3 ? row3 : (k == 4 ? row4 : "--"));
								if (!"--".equals(rStr) && StringUtil.isNotNull(rStr)) {
									BigDecimal value = new BigDecimal(rStr);
									double val = value.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									rStr = String.valueOf(val);
									if (rStr.indexOf("E") > 0) {
										BigDecimal bd = new BigDecimal(rStr);
										rStr = bd.toPlainString();
									}
								}
								rowList.add(rStr);
							}
							resultList.add(rowList);
						}
					}
					break;
				}
			}
			if (null != resultList) {
				Object[] titleArray = title;// 表头的集合
				// Object[] fieldArray = titleMap.keySet().toArray();
				realname = ExcelUtils.getFileNameUrl("实时指标列表", response, titleArray, null,
						resultList.subList(1, resultList.size()));
			}
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(resultJSONString)) {
			log.error("参数的值为空");
		}
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("fileurl", PropsUtil.get("base.url") + "/ceph/realdownloadfile/" + realname);
		resMap.put("filename", realname);
		return RestResult.getSuccess(resMap);
	}

	public RestResult analysisRealDef(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.analysisRealDef(reqParamsJSON);
		} catch (TException e) {

			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();

		return RestResult.getSuccess(resultJSONString);
	}

	public static void main(String[] args) {
		String aaa = "1.7169131251E10";
		BigDecimal bd = new BigDecimal(aaa);
		System.out.println(bd.toPlainString());
	}
	// 获得指标
	private List<Map<String, Object>> getMainTargets(Map<String, Object> reqParams, Client client, ResponseBeanDto dto,
			List<String> checkedTargets) throws TException {
		String reqParamsJSON = JSON.toJSONString(reqParams);

		// 获取所有的一级指标列表
		dto = client.analysisRealMain(reqParamsJSON);

		if (dto.getSuccess().equals("false")) {

			log.error(dto.getErrMsgs());

			return null;
		}

		String resultTargetsString = dto.getJson();
		if (StringUtils.isEmpty(resultTargetsString)) {

			log.error("一级指标数据列表为空");

			return null;
		}

		@SuppressWarnings("unchecked")
		Map<String, List<Map<String, Object>>> targetMap = JSON.parseObject(resultTargetsString, HashMap.class);

		// 指标列表
		List<Map<String, Object>> targets = targetMap.get("targets");

		if (targets == null || targets.isEmpty()) {

			log.error("一级指标数据列表为空");

			return null;
		}
		// 设置指标选中状态为true
		targets.forEach(m -> {
			Object code = m.get("code");
			if (checkedTargets.contains(code)) {
				m.put("checked", true);
			}
		});
		return targets;
	}

	/**
	 * 处理SQL返回的数据
	 * 
	 * @param mainDatas
	 *            SQL数据
	 * @param groupbyDate
	 *            按照日期分组
	 * @return
	 */
	private List<List<Object>> handleSQLData(List<Map<String, Object>> mainDatas, String groupbyDate) {
		List<List<Object>> data = new ArrayList<List<Object>>();
		if ((mainDatas == null || mainDatas.isEmpty()) && !groupbyDate.equals("DAY"))
			return data;
		String pattern = "yyyy-MM-dd HH";
		String lowerGroupbyDate = groupbyDate.toLowerCase();
		if (lowerGroupbyDate.equals("day")) {
			pattern = "yyyy-MM-dd HH:mm";
		} else if (lowerGroupbyDate.equals("month")) {
			pattern = "yyyy-MM";
		} else {
			pattern = "yyyy-MM-dd HH";
		}
		if ("day".equals(lowerGroupbyDate)) {
			String[] dayArr = { "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00", "02:15",
					"02:30", "02:45", "03:00", "03:15", "03:30", "03:45", "04:00", "04:15", "04:30", "04:45", "05:00",
					"05:15", "05:30", "05:45", "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45",
					"08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30",
					"10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15",
					"13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00",
					"16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45",
					"19:00", "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30",
					"21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00" };
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dayT = sdf.format(new Date());
			for (String dayMin : dayArr) {
				String yAxis = "0.00";
				for (Map<String, Object> mainData : mainDatas) {
					String xAxis = mainData.get("x_val").toString();
					if (dayMin.equals(xAxis)) {
						yAxis = StringUtil.isNotNull(mainData.get("y_val")) ? mainData.get("y_val").toString() : "0.00";
					}
				}
				Long xAxisTime = DateUtil.getTime(dayT + " " + dayMin, pattern);
				List<Object> objList = new ArrayList<Object>();
				objList.add(xAxisTime);
				objList.add(Double.parseDouble(yAxis));
				data.add(objList);
			}
		} else {
			for (Map<String, Object> mainData : mainDatas) {
				String xAxis = mainData.get("x_val").toString();
				if (lowerGroupbyDate.equals("year")) {
					xAxis = xAxis + "-01-01 00";
				}
				if ("month".equals(lowerGroupbyDate) && !xAxis.contains("-")) {
					continue;
				}
				Long xAxisTime = DateUtil.getTime(xAxis, pattern);
				String yAxis = StringUtil.isNotNull(mainData.get("y_val")) ? mainData.get("y_val").toString() : "0.00";
				// 数组第一个元素为时间戳 第二个元素是y轴的值
				List<Object> objList = new ArrayList<Object>();
				objList.add(xAxisTime);
				objList.add(Double.parseDouble(yAxis));
				data.add(objList);
			}
		}
		return data;
	}

	/**
	 * 一级二级指标的时间处理
	 */
	public List<List<Object>> dealTargetTimeData(String groupbyDate, List<List<Object>> dataList) {
		List<List<Object>> tempDataList = null;
		try {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
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
				String monthCount = "";
				if (month < 10) {
					monthCount = "0" + month;
				} else {
					monthCount = month + "";
				}
				List<String> dateMonthList = DateUtils.getDateBetweenByMonth(year - 1 + "-01", year + "-" + monthCount);
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

	/**
	 * 获取二级指标分组和二级指标
	 * 
	 * @param reqParams
	 * @param client
	 * @param level
	 * @param target
	 * @return
	 * @throws TException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, List<Map<String, Object>>> getSubTargets(ResponseBeanDto dto, String reqParamsJSON,
			Client client, String level, List<String> targets) throws TException {

		dto = client.analysisRealSub(reqParamsJSON);

		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());

			return null;
		}

		String targetsJSONString = dto.getJson();
		if (StringUtils.isEmpty(targetsJSONString)) {
			log.warn("二级指标组[{}]的指标组和指标列表为空", level);

			return null;
		}

		Map rest = JSON.parseObject(targetsJSONString, Map.class);
		// 获取二级指标分组
		List<Map<String, Object>> levels = (List<Map<String, Object>>) rest.get("levels");
		if (levels == null || levels.isEmpty()) {
			log.warn("二级指标组为空");
			return null;
		}
		levels.forEach(_level -> {
			if (_level.get("code").equals(level)) {
				// 设置默认选中
				_level.put("checked", true);
			} else {
				// 设置默认选中
				_level.put("checked", false);
			}
		});

		List<Map<String, Object>> target2 = (List<Map<String, Object>>) rest.get("targets");
		Object _defaultTarget = rest.get("defaultTarget");
		if (target2 != null && !target2.isEmpty()) {

			if (targets.isEmpty()) {
				if (_defaultTarget == null || ((List<String>) _defaultTarget).isEmpty()) {
					// 默认添加一个作为默认选中的指标
					Map<String, Object> firstTarget = target2.get(0);
					firstTarget.put("checked", true);
					targets.add(firstTarget.get("code").toString());

				} else {
					List<String> defaultTarget = (List<String>) _defaultTarget;
					// 默认二级指标
					targets.addAll(defaultTarget);
				}
			}
			target2.forEach(target -> {
				// 设置二级指标的选中状态
				Object code = target.get("code");
				if (targets.contains(code)) {
					target.put("checked", true);
				} else {
					target.put("checked", false);
				}
			});
		}
		rest.remove("defaultTarget");
		return rest;
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
				String x = item.get("x_val").toString();
				String y = item.get("y_val").toString();

				Object itemObj = new Object[] { x, Double.parseDouble(y) };

				data.add(itemObj);
			}
		}
		return data;
	}

	/**
	 * 获取特殊区域的指标
	 * 
	 * @param client
	 * @param dto
	 * @param reqParamsJSON
	 * @return
	 * @throws TException
	 */
	private List<String> getSpecTargets(Client client, ResponseBeanDto dto, String reqParamsJSON) throws TException {

		dto = client.analysisRealSpec(reqParamsJSON);
		if (dto.getSuccess().equals("false")) {

			log.error("特殊区域获取指标失败");

			return null;
		}

		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {

			log.error("特殊区域获取指标失败");

			return null;
		}

		List<String> targets = JSON.parseArray(resultJSONString, String.class);
		if (targets == null || targets.isEmpty()) {

			log.error("特殊区域获取指标为空");

			return null;
		}

		return targets;

	}
}