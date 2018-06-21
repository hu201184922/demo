package com.fairyland.jdp.springboot.config.view;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class FairylandViewControllerConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("redirect:/admin/desktop");
	    registry.addViewController("/biz").setViewName("redirect:/biz/desktop");
	    registry.addViewController("/biz/").setViewName("redirect:/biz/desktop");
	    registry.addViewController("/admin").setViewName("redirect:/admin/desktop");
	    registry.addViewController("/admin/").setViewName("redirect:/admin/desktop");
	    registry.addViewController("/admin/unauthorized").setViewName("jdp-framework/error/unauthorized");
	    registry.addViewController("/admin/permloading").setViewName("jdp-framework/error/urlpermloading");
	    registry.addViewController("/admin/desktop").setViewName("jdp-framework/main/desktop");
	}
}
