package com.fairyland.jdp.framework.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.dao.UserDao;
import com.fairyland.jdp.framework.security.domain.Organization;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.domain.UserModel;
import com.fairyland.jdp.framework.security.mapper.OrganizationMapper;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationMapper mapper;
	@Autowired
	private UserDao userDao;

	@Override
	public List<Organization> getOrganizationByPid(Long pid) {
		List<Organization> list = mapper.getOrganizationByPid(pid);
		Collections.sort(list,new Comparator<Organization>(){

			@Override
			public int compare(Organization o1, Organization o2) {
				return o1.getOrgid().compareTo(o2.getOrgid());
			}
			
		});
		return list;
	}

	@Override
	public List<Organization> getAllOrganization() {
		return mapper.getAllOrganization();
	}

	


}
