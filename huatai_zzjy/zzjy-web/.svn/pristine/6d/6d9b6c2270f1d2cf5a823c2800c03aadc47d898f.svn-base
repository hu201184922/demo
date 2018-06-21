package com.huatai.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.service.ServiceException;


/**
 * Http请求工具类
 * @author LC
 *
 */
public class HttpUtils {
	
	public static String GET="GET";
	public static String POST="POST";
	
	private static Logger log = Logger.getLogger(HttpUtils.class);
	
	private HttpUtils(){
		
	}
	private static HttpUtils http = null;
	public static synchronized HttpUtils getInstance(){
		if(http == null) http = new HttpUtils();
		return http;
	}

	public String request(String requestURL,String requestMethod,String params){
		log.debug(requestMethod+"请求："+requestURL);
		try {
			URL url = new URL(requestURL);
			
			//创建连接对象
			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			//设置请求方法
			conn.setRequestMethod(requestMethod);
			//设置超时时间
			conn.setReadTimeout(5000);
			
			//如果是GET请求就直接连接
			if("GET".equalsIgnoreCase(requestMethod)){
				conn.connect();
			}
			
			if(null != params){
				//设置请求参数
				OutputStream os = conn.getOutputStream();
				os.write(params.getBytes(Charset.forName("UTF-8")));
				os.flush();
				os.close();
			}
			
			//获取流对象
			InputStream is =  conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			
			StringBuffer buffer = new StringBuffer();
			
			String str = null;
			//获取结果
			while ((str = br.readLine()) != null) {
				buffer.append(str);
			}
			
			br.close();
			isr.close();
			is.close();
			
			String result = buffer.toString();
			
			log.debug(result);
			return result;	
			
		} catch (MalformedURLException e) {
			log.error("请求异常MalformedURLException："+e.getMessage());
			log.error(e.getMessage());
			
		} catch (IOException e) {
			log.error("请求异常IOException："+e.getMessage());
			log.error(e.getMessage());
		}
		log.error("API请求数据为空");
		return null;
	}
	
	/**
	 * 结果是json
	 * @param requestURL
	 * @param requestMethod
	 * @param params
	 * @return	将字符串转化为JSON
	 */
	public JSONObject requestJSON(String requestURL,String requestMethod,String params){
		String result =  this.request(requestURL, requestMethod, params);
		if(result == null) return null;
		return JSONObject.parseObject(result);
	}
	
//	/**
//	 * 结果是XML
//	 * @param requestURL
//	 * @param requestMethod
//	 * @param params
//	 * @return	将XML字符串转化为Map
//	 */
//	public Map<String, String> requestXML(String requestURL,String requestMethod,String params){
//		String result =  this.request(requestURL, requestMethod, params);
//		if(result == null) return null;
//		return XMLUtils.parse(result);
//	}
	
	/**
	 * 上传文件
	 * @param requestURL	//请求的路径
	 * @param requestMethod	//请求的方法
	 * @param file			//文件对象
	 * @return	{type:[image,voice,video,file],media_id:'',created_at:''}
	 */
	public JSONObject uploadFile(String requestURL,String requestMethod,File file){
		JSONObject json = null;
		// 定义数据分割符
		String boundary = "----------sunlight";
		
		try {
			
			URL httpUrl = new URL(requestURL);
			
			HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestMethod(requestMethod);
			
			//设置Content-Type
			conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
			
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream os = conn.getOutputStream();
			
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String contentType =  fileNameMap.getContentTypeFor(file.getName());
			
			os.write(("--" + boundary + "\r\n").getBytes());
			os.write(("Content-Disposition:form-data; name=\"media\"; filename=\""+file.getName()+"\"\r\n").getBytes());
			os.write(("Content-Type:"+contentType+"\r\n\r\n").getBytes());
			
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			
			int bytes = 0;  
            byte[] bufferOut = new byte[1024 * 8];  
            while ((bytes = in.read(bufferOut)) != -1) {  
                os.write(bufferOut, 0, bytes);  
            }  
            os.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
            in.close();  
            os.write( ("\r\n--" + boundary + "--\r\n").getBytes());  
            os.flush();  
            os.close();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line = null;  
            StringBuffer buffer = new StringBuffer();  
            while ((line = reader.readLine()) != null) {  
                buffer.append(line);  
            }  
            // 使用json解析  
            json = JSON.parseObject(buffer.toString());
			
		} catch (MalformedURLException e) {
			log.error("上传媒体文件失败MalformedURLException："+e.getMessage());
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error("上传媒体文件失败IOException："+e.getMessage());
			log.error(e.getMessage());
		}
		
		return json;
	}
	
