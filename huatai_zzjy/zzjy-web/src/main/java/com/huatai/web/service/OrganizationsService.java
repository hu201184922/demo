package com.huatai.web.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.OrgTag;
import com.huatai.web.model.Organization;

public interface OrganizationsService {

	int updateByPrimaryKeySelective(Organization record, OrgTag orgTag);

	int deleteByPrimaryKey(Organization record);

	Pager<Organization> findOrganizationPage(Pager<Organization> pager, Organization record);

	List<Organization> findOrganizationList();

	List<Organization> findOrganizationByProvComCode(String string);

	int insertSelective(Organization record);

	Pager<Organization> findTeamComPage(Pager<Organization> pager, Organization record);

	int insertOrgTag(Organization record, OrgTag orgTag);
	
	/***
	 * 查询总公司，分公司，中支
	 */
	List<DeptInfo> findOrganization();

	/**
	 * 根据机构名称查询机构代码
	 * @param string
	 */
	String findorgCodeByorgName(String orgName);

	/**
	 * 根据机构代码查询机构名
	 * @param orgCode
	 */
	String findOrgNameByOrgCode(String orgCode);

	/**
	 * 根据机构名称模糊查询
	 * @param monitorObject
	 */
	String findOrgNameByOrgCodeLike(String monitorObject);

	/**
	 * 查询机构简称
	 * @return
	 */
	List<Map<String, Object>> findOrgShortName();

}
