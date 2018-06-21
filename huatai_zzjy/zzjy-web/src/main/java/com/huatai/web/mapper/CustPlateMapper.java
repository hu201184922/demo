package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.CustPlate;
import com.huatai.web.model.CustPlateExample;

@MyBatisRepository
public interface CustPlateMapper {

	int countByExample(CustPlateExample example);

	int deleteByExample(CustPlateExample example);

	int deleteByPrimaryKey(Integer plateId);

	int insert(CustPlate record);

	int insertSelective(CustPlate record);

	List<CustPlate> selectByExample(CustPlateExample example);

	CustPlate selectByPrimaryKey(Integer plateId);

	int updateByExampleSelective(@Param("record") CustPlate record, @Param("example") CustPlateExample example);

	int updateByExample(@Param("record") CustPlate record, @Param("example") CustPlateExample example);

	int updateByPrimaryKeySelective(CustPlate record);

	int updateByPrimaryKey(CustPlate record);

	Pager<CustPlate> findPlateByUser(Pager<CustPlate> pager, @Param("role") String role,
			@Param("userName") String userName, @Param("blockName") String blockName);

}