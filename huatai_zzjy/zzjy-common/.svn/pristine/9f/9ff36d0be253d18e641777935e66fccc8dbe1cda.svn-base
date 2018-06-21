package com.ehuatai.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;

/**
 * 通用SQL查询
 * 
 * @author ctl
 *
 */
public interface SQLService {

	public List<Map<String, Object>> findBySQL(String sql);

	/**
	 * f分页查询
	 * 
	 * @param pager
	 * @param sql
	 * @return
	 */
	public Pager<Map<String, Object>> findPagerBySQL(Pager<Map<String, Object>> pager, @Param("sql") String sql);

	Pager<Map<String, Object>> findGdqdData(String code, Pager<Map<String, Object>> pager, HashMap paramMap);

}