	public static boolean downloadFile(String url,String filepath){
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
		    conn.setDoInput(true);
		    conn.setRequestMethod("GET");
		    //获取文件后缀名
//		    String fileExt = getFileEndWith(conn.getHeaderField("Content-Type"));
		    
//		    String[] paths = getAttachPaths("activity_pic_path", fileExt);
//		    String rootPath = paths[0] + File.separator + paths[1];
		    //获取文件类型
//		    String attachmentType = conn.getHeaderField("Content-Type");
		    //文件大小
//		    Long attachmentLength = Long.parseLong(String.valueOf(conn.getContentLength()));
		    //设置文件名
//		    String attachmentName = StringUtil.getUUID()+"."+fileExt;
//		    File directory = new File(getRootPath()+rootPath.replaceAll("\\\\", "/"));
		    File file = new File(filepath);
		    File directory = file.getParentFile();
			directory.setWritable(true, false);
			if(!directory.exists()) {
				directory.mkdirs();
			}
		    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		    FileOutputStream fos = new FileOutputStream(file);
		    byte[] buf = new byte[8096];
		    int size = 0;
		    while ((size = bis.read(buf)) != -1) {
		        fos.write(buf, 0, size);
		    }
		    fos.close();
		    bis.close();
		    conn.disconnect();
		    
//		    String head_path = paths[0].substring(1).replaceAll("\\\\","/")+"/" + paths[1] + "/" + attachmentName;
		    return true;
		} catch (FileNotFoundException e) {
			throw new ServiceException("下载媒体文件失败");    
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException("下载媒体文件失败!");
		}
		    
	}
//	private static String[] getAttachPaths(String attachCode, String format){
//		String[] paths = new String[2];
//		/*DictItemModel model = null;
//		if("rar".equalsIgnoreCase(format) || "zip".equalsIgnoreCase(format)) {
//			model = DictionaryUtils.codeToModel("file_system_config", Constants.FileSystemConfig.FILE_TEMP_DIRECTORY);
//		}else {
//			model = DictionaryUtils.codeToModel("file_system_config", attachCode);
//		}
//		paths[0] = model.getValue();*/
//		paths[0] = "\\upload\\images\\activityImg";//
//		log.debug("--> "+paths[0]);
//		paths[1] = DateUtil.toShortDay(DateUtil.getMondayOfWeek(new Date()));
//		return paths;
//	}
	public static String getFileEndWith(String contentType)
	  {
	    String fileEndWitsh = "";
	    if ("image/jpeg".equals(contentType)) {
	      fileEndWitsh = "jpg";
	    } else if ("audio/mpeg".equals(contentType)) {
	      fileEndWitsh = "mp3";
	    } else if ("audio/amr".equals(contentType)) {
	      fileEndWitsh = "amr";
	    } else if ("video/mp4".equals(contentType)) {
	      fileEndWitsh = "mp4";
	    } else if ("video/mpeg4".equals(contentType)) {
	      fileEndWitsh = "mp4";
	    }
	    return fileEndWitsh;
	  }
	
	public static void main(String[] args) {
		String fileUrl = "显卡天梯图.jpg";
		String type = null;   
	   
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		type = fileNameMap.getContentTypeFor(fileUrl);
		log.debug(type); 
		
		   
	   
	}
}
