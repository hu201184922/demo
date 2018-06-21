package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.FixedListMapper;
import com.huatai.web.mapper.RoleFixedListMapper;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.RoleFixedList;
import com.huatai.web.model.Target;
import com.huatai.web.service.RoleFixedListService;

@Service
public class RoleFixedListServiceImpl implements RoleFixedListService {

	@Autowired
	private FixedListMapper fixedListMapper;

	@Autowired
	private RoleFixedListMapper roleFixedListMapper;

	@Override
	public Pager<RoleFixedList> findByPager(Pager<RoleFixedList> pager, RoleFixedList record) {
		Pager<RoleFixedList> result = this.roleFixedListMapper.findByPager(pager, record);
		return result;
	}

	@Override
	public List<Target> selectTargetByTree(Object object) {
		return null;
	}

	@Override
	public int update(RoleFixedList record) {
		int result =this.roleFixedListMapper.updateByRoleCodeSelective(record);
		return result;
	}

	@Override
	public int insert(RoleFixedList record) {
		int result =this.roleFixedListMapper.insertSelective(record);
		return result;
	}

	@Override
	public List<RoleFixedList> findByRoleCode(String roleCode) {
		return this.roleFixedListMapper.findByRoleCode(roleCode);
	}

	@Override
	public List<FixedList> findByFlDeptCode(String deptCode,String roleCode) {
		
		return this.fixedListMapper.findByFlDeptCode(deptCode,roleCode);
	}

	@Override
	public int delete(String roleCode) {
		int result=this.roleFixedListMapper.deleteByRoleCode(roleCode);
		return result;
	}

	@Override
	public List<RoleFixedList> findByIsRoleCode(String roleCode) {
		return this.roleFixedListMapper.findByIsRoleCode(roleCode);
	}

	@Override
	public int updateRoleFixed(RoleFixedList record) {
		int result =this.roleFixedListMapper.updateByRoleCodeAndFlCodeSelective(record);
		return result;
	}

}
