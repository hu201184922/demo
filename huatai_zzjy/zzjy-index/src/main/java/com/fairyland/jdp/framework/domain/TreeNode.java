package com.fairyland.jdp.framework.domain;

import java.util.Set;

public interface TreeNode<T> extends LongId {

	T getChildAt(int childIndex);

	Integer getChildCount();

	T getParent();

	Boolean getAllowsChildren();

	boolean hasChild();

	Set<T> getChildren();

	Integer getSortIndex();
}
