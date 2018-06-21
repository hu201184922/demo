package com.fairyland.jdp.common.persistent.domain;

import java.util.Set;

public interface SelfAssociation<T> {

	T getChildAt(int childIndex);

	Integer getChildCount();

	T getParent();

	Boolean getAllowsChildren();

	boolean hasChild();

	Set<T> getChildren();

	Integer getSortIndex();
}
