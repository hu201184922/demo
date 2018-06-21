package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.bean.QueryDimDetailBean;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;
@MyBatisRepository
public interface QueryDimDetailMapper {
    int countByExample(QueryDimDetailExample example);

    int deleteByExample(QueryDimDetailExample example);

    int deleteByPrimaryKey(Integer qddId);

    int insert(QueryDimDetail record);

    int insertSelective(QueryDimDetail record);

    List<QueryDimDetail> selectByExample(QueryDimDetailExample example);

    QueryDimDetail selectByPrimaryKey(Integer qddId);

    int updateByExampleSelective(@Param("record") QueryDimDetail record, @Param("example") QueryDimDetailExample example);

    int updateByExample(@Param("record") QueryDimDetail record, @Param("example") QueryDimDetailExample example);

    int updateByPrimaryKeySelective(QueryDimDetail record);

    int updateByPrimaryKey(QueryDimDetail record);
    
    Pager<QueryDimDetail> findByPager(Pager<QueryDimDetail> pager, QueryDimDetail record);

	List<QueryDimDetailBean> findQueryDimDetailById();
}