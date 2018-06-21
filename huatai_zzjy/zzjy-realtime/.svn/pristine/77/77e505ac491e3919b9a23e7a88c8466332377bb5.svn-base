package com.fairyland.jdp.framework.cache.redis;

import java.lang.annotation.Annotation;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.util.RedisUtil;


@Aspect
@Component
public class RedisCacheableAop {
	
	private static Logger logger = LoggerFactory.getLogger(RedisCacheableAop.class);
	@Around("execution(* *(..)) && @annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, RedisCacheable cache) throws Throwable {
        // 生成规则key，通过key获取缓存数据
		boolean useJson = cache.useJson();
        String key = getCacheKey(pjp, cache);
        logger.debug("Redis预取："+key);
        Object value = null;
        if(RedisUtil.exists(key)){
        	logger.debug("Redis命中："+key);
        	try {
        		if(useJson){
            		Class returnType = getReturnType(pjp);
                    String name1 = returnType.getName();
                    String name2 = List.class.getName();
                    if(name1.equals(name2)){
                    	returnType = cache.listClass();
                    	value = RedisUtil.getListByJson(key,returnType);
                    }else{
                    	value = RedisUtil.getByJson(key,returnType);
                    }
            	}else{
            		value = RedisUtil.get(key);
            	}
			} catch (Exception e) {
				e.printStackTrace();
				RedisUtil.del(key);
			}
        	
    		return value;
        }
        
        logger.debug("Redis未命中:"+key+",进行缓存");
        // 跳过缓存,到后端查询数据
        value = pjp.proceed();
        
        // 如果没有设置过期时间,则无限期缓存
//        if (Constants.REDIS_DATA_EXPIRE <= 0) {
        if(useJson){
        	RedisUtil.setByJson(key,0, value);
        }else{
        	RedisUtil.set(key,0, value);
        }
           
//        } else {
//            RedisUtil.set(key, Constants.REDIS_DATA_EXPIRE, value);
//        }
        return value;
    }
	@Around("execution(* *(..)) && @annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, RedisRemoveCache cache) throws Throwable {
        // 生成规则key，通过key获取缓存数据
        String key = getCacheKey(pjp, cache);
        logger.debug("Redis删除缓存:"+key);
        RedisUtil.del(key);
        // 跳过缓存,到后端查询数据
        Object value = pjp.proceed();
        return value;
 
    }
	public static Class getReturnType(ProceedingJoinPoint pjp){
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
        String prefix = cache.prefix();
        return getCacheKey(pjp,prefix);
    }
    public static String getCacheKey(ProceedingJoinPoint pjp, RedisRemoveCache cache) {
        String prefix = cache.prefix();
        return getCacheKey(pjp,prefix);
    }
    public static String getCacheKey(ProceedingJoinPoint pjp, String prefix) {
        StringBuilder buf = new StringBuilder();
        buf.append(prefix);
        Object[] args = pjp.getArgs();
        Annotation[][] pas = ((MethodSignature) pjp.getSignature()).getMethod().getParameterAnnotations();
        for (int i = 0; i < pas.length; i++) {
            for (Annotation an : pas[i]) {
                if (an instanceof RedisCacheKey) {
                	//buf.append("_");
                	if(args[i]!=null)
                		buf.append(args[i].toString()).append("_");
                }
            }
        }
        return buf.substring(0,buf.length()-1);
    }
}
