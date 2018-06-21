/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-3 上午10:18:54 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-3 上午10:18:54       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.ecache;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-3 上午10:18:54 
 * @version V1.0.0 
 */
@Component
public class FairylandEhCacheManager extends EhCacheManager{

    @Value("${spring.fairyland.shiro.cacheManagerConfigFile}")
	public void setCacheManagerConfigFile(String classpathLocation) {
        super.setCacheManagerConfigFile(classpathLocation);
    }
    
}
