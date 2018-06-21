package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.PlateOneTar;
import com.huatai.web.model.PlateOneTarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PlateOneTarMapper {
	int countByExample(PlateOneTarExample example);

	int deleteByExample(PlateOneTarExample example);

	int deleteByPrimaryKey(Integer potId);

	int insert(PlateOneTar record);

	int insertSelective(PlateOneTar record);

	List<PlateOneTar> selectByExample(PlateOneTarExample example);

	PlateOneTar selectByPrimaryKey(Integer potId);

	int updateByExampleSelective(@Param("record") PlateOneTar record, @Param("example") PlateOneTarExample example);

	int updateByExample(@Param("record") PlateOneTar record, @Param("example") PlateOneTarExample example);

	int updateByPrimaryKeySelective(PlateOneTar record);

	int updateByPrimaryKey(PlateOneTar record);

	List<PlateOneTar> findAllPlateTarByBlock(@Param("blockId") String blockId, @Param("groupbyDate") String groupbyDate,
			@Param("roleCate") String roleCate);

	List<PlateOneTar> findPlateOneTarByBlock(@Param("blockId") Integer blockId,
			@Param("groupbyDate") String groupbyDate);
}