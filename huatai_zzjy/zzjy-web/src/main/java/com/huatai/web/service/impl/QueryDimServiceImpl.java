package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.QueryDimMapper;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimExample;
import com.huatai.web.service.QueryDimService;

/**
 * @description:
 * @author ：沈从会
 * @datetime : 2017年7月18日 下午1:25:07
 */
@Service
public class QueryDimServiceImpl implements QueryDimService {

	@Autowired
	private QueryDimMapper queryDimMapper;

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public int countByExample(QueryDimExample example) {
		return this.queryDimMapper.countByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public int deleteByExample(QueryDimExample example) {
		return this.queryDimMapper.deleteByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public void deleteByPrimaryKey(Integer qdId) {
		this.queryDimMapper.deleteByPrimaryKey(qdId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public void insert(QueryDim record) {
		this.queryDimMapper.insert(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public int insertSelective(QueryDim record) {
		return this.queryDimMapper.insertSelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public List<QueryDim> selectByExample(QueryDimExample example) {
		return this.queryDimMapper.selectByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public QueryDim selectByPrimaryKey(Integer qdId) {
		return this.queryDimMapper.selectByPrimaryKey(qdId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public int updateByExampleSelective(QueryDim record, QueryDimExample example) {
		return this.queryDimMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public int updateByExample(QueryDim record, QueryDimExample example) {
		return this.queryDimMapper.updateByExample(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public int updateByPrimaryKeySelective(QueryDim record) {
		return this.queryDimMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public void updateByPrimaryKey(QueryDim record) {
		this.queryDimMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月18日 下午1:25:08
	 */
	@Override
	public Pager<QueryDim> findByPager(Pager<QueryDim> pager, QueryDim record) {
		Pager<QueryDim> records = queryDimMapper.findByPager(pager, record);
		return records;
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月20日 下午5:13:02
	 */
	@Override
	public List<QueryDim> findAllQueryDim() {
		return this.queryDimMapper.findAll();
	}

	/**
	 * @function:
	 * @author  ：沈从会
	 * @datetime:2017年7月21日 上午10:21:57
	 */
	@Override
	public QueryDim selectByQueryDimName(String queryDimName) {
		return this.queryDimMapper.selectByQueryDimName(queryDimName);
	}

	@Override
	public List<QueryDim> findByTargetCode(String targetCode) {
		return this.queryDimMapper.findByTargetCode(targetCode);
	}

	@Override
	public List<DictItem> getDialogBoxs() {
		return queryDimMapper.getDialogBoxs();
	}

	@Override
	public List<QueryDim> findByQueryDimCode() {
		return this.queryDimMapper.findByQueryDimCode();
	}

	@Override
	public int update(QueryDim record) {
		return this.queryDimMapper.updateByPrimaryKeySelective(record);
	}

}
