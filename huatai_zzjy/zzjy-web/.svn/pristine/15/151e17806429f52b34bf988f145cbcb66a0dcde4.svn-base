package com.fairyland.jdp.framework.tree.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.core.persistence.DynamicSpecifications;
import com.fairyland.jdp.core.persistence.SearchFilter;
import com.fairyland.jdp.core.persistence.SearchFilter.Operator;
import com.fairyland.jdp.framework.domain.TreeNodeEntity;
import com.fairyland.jdp.framework.service.ICommonDao;

@Service(value = "treeService")
public class TreeNodeServiceImpl<T extends TreeNodeEntity<T>> implements TreeNodeService<T> {

	@Resource
	private ICommonDao<T> dao;

	@Override
	public List<T> findByParentId(Class<T> clazz,Long parentId) {
		List<T> treeNodes;
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		if (TreeNodeEntity.ROOT_PARENT_ID.equals(parentId)) {
			filters.put("parent.id", new SearchFilter("parent.id",
					Operator.ISNULL));
		} else {
			filters.put("parent.id", new SearchFilter("parent.id", Operator.EQ,
					parentId));
		}
		Specification<T> spec = DynamicSpecifications
				.bySearchFilter(filters.values());
		treeNodes = dao.findAll(clazz,spec, new Sort(Direction.ASC, "sortIndex"));
		
		return treeNodes;

	}

	@Override
	public List<T> findByParentId(Class<T> clazz,Long parentId,Map<String, Object> searchParams) {
		List<T> treeNodes;
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		if (TreeNodeEntity.ROOT_PARENT_ID.equals(parentId)) {
			filters.put("parent.id", new SearchFilter("parent.id",
					Operator.ISNULL));
		} else {
			filters.put("parent.id", new SearchFilter("parent.id", Operator.EQ,
					parentId));
		}
		
		Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values());
		treeNodes = dao.findAll(clazz,spec, new Sort(Direction.ASC, "sortIndex"));
		
		return treeNodes;
	}
	// @Resource
	// private TreeNodeDao<T, Long> treeNodeDao;
	//
	// @Override
	// public List<T> findByParentId(Long parentId) {
	// List<T> treeNodes;
	// if (TreeNodeEntity.ROOT_PARENT_ID.equals(parentId)) {
	// treeNodes = treeNodeDao.findByParentIdIsNullOrderBySortIndexAsc();
	// } else {
	// treeNodes = treeNodeDao.findByParentIdOrderBySortIndexAsc(parentId);
	// }
	// return treeNodes;
	// }
}
