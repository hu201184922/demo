package com.fairyland.jdp.orm.mybatis.dialect;

import java.util.Map;

import com.fairyland.jdp.orm.mybatis.util.MultiDataSourceManager;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

public class DialectProxy implements Dialect {

	private Map<String, Dialect> targetDialects;

	private Map<String, String> dataSourceTypes;

	private String defaultDialects;

	public void setDefaultDialects(String defaultDialects) {
		this.defaultDialects = defaultDialects;
	}

	public String getCountString(String sql) {
		return getDialect().getCountString(sql);
	}

	private Dialect getDialect() {
		DataSource dataSource = MultiDataSourceManager.getManager().peek();
		if (dataSource == null)
			return targetDialects.get(defaultDialects);
		return targetDialects.get(dataSourceTypes.get(dataSource.name()));
	}

	public String getLimitString(String sql, boolean hasOffset) {
		return getDialect().getLimitString(sql, hasOffset);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return getDialect().getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return getDialect().supportsLimit();
	}

	public void setTargetDialects(Map<String, Dialect> targetDialects) {
		this.targetDialects = targetDialects;
	}

	public void setDataSourceTypes(Map<String, String> dataSourceTypes) {
		this.dataSourceTypes = dataSourceTypes;
	}

}
