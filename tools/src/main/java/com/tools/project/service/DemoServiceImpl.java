package com.tools.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tools.project.mapper.DemoMapper;
import com.tools.project.mapper.OracleMapper;
import com.tools.project.pojo.Pager;

@Service("demoServiceImpl")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;
	@Autowired
	private OracleMapper oracleMapper;
	
	@Override
	public Pager<Object> findAll(String type,Pager<Object> pager) {
		List<Object> people=new ArrayList<>();
		/*for(int i=0;i<100;i++){
			Person p=new Person();
			p.setName("张三"+i);
			p.setSex("男");
			p.setAge("11");
			people.add(p);
		}*/
		switch (type) {
		case "1":
			//使用分页插件,核心代码就这一行
	        PageHelper.startPage(pager.getCurrentPage(), pager.getCurrentPage()+pager.getPageSize());
			people = demoMapper.findAll();
			pager.setPageItems(people);
			break;
		case "2":
			people = oracleMapper.findAll();
			pager.setPageItems(people);
			break;
		}
		return pager;
	}

}
