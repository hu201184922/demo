package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.PlateTwoTarGro;
import com.huatai.web.model.PlateTwoTarGroExample;

@MyBatisRepository
public interface PlateTwoTarGroMapper {
	int countByExample(PlateTwoTarGroExample example);

	int deleteByExample(PlateTwoTarGroExample example);

	int deleteByPrimaryKey(Integer pttgId);

	int insert(PlateTwoTarGro record);

	int insertSelective(PlateTwoTarGro record);

	List<PlateTwoTarGro> selectByExample(PlateTwoTarGroExample example);

	PlateTwoTarGro selectByPrimaryKey(Integer pttgId);

	int updateByExampleSelective(@Param("record") PlateTwoTarGro record,
			@Param("example") PlateTwoTarGroExample example);

	int updateByExample(@Param("record") PlateTwoTarGro record, @Param("example") PlateTwoTarGroExample example);

	int updateByPrimaryKeySelective(PlateTwoTarGro record);

	int updateByPrimaryKey(PlateTwoTarGro record);

	Pager<PlateTwoTarGro> findTwoTargetGroupByPage(Pager<PlateTwoTarGro> pager, @Param("blockId") Integer blockId);
}