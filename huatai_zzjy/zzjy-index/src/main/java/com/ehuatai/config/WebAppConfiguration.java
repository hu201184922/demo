package com.ehuatai.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ehuatai.interceptors.ApiGlobalInterceptor;
import com.ehuatai.interceptors.LogInterceptor;

@Configuration
public class WebAppConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ApiGlobalInterceptor registryApiGlobalInterceptor() {
		return new ApiGlobalInterceptor();
	}
	
	@Bean
	public LogInterceptor registryLogInterceptor() {
		return new LogInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(registryApiGlobalInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/login","/api/forgot","/api/logout");
		super.addInterceptors(registry);
		
		String loginEexclude[] = new String[] {"/api/tool/warn/update,/api/tool/notice"};
		String pathPatterns[] = new String[] { "/api/**","/app/**"};
		registry.addInterceptor(registryLogInterceptor()).addPathPatterns(pathPatterns)
				.excludePathPatterns(loginEexclude);
		super.addInterceptors(registry);
	}
	
	/*@Bean
	public ArgumentsWrapperFilter registryArgumentsWrapperFilter() {
		return new ArgumentsWrapperFilter();
	}
	@Bean
	public FilterRegistrationBean  filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		registrationBean.setFilter(registryArgumentsWrapperFilter());  
		registrationBean.addUrlPatterns("/api/*"); 
		registrationBean.setOrder(Integer.MAX_VALUE);
		return registrationBean;
	}*/

	
}
