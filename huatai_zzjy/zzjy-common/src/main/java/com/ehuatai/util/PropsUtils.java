package com.ehuatai.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropsUtils {
	// 配置文件对象
	private Properties props = null;

	// 默认构造函数，用于sh运行，自动找到classpath下的config.properties。
	public PropsUtils() {
		InputStream in = PropsUtils.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			props = new Properties();
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 根据key值读取配置的值
	public String getValue(String key) {
		return props.getProperty(key);
	}

	// 读取properties的全部信息
	public Map<String, String> getAllProperties() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = props.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}
}
