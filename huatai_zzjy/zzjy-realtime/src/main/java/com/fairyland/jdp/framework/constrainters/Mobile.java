package com.fairyland.jdp.framework.constrainters;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fairyland.jdp.framework.constrainters.validator.PatternExpandValidator;

/**
 * 手机号码验证约束注解
 * @Desc 
 * 		11为手机号码，以1开头，后面10位不限
 * 		如果需要严格控制，可修改正则表达式，或者使用首选项方式配置
 * @author XiongMiao
 *
 */
//@Pattern(regexp = "^1\\d{10}$")
@Constraint(validatedBy = {PatternExpandValidator.class}) 
@Documented 
@Target(value = { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD } ) 
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Mobile{

	String regexp() default "^1\\d{10}$";
	
	String preferenceCode() default "";
	
	 String message() default "Not Valid Mobile Number"; 
	 
	 Class<?>[] groups() default {}; 
	 
	 Class<? extends Payload>[] payload() default {};
	 
}
