package com.huatai.web.service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.BillFiltDim;

/**
 * @author 胡智辉
 * @DateTime 2017年7月27日
 */
public interface BillFiltDimService<T extends BillFiltDim> {

	Pager<T> findByPager(Pager<T> pager, T record);

	int insertSelective(T record);

	int updateByPrimaryKeySelective(T record);
	
	void deleteByPrimaryKey(Integer bfdId);

}
