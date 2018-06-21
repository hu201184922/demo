package com.fairyland.jdp.framework.organizational.city.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.framework.organizational.city.domain.City;
import com.fairyland.jdp.framework.security.domain.Organization;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface CityMapper {
	
	List<City> getCitys(Map<String,Object> map);

	List<City> getCitysByRoleCode(Map<String,Object> map);
	
	String getCityNameByCode(@Param("orgCode") String orgCode);
}