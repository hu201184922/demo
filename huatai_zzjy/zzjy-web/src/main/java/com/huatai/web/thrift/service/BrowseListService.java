package com.huatai.web.thrift.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.BillBean;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.FilterBean;
import com.huatai.web.bean.FixedBillBean;
import com.huatai.web.bean.QueryDimBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.Target;

/**
 * @功能 {浏览清单业务接口}
 * @作者 MaxBill
 * @时间 2017年7月17日 上午10:34:23
 */
public interface BrowseListService {

	GroDim findTarGroDimByCode(String code);

	TarGroDim findTarGroDimByTar(String tar);

	List<BillBean> findMyBillList(String role, String username);

	List<GroDimDetail> findGroDimDetailList(String groCode, Integer id);

	FixedBillBean findFixedBillList(String org);

	DataPageBean queryMyBillList(Pager<Bill> pager, String role, String username, String billName);

	List<Bill> findBillListByDept(String dept);

	List<BillTarget> findBillTargetByBill(Integer billId);

	List<BillTarget> findBillTargetByBillEasy(Integer billId);

	Target findTargetByCode(String code);

	int deleteBill(Integer id);

	int saveBill(String username, String role, String browseName, String targets);

	int updateBill(String username, Integer browseId, String browseName, String targets);

	DataPageBean findListTargetByRole(Boolean isEdit, Pager<Target> pager, String role, String roleOrg,
			String parentCode, String targetCode, String targetName, Integer billId);

	List<TargetBean> findTargetTypeByRole(String role, String roleOrg);

	List<QueryDimBean> findQueryDimListByTarget(String targetCode);

	List<BillFiltDim> findBillFiltDimByBillAndTarget(Integer billId, String targetCode);

	BillTarget findBillTargetByBillAndTarget(Integer billId, String targetCode);

	List<QueryDim> findQueryDimList();

	QueryDim findQueryDimByCode(String queryDim);

	List<FilterBean> findQueryDimDetailListByDimId(Integer dimId, String type);

	List<FixedBillBean> findFixedListByRole(String role);

	TarInitSql findTarInitSqlByTarAndFun(String targetCode);

	List<BillFiltDim> findBillFiltDimByBillAndTar(Integer billId, String targetCode);

	List<BillFiltDim> findBillFiltDimByBill(Integer billId);

	GroDimDetail findGroDimDetailById(Integer gddId);

	BillGroDim findBillGroDimByBill(Integer billId);

	TarGroDim findUnionTarGroDimByTar(String tar);

	Bill findBillById(Integer id);
}
