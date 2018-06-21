package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.Target;

public interface FixedListService {

	List<Target> selectTargetByTree(Object object);

	Pager<FixedList> findByPager(Pager<FixedList> pager, FixedList record);

	int add(FixedList record);

	int update(FixedList record);

	List<FixedList> findAll();

	List<FixedList> findDeptCodeByGroup();

	FixedList findFixedByFlCode(String flCode);

}
