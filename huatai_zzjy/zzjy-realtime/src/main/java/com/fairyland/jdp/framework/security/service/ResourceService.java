package com.fairyland.jdp.framework.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.security.domain.Resource;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.RoleResource;

public interface ResourceService {
	
	void createResource(Resource resource);

	void createResource(Resource resource,String categoryIds);
	
	void createResourceByMenuId(Long menuId);

	Resource readResource(Long resourceId);

	void updateResource(Resource resource);

	void updateResources(List<Resource> resources);

	void updateResource(Resource resource,String categoryIds);

	void deleteResource(Long resourceId);

	void deleteResource(Resource resource);

	void deleteResources(List<Resource> resources);

	void deleteResourcesByIds(List<Long> resourceIds);

	Page<Resource> getAllResource(Map<String, Object> searchParams,
			int pageNumber, int pagesize, String sortType);

	public void saveRoleResource(RoleResource roleResource);

	/**
	 * 将资源分配给角色
	 * @param resource
	 * @param roles
	 */
	public void resource2Role(Resource resource, List<Role> roles);

	/**
	 * 更新角色资源
	 * @param roleId
	 * @param resourceIds
	 */
	public void updateRoleResources(Long roleId,List<Long> resourceIds);

	public RoleResource findRoleResourceOne(Long resid, Long roleid);

	public void removeRoleResource(RoleResource roleresource);
    /**
     * 根据类型获取当前的资源
     * @param type
     * @param rescategoryid
     * @return
     */
	public List<Resource> findByType(String type, Long rescategoryid);


	List<RoleResource> findRoleResources(Long roleId);

	void deleteRoleResources(List<RoleResource> list);

	public Resource getResource(String name);

	public Resource getResourceByResString(String resString);

	public Resource getResourceByPerString(String perString);
	
	/**
	 * 查找所有资源
	 * @return 
	 */
	public List<Resource> findAllResource();
	


	/**
	 * 创建资源列表
	 * 主要用于批量导入
	 * @param resources
	 */
	public void createResources(List<Resource> resources);
	
	
	List<Resource> getResourceByGroup(Long id);
	
	void createRelation(Long id,Long groupid);
	
	Boolean deleteAllResource(Long id);
}
