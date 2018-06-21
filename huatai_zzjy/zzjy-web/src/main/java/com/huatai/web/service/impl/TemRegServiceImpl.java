/**
 * 
 */
package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TemRegMapper;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;
import com.huatai.web.service.TemRegService;

/**
 * @描述 :
 * @作者 : 程乐飞
 * @日期时间: 2017年7月28日 下午1:55:06
 */
@Service
public class TemRegServiceImpl implements TemRegService {

	@Autowired
	private TemRegMapper temRegMapper;

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public int countByExample(TemRegExample example) {

		return this.temRegMapper.countByExample(example);

	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public int deleteByExample(TemRegExample example) {
		return this.temRegMapper.deleteByExample(example);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public void deleteByPrimaryKey(Integer regId) {
		this.temRegMapper.deleteByPrimaryKey(regId);

	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public void insert(TemReg record) {
		this.temRegMapper.insert(record);

	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public int insertSelective(TemReg record) {
		return this.temRegMapper.insertSelective(record);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public List<TemReg> selectByExample(TemRegExample example) {
		return this.temRegMapper.selectByExample(example);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public TemReg selectByPrimaryKey(Integer regId) {
		return this.temRegMapper.selectByPrimaryKey(regId);

	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public int updateByExampleSelective(TemReg record, TemRegExample example) {
		return this.temRegMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public int updateByExample(TemReg record, TemRegExample example) {
		return this.temRegMapper.updateByExample(record, example);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public int updateByPrimaryKeySelective(TemReg record) {
		return this.temRegMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public void updateByPrimaryKey(TemReg record) {
		this.temRegMapper.updateByPrimaryKey(record);

	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午1:55:32
	 */
	@Override
	public Pager<TemReg> findByPager(Pager<TemReg> pager, TemReg record) {
		Pager<TemReg> records = temRegMapper.findByPager(pager, record);
		return records;
	}

}
