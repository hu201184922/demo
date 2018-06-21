package com.ehuatai.thrift.config;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.ehuatai.thrift.annotation.ThriftService;
import com.ehuatai.thrift.pool.TSocketFactory;
import com.ehuatai.thrift.pool.TSocketPool;
import com.ehuatai.thrift.pool.TSocketPoolCategory;
import com.fairyland.jdp.framework.util.PropsUtil;

public class ThriftServiceScanner extends ClassPathBeanDefinitionScanner {
	private TSocketPoolCategory tSocketPoolCategory;

	public ThriftServiceScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	public void registerDefaultFilters() {
		this.addIncludeFilter(new AnnotationTypeFilter(ThriftService.class));
	}

	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		for (BeanDefinitionHolder holder : beanDefinitions) {
			try {
				GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
				System.out.println(definition.getBeanClassName());
				ThriftService anno = Class.forName(definition.getBeanClassName()).getAnnotation(ThriftService.class);
				String consulServiceName = anno.consulServiceName();
				int port = anno.thriftServicePort();
				// -----
				// AutoClearGenericObjectPool pool =
				// thriftPoolCategory.getPool(consulServiceName,
				// thriftServiceName);
				// if (pool == null) {
				// GenericObjectPoolConfig poolConfig = new
				// GenericObjectPoolConfig();
				// poolConfig.setMaxTotal(50);
				// poolConfig.setMaxIdle(20);
				// poolConfig.setMinIdle(2);
				// poolConfig.setTestOnBorrow(true);
				// pool = new AutoClearGenericObjectPool<>(
				// new TProtocolFactory(consulServiceName, thriftServiceName,
				// true), poolConfig);
				// thriftPoolCategory.setPool(consulServiceName,
				// thriftServiceName, pool);
				// }
				// thriftPoolCategory.setClassPool(definition.getBeanClassName(),
				// pool);
				// ------------
				TSocketPool<TSocket> pool = tSocketPoolCategory.getPool(consulServiceName);
				if (pool == null) {
					GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
					poolConfig.setMaxTotal(PropsUtil.getInt("thrift.pool.maxTotal"));
					poolConfig.setMaxIdle(PropsUtil.getInt("thrift.pool.maxIdle"));
					poolConfig.setMinIdle(PropsUtil.getInt("thrift.pool.minIdle"));
					poolConfig.setMaxWaitMillis(PropsUtil.getLong("thrift.pool.maxWaitMillis"));
					poolConfig.setTestOnBorrow(PropsUtil.getBoolean("thrift.pool.testOnBorrow"));
					if (port <= 0) {
						pool = new TSocketPool<TSocket>(
								new TSocketFactory(consulServiceName, PropsUtil.getBoolean("thrift.pool.keepAlive")),
								poolConfig);
					} else {
						pool = new TSocketPool<TSocket>(new TSocketFactory(consulServiceName, port,
								PropsUtil.getBoolean("thrift.pool.keepAlive")), poolConfig);
					}
					// try {
					// pool.preparePool();
					// } catch (Exception e) {
					// e.printStackTrace();
					// }
					tSocketPoolCategory.setPool(consulServiceName, pool);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return beanDefinitions;
	}

	public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return super.isCandidateComponent(beanDefinition)
				&& beanDefinition.getMetadata().hasAnnotation(ThriftService.class.getName());
	}

	public TSocketPoolCategory gettSocketPoolCategory() {
		return tSocketPoolCategory;
	}

	public void settSocketPoolCategory(TSocketPoolCategory tSocketPoolCategory) {
		this.tSocketPoolCategory = tSocketPoolCategory;
	}

}
