/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.core.persistence;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort.Direction;

import com.google.common.collect.Maps;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月6日 下午2:28:22
 * @version V1.0
 */
public class SortFilter {

    public String fieldName;
    public Direction direction;

    public SortFilter(Direction direction, String fieldName) {
	this.fieldName = fieldName;
	this.direction = direction;
    }

    public static Map<String, SortFilter> parse(String sortString) {
	if (StringUtils.isEmpty(sortString)) {
	    throw new IllegalArgumentException(
		    "sortTypeString can not be null!");
	}
	Map<String, SortFilter> filters = Maps.newHashMap();
	String[] sortTypes = sortString.split(",");

	for (String key : sortTypes) {
	    String[] names = StringUtils.split(key, "_");
	    if (names.length != 2) {
		throw new IllegalArgumentException(key
			+ " is not a valid sort filter name");
	    }
	    String filedName = names[0];
	    Direction direction = Direction.valueOf(names[1].toUpperCase());

	    SortFilter filter = new SortFilter(direction, filedName);
	    filters.put(key, filter);
	}

	return filters;
    }
}
