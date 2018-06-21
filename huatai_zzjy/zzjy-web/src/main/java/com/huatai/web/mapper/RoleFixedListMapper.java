package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.RoleFixedList;
import com.huatai.web.model.RoleFixedListExample;

public interface RoleFixedListMapper {
    int countByExample(RoleFixedListExample example);

    int deleteByExample(RoleFixedListExample example);

    int deleteByPrimaryKey(Long rflId);

    int insert(RoleFixedList record);

    int insertSelective(RoleFixedList record);

    List<RoleFixedList> selectByExample(RoleFixedListExample example);

    RoleFixedList selectByPrimaryKey(Long rflId);

    int updateByExampleSelective(@Param("record") RoleFixedList record, @Param("example") RoleFixedListExample example);

    int updateByExample(@Param("record") RoleFixedList record, @Param("example") RoleFixedListExample example);

    int updateByPrimaryKeySelective(RoleFixedList record);

    int updateByPrimaryKey(RoleFixedList record);

	Pager<RoleFixedList> findByPager(Pager<RoleFixedList> pager, RoleFixedList record);

	List<RoleFixedList> findByRoleCode(@Param("roleCode") String roleCode);

	int deleteByRoleCode(@Param("roleCode")String roleCode);

	int updateByRoleCodeSelective(RoleFixedList record);

	List<RoleFixedList> findByIsRoleCode(@Param("roleCode")String roleCode);

	int updateByRoleCodeAndFlCodeSelective(RoleFixedList record);

}