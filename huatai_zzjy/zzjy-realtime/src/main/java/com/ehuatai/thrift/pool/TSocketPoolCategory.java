package com.ehuatai.thrift.pool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.transport.TSocket;

public class TSocketPoolCategory {

	private Map<String, TSocketPool<TSocket>> consulServiceMap;

	public TSocketPoolCategory() {
		this.consulServiceMap = new HashMap<String, TSocketPool<TSocket>>();
	}

	public TSocketPool<TSocket> getPool(String consulServiceName) {
		if (!consulServiceMap.containsKey(consulServiceName))
			return null;
		else {
			return consulServiceMap.get(consulServiceName);
		}
	}

	public void setPool(String consulServiceName, TSocketPool<TSocket> pool) {
		consulServiceMap.put(consulServiceName, pool);
	}

	public Collection<TSocketPool<TSocket>> getAllPools() {
		return consulServiceMap.values();
	}
}
