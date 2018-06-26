package com.tools.framework.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import com.ecwid.consul.v1.ConsulClient;

@Component
public class ShutdownListener implements ApplicationListener<ContextClosedEvent>{
	@Autowired
	private ConsulClient consulClient;
	@Value("${spring.cloud.consul.discovery.instanceId}")
	private String serviceId;
	
	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		String newServiceId = serviceId.replace("://", "-").replace(".", "-").replace(":", "-");
		System.out.println(newServiceId);
		consulClient.agentServiceDeregister(newServiceId);
	}
	
}
