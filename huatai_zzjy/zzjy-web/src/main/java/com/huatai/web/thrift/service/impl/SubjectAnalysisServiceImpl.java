package com.huatai.web.thrift.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.bean.OrgBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.mapper.BaseMapper;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.GroDimMapper;
import com.huatai.web.mapper.InterFieldMapper;
import com.huatai.web.mapper.InterMapper;
import com.huatai.web.mapper.QueryDimDetailMapper;
import com.huatai.web.mapper.QueryDimMapper;
import com.huatai.web.mapper.RoleTargetMapper;
import com.huatai.web.mapper.SQLMapper;
import com.huatai.web.mapper.SetDefaultTarMapper;
import com.huatai.web.mapper.SqlAliasMapper;
import com.huatai.web.mapper.TarQueryDimMapper;
import com.huatai.web.mapper.TarRegMapper;
import com.huatai.web.mapper.TarRegQueMapper;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.mapper.TarRegTabHeadMapper;
import com.huatai.web.mapper.TarStatisMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.mapper.TemRegMapper;
import com.huatai.web.mapper.TempletMapper;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;
import com.huatai.web.model.GroDimExample;
import com.huatai.web.model.Inter;
import com.huatai.web.model.InterExample;
import com.huatai.web.model.InterField;
import com.huatai.web.model.InterFieldExample;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;
import com.huatai.web.model.QueryDimExample;
import com.huatai.web.model.RoleTarget;
import com.huatai.web.model.RoleTargetExample;
import com.huatai.web.model.SetDefaultTar;
import com.huatai.web.model.SetDefaultTarExample;
import com.huatai.web.model.SqlAlias;
import com.huatai.web.model.SqlAliasExample;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarQueryDimExample;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarRegSqlExample;
import com.huatai.web.model.TarRegTabHead;
import com.huatai.web.model.TarRegTabHeadExample;
import com.huatai.web.model.TarStatis;
import com.huatai.web.model.TarStatisExample;
import com.huatai.web.model.Target;
import com.huatai.web.model.TargetExample;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;
import com.huatai.web.model.Templet;
import com.huatai.web.model.TempletExample;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.thrift.service.SubjectAnalysisService;

@Service
public class SubjectAnalysisServiceImpl implements SubjectAnalysisService {

	@Autowired
	private TempletMapper templetMapper;
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private GroDimMapper groDimMapper;
	@Autowired
	private GroDimDetailMapper groDimDetailMapper;
	@Autowired
	private TarRegMapper tarRegMapper;
	@Autowired
	private TemRegMapper temRegMapper;
	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;
	@Autowired
	private SqlAliasMapper sqlAliasMapper;
	@Autowired
	private InterMapper interMapper;
	@Autowired
	private InterFieldMapper interFieldMapper;
	@Autowired
	private QueryDimMapper queryDimMapper;
	@Autowired
	private QueryDimDetailMapper queryDimDetailMapper;
	@Autowired
	private TarQueryDimMapper tarQueryDimMapper;
	@Autowired
	private SetDefaultTarMapper setDefaultTarMapper;
	@Autowired
	private RoleTargetMapper roleTargetMapper;
	@Autowired
	private TarRegTabHeadMapper tarRegTabHeadMapper;
	@Autowired
	private TarStatisMapper tarStatisMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private SQLMapper sqlMapper;
	@Autowired
	private TarRegQueMapper tarRegQueMapper;

	private CommonUtil commonUtil;

	public SubjectAnalysisServiceImpl() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	@Override
	public List<Templet> findAllTemplet() {
		TempletExample templetExample = new TempletExample();
		templetExample.createCriteria().andOpTypeNotEqualTo("D");
		List<Templet> templet = this.templetMapper.selectByExample(templetExample);
		if (null != templet) {
			return templet;
		}
		return null;
	}

	public Target findTargetByCode(String code) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(code);
		List<Target> targets = this.targetMapper.selectByExample(targetExample);
		if (null != targets && targets.size() == 1) {
			return targets.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询分组维度详细按分组维度id}
	 * @作者 MaxBill
	 * @时间 2017年7月18日 上午15:27:20
	 */
	public List<GroDimDetail> findGroDimDetailListByGdid(String code) {
		GroDimExample groDimExample = new GroDimExample();
		groDimExample.createCriteria().andOpTypeNotEqualTo("D").andGroDimTypeCodeEqualTo(code);
		GroDimDetailExample groDimDetailExample = new GroDimDetailExample();
		groDimDetailExample.createCriteria().andOpTypeNotEqualTo("D")
				.andGdIdEqualTo(this.groDimMapper.selectByExample(groDimExample).get(0).getGdId());
		return this.groDimDetailMapper.selectByExample(groDimDetailExample);
	}

