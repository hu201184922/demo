package com.fairyland.jdp.framework.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.core.persistence.DynamicSort;
import com.fairyland.jdp.core.persistence.DynamicSpecifications;
import com.fairyland.jdp.core.persistence.SearchFilter;
import com.fairyland.jdp.core.persistence.SearchFilter.Operator;
import com.fairyland.jdp.core.persistence.SortFilter;
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.menu.dao.MenuItemDao;
import com.fairyland.jdp.framework.menu.domain.MenuItem;
import com.fairyland.jdp.framework.menu.view.MenuModel;

@Service
@Transactional(readOnly = true)
public class MenuItemServiceImpl implements MenuItemService {
	
	@Autowired
	private MenuItemDao menuItemDao;

	@Override
	@CacheEvict(value="MenuCache", allEntries=true)
	@Transactional
	public void createMenuItem(MenuItem menuItem) {
		menuItem.setPermString(menuItem.getUrl().replaceAll("/", "."));
		menuItemDao.save(menuItem);
	}

	@Override
	public MenuItem readMenuItem(Long menuItemId) {
		return menuItemDao.findOne(menuItemId);
	}

	@Override
	@CacheEvict(value="MenuCache", allEntries=true)
	@Transactional
	public void updateMenuItem(MenuItem menuItem) {
		menuItemDao.save(menuItem);
	}

	@Override
	@CacheEvict(value="MenuCache", allEntries=true)
	@Transactional
	public void deleteMenuItem(Long menuItemId) {
		menuItemDao.delete(menuItemId);
	}

	@Override
	@CacheEvict(value="MenuCache", allEntries=true)
	@Transactional
	public void deleteMenuItem(MenuItem menuItem) {
		menuItemDao.delete(menuItem);
	}
	
	@Override
	public List<MenuItem>  getMenuItemsByParentId(Long parentId) {
		List <MenuItem>treeNodes;
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if (TreeNodeEntity.ROOT_PARENT_ID.equals(parentId)) {
			filters.put("parent.id", new SearchFilter("parent.id",
					Operator.ISNULL));
		} else {
			filters.put("parent.id", new SearchFilter("parent.id", Operator.EQ,
					parentId));
		}
		Specification spec = DynamicSpecifications
				.bySearchFilter(filters.values());
		treeNodes = menuItemDao.findAll(spec, new Sort(Direction.ASC, "sortIndex"));
		return treeNodes;

	}

	public void buildMenuModel(MenuModel model) {
		List <MenuItem>menuItems=getMenuItemsByParentId(model.getId());
		List<MenuModel> children=new ArrayList<MenuModel>();
		for(MenuItem item:menuItems){
			MenuModel child=new MenuModel();
			buildModel(child, item);
			children.add(child);
			if(item.hasChild()){
				//如果有子菜单，创建子菜单模型
				buildMenuModel(child);
			}
			
		}
		model.setChildren(children);
	}

	@Override
	@Cacheable(value="MenuCache", key="'RootMenuId:'+#rootId.toString()")
	public MenuModel getMenuModel(Long rootId) {
		MenuModel model=new MenuModel();
		model.setId(rootId);
		if (TreeNodeEntity.ROOT_PARENT_ID.equals(rootId)) {
			model.setName("root");
		}else{
			MenuItem rootItem=readMenuItem(rootId);
			buildModel(model, rootItem);
		}
		buildMenuModel(model);
		return model;
	}

	private void buildModel(MenuModel model, MenuItem rootItem) {
		model.setId(rootItem.getId());
		model.setName(rootItem.getName());
		model.setUrl(rootItem.getUrl());
		model.setMenuIcon(rootItem.getMenuIcon());
		model.setEnabled(rootItem.getEnabled());
		model.setVisible(rootItem.getVisible());
		model.setDisableIcon(rootItem.getDisableIcon());
		model.setPermString(rootItem.getPermString());
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Map<String, SortFilter> filters = SortFilter.parse(sortType);
		// 此处添加更多排序逻辑
		Sort sort = DynamicSort.bySortFilter(filters.values());
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	
	@Override
	public Page<MenuItem> getAllMenu(Map<String, Object> searchParams,
			int pageNumber, int pagesize, String sortType) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<MenuItem> spec = DynamicSpecifications
				.bySearchFilter(filters.values());
		PageRequest pageRequest = buildPageRequest(pageNumber, pagesize,
				sortType);
		return menuItemDao.findAll(spec, pageRequest);
	}
	@Override
	public List<MenuItem> findAllMenuItem() {
		return menuItemDao.findAll();
	}
	@Override
	public MenuItem getNullId(){
		return menuItemDao.findNullNode();
	}
}
