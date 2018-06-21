package com.huatai.web.service;

import java.util.List;

import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;

public interface TarRegService {

    int deleteByExample(TarRegExample example);

    int deleteByPrimaryKey(Integer trId);

    int insert(TarReg record);

    TarReg selectByPrimaryKey(Integer trId);

    int updateByPrimaryKeySelective(TarReg record);

    int updateByPrimaryKey(TarReg record);
    
    List<TarReg> findAll(TarReg tarReg);
    
    String getSubCodeByTargetCode(String targetCode);
    
    List<TarReg> findAllLike(TarReg tarReg);
}
