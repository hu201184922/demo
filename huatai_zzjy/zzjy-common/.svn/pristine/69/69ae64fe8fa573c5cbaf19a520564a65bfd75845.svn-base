package com.ehuatai.biz.browse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehuatai.biz.browse.service.BrowseSQLCacheService;
import com.ehuatai.biz.mapper.SQLMapper;
import com.fairyland.jdp.orm.Pager;

@Service
public class BrowseSQLCacheServiceImpl implements BrowseSQLCacheService{ 

	@Autowired
	private SQLMapper SQLMapper;
	
	@Override
	public List<Map<String, Object>> browseNavs(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}
	

	@Override
	public Pager<Map<String, Object>> browseListCustom(Map<String, Object> reqParams,Pager<Map<String, Object>> pager, String sql) {
		return SQLMapper.findPagerBySQL(pager, sql);
	}

	@Override
	public List<Map<String, Object>> browseListCustomDownload(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	@Deprecated
	public List<Map<String, Object>> browseList(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	@Deprecated
	public List<Map<String, Object>> browseDownload(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> browseMy(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> browseMyTargets(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> browseEditDimen(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> browseEditSave(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	@Override
	public List<Map<String, Object>> browseDelete(Map<String, Object> reqParams, String sql) {
		return SQLMapper.findBySQL(sql);
	}

	

}
