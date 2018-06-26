package com.tools.spring.filter;

public class FilterManager {
	FilterChain filterChain;
	
	FilterManager(Target targer){
		FilterChain filterChain=new FilterChain();
		filterChain.setTarget(targer);
	}
	
	public void setFilter(Filter filter){
		filterChain.addFilter(filter);
	}
	
	public void filterRequest(String request){
	      filterChain.execute(request);
	}
}
