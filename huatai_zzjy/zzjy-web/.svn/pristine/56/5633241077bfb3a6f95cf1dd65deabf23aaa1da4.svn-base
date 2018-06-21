package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.InterFieldMapper;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.service.InterFieldService;


/**
 * @author 胡智辉
 * 2017年7月27日
 */
@Service
public class InterFieldServiceImpl implements InterFieldService {

	@Autowired
	private InterFieldMapper interFieldMapper;
	
	@Override
	public Pager<InterField> findByPager(Pager<InterField> pager, Inter record) {
		Pager<InterField> result=this.interFieldMapper.findInterFieldByPage(pager, record);
		return result;
	}

	@Override
	public int insert(InterField record) {
		return interFieldMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(InterField record) {
		return interFieldMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return interFieldMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Inter> findInterFieldByInterId(String interId) {
		return interFieldMapper.findInterFieldByInterId(interId);
	}	

}
