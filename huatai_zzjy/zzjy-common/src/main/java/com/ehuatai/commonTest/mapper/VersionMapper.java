package com.ehuatai.commonTest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ehuatai.commonTest.entity.Version;
import com.ehuatai.commonTest.entity.VersionExample;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

@DataSource(name = "dataSource1", value = "dataSource1")
@MyBatisRepository
public interface VersionMapper {

	int countByExample(VersionExample example);

	int deleteByExample(VersionExample example);

	int deleteByPrimaryKey(Integer versionId);

	int insert(Version record);

	int insertSelective(Version record);

	List<Version> selectByExample(VersionExample example);

	Version selectByPrimaryKey(Integer versionId);

	int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

	int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

	int updateByPrimaryKeySelective(Version record);

	int updateByPrimaryKey(Version record);
}