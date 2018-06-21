package com.fairyland.jdp.springboot.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fairyland.jdp.core.persistence.SearchFilter;

public class SearchFilterUtil {
	public static void addFilter(Map<String,Object> searchFilter,String fieldName,SearchFilter.Operator operator,String value){
		searchFilter.put(fieldName+"_"+operator.toString(), value);
	}
	public static Map<String, Object> convert(Map<String, SearchFilter> searchFilters){
		Map<String,Object> map = new HashMap<String,Object>();
		Set<Entry<String, SearchFilter>> entrySet = searchFilters.entrySet();
		for (Entry<String, SearchFilter> entry : entrySet) {
			SearchFilter searchFilter = entry.getValue();
			Object value = searchFilter.value;
			String key = searchFilter.operator.toString()+"_"+searchFilter.fieldName;
			map.put(key, value);
		}
		return map;
	}
}
