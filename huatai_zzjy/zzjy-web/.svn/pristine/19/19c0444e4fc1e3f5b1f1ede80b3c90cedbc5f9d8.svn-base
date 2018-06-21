package com.fairyland.jdp.springboot.config.shiro.cas.manager;

import javax.annotation.Resource;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairyland.jdp.springboot.cas.FairylandCasSubjectFactory;

//@Component
public class FairylandCasSecurityManager extends DefaultWebSecurityManager{
	@Autowired
	public void setRememberMeManager(RememberMeManager rememberMeManager) {
        super.setRememberMeManager(rememberMeManager);
    }
	
	@Resource(name="casRealm")
	public void setRealm(Realm realm) {
        super.setRealm(realm);
    }
	
	@Autowired
	public void setCacheManager(CacheManager cacheManager) {
        super.setCacheManager(cacheManager);
    }
	
	@Autowired
	public void setSessionManager(SessionManager sessionManager){
		super.setSessionManager(sessionManager);
	}
	
	@Autowired
	public void setSubjectFactory(FairylandCasSubjectFactory fairylandCasSubjectFactory){
		super.setSubjectFactory(fairylandCasSubjectFactory);
	}
}
