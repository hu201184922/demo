package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TarRegQueMapper;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.service.TarRegQueService;

@Service
public class TarRegQueServiceImpl implements TarRegQueService{
	@Autowired
	private TarRegQueMapper tarRegQueMapper;
	
	@Override
	public List<TarRegQue> findAll(TarRegQue tarRegQue) {
		return tarRegQueMapper.findAll(tarRegQue);
	}

	@Override
	public int insert(TarRegQue record) {
		return tarRegQueMapper.insert(record);
	}

	@Override
	public TarRegQue selectByPrimaryKey(Long trqId) {
		return tarRegQueMapper.selectByPrimaryKey(trqId);
	}

	@Override
	public int deleteByPrimaryKey(Long trqId) {
		return tarRegQueMapper.deleteByPrimaryKey(trqId);
	}

}
