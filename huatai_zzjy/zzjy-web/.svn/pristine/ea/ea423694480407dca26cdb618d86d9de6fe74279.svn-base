package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.TarStatisExample;
@MyBatisRepository
public interface TarStatisMapper {
    int countByExample(TarStatisExample example);

    int deleteByExample(TarStatisExample example);

    int deleteByPrimaryKey(Long tsId);

    int insert(TarStatis record);

    int insertSelective(TarStatis record);

    List<TarStatis> selectByExample(TarStatisExample example);

    TarStatis selectByPrimaryKey(Long tsId);

    int updateByExampleSelective(@Param("record") TarStatis record, @Param("example") TarStatisExample example);

    int updateByExample(@Param("record") TarStatis record, @Param("example") TarStatisExample example);

    int updateByPrimaryKeySelective(TarStatis record);

    int updateByPrimaryKey(TarStatis record);
    
    List<TarStatis> findAll(TarStatis record);

	List<TarStatis> findBySubCode(@Param("subCode")String subCode,@Param("depts") String depts);

	int addTarStatis(TarStatis record);

	int deleteTarStatis(@Param("subCode")String subCode);

	List<TarStatis> findByIsSubCode(@Param("subCode")String subCode, String object);

	int updateByTargetCodeAndAnSubCode(TarStatis record);
}