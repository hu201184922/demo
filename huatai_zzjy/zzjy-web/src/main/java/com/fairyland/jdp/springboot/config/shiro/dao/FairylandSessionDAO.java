package com.fairyland.jdp.springboot.config.shiro.dao;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FairylandSessionDAO extends EnterpriseCacheSessionDAO{

	@Value("${spring.fairyland.shiro.activeSessionsCacheName}")
	public void setActiveSessionsCacheName(String activeSessionsCacheName) {
        super.setActiveSessionsCacheName(activeSessionsCacheName);
    }
	
	@Autowired
	public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        super.setSessionIdGenerator(sessionIdGenerator);
    }
	
}
