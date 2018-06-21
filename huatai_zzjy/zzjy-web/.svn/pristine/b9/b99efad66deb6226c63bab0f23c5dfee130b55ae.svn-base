package com.fairyland.jdp.framework.security.authc.util;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken {  
    private String username;  
 
    private String ticket;   
    public Object getPrincipal() {  return username;}  
    public Object getCredentials() {  return ticket;}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	 
    
    
    
}   
