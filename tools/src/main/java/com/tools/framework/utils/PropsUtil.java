package com.tools.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtil {
	private static Logger log = LoggerFactory.getLogger(PropsUtil.class);
	private static Map<String,String> propsMap = new HashMap<String,String>();
	static{
		Properties props = new Properties();
		InputStream in = PropsUtil.class.getResourceAsStream("/application.properties");
		try {
			props.load(in);
			Set<Object> keySet = props.keySet();
			for (Object object : keySet) {
				propsMap.put(object.toString(), props.getProperty((String)object));
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if(in != null){try {in.close();} catch (IOException e) {log.error(e.getMessage());}}
		}
	}
	public static String get(String key){
		return propsMap.get(key);
	}
}
