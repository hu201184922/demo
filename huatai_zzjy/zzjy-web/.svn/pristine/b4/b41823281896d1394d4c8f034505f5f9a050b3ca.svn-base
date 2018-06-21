package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TarQueryDimMapper;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.service.TarQueryDimService;

/**
 * @作者 胡智辉
 * @日期 2017年8月3日
 */
@Service
public class TarQueryDimServiceImpl implements TarQueryDimService {

	@Autowired
	private TarQueryDimMapper tarQueryDimMapper;

	@Override
	public int insertSelective(TarQueryDim record) {
		return tarQueryDimMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TarQueryDim record) {
		return tarQueryDimMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TarQueryDim selectByPrimaryKey(Integer tqdId) {
		return tarQueryDimMapper.selectByPrimaryKey(tqdId);
	}

	@Override
	public List<TarQueryDim> findAll() {
		return tarQueryDimMapper.findAll();
	}

	@Override
	public List<TarQueryDim> findTarQueryDimByTargetCode(String targetCode) {
		return tarQueryDimMapper.findTarQueryDimByTargetCode(targetCode);
	}

}
