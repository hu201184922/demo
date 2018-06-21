package com.ehuatai.biz.service;

import java.util.List;
import java.util.Map;

/**
 * 通用SQL查询
 * @author ctl
 *
 */
public interface SQLService {

	public List<Map<String, Object>> findBySQL(String sql);
}
