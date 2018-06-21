package com.fairyland.jdp.framework.cache.redis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.framework.util.RedisUtil;

@Aspect
@Component
public class RedisCacheableAop {
	@Autowired
	private DictionaryService dictionaryService;
	private static Logger logger = LoggerFactory.getLogger(RedisCacheableAop.class);

	@Around("execution(* *(..)) && @annotation(cache)")
	public Object cached(final ProceedingJoinPoint pjp, RedisCacheable cache) throws Throwable {
		DictionaryItem dictItem = dictionaryService.findByDictCodeAndDictItemCode("redis", "enabled");
		// String enabled = PropsUtil.get("redis.enabled");
		String enabled = dictItem == null ? null : dictItem.getDescript();
		if ("true".equals(enabled)) {
			// 生成规则key，通过key获取缓存数据
			boolean useJson = cache.useJson();
			String key = getCacheKey(pjp, cache);
			if (key.contains("null")) {
				logger.debug("key中包含null，跳过Redis读取：" + key);
				return pjp.proceed();
			}
			logger.debug("Redis预取：" + key);
			Object value = null;
			if (RedisUtil.exists(key)) {
				logger.debug("Redis命中：" + key);
				try {
					if (useJson) {
						Class returnType = getReturnType(pjp);
						String name1 = returnType.getName();
						String name2 = List.class.getName();
						if (name1.equals(name2)) {
							returnType = cache.listClass();
							value = RedisUtil.getListByJson(key, returnType);
						} else {
							value = RedisUtil.getByJson(key, returnType);
						}
					} else {
						value = RedisUtil.get(key);
					}
				} catch (Exception e) {
					e.printStackTrace();
					RedisUtil.del(key);
				}

				return value;
			}

			logger.debug("Redis未命中:" + key + ",进行缓存");
			// 跳过缓存,到后端查询数据
			value = pjp.proceed();

			// 如果没有设置过期时间,则无限期缓存
			// if (Constants.REDIS_DATA_EXPIRE <= 0) {
			if (useJson) {
				RedisUtil.setByJson(key, 0, value);
			} else {
				RedisUtil.set(key, 0, value);
			}

			// } else {
			// RedisUtil.set(key, Constants.REDIS_DATA_EXPIRE, value);
			// }
			return value;
		} else {
			return pjp.proceed();
		}
	}

	@Around("execution(* *(..)) && @annotation(cache)")
	public Object cached(final ProceedingJoinPoint pjp, RedisRemoveCache cache) throws Throwable {
		// 生成规则key，通过key获取缓存数据
		String key = getCacheKey(pjp, cache);
		logger.debug("Redis删除缓存:" + key);
		RedisUtil.del(key);
		// 跳过缓存,到后端查询数据
		Object value = pjp.proceed();
		return value;

	}

	public static Class getReturnType(ProceedingJoinPoint pjp) {
		MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());
		Class returnType = methodSignature.getReturnType();
		return returnType;
	}

	/**
	 * 获取缓存的key值
	 * 
	 * @param pjp
	 * @param cache
	 * @return
	 */
	public static String getCacheKey(ProceedingJoinPoint pjp, RedisCacheable cache) {
		String prefix = cache.key();
		String keyGenerator = cache.keyGenerator();
		return getCacheKey(pjp, prefix, keyGenerator);
	}

	public static String getCacheKey(ProceedingJoinPoint pjp, RedisRemoveCache cache) {
		String prefix = cache.key();
		String keyGenerator = cache.keyGenerator();
		return getCacheKey(pjp, prefix, keyGenerator);
	}

	public static String getCacheKey(ProceedingJoinPoint pjp, String key, String keyGenerator) {
		String packageName = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		MethodSignature ms = ((MethodSignature) pjp.getSignature());

		try {
			List<Object> params = new ArrayList<Object>();
			Annotation[][] pas = pjp.getTarget().getClass().getMethod(methodName, ms.getMethod().getParameterTypes())
					.getParameterAnnotations();
			for (int i = 0; i < pas.length; i++) {
				for (Annotation an : pas[i]) {
					if (an instanceof RedisCacheKey) {
						params.add(args[i]);
					}
				}
			}
			if (!keyGenerator.equals("")) {
				Method[] methods = pjp.getTarget().getClass().getMethods();
				for (Method method : methods) {
					if (method.getName().equals(keyGenerator)) {
						try {
							Object newKey = method.invoke(pjp.getTarget(), params.toArray());
							return (String) newKey;
						} catch (Exception e) {
							e.printStackTrace();
							return null;
						}
					}
				}
			} else {
				String newKey = MessageFormat.format(key, params.toArray());
				return newKey;
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
