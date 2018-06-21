package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.InterFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface InterFieldMapper {
	int countByExample(InterFieldExample example);

	int deleteByExample(InterFieldExample example);

	int deleteByPrimaryKey(Integer fieldId);

	int insert(InterField record);

	int insertSelective(InterField record);

	List<InterField> selectByExample(InterFieldExample example);

	InterField selectByPrimaryKey(Integer fieldId);

	int updateByExampleSelective(@Param("record") InterField record, @Param("example") InterFieldExample example);

	int updateByExample(@Param("record") InterField record, @Param("example") InterFieldExample example);

	int updateByPrimaryKeySelective(InterField record);

	int updateByPrimaryKey(InterField record);

	Pager<InterField> findInterFieldByPage(Pager<InterField> pager, Inter record);

	List<Inter> findInterFieldByInterId(String interId);

	InterField findInterFieldBySub(@Param("subject") String subject, @Param("dimCode") String dimCode);

	int updateByInterId(InterField interField);
}