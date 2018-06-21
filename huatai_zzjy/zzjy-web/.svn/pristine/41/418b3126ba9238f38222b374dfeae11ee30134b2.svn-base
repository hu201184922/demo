package com.huatai.web.demo.service;

import java.util.List;
import java.util.Map;

import com.huatai.web.demo.model.DemoModel;

public interface RedisCacheDemoService {
	
	List<DemoModel> findAll(String code,String name);
	
	void removeFindAll(String code,String name);
	
	DemoModel findById(Long id);
	
	List<DemoModel> findAll(Map<String,Object> params, Integer i);
}
