package com.fairyland.jdp.orm.mybatis.interceptors;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.log4j.Logger;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.dialect.Dialect;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.common.PropertiesHelper;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;

@SuppressWarnings({"unchecked","rawtypes"})
@Intercepts({ @org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class,
		org.apache.ibatis.session.ResultHandler.class }) })
public class LimitInterceptor implements Interceptor {
	private static final Logger logger = Logger.getLogger(LimitInterceptor.class);

	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int RESULT_HANDLER_INDEX = 3;
	Dialect dialect;

	public Object intercept(Invocation invocation) throws Throwable {
		try {
			Pager<?> pager = getPager(invocation.getArgs()[PARAMETER_INDEX]);
			if (ObjectUtil.isNotNull(pager)) {
				return processIntercept(invocation, invocation.getArgs());
			}
			return invocation.proceed();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

	}

	private Object processIntercept(Invocation invocation, Object[] queryArgs) throws InvocationTargetException,
			IllegalAccessException {
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		Pager<?> pager = getPager(parameter);
		pager.setTotalCount(executeForCount(ms, parameter));
		pager.setPageItems(executeForList(invocation, ms, parameter));
		return pager.getPageItems();
	}

	private Pager<?> getPager(Object parameterObject) {
		if ((ObjectUtil.isNotNull(parameterObject)) && (Map.class.isAssignableFrom(parameterObject.getClass()))) {
			Map<String, Object> param = (Map<String, Object>) parameterObject;
			return param.containsKey("pager") ? (Pager<?>) param.get("pager") : null;
		}
		return null;
	}

	private int executeForCount(MappedStatement ms, Object parameter) {
		int count = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			SqlSource sqlSource = getCountSqlSource(ms, parameter);
			MappedStatement newMappedStatement = copyMappedStatementBySqlSource(ms, sqlSource);
			ClassUtil.setValue(newMappedStatement, "id", newMappedStatement.getId() + "_count");
			connection = newMappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			DefaultParameterHandler dp = new DefaultParameterHandler(ms, parameter, sqlSource.getBoundSql(parameter));
			statement = connection.prepareStatement(sqlSource.getBoundSql(parameter).getSql());
			dp.setParameters(statement);
			rs = statement.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return count;
	}

	private List executeForList(Invocation invocation, MappedStatement ms, Object parameterObject)
			throws InvocationTargetException, IllegalAccessException {
		SqlSource sqlSource = getPageLimitSqlSource(ms, parameterObject);
		MappedStatement newMappedStatement = copyMappedStatementBySqlSource(ms, sqlSource);
		invocation.getArgs()[0] = newMappedStatement;
		return (List) invocation.proceed();
	}

	private SqlSource getCountSqlSource(MappedStatement mappedStatement, Object parameterObject) {
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(mappedStatement.getConfiguration());
		String mapperSQL = getMapperSQL(mappedStatement, parameterObject);
		Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
		String newSql = this.dialect.getCountString(mapperSQL);
		SqlSource sqlSource = sqlSourceParser.parse(newSql, parameterType, null);
		return sqlSource;
	}

	private SqlSource getPageLimitSqlSource(MappedStatement mappedStatement, Object parameterObject) {
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(mappedStatement.getConfiguration());
		String mapperSQL = getMapperSQL(mappedStatement, parameterObject);
		Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
		Pager<?> pager = getPager(parameterObject);
		String newSql = this.dialect.getLimitString(mapperSQL, pager.getFirst(), pager.getPageSize());
		SqlSource sqlSource = sqlSourceParser.parse(newSql, parameterType, null);
		return sqlSource;
	}

	private String getMapperSQL(MappedStatement mappedStatement, Object parameterObject) {
		SqlSource nowSqlSource = mappedStatement.getSqlSource();
		SqlNode sqlNode = (SqlNode) ClassUtil.getValue(nowSqlSource, "rootSqlNode");

		DynamicContext context = new DynamicContext(mappedStatement.getConfiguration(), parameterObject);
		sqlNode.apply(context);
		return context.getSql();
	}

	private MappedStatement copyMappedStatementBySqlSource(MappedStatement mappedStatement, SqlSource sqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(),
				mappedStatement.getId(), sqlSource, mappedStatement.getSqlCommandType());
		builder.resource(mappedStatement.getResource());
		builder.fetchSize(mappedStatement.getFetchSize());
		builder.statementType(mappedStatement.getStatementType());
		builder.keyGenerator(mappedStatement.getKeyGenerator());
		builder.keyProperty((mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length > 0) ? mappedStatement
				.getKeyProperties()[0] : ObjectUtil.isNull(mappedStatement.getKeyProperties()) ? null : null);
		builder.timeout(mappedStatement.getTimeout());
		builder.parameterMap(mappedStatement.getParameterMap());
		builder.resultMaps(mappedStatement.getResultMaps());
		builder.cache(mappedStatement.getCache());
		builder.useCache(mappedStatement.isUseCache());
		MappedStatement newMappedStatement = builder.build();
		return newMappedStatement;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setDialectClass(Class<Dialect> dialectClass) {
		this.dialect = ((Dialect) ClassUtil.newInstance(dialectClass));
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public void setProperties(Properties properties) {
		String dialectClass = new PropertiesHelper(properties).getRequiredString("dialectClass");
		try {
			this.dialect = ((Dialect) Class.forName(dialectClass).newInstance());
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
		}
	}
}