package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.SqlAliasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface SqlAliasMapper {
    int countByExample(SqlAliasExample example);

    int deleteByExample(SqlAliasExample example);

    void deleteByPrimaryKey(Integer saId);

    void insert(SqlAlias record);

    int insertSelective(SqlAlias record);

    List<SqlAlias> selectByExample(SqlAliasExample example);

    SqlAlias selectByPrimaryKey(Integer saId);

    int updateByExampleSelective(@Param("record") SqlAlias record, @Param("example") SqlAliasExample example);

    int updateByExample(@Param("record") SqlAlias record, @Param("example") SqlAliasExample example);

    int updateByPrimaryKeySelective(SqlAlias record);

    void updateByPrimaryKey(SqlAlias record);
    
    Pager<SqlAlias> findByPager(Pager<SqlAlias> pager, SqlAlias record);
    
    List<SqlAlias> findByPager(SqlAlias record);
}