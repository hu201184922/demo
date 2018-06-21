package com.ehuatai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehuatai.util.CommonUtil;

public class Test {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(CommonUtil.USERLOGIN);
		HttpResponse result = null;
		try {
			result = client.execute(httpGet);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String str = "";
		if (result.getStatusLine().getStatusCode() == 200) {
			try {
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				System.out.println(str);
			} catch (Exception e) {
				System.out.println("======Get请求失败=======");			
			}
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String showURL() throws IOException {
		File f = new File(this.getClass().getResource("/").getPath()+"role.json");
		BufferedReader bdr = new BufferedReader(new FileReader(f));
        System.out.println(f);
        String str="";
		StringBuffer file=new StringBuffer(str);
		while ((str = bdr.readLine())!= null) // 判断最后一行不存在，为空结束循环
		{
			file.append(str.trim());//原样输出读到的内容
		};
		System.out.println(file);
		JSONObject data = JSON.parseObject(file.toString());
		JSONObject d = data.getJSONObject("data");
		JSONArray roles = d.getJSONArray("roleIDResult");
		for (Object object : roles) {
			System.out.println(object.toString());
		}
		return data.toJSONString();
	}


}
