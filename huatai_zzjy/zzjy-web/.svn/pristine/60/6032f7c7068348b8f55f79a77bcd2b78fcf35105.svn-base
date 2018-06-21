package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.RoleTargetMapper;
import com.huatai.web.model.RoleTarget;
import com.huatai.web.service.RoleTargetService;

/**
 * @author 胡智辉 2017年7月20日
 */
@Service
public class RoleTargetServiceImpl implements RoleTargetService {

	@Autowired
	private RoleTargetMapper roleTargetMapper;

	@Override
	public Pager<RoleTarget> findRoleTargetByPage(Pager<RoleTarget> pager, RoleTarget record) {
		Pager<RoleTarget> result = this.roleTargetMapper.findRoleTargetByPage(pager, record);
		return result;
	}

	@Override
	public int updateRoleTarget(RoleTarget roleTarget) {
		int result = this.roleTargetMapper.updateByPrimaryKeySelective(roleTarget);
		return result;
	}

	@Override
	public int addRoleTarget(RoleTarget record) {
		int result = this.roleTargetMapper.insertSelective(record);
		return result;
	}

	@Override
	public int deleteRoleTarget(String roleCode) {
		int result = this.roleTargetMapper.deleteByPrimaryKey(roleCode);
		return result;
	}

	@Override
	public List<RoleTarget> findRoleTargetByRoleCode(String roleCode,String depts) {
		return this.roleTargetMapper.findRoleTargetByRoleCode(roleCode,depts);
	}

	@Override
	public List<RoleTarget> findRoleTargetAll() {
		return this.roleTargetMapper.findRoleTargetAll();
	}

	@Override
	public int update(RoleTarget record) {
		int result = this.roleTargetMapper.updateByRoleCodeSelective(record);
		return result;
	}

	@Override
	public List<RoleTarget> findByIsRoleCode(String roleCode) {
		return this.roleTargetMapper.findByIsRoleCode(roleCode);
	}

	@Override
	public int updateByRoleTarget(RoleTarget record) {
		return this.roleTargetMapper.updateByRoleTarget(record);
	}

}
