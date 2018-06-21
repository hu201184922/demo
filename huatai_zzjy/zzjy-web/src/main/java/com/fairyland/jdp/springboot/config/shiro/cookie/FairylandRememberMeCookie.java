/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-1 下午01:49:49 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-1 下午01:49:49       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.cookie;

import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-1 下午01:49:49 
 * @version V1.0.0 
 */
@Component
public class FairylandRememberMeCookie extends SimpleCookie{
	@Value("${spring.fairyland.shiro.fairylandRememberMeCookieName}")
	public void setName(String name) {
        super.setName(name);
    }
	
	@Value("${spring.fairyland.shiro.rememberMeCookiehttpOnly}")
	public void setHttpOnly(boolean httpOnly) {
        super.setHttpOnly(httpOnly);
    }
	
	@Value("${spring.fairyland.shiro.rememberMeCookiemaxAge}")
	public void setMaxAge(int maxAge) {
        super.setMaxAge(maxAge);
    }
}
