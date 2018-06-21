package com.fairyland.jdp.framework.security.authc.util;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserAuthService;
import com.fairyland.jdp.framework.security.service.UserService;

@Component("ssoTokenRealm")
public class SSOTokenRealm  extends  AuthorizingRealm {

	private static final Logger log = LoggerFactory.getLogger(SSOTokenRealm.class);
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected UserAuthService userAuthService; 
	
	public boolean supports(AuthenticationToken token) {  
        //仅支持StatelessToken类型的Token  
        return token instanceof StatelessToken;  
    } 
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		if (log.isDebugEnabled()) {
            log.debug("-------SSOTokenRealm----doGetAuthenticationInfo------");
        } 
 
		if(authcToken!=null){ 
			StatelessToken statelessToken = (StatelessToken) authcToken;  
	        String username = statelessToken.getUsername();  
	        String ticket =statelessToken.getTicket();
			  if(null!=ticket && username!=null ){ 
				  try{
					  User user = userService.findUserByLoginName(username); 
					 	if (user != null) {
					 		//byte[] salt = Encodes.decodeHex(user.getSalt());  
					           return new SimpleAuthenticationInfo(  
					        		user,  
					                ticket, // ByteSource.Util.bytes(user.getSalt()),
					                getName());  
					 		 } 
				  }catch(Exception e){
					  throw new UnknownAccountException();
				  } 
			   } 
			  throw new UnknownAccountException();
		}
		 throw new UnknownAccountException();
	}

	/**
	 * 回调函数，提取当事人的角色和权限 principals 当事人
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
        User user = (User) principals.getPrimaryPrincipal(); 
		user.setLastLoginedTime(new Date());
		userService.updateUser(user);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(userAuthService.getRoleStringsByUserId(user.getId()));
		info.setStringPermissions(userAuthService.getResourceStringsByUserId(user.getId()));  
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
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user.getIsAdmin() != null && user.getIsAdmin().booleanValue()) {
			return true;
		}
		return super.isPermitted(principals, permission);
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if(".index".equals(permission)||"index".equals(permission)){
			return super.isPermitted(principals,".index")||super.isPermitted(principals,"index");
		}
		return super.isPermitted(principals, permission);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserAuthService(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	} 
}
