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
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.domain.User;

/**   
 * @Description: TODO
 * @author codyzeng 
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 上午10:41:26
 * @version V1.0   
 */
@Component
public class LockedAccountChecker  implements AuthenticationTokenChecker{

    @Override
    public void check(AuthenticationToken authcToken,
	    AuthenticationInfo authcInfo) throws AuthenticationException {
    	User user=(User) authcInfo.getPrincipals().getPrimaryPrincipal();
    	if(BooleanUtils.isTrue(user.getLocked())){
    		throw new LockedAccountException("账号已经被锁定");
    	}
    }

}
