/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-1 下午01:45:56 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-1 下午01:45:56       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.manager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-1 下午01:45:56 
 * @version V1.0.0 
 */
@Component
public class FairylandCookieRememberMeManager extends CookieRememberMeManager{
	@Value("${spring.fairyland.shiro.base64Encoded}")
	private String shiroBase64Encoded;
	
	@Resource(name="fairylandRememberMeCookie")
	public void setCookie(Cookie cookie) {
        super.setCookie(cookie);
    }
	
	@PostConstruct
	void innitCipherKey(){
		 super.setCipherKey(Base64.decode(shiroBase64Encoded));
	}
	
}
