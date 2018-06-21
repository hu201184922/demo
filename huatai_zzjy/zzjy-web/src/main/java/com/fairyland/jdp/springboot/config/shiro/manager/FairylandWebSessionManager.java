/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-1 下午01:08:30 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-1 下午01:08:30       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.manager;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.onlineuser.listener.OnlineUserSessionListener;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-1 下午01:08:30 
 * @version V1.0.0 
 */
@Component
public class FairylandWebSessionManager extends DefaultWebSessionManager{
	@Autowired
    private OnlineUserSessionListener onlineUserSessionListener;
	
	@Value("${spring.fairyland.shiro.globalSessionTimeout}")
	public void setGlobalSessionTimeout(long globalSessionTimeout) {
        super.setGlobalSessionTimeout(globalSessionTimeout);
    }
	
	@Value("${spring.fairyland.shiro.deleteInvalidSessions}")
	public void setDeleteInvalidSessions(boolean deleteInvalidSessions) {
        super.setDeleteInvalidSessions(deleteInvalidSessions);
    }
	
	@Value("${spring.fairyland.shiro.sessionValidationSchedulerEnabled}")
	public void setSessionValidationSchedulerEnabled(boolean sessionValidationSchedulerEnabled) {
        super.setSessionValidationSchedulerEnabled(sessionValidationSchedulerEnabled);
    }
	
	@Value("${spring.fairyland.shiro.sessionIdCookieEnabled}")
	public void setSessionIdCookieEnabled(boolean sessionIdCookieEnabled) {
        super.setSessionIdCookieEnabled(sessionIdCookieEnabled);
    }
	
	
	
	@Autowired
	public void setSessionValidationScheduler(SessionValidationScheduler sessionValidationScheduler) {
        super.setSessionValidationScheduler(sessionValidationScheduler);
    }
	
	@Autowired
	public void setSessionDAO(SessionDAO sessionDAO) {
        super.setSessionDAO(sessionDAO);
    }
	
	@Resource(name="fairylandSimpleCookie")
	public void setSessionIdCookie(Cookie sessionIdCookie) {
        super.setSessionIdCookie(sessionIdCookie);
    }

	@PostConstruct
	void innitMethod(){
		Collection<SessionListener> listeners = new HashSet<SessionListener>();
		listeners.add(onlineUserSessionListener);
		super.setSessionListeners(listeners);
	}
}
