package com.fairyland.jdp.framework.tree.controller;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.core.web.Servlets;
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.tree.service.TreeFactory;

public abstract class TreeController<T extends TreeNodeEntity<T>> {

	private Class<T> entityClass;

	public TreeController() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Autowired
	protected @Qualifier(value="zTreeFactory")TreeFactory<T> treeFactory;
	
	@RequestMapping(value = "/get-tree-nodes/{id}", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getTreeNodes(
			@PathVariable("id") Long parentId) throws Exception {
		return treeFactory.getTreeNodes(entityClass,parentId);
	}

	@RequestMapping(value = "/get-tree/{id}", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getTree(@PathVariable("id") Long parentId)
			throws Exception {
		return treeFactory.getTree(entityClass,parentId);
	}

	@RequestMapping(value = "/get-tree", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getTree() throws Exception {
		return treeFactory.getTree(entityClass);
	}

	@RequestMapping(value = "/get-tree/condition", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getTreeByConditions(HttpServletRequest  request) throws Exception {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		return treeFactory.getTree(entityClass,searchParams);
	}
}
