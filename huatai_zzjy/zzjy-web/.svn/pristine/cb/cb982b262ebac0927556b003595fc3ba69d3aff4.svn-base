package com.huatai.web.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

@MyBatisRepository
@DataSource(name = "dataSource2")
public interface SQLMapper {

	public List<Map<String, Object>> findBySQL(@Param(value = "sql") String sql);

	public List<HashMap<String, Object>> getBranchByCompany(@Param("code") String code);

}
