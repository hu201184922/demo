package com.huatai.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.service.TarRegSqlService;

@Service
public class TarRegSqlServiceImpl implements TarRegSqlService{
	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;
	
	@Override
	public Pager<TarRegSql> findByPager(Pager<TarRegSql> pager, TarRegSql record) {
		return tarRegSqlMapper.findByPager(pager, record);
	}

	@Override
	public int deleteByPrimaryKey(Integer trsId) {
		return tarRegSqlMapper.deleteByPrimaryKey(trsId);
	}

	@Override
	public int insert(TarRegSql record) {
		return tarRegSqlMapper.insert(record);
	}

	@Override
	public TarRegSql selectByPrimaryKey(Integer trsId) {
		return tarRegSqlMapper.selectByPrimaryKey(trsId);
	}

	@Override
	public int updateByPrimaryKeySelective(TarRegSql record) {
		return tarRegSqlMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TarRegSql record) {
		return tarRegSqlMapper.updateByPrimaryKey(record);
	}

}
