package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;

/**
 * @author 胡智辉
 * 2017年7月27日
 */
public interface InterFieldService {

	Pager<InterField> findByPager(Pager<InterField> pager, Inter record);

	int insert(InterField record);

	int updateByPrimaryKeySelective(InterField record);

	int deleteByPrimaryKey(Integer id);

	List<Inter> findInterFieldByInterId(String interId);

}
