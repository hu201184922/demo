package com.fairyland.jdp.framework.i18n.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairyland.jdp.framework.i18n.spi.I18NSupport;

@Aspect
public class CacheLocalizationInterceptor {
	
	@Autowired
	private I18NSupport i18NSupport;

	@Around("execution(* getCache(..)) && target(org.springframework.cache.ehcache.EhCacheCacheManager)")
	public Object beforeGetCache(ProceedingJoinPoint pjp)throws Throwable{
		Object [] args=pjp.getArgs();
		args[0]=args[0]+"_"+i18NSupport.getCurrentLocale().toString();
		return pjp.proceed(args);
	}
}
