package com.ehuatai.biz.organization.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehuatai.biz.organization.domain.Organization;

public interface OrgService {

	/**
	 * <pre>
	 * 1、managecomcode 长度为1 是总公司 长度等于3的是分公司 等于5的是中支 
	 * 2、根据长度和provcomcod获取分公司下级机构中支
	 * 3、获取分公司的时候roleOrg为空,即角色为总公司的时候roleOrg=null
	 * &#64;param roleOrg
	 * &#64;return
	 * </pre>
	 */
	public List<Organization> findOrgByRoleOrg(@Param("roleOrg")  String roleOrg);
}
