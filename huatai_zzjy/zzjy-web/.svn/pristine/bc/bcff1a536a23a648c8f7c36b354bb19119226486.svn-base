package com.fairyland.jdp.framework.security.authc.checker;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.security.Constants;
import com.fairyland.jdp.framework.security.authc.filter.CaptchaAuthenticationFilter;
import com.google.common.collect.Sets;

/**
 * 
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年8月31日 下午4:14:33
 * @version V1.0
 */
@Component
public class ConcurrentAccessChecker implements AuthenticationTokenChecker {

	static public final String SESSION_LIMIT_POLICY_KICK = "KICK";// 踢出前面的Session
	static public final String SESSION_LIMIT_POLICY_DENIED = "DENIED";// 拒绝登录

	@Autowired
	private SessionDAO sessionDAO;

	// 一个用户可以登录的最大会话数
	private Integer maxSession = 1;
	// 会话限制策略：踢出前一个用户或不允许超过限制数的用户登录
	private String sessionLimitPolicy =  SESSION_LIMIT_POLICY_DENIED;
	@Override
	public void check(AuthenticationToken authcToken,
			AuthenticationInfo authcInfo) throws AuthenticationException {
		String curLoginID = (String) authcToken.getPrincipal();

		Collection<Session> sessions = sessionDAO.getActiveSessions();

		Set<Session> curUserSessions = Sets.newHashSet();
		int sessionCount = 0;
		for (Session activeSession : sessions) {
			String loginID = (String) activeSession
					.getAttribute(CaptchaAuthenticationFilter.USER_LOGIN_ID_KEY);
			if (curLoginID.equals(loginID)) {
				sessionCount++;
				curUserSessions.add(activeSession);
			}
		}

//		if (sessionCount >= getMaxSession()){
//			if (SESSION_LIMIT_POLICY_DENIED.equals(getSessionLimitPolicy())) {
//				throw new ConcurrentAccessException("帐号【" + curLoginID
//						+ "】登录会话数已经达到上限，不允许再登录，请联系管理员");
//			} else {
//				if (!curUserSessions.isEmpty()) {
//					Session session = curUserSessions.iterator().next();
//					session.setAttribute(Constants.FORCE_LOGOUT_KEY, true);
//				}
//			}
//		}
	}

	public Integer getMaxSession() {
//		return PreferenceUtils.getInteger(PreferenceCode.CODE_MAX_SESSIONS,maxSession);
		 return maxSession;
	}

	public void setMaxSession(Integer maxSession) {
		this.maxSession = maxSession;
	}

	public String getSessionLimitPolicy() {
//		return PreferenceUtils.getString(PreferenceCode.CODE_SESSION_lIT_POLICY, sessionLimitPolicy);
		return sessionLimitPolicy;
	}

	public void setSessionLimitPolicy(String sessionLimitPolicy) {
		this.sessionLimitPolicy = sessionLimitPolicy;
	}
}
