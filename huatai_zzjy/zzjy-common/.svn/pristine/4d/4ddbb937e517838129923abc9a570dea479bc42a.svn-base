package com.fairyland.jdp.springboot.bus.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class RefreshCacheEvent extends RemoteApplicationEvent {

	private static final long serialVersionUID = -7978569685356640259L;

	public RefreshCacheEvent() {
		super();
	}

	public RefreshCacheEvent(Object source, String originService, String destinationService) {
		super(source, originService, destinationService);
	}

	public RefreshCacheEvent(Object source, String originService) {
		super(source, originService);
	}
	
	
}
