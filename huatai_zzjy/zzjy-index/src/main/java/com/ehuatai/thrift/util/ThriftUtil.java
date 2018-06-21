package com.ehuatai.thrift.util;

import java.lang.reflect.Constructor;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;

import com.ehuatai.thrift.annotation.ThriftService;
import com.ehuatai.thrift.pool.PooledTProtocol;
import com.ehuatai.thrift.pool.TSocketPool;
import com.ehuatai.thrift.pool.TSocketPoolCategory;
import com.fairyland.jdp.framework.util.SpringUtil;

public class ThriftUtil {
	// public static PooledTProtocol getConn(String consulServiceName,String
	// thriftServiceName) throws Exception{
	// ThriftPoolCategory thriftPoolCategory =
	// SpringUtil.getBean(ThriftPoolCategory.class);
	// AutoClearGenericObjectPool pool =
	// thriftPoolCategory.getPool(consulServiceName, thriftServiceName);
	// System.out.println(pool);
	// if (pool == null) {
	// GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
	// poolConfig.setMaxTotal(10);
	// poolConfig.setMinIdle(1);
	// poolConfig.setTestOnBorrow(false);
	// pool = new AutoClearGenericObjectPool<>(
	// new TProtocolFactory(consulServiceName, thriftServiceName, true),
	// poolConfig);
	// thriftPoolCategory.setPool(consulServiceName, thriftServiceName, pool);
	// }
	// System.out.println(String.format("active:%d,idea:%d",
	// pool.getNumActive(), pool.getNumIdle()));
	// PooledTProtocol protocol = (PooledTProtocol)pool.borrowObject();
	//// TProtocol protocol = (TProtocol)pool.borrowObject();
	// protocol.setPool(pool);
	// return protocol;
	// }
	// public static <T> T getService(Class<T> clazz) throws Exception{
	// String className = clazz.getName();
	// String mainClassName = className.substring(0,
	// className.lastIndexOf("$Client"));
	// //-----
	// ThriftService anno =
	// Class.forName(mainClassName).getAnnotation(ThriftService.class);
	// String consulServiceName = anno.consulServiceName();
	// String thriftServiceName = anno.thriftServiceName();
	// int port = anno.thriftServicePort();
	// //------
	// ThriftPoolCategory category =
	// SpringUtil.getBean(ThriftPoolCategory.class);
	// AutoClearGenericObjectPool pool = category.getClassPool(mainClassName);
	// PooledTProtocol protocol = (PooledTProtocol)pool.borrowObject();
	// protocol.setPool(pool);
	// Constructor[] cons = clazz.getConstructors();
	// Constructor constructor = clazz.getConstructor(new
	// Class[]{org.apache.thrift.protocol.TProtocol.class});
	// T myObject = (T) constructor.newInstance(protocol);
	// return myObject;
	// }
	public static <T> T getService(Class<T> clazz) throws Exception {
		String className = clazz.getName();
		String mainClassName = className.substring(0, className.lastIndexOf("$Client"));
		// -----
		ThriftService anno = Class.forName(mainClassName).getAnnotation(ThriftService.class);
		String consulServiceName = anno.consulServiceName();
		String thriftServiceName = anno.thriftServiceName();
		// ------
		TSocketPoolCategory category = SpringUtil.getBean(TSocketPoolCategory.class);
		TSocketPool<TSocket> socketPool = category.getPool(consulServiceName);
		TSocket tSocket = socketPool.borrowObject();
		TBinaryProtocol protocol = new TBinaryProtocol(tSocket);
		PooledTProtocol mp2 = new PooledTProtocol(protocol, thriftServiceName);
		mp2.setPool(socketPool);
		Constructor<T> constructor = clazz.getConstructor(new Class[] { org.apache.thrift.protocol.TProtocol.class });
		T myObject = constructor.newInstance(mp2);
		return myObject;
	}

	public static void close(TServiceClient client) {
		TProtocol protocol = client.getInputProtocol();
		if (protocol instanceof PooledTProtocol) {
			((PooledTProtocol) protocol).close();
		} else {
			throw new RuntimeException("Not Supported Action!");
		}
	}
}
