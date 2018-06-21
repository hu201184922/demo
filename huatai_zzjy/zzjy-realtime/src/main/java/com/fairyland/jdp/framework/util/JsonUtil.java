package com.fairyland.jdp.framework.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
	private static ObjectMapper getObjectMapper(){
		return new ObjectMapper();
	}
	
	/**
	 * map转换成json字符串
	 * @Desc 已过时，可以使用JacksonUtil的beanToJson方法
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	@Deprecated
	public static String toJSON(Map<Object, Object> map) throws JsonProcessingException{
		if(map == null){
			return null;
		}

		return getObjectMapper().writeValueAsString(map);
		
	}
}
