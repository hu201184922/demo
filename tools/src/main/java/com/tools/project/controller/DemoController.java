package com.tools.project.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tools.project.pojo.Pager;
import com.tools.project.service.DemoService;

@Controller
@RequestMapping("admin")
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/target/index");
		return mv;
	}
	
	@RequestMapping(value = "find", method = RequestMethod.GET)
	@ResponseBody
	public Pager<Object> findMysql(@Param("type")String type,Pager pager) {
		Pager<Object> list =new Pager<Object>();
		list= demoService.findAll(type,pager);
		/*for(int i=0;i<100;i++){
			Person p=new Person();
			p.setName("张三"+i);
			p.setSex("男");
			p.setAge("11");
			list.add(p);
		}*/
		return list;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) throws ParseException {
	}
	
}