	/**
	 * @功能 {按级查询默认指标}
	 * @作者 MaxBill
	 * @时间 2017年7月18日 下午4:20:09
	 */
	public Target findDefaultTargetByLevelGroup(String pid, String type) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andOpTypeNotEqualTo("D").andParentCodeEqualTo(pid).andTargetTypeEqualTo(type);
		targetExample.setOrderByClause("SORT ASC");
		List<Target> targets = this.targetMapper.selectByExample(targetExample);
		if (null != targets && targets.size() >= 1) {
			return targets.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按级查询默认指标}
	 * @作者 MaxBill
	 * @时间 2017年7月18日 下午4:20:09
	 */
	public Target findDefaultTargetByLevel(String pid, String type, String dateType) {
		List<Target> targets = this.targetMapper.findDefaultTargetByLevel(pid, type, dateType);
		if (null != targets && targets.size() >= 1) {
			return targets.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按级查询默认指标}
	 * @作者 MaxBill
	 * @时间 2017年7月18日 下午4:20:09
	 */
	public List<Target> findDefaultTargetByLevelByTop(String pid, String type, int size) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andOpTypeNotEqualTo("D").andParentCodeEqualTo(pid).andTargetTypeEqualTo(type);
		targetExample.setOrderByClause("SORT ASC");
		List<Target> targets = this.targetMapper.selectByExample(targetExample);
		if (null != targets) {
			if (targets.size() < size) {
				return targets;
			} else {
				return targets.subList(0, size - 1);
			}
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按模板code查询区域}
	 * @作者 MaxBill
	 * @时间 2017年8月18日 上午11:18:12
	 */
	public List<String> findRegionByTemp(String temp) {
		List<String> regList = new ArrayList<String>();
		List<TemReg> temRegList = this.temRegMapper.findRegionByTemp(temp);
		if (null != temRegList) {
			temRegList.stream().forEach(temReg -> {
				regList.add(temReg.getRegCode());
			});
		}
		return regList;
	}

	/**
	 * @功能 {按code查询区域}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:16:19
	 */
	public TemReg findRegionByCode(String regCode) {
		TemRegExample temRegExample = new TemRegExample();
		temRegExample.createCriteria().andOpTypeNotEqualTo("D").andRegCodeEqualTo(regCode);
		List<TemReg> temRegs = this.temRegMapper.selectByExample(temRegExample);
		if (null != temRegs && temRegs.size() == 1) {
			return temRegs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按指标和区域查询TarReg}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:33:06
	 */
	public TarReg findTarReg(String tar, Integer regId) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId);
		List<TarReg> tarRegs = this.tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegs && tarRegs.size() == 1) {
			return tarRegs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按SQLid查询SqlAlias}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午11:13:45
	 */
	public SqlAlias findSqlAliasBySqlId(Integer sqlId) {
		SqlAliasExample sqlAliasExample = new SqlAliasExample();
		sqlAliasExample.createCriteria().andOpTypeNotEqualTo("D").andTrsIdEqualTo(sqlId);
		List<SqlAlias> sqlAliasList = this.sqlAliasMapper.selectByExample(sqlAliasExample);
		if (null != sqlAliasList && sqlAliasList.size() == 1) {
			return sqlAliasList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {那表名查询 接口}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午11:18:09
	 */
	public Inter findInterByTableName(String tableNmae) {
		InterExample interExample = new InterExample();
		interExample.createCriteria().andOpTypeNotEqualTo("D").andInterNameEqualTo(tableNmae);
		List<Inter> inters = this.interMapper.selectByExample(interExample);
		if (null != inters && inters.size() == 1) {
			return inters.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @功能 {按维度和接口i查询InterField}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午12:16:03
	 */
	public InterField findInterFieldByInterAndDim(Integer interId, String dim) {
		InterFieldExample interFieldExample = new InterFieldExample();
		interFieldExample.createCriteria().andOpTypeNotEqualTo("D").andInterIdEqualTo(interId).andDimCodeEqualTo(dim);
		List<InterField> InterFieldList = this.interFieldMapper.selectByExample(interFieldExample);
		if (null != InterFieldList && InterFieldList.size() == 1) {
			return InterFieldList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按照主题和区域查询}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 下午5:53:42
	 */
	public List<TarReg> findTarRegsBySubAndReg(String sub, Integer regId) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId).andSubCodeEqualTo(sub);
		return this.tarRegMapper.selectByExample(tarRegExample);
	}

	/**
	 * @功能 {按主题、区域、角色查询模板数据}
	 * @作者 MaxBill
	 * @时间 2017年8月2日 下午2:54:11
	 */
	public List<TarReg> findTarRegsBySubAndRegAndRole(String sub, Integer regId, String role) {
		return this.tarRegMapper.findTarRegsBySubAndRegAndRole(sub, regId, role);
	}

	/**
	 * @功能 {按查询维度编码查询QueryDimDetail}
	 * @作者 MaxBill
	 * @时间 2017年7月20日 上午9:53:59
	 */
	public List<QueryDimDetail> findQueryDimDetailByQDcode(String dimCode) {
		QueryDimExample queryDimExample = new QueryDimExample();
		queryDimExample.createCriteria().andOpTypeNotEqualTo("D").andQueryDimCodeEqualTo(dimCode);
		List<QueryDim> QueryDimList = this.queryDimMapper.selectByExample(queryDimExample);
		if (null != QueryDimList && QueryDimList.size() == 1) {
			QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
			queryDimDetailExample.createCriteria().andOpTypeNotEqualTo("D")
					.andQdIdEqualTo(QueryDimList.get(0).getQdId());
			queryDimDetailExample.setOrderByClause("QDD_ID ASC");
			List<QueryDimDetail> QueryDimDetailList = this.queryDimDetailMapper.selectByExample(queryDimDetailExample);
			return QueryDimDetailList;
		} else {
			return null;
		}
	}

	// 按RegId查询TarReg
	@Override
	public List<TarReg> findtagetCodeByRegionId(Integer regId) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andTrIdEqualTo(regId);
		List<TarReg> tarRegList = this.tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList) {
			return tarRegList;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按主题和区域查询指标信息}
	 * @作者 MaxBill
	 * @时间 2017年7月24日 上午11:12:20
	 */
	public List<TarReg> findTarRegBySubAndReg(String subject, Integer regId) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andSubCodeEqualTo(subject).andRegIdEqualTo(regId);
		return this.tarRegMapper.selectByExample(tarRegExample);
	}

	/**
	 * @功能 {查询指标分级}
	 * @作者 MaxBill
	 * @时间 2017年7月24日 下午1:44:59
	 */
	public List<Target> findTargetLevel(String subject, String type) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andOpTypeNotEqualTo("D").andParentCodeEqualTo(subject)
				.andTargetTypeEqualTo(type);
		return this.targetMapper.selectByExample(targetExample);
	}

