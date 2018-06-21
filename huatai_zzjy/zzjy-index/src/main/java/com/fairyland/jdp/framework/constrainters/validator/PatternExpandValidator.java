package com.fairyland.jdp.framework.constrainters.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * 正则表达式约束验证类
 * 
 * @Desc 使用该验证类时，对应的约束要求必须含有无参的方法“regexp”
 * 
 * @author XiongMiao
 *
 */
public class PatternExpandValidator implements ConstraintValidator<Annotation, String>{
	
	public PatternExpandValidator(){
	}
	
	private String regexp;
	
	private String preferenceCode;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String regexp = null;
			regexp = this.regexp;
		Pattern pat = Pattern.compile(regexp);  
		return pat.matcher(value).find();
	}

	@Override
	public void initialize(Annotation constraintAnnotation) {
//		javax.validation.constraints.Pattern pattern = 
//				constraintAnnotation.annotationType().getAnnotation(javax.validation.constraints.Pattern.class);
//		if(pattern == null){
//			throw new NullPointerException("this annotation must have a Pattern");
//		}
		try {
			Method patternMethod = constraintAnnotation.annotationType().getMethod("regexp");

			this.regexp = (String) patternMethod.invoke(constraintAnnotation);
		} catch (SecurityException e) {
			throw new UnsupportedOperationException("must have a 'regexp' method", e);
		} catch (NoSuchMethodException e) {
			throw new UnsupportedOperationException("must have a 'regexp' method", e);
		}catch (InvocationTargetException e) {
			throw new UnsupportedOperationException(e);
		} catch (IllegalArgumentException e) {
			throw new UnsupportedOperationException("the 'regexp' method must hasn't any parameters", e);
		}catch (IllegalAccessException e) {
			throw new UnsupportedOperationException(e);
		}

		try {
			Method preferenceCodeMethod = 
					constraintAnnotation.annotationType().getMethod("preferenceCode");
			if(preferenceCodeMethod != null){
				this.preferenceCode = (String) preferenceCodeMethod.invoke(constraintAnnotation);
			}
		} catch (SecurityException e) {
			//ignore
		} catch (NoSuchMethodException e) {
			//ignore
		}catch (InvocationTargetException e) {
			//ignore
		} catch (IllegalArgumentException e) {
			//ignore
		}catch (IllegalAccessException e) {
			//ignore
		}
	}

}
