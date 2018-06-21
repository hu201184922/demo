package com.ehuatai.ceph.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fairyland.jdp.framework.util.PropsUtil;

@Controller
@RequestMapping("ceph")
public class CephController {
	public static String defaultBucketName = "ceph";
	@RequestMapping("/{bucketName}/{key:.+}")
	public void ceph(HttpServletRequest request,HttpServletResponse response,@PathVariable("bucketName") String bucketName,@PathVariable("key") String key) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "max-age=604800");
		String cephServer = PropsUtil.get("ceph.endpoint")+"/"+bucketName+"/";
		System.out.println(key);
		String newKey = URLEncoder.encode(key,"UTF-8");
		System.out.println(newKey);
		String url = cephServer+newKey;
		Object[] obj = getInputFromUrl(url);
		InputStream in = (InputStream)obj[0];
		long length = (Long)obj[1];
		transfer(request,response,"application/octet-stream",key,in,length);
	}
	public static InputStream getInputFromCeph(String bucketName,String key) throws IOException{
		if(bucketName==null)
			bucketName = defaultBucketName;
		String cephServer = PropsUtil.get("ceph.endpoint")+"/"+bucketName+"/";
		String url = cephServer+key;
		Object[] obj = getInputFromUrl(url);
		InputStream in = (InputStream)obj[0];
		return in;
	}
	public static Object[] getInputFromUrl(String urlStr) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        long length=conn.getContentLengthLong(); 
        //得到输入流  
        InputStream inputStream = conn.getInputStream();
        BufferedInputStream in = new BufferedInputStream(inputStream);
        Object[] obj = new Object[2];
        obj[0] = inputStream;
        obj[1] = length;
        return obj;
    }  
  
    public static void transfer(HttpServletRequest request,  
            HttpServletResponse response, String contentType,  
            String realName,InputStream in,long length) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedOutputStream bos = null;  
  
  
        response.setContentType(contentType);  
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(length));  
  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        in.close();
        bos.close();
    }  
}
