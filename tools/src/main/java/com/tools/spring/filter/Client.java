package com.tools.spring.filter;

public class Client {
	FilterManager filterManager;

	public void setFilterManager(FilterManager filterManager){
	      this.filterManager = filterManager;
	}
	
	public void sendRequest(String request){
	      this.filterManager.filterRequest(request);;
	}
}
