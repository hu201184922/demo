package com.huatai.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;

@MyBatisRepository
public interface GroDimDetailMapper {

	int countByExample(GroDimDetailExample example);

	int deleteByExample(GroDimDetailExample example);

	int deleteByPrimaryKey(Integer gddId);

	int insert(GroDimDetail record);

	int insertSelective(GroDimDetail record);

	List<GroDimDetail> selectByExample(GroDimDetailExample example);

	GroDimDetail selectByPrimaryKey(Integer gddId);

	int updateByExampleSelective(@Param("record") GroDimDetail record, @Param("example") GroDimDetailExample example);

	int updateByExample(@Param("record") GroDimDetail record, @Param("example") GroDimDetailExample example);

	int updateByPrimaryKeySelective(GroDimDetail record);

	int updateByPrimaryKey(GroDimDetail record);

	Pager<GroDimDetail> findByPager(Pager<GroDimDetail> pager, GroDimDetail record);

	List<GroDimDetail> findAll();

	GroDimDetail selectByGroDimName(String groDimName);

	List<GroDimDetail> findGroDimDetailByGdId(String gdId);

	List<GroDimDetail> findByTargetCodeAndGdId(Map<String, Object> params);

	List<GroDimDetail> findByGroDimTypeCode(String code);

	String findGroDimCodeByGroDimName(@Param("groDimName") String groDimName);

	GroDimDetail findGroDetailCodeByTarget(@Param("target") String target);

	List<GroDimDetail> findGroDetailListByTarget(@Param("groupType") String groupType, @Param("target") String target);

	List<GroDimDetail> findByGdId(Map<String, Object> params);

	List<GroDimDetail> findGroDetailListBySubWithDate(@Param("subCode") String subCode);

	List<GroDimDetail> findIndexGroDetailListBySubWithDate(@Param("subCode") String subCode);

	List<GroDimDetail> findGroDetailListBySubWithDateAndPlate(@Param("plateId") Integer plateId);

}