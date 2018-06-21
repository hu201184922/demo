package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.SqlAliasMapper;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.SqlAliasExample;
import com.huatai.web.service.SqlAliasService;

/**
 * @description:
 * @author ：沈从会
 * @datetime : 2017年7月17日 上午10:14:25
 */
@Service
public class SqlAliasServiceImpl implements SqlAliasService {

	@Autowired
	private SqlAliasMapper sqlAliasMapper;

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public int countByExample(SqlAliasExample example) {
		return this.sqlAliasMapper.countByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public int deleteByExample(SqlAliasExample example) {
		return this.sqlAliasMapper.deleteByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public void deleteByPrimaryKey(Integer saId) {
		this.sqlAliasMapper.deleteByPrimaryKey(saId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public void insert(SqlAlias record) {
		this.sqlAliasMapper.insert(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public int insertSelective(SqlAlias record) {
		return this.sqlAliasMapper.insertSelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public List<SqlAlias> selectByExample(SqlAliasExample example) {
		return this.sqlAliasMapper.selectByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public SqlAlias selectByPrimaryKey(Integer saId) {
		return this.sqlAliasMapper.selectByPrimaryKey(saId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public int updateByExampleSelective(SqlAlias record, SqlAliasExample example) {
		return this.sqlAliasMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public int updateByExample(SqlAlias record, SqlAliasExample example) {
		return this.sqlAliasMapper.updateByExample(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public int updateByPrimaryKeySelective(SqlAlias record) {
		return this.sqlAliasMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 上午10:14:26
	 */
	@Override
	public void updateByPrimaryKey(SqlAlias record) {
		this.sqlAliasMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月17日 下午2:40:17
	 */
	@Override
	public Pager<SqlAlias> findByPager(Pager<SqlAlias> pager, SqlAlias record) {
		Pager<SqlAlias> records = sqlAliasMapper.findByPager(pager, record);
		return records;
	}

	@Override
	public List<SqlAlias> findByPager(SqlAlias record) {
		return sqlAliasMapper.findByPager(record);
	}

	@Override
	public int update(SqlAlias record) {
		return sqlAliasMapper.updateByPrimaryKeySelective(record);
	}

}
