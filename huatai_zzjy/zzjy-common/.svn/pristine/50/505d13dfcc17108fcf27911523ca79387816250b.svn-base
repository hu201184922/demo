package com.fairyland.jdp.framework.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import com.fairyland.jdp.framework.security.domain.AuthInfo;

//@Service
public class EhcacheTokenServiceImpl implements TokenService{
	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;
	private static final String AccessTokenCache = "AccessTokenCache";
	@Override
	public boolean setAuthInfo(String key, AuthInfo value) {
		Cache cache = getCache();
		cache.put(key, value);
		return true;
	}

	@Override
	public AuthInfo getAuthInfo(String key) {
		Object obj = getCache().get(key);
		if(obj!=null){
			SimpleValueWrapper wrapper = (SimpleValueWrapper)obj;
			return (AuthInfo)wrapper.get();
		}
		return null;
	}

	@Override
	public boolean deleteAuthInfo(String key) {
		Cache cache = getCache();
		Object obj = cache.get(key);
		cache.evict(key);
		return true;
	}

	private Cache getCache(){
		return ehCacheCacheManager.getCache(AccessTokenCache);
	}

	@Override
	public boolean containKey(String key) {
		AuthInfo obj = getAuthInfo(key);
		return obj!=null;
	}

}
