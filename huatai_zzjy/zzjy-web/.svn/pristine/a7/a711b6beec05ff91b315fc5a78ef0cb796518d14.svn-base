/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.authc.checker;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午12:04:57
 * @version V1.0
 */
@Component
public class AccountExpiredChecker implements AuthenticationTokenChecker {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;

	@Override
	public void check(AuthenticationToken authcToken,
			AuthenticationInfo authcInfo) throws AuthenticationException {
		User user = (User) authcInfo.getPrincipals().getPrimaryPrincipal();
		log.debug(ClassUtil.getValue(authcToken, "sucessUrl").toString());
		if (user.getExpired() == true) {
			throw new AccountException("账号已经过期");
		} else if (user.getExpired() == false
				&& (user.getExpiredTime() != null)
				&& (new Date()).compareTo(user.getExpiredTime()) > 0) {
			user.setExpired(true);
			userService.updateUser(user);
			throw new AccountException("账号已经过期");
		}
	}

}
