package com.fairyland.jdp.framework.security.mapper;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface UserHistoryMapper {
	void saveHistory(@Param("userId")Long userId, @Param("password")String password);

	Integer getCount(@Param("userId")Long userId, @Param("password")String password);
	
	void deleteLastHistory(@Param("userId")Long userId);
	
	Boolean todayIsSaved(@Param("userId")Long userId);
}
