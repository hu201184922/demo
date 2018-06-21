/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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
import com.fairyland.jdp.core.security.utils.Digests;
import com.fairyland.jdp.core.utils.DateProvider;
import com.fairyland.jdp.core.utils.Encodes;
import com.fairyland.jdp.framework.exception.MetLifeCrmException;
import com.fairyland.jdp.framework.security.dao.UserDao;
import com.fairyland.jdp.framework.security.dao.UserRoleDao;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.domain.UserRole;
import com.fairyland.jdp.framework.security.mapper.UserHistoryMapper;
import com.fairyland.jdp.framework.security.mapper.UserMapper;
import com.fairyland.jdp.framework.util.DateUtil;
import com.fairyland.jdp.orm.Pager;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午3:39:42
 * @version V1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	private static final int SALT_SIZE = 16;

	private UserDao userDao;
	private HashService hashService;
	private DateProvider dateProvider = DateProvider.DEFAULT;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserHistoryMapper userHistoryMapper;


	@Override
	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public Page<User> getAllUsers(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		for (SearchFilter filter : filters.values()) {
			if ("false".equals(filter.value)) {
				filter.value = false;
			}
			if ("true".equals(filter.value)) {
				filter.value = true;
			}
			if ("lastLoginedTime".equals(filter.fieldName)) {
				try {
					filter.value = DateUtil.String2Date(filter.value);
				} catch (ParseException e) {
					logger.error(e.getMessage());
				}
			}
		}
		Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values());
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,sortType);
		return userDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Map<String, SortFilter> filters = SortFilter.parse(sortType);
		// 此处添加更多排序逻辑
		Sort sort = DynamicSort.bySortFilter(filters.values());
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fairyland.jdp.framework.security.service.IUserService#getUser(java
	 * .lang.Long)
	 */
	@Override
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fairyland.jdp.framework.security.service.IUserService#findUserByLoginName
	 * (java.lang.String)
	 */
	@Override
	public User findUserByLoginName(String loginName) {
		return userDao.findByAccount(loginName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fairyland.jdp.framework.security.service.IUserService#registerUser
	 * (com.fairyland.jdp.framework.security.domain.User)
	 */
	@Override
	public void registerUser(User user) {
		entryptPassword(user);
		user.setRegisterDate(dateProvider.getDate());

		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fairyland.jdp.framework.security.service.IUserService#updateUser(
	 * com.fairyland.jdp.framework.security.domain.User)
	 */
	@Override
	// @CachePut(value="UserCache",key="'LN:'+#user.loginName")
	public void updateUser(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fairyland.jdp.framework.security.service.IUserService#deleteUser(
	 * java.lang.Long)
	 */
	@Override
	public void deleteUser(Long id) {
		deleteUser(getUser(id));
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return user.getAccount();
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过指定次数指定加密算法的 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		Hash hash = hashService.computeHash(user.getPlainPassword(),
				user.getSalt());
		// 如使用Base64编码，需要将HashedCredentialsMatcher的storedCredentialsHexEncoded属性设置为false
		user.setPassword(hash.toHex());
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setHashService(HashService hashService) {
		this.hashService = hashService;
	}

	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		userRoleDao.save(userRole);
	}

	@Override
	public void deleteUserRole(UserRole userRole) {
		userRoleDao.delete(userRole);
	}

	@Override
	public UserRole findUserRole(Long userid, Long roleid) {
		return userRoleDao.findOne(userid, roleid);
	}

	// 通过Roleid找到匹配的用户
	public List<User> findMatchUsers(Long roleid) {
		return userDao.findMatchUsers(roleid);
	}

	// 通过Roleid找到未匹配的用户
	public List<User> findUnmatchUsers(Long roleid) {
		return userDao.findUnmatchUsers(roleid);
	}

	@Override
	@CachePut(value = "SecurityCache", key = "'Role_UID:'+#user.id")
	public void registerUser(User user, String roleIds) {
		entryptPassword(user);
		user.setRegisterDate(dateProvider.getDate());
		userDao.save(user);
		createUserRole(roleIds, user.getId());

	}

	@Override
	// @CachePut(value = "SecurityCache", key = "'Role_UID:'+#user.id")
	public void updateUser(User user, String roleIds) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);
		if (user.getUserRoles() != null) {
			userRoleDao.delete(user.getUserRoles());
		}
		createUserRole(roleIds, user.getId());
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@CachePut(value = "SecurityCache", key = "'Role_UID:'+#user.id")
	public void createUserRole(String roleIds, Long userId) {
		if (!StringUtils.isEmpty(roleIds)) {
			String[] ids = roleIds.split(",");
			for (String id : ids) {
				User u = new User();
				Role r = new Role();
				UserRole ur = new UserRole();
				u.setId(userId);
				r.setId(Long.valueOf(id));
				ur.setRole(r);
				ur.setUser(u);
				userRoleDao.save(ur);
			}
		}
	}

	@Override
	public void lock(String loginName) {
		User user = findUserByLoginName(loginName);
		user.setLocked(Boolean.TRUE);
		user.setLockedTime(new Date());
		updateUser(user);
	}

	@Override
	public boolean checkIfFirstLogin(User user) {
		user = getUser(user.getId());
		return !BooleanUtils.isFalse(user.getFirstLogined())
				|| user.getFirstLoginedTime() == null;
	}

	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

	@Override
	public boolean modifyPassword(Long userId, String oldPassword,
			String newPassword) {
		User user = userDao.findById(userId);
		String oldEntryptPassword = entryptPassword(oldPassword, user.getSalt());
		if (oldEntryptPassword.equals(user.getPassword())) {

			user.setPlainPassword(newPassword);
			updateUser(user);
			return true;

		}
		return false;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过指定次数指定加密算法的 hash
	 */
	private String entryptPassword(String password, String salt) {
		Hash hash = hashService.computeHash(password, salt);
		// 如使用Base64编码，需要将HashedCredentialsMatcher的storedCredentialsHexEncoded属性设置为false
		return hash.toHex();
	}

	@Override
	public void updateFirstLoginPsw(User user) {
		user.setFirstLogined(Boolean.TRUE);
		user.setFirstLoginedTime(new Date());
		updateUser2(user);
		Subject subject = SecurityUtils.getSubject();
		User u = (User) subject.getPrincipal();
		try {
			PropertyUtils.copyProperties(u, user);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateProfileUser(User user) {
		updateUser(user);
		Subject subject = SecurityUtils.getSubject();
		User u = (User) subject.getPrincipal();
		try {
			PropertyUtils.copyProperties(u, user);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public String resetPswUser(Long id) {
		User user = getUser(id);
		StringBuffer plainPassword = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			int t = r.nextInt(10);
			plainPassword.append(t);
		}
		user.setPlainPassword(plainPassword.toString());
		entryptPassword(user);
//		user.setFirstLogined(Boolean.FALSE);
		userDao.save(user);
		return plainPassword.toString();
	}


	@Override
	// @CachePut(value="UserCache",key="'LN:'+#user.loginName")
	public void updateUser2(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
//			byte [] salt={1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6}; 
//			Hash hash = hashService.computeHash(user.getPlainPassword(),
//					Encodes.encodeHex(salt));
//			String password = hash.toHex();
			entryptPassword(user);
			userDao.save(user);
//			Integer count = 0;
//			if (count >= 1) {
//				throw new MetLifeCrmException("已使用过的密码");
//			} else {
//				Boolean flag = userHistoryMapper.todayIsSaved(user.getId());
//				if (flag != null && flag) {
//					throw new MetLifeCrmException("今天已经修改过密码");
//				}
//				Integer ct = userHistoryMapper.getCount(user.getId(), null);
//				if (ct >= 12) {
//					userHistoryMapper.deleteLastHistory(user.getId());
//				}
//			
//				userHistoryMapper.saveHistory(user.getId(), password);
//			
//			}

		}

	}

	@Override
	public void setUserDisable() {
		userMapper.setUserDisabled();
	}

	@Override
	public User findUserByAccountAndIdNo(String account, String idNo) {
		return userDao.findUserByAccountAndIdNo(account, idNo);
	}

	@Override
	/**
	 * 通过map集合查找用户
	 */
	public Pager<User> getUsersByParams(Pager<User> pager,
			Map<String, Object> searchParams) {
		return userMapper.getUsersByParams(pager, searchParams);
	}

	@Override
	public Pager<User> getUsersByExample(Pager<User> pager, User user) {
		return userMapper.getUsersByExample(pager, user);
	}

	@Override
	public List<User> getUsersByExample2(User user) {
		// TODO Auto-generated method stub
		return userMapper.getUsersByExample2(user);
	}

	@Override
	public String getOrgCode(String orgCode) {
		// TODO Auto-generated method stub
		return userMapper.getOrgCode(orgCode);
	}

}
