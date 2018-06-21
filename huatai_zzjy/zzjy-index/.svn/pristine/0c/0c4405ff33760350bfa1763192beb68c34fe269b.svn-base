package com.fairyland.jdp.common.persistent.dao;

import java.util.List;

import com.fairyland.jdp.common.persistent.domain.SelfAssociationEntity;

public interface TreeNodeDao<T extends SelfAssociationEntity<T>> {

	List<T> findByParentIdOrderBySortIndexAsc(Long parentId);

	List<T> findByParentIdIsNullOrderBySortIndexAsc();
}
