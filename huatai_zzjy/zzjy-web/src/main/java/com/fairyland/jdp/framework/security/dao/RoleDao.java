package com.fairyland.jdp.framework.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.security.domain.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long>,JpaSpecificationExecutor<Role> {
//	@Query("select a from Role a,UserRole  b where a.id=b.role.id and b.user.id=?1")
	@Query("select r from UserRole ur join ur.role r where ur.user.id=?1 ")
	public List<Role> matchRoles(Long userid);
		
	@Query("select aa from Role aa where aa.id not in(select b.role.id from UserRole b where b.user.id=?1 and b.role.id is not null)")
	public List<Role> unmatchRoles(Long userid);
	
	@Query("select a from Role a,RoleResource  b where a.id=b.role.id and b.resource.id=?1")
	public List<Role> matchResourceRole(Long resourceId);
	
	@Query("select aa from Role aa where aa.id not in(select b.role.id from RoleResource b where b.resource.id=?1)")
	public List<Role> unmatchResourceRole(Long resourceId);
	Role findByCode(String code);
	Role findByName(String name);

	public List<Role> findByType(String type);
	
	@Query("select ur.role from UserRole ur left join ur.user u where u.id=?1")
	public List <Role> findUserRolesByUserId(Long userId);
}
