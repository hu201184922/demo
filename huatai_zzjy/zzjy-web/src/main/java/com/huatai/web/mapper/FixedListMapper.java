package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.FixedListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FixedListMapper {
	int countByExample(FixedListExample example);

	int deleteByExample(FixedListExample example);

	int deleteByPrimaryKey(FixedList record);

	int insert(FixedList record);

	int insertSelective(FixedList record);

	List<FixedList> selectByExample(FixedListExample example);

	int updateByExampleSelective(@Param("record") FixedList record, @Param("example") FixedListExample example);

	int updateByExample(@Param("record") FixedList record, @Param("example") FixedListExample example);

	Pager<FixedList> findByPager(Pager<FixedList> pager, FixedList record);

	int updateByPrimaryKey(FixedList record);

	List<FixedList> findAll();

	List<FixedList> findByFlDeptCode(@Param("flDeptCode") String flDeptCode,@Param("roleCode") String roleCode);

	List<FixedList> findDeptCodeByGroup();

	int deleteByRoleCode(String roleCode);

	List<FixedList> findFixedListByRoleAndDept(@Param("role") String role, @Param("dept") String dept);

	List<FixedList> findFixedListDeptByRole(@Param("role") String role);

	FixedList findFixedByFlCode(@Param("flCode")String flCode);

}