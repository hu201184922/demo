/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-1 下午01:02:13 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-1 下午01:02:13       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.springboot.cas.FairylandCasSubjectFactory;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-1 下午01:02:13 
 * @version V1.0.0 
 */
@Component
public class FairylandWebSecurityManager extends DefaultWebSecurityManager{
	@Autowired
	public void setRememberMeManager(RememberMeManager rememberMeManager) {
        super.setRememberMeManager(rememberMeManager);
    }
	
//	@Resource(name="shiroJpaRealm")
////	@Resource(name="casRealm")
//	public void setRealm(Realm realm) {
//        super.setRealm(realm);
//    }
	@Resource(name="shiroJpaRealm")
	private Realm shiroJpaRealm;
	@Resource(name="ssoTokenRealm")
	private Realm ssoTokenRealm;
	
	@PostConstruct
	void innitMethod(){
		List<Realm> realms = new ArrayList<Realm>();
		realms.add(shiroJpaRealm);
		realms.add(ssoTokenRealm);
		super.setRealms(realms);
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
