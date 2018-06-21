package com.huatai.web.thrift.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.BlockBean;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.OrgBean;
import com.huatai.web.bean.PlateTwoTarGroBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.mapper.BaseMapper;
import com.huatai.web.mapper.CustPlateMapper;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.GroDimMapper;
import com.huatai.web.mapper.PlateBillMapper;
import com.huatai.web.mapper.PlateOneTarMapper;
import com.huatai.web.mapper.PlateTwoTarGroMapper;
import com.huatai.web.mapper.PlateTwoTarMapper;
import com.huatai.web.mapper.QueryDimDetailMapper;
import com.huatai.web.mapper.QueryDimMapper;
import com.huatai.web.mapper.SQLMapper;
import com.huatai.web.mapper.SetDefaultTarMapper;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.mapper.TarInitSqlMapper;
import com.huatai.web.mapper.TarRegMapper;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.mapper.TarRegTabHeadMapper;
import com.huatai.web.mapper.TarStatisMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.mapper.TemRegMapper;
import com.huatai.web.model.CustPlate;
import com.huatai.web.model.CustPlateExample;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;
import com.huatai.web.model.GroDimExample;
import com.huatai.web.model.PlateBill;
import com.huatai.web.model.PlateBillExample;
import com.huatai.web.model.PlateOneTar;
import com.huatai.web.model.PlateOneTarExample;
import com.huatai.web.model.PlateTwoTar;
import com.huatai.web.model.PlateTwoTarExample;
import com.huatai.web.model.PlateTwoTarGro;
import com.huatai.web.model.PlateTwoTarGroExample;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;
import com.huatai.web.model.SetDefaultTar;
import com.huatai.web.model.SetDefaultTarExample;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarGroDimExample;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarInitSqlExample;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;
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
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.thrift.service.BlockService;

/**
 * @功能 {板块业务实现类}
 * @作者 MaxBill
 * @时间 2017年7月24日 下午4:04:50
 */
