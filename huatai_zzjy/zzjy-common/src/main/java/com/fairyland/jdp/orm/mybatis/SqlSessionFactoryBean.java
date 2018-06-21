package com.fairyland.jdp.orm.mybatis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.DefaultDatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fairyland.jdp.orm.mybatis.bingding.MyBatisMapperRegistry;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;



public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {
    private final Log logger = LogFactory.getLog(getClass());
    private Resource configLocation;
    private Resource[] mapperLocations;
    private DataSource dataSource;
    private TransactionFactory transactionFactory;
    private Properties configurationProperties;
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    private SqlSessionFactory sqlSessionFactory;
    private String environment = SqlSessionFactoryBean.class.getSimpleName();
    private boolean failFast;
    private Interceptor[] plugins;
    private TypeHandler<?>[] typeHandlers;
    private String typeHandlersPackage;
    private Class<?>[] typeAliases;
    private String typeAliasesPackage;
    private DatabaseIdProvider databaseIdProvider = new DefaultDatabaseIdProvider();

    public DatabaseIdProvider getDatabaseIdProvider() {
	return this.databaseIdProvider;
    }

    public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {
	this.databaseIdProvider = databaseIdProvider;
    }

    public void setPlugins(Interceptor[] plugins) {
	this.plugins = plugins;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
	this.typeAliasesPackage = typeAliasesPackage;
    }

    public void setTypeHandlersPackage(String typeHandlersPackage) {
	this.typeHandlersPackage = typeHandlersPackage;
    }

    public void setTypeHandlers(TypeHandler<?>[] typeHandlers) {
	this.typeHandlers = typeHandlers;
    }

    public void setTypeAliases(Class<?>[] typeAliases) {
	this.typeAliases = typeAliases;
    }

    public void setFailFast(boolean failFast) {
	this.failFast = failFast;
    }

    public void setConfigLocation(Resource configLocation) {
	this.configLocation = configLocation;
    }

    public void setMapperLocations(Resource[] mapperLocations) {
	this.mapperLocations = mapperLocations;
    }

    public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {
	this.configurationProperties = sqlSessionFactoryProperties;
    }

    public void setDataSource(DataSource dataSource) {
	if ((dataSource instanceof TransactionAwareDataSourceProxy)) {
	    this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
	} else
	    this.dataSource = dataSource;
    }

    public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
	this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
    }

    public void setTransactionFactory(TransactionFactory transactionFactory) {
	this.transactionFactory = transactionFactory;
    }

    public void setEnvironment(String environment) {
	this.environment = environment;
    }

    public void afterPropertiesSet() throws Exception {
	Assert.notNull(this.dataSource, "Property 'dataSource' is required");
	Assert.notNull(this.sqlSessionFactoryBuilder, "Property 'sqlSessionFactoryBuilder' is required");
	try {
	    this.sqlSessionFactory = buildSqlSessionFactory();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    throw e;
	} catch (Throwable throwable) {
	    if (ObjectUtil.isNull(this.sqlSessionFactory))
		logger.error("sqlSessionFactory 加载失败 : " + throwable + ">" + this + ">" + this.sqlSessionFactory);
	    throw new Exception(throwable);
	}
    }

    @SuppressWarnings("unchecked")
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
	XMLConfigBuilder xmlConfigBuilder = null;
	Configuration configuration;
	if (this.configLocation != null) {
	    xmlConfigBuilder = new XMLConfigBuilder(this.configLocation.getInputStream(), null, this.configurationProperties);
	    configuration = xmlConfigBuilder.getConfiguration();
	    ClassUtil.setValue(configuration, "mapperRegistry", new MyBatisMapperRegistry(configuration));
	} else {
	    if (this.logger.isDebugEnabled()) {
		this.logger.debug("Property 'configLocation' not specified, using default MyBatis Configuration");
	    }
	    configuration = new Configuration();
	    configuration.setVariables(this.configurationProperties);
	}

	if (StringUtils.hasLength(this.typeAliasesPackage)) {
	    String[] typeAliasPackageArray = StringUtils.tokenizeToStringArray(this.typeAliasesPackage, ",; \t\n");

	    for (String packageToScan : typeAliasPackageArray) {
		configuration.getTypeAliasRegistry().registerAliases(packageToScan);
		if (this.logger.isDebugEnabled()) {
		    this.logger.debug("Scanned package: '" + packageToScan + "' for aliases");
		}
	    }
	}

	if (!ObjectUtils.isEmpty(this.typeAliases)) {
	    for (Class typeAlias : this.typeAliases) {
		configuration.getTypeAliasRegistry().registerAlias(typeAlias);
		if (this.logger.isDebugEnabled()) {
		    this.logger.debug("Registered type alias: '" + typeAlias + "'");
		}
	    }
	}

	if (!ObjectUtils.isEmpty(this.plugins)) {
	    for (Interceptor plugin : this.plugins) {
		configuration.addInterceptor(plugin);
		if (this.logger.isDebugEnabled()) {
		    this.logger.debug("Registered plugin: '" + plugin + "'");
		}
	    }
	}

	if (StringUtils.hasLength(this.typeHandlersPackage)) {
	    String[] typeHandlersPackageArray = StringUtils.tokenizeToStringArray(this.typeHandlersPackage, ",; \t\n");

	    for (String packageToScan : typeHandlersPackageArray) {
		configuration.getTypeHandlerRegistry().register(packageToScan);
		if (this.logger.isDebugEnabled()) {
		    this.logger.debug("Scanned package: '" + packageToScan + "' for type handlers");
		}
	    }
	}

	if (!ObjectUtils.isEmpty(this.typeHandlers)) {
	    for (TypeHandler typeHandler : this.typeHandlers) {
		configuration.getTypeHandlerRegistry().register(typeHandler);
		if (this.logger.isDebugEnabled()) {
		    this.logger.debug("Registered type handler: '" + typeHandler + "'");
		}
	    }
	}

	if (xmlConfigBuilder != null) {
	    try {
		xmlConfigBuilder.parse();
		if (this.logger.isDebugEnabled())
		    this.logger.debug("Parsed configuration file: '" + this.configLocation + "'");
	    } catch (Exception ex) {
		throw new NestedIOException("Failed to parse config resource: " + this.configLocation, ex);
	    } finally {
		ErrorContext.instance().reset();
	    }
	}

	if (this.transactionFactory == null) {
	    this.transactionFactory = new SpringManagedTransactionFactory();
	}

	Environment environment = new Environment(this.environment, this.transactionFactory, this.dataSource);
	configuration.setEnvironment(environment);

	if (this.databaseIdProvider != null) {
	    try {
		configuration.setDatabaseId(this.databaseIdProvider.getDatabaseId(this.dataSource));
	    } catch (SQLException e) {
		throw new NestedIOException("Failed getting a databaseId", e);
	    }
	}

	if (!ObjectUtils.isEmpty(this.mapperLocations)) {
	    for (Resource mapperLocation : this.mapperLocations) {
		if (mapperLocation == null) {
		    continue;
		}
		try {
		    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(), configuration, mapperLocation.toString(), configuration.getSqlFragments());
		    xmlMapperBuilder.parse();
		} catch (Exception e) {
		    throw new NestedIOException("Failed to parse mapping resource: '" + mapperLocation + "'", e);
		} finally {
		    ErrorContext.instance().reset();
		}
		if (this.logger.isDebugEnabled()) {
		    this.logger.debug("Parsed mapper file: '" + mapperLocation + "'");
		}
	    }
	} else if (this.logger.isDebugEnabled()) {
	    this.logger.debug("Property 'mapperLocations' was not specified or no matching resources found");
	}

	return this.sqlSessionFactoryBuilder.build(configuration);
    }

    public SqlSessionFactory getObject() throws Exception {
	if (this.sqlSessionFactory == null) {
	    afterPropertiesSet();
	}
	return this.sqlSessionFactory;
    }

    public Class<? extends SqlSessionFactory> getObjectType() {
	return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();
    }

    public boolean isSingleton() {
	return true;
    }

    public void onApplicationEvent(ApplicationEvent event) {
	if ((this.failFast) && ((event instanceof ContextRefreshedEvent))) {
	    this.sqlSessionFactory.getConfiguration().getMappedStatementNames();
	}
    }
}
