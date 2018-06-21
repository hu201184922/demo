package com.ehuatai.biz.index.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.biz.index.service.BoardRealService;
import com.ehuatai.biz.mapper.SQLMapper;
import com.ehuatai.biz.mapper.SszbMapper;
import com.ehuatai.ret.RestCode;
import com.ehuatai.ret.RestResult;
import com.ehuatai.thrift.DashbordService;
import com.ehuatai.thrift.ResponseBeanDto;
import com.ehuatai.thrift.DashbordService.Client;
import com.ehuatai.thrift.util.ThriftUtil;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Service
public class BoardRealServiceImpl implements BoardRealService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SQLMapper sqlMapper;
	@Autowired
	private SszbMapper ssMapper;

	private List<Map<String, Object>> list1 = new ArrayList<>();
	private List<Map<String, Object>> list2 = new ArrayList<>();
	private List<Map<String, Object>> list3 = new ArrayList<>();

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

	public RestResult getDSBrealTime(Map<String, Object> reqParams) {
		Client client = this.getClient();
		if (client == null) {
			log.error(RestCode.THRIFT_CLIENT_ERROR.getErrMsg());
			return RestResult.getError(RestCode.THRIFT_CLIENT_ERROR);
		}
		ResponseBeanDto dto = null;
		JSONObject resultObj;
		try {
			String reqParamsJSON = JSON.toJSONString(reqParams);
			dto = client.getRealTimeTarget(reqParamsJSON);
			String resultJSONString = dto.getJson();
			resultObj = JSONObject.parseObject(resultJSONString);
			JSONObject sqlObj = resultObj.getJSONObject("sql");
			String org = reqParams.get("roleOrg").toString();
			ExecutorService exe = Executors.newFixedThreadPool(8);

			exe.execute(new Runnable() {

				@Override
				public void run() {
					String sqlDay = sqlObj.getString("DAY");
					if (sqlDay.contains("{target_month_table}")) {
						sqlDay = getSqlString(sqlDay, "YYYY-MM", org);
					}
					if (sqlDay.contains("{target_year_table}")) {
						sqlDay = getSqlString(sqlDay, "YYYY", org);
					}
					list1 = sqlMapper.findBySQL(sqlDay);
				}
			});
			exe.execute(new Runnable() {

				@Override
				public void run() {
					String sqlMon = sqlObj.getString("MONTH");
					if (sqlMon.contains("{target_month_table}")) {
						sqlMon = getSqlString(sqlMon, "YYYY-MM", org);
					}
					if (sqlMon.contains("{target_year_table}")) {
						sqlMon = getSqlString(sqlMon, "YYYY", org);
					}
					list2 = sqlMapper.findBySQL(sqlMon);
				}
			});
			exe.execute(new Runnable() {

				@Override
				public void run() {
					String sqlYear = sqlObj.getString("YEAR");
					if (sqlYear.contains("{target_month_table}")) {
						sqlYear = getSqlString(sqlYear, "YYYY-MM", org);
					}
					if (sqlYear.contains("{target_year_table}")) {
						sqlYear = getSqlString(sqlYear, "YYYY", org);
					}
					list3 = sqlMapper.findBySQL(sqlYear);
				}
			});
			exe.shutdown();
			Map<String, Object> targets = new HashMap<>();
			List<List<String>> days = new ArrayList<>();
			List<List<String>> months = new ArrayList<>();
			List<List<String>> years = new ArrayList<>();
			while (true) {
				if (exe.isTerminated()) {
					DecimalFormat df=new DecimalFormat("#.##");
					for (Map<String, Object> lista : list1) {
						List<String> day = new ArrayList<>();
						String val0 = lista.get("val0").toString();
						String val1 = lista.get("val1").toString();
						day.add(val0);
						day.add("--".equals(val1)?"--":String.valueOf(df.format(Double.parseDouble(val1))));
						days.add(day);
					}
					for (Map<String, Object> listb : list2) {
						List<String> month = new ArrayList<>();
						String val0 = listb.get("val0").toString();
						String val2 = listb.get("val2").toString();
						String val3 = listb.get("val3").toString();
						String val4 = listb.get("val4").toString();
						month.add(val0);
						month.add("--".equals(val2)?"--":String.valueOf(df.format(Double.parseDouble(val2))));
						month.add("--".equals(val3)?"--":String.valueOf(df.format(Double.parseDouble(val3))));
						month.add("--".equals(val4)?"--":String.valueOf(df.format(Double.parseDouble(val4))));
						months.add(month);
					}
					for (Map<String, Object> listc : list3) {
						List<String> year = new ArrayList<>();
						String val0 = listc.get("val0").toString();
						String val5 = listc.get("val5").toString();
						String val6 = listc.get("val6").toString();
						String val7 = listc.get("val7").toString();
						year.add(val0);
						year.add("--".equals(val5)?"--":String.valueOf(df.format(Double.parseDouble(val5))));
						year.add("--".equals(val6)?"--":String.valueOf(df.format(Double.parseDouble(val6))));
						year.add("--".equals(val7)?"--":String.valueOf(df.format(Double.parseDouble(val7))));
						years.add(year);
					}
					break;
				}
			}
			targets.put("day", days);
			targets.put("month", months);
			targets.put("year", years);
			resultObj.remove("sql");
			resultObj.put("targets", targets);
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
		return RestResult.getSuccess(resultObj);
	}

	// 替换过的SQL
	public String getSqlString(String sql, String dateStr, String org) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("partten", dateStr);
		paramMap.put("org", org);
		List<Map<String, Object>> resOras = new ArrayList<>();
		switch (org.length()) {
		case 1:
			resOras = ssMapper.findPurZongByOra(paramMap);
			break;
		case 3:
			resOras = ssMapper.findPurFenByOra(paramMap);
			break;
		case 5:
			resOras = ssMapper.findPurZhiByOra(paramMap);
			break;
		}
		String target_table = getOraString(resOras);
		if ("YYYY-MM".equals(dateStr)) {
			sql = sql.replace("{target_month_table}", target_table);
		} else {
			sql = sql.replace("{target_year_table}", target_table);
		}
		return sql;
	}

	// 替换字符串
	public static String getOraString(List<Map<String, Object>> resOras) {
		String target_table = " SELECT '1' CODE,0 AS VAL ";
		if (!resOras.isEmpty() && StringUtil.isNotNull(resOras)) {
			target_table = " SELECT * FROM ( ";
			for (int i = 0; i < resOras.size(); i++) {
				if (i == 0) {
					target_table += " SELECT '" + resOras.get(0).get("CODE").toString() + "' CODE , "
							+ resOras.get(0).get("VAL").toString() + " AS VAL ";
				} else {
					target_table += " UNION ALL SELECT '" + resOras.get(i).get("CODE").toString() + "' CODE , "
							+ resOras.get(i).get("VAL").toString() + " AS VAL ";
				}
			}
			target_table += " ) X ";
		}
		return target_table;
	}
}
