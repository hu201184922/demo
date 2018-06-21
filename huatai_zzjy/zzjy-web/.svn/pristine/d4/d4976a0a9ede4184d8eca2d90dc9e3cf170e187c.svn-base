package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TarInitSqlMapper;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.Target;
import com.huatai.web.service.TarInitSqlService;

@Service
public class TarInitSqlServiceImpl implements TarInitSqlService {

	@Autowired
	private TarInitSqlMapper tarInitSqlMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer tisId) {
		return tarInitSqlMapper.deleteByPrimaryKey(tisId);
	}

	@Override
	public void insert(TarInitSql record) {
		tarInitSqlMapper.insert(record);
	}

	@Override
	public TarInitSql selectByPrimaryKey(Integer tisId) {
		return tarInitSqlMapper.selectByPrimaryKey(tisId);
	}

	@Override
	public int updateByPrimaryKey(TarInitSql record) {
		return tarInitSqlMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Target> findSubPlates() {
		return tarInitSqlMapper.findSubPlates();
	}

	@Override
	public List<Target> getTarBySubId(String subPlaId) {
		return tarInitSqlMapper.getTarBySubId(subPlaId);
	}

	@Override
	public List<DictItem> findFuncList() {
		return tarInitSqlMapper.findFuncList();
	}

	@Override
	public Pager<TarInitSql> findByPager(Pager<TarInitSql> pager, TarInitSql record) {
		return tarInitSqlMapper.findByPager(pager, record);
	}

}
