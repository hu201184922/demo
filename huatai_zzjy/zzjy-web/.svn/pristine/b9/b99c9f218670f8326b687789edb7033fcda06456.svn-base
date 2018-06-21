package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.SqlAliasExample;

/**
 * @description :
 * @author      ：沈从会
 * @datetime    : 2017年7月17日 上午10:09:27
 */
public interface SqlAliasService {

	int countByExample(SqlAliasExample example);

    int deleteByExample(SqlAliasExample example);

    void deleteByPrimaryKey(Integer saId);

    void insert(SqlAlias record);

    int insertSelective(SqlAlias record);

    List<SqlAlias> selectByExample(SqlAliasExample example);

    SqlAlias selectByPrimaryKey(Integer saId);

    int updateByExampleSelective(SqlAlias record,SqlAliasExample example);

    int updateByExample( SqlAlias record, SqlAliasExample example);

    int updateByPrimaryKeySelective(SqlAlias record);

    void updateByPrimaryKey(SqlAlias record);
    
    Pager<SqlAlias> findByPager(Pager<SqlAlias> pager, SqlAlias record);
    
    List<SqlAlias> findByPager(SqlAlias record);

	int update(SqlAlias record);
}
