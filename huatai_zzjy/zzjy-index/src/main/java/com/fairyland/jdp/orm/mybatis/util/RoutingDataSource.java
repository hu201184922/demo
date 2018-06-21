package com.fairyland.jdp.orm.mybatis.util;

import org.springframework.core.InfrastructureProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;



public class RoutingDataSource extends AbstractRoutingDataSource implements
		InfrastructureProxy {

	private DataSource getDataSource() {
		return MultiDataSourceManager.getManager().peek();
	}

	/**
	 * 返回实际执行DataSource对象对于的key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		DataSource dataSource = getDataSource();
		String key = null;
		if (dataSource == null)
			return null;
		key = dataSource.name();
		return key;
	}

	public Object getWrappedObject() {
		return determineTargetDataSource();
	}

}
