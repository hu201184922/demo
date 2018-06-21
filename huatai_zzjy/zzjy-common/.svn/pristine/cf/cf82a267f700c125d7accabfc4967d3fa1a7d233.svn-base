package com.fairyland.jdp.framework.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.fairyland.jdp.framework.security.domain.Organization;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface OrganizationMapper {
	List<Organization> getOrganizationByPid(@Param("pid") Long pid);
	List<Organization> getAllOrganization();
	
	
}
