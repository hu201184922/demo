package com.ehuatai.thrift.config;

import java.util.Collection;

import org.apache.thrift.transport.TSocket;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ehuatai.thrift.pool.TSocketPool;
import com.ehuatai.thrift.pool.TSocketPoolCategory;
import com.fairyland.jdp.framework.util.PropsUtil;

@Component
public class ThriftServiceScannerConfigurer
		implements BeanFactoryPostProcessor, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		TSocketPoolCategory tSocketPoolCategory = applicationContext.getBean(TSocketPoolCategory.class);
		ThriftServiceScanner scanner = new ThriftServiceScanner((BeanDefinitionRegistry) beanFactory);
		scanner.settSocketPoolCategory(tSocketPoolCategory);
		scanner.setResourceLoader(this.applicationContext);
		scanner.scan(PropsUtil.get("thrift.base-package").split(","));
	}

	@Bean
	public TSocketPoolCategory getTSocketPoolCategory() {
		TSocketPoolCategory category = new TSocketPoolCategory();
		return category;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
//		TSocketPoolCategory tSocketPoolCategory = applicationContext.getBean(TSocketPoolCategory.class);
//		Collection<TSocketPool<TSocket>> list = tSocketPoolCategory.getAllPools();
//		for (TSocketPool<TSocket> tSocketPool : list) {
//			try {
//				tSocketPool.preparePool();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
}
