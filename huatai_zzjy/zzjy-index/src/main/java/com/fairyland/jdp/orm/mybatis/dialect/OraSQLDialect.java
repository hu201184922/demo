package com.fairyland.jdp.orm.mybatis.dialect;

import java.util.regex.Matcher;

import com.fairyland.jdp.orm.util.regexp.RegexpUtil;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil.ReplaceCallBack;


public class OraSQLDialect implements Dialect {

    protected static final String SQL_END_DELIMITER = ";";

//    protected static final String ORA_SQL_LIMIT = "select * from (select ora_a.*,rownum row_num from ({SQL}) ora_a ) ora_b where ora_b.row_num between {OFFSET} and {LIMIT}";
    protected static final String ORA_SQL_LIMIT = "select * from (select ora_a.*,rownum row_num from ({SQL}) ora_a where rownum <= {LIMIT} ) where row_num >= {OFFSET}";
    
    public String getLimitString(String sql, boolean hasOffset) {
	return new StringBuffer(sql.length() + 20).append(trim(sql)).append(hasOffset ? " limit ?,?" : " limit ?").append(SQL_END_DELIMITER).toString();
    }

    public String getLimitString(final String sql, final int offset, final int limit) {
	StringBuffer sb = new StringBuffer(sql.length() + 20);
	sb.append(RegexpUtil.replace(ORA_SQL_LIMIT, "\\{[A-Z]+\\}", new ReplaceCallBack() {

	    public String replace(String group, int i, Matcher m) {
		if ("{SQL}".equals(group)) {
		    return trim(sql);
		} else if ("{OFFSET}".equals(group)) {
		    return String.valueOf(offset + 1);
		} else if ("{LIMIT}".equals(group)) {
		    return String.valueOf(offset > 0 ? limit + offset : limit);
		}
		return group;
	    }
	    
	}));
	return DialectUtil.pretty(sb.toString());
    }

    public boolean supportsLimit() {
	return true;
    }

    private String trim(String sql) {
	sql = sql.trim();
	if (sql.endsWith(SQL_END_DELIMITER)) {
	    sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
	}
	return sql;
    }

    public String getCountString(String sql) {
	return DialectUtil.pretty(new StringBuffer(sql.length() + 20).append("select count(1) from (").append(trim(sql)).append(") countsql").toString());
    }

}
