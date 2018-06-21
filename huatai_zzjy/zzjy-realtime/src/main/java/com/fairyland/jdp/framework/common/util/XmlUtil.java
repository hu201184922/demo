package com.fairyland.jdp.framework.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.framework.security.domain.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * xml序列化与反序列化的工具类
 * 
 * @Description xstream支持自定义Class别名、Field别名，可以使用注解方式，也可以直接通过Xstream设置
 * 
 * @author XiongMiao
 * 
 */
public class XmlUtil {

	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	/**
	 * java 转换成xml
	 * 
	 * @param obj 对象实例        
	 * @return String xml字符串
	 */
	public static String bean2Xml(Object obj) {
		XStream xstream = new XStream();

		// 使注解生效
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}

	/**
	 * 将传入xml文本转换成Java对象
	 * 
	 * @param xmlStr 字符串
	 * @param cls 对应的class类      
	 * @return T 对应的class类的实例对象
	 * 
	 *         调用的方法实例：Person person=XmlUtil.toBean(xmlStr, Person.class);
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xmlStr, Class<T> cls) {
		// 注意：不是new Xstream(); 否则报错：java.lang.NoClassDefFoundError:
		// org/xmlpull/v1/XmlPullParserFactory
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xmlStr);
		return obj;
	}

	/**
	 * 写到xml文件中
	 * @param obj  对象
	 * @param absPath 绝对路径         
	 * @param fileName 文件名      
	 * @return boolean 布尔值
	 */

	public static boolean bean2XMLFile(Object obj, String absPath,
			String fileName) {
		String strXml = bean2Xml(obj);
		String filePath = absPath + fileName;
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("创建{" + filePath + "}文件失败!!!" + e.getMessage());
				return false;
			}
		}
		OutputStream ous = null;
		try {
			ous = new FileOutputStream(file);
			ous.write(strXml.getBytes());
			ous.flush();
		} catch (Exception e1) {
			logger.error("写{" + filePath + "}文件失败!!!" + e1.getMessage());
			return false;
		} finally {
			if (ous != null)
				try {
					ous.close();
				} catch (IOException e) {
					logger.error("写{" + filePath + "}文件关闭输出流异常!!!"
							+ e.getMessage());
				}
		}
		return true;
	}

	/**
	 * 从xml文件读取报
	 * @param fileName 文件名          
	 * @param cls 对象
	 * @return T 泛型对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBeanFromFile(String fileName, Class<T> cls)
			throws Exception {
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(fileName));
		} catch (Exception e) {
			logger.error("读{" + fileName + "}文件失败！", e);
			throw new Exception("读{" + fileName + "}文件失败！", e);
		}

		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		T obj = null;
		try {
			obj = (T) xstream.fromXML(ins);
		} catch (Exception e) {
			logger.error("解析{" + fileName + "}文件失败！", e);
			throw new Exception("解析{" + fileName + "}文件失败！", e);
		}
		if (ins != null)
			ins.close();
		return obj;
	}

}
