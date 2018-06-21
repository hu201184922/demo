package com.fairyland.jdp.framework.cachemgmt.service;

import java.util.List;

import com.fairyland.jdp.framework.cachemgmt.view.CacheModel;

public interface CacheMgmtService {

	public List  <CacheModel> getCaches(String cacheName,String itemKey,String searchKeyWord);
	
	public void clearCache(String cacheName);
	
	public void clearCache(String cacheName,String key);
	
	public void clearAll();
	
	public void clearShiroAll();
	
	void addMobileCache(String key,Object obj);
	
	Object getMobileCache(String key);
}
