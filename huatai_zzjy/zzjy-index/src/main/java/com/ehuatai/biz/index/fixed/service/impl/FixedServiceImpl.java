package com.ehuatai.biz.index.fixed.service.impl;

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

import com.alibaba.druid.support.calcite.DDLColumn;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.domain.Chart;
import com.ehuatai.biz.domain.ChartSerie;
import com.ehuatai.biz.domain.OrgBean;
import com.ehuatai.biz.index.fixed.service.FixedSQLCacheService;
import com.ehuatai.biz.index.fixed.service.FixedService;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.ZyfwService;
import com.ehuatai.thrift.ZyfwService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.ehuatai.util.CommonUtil;
import com.ehuatai.util.DateUtil;
import com.ehuatai.util.DateUtils;

@Service
public class FixedServiceImpl implements FixedService {

	private Map orgMap;
	private Map deptMap;

	public FixedServiceImpl() {
		orgMap = new HashMap();
		orgMap.put("101", "北京");
		orgMap.put("102", "浙江");
		orgMap.put("103", "四川");
		orgMap.put("104", "江苏");
		orgMap.put("105", "上海105");
		orgMap.put("106", "山东");
		orgMap.put("107", "河南");
		orgMap.put("108", "福建");
		orgMap.put("109", "湖南");
		orgMap.put("110", "广东");
		orgMap.put("111", "江西");
		orgMap.put("112", "内蒙古");
		orgMap.put("113", "湖北");
		orgMap.put("114", "河北");
		orgMap.put("115", "安徽");
		orgMap.put("116", "辽宁");
		orgMap.put("117", "黑龙江");
		deptMap = new HashMap();
		deptMap.put("trade", "130107");
		deptMap.put("premium", "130105");
		deptMap.put("org", "130101");
		deptMap.put("train", "101402");
	}

	@Autowired
	private FixedSQLCacheService fixedSQLCacheServiceImpl;

	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private SQLMapper sqlMapper;

	private Logger log = LoggerFactory.getLogger(getClass());

