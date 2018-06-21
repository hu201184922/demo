package com.fairyland.jdp.framework.menu.service;

import java.util.List;
import java.util.Map;

public interface FavoriteMenuFactory {

	List<Map<String, Object>> getFavorities(Long userId) throws Exception;
	
}
