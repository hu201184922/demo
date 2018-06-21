/**
 * 
 */
package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;

/**
 *@描述   :
 *@作者   : 程乐飞
 *@日期时间: 2017年7月28日 下午1:48:13
 */
public interface TemRegService {
	
	int countByExample(TemRegExample example);

    int deleteByExample(TemRegExample example);

    void deleteByPrimaryKey(Integer regId);

    void insert(TemReg record);

    int insertSelective(TemReg record);

    List<TemReg> selectByExample(TemRegExample example);

    TemReg selectByPrimaryKey(Integer regId);

    int updateByExampleSelective(TemReg record,TemRegExample example);

    int updateByExample( TemReg record, TemRegExample example);

    int updateByPrimaryKeySelective(TemReg record);

    void updateByPrimaryKey(TemReg record);
    
    Pager<TemReg> findByPager(Pager<TemReg> pager, TemReg record);
	
	
	
	
	
	

}
