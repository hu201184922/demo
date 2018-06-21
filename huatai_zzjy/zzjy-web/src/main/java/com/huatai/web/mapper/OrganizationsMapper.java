package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.Organization;
import com.huatai.web.model.OrganizationExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

@DataSource(name="dataSource2")
@MyBatisRepository
public interface OrganizationsMapper {
    int countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(String managecomcode);

    int insert(Organization record);

    int insertSelective(Organization record);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(String managecomcode);

    int updateByExampleSelective(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

	Pager<Organization> findOrganizationPage(Pager<Organization> pager,@Param("record") Organization record);

	List<Organization> findOrganizationList();

	List<Organization> findOrganizationByProvComCode(@Param("provComCode")String provComCode);

	Pager<Organization> findTeamComPage(Pager<Organization> pager,@Param("record") Organization record);
	
	List<DeptInfo> findOrganization();

	String findorgCodeByorgName(@Param("orgName")String orgName);

	String findOrgNameByOrgCode(@Param("orgCode")String orgCode);

	String findOrgNameByOrgCodeLike(@Param("monitorObject")String monitorObject);

	List<Map<String,Object>> findOrgShortName();
}