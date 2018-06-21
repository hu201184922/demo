/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;

import com.fairyland.jdp.framework.security.authc.checker.AuthenticationTokenChecker;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午2:47:01
 * @version V1.0
 */
public abstract class AbstractAuthorizingRealm extends AuthorizingRealm {

	private List<AuthenticationTokenChecker> preAuthenticationChecks;

	private List<AuthenticationTokenChecker> postAuthenticationChecks;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO
		AuthorizationInfo authzInfo = doAuthorize(principals);

		return authzInfo;
	}

	abstract protected AuthorizationInfo doAuthorize(
			PrincipalCollection principals);

	/**
	 * 身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		AuthenticationInfo authcInfo = doAuthenticate(authcToken);

		if (!CollectionUtils.isEmpty(preAuthenticationChecks)) {
			for (AuthenticationTokenChecker checker : preAuthenticationChecks) {
				checker.check(authcToken, authcInfo);
			}
		}
		return authcInfo;
	}

	abstract protected AuthenticationInfo doAuthenticate(
			AuthenticationToken token) throws AuthenticationException;

	/**
	 * 重新父类方法，添加密码验证后的后续验证机制
	 */
	@Override
	protected void assertCredentialsMatch(AuthenticationToken authcToken,
			AuthenticationInfo authcInfo) throws AuthenticationException {
		super.assertCredentialsMatch(authcToken, authcInfo);
		if (!CollectionUtils.isEmpty(postAuthenticationChecks)) {
			for (AuthenticationTokenChecker checker : postAuthenticationChecks) {
				checker.check(authcToken, authcInfo);
			}
		}
	}

	public void setPreAuthenticationChecks(
			List<AuthenticationTokenChecker> preAuthenticationChecks) {
		this.preAuthenticationChecks = preAuthenticationChecks;
	}

	public void setPostAuthenticationChecks(
			List<AuthenticationTokenChecker> postAuthenticationChecks) {
		this.postAuthenticationChecks = postAuthenticationChecks;
	}

}
