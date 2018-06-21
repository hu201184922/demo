/** 
* CopyRright © 2013 上海复深蓝信息技术有限公司
* Homepage：http://www.fulan.com.cn/                         
* Project:Fairyland-JDP                                      
* Module ID: framework   
* Comments:                                         
* JDK version used: JDK1.6                            
 */ 
package com.fairyland.jdp.framework.security.exception;

import org.apache.shiro.authc.AccountException;

/**   
 * @Description: TODO
 * @author codyzeng 
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 上午10:51:18
 * @version V1.0   
 */
public class FirstLoginChangePswException extends AccountException {

    /**
     * 
     */
    private static final long serialVersionUID = 5122841780371020101L;

    public FirstLoginChangePswException() {
        super();
    }

    public FirstLoginChangePswException(String message) {
        super(message);
    }

    public FirstLoginChangePswException(Throwable cause) {
        super(cause);
    }

    public FirstLoginChangePswException(String message, Throwable cause) {
        super(message, cause);
    }
}
