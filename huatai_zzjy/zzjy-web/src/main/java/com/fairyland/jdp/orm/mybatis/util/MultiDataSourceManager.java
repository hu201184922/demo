package com.fairyland.jdp.orm.mybatis.util;

import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.common.Stack;

public class MultiDataSourceManager {

    private static ThreadLocal<MultiDataSourceManager> threadLocal = new ThreadLocal<MultiDataSourceManager>();

    private Stack<DataSource> stack = new Stack<DataSource>();

    public static MultiDataSourceManager getManager() {
	MultiDataSourceManager localMessage = threadLocal.get();
	if (ObjectUtil.isNull(localMessage)) {
	    threadLocal.set(new MultiDataSourceManager());
	}
	return threadLocal.get();
    }

    public void push(DataSource dataSource) {
	this.stack.push(dataSource);
    }

    public DataSource peek() {
	return stack.peek();
    }

    public DataSource pop() {
	return stack.pop();
    }

}
