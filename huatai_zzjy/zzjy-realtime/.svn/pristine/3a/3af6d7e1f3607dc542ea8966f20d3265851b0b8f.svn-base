/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.organizational.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.organizational.agent.domain.OrgRole;
import com.fairyland.jdp.framework.organizational.agent.mapper.OrgRoleMapper;

/**
 * @author jiangbingbin
 */	
@Service(value = "orgRoleService")
@Transactional
public class OrgRoleServiceImpl implements OrgRoleService {

	@Autowired
	private OrgRoleMapper orgRoleMapper;

	/**
	 * 获取内勤列表
	 * @return
	 */
	@Override
	public List<OrgRole> getOfficeRoles() {
		return orgRoleMapper.getOfficeRoles();
	}

}
