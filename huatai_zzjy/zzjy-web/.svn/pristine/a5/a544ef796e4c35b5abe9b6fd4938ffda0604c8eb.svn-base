package com.fairyland.jdp.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InjectionFilter implements Filter{
	private Logger log = LoggerFactory.getLogger(getClass());
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) {
		try {
			Map<String,String[]> m = new HashMap<String,String[]>(request.getParameterMap());
			
			String path = WebUtils.getPathWithinApplication((HttpServletRequest) request);
			if(path.indexOf("/admin/datamodify") == 0){
				
			}else if(path.indexOf("/mobileservice") == 0 || path.indexOf("/mobileFileLoad") == 0 || path.indexOf("/fileLoad") == 0){
				//request = new ParameterRequestWrapper((HttpServletRequest)request, m,true);
			}else{
				request = new ParameterRequestWrapper((HttpServletRequest)request, m);
			}
			chain.doFilter(request, response);
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (ServletException e) {
			log.error(e.getMessage());
		}
	}
	
	public void destroy() {
		log.debug(".............InjectionFilter.destroy.................");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug(".............InjectionFilter.init.................");
	}


}
