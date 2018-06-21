package com.fairyland.jdp.filter;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class ParameterRequestWrapper extends HttpServletRequestWrapper {
	
	private Map<String, String[]> paramsMap;
	
	public ParameterRequestWrapper(HttpServletRequest request,
			Map<String, String[]> paramsMap) {
		super(request);
		this.paramsMap = paramsMap;
		renewParameterMap(request);
	}

	public ParameterRequestWrapper(HttpServletRequest request,
			Map<String, String[]> paramsMap,boolean isMobile) {
		super(request);
		this.paramsMap = paramsMap;
		renewParameterMap(request);
	}
	
	@Override
	public String getParameter(String name) {
		String result = "";
		
		Object v = paramsMap.get(name);
		if (v == null) {
			result = null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				result =  strArr[0];
			} else {
				result = null;
			}
		} else if (v instanceof String) {
			result = (String) v;
		} else {
			result =  v.toString();
		}
		
		return result;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return paramsMap;
	}

	@Override
	public Enumeration getParameterNames() {
		return new Vector(paramsMap.keySet()).elements();
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] result = null;
		
		Object v = paramsMap.get(name);
		if (v == null) {
			result =  null;
		} else if (v instanceof String[]) {
			result =  (String[]) v;
		} else if (v instanceof String) {
			result =  new String[] { (String) v };
		} else {
			result =  new String[] { v.toString() };
		}
		
		return result;
	}

	private void renewParameterMap(HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Enumeration params = request.getParameterNames();
		
		while(params.hasMoreElements()){
			String name = params.nextElement().toString();
			String[] value = request.getParameterValues(name);
			for (int i = 0; value != null && i < value.length; i++) {
				value[i] = transfrom(value[i]);
			}
			paramsMap.put(name, value);
		}
	}
	
	private String transfrom(String content){
		content = content.replaceAll("&", "&amp;&amp;");
		content = content.replaceAll("<", "&amp;lt;");
		content = content.replaceAll(">", "&amp;gt;");
		content = content.replaceAll("'", "&amp;#039;");
		content = content.replaceAll("\"", "&amp;quot;");
		return content;
	}

}
