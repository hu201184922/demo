package com.fairyland.jdp.framework.menu.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.menu.domain.MenuItem;
import com.fairyland.jdp.framework.menu.view.MenuModel;

public interface MenuItemService {

	void createMenuItem(MenuItem menuItem);

	MenuItem readMenuItem(Long menuItemId);

	void updateMenuItem(MenuItem menuItem);

	void deleteMenuItem(Long menuItemId);

	void deleteMenuItem(MenuItem menuItem);
	
	List<MenuItem> getMenuItemsByParentId(Long parentId);
	
	MenuItem getNullId();
	
	MenuModel getMenuModel(Long rootId);
	
	/**
	 * 获得所有的菜单 
	 * @return
	 */
	List<MenuItem> findAllMenuItem();
	
	Page<MenuItem> getAllMenu(Map<String, Object> searchParams,
			int pageNumber, int pagesize, String sortType) ;
}
