package com.fairyland.jdp.framework.security.mapper;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface UserMapper {
	
	void setUserDisabled();
	public String getOrgCode(String orgCode);
    Pager<User> getUsersByParams(Pager<User> pager ,Map<String, Object> searchParams);
    Pager<User> getUsersByExample(Pager<User> pager ,User user);
    List<User> getUsersByExample2(User user);
}
