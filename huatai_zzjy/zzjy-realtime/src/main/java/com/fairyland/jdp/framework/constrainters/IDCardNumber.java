package com.fairyland.jdp.framework.constrainters;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.fairyland.jdp.framework.constrainters.validator.PatternExpandValidator;

/**
 * 身份证验证约束注解
 * @Desc 
 * 		身份证号码为15位或者18位;
 * 		15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
 * 		如果需要更为严格的验证可以修改正则表达式，
 * 		18为严格表达式：((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))  
 *		，或者使用首选项方式配置
 * @author XiongMiao
 *
 */
//@Pattern(regexp = "(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)"
//		+ "|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x|X)$)")
@Constraint(validatedBy = {PatternExpandValidator.class}) 
@Documented 
@Target(value = { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD } ) 
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IDCardNumber {
	
	String regexp() default "(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)"
			+ "|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x|X)$)";

//	String preferenceCode() default "";
	
	 String message() default "Not Valid ID Card"; 
	 
	 Class<?>[] groups() default {}; 
	 
	 Class<? extends Payload>[] payload() default {};
	 
}
