package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.GroDim;


public interface GroDimService {

	Pager<GroDim> findGroDimByPage(Pager<GroDim> pager, GroDim groDim);

	int addGroDim(GroDim groDim);

	int updateGroDim(GroDim groDim);

	void deleteByPrimaryKey(Integer gdId);

	List<GroDim> findGroDimAll();
	
	List<GroDim> findGroDimByTargetCode(String targetCode);

	GroDim findById(Integer gdId);

	int update(GroDim record);


}
