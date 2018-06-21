package com.huatai.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.InterFieldMapper;
import com.huatai.web.mapper.InterMapper;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterExample;
import com.huatai.web.model.InterField;
import com.huatai.web.service.InterService;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月19日 上午9:45:11
 */
@Service
public class InterServiceImpl implements InterService {

	@Autowired
	private InterMapper interMapper;
	
	@Autowired
	private InterFieldMapper interFieldMapper;
	
	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public int countByExample(InterExample example) {
		return this.interMapper.countByExample(example);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public int deleteByExample(InterExample example) {
		return this.interMapper.deleteByExample(example);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public void deleteByPrimaryKey(Integer interId) {
		this.interMapper.deleteByPrimaryKey(interId);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public void insert(Inter record) {
		this.interMapper.insert(record);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public int insertSelective(Inter record) {
		return this.interMapper.insertSelective(record);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public List<Inter> selectByExample(InterExample example) {
		return this.interMapper.selectByExample(example);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public Inter selectByPrimaryKey(Integer interId) {
		return this.interMapper.selectByPrimaryKey(interId);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public int updateByExampleSelective(Inter record, InterExample example) {
		return this.interMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public int updateByExample(Inter record, InterExample example) {
		return this.interMapper.updateByExample(record, example);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public int updateByPrimaryKeySelective(Inter record) {
		int result=this.interMapper.updateByPrimaryKeySelective(record);
		if(result==1){
			InterField interField = new InterField();
			interField.setInterId(record.getInterId());
			interField.setOpType("D");
			interField.setModifyTime(new Date());
			interField.setModifierId(SessionContextUtils.getLoginName().toString());
			result = this.interFieldMapper.updateByInterId(interField);
			return result;
		}
		return result;
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public void updateByPrimaryKey(Inter record) {
		this.interMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月19日 上午9:45:13
	 */
	@Override
	public Pager<Inter> findByPager(Pager<Inter> pager, Inter record) {
		Pager<Inter> records = interMapper.findByPager(pager, record);
		return records;
	}

	/**
	 * @功能 
	 * @作者            胡智辉
	 * @返回类型 result
	 */
	@Override
	public List<Inter> findInterList() {
		return this.interMapper.findInterList();
	}

	@Override
	public List<Inter> findInterFieldByInterId(String interId) {
		return this.interMapper.findInterFieldByInterId(interId);
	}

}
