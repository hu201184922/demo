package com.fairyland.jdp.springboot.listener.http;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.huatai.web.thrift.ThriftServerProxy;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent>{

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ThriftServerProxy thriftServer = new ThriftServerProxy();
		thriftServer.start();
		CleanConsulThread cleanConsulThread = new CleanConsulThread();
		cleanConsulThread.start();
	}
	
	

}
