/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.util;

/**   
 * @Description: TODO
 * @author codyzeng 
 * @email zengyuekang@fulan.com.cn
 * @date 2014年1月23日 下午3:26:06
 * @version V1.0   
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringUtil.applicationContext = arg0;
	}

	public static Object getBean(String name) {
		if (applicationContext == null)
			return null;
		return applicationContext.getBean(name);
	}

	public static boolean containsBean(String name) {
		if (applicationContext == null)
			return false;
		return applicationContext.containsBean(name);
	}

	/**
	 * 具有唯一性
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		if (applicationContext == null)
			return null;
		return applicationContext.getBean(clazz);
	}

}