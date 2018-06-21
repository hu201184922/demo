package com.ehuatai.thrift.pool;

import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;

public class PooledTProtocol extends TMultiplexedProtocol {

	public PooledTProtocol(TProtocol protocol, String serviceName) {
		super(protocol, serviceName);
	}

	private TSocketPool<TSocket> pool;

	public TSocketPool<TSocket> getPool() {
		return pool;
	}

	public void setPool(TSocketPool<TSocket> pool) {
		this.pool = pool;
	}

	public void close() {
		pool.returnObject((TSocket) getTransport());
	}
}
