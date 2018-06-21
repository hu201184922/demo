package com.huatai.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatai.web.utils.ConsulUtil;

@RestController
@RequestMapping("admin/consul")
public class ConsulController {
	public static final String Password="Htconsul*171106";
	@RequestMapping(value = "/unregister/{id}")
	public String unregister(@PathVariable String id,String password){
		if(Password.equals(password)){
			ConsulUtil.removeConsulInstance(id);
			return "success";
		}
		return "failed";
	}
	@RequestMapping(value = "/clean")
	public String clean(String password){
		if(Password.equals(password)){
			ConsulUtil.removeDeadInstance();
			return "success";
		}
		return "failed";
	}
}
