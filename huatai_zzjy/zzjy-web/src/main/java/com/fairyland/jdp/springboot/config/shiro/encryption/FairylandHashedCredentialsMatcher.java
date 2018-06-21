/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-3 上午12:34:37 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-3 上午12:34:37       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.encryption;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-3 上午12:34:37 
 * @version V1.0.0 
 */
@Component("hashCredentialsMatcher")
public class FairylandHashedCredentialsMatcher extends HashedCredentialsMatcher{
	@Value("${spring.fairyland.shiro.hashAlgorithmName}")
	public void setHashAlgorithmName(String hashAlgorithmName) {
        super.setHashAlgorithmName(hashAlgorithmName);
    }
	
	@Value("${spring.fairyland.shiro.hashIterations}")
	public void setHashIterations(int hashIterations) {
        super.setHashIterations(hashIterations);
    }
}
