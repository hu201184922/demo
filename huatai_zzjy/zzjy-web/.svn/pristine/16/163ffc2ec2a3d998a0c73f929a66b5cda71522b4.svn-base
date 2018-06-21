package com.fairyland.jdp.framework.security.filterchain.service;

import org.springframework.stereotype.Component;

public interface FilterChainStatusManager {

	public final static int FILTER_CHAIN_NORMAL = 0;

	public final static int FILTER_CHAIN_UPDATING = 1;

	public final static int FILTER_CHAIN_ERROR = 2;
	
	/**
	 * 更新过滤器链时改变状态
	 */
	public void updatingFilterChain();

	/**
	 * 更新过滤器链时成功后，改变状态
	 */
	public void updatedFilterChain();

	/**
	 * 更新过滤器链时发生异常，改变状态
	 */
	public void errorFilterChain();

	/**
	 * 获得过滤器链的状态
	 * @return
	 */
	public int getFilterChainStatus();
	
}
