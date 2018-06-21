package com.huatai.web.controller;

import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fairyland.jdp.framework.util.PropsUtil;
import com.huatai.web.utils.CephUtil;

@Controller
@RequestMapping("admin/ceph")
public class CephController {
	
	@RequestMapping("/{bucketName}/{key:.+}")
	public void ceph(HttpServletRequest request,HttpServletResponse response,@PathVariable("bucketName") String bucketName,@PathVariable("key") String key) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "max-age=604800");
		String cephServer = PropsUtil.get("ceph.endpoint")+"/"+bucketName+"/";
		System.out.println(key);
		String newKey = URLEncoder.encode(key,"UTF-8");
		System.out.println(newKey);
		String url = cephServer+newKey;
		Object[] obj = CephUtil.getInputFromUrl(url);
		InputStream in = (InputStream)obj[0];
		long length = (Long)obj[1];
		CephUtil.transfer(request,response,"application/octet-stream",key,in,length);
	}
}
