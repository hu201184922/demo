package com.ehuatai.biz.app.fixed.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.app.fixed.service.AppFixedService;
import com.ehuatai.biz.domain.Chart;
import com.ehuatai.biz.domain.ChartSerie;
import com.ehuatai.biz.domain.OrgBean;
import com.ehuatai.biz.index.fixed.service.FixedSQLCacheService;
import com.ehuatai.biz.mapper.BaseMapper;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.ZyfwService;
import com.ehuatai.thrift.ZyfwService.Client;
import com.ehuatai.thrift.util.ThriftUtil;

public class AppFixedServiceImpl implements AppFixedService {
	private Map orgMap;

	public AppFixedServiceImpl() {
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
			orgMapList = this.getOrgList(3, roleOrg);
			break;
		default:
			// 营销服务部
			orgMapList = this.getOrgList(4, roleOrg);
			break;
		}
		if (roleOrg.length() < 5) {
			for (HashMap<String, Object> map : orgMapList) {
				String orgCode = (String) map.get("CODE");
				String subName = (String) map.get("SUBNAME");
				OrgBean nextOrg = new OrgBean();
				nextOrg.setName(subName);
				nextOrg.setCode(orgCode);
				nextOrgBeanList.add(nextOrg);
			}
		} else {
			for (HashMap<String, Object> map : orgMapList) {
				String orgCode = (String) map.get("CODE");
				String orgName = (String) map.get("NAME");
				OrgBean nextOrg = new OrgBean();
				nextOrg.setName(orgName);
				nextOrg.setCode(orgCode);
				nextOrgBeanList.add(nextOrg);
			}
		}
		resultJSONObject.put("date", nextOrgBeanList);
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

			for (int i = 0, len = targetsArray.size(); i < len; i++) {
				String targetCode = targetsArray.getString(i);
				// 添加指标代码参数
				reqParams.put("targetCode", targetCode);

				reqParamsJSON = JSON.toJSONString(reqParams);

				// 获取当前指标下的指标数据
				dto = client.getCoreData(reqParamsJSON);

				if (dto.getSuccess().equals("false")) {

					log.error("[{}]{}", targetCode, dto.getErrMsgs());

					continue;
				}

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

						Object data = handleMainSQLData(lists, drilldown, dateType);// 数据处理

						serie.setData(data);
						serie.setDrilldown(null);
						serie.setDateType(null);
					}

				}

				charts.add(chart);

			}
			return RestResult.getSuccess(charts);

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
	private Object handleMainSQLData(List<Map<String, Object>> lists, Boolean drilldown, String dateType) {
		List<Object> data = new ArrayList<Object>();

		if (lists == null || lists.isEmpty())
			return data;
		for (Map<String, Object> item : lists) {
			String code = "";
			Object pVal = item.get("P_VAL");
			if (pVal != null) {
				code = pVal.toString();
			}
			String name = item.get("X_VAL").toString();
			double y = Double.parseDouble(item.get("Y_VAL").toString());

			item.clear();

			item.put("code", code);
			item.put("name", name);
			item.put("y", y);
			item.put("dateType", dateType);
			item.put("drilldown", drilldown);

			data.add(item);
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

			JSONObject resultJSONObject = JSON.parseObject(resultJSONString);
			String sql = resultJSONObject.getString("sql");
			if (StringUtils.isEmpty(sql)) {
				resultJSONObject.remove("drilldown");
				resultJSONObject.remove("sql");
				resultJSONObject.put("data", new String[] {});
			} else {
				Boolean drilldown = resultJSONObject.getBoolean("drilldown");
				List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedMainDrilldown(reqParams, sql);
				Object data = handleMainDrilldownSQLData(lists, drilldown);// 处理SQL数据
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
	private Object handleMainDrilldownSQLData(List<Map<String, Object>> lists, Boolean drilldown) {

		List<Object> data = new ArrayList<Object>();

		if (lists == null || lists.isEmpty())
			return data;

		for (Map<String, Object> item : lists) {
			// 获取值
			String code = item.get("P_VAL").toString();
			String name = item.get("X_VAL").toString();
			double y = Double.parseDouble(item.get("Y_VAL").toString());

			// 清理，重新填值
			item.clear();

			item.put("code", code);
			item.put("name", name);
			item.put("y", y);
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
		JSONObject dataObject;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.monthDataInfo(reqParamsJSON);
			resultJSONString = dto.getJson();
			dataObject = JSONObject.parseObject(resultJSONString);
			Object series = dataObject.get("series");
			String nameTitle = dataObject.getString("name");
			JSONArray serList = JSON.parseArray(series.toString());

			String type = JSON.parseObject(serList.getString(0)).getString("type");
			String title = JSON.parseObject(serList.getString(0)).getString("title");
			dataObject.replace("name", title);
			List<Map<String, Object>> seriesList = new ArrayList<>();
			for (Object seObj : serList) {
				Map<String, Object> seMap = new HashMap<>();
				JSONObject seO = JSON.parseObject(seObj.toString());
				String sql = seO.getString("sql");
				seMap.put("type", seO.getString("type"));
				seMap.put("name", seO.getString("name"));
				seMap.put("code", seO.getString("code"));
				seMap.put("unit", seO.getString("unit"));
				seMap.put("stack", seO.getString("stack"));
				seMap.put("color", seO.getString("color"));
				List<Map<String, Object>> lists = fixedSQLCacheServiceImpl.fixedMcountInfo(reqParams, sql);
				List<Object> data = new ArrayList<>();
				if ("pie".equalsIgnoreCase(type)) {
					dataObject.replace("xAxis", null);
					for (Map<String, Object> list : lists) {
						List<Object> objList = new ArrayList<>();
						Object key = (lists == null || lists.isEmpty()) ? "" : list.get("VAL0");
						Object val = (lists == null || lists.isEmpty()) ? "" : list.get("VAL1");
						objList.add(key.toString());
						objList.add(Double.parseDouble(val.toString()));
						data.add(objList);
					}
				} else {
					List<String> xAxis = new ArrayList<>();
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
			dataObject.replace("series", seriesList);
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
		if (StringUtils.isEmpty(dataObject)) {
			log.warn("fixed接口 thrift 获取的结果为空");
			return RestResult.getError(RestCode.EMPTY);
		}
		return RestResult.getSuccess(dataObject);
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
			List<Map<String, Object>> honorLists = fixedSQLCacheServiceImpl.fixedMcountRankingMap(reqParams, honorSql);
			if (honorLists == null || honorLists.isEmpty()) {
				resultJSONObject.put("honor", new String[] {});
			} else {
				System.out.println(JSON.toJSONString(honorLists));
			}
		}

		// 排行榜数据
		String rankSql = resultJSONObject.getString("rank");
		if (StringUtils.isEmpty(rankSql)) {
			resultJSONObject.put("rank", new String[] {});
		} else {
			List<Map<String, Object>> rankLists = fixedSQLCacheServiceImpl.fixedMcountRankingMap(reqParams, rankSql);
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

							for (Map<String, Object> item : lists) {

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
		case 3:
			// mapData = this.baseMapper.getServiceByBranch(code);
			break;
		case 4:
			// mapData = this.baseMapper.getRegionByService(code);
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

}
