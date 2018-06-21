package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TarRegTabHeadMapper;
import com.huatai.web.model.TarRegTabHead;
import com.huatai.web.service.TarRegTabHeadService;

@Service
public class TarRegTabHeadServiceImpl implements TarRegTabHeadService{
	@Autowired
	private TarRegTabHeadMapper tarRegTabHeadMapper;
	@Override
	public List<TarRegTabHead> findAll(TarRegTabHead record) {
		return tarRegTabHeadMapper.findAll(record);
	}
	@Override
	public int insert(TarRegTabHead record) {
		return tarRegTabHeadMapper.insert(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer trthId) {
		return tarRegTabHeadMapper.deleteByPrimaryKey(trthId);
	}

}
