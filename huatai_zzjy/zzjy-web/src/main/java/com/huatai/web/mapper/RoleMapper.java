package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Role;
import com.huatai.web.model.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(String roleCode);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(String roleCode);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	Pager<Role> findRoleByPage(Pager<Role> pager, Role record);

	Role selectByPrimaryKey(Long id);

	String findroleCodeByroleName(@Param("roleName")String roleName);
}