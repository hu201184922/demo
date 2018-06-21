package com.fairyland.jdp.springboot.listener.http;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//HttpSessionBindingListener
public class OnlineUserListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	private final AtomicInteger activeSessionCounter;

	private final ConcurrentHashMap<String, HttpSession>activeSessions;
	
	public OnlineUserListener(){
		activeSessionCounter=new AtomicInteger();
		activeSessions=new ConcurrentHashMap();
	}
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		activeSessionCounter.incrementAndGet();
		
		HttpSession session=se.getSession();
		Object srcObj=se.getSource();
		activeSessions.put(session.getId(), session);
		
		Enumeration e=session.getAttributeNames();
		while(e.hasMoreElements()){
			Object attr=e.nextElement();
			System.out.println(attr);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		activeSessionCounter.decrementAndGet();
		
		HttpSession session=se.getSession();
		activeSessions.remove(session.getId());
	}

}
