package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterExample;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月19日 上午9:43:50
 */
public interface InterService {

	int countByExample(InterExample example);

    int deleteByExample(InterExample example);

    void deleteByPrimaryKey(Integer interId);

    void insert(Inter record);

    int insertSelective(Inter record);

    List<Inter> selectByExample(InterExample example);

    Inter selectByPrimaryKey(Integer interId);

    int updateByExampleSelective(Inter record, InterExample example);

    int updateByExample( Inter record,  InterExample example);

    int updateByPrimaryKeySelective(Inter record);

    void updateByPrimaryKey(Inter record);
    
    Pager<Inter> findByPager(Pager<Inter> pager, Inter record);

	List<Inter> findInterList();

	List<Inter> findInterFieldByInterId(String interId);
}
