package com.ehuatai.biz.plate.service.impl;

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
import com.ehuatai.biz.browse.bean.OrgBean;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.biz.plate.service.PlateSQLCacheService;
import com.ehuatai.biz.plate.service.PlateService;
import com.ehuatai.conts.Consts;
import com.ehuatai.conts.Module;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.BkfwService;
import com.ehuatai.thrift.BkfwService.Client;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.DateUtil;
import com.ehuatai.util.DateUtils;
import com.ehuatai.util.ExcelUtils;
import com.fairyland.jdp.framework.util.PropsUtil;

@Service
public class PlateServiceImpl implements PlateService {

	@Autowired
	private PlateSQLCacheService sqlCacheService;

	@Autowired
	private Module module;

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private SQLMapper sqlMapper;

	private Logger log = LoggerFactory.getLogger(getClass());

	private List<String> bfbSubList = null;

	public PlateServiceImpl() {
		bfbSubList = new ArrayList<String>();
		bfbSubList.add("T09");
		bfbSubList.add("T10");
	}

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(BkfwService.Client.class);
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
			dto = client.getBlockMenu(reqParamsJSON);
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
	public RestResult blockTarget(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getBlockTarget(reqParamsJSON);
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
		// 获取SQL
		String sql = resultObject.getString("sql");
		System.err.println("板块上面SQL:" + sql);

		List<Map<String, Object>> lists = sqlCacheService.blockTarget(reqParams, sql);
		if (lists == null || lists.isEmpty()) {
			log.warn("获取的数据为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		Boolean isCustom = (Boolean) reqParams.get("isCustom");
		// 复用
		reqParams.clear();

		List<Object> restList = new ArrayList<Object>();

		// 结果集
		reqParams.put("isChart", resultObject.getBoolean("isChart"));

		reqParams.put("isServer", resultObject.getBoolean("isServer"));

		reqParams.put("hasThreeOrg", resultObject.getBoolean("hasThreeOrg"));

		reqParams.put("hasFourOrg", resultObject.getBoolean("hasFourOrg"));

		if (!isCustom) {
			JSONArray head = resultObject.getJSONArray("head");
			restList.add(head);// 获取头部信息
			// 解析数据列
			int size = head.size();
			for (int i = 0, len = lists.size(); i < len; i++) {

				Map<String, Object> item = lists.get(0);
				Object[] items = new Object[size];
				for (int j = 0; j < size; j++) {
					Object val = item.get("VAL" + j);
					if (StringUtils.isEmpty(val))
						val = "--";

					items[j] = val;
				}
				restList.add(items);
			}
			reqParams.put("list", restList);
		} else {
			for (int i = 0, len = lists.size(); i < len; i++) {
				Map<String, Object> item = lists.get(i);
				int size = item.keySet().size();
				Object[] items = new Object[size];
				for (int j = 0; j < size; j++) {
					Object val = item.get("VAL" + j);
					if (StringUtils.isEmpty(val))
						val = "--";

					items[j] = val;
				}
				restList.add(items);
			}

			reqParams.put("list", restList);

		}
		return RestResult.getSuccess(reqParams);

	}

	@Override
	public RestResult blockOrgTarget(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getBlockOrgTarget(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());
			if (dto.getErrCode().equals(RestCode.THRIFT_EMPTY.getErrCode())) {
				reqParams.clear();
				// exist true标识次区域存在 false标识不存在
				reqParams.put("exist", false);
				return RestResult.getSuccess(reqParams);
			}
			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		JSONObject resultObject = JSONObject.parseObject(resultJSONString);
		// 执行SQL获取数据
		String sql = resultObject.getString("sql");
		System.err.println("板块下钻SQL:" + sql);
		Boolean isCustom = (Boolean) reqParams.get("isCustom");
		List<Object> lists = new ArrayList<Object>();
		List<Map<String, Object>> results = sqlCacheService.blockOrgTarget(reqParams, sql);
		int size = 0;// 列
		if (!isCustom) {
			// 获取头部
			JSONArray head = resultObject.getJSONArray("head");
			lists.add(head);
			size = head.size();
		} else {
			if (results != null && !results.isEmpty()) {
				// 自定义获取头部
				Map<String, Object> headMap = results.get(0);// 表头
				size = headMap.keySet().size();
				Object head[] = new Object[size];
				for (int i = 0; i < size; i++) {
					Object val = headMap.get("VAL" + i);
					head[i] = val;
				}
				lists.add(head);
				results.remove(0);// 删除头部标题
			}
		}
		if (results != null && !results.isEmpty()) {
			for (Map<String, Object> map : results) {
				List<Object> items = new ArrayList<Object>();
				Object orgCode = map.get("P_VAL");// 获取机构代码
				if (orgCode.equals("1")) {
					// 总公司
					continue;
				}
				// 处理北京特殊情况
				if (reqParams.get("roleOrg").equals("101") && orgCode.equals("101")) {
					orgCode = "10101";
				}
				for (int i = 0; i < size; i++) {
					Map<String, Object> item = new HashMap<String, Object>();
					Object val = map.get("VAL" + i);
					if (StringUtils.isEmpty(val))
						val = "--";
					if (i == 0) {
						item.put("value", orgCode);
						item.put("name", val);
					} else {
						item.put("value", val);
					}
					items.add(item);
				}
				lists.add(items);
			}
		}
		// 复用
		reqParams.clear();
		// exist true标识次区域存在 false标识不存在
		reqParams.put("exist", true);
		reqParams.put("isChart", resultObject.getBoolean("isChart"));
		reqParams.put("isServer", resultObject.getBoolean("isServer"));
		reqParams.put("hasThreeOrg", resultObject.getBoolean("hasThreeOrg"));
		reqParams.put("hasFourOrg", resultObject.getBoolean("hasFourOrg"));
		reqParams.put("drilldown", resultObject.getBoolean("drilldown"));
		reqParams.put("list", lists);
		return RestResult.getSuccess(reqParams);
	}

	@Override
	public RestResult getTrendBase(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getBaseTrend(reqParamsJSON);
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

			log.error("thrift 获取的数据为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
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

		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult getTrendCommon(Map<String, Object> reqParams) {
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
				dto = client.getCoreTrend(reqParamsJSON);

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

				List<Map<String, Object>> targetMaps = sqlCacheService.getTrendCommon(reqParams, sql);
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

	private List<Map<String, Object>> getTrendMainTargets(Map<String, Object> reqParams, Client client,
			ResponseBeanDto dto, List<String> checkedTargets) throws TException {
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

	@Override
	public RestResult getTrendMain(Map<String, Object> reqParams) {
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
			List<Map<String, Object>> targets = this.getTrendMainTargets(reqParams, client, dto, checkedTargets);
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

			for (int i = 0, len = checkedTargets.size(); i < len; i++) {
				// 获取code
				String targetCode = checkedTargets.get(i);
				String subCode = targetCode.substring(0, 3);
				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				dto = client.getMainTrend(reqParamsJSON);

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
				logInfo("一级指标区域", targetCode, sql.toString());
				if (StringUtils.isEmpty(sql)) {
					seriesMap.remove("sql");
					seriesMap.put("data", Collections.EMPTY_LIST);
					log.warn("[{}]指标的SQL数据为空", targetCode);
					continue;
				}
				List<Map<String, Object>> results = sqlCacheService.getTrendMain(reqParams, sql.toString());
				if (results == null || results.isEmpty()) {
					seriesMap.remove("sql");
					seriesMap.put("data", Collections.EMPTY_LIST);
					log.warn("[{}]指标SQL获取的数据为空", targetCode);
					continue;
				}
				seriesMap.remove("sql");
				List<List<Object>> data = getTrendTargetData(results, groupbyDate);
				// data = dealTargetTimeData(groupbyDate, data);
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
					dto = client.getMainTrend(reqParamsJSON);
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
							seriesMap.remove("sql");
							seriesMap.put("data", Collections.EMPTY_LIST);
							log.warn("从thrift获取的[{}]指标数据为空", targetCode);
						}
						Object sql = seriesMap.get("sql");
						if (StringUtils.isEmpty(sql)) {
							seriesMap.remove("sql");
							seriesMap.put("data", Collections.EMPTY_LIST);
							log.warn("[{}]指标的SQL数据为空", targetCode);
						}
						List<Map<String, Object>> results = sqlCacheService.getTrendMain(reqParams, sql.toString());
						if (results == null || results.isEmpty()) {
							seriesMap.remove("sql");
							seriesMap.put("data", Collections.EMPTY_LIST);
							log.warn("[{}]指标SQL获取的数据为空", targetCode);
						}
						seriesMap.remove("sql");
						List<List<Object>> data = getTrendTargetData(results, groupbyDate);
						// data = dealTargetTimeData(groupbyDate, data);
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
	 * 获取二级指标分组和二级指标
	 * 
	 * @param reqParams
	 * @param client
	 * @param level
	 * @param target
	 * @return
	 * @throws TException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, List<Map<String, Object>>> getTrendSubTargets(ResponseBeanDto dto, String reqParamsJSON,
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

		Map<String, List<Map<String, Object>>> rest = JSON.parseObject(targetsJSONString, Map.class);
		// 获取二级指标分组
		List<Map<String, Object>> levels = rest.get("levels");
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

		List<Map<String, Object>> target2 = rest.get("targets");
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
	public RestResult getTrendSub(Map<String, Object> reqParams) {
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

			// 默认二级指标
			List<String> targets = JSON.parseArray(JSON.toJSONString(reqParams.get("target2")), String.class);

			// 不能为空
			if (targets == null)
				targets = new ArrayList<String>();

			// 获取二级指标和二级指标组
			Map<String, List<Map<String, Object>>> rest = getTrendSubTargets(dto, reqParamsJSON, client, level,
					targets);

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
				dto = client.getSubsTrend(reqParamsJSON);

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

				List<Map<String, Object>> results = sqlCacheService.getTrendSub(reqParams, sql.toString());
				if (results == null || results.isEmpty()) {
					_targetData.remove("sql");
					_targetData.put("data", Collections.EMPTY_LIST);

					log.error("从thrift获取的SQL获取的数据为空,指标代码为{}", targetCode);

					continue;
				}

				List<List<Object>> data = getTrendTargetData(results, groupbyDate);
				// data = dealTargetTimeData(groupbyDate, data);
				data = this.isBfbTargetDeal(targetCode.substring(0, 3), groupbyDate, data);
				_targetData.remove("sql");
				_targetData.put("data", data);

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
	public RestResult getTrendDist(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getDistTrend(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());
			if (dto.getErrCode().equals("E00001")) {
				return RestResult.getError(RestCode.EMPTY);
			}
			
			if (dto.getErrCode().equals(RestCode.THRIFT_EMPTY.getErrCode())) {
				return RestResult.getSuccess(Collections.EMPTY_MAP);
			}

			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {

			log.error("从thrift获取的指标排名数据为空");

			return RestResult.getError(RestCode.EMPTY);

		}

		@SuppressWarnings("unchecked")
		Map<String, Object> data = JSON.parseObject(resultJSONString, Map.class);

		Object seriesObj = data.get("series");
		if (StringUtils.isEmpty(seriesObj)) {
			log.error("从thrift获取的指标排名数据中series为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		@SuppressWarnings("rawtypes")
		List<Map> series = JSON.parseArray(JSON.toJSONString(seriesObj), Map.class);

		if (series == null || series.isEmpty()) {
			log.error("从thrift获取的指标排名数据中series为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> serie = series.get(0);
		Object sql = serie.get("data");
		if (StringUtils.isEmpty(sql)) {
			log.error("从thrift获取的指标排名数据中缺少SQL语句");

			return RestResult.getError(RestCode.EMPTY);
		}

		List<String> xAxis = new ArrayList<String>();

		List<Map<String, Object>> results = sqlCacheService.getTrendDist(reqParams, sql.toString());

		handleDistData(results, xAxis, serie);

		data.put("xAxis", xAxis);
		data.put("series", series);
		return RestResult.getSuccess(data);
	}

	/**
	 * 处理dist区域的数据
	 * 
	 * @param results
	 * @param xAxis
	 * @param serie
	 */
	private void handleDistData(List<Map<String, Object>> results, List<String> xAxis, Map<String, Object> serie) {
		if (results == null || results.isEmpty()) {
			serie.put("data", Collections.EMPTY_LIST);
			return;
		}
		List<Object> data = new ArrayList<Object>();
		for (Map<String, Object> map : results) {

			Map<String, Object> item = new HashMap<String, Object>();

			Object code = map.get("P_VAL");
			Object x = map.get("X_VAL");
			Object y = map.get("Y_VAL");

			item.put("code", code);
			item.put("y", Double.parseDouble(y.toString()));

			xAxis.add(x.toString());
			data.add(item);

		}
		serie.put("data", data);
	}

	@Override
	public RestResult getTrendOrg(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			// 北京特殊处理
			Object roleOrg = reqParams.get("roleOrg");
			Object distOrg = reqParams.get("distOrg");
			if (roleOrg.equals("101") && distOrg.equals("101")) {
				reqParams.put("distOrg", "10101");
			}
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getOrgsTrend(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());
			if (dto.getErrCode().equals(RestCode.THRIFT_EMPTY.getErrCode())) {
				return RestResult.getSuccess(Collections.EMPTY_MAP);
			}
			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());
			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {
			log.warn("thrift返回的数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		List<Object> restList = new ArrayList<Object>();
		JSONArray resultJSONArray = JSON.parseArray(resultJSONString);
		if (resultJSONArray == null || resultJSONArray.isEmpty()) {
			log.warn("解析后数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		Map<String, Object> responseParams = new HashMap<String, Object>();
		// 标题
		JSONArray heads = resultJSONArray.getJSONArray(0);
		restList.add(heads);
		// 解析SQL语句
		if (resultJSONArray.size() > 1) {
			// 说明有body部分
			JSONObject jsonObject = resultJSONArray.getJSONObject(1);
			String sql = jsonObject.getString("sql");
			String name = jsonObject.getString("title");
			responseParams.put("name", name);
			if (!StringUtils.isEmpty(sql)) {
				List<Map<String, Object>> results = sqlCacheService.getTrendOrg(reqParams, sql);
				if (results != null && !results.isEmpty()) {
					// 根据head解析数据
					int size = heads.size();
					for (Map<String, Object> map : results) {
						Object[] item = new Object[size];
						for (int i = 0; i < size; i++) {
							Object val = map.get("VAL" + i);
							item[i] = val == null ? "--" : val;
						}
						restList.add(item);
					}
				}
			}
		}
		responseParams.put("list", restList);
		resultJSONArray = null;
		return RestResult.getSuccess(responseParams);
	}

	@Override
	public RestResult getTrendTorg(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			// 北京特殊处理
			Object roleOrg = reqParams.get("roleOrg");
			Object distOrg = reqParams.get("distOrg");
			if (roleOrg.equals("101") && distOrg.equals("101")) {
				reqParams.put("distOrg", "10101");
			}
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getTorgTrend(reqParamsJSON);
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
			log.warn("thrift返回的数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		List<Object> restList = new ArrayList<Object>();
		JSONArray resultJSONArray = JSON.parseArray(resultJSONString);
		if (resultJSONArray == null || resultJSONArray.isEmpty()) {
			log.warn("解析后数据为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		Map<String, Object> responseParams = new HashMap<String, Object>();
		// 标题
		JSONArray heads = resultJSONArray.getJSONArray(0);
		restList.add(heads);
		// 解析SQL语句
		if (resultJSONArray.size() > 1) {
			// 说明有body部分
			JSONObject jsonObject = resultJSONArray.getJSONObject(1);
			String sql = jsonObject.getString("sql");
			String name = jsonObject.getString("title");
			responseParams.put("name", name);
			if (!StringUtils.isEmpty(sql)) {
				List<Map<String, Object>> results = sqlCacheService.getTrendOrg(reqParams, sql);
				if (results != null && !results.isEmpty()) {
					// 根据head解析数据
					int size = heads.size();
					for (Map<String, Object> map : results) {
						Object[] item = new Object[size];
						for (int i = 0; i < size; i++) {
							Object val = map.get("VAL" + i);
							item[i] = val == null ? "--" : val;
						}
						restList.add(item);
					}
				}
			}
		}
		responseParams.put("list", restList);
		resultJSONArray = null;
		return RestResult.getSuccess(responseParams);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public RestResult getTrendSpec(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);

			List<String> targets = getTrendSpecTargets(client, dto, reqParamsJSON);
			if (targets == null || targets.isEmpty()) {
				return RestResult.getError(RestCode.EMPTY);
			}

			List<Object> restList = new ArrayList<Object>();

			for (String targetCode : targets) {
				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				dto = client.getSpecTrend(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {
					log.error("[{}]指标出现异常,{}", dto.getErrMsgs());
					continue;
				}

				String resultJSONString = dto.getJson();
				if (StringUtils.isEmpty(resultJSONString)) {
					log.error("从thrift获取的指标[{}]数据为空", targetCode);

					continue;
				}
				Map<String, Object> itemMap = JSON.parseObject(resultJSONString, Map.class);

				restList.add(itemMap);

				itemMap.put("xAxis", null);

				Object seriesObj = itemMap.get("series");
				if (seriesObj == null) {
					log.error("指标数据缺少series参数,[{}]", targetCode);
					continue;
				}
				List<Map> series = JSON.parseArray(JSON.toJSONString(seriesObj), Map.class);
				itemMap.put("series", series);

				Map<String, Object> serie = series.get(0);

				Object sqlObj = serie.get("sql");
				String type = serie.get("type").toString();
				if (StringUtils.isEmpty(sqlObj)) {
					log.error("指标数据缺少SQL,[{}]", targetCode);
					serie.remove("sql");
					continue;
				}

				List<Map<String, Object>> results = sqlCacheService.getTrendSpec(reqParams, sqlObj.toString());

				if (results == null || results.isEmpty()) {
					serie.put("data", Collections.EMPTY_LIST);
					serie.remove("sql");
					log.warn("特殊区域SQL获取的数据为空,[{}]", targetCode);

					continue;
				}
				serie.remove("sql");
				// 获取特殊区域的数据
				serie.put("data", getTrendSpecData(results, type));
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
	 * 获取特殊区域的数据
	 * 
	 * @param results
	 * @return
	 */
	private List<Object> getTrendSpecData(List<Map<String, Object>> results, String type) {
		List<Object> data = new ArrayList<Object>();
		// 根据图形类型处理
		type = type.toLowerCase();// scatter pie
		if (type.equals(Consts.SCATTER)) {
			// 散点图
			for (Map<String, Object> item : results) {
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
			for (Map<String, Object> item : results) {
				String x = item.get("X_VAL").toString();
				String y = item.get("Y_VAL").toString();

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
	private List<String> getTrendSpecTargets(Client client, ResponseBeanDto dto, String reqParamsJSON)
			throws TException {

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

	@Override
	public RestResult setTrendDef(Map<String, Object> reqParams) {
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
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());

			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		String resultJSONString = dto.getJson();
		return RestResult.getSuccess(resultJSONString);
	}

	@Override
	public RestResult getTrendRegion(Map<String, Object> reqParams) {
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
			if (dto.getErrCode().equals("E00001")) {
				return RestResult.getError(RestCode.EMPTY);
			} else {
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
					Map<String, Object> tarMap = new HashMap<>();
					JSONObject col = JSON.parseObject(colObj.toString());
					// String name = col.getString("name");
					String sql = col.getString("sql");
					List<Map<String, Object>> lists = sqlCacheService.getTrendRegion(reqParams, sql);
					// 处理值
					Object name = (lists == null || lists.isEmpty()) ? "" : lists.get(0).get("VAL0");
					Object value = (lists == null || lists.isEmpty()) ? "" : lists.get(0).get("VAL1");
					tarMap.put("name", name.toString());
					tarMap.put("value", value.toString());
					lestList.add(tarMap);
				}
				return RestResult.getSuccess(lestList);
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
	}

	@Override
	public RestResult getTrendOrgGet(Map<String, Object> reqParams) {
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
				if (dto.getSuccess().equals("false")) {
					log.error(dto.getErrMsgs());
					return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
				}
				return RestResult.getSuccess(JSONObject.parseObject(dto.json));
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
	public RestResult getTrendOrgCompare(Map<String, Object> reqParams) {
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
			if (dto.getSuccess().equals("true")) {
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
					logInfo("机构对比区域", reqParams.get("target").toString(), sql);
					reqParams.put("compareOrg", newOrgs.get(i));
					List<Map<String, Object>> lists = sqlCacheService.getTrendCompare(reqParams, sql);
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
			} else {
				return RestResult.getError(RestCode.EMPTY);
			}
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
		return RestResult.getSuccess(returnList);
	}

	@Override
	public RestResult getTrendReportList(Map<String, Object> reqParams) {
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
				logInfo("导出清单区域", reqParams.get("blockCode").toString(), sql);
				List<Map<String, Object>> lists = sqlCacheService.getTrendReportList(reqParams, sql);
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
		if (dto.getSuccess().equals("false")) {
			log.error(dto.getErrMsgs());

			return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
		}
		return RestResult.getSuccess(resultMap);
	}

	@Override
	public RestResult getTrendReportDownload(Map<String, Object> reqParams, HttpServletRequest request,
			HttpServletResponse response) {
		String realname = "";
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
		}
		ResponseBeanDto dto = null;
		String resultJSONString = "";
		JSONObject resultMap;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.exportBill(reqParamsJSON);
			resultJSONString = dto.getJson();
			resultMap = JSON.parseObject(resultJSONString);
			// 列表明细
			String sql = resultMap.getString("sql");
			List<Map<String, Object>> lists = sqlCacheService.getTrendReportList(reqParams, sql);
			List<Map<String, Object>> targetMaps = new ArrayList<>();
			if (lists != null && !lists.isEmpty()) {
				for (Map<String, Object> item : lists) {
					Map<String, Object> items = new HashMap<>();
					Set<String> keys = lists.get(0).keySet();
					for (int i = 0, len = keys.size(); i < len; i++) {
						items.put("VAL" + i, item.get("VAL" + i) == null ? "" : item.get("VAL" + i));
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
				Object[] titleArray = titleMap.values().toArray();
				Object[] fieldArray = titleMap.keySet().toArray();
				realname = ExcelUtils.getFileNameUrl("板块清单列表", response, titleArray, fieldArray,
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

	@Override
	public RestResult blockMyApi(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.myBlockList(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}
		String resultJSONString = dto.getJson();
		if (StringUtils.isEmpty(resultJSONString)) {

			log.warn("数据为空");

			return RestResult.getError(RestCode.EMPTY);
		}
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult blockMyDelete(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.deleteBlock(reqParamsJSON);
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
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult blockMyEditTarget(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getTarget(reqParamsJSON);
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
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult blockMyEditSave(Map<String, Object> reqParams) {

		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			Object blockId = reqParams.get("blockId");
			String reqParamsJSON = JSON.toJSONString(reqParams);
			if (StringUtils.isEmpty(blockId)) {
				dto = client.saveBlock(reqParamsJSON);

			} else {
				dto = client.editBlock(reqParamsJSON);
			}
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
		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		return RestResult.getSuccess(resultJSONObject);
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
	public RestResult blockOrgSearch(Map<String, Object> reqParams) {
		String orgKey = reqParams.get("orgKey").toString();
		List<OrgBean> orgBeanList = new ArrayList<OrgBean>();
		if (!StringUtils.isEmpty(orgKey)) {
			String roleOrg = reqParams.get("roleOrg").toString();
			Boolean hasThreeOrg = (Boolean) reqParams.get("hasThreeOrg");
			Boolean hasFourOrg = (Boolean) reqParams.get("hasFourOrg");
			Integer type = 0;
			if (hasThreeOrg) {
				type = 1;
			} else if (hasFourOrg) {
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
