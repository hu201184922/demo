package com.ehuatai.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.model.Check;
import com.fairyland.jdp.framework.util.PropsUtil;

public class ConsulUtil {
	
	public static void removeDeadInstance(String host,Integer port){
		ConsulRawClient client = new ConsulRawClient(host, port);
        ConsulClient consul = new ConsulClient(client);
		Iterator<Map.Entry<String,Service>> it =consul.getAgentServices().getValue().entrySet().iterator();  
        while (it.hasNext()){  
            //迭代数据  
            Map.Entry<String,Service> serviceMap =  it.next();  
            //获得Service对象  
            Service service = serviceMap.getValue();  
            //获取服务名称  
            String serviceName = service.getService();  
            //获取服务ID  
            String serviceId = service.getId();  
            System.out.println("serviceName="+serviceName+";serviceId="+serviceId);
            //根据服务名称获取服务的健康检查信息  
            Response<List<Check>> checkList =consul.getHealthChecksForService(serviceName,null);  
            List<Check> checks = checkList.getValue();
            if(checks!=null){
            	for (Check check : checks) {
            		try {
            			//获取健康状态值  PASSING：正常  WARNING  CRITICAL  UNKNOWN：不正常  
                        Check.CheckStatus checkStatus = check.getStatus();  
                        System.out.println("checkName="+check.getName()+";checkId="+check.getCheckId()+";status="+check.getStatus());
                        if (checkStatus != Check.CheckStatus.PASSING){
                        	String removeServiceId = check.getCheckId().replace("service:", "");
                        	consul.agentServiceDeregister(removeServiceId);
                        }  
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
            }
            
        }  
	}
	public static void removeDeadInstanceOfService(String host,Integer port,String serviceName){
		ConsulRawClient client = new ConsulRawClient(host, port);
        ConsulClient consul = new ConsulClient(client);
		Iterator<Map.Entry<String,Service>> it =consul.getAgentServices().getValue().entrySet().iterator();  
        while (it.hasNext()){  
            //迭代数据  
            Map.Entry<String,Service> serviceMap =  it.next();  
            //获得Service对象  
            Service service = serviceMap.getValue();  
            if(!service.getService().equals(serviceName))
            	continue;
            //获取服务ID  
            String serviceId = service.getId();  
            System.out.println("serviceName="+serviceName+";serviceId="+serviceId);
            //根据服务名称获取服务的健康检查信息  
            Response<List<Check>> checkList =consul.getHealthChecksForService(serviceName,null);  
            List<Check> checks = checkList.getValue();
            if(checks!=null){
            	for (Check check : checks) {
            		try {
            			//获取健康状态值  PASSING：正常  WARNING  CRITICAL  UNKNOWN：不正常  
                        Check.CheckStatus checkStatus = check.getStatus();  
                        System.out.println("checkName="+check.getName()+";checkId="+check.getCheckId()+";status="+check.getStatus());
                        if (checkStatus != Check.CheckStatus.PASSING){
                        	String removeServiceId = check.getCheckId().replace("service:", "");
                        	consul.agentServiceDeregister(removeServiceId);
                        }  
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
            }
            
        }  
	}
	public static void removeDeadInstanceOfService(String serviceName){
		String host = PropsUtil.get("spring.cloud.consul.host");
		Integer port = Integer.parseInt(PropsUtil.get("spring.cloud.consul.port"));
		removeDeadInstanceOfService(host,port,serviceName);
	}
	public static void removeDeadInstance(){
		String host = PropsUtil.get("spring.cloud.consul.host");
		Integer port = Integer.parseInt(PropsUtil.get("spring.cloud.consul.port"));
		removeDeadInstance(host,port);
	}
	public static void removeConsulInstance(String serviceId){
		String host = PropsUtil.get("spring.cloud.consul.host");
		Integer port = Integer.parseInt(PropsUtil.get("spring.cloud.consul.port"));
		removeConsulInstance(host,port,serviceId);
	}
	public static void removeConsulInstance(String ip,int port,String serviceId){
		ConsulRawClient client = new ConsulRawClient(ip, port);
        ConsulClient consul = new ConsulClient(client);
        consul.agentServiceDeregister(serviceId);
	}
	public static void main(String[] args) {
//		removeConsulInstance("localhost", 8500,"http-169-254-119-203-8082");
		removeDeadInstance("123.127.120.126",32000);
	}
}
