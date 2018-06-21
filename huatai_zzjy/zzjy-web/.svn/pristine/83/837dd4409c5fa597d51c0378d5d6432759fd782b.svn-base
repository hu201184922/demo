/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.framework.util;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月9日 上午9:20:21
 * @version V1.0
 */
public abstract class ClassPathUtil {

    static public final String SEP = ".";

    static public String getSimpleClassName(String classPath) {
	basicPathChecks(classPath);
	int sepIndex = classPath.lastIndexOf(SEP);
	if (sepIndex != -1 && sepIndex != classPath.length() - 1) {
	    return classPath.substring(sepIndex + 1);
	}
	return classPath;
    }
    
    static public String getBasePkg(String classPath) {
	basicPathChecks(classPath);
	int sepIndex = classPath.lastIndexOf(SEP);
	if (sepIndex != -1 && sepIndex != classPath.length() - 1) {
	    return classPath.substring(0,sepIndex);
	}
	return classPath;
    }

    private static void basicPathChecks(String classPath) {
	if (classPath == null) {
	    throw new IllegalArgumentException("ClassPath is null");
	}
	if (!classPath.equals(classPath.trim())) {
	    throw new IllegalArgumentException(
		    "ClassPath has not been trimmed: '" + classPath + "'");
	}
    }

}
