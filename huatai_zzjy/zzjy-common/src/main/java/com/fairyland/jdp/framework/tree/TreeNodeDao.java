package com.fairyland.jdp.framework.tree;

import java.util.List;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;

public interface TreeNodeDao<T extends TreeNodeEntity<T>> {

	List<T> findByParentIdOrderBySortIndexAsc(Long parentId);

	List<T> findByParentIdIsNullOrderBySortIndexAsc();
}
