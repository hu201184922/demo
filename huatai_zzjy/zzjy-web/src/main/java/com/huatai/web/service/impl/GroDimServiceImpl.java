package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.GroDimMapper;
import com.huatai.web.model.GroDim;
import com.huatai.web.service.GroDimService;

/**
 * @author 胡智辉 
 * 2017年7月20日
 */
@Service
public class GroDimServiceImpl implements GroDimService {

	@Autowired
	private GroDimMapper groDimMapper;

	@Override
	public Pager<GroDim> findGroDimByPage(Pager<GroDim> pager, GroDim record) {
		Pager<GroDim> result = this.groDimMapper.findGroDimByPage(pager, record);
		return result;
	}

	@Override
	public int addGroDim(GroDim groDim) {
		int result = this.groDimMapper.insertSelective(groDim);
		return result;
	}

	@Override
	public int updateGroDim(GroDim groDim) {
		int result = this.groDimMapper.updateByPrimaryKeySelective(groDim);
		return result;
	}

	@Override
	public void deleteByPrimaryKey(Integer gdId) {
		 this.groDimMapper.deleteByPrimaryKey(gdId);
	}

	/**
	 * @功能 
	 * @作者 huzhihui
	 * @返回参数 List<GroDim>
	 */
	@Override
	public List<GroDim> findGroDimAll() {
		return this.groDimMapper.findGroDimAll();
	}

	@Override
	public List<GroDim> findGroDimByTargetCode(String targetCode) {
		return groDimMapper.findGroDimByTargetCode(targetCode);
	}

	/**
	 *@功能   :
	 *@作者   : 程乐飞
	 *@日期时间: 2017年8月4日 下午6:51:04
	 */
	@Override
	public GroDim findById(Integer gdId) {
		return groDimMapper.selectByPrimaryKey(gdId);
	}

	@Override
	public int update(GroDim record) {
		return groDimMapper.updateByPrimaryKeySelective(record);
	}

 }
