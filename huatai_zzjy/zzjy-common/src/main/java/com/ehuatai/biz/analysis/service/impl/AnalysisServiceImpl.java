package com.ehuatai.biz.analysis.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
import com.ehuatai.biz.analysis.service.AnalysisSQLCacheService;
import com.ehuatai.biz.analysis.service.AnalysisService;
import com.ehuatai.biz.browse.bean.OrgBean;
import com.ehuatai.biz.domain.Chart;
import com.ehuatai.biz.domain.ChartSerie;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.conts.Consts;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.ZtfxService;
import com.ehuatai.thrift.ZtfxService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.DateUtil;
import com.ehuatai.util.DateUtils;
import com.ehuatai.util.ExcelUtils;
import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	@Autowired
	private Module module;

	@Autowired
	private AnalysisSQLCacheService sqlCacheService;

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private SQLMapper sqlMapper;

	private Logger log = LoggerFactory.getLogger(getClass());

	private List<String> bfbSubList = null;

	public AnalysisServiceImpl() {
		bfbSubList = new ArrayList<String>();
		bfbSubList.add("T09");
		// bfbSubList.add("T10");
	}

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(ZtfxService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public RestResult analysisNavs(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getFastMenu(reqParamsJSON);
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
	public RestResult analysisBase(Map<String, Object> reqParams) {
		// ["TEMP02_REG01", "TEMP02_REG02", "TEMP02_REG03", "TEMP02_REG04",
		// "TEMP02_REG05", "TEMP02_REG06","TEMP02_REG07"]
		// ["common","main","sub","dist","org","spec","torg"]
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getSubject(reqParamsJSON);
		} catch (TException e) {

			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn(dto.getErrMsgs());
			return RestResult.getError(RestCode.EMPTY);
		}

		JSONObject resultJSONObject = JSON.parseObject(resultJSONString);

		// 转换区域
		JSONArray modulesArray = resultJSONObject.getJSONArray("modules");
		if (!modulesArray.isEmpty()) {
			List<String> modules = new ArrayList<String>();
			modulesArray.forEach(module -> {

				modules.add(module.toString());
			});

			List<String> convertedModules = module.moduleConvert(modules);
			modulesArray.clear();
			modulesArray.addAll(convertedModules);
		}
		resultJSONObject.put("endTime",DateUtils.getYesterDay());
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult analysisCommon(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);

			// 获取所有的指标
			dto = client.getRegTarget(reqParamsJSON);
			if (dto.getSuccess().equals("false")) {
				log.error(dto.getErrMsgs());

				return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
			}
			String targetsJSONString = dto.getJson();
			if (StringUtils.isEmpty(targetsJSONString)) {
				log.warn("获取的指标为空");
				return RestResult.getError(RestCode.EMPTY);
			}

			// 结果集
			List<Object> results = new ArrayList<Object>();

			// 所有的指标代码
			JSONArray targets = JSONArray.parseArray(targetsJSONString);

			for (int i = 0, len = targets.size(); i < len; i++) {
				String targetCode = targets.getString(i);
				// 添加指标
				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				// 获取该指标的数据
				dto = client.getCommonData(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {
					log.warn(dto.getErrMsgs());
					continue;
				}

				String targetJSONString = dto.getJson();
				if (StringUtils.isEmpty(targetJSONString)) {
					log.warn("指标代码为{}的指标数据为空", targetCode);
					continue;
				}

				JSONObject targetObject = JSONObject.parseObject(targetJSONString);

				String name = targetObject.getString("name");
				// 获取SQL
				String sql = targetObject.getString("sql");

				List<Map<String, Object>> targetMaps = sqlCacheService.analysisCommon(reqParams, sql);
				if (targetMaps == null || targetMaps.isEmpty()) {
					log.warn("指标代码为{}的SQL数据为空", targetCode);
					continue;
				}

				Map<String, Object> map = targetMaps.get(0);
				Object value = null;
				if (map == null || map.isEmpty()) {
					log.warn("指标[{}]值为空", name);
					map = new HashMap<String, Object>();
					value = "--";
				} else {
					value = map.get("VAL");
				}

				// 清理,复用map
				map.clear();

				map.put("name", name);
				map.put("value", value);

				results.add(map);

			}

			return RestResult.getSuccess(results);
		} catch (TException e) {

			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}

	private List<Map<String, Object>> getMainTargets(Map<String, Object> reqParams, Client client, ResponseBeanDto dto,
			List<String> checkedTargets) throws TException {
		String reqParamsJSON = JSON.toJSONString(reqParams);

		// 获取所有的一级指标列表
		dto = client.getMainTarget(reqParamsJSON);

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
		int count = 0;
		// 设置指标选中状态为true
		for (Map<String, Object> m : targets) {
			Object code = m.get("code");
			if (checkedTargets.contains(code)) {
				m.put("checked", true);
				count++;
			}
		}
		if (count == 0) {
			targets.get(0).put("checked", true);
		}
		return targets;
	}

	@Override
	public RestResult analysisMain(Map<String, Object> reqParams) {
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

			// 数据结果
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
				String subCode = targetCode.substring(0, 3);
				reqParams.put("targetCode", targetCode);
				reqParamsJSON = JSON.toJSONString(reqParams);
				dto = client.getMainData(reqParamsJSON);
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
				List<Map<String, Object>> results = sqlCacheService.analysisMain(reqParams, sql.toString());
				if (results == null || results.isEmpty()) {
					seriesMap.remove("sql");
					seriesMap.put("data", Collections.EMPTY_LIST);
					log.warn("[{}]指标SQL获取的数据为空", targetCode);
					continue;
				}
				seriesMap.remove("sql");
				// 结果集时间处理
				List<List<Object>> data = handleSQLData(results, groupbyDate);
				// data = this.dealTargetTimeData(groupbyDate, data);
				data = this.isBfbTargetDeal(subCode, groupbyDate, data);
				seriesMap.put("data", data);
			}
			// 如果当前维度无默认指标，则选择可现的第一个指标展示
			if (series.size() == 0) {
				if (targets.size() > 0) {
					JSONObject obj = JSONObject.parseObject(targets.get(0).toString());
					String targetCode = obj.get("code").toString();
					String subCode = targetCode.substring(0, 3);
					reqParams.put("targetCode", targetCode);
					reqParamsJSON = JSON.toJSONString(reqParams);
					dto = client.getMainData(reqParamsJSON);
					if (dto.getSuccess().equals("true")) {
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
						List<Map<String, Object>> results = sqlCacheService.analysisMain(reqParams, sql.toString());
						seriesMap.remove("sql");
						// 结果集时间处理
						List<List<Object>> data = handleSQLData(results, groupbyDate);
						// data = this.dealTargetTimeData(groupbyDate, data);
						data = this.isBfbTargetDeal(subCode, groupbyDate, data);
						seriesMap.put("data", data);
					} else {
						String errCode = dto.getErrCode();
						if (errCode.equals("500")) {
							return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
						} else if (errCode.equals("E00001")) {
							if (null != targets && !targets.isEmpty()) {
								targetData.remove("series");
								return RestResult.getSuccess(restMap);
							} else {
								return RestResult.getError(RestCode.EMPTY);
							}
						}
					}
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
		if (mainDatas == null || mainDatas.isEmpty())
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
		for (Map<String, Object> mainData : mainDatas) {
			String xAxis = mainData.get("X_VAL").toString();
			if (lowerGroupbyDate.equals("year")) {
				xAxis = xAxis + "-01-01 00";
			}
			Long xAxisTime = DateUtil.getTime(xAxis, pattern);
			String yAxis = mainData.get("Y_VAL").toString();
			// 数组第一个元素为时间戳 第二个元素是y轴的值
			List<Object> objList = new ArrayList<Object>();
			objList.add(xAxisTime);
			objList.add(Double.parseDouble(yAxis));
			data.add(objList);
		}
		return data;
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

		dto = client.getSubTarget(reqParamsJSON);

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

	@Override
	public RestResult analysisSub(Map<String, Object> reqParams) {
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
				dto = client.getSubData(reqParamsJSON);
				if (dto.getSuccess().equals("true")) {
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
					logInfo("二级指标区域", targetCode, sql.toString());
					if (StringUtils.isEmpty(sql)) {
						_targetData.remove("sql");
						_targetData.put("data", Collections.EMPTY_LIST);
						log.error("从thrift获取的SQL为空,指标代码为{}", targetCode);
						continue;
					}
					List<Map<String, Object>> results = sqlCacheService.analysisSub(reqParams, sql.toString());
					if (results == null || results.isEmpty()) {
						_targetData.remove("sql");
						_targetData.put("data", Collections.EMPTY_LIST);
						log.error("从thrift获取的SQL获取的数据为空,指标代码为{}", targetCode);
						continue;
					}
					List<List<Object>> data = handleSQLData(results, groupbyDate);
					// 结果集时间处理
					// data = this.dealTargetTimeData(groupbyDate, data);
					data = this.isBfbTargetDeal(targetCode.substring(0, 3), groupbyDate, data);
					_targetData.remove("sql");
					_targetData.put("data", data);
				} else {
					String errCode = dto.getErrCode();
					if (errCode.equals("500")) {
						return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
					} else if (errCode.equals("E00001")) {
						if (null != targets && !targets.isEmpty()) {
							return RestResult.getSuccess(rest);
						} else {
							return RestResult.getError(RestCode.EMPTY);
						}
					}
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

	@Override
	public RestResult analysisDist(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			Object targetCode = reqParams.get("target1");
			reqParams.remove("target1");
			reqParams.put("targetCode", targetCode);
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getDistData(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("排名数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		// 转换为图表对象
		Chart chart = JSONObject.parseObject(resultJSONString, Chart.class);
		// 获取series
		List<ChartSerie> series = chart.getSeries();
		if (series != null && !series.isEmpty()) {
			for (ChartSerie serie : series) {
				String sql = serie.getData().toString();
				if (StringUtils.isEmpty(sql)) {
					log.warn("{} SQL为空", serie.getName());
					continue;
				}
				List<Map<String, Object>> lists = sqlCacheService.analysisDist(reqParams, sql);
				log.warn(lists.toString());
				// 处理SQL获取的数据
				Object data = handleDistSQLData(lists, chart);
				serie.setData(data);
			}
		}
		return RestResult.getSuccess(chart);
	}

	/**
	 * 处理Dist区域SQL查询出来的数据
	 * 
	 * @param lists
	 * @return
	 */
	private Object handleDistSQLData(List<Map<String, Object>> lists, Chart chart) {
		List<Object> data = new ArrayList<Object>();
		if (lists == null || lists.isEmpty()) {
			log.warn("SQL数据为空");
			return data;
		}
		List<String> xAixs = new ArrayList<String>();
		lists.forEach(point -> {
			String code = null;
			String value = null;
			String xAix = null;
			if(point.containsKey("P_VAL")) {
				 code = point.get("P_VAL").toString();
			}
			if(point.containsKey("Y_VAL")) {
				 value = point.get("Y_VAL").toString();
			}
			if(point.containsKey("X_VAL")) {
				 xAix = point.get("X_VAL").toString();
			}			
			// 添加x轴信息			
			xAixs.add(xAix);
			Map<String, Object> pointMap = new HashMap<String, Object>();
			pointMap.put("code", code);
			if(!StringUtils.isEmpty(value)) {
				pointMap.put("y", Double.parseDouble(value));
			}else {
				pointMap.put("y", null);
			}
			// y轴信息 并添加code
			data.add(pointMap);
		});
		chart.setxAxis(xAixs);
		return data;
	}

	@Override
	public RestResult analysisOrg(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			Object targetCode = reqParams.get("target1");
			reqParams.remove("target1");
			reqParams.put("targetCode", targetCode);
			// 北京特殊处理
			Object roleOrg = reqParams.get("roleOrg");
			Object pOrg = reqParams.get("pOrg");
			if (roleOrg.equals("101") && pOrg.equals("101")) {
				reqParams.put("pOrg", "10101");
			}
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getOrgData(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("thrift数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		JSONArray resultArray = JSONArray.parseArray(resultJSONString);
		// 获取表头
		JSONArray title = resultArray.getJSONArray(0);
		// 获取数据
		JSONObject resultJSONObject = resultArray.getJSONObject(1);
		// 获取SQL语句
		String sql = resultJSONObject.getString("sql");
		List<Object> list = new ArrayList<Object>();
		// 添加表头
		list.add(title);
		if (!StringUtils.isEmpty(sql)) {
			List<Map<String, Object>> lists = sqlCacheService.analysisOrg(reqParams, sql);
			if (lists != null && !lists.isEmpty()) {
				for (Map<String, Object> item : lists) {
					// 列的数量
					int size = title.size();
					// 存储值
					Object[] values = new Object[size];
					for (int i = 0; i < size; i++) {
						values[i] = item.get("VAL" + i);
					}
					list.add(values);
				}
			}
		}
		// 不能将SQL暴露到前台
		resultJSONObject.remove("sql");
		// 添加转换后的列表
		resultJSONObject.put("list", list);
		return RestResult.getSuccess(resultJSONObject);
	}

	public RestResult analysisTorg(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			Object targetCode = reqParams.get("target1");
			reqParams.remove("target1");
			reqParams.put("targetCode", targetCode);
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getTorgData(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("thrift数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		JSONArray resultArray = JSONArray.parseArray(resultJSONString);
		// 获取表头
		JSONArray title = resultArray.getJSONArray(0);
		// 获取数据
		JSONObject resultJSONObject = resultArray.getJSONObject(1);
		// 获取SQL语句
		String sql = resultJSONObject.getString("sql");
		List<Object> list = new ArrayList<Object>();
		// 添加表头
		list.add(title);
		if (!StringUtils.isEmpty(sql)) {
			List<Map<String, Object>> lists = sqlCacheService.analysisTorg(reqParams, sql);
			if (lists != null && !lists.isEmpty()) {
				for (Map<String, Object> item : lists) {
					// 列的数量
					int size = title.size();
					// 存储值
					Object[] values = new Object[size];
					for (int i = 0; i < size; i++) {
						values[i] = item.get("VAL" + i);
					}
					list.add(values);
				}
			}
		}
		// 不能将SQL暴露到前台
		resultJSONObject.remove("sql");
		// 添加转换后的列表
		resultJSONObject.put("list", list);
		return RestResult.getSuccess(resultJSONObject);
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

		dto = client.getRegTarget(reqParamsJSON);
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public RestResult analysisSpec(Map<String, Object> reqParams) {
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

				dto = client.getSpecData(reqParamsJSON);

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

				this.logInfo("特殊区域", targetCode, sqlObj.toString());

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
				List<Map<String, Object>> results = sqlCacheService.analysisSpec(reqParams, sqlObj.toString());

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

	@Override
	public RestResult analysisDef(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.setDefTarget(reqParamsJSON);
		} catch (TException e) {

			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();

		return RestResult.getSuccess(resultJSONString);
	}

	@Override
	public RestResult analysisRegion(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONArray lestList;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.tallestCow(reqParamsJSON);
			lestList = JSON.parseArray(dto.getJson());
			if (lestList.isEmpty()) {
				log.warn("结果为空");
				return RestResult.getError(RestCode.EMPTY);
			}
			// 列表List
			JSONArray colList = JSON.parseArray(lestList.get(1).toString());
			lestList.clear();
			// List<Map<String, Object>> list = new ArrayList<>();
			for (Object colObj : colList) {
				JSONObject col = JSON.parseObject(colObj.toString());
				// String name = col.getString("name");
				String sql = col.getString("sql");
				List<Map<String, Object>> lists = sqlCacheService.analysisRegion(reqParams, sql);
				if (!lists.isEmpty()) {
					// 处理值
					for (Map<String, Object> dataMap : lists) {
						Map<String, Object> tarMap = new HashMap<>();
						Object name = (dataMap == null || dataMap.isEmpty()) ? "" : dataMap.get("VAL0");
						Object value = (dataMap == null || dataMap.isEmpty()) ? "" : dataMap.get("VAL1");
						Object val02 = (dataMap == null || dataMap.isEmpty()) ? "" : dataMap.get("VAL2");
						Object val03 = (dataMap == null || dataMap.isEmpty()) ? "" : dataMap.get("VAL3");
						tarMap.put("name", name.toString());
						tarMap.put("value", value.toString());
						if (null != val02 && null != val03) {
							tarMap.put("va102", val02);
							tarMap.put("va103", val03);
						}
						lestList.add(tarMap);
					}
				}
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		return RestResult.getSuccess(lestList);
	}

	@Override
	public RestResult analysisOrgGet(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.orgCompareParam(reqParamsJSON);
			if (dto.getErrCode().equals("E00001")) {
				return RestResult.getError(RestCode.EMPTY);
			} else {
				String resultJSONString = dto.getJson();
				if (StringUtils.isEmpty(resultJSONString)) {
					log.error("参数的值为空");
					return RestResult.getError(RestCode.EMPTY);
				}
				return RestResult.getSuccess(JSON.parseObject(resultJSONString));
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RestResult analysisOrgCompare(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONArray returnList;
		try {
			returnList = new JSONArray();
			// 2017-10-12 修改重组reqParams
			List<String> newOrgs = new ArrayList<>();
			List<String> newNames = new ArrayList<>();
			List<Map<String, Object>> orgs = (List<Map<String, Object>>) reqParams.get("orgs");
			for (Map<String, Object> or : orgs) {
				newOrgs.add(or.get("code").toString());
				newNames.add(or.get("name").toString());
			}
			reqParams.remove("orgs");
			reqParams.put("orgs", newOrgs);
			reqParams.put("names", newNames);
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.orgCompare(reqParamsJSON);
			JSONArray orgList = JSON.parseArray(dto.getJson());
			if (orgList.isEmpty()) {
				log.error("结果为空");
				return RestResult.getError(RestCode.EMPTY);
			}
			for (int i = 0; i < orgList.size(); i++) {
				JSONObject org = JSON.parseObject(orgList.getString(i));
				List<Object> sList = new ArrayList<>();
				JSONObject series = org.getJSONObject("series");
				List<String> xAxis = new ArrayList<>();
				List<Double> dataList = new ArrayList<>();
				String sql = series.getString("sql");
				reqParams.put("compareOrg", newOrgs.get(i));
				List<Map<String, Object>> lists = sqlCacheService.analysisOrgCompare(reqParams, sql);
				for (Map<String, Object> list : lists) {
					Object mon = (lists == null || lists.isEmpty()) ? "" : list.get("X_VAL");
					Object data = (lists == null || lists.isEmpty()) ? "" : list.get("Y_VAL");
					xAxis.add(mon.toString());
					dataList.add(Double.parseDouble(data.toString()));
				}
				// 处理值
				org.replace("xAxis", xAxis);
				series.remove("sql");
				series.put("data", dataList);
				List<String> roleOrgs = (List<String>) reqParams.get("names");
				String rName = (String) roleOrgs.get(i);
				org.put("name", rName);
				sList.add(series);
				org.replace("series", sList);
				returnList.add(org);
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(returnList)) {
			log.error("参数的值为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(returnList);
	}

	@Override
	public RestResult analysisReportList(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		String resultJSONString = "";
		JSONObject resultMap;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.exportBill(reqParamsJSON);
			if (dto.getErrCode().equals("E00001")) {
				return RestResult.getError(RestCode.EMPTY);
			} else {
				resultJSONString = dto.getJson();
				resultMap = JSON.parseObject(resultJSONString);
				String roleOrg = (String) reqParams.get("roleOrg");
				if (StringUtil.isNotNull(reqParams.get("orgCode"))) {
					roleOrg = (String) reqParams.get("orgCode");
				}
				String orgName = "";
				Map<String, Object> mapData = null;
				switch (roleOrg.length()) {
				case 1:
					orgName = "总公司";
					break;
				case 3:
					mapData = this.baseMapper.getCompanyByCode(roleOrg);
					orgName = (String) mapData.get("NAME");
					break;
				case 5:
					mapData = this.baseMapper.getBranchsByCode(roleOrg);
					orgName = (String) mapData.get("NAME");
					break;
				default:
					mapData = this.baseMapper.getServiceByCenterCode(roleOrg);
					orgName = (String) mapData.get("NAME");
					break;
				}
				resultMap.put("orgName", orgName);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				resultMap.put("endTime", sdf.format(cal.getTime()));
				// 响应数据
				List<List<String>> list = new ArrayList<>();
				// 列表明细
				String sql = resultMap.getString("sql");
				List<Map<String, Object>> lists = sqlCacheService.analysisReportList(reqParams, sql);
				for (Map<String, Object> colObj : lists) {
					// 每一行记录
					List<String> deList = new ArrayList<>();
					for (int j = 0; j < colObj.size(); j++) {
						Object col = (lists == null || lists.isEmpty()) ? "" : colObj.get("VAL" + j);
						deList.add(col.toString());
					}
					list.add(deList);
				}
				resultMap.put("list", list);
				// 去除多余key
				resultMap.remove("head");
				resultMap.remove("sql");
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (StringUtils.isEmpty(resultMap)) {
			log.error("参数的值为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(resultMap);
	}

	@Override
	public RestResult analysisReportDownload(Map<String, Object> reqParams, HttpServletRequest request,
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
			dto = client.downloadBill(reqParamsJSON);
			resultJSONString = dto.getJson();
			JSONObject resultMap = JSON.parseObject(resultJSONString);
			String sql = resultMap.getString("sql");
			List<Map<String, Object>> lists = sqlCacheService.analysisReportDownload(reqParams, sql);
			List<Map<String, Object>> targetMaps = new ArrayList<>();
			if (lists != null && !lists.isEmpty()) {
				for (Map<String, Object> item : lists) {
					Map<String, Object> items = new HashMap<>();
					Set<String> keys = lists.get(0).keySet();
					for (int i = 0, len = keys.size(); i < len; i++) {
						items.put("VAL" + i, item.get("VAL" + i) == null ? "" : item.get("VAL" + i).toString().trim());
					}
					targetMaps.add(items);
				}
			}
			List<Map<String, Object>> sortMaps = new ArrayList<>();
			for (Map<String, Object> targetMap : targetMaps) {
				List<Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(targetMap.entrySet());
				Collections.sort(list, new Comparator<Entry<String, Object>>() {
					@Override
					public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
						String o1key = o1.getKey();
						String o2key = o2.getKey();
						Integer o1kv = Integer.parseInt(o1key.substring(3, o1key.length()));
						Integer o2kv = Integer.parseInt(o2key.substring(3, o2key.length()));
						int flag = o1kv.compareTo(o2kv);
						if (flag == 0) {
							return o1kv.compareTo(o2kv);
						}
						return flag;
					}
				});
				Map<String, Object> tarmap = new LinkedHashMap<>();
				for (Entry<String, Object> en : list) {
					tarmap.put(en.getKey(), en.getValue());
				}
				sortMaps.add(tarmap);
			}
			if (null != sortMaps) {
				Map<String, Object> titleMap = sortMaps.get(0);
				Object[] titleArray = titleMap.values().toArray();// 表头的集合
				Object[] fieldArray = titleMap.keySet().toArray();
				realname = ExcelUtils.getFileNameUrl("analysislist", response, titleArray, fieldArray,
						sortMaps.subList(1, sortMaps.size()));
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
		resMap.put("fileurl", PropsUtil.get("base.url") + "/ceph/comdownloadfile/" + realname);
		resMap.put("filename", realname);
		return RestResult.getSuccess(resMap);
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
			calendar.add(Calendar.DATE, -1);
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
	 * @功能 {保费部一级二级指标的天时间维度数据特殊处理}
	 * @作者 MaxBill
	 * @时间 2017年9月25日 下午1:20:22
	 */
	public List<List<Object>> dealBfbDayData(List<List<Object>> dataList) {
		List<List<Object>> tempDataList = new ArrayList<List<Object>>();
		try {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			List<String> dateMonthList = null;
			if (month <= 2) {
				dateMonthList = DateUtils.getDateBetweenByMonth(year - 1 + "-01", year - 1 + "-" + (12 - month));
			} else {
				dateMonthList = DateUtils.getDateBetweenByMonth(year - 1 + "-01", year + "-" + (month - 2));
			}
			// 获取时间范围每月最后一天的时间集合
			List<String> lastDayDateList = new ArrayList<String>();
			for (String dateMonth : dateMonthList) {
				lastDayDateList.add(DateUtils.getLastDayOfMonth(dateMonth));
			}
			// 获取最近两个月每天的时间集合
			List<String> currDayDateList = new ArrayList<String>();
			SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1);
			String currDay = formatDay.format(calendar.getTime());
			if (month == 1) {
				String lateDay = year - 1 + "-12" + "-01";
				currDayDateList = DateUtils.getDateBetweenByDay(lateDay, currDay);
			} else {
				String lateDay = year + "-" + (month - 1) + "-01";
				currDayDateList = DateUtils.getDateBetweenByDay(lateDay, currDay);
			}
			// 特殊时间和最近时间集合
			List<String> allDayDateList = new ArrayList<String>();
			allDayDateList.addAll(lastDayDateList);
			allDayDateList.addAll(currDayDateList);
			if (null != dataList) {
				for (String dataStr : allDayDateList) {
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
				for (String dataStr : allDayDateList) {
					Long time = DateUtil.getTime(dataStr, "yyyy-MM-dd");
					List<Object> tempList = new ArrayList<Object>();
					tempList.add(time);
					tempList.add(0);
					tempDataList.add(tempList);
				}
			}
			dataList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempDataList;
	}

	/**
	 * @功能 {对保费部天维度一级和二级特殊处理}
	 * @作者 MaxBill
	 * @时间 2017年9月25日 下午5:12:48
	 */
	public List<List<Object>> isBfbTargetDeal(String subCode, String groupbyDate, List<List<Object>> dataList) {
		if (bfbSubList.contains(subCode) && groupbyDate.equals("DAY")) {
			return this.dealBfbDayData(dataList);
		} else {
			return this.dealTargetTimeData(groupbyDate, dataList);
		}
	}

	/**
	 * @功能 {机构搜索}
	 * @作者 MaxBill
	 * @时间 2017年9月25日 下午5:12:48
	 */
	public RestResult analysisOrgSearch(Map<String, Object> reqParams) {
		String orgKey = reqParams.get("orgKey").toString();
		List<OrgBean> orgBeanList = new ArrayList<OrgBean>();
		if (!StringUtils.isEmpty(orgKey)) {
			String roleOrg = reqParams.get("roleOrg").toString();
			String subject = reqParams.get("subject").toString();
			Integer type = 0;
			if (subject.equals("T07")) {
				type = 1;
			} else if (subject.equals("T08") || subject.equals("T09") || subject.equals("T10")) {
				type = 2;
			} else {
				type = 3;
			}
			List<HashMap<String, Object>> mapData = null;
			switch (roleOrg.length()) {
			case 1:
				mapData = sqlMapper.searchOrg01(orgKey, type);
				break;
			case 3:
				mapData = sqlMapper.searchOrg02(orgKey, type);
				break;
			case 5:
				mapData = sqlMapper.searchOrg03(orgKey);
				break;
			}
			for (HashMap<String, Object> map : mapData) {
				String orgCode = (String) map.get("O_CODE").toString();
				String orgName = (String) map.get("O_NAME");
				String orgType = (String) map.get("O_TYPE").toString();
				OrgBean orgBean = new OrgBean();
				orgBean.setCode(orgCode);
				orgBean.setName(orgName);
				orgBean.setType(orgType);
				orgBeanList.add(orgBean);
			}
		}
		return RestResult.getSuccess(orgBeanList);
	}

	public void logInfo(String reg, String targetCode, String sql) {
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.err.println(">>>>>>区域：" + reg);
		System.err.println(">>>>>>指标：" + targetCode);
		System.err.println(">>>>>>语句：" + sql);
		System.err.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}
