package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.JyfxTargetMapper;
import com.huatai.web.model.JyfxTarget;
import com.huatai.web.service.TarManService;

@Service
public class TarManServiceImpl implements TarManService {
	
	@Autowired
	private JyfxTargetMapper tarManMapper;

	@Override
	public List<JyfxTarget> findByAnTargetCode(String anTargetCode) {
		return this.tarManMapper.findByAnTargetCode(anTargetCode);
	}

	@Override
	public List<JyfxTarget> findByAnTargetCode(String anTargetCode, String depts) {
		return this.tarManMapper.findByAnTargetCode(anTargetCode);
	}

	@Override
	public int addTarMan(JyfxTarget record) {
		return this.tarManMapper.addTarMan(record);
	}

	@Override
	public int deleteTarMan(String targetCode) {
		return this.tarManMapper.deleteTarMan(targetCode);
	}

	@Override
	public List<JyfxTarget> findByIsAnTargetCode(String targetCode, String depts) {
		return this.tarManMapper.findByIsAnTargetCode(targetCode,null);
	}

	@Override
	public int updateByTargetCodeAndAnTargetCode(JyfxTarget record) {
		return this.tarManMapper.updateByTargetCodeAndAnTargetCode(record);
	}


	
}
