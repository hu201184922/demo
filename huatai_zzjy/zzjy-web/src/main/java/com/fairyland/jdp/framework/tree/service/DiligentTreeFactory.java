package com.fairyland.jdp.framework.tree.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;

public interface DiligentTreeFactory<T extends TreeNodeEntity<T>>{

	List<Map<String,Object>> getTree(Class<T> clazz,Long parentId) throws Exception;
	
	List<Map<String,Object>> getTree(Class<T> clazz) throws Exception;

}
