package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Templet;


/**
 * @author 胡智辉
 * 2017年7月19日
 */
public interface TempletService {

	int addTemplet(Templet templet);

	Templet findById(Integer id);

	int updateTemplet(Templet templet);

	int delectTemplet(Integer id);
	
	List<Templet> findTempletByAll();

	Pager<Templet> findTempletByPage(Pager<Templet> pager, Templet templet);
	
}
