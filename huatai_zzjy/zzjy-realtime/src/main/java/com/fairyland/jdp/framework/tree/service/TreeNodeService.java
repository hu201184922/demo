package com.fairyland.jdp.framework.tree.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;

public interface TreeNodeService<T extends TreeNodeEntity<T>> {
	
	List<T> findByParentId(Class<T> clazz,Long parentId);
	
	public List<T> findByParentId(Class<T> clazz,Long parentId,Map<String, Object> searchParams) ;
	
}
