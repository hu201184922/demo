package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarRegSqlExample;

@MyBatisRepository
public interface TarRegSqlMapper {

	int countByExample(TarRegSqlExample example);

	int deleteByExample(TarRegSqlExample example);

	int deleteByPrimaryKey(Integer trsId);

	int insert(TarRegSql record);

	int insertSelective(TarRegSql record);

	List<TarRegSql> selectByExample(TarRegSqlExample example);

	TarRegSql selectByPrimaryKey(Integer trsId);

	int updateByExampleSelective(@Param("record") TarRegSql record, @Param("example") TarRegSqlExample example);

	int updateByExample(@Param("record") TarRegSql record, @Param("example") TarRegSqlExample example);

	int updateByPrimaryKeySelective(TarRegSql record);

	int updateByPrimaryKey(TarRegSql record);

	Pager<TarRegSql> findByPager(Pager<TarRegSql> pager, TarRegSql record);

	List<TarRegSql> getSqlByTarRegCode(@Param("code") String code, @Param("regCode") String regCode);

	List<TarRegSql> getRedisTarRegSql(@Param("target") String target, @Param("regId") Integer regId,
			@Param("groupType") String groupType, @Param("groupDetail") String groupDetail,
			@Param("dateType") String dateType);

	List<TarRegSql> getTarRegSql(@Param("target") String target, @Param("regId") Integer regId,
			@Param("groupType") String groupType, @Param("groupDetail") String groupDetail,
			@Param("dateType") String dateType);
}