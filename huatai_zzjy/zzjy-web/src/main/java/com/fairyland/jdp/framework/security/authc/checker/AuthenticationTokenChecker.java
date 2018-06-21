package com.fairyland.jdp.framework.security.authc.checker;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

public interface AuthenticationTokenChecker {

	void check(AuthenticationToken authcToken,AuthenticationInfo authcInfo) throws AuthenticationException;
}
