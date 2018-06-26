package com.tools.project.service;

import com.tools.project.pojo.Pager;

public interface DemoService {

	Pager<Object> findAll(String type, Pager<Object> pager);

}
