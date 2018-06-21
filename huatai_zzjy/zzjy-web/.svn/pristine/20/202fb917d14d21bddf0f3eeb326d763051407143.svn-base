package com.fairyland.jdp.framework.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
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
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.menu.dao.MenuItemDao;
import com.fairyland.jdp.framework.menu.domain.MenuItem;
import com.fairyland.jdp.framework.security.annotation.InitFilterChain;
import com.fairyland.jdp.framework.security.dao.ResourceDao;
import com.fairyland.jdp.framework.security.dao.RoleResourceDao;
import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.RoleResource;
import com.fairyland.jdp.framework.security.mapper.ResourceMapper;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private MenuItemDao menuItemDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private ResourceMapper resourceMapper;
	
	

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void createResource(Resource resource) {
		resourceDao.save(resource);
	}

	@Override
	public Resource readResource(Long resourceId) {
		return resourceDao.findOne(resourceId);
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void updateResource(Resource resource) {
		resourceDao.save(resource);
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void deleteResource(Long resourceId) {
		resourceDao.delete(resourceId);
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void deleteResource(Resource resource) {
		resourceDao.delete(resource);
	}

	@Override
	public Page<Resource> getAllResource(Map<String, Object> searchParams,
			int pageNumber, int pagesize, String sortType) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Resource> spec = DynamicSpecifications
				.bySearchFilter(filters.values());
		PageRequest pageRequest = buildPageRequest(pageNumber, pagesize,
				sortType);
		return resourceDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Map<String, SortFilter> filters = SortFilter.parse(sortType);
		// 此处添加更多排序逻辑
		Sort sort = DynamicSort.bySortFilter(filters.values());
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	@Transactional(readOnly = false)
	@InitFilterChain
	public void saveRoleResource(RoleResource roleResource) {
		roleResourceDao.save(roleResource);
	}

	public RoleResource findRoleResourceOne(Long resid, Long roleid) {
		return roleResourceDao.findRoleResourceOne(resid, roleid);
	}

	@Transactional(readOnly = false)
	@InitFilterChain
	public void removeRoleResource(RoleResource roleresource) {
		roleResourceDao.delete(roleresource);
	}

	@Override
	public List<Resource> findByType(String type, Long rescategoryid) {
		return null;
	}



	@Override
	public List<RoleResource> findRoleResources(Long roleId) {
		return roleResourceDao.getRoleResources(roleId);
	}

	@Override
	@InitFilterChain
	public void deleteRoleResources(List<RoleResource> list) {
		roleResourceDao.delete(list);
	}

	@Override
	public Resource getResource(String name) {
		return resourceDao.findByName(name);
	}

	@Override
	public Resource getResourceByResString(String resString) {
		List<Resource> resources = resourceDao.findByResString(resString);
		return resources.size()>0 ? resources.get(0) : null;
	}

	@Override
	public Resource getResourceByPerString(String perString) {
		List<Resource> resources =  resourceDao.findByPerString(perString);
		return resources.isEmpty()? null : resources.get(0);
	}

	@Override
	@Transactional
	@InitFilterChain
	public void createResourceByMenuId(Long menuId) {
		if(TreeNodeEntity.ROOT_PARENT_ID.equals(menuId)){
			List<MenuItem> menus=menuItemDao.findByParentIdIsNullOrderBySortIndexAsc();
			for (MenuItem menuItem : menus) {
				createResourceByMenuId(menuItem, null);
			}
		}else{
			MenuItem menu = menuItemDao.findOne(menuId);
			createResourceByMenuId(menu, null);
		}
	}

	private void createResourceByMenuId(MenuItem menu, Resource parent) {
		Resource resource = new Resource();
		resource.setName(menu.getName());
		resource.setResType(Resource.RES_TYPE_URL);
		resource.setResString(menu.getUrl());
		resource.setPerString(menu.getPermString());
		resource.setSortIndex(menu.getSortIndex());
		resource.setEnabled(Boolean.TRUE);
//		resource.setParent(parent);
		resourceDao.save(resource);

		for (MenuItem child : menu.getChildren()) {
			createResourceByMenuId(child, resource);
		}
	}
	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void deleteResources(List<Resource> resources) {
		resourceDao.delete(resources);
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void resource2Role(Resource resource, List<Role> roles) {
		roleResourceDao.deleteByResourceId(resource.getId());
		for (Role role : roles) {
			RoleResource roleResource =  new RoleResource();
			roleResource.setResource(resource);
			roleResource.setRole(role);
			roleResourceDao.save(roleResource);
		}
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void createResource(Resource resource, String categoryIds) {
		resourceDao.save(resource);
		
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void updateResource(Resource resource, String categoryIds) {
		resourceDao.save(resource);

	}
	


	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void updateResources(List<Resource> resources) {
		resourceDao.save(resources);
	}

	@Override
	public List<Resource> findAllResource() {
		return resourceDao.findAllResource();
	}



	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void updateRoleResources(Long roleId,List<Long> resourceIds){
		roleResourceDao.deleteByRoleId(roleId);
		for (Long resourceId : resourceIds) {
			RoleResource roleResource =  new RoleResource();
			Resource resource = new Resource();
			resource.setId(resourceId);
			Role role = new Role();
			role.setId(roleId);
			
			roleResource.setResource(resource);
			roleResource.setRole(role);
			roleResourceDao.save(roleResource);
		}
	}



	@Override
	@Transactional
	@InitFilterChain
	public void deleteResourcesByIds(List<Long> resourceIds) {
		for (Long resourceId : resourceIds) {
			resourceDao.delete(resourceId);
		}
	}

	@Override
	@Transactional(readOnly = false)
	@InitFilterChain
	public void createResources(List<Resource> resources) {
//		resourceDao.save(resources);
		for (Resource resource : resources) {
			resourceDao.save(resource);
		}
	}

	@Override
	public List<Resource> getResourceByGroup(Long id) {
		if(id==null){
			return resourceDao.findResourceByGroup();
		}
		return resourceDao.findResourceByPid(id);
	}

	@Override
	public void createRelation(Long id, Long groupid) {
		resourceMapper.createRelation(id, groupid);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class)
	public Boolean deleteAllResource(Long id) {
//		List<Long> list = resourceMapper.getAllResource(id);
		List<Long> list = new ArrayList<Long>();
		getResources(id,list);
//		resourceMapper.deleteParentId(id);
		for(Long cid:list){
			resourceMapper.deleteMtRoleRes(cid);
		}
		for (Long cid : list) {
			resourceDao.delete(cid);
		}
//		resourceMapper.deleteResourceById(id);
		return true;
	}
	
	public void getResources(Long cid,List<Long> list){
		list.add(cid);
		List<Long> list1 = resourceMapper.getResourceByPid(cid);
		if(CollectionUtils.isEmpty(list1))
			return;
		else{
			for (Long id : list1) {
				getResources(id,list);
			}
		}
	}

}
