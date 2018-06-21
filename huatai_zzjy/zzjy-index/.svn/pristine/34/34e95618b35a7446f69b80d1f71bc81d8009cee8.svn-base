package com.fairyland.jdp.framework.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.security.domain.RoleResource;

public interface RoleResourceDao extends
		PagingAndSortingRepository<RoleResource, Long>,
		JpaSpecificationExecutor<RoleResource> {
	
	@Query("select u from RoleResource u where u.resource.id=?1 and u.role.id=?2")
	public RoleResource findRoleResourceOne(Long resid,Long roleid);
	
	@Query("select rr from RoleResource rr where rr.role.id=?1")
	List<RoleResource> getRoleResources(Long roleId);
	

	@Query("delete from RoleResource rr where rr.resource.id=?1")
	@Modifying
	void deleteByResourceId(Long resourceId);

	@Query("delete from RoleResource rr where rr.role.id=?1")
	@Modifying
	void deleteByRoleId(Long roleId);
	
}
