package com.ehuatai.biz.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.biz.mapper.SQLMapper;

@Service
public class SQLServiceImpl implements SQLService {

	@Autowired
	private SQLMapper sqlMapper;
	
	@Override
	public List<Map<String, Object>> findBySQL(String sql) {

		return sqlMapper.findBySQL(sql);
	}

}
