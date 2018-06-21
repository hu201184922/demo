package com.fairyland.jdp.framework.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fairyland.jdp.framework.security.domain.User;

public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User> {
	@Query("select aa from User aa where Lower(aa.account)=Lower(?1)")
	User findByAccount(String account);
	@Query("select a from User a,UserRole  b where a.id=b.user.id and b.role.id=?1")
	public List<User> findMatchUsers(Long roleid);
	
	@Query("select aa from User aa where aa.id not in(select b.user.id from UserRole b where b.role.id=?1)")
	public List<User> findUnmatchUsers(Long roleid);
	
	
	User findById(Long Id);
	
	User findByUserName(String userName);
	
	@Query("select b from User  b order by b.registerDate asc")
	public List<User> findAllUser();
	@Query("select aa from User aa where Lower(aa.account)=Lower(?1) and aa.idNo=?2")
	User findUserByAccountAndIdNo(String account, String idNo);
	
}
