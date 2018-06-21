package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TarRegMapper;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;
import com.huatai.web.model.Target;
import com.huatai.web.service.TarRegService;
import com.huatai.web.service.TargetService;

@Service
public class TarRegServiceImpl implements TarRegService {
	@Autowired
	private TarRegMapper tarRegMapper;
	@Autowired
	private TargetService targetService;

	@Override
	public int deleteByExample(TarRegExample example) {
		return tarRegMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer trId) {
		return tarRegMapper.deleteByPrimaryKey(trId);
	}

	@Override
	public int insert(TarReg record) {
		return tarRegMapper.insert(record);
	}

	@Override
	public TarReg selectByPrimaryKey(Integer trId) {
		return tarRegMapper.selectByPrimaryKey(trId);
	}

	@Override
	public int updateByPrimaryKeySelective(TarReg record) {
		return tarRegMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TarReg record) {
		return tarRegMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TarReg> findAll(TarReg tarReg) {
		return tarRegMapper.findAll(tarReg);
	}

	@Override
	public String getSubCodeByTargetCode(String targetCode) {
		Target targetRoot = targetService.selectByPrimaryKey(targetCode);
		if (targetRoot == null)
			return null;
		while (!"0".equals(targetRoot.getTargetType())) {
			targetRoot = targetService.selectByPrimaryKey(targetRoot.getParentCode());
			if (targetRoot == null)
				return null;
		}
		return targetRoot.getTargetCode();
	}

	@Override
	public List<TarReg> findAllLike(TarReg tarReg) {
		return tarRegMapper.findAllLike(tarReg);
	}

}
