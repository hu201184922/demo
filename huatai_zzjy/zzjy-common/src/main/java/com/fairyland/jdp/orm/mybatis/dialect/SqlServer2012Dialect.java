package com.fairyland.jdp.orm.mybatis.dialect;

public class SqlServer2012Dialect implements Dialect {
	protected static final String SQL_END_DELIMITER = ";";

    public String getLimitString(String sql, boolean hasOffset) {
	return sql.length() + 20 + trim(sql) + (hasOffset ? " limit ?,?" : " limit ?") + ";";
    }
    //sqlserver分页,必须有order by条件,否则报错
    public String getLimitString(String sql, int offset, int limit) {
	sql = trim(sql);
	StringBuffer sb = new StringBuffer(sql.length() + 20);
	sb.append(sql);
    sb.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(limit).append(" ROWS ONLY;");
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
    //sqlserver中count子句中不能包含order by,否则报错.
    //默认order by放最后,如果sql复杂,有多个order by嵌套,则不能使用该分页方法,请自行在sql中分页.
    private String delOrder(String sql){
    	sql = sql.toLowerCase();
    	sql = sql.substring(0,sql.lastIndexOf("order"));
    	return sql;
    }
    public String getCountString(String sql) {
	return  "select count(1) from (" + delOrder(trim(sql)) + ") as countSql";
    }
    public static void main(String[] args) {
		String sql = "select id_,account from jdp_user";
		SqlServer2012Dialect dialect = new SqlServer2012Dialect();
		System.out.println(dialect.getLimitString(sql,20,10));
	}
}
