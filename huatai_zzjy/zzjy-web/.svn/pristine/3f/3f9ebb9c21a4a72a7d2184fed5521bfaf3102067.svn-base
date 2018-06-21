package com.huatai.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.dao.UserDao;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.BillFiltDimMapper;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.service.BillFiltDimService;

@Service
public class BillFiltDimServiceImpl implements BillFiltDimService{

	@Autowired
	private BillFiltDimMapper billFiltDimMapper;
	@Autowired
	private UserDao userDao;

	@Override
	public Pager<BillFiltDim> findByPager(Pager pager, BillFiltDim record) {
		Pager<BillFiltDim> result=this.billFiltDimMapper.findByPager(pager, record);
		for (BillFiltDim role : result.getPageItems()) {
			User user = userDao.findById(Long.parseLong(role.getCreatorId().trim()));
			role.setCreatorId(user.getUserName());
			if (!"".equals(role.getModifierId()) && null != role.getModifierId()) {
				User us = userDao.findById(Long.parseLong(role.getModifierId()));
				role.setModifierId(us.getUserName());
			} else {
				role.setModifierId(null);
			}
		}
		return result;
	}

	@Override
	public int insertSelective(BillFiltDim record) {
		return this.billFiltDimMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(BillFiltDim record) {
		return this.billFiltDimMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 *@功能   :
	 *@作者   : 程乐飞
	 *@日期时间: 2017年8月14日 上午1:52:11
	 */
	@Override
	public void deleteByPrimaryKey(Integer bfdId) {
		this.billFiltDimMapper.deleteByPrimaryKey(bfdId);
		
	}

}
