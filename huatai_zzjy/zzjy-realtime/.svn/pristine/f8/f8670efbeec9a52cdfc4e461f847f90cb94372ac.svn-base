package com.fairyland.jdp.springboot.config.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * 非连接池数据源，有时候不希望保持连接时使用。
 * 每次查询都会新建连接，用完会关闭连接。
 */
public class NonePoolDataSource implements DataSource{
	private String url;
	private String driverClassName;
	private String username;
	private String password;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		for(int i=0;i<3;i++){
			try {
				Class.forName(driverClassName);
				if(username==null || password==null){
					Connection conn = DriverManager.getConnection(url);
					return conn;
				}else{
					Connection conn = DriverManager.getConnection(url,username,password);
					return conn;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		throw new SQLException("连接创建失败");
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}
	
}
