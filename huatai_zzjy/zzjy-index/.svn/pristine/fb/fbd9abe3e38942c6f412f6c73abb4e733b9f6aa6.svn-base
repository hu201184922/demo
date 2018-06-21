package com.ehuatai.consul;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent>{

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		CleanConsulThread cleanConsulThread = new CleanConsulThread();
		cleanConsulThread.start();
	}
	
	

}
