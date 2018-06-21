package com.fairyland.jdp.framework.security.filterchain.service;

import org.springframework.stereotype.Component;

@Component(value="filterChainStatusManager")
public class DefaultFilterChainStatusManager implements FilterChainStatusManager{

	private int filterChainStatus = FILTER_CHAIN_NORMAL;
	
	public void initFilterChain(){
		filterChainStatus = FILTER_CHAIN_UPDATING;
	}

	public void updatingFilterChain(){
		filterChainStatus = FILTER_CHAIN_UPDATING;
	}

	public void updatedFilterChain(){
		filterChainStatus = FILTER_CHAIN_NORMAL;
	}

	public void errorFilterChain(){
		filterChainStatus = FILTER_CHAIN_ERROR;
	}

	public int getFilterChainStatus(){
		return filterChainStatus;
	}

	public void setFilterChainStatus(int filterChainStatus) {
		this.filterChainStatus = filterChainStatus;
	}
	
}