	/**
	 * @功能 {按类型查询板块或主题（默认关联角色）}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 下午5:59:17
	 */
	public List<TargetBean> findSubjectByTypeAndRole(String isplate, String role, String isRealtime) {
		List<Target> targetList = targetMapper.findSubjectByTypeAndRole(isplate, role, isRealtime);
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		if (null != targetBeanList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setCode(target.getTargetCode());
				targetBean.setName(target.getTargetName());
				targetBean.setSort(target.getSort());
				targetBeanList.add(targetBean);
			}
		}
		return targetBeanList;
	}

	/**
	 * @功能 {按类型查询指标信息（默认关联角色，且是已经配置模板的）}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 下午5:59:17
	 */
	public List<TargetBean> findTargetByTypeAndRole(String type, String role, String pid) {
		List<Target> targetList = targetMapper.findTargetByTypeAndRole(type, role, pid);
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		if (null != targetList) {
			switch (type) {
			// 一级指标
			case "1":
				for (Target target : targetList) {
					TargetBean targetBean = new TargetBean();
					targetBean.setName(target.getTargetName());
					targetBean.setChecked(false);
					targetBeanList.add(targetBean);
				}
				break;
			// 二级指标
			case "2":
				for (Target target : targetList) {
					TargetBean targetBean = new TargetBean();
					targetBean.setCode(target.getTargetCode());
					targetBean.setName(target.getTargetName());
					List<Target> twoTargetList = targetMapper.findTargetByTypeAndRole("3", role,
							target.getTargetCode());
					List<TargetBean> twoTargetBeanList = new ArrayList<TargetBean>();
					if (null != twoTargetList) {
						for (Target twoTarget : twoTargetList) {
							TargetBean twoTargetBean = new TargetBean();
							twoTargetBean.setCode(twoTarget.getTargetCode());
							twoTargetBean.setName(twoTarget.getTargetName());
							twoTargetBean.setChecked(false);
							twoTargetBeanList.add(twoTargetBean);
						}
					}
					targetBean.setTarget(twoTargetBeanList);
					targetBeanList.add(targetBean);
				}
				break;
			}
		}
		return targetBeanList;
	}

	/**
	 * @功能 {按类型查询指标信息（默认关联角色，且是已经配置模板的）}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 下午5:59:17
	 */
	public List<TargetBean> findTargetByTypeAndRoleAndReg(String type, String role, String pid, Integer reg) {
		List<Target> targetList = targetMapper.findTargetByTypeAndRoleAndReg(type, role, pid, reg);
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setCode(target.getTargetCode());
				targetBean.setName(target.getTargetName());
				targetBeanList.add(targetBean);
			}
		}
		return targetBeanList;
	}

	/**
	 * @功能 {查询指标查询维度}
	 * @作者 MaxBill
	 * @时间 2017年8月3日 下午5:12:28
	 */
	public List<TarQueryDim> findTarQueryDim(String targetCode, String queryCode) {
		QueryDimExample queryDimExample = new QueryDimExample();
		queryDimExample.createCriteria().andOpTypeNotEqualTo("D").andQueryDimCodeEqualTo(queryCode);
		List<QueryDim> queryDimList = this.queryDimMapper.selectByExample(queryDimExample);
		if (null != queryDimList && queryDimList.size() == 1) {
			int qdId = queryDimList.get(0).getQdId();
			TarQueryDimExample tarQueryDimExample = new TarQueryDimExample();
			tarQueryDimExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
					.andQdIdEqualTo(qdId);
			return this.tarQueryDimMapper.selectByExample(tarQueryDimExample);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {保存用户默认指标}
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午3:02:04
	 */
	@Transactional
	public int saveUserDefTarget(List<String> targetList, String user, String role, String subject, String level) {
		if (null == level) {
			level = "";
		}
		int addFlag = 1;
		List<SetDefaultTar> defaultTarList = this.setDefaultTarMapper.findDefaultTarByTempAndSubAndUser("TEMP03",
				subject, user, role, level);
		if (null != defaultTarList) {
			// 删除原有的默认指标
			for (SetDefaultTar setDefaultTar : defaultTarList) {
				setDefaultTar.setModifyTime(new Date());
				setDefaultTar.setOpType("D");
				this.setDefaultTarMapper.updateByPrimaryKeySelective(setDefaultTar);
			}
		}
		for (String targetCode : targetList) {
			SetDefaultTar setDefaultTar = new SetDefaultTar();
			setDefaultTar.setUsername(user);
			setDefaultTar.setRoleCode(role);
			setDefaultTar.setSubCode(subject);
			setDefaultTar.setTempCode("TEMP03");
			setDefaultTar.setTargetCode(targetCode);
			setDefaultTar.setTargetGroCode(level);
			setDefaultTar.setCreateTime(new Date());
			setDefaultTar.setOpType("A");
			int temp = this.setDefaultTarMapper.insertSelective(setDefaultTar);
			if (temp == 0) {
				addFlag = 0;
			}
		}
		return addFlag;
	}

	/**
	 * @功能 {按主题、角色查询模板数据}
	 * @作者 MaxBill
	 * @时间 2017年8月17日 上午11:30:41
	 */
	public List<TarReg> findTarRegsBySubAndRole(String temp, String sub, String role) {
		return this.tarRegMapper.findTarRegsBySubAndRole(sub, role, temp);
	}

	/**
	 * @功能 {按主题}
	 * @作者 MaxBill
	 * @时间 2017年8月17日 下午5:21:48
	 */
	public TarReg findTarRegSubAndTar(String sub, String tar) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andSubCodeEqualTo(sub).andTargetCodeEqualTo(tar);
		List<TarReg> tarRegList = this.tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList && tarRegList.size() == 1) {
			return tarRegList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据角色筛选指标
	 */
	@Override
	public RoleTarget findtargetByrole(String role, String targetStr) {
		RoleTargetExample roleTargetExample = new RoleTargetExample();
		roleTargetExample.createCriteria().andOpTypeNotEqualTo("D").andRoleCodeEqualTo(role)
				.andTargetCodeEqualTo(targetStr);
		List<RoleTarget> targetList = roleTargetMapper.selectByExample(roleTargetExample);
		if (null != targetList && targetList.size() == 1) {
			return targetList.get(0);
		}
		return null;
	}

	/**
	 * 查询某个角色在其主题下的某个区域的指标
	 */
	@Override
	public List<Target> findTargetByRoleAndType(String role, String type, String blockCode, String parentCode) {
		return this.targetMapper.findTargetByRoleAndType(role, type, blockCode, parentCode);
	}

	@Override
	public List<Target> findTargetByRoleAndTypeAndReg(String role, String type, String blockCode, Integer regId) {
		return this.targetMapper.findTargetByRoleAndTypeAndReg(role, type, blockCode, regId);
	}

	/**
	 * 查询指标的最低权限机构
	 */
	@Override
	public TarReg findOrgByTargtAndRen(String targetCode, String subject, Integer regId) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andSubCodeEqualTo(subject)
				.andTargetCodeEqualTo(targetCode).andRegIdEqualTo(regId);
		List<TarReg> tarRegList = tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList && tarRegList.size() == 1) {
			return tarRegList.get(0);
		}
		return null;
	}

	/**
	 * @功能 {按角色机构代码和主题查询区域信息}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午1:09:17
	 */
	public List<String> findRegionByRoleorgAndSubject(String temp, String roleOrg, String subject, String role) {
		List<String> regList = new ArrayList<String>();
		Set<Integer> regSet = new HashSet<Integer>();
		List<TarReg> tarRegListAll = this.tarRegMapper.findRegionBySubAndRole(subject, role, temp);
		List<TarReg> resultTarRegList = new ArrayList<TarReg>();
		if (null != tarRegListAll) {
			for (TarReg tarReg : tarRegListAll) {
				String roleOrgType = tarReg.getRoleOrgType();
				// 按最低权限类型过滤
				if (commonUtil.hasRoleOrg(Integer.parseInt(roleOrgType), roleOrg.length())) {
					resultTarRegList.add(tarReg);
				}
			}
			if (null != resultTarRegList) {
				for (TarReg tarReg : resultTarRegList) {
					regSet.add(tarReg.getRegId());
				}
			}
		}
		if (!regSet.isEmpty()) {
			Iterator<Integer> it = regSet.iterator();
			while (it.hasNext()) {
				TemReg temReg = this.temRegMapper.selectByPrimaryKey(it.next());
				regList.add(temReg.getRegCode());
			}
		}
		return regList;
	}

	/**
	 * @功能 {按区域获取表格头部信息}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 下午7:16:30
	 */
	public List<String> findTarRegTabHeadByReg(Integer regId) {
		List<String> headList = new ArrayList<String>();
		TarRegTabHeadExample tarRegTabHeadExample = new TarRegTabHeadExample();
		tarRegTabHeadExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId);
		List<TarRegTabHead> tarRegTabHeadList = this.tarRegTabHeadMapper.selectByExample(tarRegTabHeadExample);
		if (null != tarRegTabHeadList) {
			for (TarRegTabHead obj : tarRegTabHeadList) {
				headList.add(obj.getColName());
			}
		}
		return headList;
	}

	/**
	 * @功能 {按区域和指标获取表格头部信息}
	 * @作者 MaxBill
	 * @时间 2017年8月30日 上午10:15:47
	 */
	public List<String> findTarRegTabHeadByRegAndTar(Integer regId, String target) {
		List<String> headList = new ArrayList<String>();
		TarRegTabHeadExample tarRegTabHeadExample = new TarRegTabHeadExample();
		tarRegTabHeadExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId)
				.andTargetCodeEqualTo(target);
		tarRegTabHeadExample.setOrderByClause("SORT ASC");
		List<TarRegTabHead> tarRegTabHeadList = this.tarRegTabHeadMapper.selectByExample(tarRegTabHeadExample);
		if (null != tarRegTabHeadList) {
			for (TarRegTabHead obj : tarRegTabHeadList) {
				headList.add(obj.getColName());
			}
		}
		return headList;
	}

	/**
	 * 根据角色查询其主题下的板块
	 */
	public List<Target> findTargetBysubAndTypeBack(String role, String type, String sub) {
		return this.targetMapper.findTargetBysubAndTypeBack(role, type, sub);
	}

	/**
	 * 根据角色查询其主题下的板块
	 */
	public List<Target> findTargetBysubAndType(String role, String type, String sub, String dateType) {
		return this.targetMapper.findTargetBysubAndType(role, type, sub, dateType);
	}

	/**
	 * @功能 {分析管理导出清单}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 上午10:40:20
	 */
	public TarReg findTarRegByRegAndSub(Integer regId, String subject) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId).andSubCodeEqualTo(subject);
		List<TarReg> tarRegList = this.tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList && tarRegList.size() == 1) {
			return tarRegList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按指标、区域、维度查询TarRegSql}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:34:58
	 */
	public TarRegSql findTarRegSql(int type, String tar, Integer regId, String groupType, String groupDetail,
			String dateType) {
		TarRegSqlExample tarRegSqlExample = new TarRegSqlExample();
		switch (type) {
		case 1:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andGroupDetailCodeEqualTo(groupDetail).andDateTypeEqualTo(dateType);
			break;
		case 2:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andGroupDetailCodeEqualTo(groupDetail);
			break;
		case 3:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andDateTypeEqualTo(dateType);
			break;
		case 4:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupDetailCodeEqualTo(groupDetail);
			break;
		case 5:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andDateTypeEqualTo(dateType);
			break;
		case 6:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId);
			break;
		}
		List<TarRegSql> tarRegSqls = this.tarRegSqlMapper.selectByExample(tarRegSqlExample);
		if (null != tarRegSqls && tarRegSqls.size() == 1) {
			return tarRegSqls.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按code查询指标名称}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午10:31:00
	 */
	public String findTargetNameByCode(String code) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(code);
		List<Target> target = this.targetMapper.selectByExample(targetExample);
		if (null != target && target.size() == 1) {
			return target.get(0).getTargetName();
		}
		return null;
	}

	/**
	 * @功能 {按区域、指标查询}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 上午10:45:16
	 * 
	 */
	public TarReg findTarRegByRegAndSubAndTar(Integer regId, String subject, String targetCode) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
				.andSubCodeEqualTo(subject).andRegIdEqualTo(regId);
		List<TarReg> tarRegList = tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList && tarRegList.size() == 1) {
			return tarRegList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按主题查询区间统计的指标}
	 * @作者 MaxBill
	 * @时间 2017年9月8日 下午3:48:22
	 */
	public List<TarStatis> findTarStatisBySub(String subject) {
		TarStatisExample tarStatisExample = new TarStatisExample();
		tarStatisExample.createCriteria().andOpTypeNotEqualTo("D").andSubCodeEqualTo(subject);
		return this.tarStatisMapper.selectByExample(tarStatisExample);
	}

	/**
	 * @功能 {按角色、部门、机构、区域查询指标}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午5:23:00
	 */
	public List<String> findTargetByRegAndRoleAndOrgAndDeptAndSubject(Integer regId, String role, String roleOrg,
			String roleDept, String subject) {
		List<String> targetList = new ArrayList<String>();
		List<TarReg> tarRegList = tarRegMapper.findTarRegsByRegionAndSubject(regId, role, roleDept, subject);
		if (null != tarRegList) {
			for (TarReg tarReg : tarRegList) {
				targetList.add(tarReg.getTargetCode());
			}
		}
		return targetList;
	}

	/**
	 * @功能 {按角色查询指标列表}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 上午11:09:54
	 */
	public List<TargetBean> findTargetByRoleAndSubAndRegWithTarReg(String role, String sub, Integer reg,
			String dateType) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = this.targetMapper.findTargetByRoleAndSubAndRegWithTarReg(role, sub, reg, dateType);
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBean.setChecked(false);
				targetBeanList.add(targetBean);
			}
			return targetBeanList;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按角色查询指标列表}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 上午11:09:54
	 */
	public List<TargetBean> findTargetByRoleAndSubAndRegWithTarRegBack(String role, String sub, Integer reg) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = this.targetMapper.findTargetByRoleAndSubAndRegWithTarRegBack(role, sub, reg);
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBean.setChecked(false);
				targetBeanList.add(targetBean);
			}
			return targetBeanList;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按区域、指标查询}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 上午10:45:16
	 */
	public TarReg findTarRegByRegAndTarget(Integer regId, String targetCode) {
		TarRegExample tarRegExample = new TarRegExample();
		tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode).andRegIdEqualTo(regId);
		List<TarReg> tarRegList = tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList && tarRegList.size() == 1) {
			return tarRegList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询机构}
	 * @作者 MaxBill
	 * @时间 2017年9月11日 下午1:08:11
	 */
	public List<OrgBean> getOrgList(int type, String code, String roleDept) {
		List<OrgBean> orgBeanList = new ArrayList<OrgBean>();
		List<HashMap<String, Object>> mapData = null;
		switch (type) {
		case 1:
			mapData = this.baseMapper.getCompanyByManage(code);
			break;
		case 2:
			mapData = this.sqlMapper.getBranchByCompany(code);
			break;
		case 3:
			if (commonUtil.isGeYeAndPeiXunDept(roleDept)) {
				mapData = this.baseMapper.getServerByBranch(code);
			} else {
				mapData = this.baseMapper.getCenterByBranch(code);
			}
			break;
		case 41:
			mapData = this.baseMapper.getRegionByServer(code);
			break;
		case 42:
			mapData = this.baseMapper.getRegionByCenter(code);
			break;
		case 5:
			mapData = this.baseMapper.getSectionByRegion(code);
			break;
		case 6:
			mapData = this.baseMapper.getGroupsBySection(code);
			break;
		case 7:
			mapData = this.baseMapper.getSellersByGroups(code);
			break;
		}
		if (null != mapData) {
			for (HashMap<String, Object> map : mapData) {
				String orgCode = (String) map.get("CODE");
				String orgName = (String) map.get("NAME");
				OrgBean org = new OrgBean();
				org.setName(orgName);
				org.setCode(orgCode);
				orgBeanList.add(org);
			}
		}
		return orgBeanList;
	}

	/**
	 * @功能 {按主题、模板、指标、查询维度查询TarRegQue}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午2:25:04
	 */
	public TarRegQue findTarRegQueBySubAndTarAndTempAndQue(String sub, String target, String temp, String que) {
		List<TarRegQue> tarRegQueList = this.tarRegQueMapper.findTarRegQueBySubAndTarAndTempAndQue(sub, target, temp,
				que);
		if (null != tarRegQueList && tarRegQueList.size() == 1) {
			return tarRegQueList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按主题、模板查询TarRegQue}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午3:13:14
	 */
	public List<TarRegQue> findTarRegQueBySubAndTemp(String sub, String temp) {
		return this.tarRegQueMapper.findTarRegQueBySubAndTemp(sub, temp);
	}

	/**
	 * @功能 {按id查询查询维度}
	 * @作者 MaxBill
	 * @时间 2017年9月15日 下午3:31:51
	 */
	public QueryDim findQueryDimById(Integer id) {
		QueryDim queryDim = this.queryDimMapper.selectByPrimaryKey(id);
		if (null != queryDim) {
			QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
			queryDimDetailExample.createCriteria().andOpTypeNotEqualTo("D").andQdIdEqualTo(id);
			queryDimDetailExample.setOrderByClause("QDD_ID ASC");
			List<QueryDimDetail> QueryDimDetailList = this.queryDimDetailMapper.selectByExample(queryDimDetailExample);
			queryDim.setQueryDimDetail(QueryDimDetailList);
			return queryDim;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按模板和主题查询用户设置的默认指标}
	 * @作者 MaxBill
	 * @时间 2017年9月23日 下午4:57:17
	 */
	public List<String> findDefaultTarByTempAndSubAndUser(String temp, String sub, String username, String role,
			String level) {
		List<String> targetList = new ArrayList<String>();
		List<SetDefaultTar> defaultTarList = this.setDefaultTarMapper.findDefaultTarByTempAndSubAndUser(temp, sub,
				username, role, level);
		if (null != defaultTarList) {
			for (SetDefaultTar setDefaultTar : defaultTarList) {
				targetList.add(setDefaultTar.getTargetCode());
			}
			return targetList;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 查询角色板块下的默认指标
	 */
	@Override
	public List<String> findRoleDefaultTarget(String role, String userName, String subject) {
		SetDefaultTarExample setDefaultTargetExample = new SetDefaultTarExample();
		setDefaultTargetExample.createCriteria().andOpTypeNotEqualTo("D").andSubCodeEqualTo(subject)
				.andRoleCodeEqualTo(role).andUsernameEqualTo(userName);
		List<SetDefaultTar> setDefault = this.setDefaultTarMapper.selectByExample(setDefaultTargetExample);
		List<String> targetCode = new ArrayList<String>();
		if (null != setDefault) {
			for (SetDefaultTar setDefaultTar : setDefault) {
				targetCode.add(setDefaultTar.getTargetCode());
			}
		}
		return targetCode;
	}

	/**
	 * 
	 * 根据图形类型查询指标
	 */
	@Override
	public List<Target> findTargetByRoleAndBlockAndType(String role, Integer reId, String type, String parentCode) {
		return this.targetMapper.findTargetByRoleAndBlockAndType(role, reId, parentCode, type);
	}

	/**
	 * 判断指标是否默认
	 */
	public boolean isTarSetDefault(String temp, String subject, String targetCode, String role, String opType) {
		SetDefaultTarExample example = new SetDefaultTarExample();
		example.createCriteria().andOpTypeNotEqualTo("D").andTempCodeEqualTo(temp).andTargetCodeEqualTo(targetCode)
				.andRoleCodeEqualTo(role).andOpTypeNotEqualTo(opType);
		List<SetDefaultTar> sdfList = setDefaultTarMapper.selectByExample(example);
		return sdfList.size() > 0 ? true : false;
	}

	/**
	 * 分组指标信息
	 */
	public Target findPTarInfoByTar(String targetCode) {
		TargetExample example = new TargetExample();
		example.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode);
		Target tar = targetMapper.selectByExample(example).get(0);
		TargetExample exampleP = new TargetExample();
		exampleP.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar.getParentCode());
		return targetMapper.selectByExample(exampleP).get(0);
	}

	/**
	 * 
	 * 根据角色板块查询二级指标分组
	 */
	@Override
	public List<Target> findTargetByTypeAndRoleAndBlock(String type, String role, String block) {
		return targetMapper.findTargetByTypeAndRole(type, role, block);
	}

	@Override
	public List<TarReg> findMoreTarRegsBySubAndRole(List<String> temps, String subject, String role,
			String groupbyDate) {
		return this.tarRegMapper.findMoreTarRegsBySubAndRole(temps, subject, role, groupbyDate);
	}

	/**
	 * 判断是否是默认指标
	 */
	@Override
	public boolean isdefaultTarget(String username, String target, String block, String role) {
		SetDefaultTarExample setDefaultTarExample = new SetDefaultTarExample();
		setDefaultTarExample.createCriteria().andOpTypeNotEqualTo("D").andUsernameEqualTo(username)
				.andTargetCodeEqualTo(target).andSubCodeEqualTo(block).andRoleCodeEqualTo(role);
		List<SetDefaultTar> list = this.setDefaultTarMapper.selectByExample(setDefaultTarExample);
		return list.size() > 0 ? true : false;
	}

	/**
	 * 根据图形类型查找指标
	 */
	@Override
	public List<TargetBean> findTargetByRoleAndSubAndRegAndGraph(String role, String type, Integer reg, String sub) {
		List<Target> targetList = this.targetMapper.findTargetByRoleAndSubAndRegAndGraph(role, type, reg, sub);
		List<TargetBean> targetBeans = new ArrayList<TargetBean>();
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBeans.add(targetBean);
			}
		}
		return targetBeans;
	}

	/**
	 * 根据图形类型查找指标
	 */
	@Override
	public List<TargetBean> findTargetByRoleAndSubAndRegAndGraphType(String role, String type, Integer reg,
			String sub) {
		List<Target> targetList = this.targetMapper.findTargetByRoleAndSubAndRegAndGraphType(role, type, reg, sub);
		List<TargetBean> targetBeans = new ArrayList<TargetBean>();
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBeans.add(targetBean);
			}
		}
		return targetBeans;
	}

	@Override
	public List<Target> findTargetByTypeAndRoleAndPlate(String type, String role, String pid) {
		return this.targetMapper.findTargetByTypeAndRoleAndPlate(type, role, pid);
	}

	@Override
	public List<TarRegSql> findTarRegSqlList(String tar, Integer regId, String dateType, String subcode) {
		TarRegSqlExample tarRegSqlExample = new TarRegSqlExample();
		if (null != tar) {
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andDateTypeEqualTo(dateType);
		}
		if (null != subcode) {
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andSubCodeEqualTo(subcode).andRegIdEqualTo(regId)
					.andDateTypeEqualTo(dateType);
		}
		List<TarRegSql> tarRegSqls = this.tarRegSqlMapper.selectByExample(tarRegSqlExample);
		return tarRegSqls;
	}

}
