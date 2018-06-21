package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月20日 下午4:25:05
 */
public interface QueryDimDetailService {

	int countByExample(QueryDimDetailExample example);

    int deleteByExample(QueryDimDetailExample example);

    int deleteByPrimaryKey(Integer qddId);

    int insert(QueryDimDetail record);

    int insertSelective(QueryDimDetail record);

    List<QueryDimDetail> selectByExample(QueryDimDetailExample example);

    QueryDimDetail selectByPrimaryKey(Integer qddId);

    int updateByExampleSelective( QueryDimDetail record,  QueryDimDetailExample example);

    int updateByExample( QueryDimDetail record,  QueryDimDetailExample example);

    int updateByPrimaryKeySelective(QueryDimDetail record);

    int updateByPrimaryKey(QueryDimDetail record);
    
    Pager<QueryDimDetail> findByPager(Pager<QueryDimDetail> pager, QueryDimDetail record);

	QueryDimDetail findByQdId(Integer qdId);

	int update(QueryDimDetail record);
}
