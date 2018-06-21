package com.fairyland.jdp.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;


@Service
public class ConsulClientImpl implements ConsulClient{
	@Autowired  
    private LoadBalancerClient loadBalancer;  
      
    /** 
     * 从所有服务中选择一个服务（轮询） 
     */  
    public String discover(String serviceName) {  
    	ServiceInstance si = loadBalancer.choose(serviceName);
    	if(si!=null)
    		return si.getHost();
    	else
    		return null;
    }  
      
}
