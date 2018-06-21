package com.fairyland.jdp.framework.cache.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface RedisCacheable {
	/**
	 * 在Redis中的Key的前缀
	 * @return
	 */
	public String prefix() default "";
//	/**
//	 * 超时时间，单位：秒
//	 * @return
//	 */
//    public int expire() default 0;
    /**
     * 如果useJson为true，且返回类型为List，必须指定List内的类的名称
     * @return
     */
    public Class listClass() default Object.class;
    /**
     * 是否使用json保存数据，为false时使用object序列化并转换成base64字符串保存。
     * 使用JPA查询时请将此项设为true
     * @return
     */
    public boolean useJson() default false;
}
