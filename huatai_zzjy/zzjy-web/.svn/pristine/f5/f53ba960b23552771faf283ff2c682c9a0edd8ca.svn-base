package com.huatai.web.service;

import java.util.List;

import com.huatai.web.model.TarQueryDim;

/**
 * @功能 
 * @作者    胡智辉
 * @时间 2017年8月3日
 * @版本 v1.0.0
 */
public interface TarQueryDimService {
	int insertSelective(TarQueryDim record);
	
	int updateByPrimaryKeySelective(TarQueryDim record);
	
	TarQueryDim selectByPrimaryKey(Integer tqdId);
	
	List<TarQueryDim> findAll();

	List<TarQueryDim> findTarQueryDimByTargetCode(String targetCode);
}
