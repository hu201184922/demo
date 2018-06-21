package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TableTriggle;
import com.huatai.web.model.TableTriggleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface TableTriggleMapper {
	int countByExample(TableTriggleExample example);

	int deleteByExample(TableTriggleExample example);

	int insert(TableTriggle record);

	int insertSelective(TableTriggle record);

	java.util.List<com.huatai.web.model.TableTriggle> selectByExample(TableTriggleExample example);

	int updateByExampleSelective(@Param("record") TableTriggle record, @Param("example") TableTriggleExample example);

	int updateByExample(@Param("record") TableTriggle record, @Param("example") TableTriggleExample example);

	List<TableTriggle> findAll(TableTriggle tableTriggle);

	List<TableTriggle> queryTableTriggleList();
}