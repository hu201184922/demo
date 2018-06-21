/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.realm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.common.util.XmlUtil;
import com.fairyland.jdp.framework.security.authc.checker.AccountExpiredChecker;
import com.fairyland.jdp.framework.security.authc.checker.AuthenticationTokenChecker;
import com.fairyland.jdp.framework.security.authc.checker.ConcurrentAccessChecker;
import com.fairyland.jdp.framework.security.authc.checker.DisabledAccountChecker;
import com.fairyland.jdp.framework.security.authc.checker.ExcessiveAttemptsChecker;
import com.fairyland.jdp.framework.security.authc.checker.ExpiredCredentialsChecker;
import com.fairyland.jdp.framework.security.authc.checker.FirstLoginChangePswChecker;
import com.fairyland.jdp.framework.security.authc.checker.LockedAccountChecker;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.HashService;
import com.fairyland.jdp.framework.security.service.UserAuthService;
import com.fairyland.jdp.framework.security.service.UserService;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午2:48:43
 * @version V1.0
 */
@Component("shiroJpaRealm")
public class DefaultAuthorizingRealm extends AbstractAuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	@Autowired
	protected UserService userService;
	@Autowired
	protected UserAuthService userAuthService;
	@Autowired
	protected HashService hashService;
	
	@Autowired
	private AccountExpiredChecker accountExpiredChecker;
	@Autowired
	private ConcurrentAccessChecker concurrentAccessChecker;
	@Autowired
	private DisabledAccountChecker disabledAccountChecker;
	@Autowired
	private ExcessiveAttemptsChecker excessiveAttemptsChecker;
	@Autowired
	private ExpiredCredentialsChecker expiredCredentialsChecker;
	@Autowired
	private FirstLoginChangePswChecker firstLoginChangePswChecker;
	@Autowired
	private LockedAccountChecker lockedAccountChecker;
	
	@Resource(name="hashCredentialsMatcher")
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }
	@PostConstruct
	void innitMethod(){
		 List<AuthenticationTokenChecker> preAuthenticationChecks = new ArrayList<AuthenticationTokenChecker>();
	        preAuthenticationChecks.add(accountExpiredChecker);
	        preAuthenticationChecks.add(concurrentAccessChecker);
	        preAuthenticationChecks.add(disabledAccountChecker);
	        preAuthenticationChecks.add(excessiveAttemptsChecker);
	        preAuthenticationChecks.add(expiredCredentialsChecker);
	        preAuthenticationChecks.add(firstLoginChangePswChecker);
	        preAuthenticationChecks.add(lockedAccountChecker);
	        super.setPreAuthenticationChecks(preAuthenticationChecks);
	        
//	        List<AuthenticationTokenChecker> postAuthenticationChecks = new ArrayList<AuthenticationTokenChecker>();
//	        super.setPostAuthenticationChecks(postAuthenticationChecks);
	}
	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 令牌基于用户名和密码的令牌
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 令牌中可以取出用户名密码
		String inputUserName = token.getUsername();
		String userName = inputUserName.replaceAll(" ", "");
		logger.debug("userName{" + userName + "}");
		token.setUsername(userName);
		User user = userService.findUserByLoginName(token.getUsername());
		if(!(user.getIsAdmin()!=null&&user.getIsAdmin())){
			Set<String>set=userAuthService
					.getResourceStringsByUserId(user.getId());
			if (set.size()==0) {
				throw new AccountException("未授权用户");
			}
		}

		return new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), getName());
	}


	/**
	 * 回调函数，提取当事人的角色和权限 principals 当事人
	 */
	@Override
	protected AuthorizationInfo doAuthorize(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		// User user = userService.findUserByLoginName(authzUser.getUsername());
		user.setLastLoginedTime(new Date());
		user.setLoginNum(0L);
		user.setErrorTryTime(null);
		userService.updateUser(user);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String>	roleSet = userAuthService.getRoleStringsByUserId(user.getId());
		info.setRoles(roleSet);
		Set<String>	resourceSet = userAuthService
					.getResourceStringsByUserId(user.getId());
		info.setStringPermissions(resourceSet);
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
	public void setHashService(HashService hashService) {
		this.hashService = hashService;
	}

}
