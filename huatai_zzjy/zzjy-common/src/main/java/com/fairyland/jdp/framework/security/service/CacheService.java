package com.fairyland.jdp.framework.security.service;

public interface CacheService {
	public boolean set(String key,Object value);
	public Object get(String key);
	public Object delete(String key);
	public boolean containKey(String key);
}
