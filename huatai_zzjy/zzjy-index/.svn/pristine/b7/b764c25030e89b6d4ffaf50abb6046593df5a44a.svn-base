package com.ehuatai.biz.organization.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ehuatai.biz.organization.domain.Organization;
import com.ehuatai.biz.organization.mapper.OrgMapper;
import com.ehuatai.biz.organization.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgMapper mapper;
	@Override
	public List<Organization> findOrgByRoleOrg(String roleOrg) {
		if(StringUtils.isEmpty(roleOrg)) {
			return new ArrayList<Organization>();
		}
		int len = 1;//默认总公司 
		
		if(roleOrg.length() == 1) {
			//当前人为总公司
			len = 3;
			roleOrg = null;
		}else if(roleOrg.length() == 3) {
			//当前人为分公司
			len = 5;
		}
		return mapper.findOrgByRoleOrg(len, roleOrg);
	}

}
