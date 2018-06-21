package com.fairyland.jdp.framework.quartz.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class JobUtil {
	
	 static private final String Job_FILE = "job.properties";
	
	public static Map<String, Object>  getAllJobClass() throws IOException{
		Map<String, Object> jobMap = new HashMap<String, Object>();
		Properties p = null;
		p = PropertiesLoaderUtils.loadAllProperties(Job_FILE, JobUtil.class.getClassLoader());
		for (Iterator it = p.keySet().iterator(); it.hasNext();) {
		    String key = (String) it.next();
		    String value = p.getProperty(key);
		    jobMap.put(key, value);
		}
		
		return jobMap;
	}

}
