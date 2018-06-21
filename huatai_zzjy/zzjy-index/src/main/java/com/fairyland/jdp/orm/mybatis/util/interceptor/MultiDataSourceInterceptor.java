package com.fairyland.jdp.orm.mybatis.util.interceptor;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import com.fairyland.jdp.orm.mybatis.util.MultiDataSourceManager;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil;

@Intercepts( { @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }), @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }), @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MultiDataSourceInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger(MultiDataSourceInterceptor.class);

    static int MAPPED_STATEMENT_INDEX = 0;

    public Object intercept(Invocation invocation) throws Throwable {
	MappedStatement ms = (MappedStatement) invocation.getArgs()[MAPPED_STATEMENT_INDEX];
	String className = RegexpUtil.replace(ms.getId(),"!.*","");
	className = RegexpUtil.replace(className, ".[_a-zA-Z0-9]+$", "");
	DataSource dataSource = ClassUtil.getClassGenricType(ClassUtil.forName(className), DataSource.class);
	try {
	    if (ObjectUtil.isNotNull(dataSource))
		MultiDataSourceManager.getManager().push(dataSource);
	    return invocation.proceed();
	} finally {
	    if (ObjectUtil.isNotNull(dataSource))
		MultiDataSourceManager.getManager().pop();
	}
    }

    public Object plugin(Object target) {
	return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}

