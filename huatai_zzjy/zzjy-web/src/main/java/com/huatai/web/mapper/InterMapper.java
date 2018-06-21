package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterExample;

@MyBatisRepository
public interface InterMapper {
	int countByExample(InterExample example);

	int deleteByExample(InterExample example);

	void deleteByPrimaryKey(Integer interId);

	void insert(Inter record);

	int insertSelective(Inter record);

	List<Inter> selectByExample(InterExample example);

	Inter selectByPrimaryKey(Integer interId);

	int updateByExampleSelective(@Param("record") Inter record, @Param("example") InterExample example);

	int updateByExample(@Param("record") Inter record, @Param("example") InterExample example);

	int updateByPrimaryKeySelective(Inter record);

	void updateByPrimaryKey(Inter record);

	Pager<Inter> findByPager(Pager<Inter> pager, Inter record);

	List<Inter> findInterList();

	List<Inter> findInterFieldByInterId(String interId);

	List<Inter> findInterByTabNameAndQueDim(@Param("tableName") String tableName, @Param("dimCode") String dimCode);
}