/** 
* CopyRright © 2013 上海复深蓝信息技术有限公司
* Homepage：http://www.fulan.com.cn/                         
* Project:Fairyland-JDP                                      
* Module ID: framework   
* Comments:                                         
* JDK version used: JDK1.6                            
 */ 
package com.fairyland.jdp.framework.security.authc.checker;

import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.UserService;

/**   
 * @Description: TODO
 * @author codyzeng 
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 上午10:42:49
 * @version V1.0   
 */
@Component
public class ExpiredCredentialsChecker  implements AuthenticationTokenChecker{
	@Autowired
	private UserService userService;
    @Override
    public void check(AuthenticationToken authcToken,
	    AuthenticationInfo authcInfo) throws AuthenticationException {
    	User user=(User) authcInfo.getPrincipals().getPrimaryPrincipal();
    	if(BooleanUtils.isTrue(user.getCredentialsExpired())){
    		throw new ExpiredCredentialsException("账号密码过期");
    	}else if(BooleanUtils.isFalse(user.getCredentialsExpired())&&(user.getCredentialsExpiredTime()!=null)&&(new Date()).compareTo(user.getCredentialsExpiredTime())>0){
    		user.setCredentialsExpired(true);
    		userService.updateUser(user);
    		throw new ExpiredCredentialsException("账号密码过期");
    	}
    }

}
