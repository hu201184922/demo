package com.fairyland.jdp.springboot.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fairyland.jdp.springboot.bus.event.RefreshCacheEvent;

@RestController
@RequestMapping("bus")
public class BusEventController{
	@Autowired
	private ApplicationContext context;
//	@Value("${eureka.instance.instance-id}")
//	private String originService;
	
	@RequestMapping("refreshCache")
	public String refreshCache(){
//		RemoteApplicationEvent event = new RemoteApplicationEvent();
//		applicationEventPublisher.publishEvent(event);
		final String myUniqueId = context.getId(); 
		RefreshCacheEvent event = new RefreshCacheEvent(this,myUniqueId);
        context.publishEvent(event);

        return "event published";
	}

}
