package com.fairyland.jdp.framework.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.security.domain.Role;

public interface RoleService {
	/**
	 * 创建角色
	 * @param role
	 * @param resIds
	 */
    void createRole(Role role,String resIds);
    
    /**
     * 更新角色
     * @param role
     * @param resIds
     */
    void updateRole(Role role,String resIds);
    
	void createRole(Role role);

	Role readRole(Long roleId);

	void updateRole(Role role);

	void deleteRole(Long roleId);

	void deleteRole(Role role);

	List<Role> findAll();

	public Page<Role> getAllRoles(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType);
    /**
     * 获取用户已经匹配的角色
     * @param userid
     * @return
     */
	public List<Role> matchRoles(Long userid);
    /**
     * 获取用户未匹配的角色
     * @param userid
     * @return
     */
	public List<Role> unmatchRoles(Long userid);
    /**
     * 获取资源匹配的角色
     * @param resourceid
     * @return
     */
	public List<Role> matchResourceRoles(Long resourceid);
	
	public Role getRole(String name);
    /**
     * 获取资源未匹配的角色
     * @param resourceid
     * @return
     */
	public Role getRoleByCode(String code);
	public List<Role> unmatchResourceRoles(Long resourceid);
	
	public List<Role> getRoleByRoleCategory();
	
	public List<Role> findByType(String type);
}
