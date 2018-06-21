package com.ehuatai.commonTest.entity;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;

public class UrlConnection {
	/**
	 * 
	 * 发送请求并接受返回数据
	 */
	private static Logger logger = Logger.getLogger(UrlConnection.class);

	public static String getData(String requestUrl, String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(requestUrl);
		// 设置header信息
		String  eqp = jsonObject.getString("eqp");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("system", "jyfx");
		httpPost.setHeader("eqp", eqp);
		if (null != jsonObject.getString("ldToken") && "" != jsonObject.getString("ldToken")) {
			httpPost.setHeader("ldToken", jsonObject.getString("ldToken"));
		}
		// 设置参数到请求对象中
		if (null != json) {
			StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		}
		// 执行请求操作，并拿到结果
		HttpResponse result = null;
		try {
			result = client.execute(httpPost);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 请求发送成功，并得到响应
		String str = "";
		if (result.getStatusLine().getStatusCode() == 200) {
			try {
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());

			} catch (Exception e) {

				logger.error("post请求提交失败");
			}
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;

	}
}
