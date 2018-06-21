package com.fairyland.jdp.webbindsupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 * 用于配置springMVC3.1以后  全局日期转换
 * @author XiongMiao
 *
 */
public class DateConverter implements Converter<String, java.util.Date> {

	@Override
	public java.util.Date convert(String source) {
		if(StringUtils.isEmpty(source)){
			return null;
		}
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    try {    
	        return dateFormat.parse(source);    
	    } catch (ParseException e) {    
			throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
	    }
	}
}
