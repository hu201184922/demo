package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface QueryDimMapper {

	int countByExample(QueryDimExample example);

	int deleteByExample(QueryDimExample example);

	void deleteByPrimaryKey(Integer qdId);

	void insert(QueryDim record);

	int insertSelective(QueryDim record);

	List<QueryDim> selectByExample(QueryDimExample example);

	QueryDim selectByPrimaryKey(Integer qdId);

	int updateByExampleSelective(@Param("record") QueryDim record, @Param("example") QueryDimExample example);

	int updateByExample(@Param("record") QueryDim record, @Param("example") QueryDimExample example);

	int updateByPrimaryKeySelective(QueryDim record);

	void updateByPrimaryKey(QueryDim record);

	Pager<QueryDim> findByPager(Pager<QueryDim> pager, QueryDim record);

	List<QueryDim> findAll();

	QueryDim selectByQueryDimName(String queryDimName);

	List<QueryDim> findByTargetCode(String targetCode);

	List<DictItem> getDialogBoxs();

	List<QueryDim> findQueryDimListByTarget(@Param("targetCode") String targetCode);

	List<QueryDim> findByQueryDimCode();

	List<QueryDim> findQueryDimByBlock(@Param("blockId") Integer blockId);

}