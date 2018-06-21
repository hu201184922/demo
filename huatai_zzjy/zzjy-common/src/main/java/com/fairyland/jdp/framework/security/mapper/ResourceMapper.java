package com.fairyland.jdp.framework.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface ResourceMapper {
	void createRelation(@Param("id")Long id,@Param("groupId")Long groupId);
	
	List<Long> getAllResource(Long id);
	
	void deleteParentId(Long id);
	
	void deleteResourceById(Long id);
}
