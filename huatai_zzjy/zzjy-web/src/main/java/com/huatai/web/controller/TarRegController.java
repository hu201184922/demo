package com.huatai.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarRegTabHead;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.Templet;
import com.huatai.web.service.GroDimDetailService;
import com.huatai.web.service.GroDimService;
import com.huatai.web.service.QueryDimService;
import com.huatai.web.service.SqlAliasService;
import com.huatai.web.service.TarRegQueService;
import com.huatai.web.service.TarRegService;
import com.huatai.web.service.TarRegSqlService;
import com.huatai.web.service.TarRegTabHeadService;
import com.huatai.web.service.TargetService;
import com.huatai.web.service.TemRegService;
import com.huatai.web.service.TempletService;
import com.huatai.web.utils.Constants;

@Controller
@RequestMapping("admin/tarReg")
public class TarRegController {
	@Autowired
	private TargetService targetService;
	@Autowired
	private TemRegService temRegService;
	@Autowired
	private TarRegService tarRegService;
	@Autowired
	private TempletService templetService;
	@Autowired
	private GroDimService groDimService;
	@Autowired
	private TarRegSqlService tarRegSqlService;
	@Autowired
	private GroDimDetailService groDimDetailService;
	@Autowired
	private TarRegTabHeadService tarRegTabHeadService;
	@Autowired
	private SqlAliasService sqlAliasService;
	@Autowired
	private QueryDimService queryDimService;
	@Autowired
	private TarRegQueService tarRegQueService;
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(Integer tempId, Integer regId) {
		Templet templet = templetService.findById(tempId);
		TemReg temReg = temRegService.selectByPrimaryKey(regId);
		List<DictionaryItem> picTypes = dictionaryService.findByDictionaryCodeOrderByCode("PIC_TYPE");
		List<DictionaryItem> roleOrgTypes = dictionaryService.findByDictionaryCodeOrderByCode("DETP_DICT");
		List<DictionaryItem> unitTypes = dictionaryService.findByDictionaryCodeOrderByCode("UNIT_TYPE");
		List<GroDimDetail> groDimDetails = groDimDetailService.findByGroDimTypeCode("ORG_GROUP");
		ModelAndView model = new ModelAndView();
		model.addObject("groDimDetails", groDimDetails);
		model.addObject("unitTypes", unitTypes);
		model.addObject("roleOrgTypes", roleOrgTypes);
		model.addObject("picTypes", picTypes);
		model.addObject("templet", templet);
		model.addObject("temReg", temReg);
		model.setViewName("/admin/tarReg/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "listTarget")
	public Pager<Target> getTargetPager(Pager<Target> pager, Target record, Integer regId, String targetCode) {
		TarReg tarRegQuery = new TarReg();
		tarRegQuery.setRegId(regId);
		// tarRegQuery.setTargetCode(targetCode);
		List<TarReg> tarRegResult = tarRegService.findAll(tarRegQuery);
		Set<String> selectedTargetList = new HashSet<String>();
		for (TarReg tarReg : tarRegResult) {
			selectedTargetList.add(tarReg.getTargetCode());
		}
		Pager<Target> result = targetService.findTargetByPage2(pager, record);
		List<Target> targetList = result.getPageItems();
		for (Target target : targetList) {
			if (selectedTargetList.contains(target.getTargetCode())) {
				target.setChecked(true);
			} else {
				target.setChecked(false);
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public List<TarReg> findAll(TarReg record) {
		List<TarReg> result = tarRegService.findAll(record);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "findTarReg")
	public TarReg findTarReg(TarReg record) {
		List<TarReg> result = tarRegService.findAll(record);
		if (!CollectionUtils.isEmpty(result))
			return result.get(0);
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "listTarRegSql")
	public Pager<TarRegSql> getTarRegSqlPager(Pager<TarRegSql> pager, TarRegSql tarRegSql) {
		pager = tarRegSqlService.findByPager(pager, tarRegSql);
		return pager;
	}

	@ResponseBody
	@RequestMapping(value = "deleteTarRegSql")
	public String deleteTarRegSql(Integer trsId) {
		tarRegSqlService.deleteByPrimaryKey(trsId);
		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "saveTarRegSql")
	public String saveTarRegSql(TarRegSql tarRegSql, String[] aliasTableName, String[] aliasName,
			HttpServletRequest request) {
		try {
			tarRegSql.setSqlCode(java.net.URLDecoder.decode(tarRegSql.getSqlCode(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (tarRegSql.getTrsId() != null) {
			tarRegSql.setModifyTime(new Date());
			tarRegSql.setModifierId(SessionContextUtils.getLoginName().toString());
			tarRegSqlService.updateByPrimaryKeySelective(tarRegSql);

			// 更新表别名
			SqlAlias sqlAliasQuery = new SqlAlias();
			sqlAliasQuery.setTrsId(tarRegSql.getTrsId());
			List<SqlAlias> sqlAliasList = sqlAliasService.findByPager(sqlAliasQuery);
			for (SqlAlias sqlAlias : sqlAliasList) {
				sqlAliasService.deleteByPrimaryKey(sqlAlias.getSaId());
			}
			if (aliasTableName != null) {
				for (int i = 0; i < aliasTableName.length; i++) {
					String aliasTableName1 = aliasTableName[i];
					String aliasName1 = aliasName[i];
					SqlAlias sqlAlias = new SqlAlias();
					sqlAlias.setAliasName(aliasName1);
					sqlAlias.setTableName(aliasTableName1);
					sqlAlias.setCreateTime(new Date());
					sqlAlias.setCreatorId(SessionContextUtils.getLoginName().toString());
					sqlAlias.setModifyTime(new Date());
					sqlAlias.setModifierId(SessionContextUtils.getLoginName().toString());
					sqlAlias.setOpType("A");
					sqlAlias.setTrsId(tarRegSql.getTrsId());
					sqlAliasService.insert(sqlAlias);
				}
			}
		} else {
			tarRegSql.setOpType("A");
			// String emptyCode = "EMPTY";
			// tarRegSql.setGroupCode(emptyCode);
			// tarRegSql.setGroupDetailCode(emptyCode);
			String targetCode = tarRegSql.getTargetCode();
			// GroDim groDim = groDimService.findGroDimByTargetCode(targetCode);
			// GroDimDetail groDimDetail =
			// groDimDetailService.findByTargetCode(targetCode);
			// if(groDim!=null)
			// tarRegSql.setGroupCode(groDim.getGroDimTypeCode());
			// if(groDimDetail!=null)
			// tarRegSql.setGroupDetailCode(groDimDetail.getGroDimCode());
			tarRegSql.setCreateTime(new Date());
			tarRegSql.setCreatorId(SessionContextUtils.getLoginName().toString());
			tarRegSql.setModifyTime(new Date());
			tarRegSql.setModifierId(SessionContextUtils.getLoginName().toString());
			tarRegSql.setSubCode(tarRegService.getSubCodeByTargetCode(targetCode));
			tarRegSqlService.insert(tarRegSql);

			if (aliasTableName != null) {
				for (int i = 0; i < aliasTableName.length; i++) {
					String aliasTableName1 = aliasTableName[i];
					String aliasName1 = aliasName[i];
					SqlAlias sqlAlias = new SqlAlias();
					sqlAlias.setAliasName(aliasName1);
					sqlAlias.setTableName(aliasTableName1);
					sqlAlias.setCreateTime(new Date());
					sqlAlias.setCreatorId(SessionContextUtils.getLoginName().toString());
					sqlAlias.setModifyTime(new Date());
					sqlAlias.setModifierId(SessionContextUtils.getLoginName().toString());
					sqlAlias.setOpType("A");
					sqlAlias.setTrsId(tarRegSql.getTrsId());
					sqlAliasService.insert(sqlAlias);
				}
			}
		}

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "listGroDim")
	public List<GroDim> listGroDim(String targetCode) {
		List<GroDim> groDimList = groDimService.findGroDimByTargetCode(targetCode);
		return groDimList;
	}
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "listGroDimDetail") public List<GroDimDetail>
	 * listGroDimDetail(String targetCode,Integer gdId){ List<GroDimDetail>
	 * groDimDetail = groDimDetailService.findByTargetCodeAndGdId(targetCode,
	 * gdId); return groDimDetail; }
	 */

	@ResponseBody
	@RequestMapping(value = "listGroDimDetail")
	public List<GroDimDetail> listGroDimDetail(String targetCode, Integer gdId) {
		List<GroDimDetail> groDimDetail = groDimDetailService.findByTargetCodeAndGdId(gdId);
		return groDimDetail;
	}

	@ResponseBody
	@RequestMapping(value = "listSqlAlias")
	public List<SqlAlias> listSqlAlias(SqlAlias sa) {
		List<SqlAlias> result = sqlAliasService.findByPager(sa);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "listTarRegTabHead")
	public List<TarRegTabHead> listTarRegTabHead(TarRegTabHead trth) {
		List<TarRegTabHead> result = tarRegTabHeadService.findAll(trth);
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public TarReg create(@Valid TarReg record, String[] tabHeadName, Integer[] tabHeadIndex, Long[] qdId, Long tempId)
			throws ParseException {
		if (!"1".equals(record.getIsRank()))
			record.setIsRank("0");
		String color = record.getColor();
		if (StringUtil.isNotNull(color)) {
			color = "#" + record.getColor();
		}
		String graphType = record.getGraphType();
		if (Constants.GraphType7.equals(graphType)) {
			// record.setGraphTitle(tableName);
			if (tabHeadName != null) {
				for (int i = 0; i < tabHeadName.length; i++) {
					String headName = tabHeadName[i];
					Integer sort = tabHeadIndex[i];
					TarRegTabHead trth = new TarRegTabHead();
					trth.setColName(headName);
					trth.setSort(sort);
					trth.setCreateTime(new Date());
					trth.setCreatorId(SessionContextUtils.getLoginName().toString());
					trth.setModifyTime(new Date());
					trth.setModifierId(SessionContextUtils.getLoginName().toString());
					trth.setOpType("A");
					trth.setRegId(record.getRegId());
					trth.setTargetCode(record.getTargetCode());
					tarRegTabHeadService.insert(trth);
				}
			}
		}
		// String emptyCode = "EMPTY";
		// record.setDimType(emptyCode);
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setCreateTime(new Date());
		record.setColor(color);
		String targetCode = record.getTargetCode();
		String subCode = tarRegService.getSubCodeByTargetCode(targetCode);
		record.setSubCode(subCode);
		this.tarRegService.insert(record);

		if (qdId != null) {
			for (Long qdId1 : qdId) {
				TarRegQue tarRegQue = new TarRegQue();
				tarRegQue.setQdId(qdId1);
				tarRegQue.setRegId(record.getRegId().longValue());
				tarRegQue.setTempId(tempId);
				tarRegQue.setTargetCode(targetCode);
				tarRegQue.setCreateId(SessionContextUtils.getLoginName().toString());
				tarRegQue.setModifierId(SessionContextUtils.getLoginName().toString());
				tarRegQue.setCreateTime(new Date());
				tarRegQue.setModifyTime(new Date());
				tarRegQue.setModifierId(SessionContextUtils.getLoginName().toString());
				tarRegQue.setOpType("A");
				tarRegQue.setSubCode(subCode = targetCode.length() >= 3 ? targetCode.substring(0, 3) : targetCode);
				tarRegQueService.insert(tarRegQue);
			}
		}
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public TarReg update(@Valid TarReg record, String[] tabHeadName, Integer[] tabHeadIndex, Long[] qdId, Long tempId,
			RedirectAttributes redirectAttributes) throws Exception {
		if (!"1".equals(record.getIsRank()))
			record.setIsRank("0");
		String graphType = record.getGraphType();
		String color = record.getColor();
		if (StringUtil.isNotNull(color)) {
			color = "#" + record.getColor();
		}
		if (Constants.GraphType7.equals(graphType)) {
			// record.setGraphTitle(tableName);
			TarRegTabHead trthQuery = new TarRegTabHead();
			trthQuery.setRegId(record.getRegId());
			trthQuery.setTargetCode(record.getTargetCode());
			List<TarRegTabHead> trthList = tarRegTabHeadService.findAll(trthQuery);
			for (TarRegTabHead tarRegTabHead : trthList) {
				tarRegTabHeadService.deleteByPrimaryKey(tarRegTabHead.getTrthId());
			}
			if (tabHeadName != null) {
				for (int i = 0; i < tabHeadName.length; i++) {
					String headName = tabHeadName[i];
					Integer sort = tabHeadIndex[i];
					TarRegTabHead trth = new TarRegTabHead();
					trth.setColName(headName);
					trth.setSort(sort);
					trth.setCreateTime(new Date());
					trth.setCreatorId(SessionContextUtils.getLoginName().toString());
					trth.setModifyTime(new Date());
					trth.setModifierId(SessionContextUtils.getLoginName().toString());
					trth.setOpType("A");
					trth.setRegId(record.getRegId());
					trth.setTargetCode(record.getTargetCode());
					tarRegTabHeadService.insert(trth);
				}
			}
		}
		// 获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		record.setColor(color);
		this.tarRegService.updateByPrimaryKeySelective(record);

		if (qdId != null) {
			String targetCode = record.getTargetCode();
			String subCode = record.getSubCode();
			if (StringUtils.isEmpty(subCode)) {
				subCode = targetCode.length() >= 3 ? targetCode.substring(0, 3) : targetCode;
			}
			TarRegQue tarRegQueQuery = new TarRegQue();
			tarRegQueQuery.setRegId(record.getRegId().longValue());
			tarRegQueQuery.setTempId(tempId);
			tarRegQueQuery.setTargetCode(targetCode);
			List<TarRegQue> tarRegQueListOld = tarRegQueService.findAll(tarRegQueQuery);
			for (TarRegQue tarRegQue : tarRegQueListOld) {
				tarRegQueService.deleteByPrimaryKey(tarRegQue.getTrqId());
			}
			for (Long qdId1 : qdId) {
				TarRegQue tarRegQue = new TarRegQue();
				tarRegQue.setQdId(qdId1);
				tarRegQue.setRegId(record.getRegId().longValue());
				tarRegQue.setTempId(tempId);
				tarRegQue.setTargetCode(targetCode);
				tarRegQue.setCreateId(SessionContextUtils.getLoginName().toString());
				tarRegQue.setCreateTime(new Date());
				tarRegQue.setModifyTime(new Date());
				tarRegQue.setModifierId(SessionContextUtils.getLoginName().toString());
				tarRegQue.setOpType("A");
				tarRegQue.setSubCode(subCode);
				tarRegQueService.insert(tarRegQue);
			}
		}
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(TarReg tarReg) {
		List<TarReg> deleteList = tarRegService.findAll(tarReg);
		for (TarReg tarReg2 : deleteList) {
			tarRegService.deleteByPrimaryKey(tarReg2.getTrId());
		}
	}

	@RequestMapping(value = "listQueryDim")
	@ResponseBody
	public List<QueryDim> listQueryDim(String targetCode, TarRegQue tarRegQue) {
		List<QueryDim> queryDimList = queryDimService.findByTargetCode(targetCode);
		List<TarRegQue> tarRegQueList = tarRegQueService.findAll(tarRegQue);
		Set<Long> qdIds = new HashSet<Long>();
		for (TarRegQue tarRegQue2 : tarRegQueList) {
			qdIds.add(tarRegQue2.getQdId());
		}
		for (QueryDim queryDim : queryDimList) {
			if (qdIds.contains(Long.valueOf(queryDim.getQdId()))) {
				queryDim.setChecked(true);
			} else {
				queryDim.setChecked(false);
			}
		}
		return queryDimList;
	}
}
