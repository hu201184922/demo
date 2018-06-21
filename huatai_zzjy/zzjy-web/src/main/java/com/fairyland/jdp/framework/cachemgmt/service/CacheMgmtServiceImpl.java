package com.fairyland.jdp.framework.cachemgmt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.core.mapper.JsonMapper;
import com.fairyland.jdp.framework.cachemgmt.enums.CacheName;
import com.fairyland.jdp.framework.cachemgmt.view.CacheModel;
import com.google.common.collect.Maps;

@Service("cacheMgmtService")
public class CacheMgmtServiceImpl implements CacheMgmtService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private CacheManager cacheManager;
	
	@Resource
	private EhCacheManager ehCacheManager;

	private static Map<String, String> cacheTypes = Maps.newLinkedHashMap();
	static {
		cacheTypes.put(CacheName.Menu.value(), "菜单");
		cacheTypes.put(CacheName.Dict.value(), "字典");
		cacheTypes.put(CacheName.Preference.value(), "首选项");
		cacheTypes.put(CacheName.LogCfg.value(), "日志配置");
	}

	@Override
	public List<CacheModel> getCaches(String cacheName, String itemKey,
			String searchKeyWord) {
		List<CacheModel> cacheModels = new ArrayList<CacheModel>();
		if (StringUtils.isNotEmpty(cacheName)) {
			Cache cache = (Cache) cacheManager.getCache(cacheName);
			if(cache != null){
				addCacheModel(cacheName, itemKey, cacheModels, cache);
			}
		} else {
			Collection<String> cacheNames = cacheManager.getCacheNames();
			for (String cacheN : cacheNames) {
				Cache cache = (Cache) cacheManager.getCache(cacheN);
				if(cache!=null)
				addCacheModel(cacheN, itemKey, cacheModels, cache);
			}
		}
		return cacheModels;
	}

	private void addCacheModel(String cacheName, String itemKey,
			List<CacheModel> cacheModels, Cache cache) {
		net.sf.ehcache.Cache c = (net.sf.ehcache.Cache) cache.getNativeCache();

		if (StringUtils.isNotEmpty(itemKey)) {
			buildCacheModel(cacheName, itemKey, cacheModels, cache, itemKey);
		} else {
			List list = c.getKeys();
			for (Object key : list) {
				log.debug(key + "=============" + cache.get(key));
				buildCacheModel(cacheName, (String) key, cacheModels, cache,
						key);
				// List l = (List) value.get();
				// for (Object v : l) {
				// MenuItem m = (MenuItem) v;
				// log.debug(m.getId() + "============="
				// + m.getName());
				// }
			}
		}
	}

	private void buildCacheModel(String cacheName, String itemKey,
			List<CacheModel> cacheModels, Cache cache, Object key) {
		SimpleValueWrapper value = (SimpleValueWrapper) cache.get(key);

		if (value != null) {
			CacheModel cacheModel = new CacheModel();
			cacheModel.setCacheName(cacheName);
			cacheModel.setKey(itemKey);
			cacheModel
					.setValue(JsonMapper.nonEmptyMapper().toJson(value.get()));
			cacheModels.add(cacheModel);
		}
	}

	@Override
	public void clearCache(String cacheName) {
		Cache cache = (Cache) cacheManager.getCache(cacheName);

		if (cache != null) {
			cache.clear();
		}
	}

	@Override
	public void clearAll() {
		Collection<String> cacheNames = cacheManager.getCacheNames();

		for (String cacheName : cacheNames) {
			clearCache(cacheName);
		}
	}

	private void clearShiroCache(String cacheName){
		net.sf.ehcache.CacheManager ca= ehCacheManager.getCacheManager();
		net.sf.ehcache.Cache cache= ca.getCache(cacheName);
		if (cache != null) {
			cache.removeAll();
		}
	}
	
	@Override
	public void clearCache(String cacheName, String key) {
		Cache cache = (Cache) cacheManager.getCache(cacheName);

		if (StringUtils.isEmpty(key)) {
			cache.clear();
		} else {
			net.sf.ehcache.Cache c = (net.sf.ehcache.Cache) cache
					.getNativeCache();
			c.remove(key);
		}
	}

	@Override
	public void clearShiroAll() {
		net.sf.ehcache.CacheManager ca= ehCacheManager.getCacheManager();
		String[] strary= ca.getCacheNames();
		for (String str : strary) {
			clearShiroCache(str);
		}
	}
	
	@Override
	public void addMobileCache(String key,Object obj){
		net.sf.ehcache.CacheManager ca= ehCacheManager.getCacheManager();
		net.sf.ehcache.Cache cache = ca.getCache("AndroidCache");
		cache.put( new Element(key, obj));
	}
	
	public Object getMobileCache(String key){
		net.sf.ehcache.CacheManager ca= ehCacheManager.getCacheManager();
		net.sf.ehcache.Cache cache = ca.getCache("AndroidCache");
		return cache.get(key)==null?null:cache.get(key).getObjectValue();
	}
}
