package com.huatai.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.BillFiltDimMapper;
import com.huatai.web.mapper.BillGroDimMapper;
import com.huatai.web.mapper.BillMapper;
import com.huatai.web.mapper.BillRoleMapper;
import com.huatai.web.mapper.BillSqlMapper;
import com.huatai.web.mapper.BillTargetMapper;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillFiltDimExample;
import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillGroDimExample;
import com.huatai.web.model.BillRole;
import com.huatai.web.model.BillRoleExample;
import com.huatai.web.model.BillSql;
import com.huatai.web.model.BillSqlExample;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.BillTargetDetail;
import com.huatai.web.model.BillTargetExample;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.GroDimCodeStr;
import com.huatai.web.service.BillFixService;

/**
 * @remark 固定清单
 * @author yangbo
 */

@Service
public class BillFixServiceImpl implements BillFixService{

	@Autowired
	private BillMapper billMapper;
	@Autowired
	private BillTargetMapper billTargetMapper;
	@Autowired
	private BillFiltDimMapper billFiltDimMapper;
	@Autowired
	private BillGroDimMapper billGroDimMapper;
	@Autowired
	private BillSqlMapper billSqlMapper;
	@Autowired
	private BillRoleMapper billRoleMapper;
	
	@Override
	public int insert(Bill record) {
		return billMapper.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer billId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Bill record = billMapper.selectByPrimaryKey(billId);
		record.setOpType("D");
		record.setModifierId(SessionContextUtils.getLoginUserId().toString());
		try {
			record.setModifyTime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return billMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Bill record) {
		return billMapper.updateByPrimaryKey(record);
	}

	@Override
	public Pager<Bill> findByPager(Pager<Bill> pager, Bill record) {
		return billMapper.findByPager(pager, record);
	}

	@Override
	public Bill selectByPrimaryKey(Integer billId) {
		return billMapper.selectByPrimaryKey(billId);
	}

	@Override
	public int insertBillTarget(BillTarget billT) {
		return billTargetMapper.insert(billT);
	}

	@Override
	public int insertBillFiltDim(BillFiltDim bfd) {
		return billFiltDimMapper.insert(bfd);
	}

	@Override
	public List<GroDimCodeStr> selectGroDimByTars(String tarsStr) {
		return billMapper.selectGroDimByTars(tarsStr);
	}

	@Override
	public int insertBillGroDim(BillGroDim bgd) {
		return billGroDimMapper.insert(bgd);
	}

	@Override
	public int insertBillSQL(BillSql bs) {
		return billSqlMapper.insert(bs);
	}

	@Override
	public void deleteByBillId(Integer id) {
		String loginId = SessionContextUtils.getCurrentUserId().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		try {
			now = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 清单指标
		BillTargetExample example = new BillTargetExample();
		List<String> v0 = new ArrayList<>();
		v0.add("D");
		example.createCriteria().andBillIdEqualTo(id).andOpTypeNotIn(v0);
		List<BillTarget> bt = billTargetMapper.selectByExample(example);
		for (BillTarget b : bt) {
			b.setModifyTime(now);
			b.setOpType("D");
			b.setModifierId(loginId);
			billTargetMapper.updateByPrimaryKey(b);
		}
		// 清单筛选维度
		BillFiltDimExample example1 = new BillFiltDimExample();
		List<String> v1 = new ArrayList<>();
		v1.add("D");
		example1.createCriteria().andBillIdEqualTo(id).andOpTypeNotIn(v1);
		List<BillFiltDim> bfd = billFiltDimMapper.selectByExample(example1);
		for (BillFiltDim b : bfd) {
			b.setModifyTime(now);
			b.setOpType("D");
			b.setModifierId(loginId);
			billFiltDimMapper.updateByPrimaryKey(b);
		}
		// 清单分组维度
		BillGroDimExample example2 = new BillGroDimExample();
		List<String> v2 = new ArrayList<>();
		v2.add("D");
		example2.createCriteria().andBillIdEqualTo(id).andOpTypeNotIn(v2);
		List<BillGroDim> bgd = billGroDimMapper.selectByExample(example2);
		for (BillGroDim b : bgd) {
			b.setModifyTime(now);
			b.setOpType("D");
			b.setModifierId(loginId);
			billGroDimMapper.updateByPrimaryKey(b);
		}
		// 清单SQL
		BillSqlExample bse = new BillSqlExample();
		List<String> v3 = new ArrayList<>();
		v3.add("D");
		bse.createCriteria().andBillIdEqualTo(id).andOpTypeNotIn(v3);
		List<BillSql> bs = billSqlMapper.selectByExample(bse);
		for (BillSql b : bs) {
			b.setModifyTime(now);
			b.setOpType("D");
			b.setModifierId(loginId);
			billSqlMapper.updateByPrimaryKey(b);
		}
	}

	@Override
	public List<BillTargetDetail> getTarListByQid(String qid) {
		return billMapper.getTarListByQid(qid);
	}

	@Override
	public BillTarget selectByBillIdAndTarCode(Integer billId, String tar) {
		BillTargetExample exa = new BillTargetExample();
		exa.createCriteria().andBillIdEqualTo(billId).andTargetCodeEqualTo(tar);
		List<BillTarget> bts = billTargetMapper.selectByExample(exa);
		return bts.size()==0?null:bts.get(0);
	}

	@Override
	public void updateBillTarget(BillTarget billT) {
		billTargetMapper.updateByPrimaryKey(billT);
	}

	@Override
	public BillFiltDim selectBillFiltDim(Integer billId, String tar) {
		BillFiltDimExample exa = new BillFiltDimExample();
		exa.createCriteria().andBillIdEqualTo(billId).andTargetCodeEqualTo(tar);
		List<BillFiltDim> bfds = billFiltDimMapper.selectByExample(exa);
		return bfds.size()==0?null:bfds.get(0);
	}

	@Override
	public void updateBillFiltDim(BillFiltDim bfd) {
		billFiltDimMapper.updateByPrimaryKey(bfd);
	}

	@Override
	public BillGroDim selectBillGroDim(Integer billId, String groDimType) {
		BillGroDimExample exa = new BillGroDimExample();
		exa.createCriteria().andBillIdEqualTo(billId).andGroupTypeEqualTo(groDimType);
		List<BillGroDim> bgds = billGroDimMapper.selectByExample(exa);
		return bgds.size()==0?null:bgds.get(0);
	}

	@Override
	public void updateBillGroDim(BillGroDim bgd) {
		billGroDimMapper.updateByPrimaryKey(bgd);
	}

	@Override
	public BillSql selectBillSql(Integer billId) {
		BillSqlExample exa = new BillSqlExample();
		exa.createCriteria().andBillIdEqualTo(billId);
		List<BillSql> bss = billSqlMapper.selectByExample(exa);
		return bss.size()==0?null:bss.get(0);
	}

	@Override
	public void updateBillSQL(BillSql bs) {
		billSqlMapper.updateByPrimaryKey(bs);
	}

	@Override
	public void deleteNoNeedBillTar(Integer billId, List<String> tars) throws ParseException {
		String loginId = SessionContextUtils.getCurrentUserId().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 清单指标
		BillTargetExample exa = new BillTargetExample();
		exa.createCriteria().andBillIdEqualTo(billId);
		List<BillTarget> bts = billTargetMapper.selectByExample(exa);
		for (BillTarget bt : bts) {
			if (!tars.contains(bt.getTargetCode())) {
				bt.setOpType("D");
				bt.setModifierId(loginId);
				bt.setModifyTime(sdf.parse(sdf.format(new Date())));
				billTargetMapper.updateByPrimaryKey(bt);
			}
		}
		
		// 清单筛选指标
		BillFiltDimExample exab = new BillFiltDimExample();
		exab.createCriteria().andBillIdEqualTo(billId);
		List<BillFiltDim> bgds = billFiltDimMapper.selectByExample(exab);
		for (BillFiltDim bgd : bgds) {
			if (!tars.contains(bgd.getTargetCode())) {
				bgd.setOpType("D");
				bgd.setModifierId(loginId);
				bgd.setModifyTime(sdf.parse(sdf.format(new Date())));
				billFiltDimMapper.updateByPrimaryKey(bgd);
			}
		}
	}

	@Override
	public List<DeptInfo> selectDeptInfo() {
		return billMapper.selectDeptInfo();
	}

	@Override
	public void insertBillRole(BillRole br) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
		String loginId = SessionContextUtils.getLoginUserId().toString();
		String billId = br.getBillId();
		BillRoleExample exa = new BillRoleExample();
		exa.createCriteria().andBillIdEqualTo(billId);
		List<BillRole> billRs = billRoleMapper.selectByExample(exa);
		if (billRs.size() > 0) {
			BillRole brr = billRs.get(0);
			brr.setModifyTime(now);
			brr.setModifierId(loginId);
			brr.setDeptCode(br.getDeptCode());
			brr.setOpType("U");
			billRoleMapper.updateByExample(brr, exa);
		} else {
			billRoleMapper.insert(br);
		}
	}

	@Override
	public List<BillRole> selectBillRoleByBillid(String billId) {
		BillRoleExample exa = new BillRoleExample();
		exa.createCriteria().andBillIdEqualTo(billId);
		return billRoleMapper.selectByExample(exa);
	}
	 
}
