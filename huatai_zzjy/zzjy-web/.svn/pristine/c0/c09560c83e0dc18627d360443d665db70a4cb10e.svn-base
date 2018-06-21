package com.huatai.web.service;

import java.text.ParseException;
import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillRole;
import com.huatai.web.model.BillSql;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.BillTargetDetail;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.GroDimCodeStr;

public interface BillFixService {

	int insert(Bill record);
	
	int deleteByPrimaryKey(Integer billId);
	
	int updateByPrimaryKey(Bill record);

	Pager<Bill> findByPager(Pager<Bill> pager, Bill record);
	
	Bill selectByPrimaryKey(Integer billId);

	int insertBillTarget(BillTarget billT);

	int insertBillFiltDim(BillFiltDim bfd);

	List<GroDimCodeStr> selectGroDimByTars(String tarsStr);

	int insertBillGroDim(BillGroDim bgd);

	int insertBillSQL(BillSql bs);

	void deleteByBillId(Integer id);

	List<BillTargetDetail> getTarListByQid(String qid);

	// 联动更新清单对应的指标表
	BillTarget selectByBillIdAndTarCode(Integer billId, String string);

	void updateBillTarget(BillTarget billT);

	BillFiltDim selectBillFiltDim(Integer billId, String tar);

	void updateBillFiltDim(BillFiltDim bfd);

	BillGroDim selectBillGroDim(Integer billId, String groDimType);

	void updateBillGroDim(BillGroDim bgd);

	BillSql selectBillSql(Integer billId);

	void updateBillSQL(BillSql bs);

	void deleteNoNeedBillTar(Integer billId, List<String> tars) throws ParseException;

	List<DeptInfo> selectDeptInfo();

	void insertBillRole(BillRole br) throws ParseException;

	List<BillRole> selectBillRoleByBillid(String billId);
}