@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	private CustPlateMapper custPlateMapper;
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private PlateBillMapper plateBillMapper;
	@Autowired
	private TemRegMapper temRegMapper;
	@Autowired
	private TarRegMapper tarRegMapper;
	@Autowired
	private PlateOneTarMapper plateOneTarMapper;
	@Autowired
	private PlateTwoTarGroMapper plateTwoTarGroMapper;
	@Autowired
	private PlateTwoTarMapper plateTwoTarMapper;
	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;
	@Autowired
	private TarInitSqlMapper tarInitSqlMapper;
	@Autowired
	private TarRegTabHeadMapper tarRegTabHeadMapper;
	@Autowired
	private SetDefaultTarMapper setDefaultTarMapper;
	@Autowired
	private GroDimMapper groDimMapper;
	@Autowired
	private GroDimDetailMapper groDimDetailMapper;
	@Autowired
	private TarStatisMapper tarStatisMapper;
	@Autowired
	private TarGroDimMapper tarGroDimMapper;
	@Autowired
	private QueryDimMapper queryDimMapper;
	@Autowired
	private QueryDimDetailMapper queryDimDetailMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private SQLMapper sqlMapper;

	private CommonUtil commonUtil;

	public BlockServiceImpl() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * @功能 {查找板块信息}
	 * @作者 MaxBill
	 * @时间 2017年7月24日 下午4:28:10
	 */
	public CustPlate findCustPlateById(Integer plateId) {
		return this.custPlateMapper.selectByPrimaryKey(plateId);
	}

	/**
	 * @功能 {删除板块信息}
	 * @作者 MaxBill
	 * @时间 2017年7月24日 下午4:29:23
	 */
	@Transactional
	public int deletePlate(Integer plateId) {
		CustPlate custPlate = this.custPlateMapper.selectByPrimaryKey(plateId);
		custPlate.setModifyTime(new Date());
		custPlate.setOpType("D");
		int result = this.custPlateMapper.updateByPrimaryKeySelective(custPlate);
		if (result == 1) {
			// 删除板块下的一级指标
			PlateOneTarExample plateOneTarExample = new PlateOneTarExample();
			plateOneTarExample.createCriteria().andPlateIdEqualTo(plateId);
			List<PlateOneTar> plateOneList = this.plateOneTarMapper.selectByExample(plateOneTarExample);
			if (null != plateOneList) {
				for (PlateOneTar plateOneTar : plateOneList) {
					plateOneTar.setModifyTime(new Date());
					plateOneTar.setOpType("D");
					this.plateOneTarMapper.updateByPrimaryKeySelective(plateOneTar);
				}
			}
			// 删除板块下的二级指标分类
			PlateTwoTarGroExample plateTwoTarGroExample = new PlateTwoTarGroExample();
			plateTwoTarGroExample.createCriteria().andPlateIdEqualTo(plateId);
			List<PlateTwoTarGro> ptgList = this.plateTwoTarGroMapper.selectByExample(plateTwoTarGroExample);
			if (null != ptgList) {
				for (PlateTwoTarGro plateTwoTarGro : ptgList) {
					plateTwoTarGro.setModifyTime(new Date());
					plateTwoTarGro.setOpType("D");
					int resultGro = this.plateTwoTarGroMapper.updateByPrimaryKeySelective(plateTwoTarGro);
					// 删除板块下的二级指标分组下的二级指标
					if (resultGro == 1) {
						PlateTwoTarExample plateTwoTarExample = new PlateTwoTarExample();
						plateTwoTarExample.createCriteria().andPttgIdEqualTo(plateTwoTarGro.getPttgId());
						List<PlateTwoTar> plateTwoList = this.plateTwoTarMapper.selectByExample(plateTwoTarExample);
						if (null != plateTwoList) {
							for (PlateTwoTar plateTwoTar : plateTwoList) {
								plateTwoTar.setModifyTime(new Date());
								plateTwoTar.setOpType("D");
								this.plateTwoTarMapper.updateByPrimaryKeySelective(plateTwoTar);
							}
						}
					}
				}
			}
			// 删除设置的默认指标
			SetDefaultTarExample setDefaultTarExample = new SetDefaultTarExample();
			setDefaultTarExample.createCriteria().andSubCodeEqualTo(plateId.toString());
			List<SetDefaultTar> setDefaultTarList = this.setDefaultTarMapper.selectByExample(setDefaultTarExample);
			if (null != setDefaultTarList) {
				for (SetDefaultTar setDefaultTar : setDefaultTarList) {
					setDefaultTar.setModifyTime(new Date());
					setDefaultTar.setOpType("D");
					this.setDefaultTarMapper.updateByPrimaryKeySelective(setDefaultTar);
				}
			}
		}
		return result;
	}

	/**
	 * @功能 {我的板块列表}
	 * @作者 MaxBill
	 * @时间 2017年7月24日 下午4:32:28
	 */
	public DataPageBean myPlateList(Pager pager, String role, String userName, String blockName) {
		Pager<CustPlate> custplate = this.custPlateMapper.findPlateByUser(pager, role, userName, blockName);
		DataPageBean dataPage = new DataPageBean();
		dataPage.setPageSize(custplate.getPageSize());
		dataPage.setCurPage(custplate.getCurrentPage());
		dataPage.setTotal(custplate.getTotalCount());
		dataPage.setTotalPage(custplate.getTotalPage());
		List<BlockBean> blockBeanList = new ArrayList<BlockBean>();
		List<CustPlate> custplateList = custplate.getPageItems();
		for (CustPlate custPlate : custplateList) {
			BlockBean blockBean = new BlockBean();
			blockBean.setBlockId(custPlate.getPlateId());
			blockBean.setBlockName(custPlate.getPlateName());
			blockBeanList.add(blockBean);
		}
		dataPage.setList(blockBeanList);
		return dataPage;

	}

	/**
	 * @功能 {按角色查询板块}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午8:52:46
	 */
	public List<Target> findFixedPlateByRole(String role) {
		return this.targetMapper.findBlockByRole(role);
	}

	/**
	 * @功能 {按code查询指标名称}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午10:31:00
	 */
	public String findTargetNameByCode(String code) {
		TargetExample targetExample = new TargetExample();
		targetExample.createCriteria().andTargetCodeEqualTo(code).andOpTypeNotEqualTo("D");
		List<Target> target = this.targetMapper.selectByExample(targetExample);
		if (null != target && target.size() == 1) {
			return target.get(0).getTargetName();
		}
		return null;
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
	 * @功能 {按模板code查询区域}
	 * @作者 MaxBill
	 * @时间 2017年8月18日 上午11:18:12
	 */
	public List<TemReg> findTemRegByTemp(String temp) {
		return this.temRegMapper.findRegionByTemp(temp);
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
					.andDateTypeEqualTo(dateType);
			break;
		case 5:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupDetailCodeEqualTo(groupDetail);
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
	 * @功能 {按指标、区域、维度查询PlateBill}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:34:58
	 */
	public PlateBill findPlateBillSql(int type, Integer blockId, Integer regId, String groupType, String groupDetail,
			String dateType) {
		PlateBillExample plateBillExample = new PlateBillExample();
		switch (type) {
		case 1:
			plateBillExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andGroupDetailCodeEqualTo(groupDetail).andDateTypeEqualTo(dateType);
			break;
		case 2:
			plateBillExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andGroupDetailCodeEqualTo(groupDetail);
			break;
		case 3:
			plateBillExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andDateTypeEqualTo(dateType);
			break;
		case 4:
			plateBillExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId).andRegIdEqualTo(regId)
					.andDateTypeEqualTo(dateType);
			break;
		case 5:
			plateBillExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId).andRegIdEqualTo(regId)
					.andGroupDetailCodeEqualTo(groupDetail);
			break;
		case 6:
			plateBillExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId)
					.andRegIdEqualTo(regId);
			break;
		}
		List<PlateBill> plateBills = this.plateBillMapper.selectByExample(plateBillExample);
		if (null != plateBills && plateBills.size() == 1) {
			return plateBills.get(0);
		} else {
			return null;
		}
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
	 * @功能 {查询自定义板块}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:11:29
	 */
	public List<CustPlate> findCustPlateByUser(String role, String userName) {
		CustPlateExample custPlateExample = new CustPlateExample();
		custPlateExample.createCriteria().andOpTypeNotEqualTo("D").andRoleCodeEqualTo(role)
				.andUsernameEqualTo(userName);
		custPlateExample.setOrderByClause("CREATE_TIME DESC");
		return this.custPlateMapper.selectByExample(custPlateExample);
	}

	/**
	 * @功能 {查询自定义板块}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:11:29
	 */
	public List<TargetBean> findCustPlateByRole(String role) {
		CustPlateExample custPlateExample = new CustPlateExample();
		custPlateExample.createCriteria().andOpTypeNotEqualTo("D").andRoleCodeEqualTo(role);
		List<CustPlate> custs = this.custPlateMapper.selectByExample(custPlateExample);
		List<TargetBean> targetBeans = new ArrayList();
		if (null != custs) {
			for (CustPlate custPlate : custs) {
				TargetBean targetBean = new TargetBean();
				targetBean.setCode(custPlate.getPlateCode());
				targetBean.setName(custPlate.getPlateName());
				targetBeans.add(targetBean);
			}
		}
		return targetBeans;
	}

	/**
	 * @功能 {按角色查询指标列表}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 上午11:09:54
	 */
	public List<TargetBean> findTargetByRoleAndTypeWithTarReg(String regCode, String role, String roleOrg,
			String type) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		// 按角色查询数据
		List<Target> targetList = this.targetMapper.findTargetByRoleAndTypeWithTarReg(regCode, role, type);
		if (null != targetList) {
			for (Target target : targetList) {
				// 按用户机构过滤无效数据
				TarGroDim tarGroDim = this.findTarGroDimByTar(target.getTargetCode());
				String orgType = findGroDimDetailById(tarGroDim.getGddId()).getGroDimCode();
				if (commonUtil.hasRoleOrg(Integer.parseInt(orgType), roleOrg.length())) {
					TargetBean targetBean = new TargetBean();
					targetBean.setName(target.getTargetName());
					targetBean.setCode(target.getTargetCode());
					targetBean.setDept(target.getDeptCode());
					switch (target.getTargetType()) {
					case "1":
						targetBean.setProps("一级");
						break;
					case "3":
						targetBean.setProps("二级");
						break;
					}
					if (null != target.getChannel()) {
						targetBean.setChannel(target.getChannel());
					} else {
						targetBean.setChannel("");
					}
					targetBean.setChecked(false);
					targetBeanList.add(targetBean);
				}
			}
			return targetBeanList;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按板块id查询一级指标}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:33:03
	 */
	public List<PlateOneTar> findPlateOneTarByBlockWithDate(Integer blockId, String groupbyDate) {
		return this.plateOneTarMapper.findPlateOneTarByBlock(blockId, groupbyDate);
	}

	/**
	 * @功能 {按板块id查询一级指标}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:33:03
	 */
	public List<PlateOneTar> findPlateOneTarByBlock(Integer blockId) {
		PlateOneTarExample plateOneTarExample = new PlateOneTarExample();
		plateOneTarExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId);
		return this.plateOneTarMapper.selectByExample(plateOneTarExample);
	}

	/**
	 * @功能 {按板块id查询二级指标分组}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:33:03
	 */
	public List<PlateTwoTarGro> findPlateTwoTarGroByBlock(Integer blockId) {
		PlateTwoTarGroExample plateTwoTarGroExample = new PlateTwoTarGroExample();
		plateTwoTarGroExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId);
		return this.plateTwoTarGroMapper.selectByExample(plateTwoTarGroExample);
	}

	/**
	 * @功能 {按二级指标组id查询二级指标}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:33:03
	 */
	public List<PlateTwoTar> findPlateTwoTarByGroup(Integer groupId) {
		PlateTwoTarExample platetwoTarExample = new PlateTwoTarExample();
		platetwoTarExample.createCriteria().andOpTypeNotEqualTo("D").andPttgIdEqualTo(groupId);
		return this.plateTwoTarMapper.selectByExample(platetwoTarExample);
	}

	/**
	 * @功能 {按二级指标组id查询二级指标}
	 * @作者 MaxBill
	 * @时间 2017年9月4日 下午7:33:03
	 */
	public List<PlateTwoTar> findPlateTwoTarByGroupWithDate(Integer groupId, String dateType) {
		return this.plateTwoTarMapper.findPlateTwoTarByGroupWithDate(groupId, dateType);
	}

	/**
	 * @功能 {按区域id和主题查询}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午9:45:28
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
	 * @功能 {!!!按指标查询维度最低机构代码}
	 * @作者 MaxBill
	 * @时间 2017年9月7日 下午2:41:15
	 */
	public String findGroDetailOrgCodeByTarget(String target) {
		return this.groDimDetailMapper.findGroDetailCodeByTarget(target).getGroDimCode();
	}

	/**
	 * @功能 {保存自定义板块}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午10:49:45
	 */
	@Transactional
	public int savePlate(CustPlate custPlate, String target1, String target2, String roleOrg) {
		int addFlag = this.custPlateMapper.insertSelective(custPlate);
		Integer blockId = custPlate.getPlateId();
		if (addFlag == 1) {
			// 添加一级指标
			List<String> target1List = JSON.parseArray(target1, String.class);
			if (null != target1List) {
				for (String target1Obj : target1List) {
					String targetCode = target1Obj;
					PlateOneTar plateOneTar = new PlateOneTar();
					plateOneTar.setPlateId(blockId);
					plateOneTar.setTargetCode(targetCode);
					plateOneTar.setCreateTime(new Date());
					plateOneTar.setOpType("A");
					this.plateOneTarMapper.insertSelective(plateOneTar);
				}
			}
			// 添加二级指标组
			List<JSONObject> target2GroupList = JSON.parseArray(target2, JSONObject.class);
			if (null != target2GroupList) {
				for (JSONObject groupObj : target2GroupList) {
					String groupName = groupObj.getString("name");
					List<String> target2List = JSON.parseArray(groupObj.getString("targets"), String.class);
					// 添加二级指标组
					PlateTwoTarGro plateTwoTarGro = new PlateTwoTarGro();
					plateTwoTarGro.setTwoGroupName(groupName);
					plateTwoTarGro.setPlateId(blockId);
					plateTwoTarGro.setCreateTime(new Date());
					plateTwoTarGro.setOpType("A");
					int addResult = this.plateTwoTarGroMapper.insertSelective(plateTwoTarGro);
					int groupId = plateTwoTarGro.getPttgId();
					if (addResult == 1) {
						for (String target2Obj : target2List) {
							String targetCode = target2Obj;
							// 添加二级指标
							PlateTwoTar plateTwoTar = new PlateTwoTar();
							plateTwoTar.setPttgId(groupId);
							plateTwoTar.setTargetCode(targetCode);
							plateTwoTar.setCreateTime(new Date());
							plateTwoTar.setOpType("A");
							this.plateTwoTarMapper.insertSelective(plateTwoTar);
						}
					}
				}
			}
		}
		return addFlag;
	}

	/**
	 * @功能 {编辑自定义板块}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 上午10:49:45
	 */
	@Transactional
	public int editPlate(CustPlate custPlate, String target1, String target2, String roleOrg) {
		int updFlag = this.custPlateMapper.updateByPrimaryKeySelective(custPlate);
		Integer blockId = custPlate.getPlateId();
		if (updFlag == 1) {
			// 删除一级指标
			List<PlateOneTar> plateOneTarList = this.findPlateOneTarByBlock(blockId);
			for (PlateOneTar plateOneTar : plateOneTarList) {
				plateOneTar.setModifyTime(new Date());
				plateOneTar.setOpType("D");
				this.plateOneTarMapper.updateByPrimaryKeySelective(plateOneTar);
			}
			// 添加一级指标
			List<String> target1List = JSON.parseArray(target1, String.class);
			if (null != target1List) {
				for (String target1Obj : target1List) {
					String targetCode = target1Obj;
					PlateOneTar plateOneTar = new PlateOneTar();
					plateOneTar.setPlateId(blockId);
					plateOneTar.setTargetCode(targetCode);
					plateOneTar.setCreateTime(new Date());
					plateOneTar.setOpType("A");
					this.plateOneTarMapper.insertSelective(plateOneTar);
				}
			}
			// 删除二级指标组及其下的二级指标
			List<PlateTwoTarGro> plateTwoTarGroList = this.findPlateTwoTarGroByBlock(blockId);
			if (null != plateTwoTarGroList) {
				for (PlateTwoTarGro plateTwoTarGro : plateTwoTarGroList) {
					plateTwoTarGro.setModifyTime(new Date());
					plateTwoTarGro.setOpType("D");
					int delFlag = this.plateTwoTarGroMapper.updateByPrimaryKeySelective(plateTwoTarGro);
					if (delFlag == 1) {
						// 删除该二级指标组下的二级指标
						List<PlateTwoTar> plateTwoTarList = findPlateTwoTarByGroup(plateTwoTarGro.getPttgId());
						if (null != plateTwoTarList) {
							for (PlateTwoTar plateTwoTar : plateTwoTarList) {
								plateTwoTar.setModifyTime(new Date());
								plateTwoTar.setOpType("D");
								this.plateTwoTarMapper.updateByPrimaryKeySelective(plateTwoTar);
							}
						}
					}
				}
			}
			// 添加二级指标组
			List<JSONObject> target2GroupList = JSON.parseArray(target2, JSONObject.class);
			if (null != target2GroupList) {
				for (JSONObject groupObj : target2GroupList) {
					String groupName = groupObj.getString("name");
					List<String> target2List = JSON.parseArray(groupObj.getString("targets"), String.class);
					// 添加二级指标组
					PlateTwoTarGro plateTwoTarGro = new PlateTwoTarGro();
					plateTwoTarGro.setTwoGroupName(groupName);
					plateTwoTarGro.setPlateId(blockId);
					plateTwoTarGro.setCreateTime(new Date());
					plateTwoTarGro.setOpType("A");
					int addResult = this.plateTwoTarGroMapper.insertSelective(plateTwoTarGro);
					int groupId = plateTwoTarGro.getPttgId();
					if (addResult == 1) {
						for (String target2Obj : target2List) {
							String targetCode = target2Obj;
							// 添加二级指标
							PlateTwoTar plateTwoTar = new PlateTwoTar();
							plateTwoTar.setPttgId(groupId);
							plateTwoTar.setTargetCode(targetCode);
							plateTwoTar.setCreateTime(new Date());
							plateTwoTar.setOpType("A");
							this.plateTwoTarMapper.insertSelective(plateTwoTar);
						}
					}
				}
			}
		}
		return updFlag;
	}

	/**
	 * @功能 {!!!按板块和指标查询一级指标}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午1:22:25
	 */
	public PlateOneTar findPlateOneTarByBlockAndTar(Integer blockId, String target) {
		PlateOneTarExample plateOneTarExample = new PlateOneTarExample();
		plateOneTarExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId)
				.andTargetCodeEqualTo(target);
		List<PlateOneTar> plateOneTarList = this.plateOneTarMapper.selectByExample(plateOneTarExample);
		if (null != plateOneTarList) {
			return plateOneTarList.get(0);
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
		List<SetDefaultTar> defaultTarList = this.setDefaultTarMapper.findDefaultTarByTempAndSubAndUser("TEMP04",
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
			setDefaultTar.setTempCode("TEMP04");
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
	 * @功能 {按分组维度id查询分组维度详细}
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
	 * @功能 {按角色机构代码和主题查询区域信息}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午1:09:17
	 */
	public List<String> findRegionByRoleorgAndSubject(String temp, String roleOrg, String subject, String role) {
		List<String> regList = new ArrayList<String>();
		int userOrgRole;
		if (null == roleOrg || roleOrg.equals("")) {
			userOrgRole = 0;
		} else {
			userOrgRole = roleOrg.length();
		}
		Set<Integer> regSet = new HashSet<Integer>();
		List<TarReg> tarRegListAll = this.tarRegMapper.findTarRegsBySubAndRole(subject, role, temp);
		List<TarReg> resultTarRegList = new ArrayList<>();
		if (null != tarRegListAll) {
			for (TarReg tarReg : tarRegListAll) {
				String roleOrgType = tarReg.getRoleOrgType();
				// 按最低权限类型过滤
				if (commonUtil.hasRoleOrg(Integer.parseInt(roleOrgType), userOrgRole)) {
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
	 * @功能 {查找板块默认一级指标}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午4:45:29
	 */
	public PlateOneTar findBlockDefaultOneTarget(Integer blockId) {
		PlateOneTarExample plateOneTarExample = new PlateOneTarExample();
		plateOneTarExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId);
		plateOneTarExample.setOrderByClause("POT_ID ASC");
		List<PlateOneTar> plateOneTarList = this.plateOneTarMapper.selectByExample(plateOneTarExample);
		if (null != plateOneTarList && plateOneTarList.size() > 0) {
			return plateOneTarList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查找板块默认二级指标分组}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午4:59:45
	 */
	public PlateTwoTarGro findBlockDefaultTwoTarget(Integer blockId) {
		PlateTwoTarGroExample plateTwoTarGroExample = new PlateTwoTarGroExample();
		plateTwoTarGroExample.createCriteria().andOpTypeNotEqualTo("D").andPlateIdEqualTo(blockId);
		plateTwoTarGroExample.setOrderByClause("PTTG_ID ASC");
		List<PlateTwoTarGro> plateTwoTarGroList = this.plateTwoTarGroMapper.selectByExample(plateTwoTarGroExample);
		if (null != plateTwoTarGroList && plateTwoTarGroList.size() > 0) {
			return plateTwoTarGroList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按模板查询区域信息}
	 * @作者 MaxBill
	 * @时间 2017年9月5日 下午8:23:33
	 */
	public List<TemReg> findTemRegListByBlock(Integer blockId) {
		return this.temRegMapper.findTemRegListByBlock(blockId);
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
	 * @功能 {按区域、指标查询}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 上午10:45:16
	 */
	public TarReg findTarRegByRegAndSubAndTar(Integer regId, String subject, String targetCode) {
		TarRegExample tarRegExample = new TarRegExample();
		if (null != subject) {
			tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
					.andSubCodeEqualTo(subject).andRegIdEqualTo(regId);
		} else {
			tarRegExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
					.andRegIdEqualTo(regId);
		}
		List<TarReg> tarRegList = tarRegMapper.selectByExample(tarRegExample);
		if (null != tarRegList && tarRegList.size() == 1) {
			return tarRegList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按code查询指标}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午9:26:40
	 */
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
	 * @功能 {！！！按区域id查询}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午5:24:32
	 */
	public List<TarReg> findTarRegsByRegion(Integer regId, String role, String roleOrg, String roleDept) {
		List<TarReg> tarRegList = tarRegMapper.findTarRegsByRegion(regId, role, roleDept);
		return commonUtil.dealTarRegList(tarRegList, roleOrg);
	}

	/**
	 * @功能 {！！！按区域id、主题查询}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午5:24:32
	 */
	public List<TarReg> findTarRegsByRegionAndSubject(Integer regId, String role, String roleOrg, String roleDept,
			String subject) {
		List<TarReg> tarRegList = tarRegMapper.findTarRegsByRegionAndSubject(regId, role, roleDept, subject);
		return commonUtil.dealTarRegList(tarRegList, roleOrg);
	}

	/**
	 * @功能 {按角色、部门、机构、区域查询指标}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午5:23:00
	 */
	public List<String> findTargetByRegAndRoleAndOrgAndDept(Integer regId, String role, String roleOrg,
			String roleDept) {
		List<String> targetList = new ArrayList<String>();
		List<TarReg> tarRegList = this.findTarRegsByRegion(regId, role, roleOrg, roleDept);
		if (null != tarRegList) {
			for (TarReg tarReg : tarRegList) {
				targetList.add(tarReg.getTargetCode());
			}
		}
		return targetList;
	}

	/**
	 * @功能 {按角色、部门、机构、区域查询指标}
	 * @作者 MaxBill
	 * @时间 2017年8月31日 下午5:23:00
	 */
	public List<String> findTargetByRegAndRoleAndOrgAndDeptAndSubject(Integer regId, String role, String roleOrg,
			String roleDept, String subject) {
		List<String> targetList = new ArrayList<String>();
		List<TarReg> tarRegList = this.findTarRegsByRegionAndSubject(regId, role, roleOrg, roleDept, subject);
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
	 * @功能 {根据角色查询其主题下的板块}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午10:46:17
	 */
	public List<Target> findTargetBysubAndType(String role, String type, String sub, String dateType) {
		return this.targetMapper.findTargetBysubAndType(role, type, sub, dateType);
	}

	/**
	 * @功能 {根据角色查询其主题下的板块}
	 * @作者 MaxBill
	 * @时间 2017年9月6日 上午10:46:17
	 */
	public List<Target> findTargetBysubAndTypeBack(String role, String type, String sub) {
		return this.targetMapper.findTargetBysubAndTypeBack(role, type, sub);
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
		case 43:
			if (commonUtil.isGeYeAndPeiXunDept(roleDept)) {
				mapData = this.baseMapper.getRegionByServer(code);
			} else {
				mapData = this.baseMapper.getRegionByCenter(code);
			}
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
	 * @功能 {查询总区域 的指标}
	 * @作者 MaxBill
	 * @时间 2017年9月16日 下午2:15:40
	 */
	public TarInitSql findFinalTarInitSql(String targetCode) {
		TarInitSqlExample finalTarInitSqlExample = new TarInitSqlExample();
		finalTarInitSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
				.andDateTypeEqualTo("MONTH").andFunIdsEqualTo("01").andGroupTypeEqualTo("");
		List<TarInitSql> tarInitSqlList = this.tarInitSqlMapper.selectByExample(finalTarInitSqlExample);
		if (null != tarInitSqlList && tarInitSqlList.size() == 1) {
			return tarInitSqlList.get(0);
		} else {
			return null;
		}

	}

	/**
	 * @功能 {查询总区域的指标}
	 * @作者 MaxBill
	 * @时间 2017年9月16日 下午2:15:40
	 */
	public TarInitSql findBelowTarInitSql(String targetCode) {
		TarInitSqlExample finalTarInitSqlExample = new TarInitSqlExample();
		finalTarInitSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
				.andGroupTypeEqualTo("ORG_GROUP").andDateTypeEqualTo("MONTH").andFunIdsEqualTo("01");
		List<TarInitSql> tarInitSqlList = this.tarInitSqlMapper.selectByExample(finalTarInitSqlExample);
		if (null != tarInitSqlList && tarInitSqlList.size() == 1) {
			return tarInitSqlList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<TargetBean> findTargetByParentCode(String regCode, String role, String type, String parentCode) {
		List<Target> targets = this.targetMapper.findTargetByparentCode(regCode, role, type, parentCode);
		List<TargetBean> TargetBeanList = new ArrayList();
		if (null != targets) {
			for (Target target : targets) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				TargetBeanList.add(targetBean);
			}
		}
		return TargetBeanList;
	}

	@Override
	public List<TargetBean> findTargetByParentCodes(String role, String type, String parentCode) {
		List<Target> targets = this.targetMapper.findTargetByparentCodes(role, type, parentCode);
		List<TargetBean> TargetBeanList = new ArrayList();
		if (null != targets) {
			for (Target target : targets) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				TargetBeanList.add(targetBean);
			}
		}
		return TargetBeanList;
	}

	@Override
	public CustPlate findCustPlateByUserNameAndPlate(String userName, String role, String plate) {
		CustPlateExample custPlateExample = new CustPlateExample();
		custPlateExample.createCriteria().andOpTypeNotEqualTo("D").andUsernameEqualTo(userName).andRoleCodeEqualTo(role)
				.andPlateCodeEqualTo(plate);
		List<CustPlate> CustPlates = this.custPlateMapper.selectByExample(custPlateExample);
		if (null != CustPlates && CustPlates.size() == 1) {
			return CustPlates.get(0);
		}
		return null;
	}

	/**
	 * @功能 {按区域id和板块id查询}
	 * @作者 MaxBill
	 * @时间 2017年9月19日 下午2:49:22
	 */
	public List<TarReg> findPlateTarRegByRegAndPlate(Integer regId, Integer blockId) {
		return this.tarRegMapper.findPlateTarRegByRegAndPlate(regId, blockId);
	}

	/**
	 * @功能 {按指标查询}
	 * @作者 MaxBill
	 * @时间 2017年7月17日 上午10:52:20
	 */
	public TarGroDim findTarGroDimByTar(String tar) {
		TarGroDimExample tarGroDimExample = new TarGroDimExample();
		tarGroDimExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar);
		tarGroDimExample.setOrderByClause("GDD_ID DESC");
		List<TarGroDim> tarGroDimList = this.tarGroDimMapper.selectByExample(tarGroDimExample);
		if (null != tarGroDimList && tarGroDimList.size() >= 1) {
			TarGroDim tarGroDim = tarGroDimList.get(0);
			tarGroDim.setGroDimDetail(this.groDimDetailMapper.selectByPrimaryKey(tarGroDim.getGddId()));
			return tarGroDim;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按id查询分组维度}
	 * @作者 MaxBill
	 * @时间 2017年9月22日 上午9:45:15
	 */
	public GroDimDetail findGroDimDetailById(Integer gddId) {
		return this.groDimDetailMapper.selectByPrimaryKey(gddId);
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
	 * @功能 {按指标查询公共机构维度}
	 * @作者 MaxBill
	 * @时间 2017年7月17日 上午10:52:20
	 */
	public TarGroDim findUnionTarGroDimByTar(String tar) {
		List<TarGroDim> tarGroDimList = this.tarGroDimMapper.getUnionOrgType(tar, "ORG_GROUP");
		if (null != tarGroDimList && tarGroDimList.size() >= 1) {
			TarGroDim tarGroDim = tarGroDimList.get(0);
			tarGroDim.setGroDimDetail(this.groDimDetailMapper.selectByPrimaryKey(tarGroDim.getGddId()));
			return tarGroDim;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询自定义板块的一级主题主题列表}
	 * @作者 MaxBill
	 * @时间 2017年9月29日 下午4:57:12
	 */
	public List<TargetBean> findBlockOneTargetTypeByParam(String role, Integer blockId, String roleOrg) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = this.targetMapper.findBlockOneTargetTypeByParam(role, blockId,
				Integer.parseInt(commonUtil.getRoleOrgCode(roleOrg)));
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBeanList.add(targetBean);
			}
			return targetBeanList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * @功能 {查询自定义板块添加时的指标列表}
	 * @作者 MaxBill
	 * @时间 2017年9月29日 下午4:06:39findEditBlockOneTargetByParam
	 */
	public List<TargetBean> findSaveBlockTargetByParam(String regCode, String role, String roleOrg) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = null;
		switch (regCode) {
		case "TEMP03_REG02":
			targetList = this.targetMapper.findSaveBlockOneTargetByParam(regCode, role,
					Integer.parseInt(commonUtil.getRoleOrgCode(roleOrg)));
			break;
		case "TEMP03_REG03":
			targetList = this.targetMapper.findSaveBlockTwoTargetByParam(regCode, role,
					Integer.parseInt(commonUtil.getRoleOrgCode(roleOrg)));
			break;
		}
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBean.setDept(target.getDeptCode());
				targetBean.setPcode(target.getParentCode());
				targetBean.setPname(target.getCalFormula());
				switch (target.getTargetType()) {
				case "1":
					targetBean.setProps("一级");
					break;
				case "3":
					targetBean.setProps("二级");
					break;
				}
				if (null != target.getChannel()) {
					targetBean.setChannel(target.getChannel());
				} else {
					targetBean.setChannel("");
				}
				targetBean.setChecked(false);
				targetBeanList.add(targetBean);
			}
			return targetBeanList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * @功能 {查询自定义板块编辑时的一级指标列表}
	 * @作者 MaxBill
	 * @时间 2017年9月29日 下午4:57:12
	 */
	public List<TargetBean> findEditBlockOneTargetByParam(String role, Integer blockId, String roleOrg) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = this.targetMapper.findEditBlockOneTargetByParam(role, blockId,
				Integer.parseInt(commonUtil.getRoleOrgCode(roleOrg)));
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBean.setDept(target.getDeptCode());
				targetBean.setPcode(target.getParentCode());
				targetBean.setPname(target.getCalFormula());
				targetBean.setProps("一级");
				if (null != target.getChannel()) {
					targetBean.setChannel(target.getChannel());
				} else {
					targetBean.setChannel("");
				}
				if (target.getRemark().equals("1")) {
					targetBean.setChecked(true);
				} else {
					targetBean.setChecked(false);
				}
				targetBeanList.add(targetBean);
			}
			return targetBeanList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * @功能 {查询自定义板块的二级指标列表分类}
	 * @作者 MaxBill
	 * @时间 2017年9月29日 下午4:57:12
	 */
	public List<TargetBean> findBlockTwoTargetTypeByParam(String role, Integer blockId, String roleOrg) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = this.targetMapper.findBlockTwoTargetTypeByParam(role, blockId,
				Integer.parseInt(commonUtil.getRoleOrgCode(roleOrg)));
		if (null != targetList) {
			for (Target target : targetList) {
				// 封装二级指标
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBeanList.add(targetBean);
			}
			return targetBeanList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * @功能 {查询自定义板块编辑时的二级指标列表}
	 * @作者 MaxBill
	 * @时间 2017年9月29日 下午4:57:12
	 */
	public List<Object> findEditBlockTwoTargetByParam(String role, Integer blockId, String roleOrg) {
		List<Object> objectList = new ArrayList<Object>();
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<PlateTwoTarGroBean> plateTwoTarGroBeanList = new ArrayList<PlateTwoTarGroBean>();
		List<Target> targetList = this.targetMapper.findEditBlockTwoTargetByParam(role, blockId,
				Integer.parseInt(commonUtil.getRoleOrgCode(roleOrg)));
		Map<Integer, List<Target>> targetMap = new HashMap<Integer, List<Target>>();
		if (null != targetList) {
			for (Target target : targetList) {
				// 封装二级指标
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBean.setDept(target.getDeptCode());
				targetBean.setPcode(target.getFieldCode());
				targetBean.setPname(target.getCalFormula());
				switch (target.getTargetType()) {
				case "1":
					targetBean.setProps("一级");
					break;
				case "3":
					targetBean.setProps("二级");
					break;
				}
				if (null != target.getChannel()) {
					targetBean.setChannel(target.getChannel());
				} else {
					targetBean.setChannel("");
				}
				targetBean.setChecked(false);
				targetBeanList.add(targetBean);
				// 封装二级指标组
				if (target.getRemark().equals("1")) {
					if (targetMap.containsKey(target.getSort())) {
						List<Target> tempTargetList = targetMap.get(target.getSort());
						tempTargetList.add(target);
						targetMap.put(target.getSort(), tempTargetList);
					} else {
						List<Target> tempTargetList = new ArrayList<Target>();
						tempTargetList.add(target);
						targetMap.put(target.getSort(), tempTargetList);
					}
				}
			}
			if (!targetMap.isEmpty()) {
				for (Map.Entry<Integer, List<Target>> entry : targetMap.entrySet()) {
					PlateTwoTarGroBean plateTwoTarGroBean = new PlateTwoTarGroBean();
					plateTwoTarGroBean.setId(entry.getKey());
					String blockName = entry.getValue().get(0).getParentCode();
					plateTwoTarGroBean.setName(blockName);
					List<String> target2List = new ArrayList<String>();
					for (Target plateTwoTar : entry.getValue()) {
						target2List.add(plateTwoTar.getTargetCode());
					}
					plateTwoTarGroBean.setTargets(target2List);
					plateTwoTarGroBeanList.add(plateTwoTarGroBean);
				}
			}
			objectList.add(targetBeanList);
			objectList.add(plateTwoTarGroBeanList);
		} else {
			objectList.add(targetBeanList);
			objectList.add(plateTwoTarGroBeanList);
		}
		return objectList;
	}

	/**
	 * @功能 {查询指标查询维度}
	 * @作者 MaxBill
	 * @时间 2017年8月3日 下午5:12:28
	 */
	public List<QueryDim> findQueryDimByTar(Integer blockId) {
		List<QueryDim> queryDimList = this.queryDimMapper.findQueryDimByBlock(blockId);
		if (null != queryDimList) {
			for (QueryDim queryDim : queryDimList) {
				QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
				queryDimDetailExample.createCriteria().andOpTypeNotEqualTo("D").andQdIdEqualTo(queryDim.getQdId());
				queryDimDetailExample.setOrderByClause("QDD_ID ASC");
				List<QueryDimDetail> queryDimDetailList = this.queryDimDetailMapper
						.selectByExample(queryDimDetailExample);
				queryDim.setQueryDimDetail(queryDimDetailList);
			}
		}
		return queryDimList;
	}

	@Override
	public List<TarReg> findMoreTarRegsBySubAndRole(List<String> temps, String blockCode, String role,
			String groupbyDate) {
		return this.tarRegMapper.findMoreTarRegsBySubAndRole(temps, blockCode, role, groupbyDate);
	}

	@Override
	public List<PlateOneTar> findAllPlateTarByBlock(String blockId, String groupbyDate, String roleCate) {
		return this.plateOneTarMapper.findAllPlateTarByBlock(blockId, groupbyDate, roleCate);
	}

}
