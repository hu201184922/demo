package com.huatai.web.service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Role;

/**
 * @author 胡智辉
 * 2017年7月17日
 */
public interface RoleService {

	Pager<Role> findByPager(Pager<Role> pager, Role role);

	int updateRole(Role role);

	int addRole(Role role);

	int deleteRole(String id);

	Role findRoleById(String id);

	/**
	 * 根据角色代码查询角色名
	 * @param roleName
	 * @return 杜金虎
	 */
	String findroleCodeByroleName(String roleName);

}
