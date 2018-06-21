package com.huatai.web.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.cache.redis.RedisCacheKey;
import com.fairyland.jdp.framework.cache.redis.RedisCacheable;
import com.fairyland.jdp.framework.cache.redis.RedisRemoveCache;
import com.huatai.web.demo.model.DemoModel;

@Service
public class RedisCacheDemoServiceImpl implements RedisCacheDemoService{

	@Override
	@RedisCacheable(key="RedisCacheDemo-findAll-{0}-{1}")
	public List<DemoModel> findAll(@RedisCacheKey String code,@RedisCacheKey String name) {
		List<DemoModel> list = new ArrayList<DemoModel>();
		list.add(new DemoModel(1L, "name1", "code1"));
		list.add(new DemoModel(2L, "name2", "code2"));
		list.add(new DemoModel(0L, name, code));
		return list;
	}
	
	@Override
	@RedisCacheable(key="RedisCacheDemo-findById-{0}")
	public DemoModel findById(@RedisCacheKey Long id) {
		return new DemoModel(id, "name"+id, "code"+id);
	}

	@Override
	@RedisRemoveCache(key="RedisCacheDemo-findAll-{0}-{1}")
	public void removeFindAll(@RedisCacheKey String code,@RedisCacheKey String name) {
		
	}

	@Override
	@RedisCacheable(keyGenerator="keyGenerator")
	public List<DemoModel> findAll(@RedisCacheKey Map<String, Object> params,@RedisCacheKey Integer i) {
		List<DemoModel> list = new ArrayList<DemoModel>();
		list.add(new DemoModel(1L, "name1", "code1"));
		list.add(new DemoModel(2L, "name2", "code2"));
		list.add(new DemoModel(0L, (String)params.get("name"), (String)params.get("code")));
		return list;
	}

	public String keyGenerator(Map<String,Object> params,Integer i){
		String code = (String)params.get("code");
		String name = (String)params.get("name");
		return code+"-"+name+"-"+i;
	}
}
