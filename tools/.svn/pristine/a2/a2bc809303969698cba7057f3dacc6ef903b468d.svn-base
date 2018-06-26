package com.tools.spring.config;

public class MultiDataSourceManager {

    private static ThreadLocal<MultiDataSourceManager> threadLocal = new ThreadLocal<MultiDataSourceManager>();

    private java.util.Stack<DataSource> stack = new java.util.Stack<DataSource>();

    public static MultiDataSourceManager getManager() {
	MultiDataSourceManager localMessage = threadLocal.get();
	if (org.springframework.util.ObjectUtils.isEmpty(localMessage)) {
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
