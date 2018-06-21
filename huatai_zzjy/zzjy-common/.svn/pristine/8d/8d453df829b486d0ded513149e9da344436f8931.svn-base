package com.ehuatai.biz.constants;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @功能 {sql参数处理器}
 * @作者 MaxBill
 * @时间 2017年9月15日 上午9:39:42
 */

public class SqlParameter {
	/**
	 * @状态
	 * @功能 {分析管理SQL参数处理}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 上午10:09:23
	 */
	public static String fxglSqlParam(String filters) {
		JSONArray filterArray = JSON.parseArray(filters);
		List<JSONObject> filterList = JSON.parseArray(filterArray.toString(), JSONObject.class);
		String tempKey = "";
		for (JSONObject filterJSONObject : filterList) {
			// 1.解析筛选维度编码
			String queryDimCode = filterJSONObject.getString("group");
			tempKey = tempKey + "-" + queryDimCode + "_";
			// 2.解析筛选维度的值
			String queryDimVals = filterJSONObject.getString("value");
			List<String> queryDimValList = JSON.parseArray(queryDimVals, String.class);
			// 3.拼装查询维度参数
			for (String param : queryDimValList) {
				tempKey = tempKey + param + ",";
			}
			tempKey = tempKey.substring(0, tempKey.length() - 1);
		}
		return tempKey;
	}

}
