package com.fairyland.jdp.framework.webconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 用于拦截RequestMappingHandlerMapping注册后的处理
 * 
 * @author XiongMiao
 *
 */
public class RequestMappingHandlerMappingPostProcessor implements BeanPostProcessor{
	
	/**
	 * 是否使用后缀模式匹配 ，默认为false，如果为true，可识别"/simple.*"，
	 */
	private boolean useSuffixPatternMatch = false;

	/**
	 * 末尾斜线匹配 ,默认为true, 如果为true，可识别"/simple/"。
	 */
	private boolean useTrailingSlashMatch = true;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof RequestMappingHandlerMapping) {
			RequestMappingHandlerMapping handlerMapping = 
					(RequestMappingHandlerMapping) bean;
			handlerMapping.setUseSuffixPatternMatch(useSuffixPatternMatch);
			handlerMapping.setUseTrailingSlashMatch(useTrailingSlashMatch);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	public boolean isUseSuffixPatternMatch() {
		return useSuffixPatternMatch;
	}

	public void setUseSuffixPatternMatch(boolean useSuffixPatternMatch) {
		this.useSuffixPatternMatch = useSuffixPatternMatch;
	}

	public boolean isUseTrailingSlashMatch() {
		return useTrailingSlashMatch;
	}

	public void setUseTrailingSlashMatch(boolean useTrailingSlashMatch) {
		this.useTrailingSlashMatch = useTrailingSlashMatch;
	}

}
