package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.RoleTarget;

/**
 * @author 胡智辉
 * 2017年7月20日
 */
public interface RoleTargetService {

	Pager<RoleTarget> findRoleTargetByPage(Pager<RoleTarget> pager, RoleTarget roleTarget);

	int updateRoleTarget(RoleTarget roleTarget);

	int addRoleTarget(RoleTarget roleTarget);

	int deleteRoleTarget(String string);

	List<RoleTarget> findRoleTargetByRoleCode(String roleCode, String depts);

	List<RoleTarget> findRoleTargetAll();

	int update(RoleTarget record);

	List<RoleTarget> findByIsRoleCode(String roleCode);

	int updateByRoleTarget(RoleTarget record);

}
