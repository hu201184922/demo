package com.huatai.web.service;

import java.util.List;

import com.huatai.web.model.TarStatis;

public interface TarStatisService {
	int deleteByPrimaryKey(Long tsId);

    int insert(TarStatis record);
    
    TarStatis selectByPrimaryKey(Long tsId);
    
    int updateByPrimaryKey(TarStatis record);
    
    List<TarStatis> findAll(TarStatis record);

	List<TarStatis> findBySubCode(String subCode, String depts);

	int addTarStatis(TarStatis record);

	int deleteTarStatis(String roleTarget);

	List<TarStatis> findByIsSubCode(String subCode, String depts);

	int updateByTargetCodeAndAnSubCode(TarStatis record);
}
