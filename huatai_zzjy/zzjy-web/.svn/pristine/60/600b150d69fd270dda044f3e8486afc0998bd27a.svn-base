package com.huatai.web.thrift.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fairyland.jdp.framework.util.SpringUtil;
import com.huatai.web.bean.NoticeBean;
import com.huatai.web.mapper.CoreTargetMapper;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.GroDimMapper;
import com.huatai.web.mapper.JyfxTargetMapper;
import com.huatai.web.mapper.NoticeMapper;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.mapper.TarRegMapper;
import com.huatai.web.mapper.TarRegSqlMapper;
import com.huatai.web.mapper.TarRegTabHeadMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.mapper.TemRegMapper;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.CoreTargetExample;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;
import com.huatai.web.model.GroDimExample;
import com.huatai.web.model.JyfxTarget;
import com.huatai.web.model.JyfxTargetExample;
import com.huatai.web.model.Notice;
import com.huatai.web.model.NoticeExample;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegExample;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.TarRegSqlExample;
import com.huatai.web.model.TarRegTabHead;
import com.huatai.web.model.TarRegTabHeadExample;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.thrift.service.IndexService;
import com.huatai.web.utils.DateUtil;

/**
 * @功能 {主页服务}
 * @作者 MaxBill
 * @时间 2017年8月22日 上午11:22:49
 */
