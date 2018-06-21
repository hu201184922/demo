/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.exception.MetLifeCrmException;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.domain.UserRole;
import com.fairyland.jdp.orm.Pager;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午3:37:58
 * @version V1.0
 */
public interface UserService {

	public abstract List<User> getAllUser();
    public String getOrgCode(String orgCode);
	public Page<User> getAllUsers(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType);
	
	/**
	 * 通过map集合查找用户
	 * @param pager 
	 * @param searchParams
	 * @return
	 */
	public Pager<User> getUsersByParams(Pager<User> pager ,Map<String, Object> searchParams);

	public Pager<User> getUsersByExample(Pager<User> pager ,User user);
	public List<User> getUsersByExample2(User user);
	 
	public abstract User getUser(Long id);

	public abstract User findUserByLoginName(String loginName);

	void  registerUser(User user,String roleIds);
	
	void updateUser(User user,String roleIds);
	
	public abstract void registerUser(User user);

	public abstract void updateUser(User user);

	public abstract void deleteUser(Long id);

	public abstract void deleteUser(User user);

	/**
	 * 添加一个用户角色
	 * 
	 * @param userRole
	 */
	public abstract void saveUserRole(UserRole userRole);

	/**
	 * 删除一个用户角色
	 * 
	 * @param userRole
	 */
	public abstract void deleteUserRole(UserRole userRole);

	/**
	 * 获取用户的一个角色
	 * 
	 * @param userid
	 * @param roleid
	 * @return
	 */
	public abstract UserRole findUserRole(Long userid, Long roleid);

	/**
	 * 获取角色已经分配的用户
	 * 
	 * @param roleid
	 * @return
	 */
	public List<User> findMatchUsers(Long roleid);

	/**
	 * 获取角色未分配的用户
	 * 
	 * @param roleid
	 * @return
	 */
	public List<User> findUnmatchUsers(Long roleid);

	
	/**
	 * 锁定用户
	 * 
	 * @param groupId
	 * @return
	 */
	public void lock(String loginId);
	
	/**
	 * 检查是否首次登录系统
	 * 
	 * @param groupId
	 * @return
	 */
	public boolean checkIfFirstLogin(User user);
	
	/**
	 * 查找所有用户
	 */
	public List<User> findAllUser();
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean modifyPassword(Long userId,String oldPassword,String newPassword);

	public abstract void updateFirstLoginPsw(User user);

	public abstract void updateProfileUser(User user);
	
	void createUserRole(String roleIds, Long userId);

	/**
	 * 重置密码
	 * @param id
	 * @return 新密码
	 */
	public abstract String resetPswUser(Long id);
	
	
	void updateUser2 (User user) throws MetLifeCrmException;
	
	void setUserDisable();

	public abstract User findUserByAccountAndIdNo(String account, String idNo);

}