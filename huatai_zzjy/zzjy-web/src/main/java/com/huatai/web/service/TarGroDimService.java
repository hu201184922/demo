package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;

import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarGroDimExample;

public interface TarGroDimService {
		
	int countByExample(TarGroDimExample example);

	int deleteByExample(TarGroDimExample example);

	void deleteByPrimaryKey(Integer tgdId);

	void insert(TarGroDim record);

	int insertSelective(TarGroDim record);

	List<TarGroDim> selectByExample(TarGroDimExample example);

	TarGroDim selectByPrimaryKey(Integer tgdId);
	int updateByExampleSelective( TarGroDim record, TarGroDimExample example);

	int updateByExample( TarGroDim record,  TarGroDimExample example);

	int updateByPrimaryKeySelective(TarGroDim record);

	void updateByPrimaryKey(TarGroDim record);
	
	Pager<TarGroDim> findByPager(Pager<TarGroDim> pager, TarGroDim record);

	List<TarGroDim> findAllTarGroDim();

	List<TarGroDim> findTarGroDimByTargetCode(String targetCode);

}
