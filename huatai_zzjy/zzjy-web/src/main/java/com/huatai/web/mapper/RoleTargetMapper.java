package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.RoleTarget;
import com.huatai.web.model.RoleTargetExample;

@MyBatisRepository
public interface RoleTargetMapper {
    int countByExample(RoleTargetExample example);

    int deleteByExample(RoleTargetExample example);

    int deleteByPrimaryKey(String roleCode);

    int insert(RoleTarget record);

    int insertSelective(RoleTarget record);

    List<RoleTarget> selectByExample(RoleTargetExample example);

    RoleTarget selectByPrimaryKey(Integer rtId);

    int updateByExampleSelective(@Param("record") RoleTarget record, @Param("example") RoleTargetExample example);

    int updateByExample(@Param("record") RoleTarget record, @Param("example") RoleTargetExample example);

    int updateByPrimaryKeySelective(RoleTarget record);

    int updateByPrimaryKey(RoleTarget record);

	Pager<RoleTarget> findRoleTargetByPage(Pager<RoleTarget> pager, RoleTarget record);

	int updateRoleTargetByRoleCode(RoleTarget roleTarget);

	List<RoleTarget> findRoleTargetByRoleCode(@Param("roleCode")String roleCode,@Param("depts") String depts);

	List<RoleTarget> findRoleTargetAll();

	List<TargetBean> getPreDeatailByRoleCode(@Param("role") String role,@Param("premium") String premium);

	int updateByRoleCodeSelective(RoleTarget record);

	List<RoleTarget> findByIsRoleCode(@Param("roleCode")String roleCode);

	int updateByRoleTarget(RoleTarget record);

}