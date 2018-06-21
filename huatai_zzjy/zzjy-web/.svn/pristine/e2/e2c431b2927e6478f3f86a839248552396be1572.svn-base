package com.huatai.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.TableExecute;
import com.huatai.web.model.TableExecuteExample;

@MyBatisRepository
@DataSource(name = "dataSource2")
public interface TableExecuteMapper {
	int countByExample(TableExecuteExample example);

	int deleteByExample(TableExecuteExample example);

	int deleteByPrimaryKey(String tableCode);

	int insert(TableExecute record);

	int insertSelective(TableExecute record);

	java.util.List<com.huatai.web.model.TableExecute> selectByExample(TableExecuteExample example);

	TableExecute selectByPrimaryKey(String tableCode);

	int updateByExampleSelective(@Param("record") TableExecute record, @Param("example") TableExecuteExample example);

	int updateByExample(@Param("record") TableExecute record, @Param("example") TableExecuteExample example);

	int updateByPrimaryKeySelective(TableExecute record);

	int updateByPrimaryKey(TableExecute record);

	List<TableExecute> findCompletedOfToday(@Param("tableCode") String tableCode, @Param("dateCode") Date dateCode,
			@Param("type") Integer type);

	List<TableExecute> findTableExecuteByTable(@Param("tableCode") String tableCode, @Param("dateCode") Date dateCode,
			@Param("type") Integer type);
}