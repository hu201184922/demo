package com.fairyland.jdp.framework.security.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fairyland.jdp.framework.security.domain.UserRole;
public interface UserRoleDao extends CrudRepository<UserRole, Long> {
	@Query("select u from UserRole u where u.user.id=?1 and u.role.id=?2")
	public UserRole findOne(Long userid,Long roleid);
	
	@Query("select u from UserRole u where u.role.id=?1")
	public List<UserRole> findByRoleId(Long roleid);
}
