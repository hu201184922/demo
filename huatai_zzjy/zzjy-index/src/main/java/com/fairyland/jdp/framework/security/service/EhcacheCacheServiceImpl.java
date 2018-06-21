package com.fairyland.jdp.framework.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

@Service
public class EhcacheCacheServiceImpl implements CacheService{
	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;
	private static final String AccessTokenCache = "AccessTokenCache";
	@Override
	public boolean set(String key, Object value) {
		Cache cache = getCache();
		cache.put(key, value);
		return true;
	}

	@Override
	public Object get(String key) {
		Object obj = getCache().get(key);
		if(obj!=null){
			SimpleValueWrapper wrapper = (SimpleValueWrapper)obj;
			return wrapper.get();
		}
		return null;
	}

	@Override
	public Object delete(String key) {
		Cache cache = getCache();
		Object obj = cache.get(key);
		cache.evict(key);
		return obj;
	}

	private Cache getCache(){
		return ehCacheCacheManager.getCache(AccessTokenCache);
	}

	@Override
	public boolean containKey(String key) {
		Object obj = get(key);
		return obj!=null;
	}
}
