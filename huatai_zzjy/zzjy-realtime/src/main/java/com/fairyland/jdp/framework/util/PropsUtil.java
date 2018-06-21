package com.fairyland.jdp.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.springboot.util.NetUtil;

public class PropsUtil {
	private static Logger log = LoggerFactory.getLogger(PropsUtil.class);
	private static Map<String, String> propsMap = new HashMap<String, String>();
	static {
		Properties props = new Properties();
		InputStream in = PropsUtil.class.getResourceAsStream("/application.properties");
		try {
			props.load(in);
			Set<Object> keySet = props.keySet();
			for (Object object : keySet) {
				if (object.toString().equals("server.port")) {
					String serverPortStr = props.getProperty((String) object);
					Integer port = Integer.parseInt(serverPortStr);
					while (NetUtil.isLoclePortUsing(port)) {
						port++;
					}
					propsMap.put(object.toString(), port.toString());
				} else {
					propsMap.put(object.toString(), props.getProperty((String) object));
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	public static String get(String key) {
		return propsMap.get(key);
	}

	public static Integer getServerPort() {
		String serverPortStr = get("server.port");
		Integer port = Integer.parseInt(serverPortStr);
		return port;
	}

	public static Integer getInt(String key) {
		return Integer.parseInt(get(key));
	}

	public static Long getLong(String key) {
		return Long.parseLong(get(key));
	}

	public static Boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}
}
