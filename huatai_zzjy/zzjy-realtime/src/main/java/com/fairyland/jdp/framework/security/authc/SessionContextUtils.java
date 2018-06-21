/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.authc;

import com.fairyland.jdp.framework.security.domain.AuthInfo;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.framework.security.service.TokenService;
import com.fairyland.jdp.framework.security.service.UserService;
import com.fairyland.jdp.framework.util.SpringUtil;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 上午9:29:19
 * @version V1.0
 */
public abstract class SessionContextUtils {
	//当前线程的AccessToken，由拦截器插入
	private static ThreadLocal<String> AccessTokenLocal = new ThreadLocal<String>();

    static public Long getLoginUserId() {
		User user = getCurrentUser();
		return user!=null? user.getId():null;
    }
	
	static public Long getCurrentUserId() {
		User user = getCurrentUser();
		return user != null ? user.getId() : null;
	}
	
	static public String getLoginName() {
		User user = getCurrentUser();
		return user != null ? user.getAccount() : null;
	}

	static public User getCurrentUser() {
//		User user = (User) SecurityUtils.getSubject().getPrincipal();
//		if(user==null){
//			user = getWechatUser();
//		}
		TokenService tokenService = SpringUtil.getBean(TokenService.class);
		AuthInfo authInfo = (AuthInfo)tokenService.getAuthInfo(getAccessToken());
		if(authInfo!=null){
			UserService userService = SpringUtil.getBean(UserService.class);
			return userService.getUser(authInfo.getUserId());
		}
		return null;
	}
//	static public void updateCurrentUser(User user){
//		CacheService cacheService = SpringUtil.getBean(CacheService.class);
//		AuthInfo authInfo = (AuthInfo)cacheService.get(getAccessToken());
//		authInfo.setUser(user);
//		cacheService.set(getAccessToken(), authInfo);
//	}
	public static String getAccessToken(){
		return AccessTokenLocal.get();
	}
	public static void setAccessToken(String accessToken){
		AccessTokenLocal.set(accessToken);
	}
//	static public User getWechatUser(){
//		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(WechatUtil.WECHAT_SESSION);
//		return user;
//	}
//	static public String getWechatAgentCode(){
//		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(WechatUtil.WECHAT_SESSION);
//		return user==null?null:user.getAccount();
//	}
//
//	static public String getString(String key) {
//		return (String) getAttribute(key);
//	}
//
//	static public Long getLong(String key) {
//		return (Long) getAttribute(key);
//	}
//
//	static public Integer getInteger(String key) {
//		return (Integer) getAttribute(key);
//	}
//
//	static public Boolean getBoolean(String key) {
//		return (Boolean) getAttribute(key);
//	}
//
//	static public Float getFloat(String key) {
//		return (Float) getAttribute(key);
//	}
//
//	static public Double getDouble(String key) {
//		return (Double) getAttribute(key);
//	}
//
//	static public Date Date(String key) {
//		return (Date) getAttribute(key);
//	}

//	static public Object getAttribute(String key) {
//		return getSession().getAttribute(key);
//	}
//
//	static public void setAttribute(String key, Object value) {
//		getSession().setAttribute(key, value);
//	}
}
