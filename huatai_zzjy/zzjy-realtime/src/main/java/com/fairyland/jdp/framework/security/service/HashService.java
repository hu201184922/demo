/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.security.service;

import org.apache.shiro.crypto.hash.Hash;

/**   
 * @Description: TODO
 * @author codyzeng 
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午4:28:33
 * @version V1.0   
 */
public interface HashService {

    public abstract Hash computeHash(String plainPassword,String salt);
    

}