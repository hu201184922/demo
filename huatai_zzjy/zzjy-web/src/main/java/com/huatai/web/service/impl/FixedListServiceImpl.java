package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.FixedListMapper;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.Target;
import com.huatai.web.service.FixedListService;

@Service
public class FixedListServiceImpl implements FixedListService {

	@Autowired
	private FixedListMapper fixedListMapper;

	@Override
	public Pager<FixedList> findByPager(Pager<FixedList> pager, FixedList record) {
		Pager<FixedList> result=this.fixedListMapper.findByPager(pager, record);
		return result;
	}

	@Override
	public List<Target> selectTargetByTree(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(FixedList record) {
		int result= this.fixedListMapper.insertSelective(record);
		return result;
	}

	@Override
	public int update(FixedList record) {
		int result= this.fixedListMapper.updateByPrimaryKey(record);
		return result;
	}

	@Override
	public List<FixedList> findAll() {
		List<FixedList> result= this.fixedListMapper.findAll();
		return result;
	}

	@Override
	public List<FixedList> findDeptCodeByGroup() {
		List<FixedList> result= this.fixedListMapper.findDeptCodeByGroup();
		return result;
	}

	@Override
	public FixedList findFixedByFlCode(String flCode) {
		return this.fixedListMapper.findFixedByFlCode(flCode);
	}

}
