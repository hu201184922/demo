package com.huatai.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.RoleMapper;
import com.huatai.web.mapper.RoleTargetMapper;
import com.huatai.web.model.Role;
import com.huatai.web.model.RoleTarget;
import com.huatai.web.service.RoleService;

/**
 * @author 胡智辉
 * 2017年7月17日
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleTargetMapper roleTargetMapper;

	@Override
	public Pager<Role> findByPager(Pager<Role> pager, Role record) {
		Pager<Role> result=this.roleMapper.findRoleByPage(pager, record);
		return result;
	}

	@Override
	@Transactional
	public int updateRole(Role record) {
		if(record.getOpType()=="D"){
			//1.更新coreTarget表里的roleCode为当前假删角色ID
			RoleTarget roleTarget = new RoleTarget();
			roleTarget.setOpType("D");
			roleTarget.setRoleCode(record.getRoleCode());
			this.roleTargetMapper.updateRoleTargetByRoleCode(roleTarget);
			return this.roleMapper.updateByPrimaryKeySelective(record);
		}
		return this.roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int addRole(Role role) {
		return this.roleMapper.insertSelective(role);
	}

	@Override
	public int deleteRole(String id) {
		return this.roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Role findRoleById(String roleCode) {
		return this.roleMapper.selectByPrimaryKey(roleCode);
	}

	@Override
	public String findroleCodeByroleName(String roleName) {
		return this.roleMapper.findroleCodeByroleName(roleName);
	}
	
}
