package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarInitSqlExample;
import com.huatai.web.model.Target;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface TarInitSqlMapper {
	int countByExample(TarInitSqlExample example);

	int deleteByExample(TarInitSqlExample example);

	int deleteByPrimaryKey(Integer tisId);

	int insert(TarInitSql record);

	int insertSelective(TarInitSql record);

	List<TarInitSql> selectByExample(TarInitSqlExample example);

	TarInitSql selectByPrimaryKey(Integer tisId);

	int updateByExampleSelective(@Param("record") TarInitSql record, @Param("example") TarInitSqlExample example);

	int updateByExample(@Param("record") TarInitSql record, @Param("example") TarInitSqlExample example);

	int updateByPrimaryKeySelective(TarInitSql record);

	int updateByPrimaryKey(TarInitSql record);

	List<Target> findSubPlates();

	List<Target> getTarBySubId(@Param("subPlaId") String subPlaId);

	List<DictItem> findFuncList();

	Pager<TarInitSql> findByPager(Pager<TarInitSql> pager, TarInitSql record);

	List<TarInitSql> getTarInitSql(@Param("fun") String fun, @Param("targetCode") String targetCode,
			@Param("groupCode") String groupCode, @Param("groupDetail") String groupDetail,
			@Param("dateType") String dateType);
}