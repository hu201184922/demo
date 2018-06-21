/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-4 下午03:59:34 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-4 下午03:59:34       Initailized
 */
package com.fairyland.jdp.springboot.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-4 下午03:59:34 
 * @version V1.0.0 
 */
@Configuration
public class FairylandMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
//	@Value("${spring.fairyland.customerMvc.pathconfig}")
//	private String pathconfig;
//	
//	@Value("${spring.fairyland.customerMvc.pathViewConfig}")
//	private String pathViewConfig;
	
	@Value("${spring.static-resource.url}")
	private String resourceUrl;
	
	@Value("${spring.static-resource.folder}")
	private String resourceFolder;
	
//	@Override
//	public  void addViewControllers(
//			ViewControllerRegistry paramViewControllerRegistry){
//		
//		String[] pathconfigArr = pathconfig.split(",");
//		String[] pathViewConfigArr = pathViewConfig.split(",");
//		for (int i = 0; i < pathconfigArr.length; i++) {
//			paramViewControllerRegistry.addViewController(pathconfigArr[i])
//			.setViewName(pathViewConfigArr[i]);  
//		}
//	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(resourceUrl!=null && resourceFolder!=null)
			registry.addResourceHandler(resourceUrl).addResourceLocations("file:"+resourceFolder);
		
		super.addResourceHandlers(registry);
	}
}
