package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TriggleExecute;
import com.huatai.web.model.TriggleExecuteExample;
import com.huatai.web.model.TriggleExecuteKey;

@MyBatisRepository
public interface TriggleExecuteMapper {
	int countByExample(TriggleExecuteExample example);

	int deleteByExample(TriggleExecuteExample example);

	int deleteByPrimaryKey(TriggleExecuteKey key);

	int deleteByQrtzCode(@Param("qrtzCode") String qrtzCode);

	int insert(TriggleExecute record);

	int insertSelective(TriggleExecute record);

	java.util.List<com.huatai.web.model.TriggleExecute> selectByExample(TriggleExecuteExample example);

	TriggleExecute selectByPrimaryKey(TriggleExecuteKey key);

	int updateByExampleSelective(@Param("record") TriggleExecute record,
			@Param("example") TriggleExecuteExample example);

	int updateByExample(@Param("record") TriggleExecute record, @Param("example") TriggleExecuteExample example);

	int updateByPrimaryKeySelective(TriggleExecute record);

	int updateByPrimaryKey(TriggleExecute record);

	List<TriggleExecute> findAll(TriggleExecute record);

	Pager<TriggleExecute> findByPager(Pager<TriggleExecute> pager, TriggleExecute record);
}