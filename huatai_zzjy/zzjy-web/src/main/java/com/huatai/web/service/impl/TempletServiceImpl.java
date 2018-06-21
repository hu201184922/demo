package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TempletMapper;
import com.huatai.web.model.Templet;
import com.huatai.web.model.TempletExample;
import com.huatai.web.service.TempletService;

/**
 * @author 胡智辉
 * 2017年7月19日
 */
@Service
public class TempletServiceImpl implements TempletService {

	@Autowired
	private TempletMapper templetMapper;

	@Override
	public int addTemplet(Templet templet) {
		int result=this.templetMapper.insertSelective(templet);
		return result;
	}

	@Override
	public Templet findById(Integer tempId) {
		Templet result=this.templetMapper.selectByPrimaryKey(tempId);
		return result;
	}

	@Override
	public int updateTemplet(Templet templet) {
		int result=this.templetMapper.updateByPrimaryKeySelective(templet);
		return result;
	}

	@Override
	public int delectTemplet(Integer tempId) {
		int result=this.templetMapper.deleteByPrimaryKey(tempId);
		return result;
	}

	@Override
	public List<Templet> findTempletByAll() {
		TempletExample example=new TempletExample();
		List<Templet> result=this.templetMapper.selectByExample(example);
		return result;
	}

	@Override
	public Pager<Templet> findTempletByPage(Pager<Templet> pager, Templet record) {
		Pager<Templet> result=this.templetMapper.findTempletByPage(pager,record);
		return result;
	}

}
