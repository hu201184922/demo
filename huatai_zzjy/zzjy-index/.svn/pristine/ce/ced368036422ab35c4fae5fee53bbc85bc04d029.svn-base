package com.fairyland.jdp.springboot.bus.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.springboot.bus.event.RefreshCacheEvent;

@Component
public class AckRefreshCacheEvent {
	
	@EventListener(classes=RefreshCacheEvent.class)
	public void handleRefreshCacheEvent(RefreshCacheEvent refreshCacheEvent){
		System.out.println("Received RefreshCacheEvent!!!");
		System.out.println(refreshCacheEvent.getSource());
	}
}
