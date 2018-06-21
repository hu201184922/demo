package com.fairyland.jdp.orm.mybatis.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	/**
	 * MyBatis多数据源扩展时，定义子数据源的Key
	 * 
	 * @return
	 */
	String name();

	/**
	 * 要设置的catalog值
	 * 
	 * @return
	 */
	String catalog() default "";

	String value();

}
