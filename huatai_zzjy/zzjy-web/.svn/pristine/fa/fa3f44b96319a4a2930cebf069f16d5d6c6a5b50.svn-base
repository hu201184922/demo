package com.fairyland.jdp.springboot.config.filter;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.tagprocessor.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.framework.util.PropsUtil;

//@Component
public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {
//	@Value("${spring.fairyland.sitemeshfilter.decorator-path}")
//	private String decoratorPath;
//	@Value("${spring.fairyland.sitemeshfilter.excluded-path}")
//	private String excludedPath;
	
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    	String decoratorPath = PropsUtil.get("spring.fairyland.sitemeshfilter.decorator-path");
    	String excludedPath = PropsUtil.get("spring.fairyland.sitemeshfilter.excluded-path");
    	if(decoratorPath!=null && decoratorPath.length()>0){
    		String[] decoratorArr = decoratorPath.split(",");
    		for (String decorator : decoratorArr) {
				String[] keyValue = decorator.split(":");
				builder.addDecoratorPath(keyValue[0], keyValue[1]);
			}
    	}
    	if(excludedPath!=null && excludedPath.length()>0){
    		String[] excludedArr = excludedPath.split(",");
    		for (String excluded : excludedArr) {
				builder.addExcludedPath(excluded);
			}
    	}
    }
}