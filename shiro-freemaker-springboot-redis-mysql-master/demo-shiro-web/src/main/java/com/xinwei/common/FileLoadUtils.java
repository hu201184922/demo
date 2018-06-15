package com.xinwei.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FileLoadUtils {
	public String getString(String fileName) throws IOException {
		File file = new File(this.getClass().getResource("/").getPath()+fileName);
		BufferedReader bfRead = new BufferedReader(new FileReader(file));
		JSONObject data = null;
		try {
	        System.out.println(bfRead);
	        String str="";
			StringBuffer fileData=new StringBuffer(str);
			while ((str = bfRead.readLine())!= null) // 判断最后一行不存在，为空结束循环
			{
				fileData.append(str.trim().replace(" ", ""));//原样输出读到的内容
			};
			bfRead.close();
			System.out.println(fileData);
			data = JSON.parseObject(fileData.toString());
		} catch (Exception e) {
			bfRead.close();
		}
		/*	JSONObject d = data.getJSONObject("data");
			JSONArray roles = d.getJSONArray("roleIDResult");
			for (Object object : roles) {
				System.out.println(object.toString());
			}*/
		return data.toJSONString();
	}
}
