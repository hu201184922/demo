package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.QueryDimDetailMapper;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;
import com.huatai.web.service.QueryDimDetailService;

/**
 * @description:
 * @author ：沈从会
 * @datetime : 2017年7月20日 下午4:27:04
 */
@Service
public class QueryDimDetailServiceImpl implements QueryDimDetailService {

	@Autowired
	private QueryDimDetailMapper queryDimDetailMapper;

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int countByExample(QueryDimDetailExample example) {
		return this.queryDimDetailMapper.countByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int deleteByExample(QueryDimDetailExample example) {
		return this.queryDimDetailMapper.deleteByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int deleteByPrimaryKey(Integer qddId) {
		return this.queryDimDetailMapper.deleteByPrimaryKey(qddId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int insert(QueryDimDetail record) {
		return this.queryDimDetailMapper.insert(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int insertSelective(QueryDimDetail record) {
		return this.queryDimDetailMapper.insertSelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public List<QueryDimDetail> selectByExample(QueryDimDetailExample example) {
		return this.queryDimDetailMapper.selectByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public QueryDimDetail selectByPrimaryKey(Integer qddId) {
		return this.queryDimDetailMapper.selectByPrimaryKey(qddId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int updateByExampleSelective(QueryDimDetail record, QueryDimDetailExample example) {
		return this.queryDimDetailMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int updateByExample(QueryDimDetail record, QueryDimDetailExample example) {
		return this.queryDimDetailMapper.updateByExample(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int updateByPrimaryKeySelective(QueryDimDetail record) {
		return this.queryDimDetailMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public int updateByPrimaryKey(QueryDimDetail record) {
		return this.queryDimDetailMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月20日 下午4:27:04
	 */
	@Override
	public Pager<QueryDimDetail> findByPager(Pager<QueryDimDetail> pager, QueryDimDetail record) {
		return this.queryDimDetailMapper.findByPager(pager, record);
	}

	@Override
	public QueryDimDetail findByQdId(Integer qdId) {
		QueryDimDetailExample exa = new QueryDimDetailExample();
		exa.createCriteria().andQdIdEqualTo(qdId);
		return queryDimDetailMapper.selectByExample(exa).get(0);
	}

	@Override
	public int update(QueryDimDetail record) {
		return queryDimDetailMapper.updateByPrimaryKeySelective(record);
	}

}
