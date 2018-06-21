package com.fairyland.jdp.framework.tree.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.util.BeanUtils;

@Component(value="zTreeFactory")
public class ZTreeFactory<T extends TreeNodeEntity<T>> implements TreeFactory<T> {

	@Resource
	private TreeNodeService<T> treeService;
	
	@Override
	public List<Map<String, Object>> getTree(Class<T> clazz,Long parentId,Map<String,Object> searchParams) throws Exception{
		List<T> items = treeService.findByParentId(clazz,parentId,searchParams);

		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();

		for (TreeNodeEntity<T> item : items) {
			Map<String, Object> treeNode = getTreeNodeMap(item);
			treeNode.put("open", true);
			
			if(item.hasChild()){
				List<Map<String, Object>> children = getTree(clazz,item.getId(),searchParams);
				treeNode.put("children", children);
			}else{
				treeNode.put("children", Collections.EMPTY_LIST);
			}
			
			// treeNode.put("isParent", item.hasChild());
			treeNodes.add(treeNode);

		}
		return treeNodes;
	}
	
	@Override
	public List<Map<String, Object>> getTree(Class<T> clazz,Long parentId) throws Exception {
		List<T> items = treeService.findByParentId(clazz,parentId);

		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();

		for (TreeNodeEntity<T> item : items) {
			Map<String, Object> treeNode = getTreeNodeMap(item);
			treeNode.put("open", true);
			
			if(item.hasChild()){
				List<Map<String, Object>> children = getTree(clazz,item.getId());
				treeNode.put("children", children);
			}else{
				treeNode.put("children", Collections.EMPTY_LIST);
			}
			
			// treeNode.put("isParent", item.hasChild());
			treeNodes.add(treeNode);

		}
		return treeNodes;
	}
	
	private Map<String, Object> getTreeNodeMap(TreeNodeEntity<T> item) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return BeanUtils.describeExcludeAnd(item);
	}

	@Override
	public List<Map<String, Object>> getTree(Class<T> clazz) throws Exception {
		return getTree(clazz,TreeNodeEntity.ROOT_PARENT_ID);
	}
	
	@Override
	public List<Map<String, Object>> getTree(Class<T> clazz,Map<String,Object> searchParams) throws Exception {
		return getTree(clazz,TreeNodeEntity.ROOT_PARENT_ID,searchParams);
	}
	
	@Override
	public List<Map<String, Object>> getTreeNodes(Class<T> clazz,Long parentId)
			throws Exception {
		List<T> items = treeService.findByParentId(clazz,parentId);

		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();

		for (TreeNodeEntity<T> item : items) {
			Map<String, Object> treeNode = getTreeNodeMap(item);// FIXME
																	// 效率问题
			treeNode.put("children", null);
			treeNode.put("isParent", item.hasChild());

			treeNodes.add(treeNode);
		}

		return treeNodes;
	}

}
