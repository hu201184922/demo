package com.ehuatai.biz.tool.service;

import java.util.List;
import java.util.Map;

public interface ToolSQLCacheService {

	/**
	 * @Describtion 获取实际指标值
	 * @param reqParams
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getActualVal(Map<String, Object> reqParams,String sql);
}
