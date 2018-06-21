/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.authc.checker;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.domain.User;

/**
 * @Description: 校验帐号是否被禁用
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年8月31日 下午4:31:30
 * @version V1.0
 */
@Component
public class DisabledAccountChecker implements AuthenticationTokenChecker {

	@Override
	public void check(AuthenticationToken authcToken,
			AuthenticationInfo authcInfo) throws AuthenticationException {
		User user = authcInfo.getPrincipals().oneByType(User.class);
		if (BooleanUtils.isFalse(user.getEnabled())) {
			throw new DisabledAccountException("登陆不成功");
		}
	}
}
