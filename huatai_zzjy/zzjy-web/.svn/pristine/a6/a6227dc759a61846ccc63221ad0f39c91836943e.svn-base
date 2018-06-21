/**
 * Copyright : http://www.fulan.com.cn/, 2016-2017
 * Project : JDP4.0
 * vision 1.0
 * Last Changed by panzuchao at * @date 2016-3-1 下午01:41:34 
 * JDK version used: JDK1.7
 * Change Log
 * Author      Change Date    Comments
 *---------------------------------------------
 * panzuchao  email:panzuchao@fulan.com.cn    * @date 2016-3-1 下午01:41:34       Initailized
 */
package com.fairyland.jdp.springboot.config.shiro.scheduler;

import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 用一句话描述该类做了什么吧
 * @author panzuchao
 * @email panzuchao@fulan.com.cn
 * @date * @date 2016-3-1 下午01:41:34 
 * @version V1.0.0 
 */
@Component
public class FairylandQuartzSessionValidationScheduler extends QuartzSessionValidationScheduler{
	
	@Value("${spring.fairyland.shiro.sessionValidationInterval}")
	public void setSessionValidationInterval(long sessionValidationInterval) {
        super.setSessionValidationInterval(sessionValidationInterval);
    }
	
	@Autowired
	public void setSessionManager(ValidatingSessionManager sessionManager) {
        super.setSessionManager(sessionManager);
    }
	
}
