package com.huatai.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.mapper.TarQueryDimMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.Target;
import com.huatai.web.model.TargetExample;
import com.huatai.web.service.TargetService;

/**
 * @author 胡智辉 2017年7月19日
 */
@Service
public class TargetServiceImpl implements TargetService {

	@Autowired
	private TarQueryDimMapper tarQueryDimMapper;

	@Autowired
	private TarGroDimMapper tarGroDimMapper;

	@Autowired
	private TargetMapper targetMapper;

	@Override
	public int addTarget(Target target) {
		int result = this.targetMapper.insertSelective(target);
		return result;
	}

	@Override
	public int deleteTarget(String id) {
		int result = this.targetMapper.deleteByPrimaryKey(id);
		if (result == 1) {
			TarGroDim tarGeoDim = new TarGroDim();
			tarGeoDim.setOpType("D");
			tarGeoDim.setTargetCode(id);
			tarGeoDim.setModifierId(SessionContextUtils.getLoginName().toString());
			TarQueryDim tarQueryDim = new TarQueryDim();
			tarQueryDim.setTargetCode(id);
			tarQueryDim.setOpType("D");
			tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
			tarQueryDim.setModifyTime(new Date());
			this.tarQueryDimMapper.updateByTargetCode(tarQueryDim);
			this.tarGroDimMapper.updateByTargetCode(tarGeoDim);
			// this.tarQueryDimMapper.deleteByTargetCode(id);
			// this.tarGroDimMapper.deleteByTargetCode(id);
		}
		return result;
	}

	public int update(Target target) {
		int result = this.targetMapper.updateByOldTargetCode(target, null);
		if (result == 1 || target.getOpType() == "D") {
			TarGroDim tarGeoDim = new TarGroDim();
			tarGeoDim.setOpType("D");
			tarGeoDim.setTargetCode(target.getTargetCode());
			tarGeoDim.setModifierId(SessionContextUtils.getLoginName().toString());
			TarQueryDim tarQueryDim = new TarQueryDim();
			tarQueryDim.setTargetCode(target.getTargetCode());
			tarQueryDim.setOpType("D");
			tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
			tarQueryDim.setModifyTime(new Date());
			this.tarQueryDimMapper.updateByTargetCode(tarQueryDim);
			this.tarGroDimMapper.updateByTargetCode(tarGeoDim);
			// tarQueryDimMapper.deleteByTargetCode(target.getTargetCode());
			// tarGroDimMapper.deleteByTargetCode(target.getTargetCode());
		}
		return result;
	}

	@Override
	public List<Target> selectTargetByTree(String treeNode) {
		return this.targetMapper.selectTargetByTree(treeNode);
	}

	@Override
	public Pager<Target> findTargetByPage(Pager<Target> pager, Target record) {
		Pager<Target> result = this.targetMapper.findTargetByPage(pager, record);
		return result;
	}

	/**
	 * @功能 : 根据isHomeCoreTarget查询获得首页核心指标列表
	 * @作者 : 沈从会
	 * @日期 : 2017年7月25日 上午10:50:49
	 */
	@Override
	public List<Target> selectByIsHomeCoreTarget(String isHomeCoreTarget) {
		return this.targetMapper.selectByIsHomeCoreTarget(isHomeCoreTarget);
	}

	/**
	 * @功能 : 通过指标编码查询指标信息
	 * @作者 : 沈从会
	 * @日期 : 2017年7月26日 上午9:10:55
	 */
	@Override
	public Target selectByPrimaryKey(String targetCode) {
		return this.targetMapper.selectByPrimaryKey(targetCode);
	}

	/**
	 * @功能 : 根据指标名称查询指标信息
	 * @作者 : 沈从会
	 * @日期 : 2017年7月26日 下午4:30:00
	 */
	@Override
	public Target selectByTargetName(String targetName) {
		return this.targetMapper.selectByTargetName(targetName);
	}

	/**
	 * @功能 :
	 * @作者 : 程乐飞
	 * @日期时间: 2017年7月28日 下午7:28:17
	 */
	@Override
	public List<Target> findAllTarget() {
		return this.targetMapper.findAll();
	}

	@Override
	public List<Target> findTargetByDeptCodeOrQueryCode(String deptCode, String regCode) {
		return this.targetMapper.findTargetByDeptCodeOrQueryCode(deptCode, regCode, "");
	}

	@Override
	public List<Target> findTargetByTargetType(String targetType) {
		return this.targetMapper.findTargetByTargetType(targetType);
	}

	@Override
	public List<Target> findTargetByRegId(TarReg tarReg) {
		return this.targetMapper.findTargetByRegId(tarReg);
	}

	@Override
	public List<Target> findTargetSelective(Target target) {
		return this.targetMapper.findTargetSelective(target);
	}

