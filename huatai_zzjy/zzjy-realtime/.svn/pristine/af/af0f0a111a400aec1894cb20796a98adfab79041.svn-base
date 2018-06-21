package com.fairyland.jdp.orm.mybatis.dialect;

public class ImpalaDialect implements Dialect {
    protected static final String SQL_END_DELIMITER = ";";

    public String getLimitString(String sql, boolean hasOffset) {
	return sql.length() + 20 + trim(sql) + (hasOffset ? " limit ? offset ?" : " limit ?") + ";";
    }

    public String getLimitString(String sql, int offset, int limit) {
	sql = trim(sql);
	StringBuffer sb = new StringBuffer(sql.length() + 20);
	sb.append(sql);
	if (offset > 0)
	    sb.append(" limit ").append(limit).append(" offset ").append(offset).append(";");
	else {
	    sb.append(" limit ").append(limit).append(";");
	}
	return sb.toString();
    }

    public boolean supportsLimit() {
	return true;
    }

    private String trim(String sql) {
	sql = sql.trim();
	if (sql.endsWith(";")) {
	    sql = sql.substring(0, sql.length() - 1 - ";".length());
	}
	return sql;
    }

    public String getCountString(String sql) {
	return  "select count(1) from (" + trim(sql) + ") as countSql";
    }
}