package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.PlateTwoTar;
import com.huatai.web.model.PlateTwoTarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PlateTwoTarMapper {
	int countByExample(PlateTwoTarExample example);

	int deleteByExample(PlateTwoTarExample example);

	int deleteByPrimaryKey(Integer pttId);

	int insert(PlateTwoTar record);

	int insertSelective(PlateTwoTar record);

	List<PlateTwoTar> selectByExample(PlateTwoTarExample example);

	PlateTwoTar selectByPrimaryKey(Integer pttId);

	int updateByExampleSelective(@Param("record") PlateTwoTar record, @Param("example") PlateTwoTarExample example);

	int updateByExample(@Param("record") PlateTwoTar record, @Param("example") PlateTwoTarExample example);

	int updateByPrimaryKeySelective(PlateTwoTar record);

	int updateByPrimaryKey(PlateTwoTar record);

	List<PlateTwoTar> findPlateTwoTarByGroupWithDate(@Param("groupId") Integer groupId,
			@Param("dateType") String dateType);
}