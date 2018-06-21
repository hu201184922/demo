package com.ehuatai.biz.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

@MyBatisRepository
@DataSource(name = "dataSource2", value = "dataSource2")
public interface SQLMapper {

	public List<HashMap<String, Object>> getBranchByCompany(@Param("code") String code);

	// 总公司
	public List<HashMap<String, Object>> searchOrg01(@Param("code") String code, @Param("type") Integer type);

	// 分公司
	public List<HashMap<String, Object>> searchOrg02(@Param("code") String code, @Param("type") Integer type);

	// 中支
	public List<HashMap<String, Object>> searchOrg03(@Param("code") String code);

	public List<Map<String, Object>> findBySQL(@Param(value = "sql") String sql);

	public Pager<Map<String, Object>> findPagerBySQL(Pager<Map<String, Object>> pager, @Param("sql") String sql);
}
