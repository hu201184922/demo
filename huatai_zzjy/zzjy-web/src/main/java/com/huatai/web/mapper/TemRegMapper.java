package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface TemRegMapper {

	int countByExample(TemRegExample example);

	int deleteByExample(TemRegExample example);

	int deleteByPrimaryKey(Integer regId);

	int insert(TemReg record);

	int insertSelective(TemReg record);

	List<TemReg> selectByExampleWithBLOBs(TemRegExample example);

	List<TemReg> selectByExample(TemRegExample example);

	TemReg selectByPrimaryKey(Integer regId);

	int updateByExampleSelective(@Param("record") TemReg record, @Param("example") TemRegExample example);

	int updateByExampleWithBLOBs(@Param("record") TemReg record, @Param("example") TemRegExample example);

	int updateByExample(@Param("record") TemReg record, @Param("example") TemRegExample example);

	int updateByPrimaryKeySelective(TemReg record);

	int updateByPrimaryKeyWithBLOBs(TemReg record);

	int updateByPrimaryKey(TemReg record);

	Pager<TemReg> findByPager(Pager<TemReg> pager, TemReg record);

	List<TemReg> findRegionByTemp(@Param("code") String code);
	
	List<TemReg> findTemRegListByBlock(@Param("blockId") Integer blockId);
}