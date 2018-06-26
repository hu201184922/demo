package com.tools.framework.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent>{

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		/*ThriftServerProxy thriftServer = new ThriftServerProxy();
		thriftServer.start();*/
		CleanConsulThread cleanConsulThread = new CleanConsulThread();
		cleanConsulThread.start();
	}
	
	

}
