package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TarStatisMapper;
import com.huatai.web.model.TarStatis;
import com.huatai.web.service.TarStatisService;

@Service
public class TarStatisServiceImpl implements TarStatisService{
	@Autowired
	private TarStatisMapper tarStatisMapper;
	
	@Override
	public int deleteByPrimaryKey(Long tsId) {
		return tarStatisMapper.deleteByPrimaryKey(tsId);
	}

	@Override
	public int insert(TarStatis record) {
		return tarStatisMapper.insert(record);
	}

	@Override
	public TarStatis selectByPrimaryKey(Long tsId) {
		return tarStatisMapper.selectByPrimaryKey(tsId);
	}

	@Override
	public int updateByPrimaryKey(TarStatis record) {
		return tarStatisMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TarStatis> findAll(TarStatis record) {
		return tarStatisMapper.findAll(record);
	}

	@Override
	public List<TarStatis> findBySubCode(String subCode, String depts) {
		return tarStatisMapper.findBySubCode(subCode,depts);
	}

	@Override
	public int addTarStatis(TarStatis record) {
		return tarStatisMapper.addTarStatis(record);
	}

	@Override
	public int deleteTarStatis(String subCode) {
		return tarStatisMapper.deleteTarStatis(subCode);
	}

	@Override
	public List<TarStatis> findByIsSubCode(String subCode, String depts) {
		return this.tarStatisMapper.findByIsSubCode(subCode,null);
	}

	@Override
	public int updateByTargetCodeAndAnSubCode(TarStatis record) {
		return this.tarStatisMapper.updateByTargetCodeAndAnSubCode(record);
	}

}
