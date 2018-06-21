package com.fairyland.jdp.framework.cache.redis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.framework.util.RedisUtil;

@Controller
@RequestMapping("admin/redis")
public class RedisController {
	
	@RequestMapping("index")
	public String index(String pattern,Model model){
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isEmpty(pattern)){
			TreeSet<String> set = RedisUtil.keys(pattern);
			list.addAll(set);
			model.addAttribute("list", list);
		}
		model.addAttribute("pattern", pattern);
		return "jdp-framework/redis/redis_index";
	}
	@RequestMapping("delete")
	@ResponseBody
	public String delete(String key){
		RedisUtil.del(key);
		return "success";
	}
	
	@RequestMapping("getVal")
	@ResponseBody
	public String getVal(String key) throws ClassNotFoundException, IOException{
		Object obj = RedisUtil.get(key);
		String json = JSON.toJSONString(obj,true);
		return json;
	}
	@RequestMapping("multiDelete")
	@ResponseBody
	public String multiDelete(String[] keys){
		for (String key : keys) {
			RedisUtil.del(key);
		}
		return "success";
	}
	@RequestMapping("setRedis")
	@ResponseBody
	public String setRedis(String key,String value){
		RedisUtil.set(key, 0, value);
		return "success";
	}
}
