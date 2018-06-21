package com.fairyland.jdp.framework.tree.service;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;

public interface TreeFactory<T extends TreeNodeEntity<T>>  extends LazyTreeFactory<T>,DiligentTreeFactory<T>{

	public List<Map<String, Object>> getTree(Class<T> clazz,Long parentId,Map<String,Object> searchParams) throws Exception;

	public List<Map<String, Object>> getTree(Class<T> clazz,Map<String,Object> searchParams) throws Exception;
}
