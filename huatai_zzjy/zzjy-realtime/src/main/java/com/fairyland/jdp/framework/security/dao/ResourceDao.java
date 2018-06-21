package com.fairyland.jdp.framework.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.User;

public interface ResourceDao extends
		PagingAndSortingRepository<Resource, Long>,
		JpaSpecificationExecutor<Resource> {


	
	public List<Resource> findByResType(String resType);

	@Query("select aa from Resource aa where aa.resType=?1 and aa.enabled = true")
	public List<Resource> findEnableResourcesByResType(String resType);

	Resource findByName(String name);

	Resource findById(Long Id);
	
	List<Resource> findByResString(String resString);

	List<Resource> findByPerString(String perString);



	@Query("select r from RoleResource rr join rr.resource r where rr.role.id=?1")
	List<Resource> findRoleResourceByType(Long roleId);



	// FIXME 优化
	@Query("select rr.resource from User u left join u.userRoles ur left join ur.role r left join r.roleResources rr  "
			+ "where u.id=?1 and rr.resource.enabled = true and rr.resource.resType='U'")
	public List<Resource> findUserRoleResourcesByUserId(Long userId);

//	// FIXME 优化
//	@Query("select  rr.resource from User u left join u.userGroups ug left join ug.group g left join g.groupRoles gr left join gr.role r left join r.roleResources rr "
//			+ "where u.id=?1 and rr.resource.enabled = true")
//	public List<Resource> findGroupRoleResourcesByUserId(Long userId);
	
	@Query("select b from Resource  b order by b.sortIndex asc")
	public List<Resource> findAllResource();
	
	@Query("select a from Resource a where a.resType='G' and a.pid is null")
	public List<Resource> findResourceByGroup();
	
	@Query("select a from Resource a where a.pid=?1")
	public List<Resource> findResourceByPid(Long id);
	
}
