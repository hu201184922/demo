package com.ehuatai.biz.app.fixed.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ehuatai.biz.app.fixed.service.AppFixedSqlService;
import com.ehuatai.biz.mapper.SQLMapper;

public class AppFixedSqlServiceImpl implements AppFixedSqlService {
	@Autowired
	private SQLMapper sqlMapper;

	@Override
	public List<Map<String, Object>> fixedMain(Map<String, Object> reqParams, String sql) {
		// TODO Auto-generated method stub
		return sqlMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> fixed(Map<String, Object> reqParams, String sql) {
		// TODO Auto-generated method stub
		return sqlMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> fixedMainDrilldown(Map<String, Object> reqParams, String sql) {
		// TODO Auto-generated method stub
		return sqlMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> fixedMcountInfo(Map<String, Object> reqParams, String sql) {
		// TODO Auto-generated method stub
		return sqlMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> fixedCore(Map<String, Object> reqParams, String sql) {
		// TODO Auto-generated method stub
		return sqlMapper.findBySQL(sql);
	}

}
