package com.fairyland.jdp.framework.security.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.dao.ResourceDao;
import com.fairyland.jdp.framework.security.dao.RoleDao;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

@Service(value="userAuthService")
public class UserAuthServiceImpl implements UserAuthService {
	@javax.annotation.Resource
	private RoleDao roleDao;
	@javax.annotation.Resource
	private ResourceDao resourceDao;

	@Override
	public Set<Role> getRolesByUserId(Long userId) {
		Set<Role> roles = Sets.newHashSet();

		// 用户直属角色
		List<Role> userRoles = roleDao.findUserRolesByUserId(userId);
		roles.addAll(userRoles);
		// 用户所在组的角色
//		List<Role> groupRoles = roleDao.findGroupRolesByUserId(userId);
//		roles.addAll(groupRoles);
		// 如果有基于组织机构或其它方式的角色分派，均须在此提取
		return roles;
	}

	@Override
	public Set<Resource> getResourcesByUserId(Long userId) {
		Set<Resource> resources = Sets.newHashSet();

		// 用户直属角色
		List<Resource> userRoleResources = resourceDao.findUserRoleResourcesByUserId(userId);
		resources.addAll(userRoleResources);
		// 用户所在组的角色
//		List<Resource> groupRoleResources = resourceDao.findGroupRoleResourcesByUserId(userId);
//		resources.addAll(groupRoleResources);
		// 如果有基于组织机构或其它方式的角色分派，均须在此提取
		return resources;
	}

	@Override
//缓存同步问题涉及面广，暂不使用	@Cacheable(value="SecurityCache", key="'Role_UID:'+#userId.toString()")
	public Set<String> getRoleStringsByUserId(Long userId) {
		Set<Role> roles = getRolesByUserId(userId);
		return Sets.newHashSet(Collections2.transform(roles,
				new Function<Role, String>() {
					@Override
					public String apply(Role input) {
						return input.getCode();
					}
				}));
	}

	@Override
//	@Cacheable(value="SecurityCache", key="'Res_UID:'+#userId.toString()")
	public Set<String> getResourceStringsByUserId(Long userId) {
		Set<Resource> resources = getResourcesByUserId(userId);
		return Sets.newHashSet(Collections2.transform(resources,
				new Function<Resource, String>() {
					@Override
					public String apply(Resource input) {
						return input.getPerString();
					}
				}));
	}
}
