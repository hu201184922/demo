package com.huatai.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.CoreTargetMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.CoreTargetExample;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.RelaTarget;
import com.huatai.web.model.Target;
import com.huatai.web.service.CoreTargetService;

/**
 * @description:
 * @author ：沈从会
 * @datetime : 2017年7月21日 下午4:22:33
 */
@Service
public class CoreTargetServiceImpl implements CoreTargetService {

	@Autowired
	private CoreTargetMapper coreTargetMapper;
	@Autowired
	private TargetMapper targetMapper;

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public int countByExample(CoreTargetExample example) {
		return this.coreTargetMapper.countByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public int deleteByExample(CoreTargetExample example) {
		return this.coreTargetMapper.deleteByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @throws ParseException 
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public void deleteByPrimaryKey(Integer ctId) throws ParseException {
		this.coreTargetMapper.deleteByPid(ctId);
	    this.coreTargetMapper.deleteByPrimaryKey(ctId);
	}

	/**
	 * @function:批量新增
	 * @author ：沈从会
	 * @throws ParseException
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public void insert(CoreTarget record) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setOpType("A");
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		if (record.getPid() == null || record.getPid() == "") {
			record.setPid("1");
		}
		this.coreTargetMapper.insert(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public int insertSelective(CoreTarget record) {
		return this.coreTargetMapper.insertSelective(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public List<CoreTarget> selectByExample(CoreTargetExample example) {
		return this.coreTargetMapper.selectByExample(example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public CoreTarget selectByPrimaryKey(Integer ctId) {
		return this.coreTargetMapper.selectByPrimaryKey(ctId);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public int updateByExampleSelective(CoreTarget record, CoreTargetExample example) {
		return this.coreTargetMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public int updateByExample(CoreTarget record, CoreTargetExample example) {
		return this.coreTargetMapper.updateByExample(record, example);
	}

	/**
	 * @功能 : 更新
	 * @作者 : 沈从会
	 * @日期 : 2017年7月28日 下午3:22:22
	 */
	@Override
	public void updateByPrimaryKeySelective(CoreTarget record) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		this.coreTargetMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public int updateByPrimaryKey(CoreTarget record) {
		return this.coreTargetMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author ：沈从会
	 * @datetime:2017年7月21日 下午4:22:33
	 */
	@Override
	public Pager<CoreTarget> findByPager(Pager<CoreTarget> pager, CoreTarget record) {
//		Pager<CoreTarget> records = this.coreTargetMapper.findByPager(pager, record);
//		for (CoreTarget coreTarget : records.getPageItems()) {
//			Target target = targetMapper.selectByPrimaryKey(coreTarget.getTargetCode());
//			coreTarget.setTargetName(target.getTargetName());
//			User user = userDao.findById(Long.parseLong(coreTarget.getCreatorId().trim()));
//			coreTarget.setCreatorName(user.getUserName());
//			if (!"".equals(coreTarget.getModifierId()) && null != coreTarget.getModifierId()) {
//				User us = userDao.findById(Long.parseLong(coreTarget.getModifierId()));
//				coreTarget.setModifierName(us.getUserName());
//			} else {
//				coreTarget.setModifierName(null);
//			}
//		}
		List<CoreTarget> rts = new ArrayList<>();
		List<Target> records = this.targetMapper.findTargetByDeptCodeOrQueryCode(record.getDeptCode(), "TEMP02_REG01", record.getTargetName());
		for (Target target : records) {
			CoreTarget rt = new CoreTarget();
			rt.setDeptCode(record.getDeptCode());
			rt.setTargetCode(target.getTargetCode());
			rt.setTargetName(target.getTargetName());
			rts.add(rt);
		}
		Pager<CoreTarget> pg = new Pager<>();
		pg.setPageItems(rts);
		pg.setTotalCount(rts.size());
		return pg;
	}

	@Override
	public Pager<RelaTarget> findByPagerRela(Pager<RelaTarget> pager, RelaTarget record) {
		List<RelaTarget> rts = new ArrayList<>();
		List<Target> records = this.targetMapper.findTargetByDeptCodeOrQueryCode(record.getDeptCode(), "TEMP02_REG02", record.getTargetName());
		for (Target target : records) {
			RelaTarget rt = new RelaTarget();
			rt.setTargetCode(target.getTargetCode());
			rt.setTargetName(target.getTargetName());
			rt.setTargetType(target.getTargetType());
			rt.setChannel(target.getChannel());
			rt.setDeptCode(target.getDeptCode());
			rt.setRegCode("REG_02");
			rts.add(rt);
		}
		Pager<RelaTarget> pg = new Pager<>();
		pg.setPageItems(rts);
		pg.setTotalCount(rts.size());
		return pg;
	}

	@Override
	public List<CoreTarget> findByPid(String pid, String deptCode) {
		return coreTargetMapper.findByPid(pid, deptCode);
	}

	@Override
	public int deleteByPidAndTargetCode(String pid, String targetCode) {
		return coreTargetMapper.deleteByPidAndTargetCode(pid, targetCode);
	}

	@Override
	public List<CoreTarget> findByPidAndDeptCode(String pid, String deptCode) {
		return coreTargetMapper.findByPid(pid, deptCode);
	}

	@Override
	public List<CoreTarget> findByTargetCodeAndDeptCode(String targetCode, String deptCode) {
		return coreTargetMapper.findByTargetCodeAndDeptCode(targetCode, deptCode);
	}

	@Override
	public List<DeptInfo> findByDeptCodeStr(String dstr) {
		return coreTargetMapper.findByDeptCodeStr(dstr);
	}

	@Override
	public int update(CoreTarget record) {
		return coreTargetMapper.updateByPrimaryKeySelective(record);
	}
}
