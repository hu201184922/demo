package com.fairyland.jdp.springboot.config.shiro.filterbean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

@Component
public class BizShiroFilterRegistrationBean extends FilterRegistrationBean{
//	@Autowired
//	private BizShiroFilterFactoryBean bizShiroFilterFactoryBean;
	@Value("${spring.fairyland.shiro.biz.urlPatterns}")
	private String urlPatterns;
	
	@Value("${spring.fairyland.shiro.biz.targetFilterLifecycle}")
	private String initParameter;
	
	@Value("${spring.fairyland.shiro.biz.registrationEnabled}")
	public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
	
//	@Resource(name="bizShiroFilter")
//	public void setFilter(Filter filter) {
//        super.setFilter(filter);
//    }

	@PostConstruct
	public void innitMethod(){
		 super.addInitParameter("targetFilterLifecycle", initParameter);
		 super.addUrlPatterns(urlPatterns.split(System.lineSeparator()));
		 super.setFilter(new DelegatingFilterProxy("bizShiroFilter"));
	}
}