	@Override
	public int updateTarget(Target target, String oldTargetCode, String[] gdIds, String[] gddIds, String[] qdIds) {
		int result = this.targetMapper.updateByOldTargetCode(target, oldTargetCode);
		if (result == 1) {
			TarGroDim tarGeoDim = new TarGroDim();
			tarGeoDim.setOpType("D");
			tarGeoDim.setTargetCode(oldTargetCode);
			tarGeoDim.setModifierId(SessionContextUtils.getLoginName().toString());
			TarQueryDim tarQueryDim = new TarQueryDim();
			tarQueryDim.setTargetCode(oldTargetCode);
			tarQueryDim.setOpType("D");
			tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
			tarQueryDim.setModifyTime(new Date());
			this.tarQueryDimMapper.updateByTargetCode(tarQueryDim);
			this.tarGroDimMapper.updateByTargetCode(tarGeoDim);
			// this.tarQueryDimMapper.deleteByTargetCode(oldTargetCode);
			// this.tarGroDimMapper.deleteByTargetCode(oldTargetCode);
		}
		return result;
	}

	@Override
	public Pager<Target> findTargetByPage2(Pager<Target> pager, Target record) {
		Pager<Target> result = this.targetMapper.findTargetByPage2(pager, record);
		return result;
	}

	@Override
	@Transactional
	public int updateTarget(Target target) {
		int result = this.targetMapper.updateByPrimaryKey(target);
		if (result == 1) {
			TarGroDim tarGeoDim = new TarGroDim();
			tarGeoDim.setOpType("D");
			tarGeoDim.setTargetCode(target.getTargetCode());
			tarGeoDim.setModifyTime(new Date());
			tarGeoDim.setModifierId(SessionContextUtils.getLoginName().toString());
			TarQueryDim tarQueryDim = new TarQueryDim();
			tarQueryDim.setTargetCode(target.getTargetCode());
			tarQueryDim.setOpType("D");
			tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
			tarQueryDim.setModifyTime(new Date());
			this.tarQueryDimMapper.updateByTargetCode(tarQueryDim);
			this.tarGroDimMapper.updateByTargetCode(tarGeoDim);
			// this.tarQueryDimMapper.deleteByTargetCode(target.getTargetCode());
			// this.tarGroDimMapper.deleteByTargetCode(target.getTargetCode());
		}
		return result;
	}

	@Override
	public Pager<Target> findTargetStatisByPage(Pager<Target> pager, Target record) {
		return targetMapper.findTargetStatisByPage(pager, record);
	}

	@Override
	public Pager<Target> findRefTarget(Pager<Target> pager, String[] regCodes, Target target) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("regCodes", regCodes);
		params.put("target", target);
		return targetMapper.findRefTarget(pager, params);
	}

	/**
	 * @功能 查询主题板块
	 * @作者 胡智辉
	 * @返回类型 result
	 */
	@Override
	public List<Target> findTargetParentId(String parentCode) {
		return this.targetMapper.findTargetParentId(parentCode);
	}

	@Override
	public List<Target> findParentTargetById(String parentCode) {
		return this.targetMapper.findParentTargetById(parentCode);
	}

	@Override
	public String findTargetCodeByTargetName(String targetName) {
		return this.targetMapper.findTargetCodeByTargetName(targetName);
	}

	@Override
	public List<Target> findDeptCodeByGroup() {
		return this.targetMapper.findDeptCodeByGroup();
	}

	@Override
	public List<Target> findTargetByIsWarnType(String dept) {
		return this.targetMapper.findTargetByIsWarnType(dept);
	}

	@Override
	public String findFieldByTargetCode(String targetCode) {
		return this.targetMapper.findFieldByTargetCode(targetCode);
	}

	@Override
	public List<Target> findTargetByDept(String depts) {
		return this.targetMapper.findTargetByDept(depts);
	}

	@Override
	public List<Target> findByIsStaTar(String depts) {
		return this.targetMapper.findByIsStaTar(depts);
	}

	@Override
	public List<Target> findTargetStatis(Target record) {
		return targetMapper.findTargetStatis(record);
	}

	@Override
	public List<Target> findByIsStatis() {
		return targetMapper.findByIsStatis();
	}

	@Override
	public List<Target> findByTargetType(Target target) {
		return targetMapper.findByTargetType(target);
	}

	@Override
	public Target findByTargetCode(String targetCode) {
		return targetMapper.findByTargetCode(targetCode);
	}

	@Override
	public Pager<Target> findTarManByPage(Pager<Target> pager, Target target) {
		return targetMapper.findTarManByPage(pager, target);
	}

	@Override
	public List<Target> findTargetByTarMan() {
		return targetMapper.findTargetByTarMan();
	}

	@Override
	public List<Target> findTargetBySubject() {
		return targetMapper.findTargetBySubject();
	}

	@Override
	public List<Target> findTargetBySubTarget(String subCode) {
		TargetExample example = new TargetExample();
		example.createCriteria().andTargetTypeNotEqualTo("0").andTargetTypeNotEqualTo("2")
				.andTargetCodeLike(subCode + "%").andOpTypeNotEqualTo("D");
		targetMapper.selectByExample(example);
		return targetMapper.selectByExample(example);
	}

}
