/** 
* CopyRright © 2013 上海复深蓝信息技术有限公司
* Homepage：http://www.fulan.com.cn/                         
* Project:Fairyland-JDP                                      
* Module ID: framework   
* Comments:                                         
* JDK version used: JDK1.6                            
 */ 
package com.fairyland.jdp.framework.security.service;

import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**   
 * @Description: TODO
 * @author codyzeng 
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月1日 下午3:53:01
 * @version V1.0   
 */
@Service
public class HashServiceImpl extends DefaultHashService implements HashService {

	@Value("${spring.fairyland.shiro.hashAlgorithmName}")
	public void setHashAlgorithmName(String name) {
		super.setHashAlgorithmName(name);
	}

	@Value("${spring.fairyland.shiro.hashIterations}")
	public void setHashIterations(int count) {
		super.setHashIterations(count);
	}

//	@Value("${jdp.shiro.generate-public-salt}")
//	public void setGeneratePublicSalt(boolean generatePublicSalt) {
//		super.setGeneratePublicSalt(generatePublicSalt);
//	}
    /* (non-Javadoc)
     * @see com.fairyland.jdp.framework.security.service.HashService#computeHash(java.lang.String)
     */
    @Override
    public Hash computeHash(String plainPassword,String saltStr){
	String algorithmName=getHashAlgorithmName();  
	int iterations=getHashIterations();
	ByteSource source=ByteSource.Util.bytes(plainPassword);
	ByteSource salt=ByteSource.Util.bytes(saltStr);;//使用默认盐，16位随机数
	HashRequest hashRequest=new SimpleHashRequest(algorithmName,source,salt,iterations);
	return computeHash(hashRequest);
    }


}
