package com.fairyland.jdp.framework.onlineuser.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.onlineuser.view.OnlineUserModel;
import com.fairyland.jdp.framework.security.domain.User;

@Service
public class OnlineUserServiceImpl implements OnlineUserService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private SessionDAO sessionDao;

	@Override
	public List<OnlineUserModel> getOnlineUsers() {
		Collection<Session> sessions = sessionDao.getActiveSessions();

		List<OnlineUserModel> onlineUserModels = new ArrayList<OnlineUserModel>();
		for (Session session : sessions) {

			for (Object key : session.getAttributeKeys()) {
				log.debug(key + "=" + session.getAttribute(key));
			}

			SimplePrincipalCollection principals = (SimplePrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (principals != null) {
				User user = (User) principals.getPrimaryPrincipal();

				OnlineUserModel onlineUserModel = new OnlineUserModel(session);
				onlineUserModel.setLoginName(user.getAccount());
				onlineUserModel.setName(user.getUserName());
				onlineUserModel.setOrg(null);// FIXME
				onlineUserModels.add(onlineUserModel);
			}
		}
		return onlineUserModels;
	}

}