@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private GroDimMapper groDimMapper;
	@Autowired
	private GroDimDetailMapper groDimDetailMapper;
	@Autowired
	private TarRegMapper tarRegMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private TemRegMapper temRegMapper;
	@Autowired
	private TarRegSqlMapper tarRegSqlMapper;
	@Autowired
	private TarRegTabHeadMapper tarRegTabHeadMapper;
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private JyfxTargetMapper jyfxTargetMapper;
	@Autowired
	private CoreTargetMapper coreTargetMapper;
	@Autowired
	private TarGroDimMapper tarGroDimMapper;

	private CommonUtil commonUtil;

	public IndexServiceImpl() {
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * @功能 {查询分组维度详细按分组维度id}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 上午11:36:36
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
	 * @功能 {根据主题和角色查询指标区域}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午3:16:00
	 */
	public List<TarReg> findTarRegsBySubAndRole(String temp, String sub, String role) {
		return this.tarRegMapper.findTarRegsBySubAndRole(temp, sub, role);
	}

	/**
	 * @功能 {公告列表}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午4:10:38
	 */
	public List<Notice> listNotice(String roleDept) {
		NoticeExample noticeExample = new NoticeExample();
		noticeExample.createCriteria().andOpTypeNotEqualTo("D");
		noticeExample.setOrderByClause("CREATE_TIME DESC");
		List<Notice> noticeList = this.noticeMapper.selectByExample(noticeExample);
		List<Notice> newNoticeList = new ArrayList<Notice>();
		if (null != noticeList) {
			for (Notice notice : noticeList) {
				String deptCode = notice.getDeptCode();
				List<String> deptList = Arrays.asList(deptCode.split(","));
				if (null != deptList && deptList.contains(roleDept)) {
					newNoticeList.add(notice);
				}
			}
		}
		return newNoticeList;
	}

	/**
	 * @功能 {查看公告}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午4:12:25
	 */
	public NoticeBean lookNotice(Integer noticeId) {
		Notice notice = this.noticeMapper.selectByPrimaryKey(noticeId);
		if (null != notice) {
			NoticeBean noticeBean = new NoticeBean();
			noticeBean.setId(notice.getNoticeId());
			noticeBean.setTitle(notice.getTitle());
			noticeBean.setPublisher(notice.getCreatorId());
			noticeBean.setContent(notice.getContent());
			noticeBean.setDate(DateUtil.formatDate(notice.getCreateTime(), DateUtil.DATE_FORMAT));
			return noticeBean;
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
	 * @功能 {按区域id查询}
	 * @作者 MaxBill
	 * @时间 2017年8月22日 下午5:24:32
	 */
	public List<TarReg> findTarRegsByRegion(Integer regId, String role, String roleOrg, String roleDept) {
		List<TarReg> tarRegList = tarRegMapper.findTarRegsByRegion(regId, role, roleDept);
		return commonUtil.dealTarRegList(tarRegList, roleOrg);
	}

	/**
	 * @功能 {按指标、区域、维度查询TarRegSql}
	 * @作者 MaxBill
	 * @时间 2017年7月19日 上午10:34:58
	 */
	public TarRegSql findTarRegSql(int type, String tar, Integer regId, String groupType, String byDim) {
		TarRegSqlExample tarRegSqlExample = new TarRegSqlExample();
		switch (type) {
		case 1:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andGroupDetailCodeEqualTo(byDim);
			break;
		case 2:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andGroupCodeEqualTo(groupType).andDateTypeEqualTo(byDim);
			break;
		case 3:
			tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar).andRegIdEqualTo(regId)
					.andDateTypeEqualTo(byDim);
			break;
		case 4:
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
			if (!StringUtils.isEmpty(groupType)) {
				tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar)
						.andRegIdEqualTo(regId).andGroupCodeEqualTo(groupType).andDateTypeEqualTo(dateType);
			} else {
				tarRegSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(tar)
						.andRegIdEqualTo(regId).andDateTypeEqualTo(dateType);
			}
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
	 * @功能 {按区域获取表格头部信息}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 下午7:16:30
	 */
	public List<String> findTarRegTabHeadByReg(Integer regId) {
		List<String> headList = new ArrayList<String>();
		TarRegTabHeadExample tarRegTabHeadExample = new TarRegTabHeadExample();
		tarRegTabHeadExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId);
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
	 * @功能 {按区域获和指标取表格头部信息}
	 * @作者 MaxBill
	 * @时间 2017年8月23日 下午7:16:30
	 */
	public List<String> findTarRegTabHeadByRegAndTar(Integer regId, String tar) {
		List<String> headList = new ArrayList<String>();
		TarRegTabHeadExample tarRegTabHeadExample = new TarRegTabHeadExample();
		tarRegTabHeadExample.createCriteria().andOpTypeNotEqualTo("D").andRegIdEqualTo(regId).andTargetCodeEqualTo(tar);
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
	 * @功能 {按模板code查询区域}
	 * @作者 MaxBill
	 * @时间 2017年8月18日 上午11:18:12
	 */
	public List<TemReg> findRegionByTemp(String temp) {
		List<TemReg> temRegList = this.temRegMapper.findRegionByTemp(temp);
		return temRegList;
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
	 * @功能 {按code查询指标}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午11:29:15
	 */
	public Target findTargetByCode(String code) {
		return this.targetMapper.selectByPrimaryKey(code);
	}

	/**
	 * @功能 {根据指标编码查询经营信息}
	 * @作者 MaxBill
	 * @时间 2017年9月14日 下午4:41:04
	 */
	public List<JyfxTarget> findJyfxTargetByTarget(String target) {
		JyfxTargetExample jyfxTargetExample = new JyfxTargetExample();
		jyfxTargetExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(target);
		return jyfxTargetMapper.selectByExample(jyfxTargetExample);
	}

	/**
	 * @功能 {按照指标PID查询核心指标}
	 * @作者 MaxBill
	 * @时间 2017年9月18日 下午5:01:29
	 */
	public List<CoreTarget> findCoreTargetByPid(String target) {
		CoreTargetExample coreTargetExample = new CoreTargetExample();
		coreTargetExample.createCriteria().andOpTypeNotEqualTo("D").andPidEqualTo(target);
		return this.coreTargetMapper.selectByExample(coreTargetExample);
	}

	/**
	 * @功能 {按指标查询核心指标}
	 * @作者 MaxBill
	 * @时间 2017年9月18日 下午5:18:04
	 */
	public CoreTarget findCoreTargetByTarget(String target) {
		CoreTargetExample coreTargetExample = new CoreTargetExample();
		coreTargetExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(target);
		List<CoreTarget> coreTargetList = this.coreTargetMapper.selectByExample(coreTargetExample);
		if (null != coreTargetList) {
			return coreTargetList.get(0);
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

	public String findDeptCodeByTar(String targetCode) {
		return targetMapper.selectByPrimaryKey(targetCode).getDeptCode();
	}

	/**
	 * @功能 {按主题查询时间维度}
	 * @作者 MaxBill
	 * @时间 2017年9月9日 下午1:24:03
	 */
	public List<GroDimDetail> findIndexGroDetailListBySubWithDate(String subCode) {
		return this.groDimDetailMapper.findIndexGroDetailListBySubWithDate(subCode);
	}

}
