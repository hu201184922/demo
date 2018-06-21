/**
 * 
 */
package com.fairyland.jdp.springboot.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;

/**
 * HTTP 客户端
 * @author zero
 * @since 1.0
 */
public class HttpClientNetwork {
	public static String  getHttpClientContent( String url,String charset,String dcubeAppKeyValue) throws  Exception {
		HttpClient httpclient = new DefaultHttpClient();
		String content = null;
		try {  
		      HttpGet httpget = new HttpGet(url);  
		      httpget.setHeader("dcubeAppKey", dcubeAppKeyValue);
//		      httpget.setHeader("dcubeAppKey", "Ml9mOGZlNWQ3ZS00MWU0LTQxMzctYmE0Mi02ZTJlZDU5MmNlNjU=");
		      System.out.println("executing request " + httpget.getURI());  
		      HttpResponse response = httpclient.execute(httpget);  
		      HttpEntity  entity = response.getEntity();  
		      if (response.getStatusLine().getStatusCode() == 200){
		    	  content = EntityUtils.toString(entity, charset); 
		      }else{
		    	  throw new Exception("连接失败");
		      }
		      httpget.abort();  
	   } finally {  
	       // When HttpClient instance is no longer needed,  
	       // shut down the connection manager to ensure  
	       // immediate deallocation of all system resources  
	       httpclient.getConnectionManager().shutdown();  
	   }
		return content;  
	}
	
	public static String  postHtpCltData2Cube( String url,String charset,String jsonParam,String dcubeAppKeyValue) throws  Exception {
		HttpClient httpClient = new DefaultHttpClient();  
		String content = null;
       HttpPost httpPost = new HttpPost(url);  
       try {  
//           httpPost.setHeader("dcubeAppKey", "Ml9mOGZlNWQ3ZS00MWU0LTQxMzctYmE0Mi02ZTJlZDU5MmNlNjU=");
//           httpPost.setHeader("dcubeAppKey", dcubeAppKeyValue);
    	   StringEntity entity = new StringEntity(jsonParam.toString(),charset);//解决中文乱码问题    
    	   entity.setContentEncoding(charset);    
    	   entity.setContentType("application/json");    
    	   httpPost.setEntity(entity); 
           System.out.println("execurting request:" + httpPost.getURI());  
           HttpResponse httpResponse = null;  
           httpResponse = httpClient.execute(httpPost);  
           HttpEntity httpEntity = httpResponse.getEntity();  
    	   if (httpEntity != null) {  
               content = EntityUtils.toString(httpEntity, charset);  
               System.out.println("Response content:" + content);  
           }  
       } catch (ClientProtocolException e) {  
           e.printStackTrace();  
       } catch (UnsupportedEncodingException e) {  
           e.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       } finally {  
           //关闭连接，释放资源  
           httpClient.getConnectionManager().shutdown();  
       }  
       return content;  
	}
	/**
	 * http发送
	 * @param url
	 * @param charSet
	 * @param params
	 * @return
	 */
	public static String post(String url,String charSet,Map<String,String> params){
		HttpClient client = null;
		try{
			client = new DefaultHttpClient();
			 List<NameValuePair> pairs = null;
	            if(params != null && !params.isEmpty()){
	                pairs = new ArrayList<NameValuePair>(params.size());
	                for(Map.Entry<String,String> entry : params.entrySet()){
	                    String value = entry.getValue();
	                    if(value != null){
	                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
	                    }
	                }
	            }
	            HttpPost httpPost = new HttpPost(url);
	            if(pairs != null && pairs.size() > 0){
	                httpPost.setEntity(new UrlEncodedFormEntity(pairs,charSet));
	            }
	            HttpResponse response = client.execute(httpPost);
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode != 200) {
	                httpPost.abort();
	                throw new RuntimeException("HttpClient,error status code :" + statusCode);
	            }
	            HttpEntity entity = response.getEntity();
	            String result = null;
	            if (entity != null){
	                result = EntityUtils.toString(entity, "utf-8");
	            }
	            EntityUtils.consume(entity);
	            return result;
		}catch(Exception e){
			return null;
		}finally{
			if(client != null){
				client.getConnectionManager().shutdown();  
			}
		}
	}
	
//	public static void main(String[] args) {
//		/**
//		 * 2.2.1HTTP参数
//		 */
//		String url = "http://10.60.17.179:8088/base-message/bas_center/dataSummary";
//		String charset = "UTF-8";
//		// String charset = "GBK";
//		JSONObject jsonParam = new JSONObject();
////		if (CommonUtil.isEmpty(magicSendEntity.getSsoId())) {
////			map.put("respCode", ResponseStatus.FAILD_RES_CODE);
////			map.put("respDesc", "ssoid为空,请传入ssoid");
////			return map;
////		}
//		jsonParam.put("type", 1);
//		jsonParam.put("sourcetable", "this is the world");
//		String dcubeAppKeyValue = null;
//		try {
//			String result = HttpClientNetwork.postHtpCltData2Cube(url, charset,
//					jsonParam.toString(),dcubeAppKeyValue);
//			System.out.println("result==="+result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
