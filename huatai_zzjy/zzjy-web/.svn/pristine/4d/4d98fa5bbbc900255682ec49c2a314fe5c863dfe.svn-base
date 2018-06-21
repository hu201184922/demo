package com.huatai.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;
import com.huatai.web.service.GroDimDetailService;

@Service
public class GroDimDetailServiceImpl implements GroDimDetailService {

	@Autowired
	private GroDimDetailMapper groDimDetailMapper;

	@Override
	public int countByExample(GroDimDetailExample example) {

		return this.groDimDetailMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(GroDimDetailExample example) {

		return this.groDimDetailMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer gddId) {

		return this.groDimDetailMapper.deleteByPrimaryKey(gddId);
	}

	@Override
	public int insert(GroDimDetail record) {

		return this.groDimDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(GroDimDetail record) {

		return this.groDimDetailMapper.insertSelective(record);
	}

	@Override
	public List<GroDimDetail> selectByExample(GroDimDetailExample example) {
		return this.groDimDetailMapper.selectByExample(example);
	}

	@Override
	public GroDimDetail selectByPrimaryKey(Integer gddId) {
		return this.groDimDetailMapper.selectByPrimaryKey(gddId);
	}

	@Override
	public int updateByExampleSelective(GroDimDetail record, GroDimDetailExample example) {
		return this.groDimDetailMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(GroDimDetail record, GroDimDetailExample example) {
		return this.groDimDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(GroDimDetail record) {
		return this.groDimDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GroDimDetail record) {
		return this.groDimDetailMapper.updateByPrimaryKey(record);
	}

	@Override
	public Pager<GroDimDetail> findByPager(Pager<GroDimDetail> pager, GroDimDetail record) {
		Pager<GroDimDetail> records = groDimDetailMapper.findByPager(pager, record);
		return records;
	}

	@Override
	public List<GroDimDetail> findAllGroDimDetail() {
		return this.groDimDetailMapper.findAll();
	}

	@Override
	public GroDimDetail selectByGroDimName(String groDimName) {
		return this.groDimDetailMapper.selectByGroDimName(groDimName);
	}
	
	/**
	 * @功能 
	 * @作者            胡智辉
	 * @返回参数 List<GroDimDetailService>
	 */
	@Override
	public List<GroDimDetail> findGroDimDetailByGdId(String gdId) {
		return this.groDimDetailMapper.findGroDimDetailByGdId(gdId);
	}

	@Override
	public List<GroDimDetail> findByTargetCodeAndGdId(String targetCode, Integer gdId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("targetCode", targetCode);
		params.put("gdId", gdId);
		return groDimDetailMapper.findByTargetCodeAndGdId(params);
	}

	@Override
	public List<GroDimDetail> findByGroDimTypeCode(String code) {
		return groDimDetailMapper.findByGroDimTypeCode(code);
	}
	
	@Override
	public String findGroDimCodeByGroDimName(String groDimName) {
		return groDimDetailMapper.findGroDimCodeByGroDimName(groDimName);
	}

	@Override
	public List<GroDimDetail> findByTargetCodeAndGdId(Integer gdId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("gdId", gdId);
		return groDimDetailMapper.findByGdId(params);
	}

	@Override
	public int update(GroDimDetail record) {
		return groDimDetailMapper.updateByPrimaryKeySelective(record);
	}
}
