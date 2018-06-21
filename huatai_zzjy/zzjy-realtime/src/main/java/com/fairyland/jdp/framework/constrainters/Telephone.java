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
 * 电话号码验证约束注解
 * @Desc 
 * 		[国区号-][区号-]座机号[-分机号]
 * 		如果需要严格控制，请修改正则表达式，或者使用首选项方式配置
 * @author XiongMiao
 *
 */
//@Pattern(regexp = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$")
@Constraint(validatedBy = {PatternExpandValidator.class}) 
@Documented 
@Target(value = { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD } ) 
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Telephone {
	
	String regexp() default "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
	
	String preferenceCode() default "";
	
	 String message() default "Not Valid Telephone"; 
	 
	 Class<?>[] groups() default {}; 
	 
	 Class<? extends Payload>[] payload() default {};
	 
}
