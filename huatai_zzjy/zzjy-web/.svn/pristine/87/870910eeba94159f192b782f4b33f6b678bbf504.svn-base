package com.huatai.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.RoleFixedList;
import com.huatai.web.model.Target;

public interface RoleFixedListService {

	List<Target> selectTargetByTree(Object object);

	int insert(RoleFixedList record);

	int update(RoleFixedList record);

	Pager<RoleFixedList> findByPager(Pager<RoleFixedList> pager, RoleFixedList record);

	List<RoleFixedList> findByRoleCode(@Param("roleCode")String roleCode);

	List<FixedList> findByFlDeptCode(@Param("deptCode")String deptCode, String roleCode);

	int delete(String roleCode);

	List<RoleFixedList> findByIsRoleCode(String roleCode);

	int updateRoleFixed(RoleFixedList record);
}
