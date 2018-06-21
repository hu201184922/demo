package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimExample;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月18日 下午1:23:18
 */
public interface QueryDimService {

	int countByExample(QueryDimExample example);

	int deleteByExample(QueryDimExample example);

	void deleteByPrimaryKey(Integer qdId);

	void insert(QueryDim record);

	int insertSelective(QueryDim record);

	List<QueryDim> selectByExample(QueryDimExample example);

	QueryDim selectByPrimaryKey(Integer qdId);

	int updateByExampleSelective( QueryDim record,  QueryDimExample example);

	int updateByExample( QueryDim record,  QueryDimExample example);

	int updateByPrimaryKeySelective(QueryDim record);

	void updateByPrimaryKey(QueryDim record);
	
	Pager<QueryDim> findByPager(Pager<QueryDim> pager, QueryDim record);

	List<QueryDim> findAllQueryDim();

	QueryDim selectByQueryDimName(String queryDimName);

	List<QueryDim> findByTargetCode(String targetCode);

	List<DictItem> getDialogBoxs();

	List<QueryDim> findByQueryDimCode();

	int update(QueryDim record);
}
