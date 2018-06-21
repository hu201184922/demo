/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-3 下午03:11:27 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-3 下午03:11:27       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.factorybean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.stereotype.Component;

import com.fairyland.jdp.springboot.config.shiro.manager.FairylandWebSecurityManager;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-3 下午03:11:27 
 * @version V1.0.0 
 */
@Component
public class FairylandMethodInvokingFactoryBean extends MethodInvokingFactoryBean{
	@Autowired
	private FairylandWebSecurityManager fairylandWebSecurityManager;
	
	@Value("${spring.fairyland.shiro.setStaticMethod}")
	public void setStaticMethod(String staticMethod) {
		super.setStaticMethod(staticMethod);
	}
	
	@PostConstruct
	void innitMethodBeanFasctory(){
		Object[] arguments = new Object[]{fairylandWebSecurityManager};
		super.setArguments(arguments);
	}
}
