package com.fairyland.jdp.springboot.shiro.realm;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserAuthService;
import com.fairyland.jdp.framework.security.service.UserService;

//@Component("casRealm")
public class SSORealm extends CasRealm {
//	@Autowired
//	protected UserService userService;
	
//	@Autowired
//	protected UserAuthService userAuthService;
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected UserAuthService userAuthService;
	
	public static String DATA_PERM = "DATA_PERM";
	
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}

//	public void setUserAuthService(UserAuthService userAuthService) {
//		this.userAuthService = userAuthService;
//	}

	@Value("${casrealm.cas.prefix}")
	public void setCasServerUrlPrefix(String casServerUrlPrefix){
		super.setCasServerUrlPrefix(casServerUrlPrefix);
	}
	@Value("${server.host}${cas.shirocas.path}")
	public void setCasService(String casService){
		super.setCasService(casService);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo authInfo = super.doGetAuthenticationInfo(token);

		PrincipalCollection coll = authInfo.getPrincipals();
		List list = coll.asList();
		String loginName=(String)list.get(0);
		
		User user = userService.findUserByLoginName(loginName);
//		UserWrapper user = userService.findUserByLoginName(loginName);
//		user.setId(coll.);
//		return new SimpleAuthenticationInfo(user, user.getPassword(),
//				ByteSource.Util.bytes(user.getSalt()), getName());
//		authInfo
	
		return new SimpleAuthenticationInfo(user, 	authInfo.getCredentials(),getName());
	}
//	private User buildUser(PrincipalCollection principals){
//		List list = principals.asList();
//		String loginName=(String)list.get(0);
////		UserDto test = userService.findUserByLoginName(loginName);
//		Map<String,String> params = (Map<String,String>)list.get(1);
//		
//		User user = new User();
//		user.setLoginName(loginName);
//		user.setId(Long.parseLong(params.get("id")));
//		user.setIsAdmin("1".equals(params.get("isAdmin")));
//		user.setName(params.get("name"));
//		return user;
//	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/**
		 * 1.获取用户数据
		 */
		User user = (User) principals.getPrimaryPrincipal();
		user.setLastLoginedTime(new Date());
		/**
		 * 2.更新用户登录时间
		 */
//		userService.updatePersonalInfo(user);
		/**
		 * 3.获取用户角色
		 */
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userAuthService.getRoleStringsByUserId(user.getId()));
		/**
		 * 4.获取用户权限
		 */
		info.setStringPermissions(userAuthService.getResourceStringsByUserId(user.getId()));
		/**
		 * 5.获取数据权限
		 */
		Session session = SessionContextUtils.getSession();
//		Map<String, List<UserDataPermViewDto>> userResDataPermDtoMap;
//		if (!session.getAttributeKeys().contains(DATA_PERM)) {
//			userResDataPermDtoMap = userAuthService.getUserResDataPermDtoMap(user.getId(),null);
//			session.setAttribute(DATA_PERM, userResDataPermDtoMap);
//		}else{
//			userResDataPermDtoMap = (Map<String, List<UserDataPermViewDto>>) session.getAttribute(DATA_PERM);
//			if (userResDataPermDtoMap==null||userResDataPermDtoMap.isEmpty()) {
//				userResDataPermDtoMap = userAuthService.getUserResDataPermDtoMap(user.getId(),null);
//				session.setAttribute(DATA_PERM, userResDataPermDtoMap);
//			}
//		}
		return info;
	}
	

	@Override
	protected boolean[] isPermitted(List<Permission> permissions,
			AuthorizationInfo info) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user.getIsAdmin() != null && user.getIsAdmin().booleanValue()) {
			boolean[] perms = new boolean[permissions.size()];
			for (int i = 0; i < permissions.size(); i++) {
				perms[i] = true;
			}
			return perms;
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals,
			Permission permission) {
// 		User user = (User) SecurityUtils.getSubject().getPrincipal();
//		if (user.getIsAdmin() != null && user.getIsAdmin().booleanValue()) {
//			return true;
//		}else{
//			AuthorizationInfo info = getAuthorizationInfo(principals);
//			Collection<String> permList = info.getStringPermissions();
//			if(permList.contains(permission.toString())){
//				return true;
//			}else{
//				return false;
//			}
//		}
		return super.isPermitted(principals, permission);
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		
//		return super.isPermitted(principals, permission);
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user.getIsAdmin() != null && user.getIsAdmin().booleanValue()) {
			return true;
		}else{
			AuthorizationInfo info = getAuthorizationInfo(principals);
			Collection<String> permList = info.getStringPermissions();
			if(permList.contains(permission)){
				System.out.println("拥有权限："+permission);
				return true;
			}else{
				System.err.println("未拥有权限："+permission);
				return false;
			}
		}
	}


}
