package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TargetInterMapper;
import com.huatai.web.model.TargetInter;
import com.huatai.web.service.TargetInterService;

/**
 * @author 胡智辉 2017年7月27日
 */
@Service
public class TargetInterServiceImpl implements TargetInterService {

	@Autowired
	private TargetInterMapper targetInterMapper;

	@Override
	public int insertSelective(TargetInter record) {
		return targetInterMapper.insertSelective(record);
	}

	@Override
	public Pager<TargetInter> findByPager(Pager<TargetInter> pager, TargetInter record) {
		Pager<TargetInter> result = this.targetInterMapper.findByPager(pager, record);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(TargetInter record) {
		return targetInterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer record) {
		return this.targetInterMapper.deleteByPrimaryKey(record);
	}

	@Override
	public List<TargetInter> findTargetInterByInterId(String interId) {
		return this.targetInterMapper.findTargetInterByInterId(interId);
	}

	@Override
	public int updateTargetInter(TargetInter record) {
		return targetInterMapper.updateTargetInter(record);
	}

	@Override
	public List<TargetInter> findByIsTargetInter(Integer interId) {
		return this.targetInterMapper.findByIsTargetInter(interId);
	}

	@Override
	public int updateInterId(TargetInter record) {
		return targetInterMapper.updateInterId(record);
	}

	//未使用
	@Override
	public int updateByInterIdAndTargetCode(Integer interId, String targetCode) {
		return 0;
	}

}
