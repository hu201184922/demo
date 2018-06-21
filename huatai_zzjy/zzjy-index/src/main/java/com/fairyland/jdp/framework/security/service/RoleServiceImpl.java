package com.fairyland.jdp.framework.security.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.core.persistence.DynamicSort;
import com.fairyland.jdp.core.persistence.DynamicSpecifications;
import com.fairyland.jdp.core.persistence.SearchFilter;
import com.fairyland.jdp.core.persistence.SortFilter;
import com.fairyland.jdp.framework.security.dao.RoleDao;
import com.fairyland.jdp.framework.security.dao.RoleResourceDao;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.RoleResource;
@Transactional
@Service(value="roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Override
	public void createRole(Role role) {
		roleDao.save(role);
	}
	@Override
	public Role readRole(Long roleId) {
		return roleDao.findOne(roleId);
	}
	@Override
	public void updateRole(Role role) {
		roleDao.save(role);
	}
	@Override
	public void deleteRole(Long roleId) {
		roleDao.delete(roleId);
	}
	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);

	}
	public List<Role> findAll(){
		return  (List<Role>) roleDao.findAll();
	}
	public Page<Role> getAllRoles(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Role> spec = DynamicSpecifications
				.bySearchFilter(filters.values());
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortType);
		return roleDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Map<String, SortFilter> filters = SortFilter.parse(sortType);
		// 此处添加更多排序逻辑
		Sort sort = DynamicSort.bySortFilter(filters.values());
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	//找到已分配的角色
	public List<Role> matchRoles(Long userid) {
		return roleDao.matchRoles(userid);
	}
	//找到未分配的角色
	public List<Role> unmatchRoles(Long userid) {
		return roleDao.unmatchRoles(userid);
	}
	@Override
	public List<Role> matchResourceRoles(Long resourceid) {
		return roleDao.matchResourceRole(resourceid);
	}
	@Override
	public List<Role> unmatchResourceRoles(Long resourceid) {
		return roleDao.unmatchResourceRole(resourceid);
	}
	@Override
	public Role getRole(String name) {
		return roleDao.findByName(name);
	}
	@Override
	public Role getRoleByCode(String code){
		return roleDao.findByCode(code);
	}
	@Override
	public void createRole(Role role, String resIds) {
		roleDao.save(role);
		
	}
	private void createRoleResource(String resIds,Long roleId){
		if(!StringUtils.isEmpty(resIds)){
			String[] ids=resIds.split(",");
			for(String id:ids){
				RoleResource roleResource=new RoleResource();
				Resource resource=new Resource();
				resource.setId(Long.valueOf(id));
				Role role = new Role();
				role.setId(roleId);
				roleResource.setRole(role);
				roleResource.setResource(resource);
				roleResourceDao.save(roleResource);
			}
		}
	}
	@Override
	public void updateRole(Role role, String resIds) {
		roleDao.save(role);
		if(role.getUserRoles()!=null){
			roleResourceDao.delete(role.getRoleResources());
		}
		createRoleResource(resIds,role.getId());
	}
	@Override
	public List<Role> getRoleByRoleCategory() {
		
		return (List<Role>) roleDao.findAll();
	}
	@Override
	public List<Role> findByType(String type) {
		return roleDao.findByType(type);
	}
	
}
