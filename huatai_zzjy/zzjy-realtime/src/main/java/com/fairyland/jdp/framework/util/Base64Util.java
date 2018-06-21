package com.fairyland.jdp.framework.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	/** 
     * @param bytes 
     * @return 
     */  
    public static byte[] decode(final byte[] bytes) {  
        return Base64.decodeBase64(bytes);  
    }
  
    /** 
     * 二进制数据编码为BASE64字符串 
     * 
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode(final byte[] bytes) {  
        try {
			return new String(Base64.encodeBase64(bytes),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    /** 
     * @param bytes 
     * @return 
     */  
    public static String decode(final String s) {
        return new String(decode(s.getBytes(Charset.forName("utf-8"))));
    }
    
    public static byte[] decodeToByte(final String s) {
        return decode(s.getBytes(Charset.forName("utf-8")));
    }
    /** 
     * 二进制数据编码为BASE64字符串 
     * 
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode(final String s) {  
        return new String(encode(s.getBytes(Charset.forName("utf-8"))));
    }
}
