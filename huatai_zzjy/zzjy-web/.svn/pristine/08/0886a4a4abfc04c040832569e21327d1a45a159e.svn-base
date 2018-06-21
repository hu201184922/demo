/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-3 上午10:05:02 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-3 上午10:05:02       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.proxy;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-3 上午10:05:02 
 * @version V1.0.0 
 */
@SuppressWarnings("serial")
@Component
public class FairylandAdvisorAutoProxyCreator extends DefaultAdvisorAutoProxyCreator{
    @Value("${spring.fairyland.shiro.proxyTargetClass}")
	public void setProxyTargetClass(boolean proxyTargetClass) {
        super.setProxyTargetClass(proxyTargetClass);
    }
}
