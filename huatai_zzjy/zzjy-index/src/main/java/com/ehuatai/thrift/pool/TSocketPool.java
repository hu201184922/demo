package com.ehuatai.thrift.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class TSocketPool<T> extends GenericObjectPool<T> {
	 
    public TSocketPool(PooledObjectFactory<T> factory) {
        super(factory);
    }
 
    public TSocketPool(PooledObjectFactory<T> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
 
    @Override
	public T borrowObject() throws Exception {
    	System.out.println(String.format("active:%d,idea:%d", getNumActive(), getNumIdle()));
		return super.borrowObject();
	}

	@Override
    public void returnObject(T obj) {
        super.returnObject(obj);
    }
}