	private Client getClient() {
		Client client = null;
		try {
			client = ThriftUtil.getService(ZyfwService.Client.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public RestResult fixed(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.fixedMenu(reqParamsJSON);
		} catch (TException e) {
			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}

		String resultJSONString = dto.getJson();

		if (StringUtils.isEmpty(resultJSONString)) {

			log.warn("fixed接口 thrift 获取的结果为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		JSONObject resultJSONObject = JSON.parseObject(resultJSONString);

		// 获取当前用户的角色
		String roleOrg = reqParams.get("roleOrg").toString();
		// 根据当前角色获取下级机构
		List<OrgBean> nextOrgBeanList = new ArrayList<OrgBean>();
		List<HashMap<String, Object>> orgMapList = null;
		switch (roleOrg.length()) {
		case 1:
			// 总公司
			orgMapList = this.getOrgList(1, roleOrg);
			break;
		case 3:
			// 分公司
			orgMapList = this.getOrgList(2, roleOrg);
			break;
		case 5:
			// 中支
			// 判断是成本中心还是营销服务部、
			if (new CommonUtil().isGeYeAndPeiXunDept(deptMap.get(reqParams.get("dept").toString()).toString())) {
				orgMapList = this.getOrgList(31, roleOrg);
			} else {
				orgMapList = this.getOrgList(32, roleOrg);
			}
			break;
		case 10:
			// 营销服务部
			// 判断是成本中心还是营销服务部、
			if (new CommonUtil().isGeYeAndPeiXunDept(deptMap.get(reqParams.get("dept").toString()).toString())) {
				orgMapList = this.getOrgList(42, roleOrg);
			} else {
				// orgMapList = this.getOrgList(42, roleOrg);
				orgMapList = null;
			}
			break;
		}
		if (null != orgMapList) {
			for (HashMap<String, Object> map : orgMapList) {
				String orgCode = (String) map.get("CODE");
				String orgName = (String) map.get("NAME");
				OrgBean nextOrg = new OrgBean();
				nextOrg.setName(orgName);
				nextOrg.setCode(orgCode);
				nextOrgBeanList.add(nextOrg);
			}
			// 全部机构装入
			OrgBean nextOrg = new OrgBean();
			nextOrg.setName("全部");
			nextOrg.setCode("ALL-" + roleOrg);
			nextOrgBeanList.add(0, nextOrg);
			resultJSONObject.put("date", nextOrgBeanList);
		} else {
			OrgBean nextOrg = new OrgBean();
			nextOrg.setName("全部");
			nextOrg.setCode("ALL-" + roleOrg);
			nextOrgBeanList.add(0, nextOrg);
			// resultJSONObject.put("date", new ArrayList<>());
			resultJSONObject.put("date", nextOrgBeanList);
		}
		resultJSONObject.put("endTime",DateUtils.getYesterDay());
		return RestResult.getSuccess(resultJSONObject);
	}

	/**
	 * 获取当前区域的所有的角色
	 * 
	 * @param reqParams
	 * @param client
	 * @return
	 * @throws TException
	 */
	private JSONArray getTargets(Map<String, Object> reqParams, Client client) throws TException {
		// 获取区域标识

		String reqParamsJSON = JSON.toJSONString(reqParams);
		// 获取所有的指标
		ResponseBeanDto targetsDto = client.getTarget(reqParamsJSON);

		String targetsJSONString = targetsDto.getJson();
		if (StringUtils.isEmpty(targetsJSONString)) {
			log.warn(targetsDto.getErrMsgs());
			return null;
		}
		return JSONArray.parseArray(targetsJSONString);
	}

	@Override
	public RestResult fixedMain(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		try {
			String reqParamsJSON = null;

			ResponseBeanDto dto = null;

			// 所有的数据集合
			List<Chart> charts = new ArrayList<Chart>();

			// 获取所有的指标代码
			JSONArray targetsArray = this.getTargets(reqParams, client);
			if (targetsArray == null) {
				return RestResult.getError(RestCode.EMPTY);
			}
			Boolean isEmpty = true;
			for (int i = 0, len = targetsArray.size(); i < len; i++) {
				String targetCode = targetsArray.getString(i);
				// 添加指标代码参数
				reqParams.put("targetCode", targetCode);
				reqParamsJSON = JSON.toJSONString(reqParams);
				// 获取当前指标下的指标数据
				dto = client.getCoreData(reqParamsJSON);
				if (dto.getSuccess().equals("false") && dto.getErrCode().equals("E00001")) {
					log.warn("固定指标核心区域" + targetCode + "获取thrift数据为空");
				} else {
					isEmpty = false;
					String resultJSONString = dto.getJson();
					if (StringUtils.isEmpty(resultJSONString)) {
						log.warn("[{}]{}", targetCode, "fixed接口 thrift 获取的结果为空");
						continue;
					}
					Chart chart = JSONObject.parseObject(resultJSONString, Chart.class);
					List<ChartSerie> series = chart.getSeries();
					if (series == null || series.isEmpty()) {
						log.warn("[{}]{}", targetCode, "series为空");
						continue;
					}
					for (ChartSerie serie : series) {
						Object sqlObj = serie.getData();
						if (sqlObj == null) {
							serie.setData(new String[] {});
						} else {
							Boolean drilldown = serie.getDrilldown();
							String dateType = serie.getDateType();
							// 根据SQL查询数据
							List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedMain(reqParams,
									sqlObj.toString());
							Object data = handleMainSQLData(lists, drilldown, dateType,
									reqParams.get("roleOrg").toString());// 数据处理
							serie.setData(data);
							serie.setDrilldown(null);
							serie.setDateType(null);
						}
					}
					charts.add(chart);
				}
			}
			if (isEmpty) {
				return RestResult.getError(RestCode.EMPTY);
			} else {
				return RestResult.getSuccess(charts);
			}
		} catch (TException e) {
			e.printStackTrace();
			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}

	}

	/**
	 * main区域SQL数据处理
	 * 
	 * @param lists
	 * @return
	 */
	private Object handleMainSQLData(List<Map<String, Object>> lists, Boolean drilldown, String dateType,
			String roleOrg) {
		List<Object> data = new ArrayList<Object>();

		if (lists == null || lists.isEmpty())
			return data;
		for (Map<String, Object> item : lists) {
			String code = "";
			Object pVal = item.get("P_VAL");
			if (pVal != null) {
				code = pVal.toString();
			}
			String name = dealOrgData(code, item.get("X_VAL").toString());
			// Object name = item.get("X_VAL");
			if (null != pVal && null != name) {
				double y = Double.parseDouble(item.get("Y_VAL").toString());
				item.clear();
				if (roleOrg.equals("101")) {
					item.put("code", "10101");
				} else {
					item.put("code", code);
				}
				item.put("name", name);
				item.put("y", y);
				item.put("dateType", dateType);
				item.put("drilldown", drilldown);
				data.add(item);
			}
		}
		return data;
	}

	@Override
	public RestResult fixedMainDrilldown(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.coreDataDown(reqParamsJSON);
			if (dto.getSuccess().equals("false")) {

				log.error(dto.getErrMsgs());

				return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
			}

			String resultJSONString = dto.getJson();

			if (StringUtils.isEmpty(resultJSONString)) {

				log.warn("fixed接口 thrift 获取的结果为空");

				return RestResult.getError(RestCode.EMPTY);
			}
//T02002
			JSONObject resultJSONObject = JSON.parseObject(resultJSONString);
			log.warn(resultJSONObject.toJSONString());
			String sql = resultJSONObject.getString("sql");			
			if (StringUtils.isEmpty(sql)) {
				resultJSONObject.remove("drilldown");
				resultJSONObject.remove("sql");
				resultJSONObject.put("data", new String[] {});
			} else {
				Object parentCode = reqParams.get("parent");
				Boolean drilldown = resultJSONObject.getBoolean("drilldown");
				List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedMainDrilldown(reqParams, sql);
				Object data = handleMainDrilldownSQLData(lists, drilldown, parentCode);// 处理SQL数据
				resultJSONObject.remove("sql"); // 将SQL删除
				resultJSONObject.remove("drilldown"); // 将drilldown删除
				resultJSONObject.put("data", data);
			}
			return RestResult.getSuccess(resultJSONObject);

		} catch (TException e) {
			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}

	}

	/**
	 * 处理下钻数据逻辑
	 * 
	 * @param lists
	 * @param drilldown
	 * @return
	 */
	private Object handleMainDrilldownSQLData(List<Map<String, Object>> lists, Boolean drilldown, Object parent) {
		List<Object> data = new ArrayList<Object>();
		if (lists == null || lists.isEmpty())
			return data;
		log.warn(lists.toString());
		for (Map<String, Object> item : lists) {
			// 获取值
			String code = null;
			String name = null;
			String xAix = null;
			if (item.containsKey("P_VAL")) {
				code = item.get("P_VAL").toString();
			}
			if (item.containsKey("Y_VAL")) {
				xAix = item.get("Y_VAL").toString();
			}
			if (item.containsKey("X_VAL")) {
				name = item.get("X_VAL").toString();
			}
			name = dealOrgData(code, name);
			// 清理，重新填值
			item.clear();
			if (null != parent && parent.equals("101")) {
				item.put("code", "10101");
			} else {
				item.put("code", code);
			}
			if (!StringUtils.isEmpty(xAix)) {
				item.put("y", Double.parseDouble(xAix));
			} else {
				item.put("y", null);
			}
			item.put("name", name);
			item.put("drilldown", drilldown);
			data.add(item);
		}
		return data;
	}

	@Override
	public RestResult fixedMcount(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		ResponseBeanDto dto = null;
		try {

			// 获取所有的指标代码
			JSONArray targetsArray = this.getTargets(reqParams, client);

			if (targetsArray == null) {
				return RestResult.getError(RestCode.EMPTY);
			}

			List<Object> results = new ArrayList<Object>();

			for (int i = 0, len = targetsArray.size(); i < len; i++) {
				String targetCode = targetsArray.getString(i);
				reqParams.put("targetCode", targetCode);

				String reqParamsJSON = JSON.toJSONString(reqParams);
				// 从thrift获取预处理数据
				dto = client.getMonthData(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {

					log.error(dto.getErrMsgs());

					return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
				}

				String resultJSONString = dto.getJson();

				if (StringUtils.isEmpty(resultJSONString)) {

					log.warn("fixed接口 thrift 获取的结果为空");

					return RestResult.getError(RestCode.EMPTY);
				}

				JSONObject dataObject = JSONObject.parseObject(resultJSONString);
				String sql = dataObject.getString("data");
				if (StringUtils.isEmpty(sql)) {
					dataObject.put("value", "");
					dataObject.remove("sql");
				} else {
					List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedMcount(reqParams, sql);
					Object data = handleMcountSQLData(lists);
					dataObject.put("value", data);
					dataObject.remove("sql");
				}
				dataObject.remove("data");
				results.add(dataObject);

			}
			return RestResult.getSuccess(results);

		} catch (TException e) {
			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}

	}

	/**
	 * 月度指标统计接口SQL数据处理
	 * 
	 * @param lists
	 * @return
	 */
	private Object handleMcountSQLData(List<Map<String, Object>> lists) {
		if (lists == null || lists.isEmpty())
			return "";
		Map<String, Object> item = lists.get(0);
		Object val = item.get("VAL");
		if (val == null)
			val = "";
		return val;
	}

	@Override
	public RestResult fixedMcountInfo(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		String resultJSONString = "";
		List<Map<String, Object>> seriesList = new ArrayList<>();
		Map<String, Object> dataMap = new HashMap<>();
		String title = "";
		try {
			String jyfxStr = reqParams.get("target").toString();
			String[] jyfxArray = jyfxStr.split(",");
			List<String> xAxis = null;
			for (String jyfxTar : jyfxArray) {
				reqParams.put("jyfx", jyfxTar);
				String reqParamsJSON = JSON.toJSONString(reqParams);
				dto = client.monthDataInfo(reqParamsJSON);
				resultJSONString = dto.getJson();
				JSONObject dataObject = JSONObject.parseObject(resultJSONString);
				if (StringUtils.isEmpty(title)) {
					title = dataObject.getString("title");
				}
				Map<String, Object> seMap = new HashMap<>();
				String sql = dataObject.getString("sql");
				String type = dataObject.getString("type");
				seMap.put("type", type);
				seMap.put("name", dataObject.getString("name"));
				seMap.put("code", dataObject.getString("code"));
				seMap.put("unit", dataObject.getString("unit"));
				seMap.put("stack", dataObject.getString("stack"));
				seMap.put("color", dataObject.getString("color"));
				dataObject.clear();
				List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedMcountInfo(reqParams, sql);
				List<Object> data = new ArrayList<>();
				if ("pie".equalsIgnoreCase(type)) {
					for (Map<String, Object> list : lists) {
						List<Object> objList = new ArrayList<>();
						Object key = (lists == null || lists.isEmpty()) ? "" : list.get("VAL0");
						Object val = (lists == null || lists.isEmpty()) ? "" : list.get("VAL1");
						objList.add(key.toString());
						objList.add(Double.parseDouble(val.toString()));
						data.add(objList);
					}
				} else {
					xAxis = new ArrayList<>();
					for (Map<String, Object> list : lists) {
						Object x = (lists == null || lists.isEmpty()) ? "" : list.get("X_VAL");
						Object v = (lists == null || lists.isEmpty()) ? "" : list.get("Y_VAL");
						xAxis.add(x.toString());
						data.add(Double.parseDouble(v.toString()));
					}
				}
				seMap.put("data", data);
				seriesList.add(seMap);
			}
			dataMap.put("name", title);
			dataMap.put("stacking", "");
			dataMap.put("xAxis", xAxis);
			dataMap.put("series", seriesList);
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
		if (StringUtils.isEmpty(dataMap)) {
			log.warn("fixed接口 thrift 获取的结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(dataMap);
	}

	@Override
	public RestResult fixedMcountRanking(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getRankData(reqParamsJSON);
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

			log.warn("fixed接口 thrift 获取的结果为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		JSONObject resultJSONObject = JSONObject.parseObject(resultJSONString);
		// 地图数据
		String mapSql = resultJSONObject.getString("map");

		if (StringUtils.isEmpty(mapSql)) {
			resultJSONObject.put("map", new String[] {});
		} else {
			List<Map<String, Object>> mapLists = fixedSQLCacheServiceImpl.fixedMcountRankingMap(reqParams, mapSql);
			if (mapLists == null || mapLists.isEmpty()) {
				resultJSONObject.put("map", new String[] {});
			} else {
				List<Object> data = new ArrayList<Object>();
				for (Map<String, Object> item : mapLists) {
					double complete = Double.parseDouble(item.get("Y_VAL").toString());
					String code = item.get("X_VAL").toString();
					item.clear();
					item.put("value", complete);
					item.put("code", orgMap.get(code).toString());
					data.add(item);
				}
				resultJSONObject.put("map", data);
			}
		}

		// 光荣榜数据
		String honorSql = resultJSONObject.getString("honor");
		if (StringUtils.isEmpty(honorSql)) {
			resultJSONObject.put("honor", new String[] {});
		} else {
			List<Map<String, Object>> honorLists = fixedSQLCacheServiceImpl.fixedMcountRankingHonor(reqParams,
					honorSql);
			if (honorLists == null || honorLists.isEmpty()) {
				resultJSONObject.put("honor", new String[] {});
			} else {
				List<Object> data = new ArrayList<Object>();
				for (Map<String, Object> item : honorLists) {
					String no = item.get("VAL0").toString();
					String name = item.get("VAL1").toString();
					String detail = item.get("VAL2").toString();
					String complete = item.get("VAL3").toString();

					item.clear();

					if (StringUtils.isEmpty(complete) || complete.equals("--")) {
						item.put("complete", 0);
					} else {
						item.put("complete", complete);
					}

					item.put("no", no);
					item.put("name", name);
					item.put("detail", detail);

					data.add(item);
				}
				resultJSONObject.put("honor", data);

			}
		}

		// 排行榜数据
		String rankSql = resultJSONObject.getString("rank");
		if (StringUtils.isEmpty(rankSql)) {
			resultJSONObject.put("rank", new String[] {});
		} else {
			List<Map<String, Object>> rankLists = fixedSQLCacheServiceImpl.fixedMcountRankingRank(reqParams, rankSql);
			if (rankLists == null || rankLists.isEmpty()) {
				resultJSONObject.put("rank", new String[] {});
			} else {
				List<Object> data = new ArrayList<Object>();

				for (Map<String, Object> item : rankLists) {
					String complete = item.get("Y_VAL").toString();
					String name = item.get("X_VAL").toString();

					item.clear();

					item.put("complete", complete);
					item.put("name", name);

					data.add(item);
				}

				resultJSONObject.put("rank", data);
			}
		}
		return RestResult.getSuccess(resultJSONObject);
	}

	@Override
	public RestResult fixedCore(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		ResponseBeanDto dto = null;
		try {
			JSONArray targetsArray = this.getTargets(reqParams, client);
			if (targetsArray == null) {

				return RestResult.getError(RestCode.EMPTY);

			}

			List<Object> results = new ArrayList<Object>();

			// 单个指标获取SQL
			for (int i = 0, len = targetsArray.size(); i < len; i++) {

				// 获取指标代码
				String targetCode = targetsArray.getString(i);
				reqParams.put("targetCode", targetCode);

				String reqParamsJSON = JSON.toJSONString(reqParams);
				// 从Thrift获取预处理数据
				dto = client.getMonthList(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {

					log.error(dto.getErrMsgs());

					return RestResult.getError(RestCode.THRIFT_CLIENT_RESULT_EXCEPTION);
				}

				String resultJSONString = dto.getJson();

				if (StringUtils.isEmpty(resultJSONString)) {

					log.warn("fixed接口 thrift 获取的结果为空");

					return RestResult.getError(RestCode.EMPTY);
				}
				JSONArray dataArray = JSONArray.parseArray(resultJSONString);
				if (dataArray == null || dataArray.isEmpty()) {
					return RestResult.getError(RestCode.EMPTY);
				}

				// 获取标题
				JSONArray title = dataArray.getJSONArray(0);
				results.add(title);

				if (dataArray.size() > 1) {
					// 获取数据
					JSONArray sqlArrray = dataArray.getJSONArray(1);
					JSONObject sqlObject = sqlArrray.getJSONObject(0);
					String sql = sqlObject.getString("data");
					if (StringUtils.isEmpty(sql)) {
						log.warn("列表的SQL语句不存在");
					} else {
						List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedCore(reqParams, sql);
						if (lists == null || lists.isEmpty()) {
							log.warn("列表SQL获取数据为空");
						} else {
							int cols = title.size();// 获取所有的列数

							dataArray.remove(1);// 移除SQL语句

							// for (Map<String, Object> item : lists) {
							for (int j = 0; j < lists.size(); j++) {
								Map<String, Object> item = lists.get(j);
								String[] items = new String[cols];
								for (int k = 0; k < cols; k++) {
									Object val = item.get("VAL" + k);
									if (val == null) {
										items[k] = "--";
									} else {
										items[k] = val.toString();
									}
								}
								results.add(items);
							}

						}
					}
				}
			}
			return RestResult.getSuccess(results);

		} catch (TException e) {
			e.printStackTrace();

			return RestResult.getError(RestCode.THRIFT_CLIENT_UNKNOW_RESULT);
		} finally {
			ThriftUtil.close(client);
		}

	}

	@Override
	public RestResult fixedNotice(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.listNotice(reqParamsJSON);
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

			log.warn("fixed接口 thrift 获取的结果为空");

			return RestResult.getError(RestCode.EMPTY);
		}
		JSONArray resultArray = JSONArray.parseArray(resultJSONString);

		if (resultArray == null || resultArray.isEmpty()) {
			log.warn("公告列表为空");

			return RestResult.getError(RestCode.EMPTY);
		}

		for (int i = 0, len = resultArray.size(); i < len; i++) {

			JSONObject dataObject = resultArray.getJSONObject(i);
			String desc = dataObject.getString("content");
			String id = dataObject.getString("noticeId");
			String title = dataObject.getString("title");
			String date = dataObject.getString("createTime");

			dataObject.clear();
			if (!StringUtils.isEmpty(date)) {
				Long time = DateUtil.getTime(date, "yyyy-MM-dd HH:mm:ss");
				date = DateUtil.getDate(time, "yyyy-MM-dd");
			}
			dataObject.put("desc", desc);
			dataObject.put("id", id);
			dataObject.put("title", title);
			dataObject.put("date", date);

		}

		return RestResult.getSuccess(resultArray);
	}

	@Override
	public RestResult fixedNoticeDetail(Map<String, Object> reqParams) {
		Client client = getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}

		ResponseBeanDto dto = null;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.lookNotice(reqParamsJSON);
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

			log.warn("fixed接口 thrift 获取的结果为空");

			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(JSON.parse(resultJSONString));
	}

	/**
	 * @功能 {查询机构}
	 * @作者 MaxBill
	 * @时间 2017年9月11日 下午1:08:11
	 */
	public List<HashMap<String, Object>> getOrgList(int type, String code) {
		List<HashMap<String, Object>> mapData = null;
		switch (type) {
		case 1:
			mapData = this.baseMapper.getCompanyByManage(code);
			break;
		case 2:
			mapData = this.sqlMapper.getBranchByCompany(code);
			break;
		case 31:
			mapData = this.baseMapper.getServerByBranch(code);
			break;
		case 32:
			mapData = this.baseMapper.getCenterByBranch(code);
			break;
		case 41:
			mapData = this.baseMapper.getRegionByServer(code);
			break;
		case 42:
			mapData = this.baseMapper.getRegionByCenter(code);
			break;
		case 5:
			mapData = this.baseMapper.getSectionByRegion(code);
			break;
		case 6:
			mapData = this.baseMapper.getGroupsBySection(code);
			break;
		case 7:
			mapData = this.baseMapper.getSellersByGroups(code);
			break;
		}
		return mapData;
	}

	/**
	 * @功能 {特殊机构处理}
	 * @作者 MaxBill
	 * @时间 2017年9月11日 下午1:08:11
	 */
	public String dealOrgData(String orgCode, String orgNameOld) {
		String orgName = "";
		if (!StringUtils.isEmpty(orgCode)) {
			switch (orgCode) {
			case "11601":
				orgName = "沈阳一";
				break;
			case "11602":
				orgName = "沈阳二";
				break;
			case "11603":
				orgName = "沈阳三";
				break;
			}
		}

		if (StringUtils.isEmpty(orgName)) {
			orgName = orgNameOld;
		}
		return orgName;
	}
	public static void main(String[] args) {
	/*	List<Map<String, Object>> lists  = new  ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("P_VAL", "1090100021");
		map.put("Y_VAL", "100.00");
		map.put("X_VAL", "二十一区");
		lists.add(map);
		FixedServiceImpl fix = new FixedServiceImpl();
		fix.handleMainDrilldownSQLData(lists, true, "");*/
	}
}
