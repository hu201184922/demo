package com.ehuatai.thrift.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import com.fairyland.jdp.consul.ConsulClient;
import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.thrift.common.pingservice.TPingService;

public class TSocketFactory extends BasePooledObjectFactory<TSocket> {
	private String consulServiceName;
    private int port;
    private boolean keepAlive = true;
 
    public TSocketFactory(String consulServiceName, boolean keepAlive) {
    	this.port = Integer.parseInt(PropsUtil.get("thrift.port"));
        this.consulServiceName = consulServiceName;
        this.keepAlive = keepAlive;
    }
    public TSocketFactory(String consulServiceName,int thriftServicePort, boolean keepAlive) {
    	this.port = thriftServicePort;
        this.consulServiceName = consulServiceName;
        this.keepAlive = keepAlive;
    }
    @Override
    public TSocket create() throws TTransportException {
    	ConsulClient consulClient = SpringUtil.getBean(ConsulClient.class);
    	String host = consulClient.discover(consulServiceName);
    	if(host==null)
    		throw new RuntimeException("Consul Not Ready Yet.Please Wait.");
        TSocket tSocket = new TSocket(host, port);
//        TBinaryProtocol protocol = new TBinaryProtocol(tSocket);
//        PooledTProtocol mp2 = new PooledTProtocol(protocol,thriftServiceName);
        tSocket.open();
        System.out.println("-------------------"+tSocket);
        return tSocket;
    }
 
 
    @Override
    public PooledObject<TSocket> wrap(TSocket protocol) {
        return new DefaultPooledObject<>(protocol);
    }
 
    /**
     * 对象钝化(即：从激活状态转入非激活状态，returnObject时触发）
     *
     * @param pooledObject
     * @throws TTransportException
     */
    @Override
    public void passivateObject(PooledObject<TSocket> pooledObject) throws TTransportException {
        if (!keepAlive) {
            pooledObject.getObject().flush();
            pooledObject.getObject().close();
        }
    }
 
 
    /**
     * 对象激活(borrowObject时触发）
     *
     * @param pooledObject
     * @throws TTransportException
     */
    @Override
    public void activateObject(PooledObject<TSocket> pooledObject) throws TTransportException {
        if (!pooledObject.getObject().isOpen()) {
            pooledObject.getObject().open();
        }
    }
 
 
    /**
     * 对象销毁(clear时会触发）
     * @param pooledObject
     * @throws TTransportException
     */
    @Override
    public void destroyObject(PooledObject<TSocket> pooledObject) throws TTransportException {
        passivateObject(pooledObject);
        pooledObject.markAbandoned();
    }
 
 
    /**
     * 验证对象有效性
     *
     * @param p
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<TSocket> p) {
        if (p.getObject() != null) {
            if (!p.getObject().isOpen()) {
            	try {
                    p.getObject().open();
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
            }
            try {
            	//调用目标连接的TPingService服务以验证连接有效性
            	TSocket tSocket = p.getObject();
                TBinaryProtocol protocol = new TBinaryProtocol(tSocket);
                TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol,"TPingService");
                TPingService.Client tPingService = new TPingService.Client(mp2);
                if(1==tPingService.ping()){
                	tPingService = null;
                	mp2 = null;
                	protocol = null;
                	return true;
                }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
            
        }
        return false;
    }
}
