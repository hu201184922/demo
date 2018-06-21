package com.huatai.web.thrift.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.bean.FieldInterBean;
import com.huatai.web.mapper.BillFiltDimMapper;
import com.huatai.web.mapper.BillTargetMapper;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.InterFieldMapper;
import com.huatai.web.mapper.InterMapper;
import com.huatai.web.mapper.QueryDimMapper;
import com.huatai.web.mapper.SqlAliasMapper;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.mapper.TarInitSqlMapper;
import com.huatai.web.mapper.TarQueryDimMapper;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.mapper.TargetInterMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillFiltDimExample;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.BillTargetExample;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterField;
import com.huatai.web.model.InterFieldExample;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimExample;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.SqlAliasExample;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarQueryDimExample;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.thrift.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private SqlAliasMapper sqlAliasMapper;

	@Autowired
	private InterFieldMapper interFieldMapper;

	@Autowired
	private InterMapper interMapper;

	@Autowired
	private TargetMapper targetMapper;

	@Autowired
	private BillFiltDimMapper billFiltDimMapper;

	@Autowired
	private QueryDimMapper queryDimMapper;

	@Autowired
	private TarQueryDimMapper tarQueryDimMapper;

	@Autowired
	private BillTargetMapper billTargetMapper;

	@Autowired
	private TargetInterMapper targetInterMapper;

	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;

	@Autowired
	private TarInitSqlMapper tarInitSqlMapper;

	@Autowired
	private TarGroDimMapper tarGroDimMapper;

	@Autowired
	private GroDimDetailMapper groDimDetailMapper;

	/**
	 * @功能 {按维度和接口查询InterField}
	 * @时间 2017年7月19日 下午12:16:03
	 */
	public List<SqlAlias> findSqlAliasByTrsId(Integer trsId) {
		SqlAliasExample sqlAliasExample = new SqlAliasExample();
		sqlAliasExample.createCriteria().andOpTypeNotEqualTo("D").andTrsIdEqualTo(trsId);
		return this.sqlAliasMapper.selectByExample(sqlAliasExample);
	}

	/**
	 * 
	 * @功能 {按维度和接口查询InterField}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午12:16:03
	 */
	public InterField findInterFieldByInterAndDim(Integer interId, String dimCode) {
		InterFieldExample interFieldExample = new InterFieldExample();
		interFieldExample.createCriteria().andOpTypeNotEqualTo("D").andInterIdEqualTo(interId)
				.andDimCodeEqualTo(dimCode);
		List<InterField> InterFieldList = this.interFieldMapper.selectByExample(interFieldExample);
		if (null != InterFieldList && InterFieldList.size() == 1) {
			return InterFieldList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {那表名查询 接口}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午11:18:09
	 */
	public Inter findInterByTableName(String tableNmae, String dimCode) {
		List<Inter> inters = this.interMapper.findInterByTabNameAndQueDim(tableNmae, dimCode);
		if (null != inters && inters.size() == 1) {
			return inters.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询接口字段}
	 * @作者 MaxBill
	 * @时间 2017年8月29日 下午4:40:55
	 */
	public InterField findInterFieldByTabAndTar(String target, String dimCode) {
		Target targetBean = this.targetMapper.selectByPrimaryKey(target);
		return interFieldMapper.findInterFieldBySub(targetBean.getParentCode(), dimCode);
	}

	/**
	 * @功能 {按清单和维度查询BillFiltDim}
	 * @作者 MaxBill
	 * @时间 2017年8月29日 下午6:11:03
	 */
	public BillFiltDim findBillFiltByBillAndTarAndDim(Integer billId, String target, String dimCode) {
		BillFiltDimExample billFiltDimExample = new BillFiltDimExample();
		billFiltDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId)
				.andTargetCodeEqualTo(target).andFiltDimCodeEqualTo(dimCode);
		List<BillFiltDim> billFiltDimList = this.billFiltDimMapper.selectByExample(billFiltDimExample);
		if (null != billFiltDimList && billFiltDimList.size() == 1) {
			return billFiltDimList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按code查维度}
	 * @作者 MaxBill
	 * @时间 2017年8月29日 下午6:26:12
	 */
	public QueryDim findQueryDimByCode(String code) {
		QueryDimExample queryDimExample = new QueryDimExample();
		queryDimExample.createCriteria().andOpTypeNotEqualTo("D").andQueryDimCodeEqualTo(code);
		List<QueryDim> queryDimList = this.queryDimMapper.selectByExample(queryDimExample);
		if (null != queryDimList && queryDimList.size() == 1) {
			return queryDimList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按清单id查询指标数据}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午11:07:38
	 */
	public List<BillTarget> findBillTargetByBill(Integer billId) {
		BillTargetExample billTargetExample = new BillTargetExample();
		billTargetExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId);
		return this.billTargetMapper.selectByExample(billTargetExample);
	}

	/**
	 * @功能 {按指标、区域、维度查询TarRegSql}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:34:58
	 */
	public TarRegSql findTarRegSql(String target, Integer regId, String groupType, String groupDetail,
			String dateType) {
		List<TarRegSql> tarRegSqls = this.tarRegSqlMapper.getTarRegSql(target, regId, groupType, groupDetail, dateType);
		if (null != tarRegSqls && tarRegSqls.size() == 1) {
			return tarRegSqls.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询基础 sql}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:24:03
	 */
	public Object getTarInitSql(String fun, String targetCode, String groupType, String groupDetail, String dateType) {
		List<TarInitSql> tarInitSqlList = this.tarInitSqlMapper.getTarInitSql(fun, targetCode, groupType, groupDetail,
				dateType);
		if (null != tarInitSqlList && tarInitSqlList.size() > 0) {
			return tarInitSqlList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按分组维度}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:24:03
	 */
	public Boolean findTarGroDimByGroAndTar(String dimCode, String target) {
		List<TarGroDim> tarGroDimList = tarGroDimMapper.findTarGroDimByGroAndTar(dimCode, target);
		if (null != tarGroDimList && tarGroDimList.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @功能 {按主题查询时间维度}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:24:03
	 */
	public List<GroDimDetail> findGroDetailListBySubWithDate(String subCode) {
		return this.groDimDetailMapper.findGroDetailListBySubWithDate(subCode);
	}

	/**
	 * @功能 {按主题查询时间维度}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:24:03
	 */
	public List<GroDimDetail> findGroDetailListBySubWithDateAndPlate(Integer plateId) {
		return this.groDimDetailMapper.findGroDetailListBySubWithDateAndPlate(plateId);
	}

	@Override
	public boolean findTQDByChannelCode(String code) {
		boolean result = false;
		QueryDimExample queryDimExample = new QueryDimExample();
		queryDimExample.createCriteria().andOpTypeNotEqualTo("D").andQueryDimCodeEqualTo(code);
		List<QueryDim> qds = queryDimMapper.selectByExample(queryDimExample);
		if (qds.size() > 0) {
			Integer qdId = qds.get(0).getQdId();
			TarQueryDimExample tarQueryDimExample = new TarQueryDimExample();
			tarQueryDimExample.createCriteria().andOpTypeNotEqualTo("D").andQdIdEqualTo(qdId);
			List<TarQueryDim> tqds = tarQueryDimMapper.selectByExample(tarQueryDimExample);
			if (tqds.size() > 0) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public List<FieldInterBean> findTIByTarChanCode(String targetCode, String ChannelCode, String interName) {
		return targetInterMapper.findTIByTarChanCode(targetCode, ChannelCode, interName);
	}

}
