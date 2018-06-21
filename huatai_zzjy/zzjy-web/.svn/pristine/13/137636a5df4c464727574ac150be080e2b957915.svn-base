package com.huatai.web.thrift.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.util.SpringUtil;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.bean.BillBean;
import com.huatai.web.bean.DataPageBean;
import com.huatai.web.bean.FilterBean;
import com.huatai.web.bean.FixedBillBean;
import com.huatai.web.bean.QueryDimBean;
import com.huatai.web.bean.TargetBean;
import com.huatai.web.bean.ValBean;
import com.huatai.web.mapper.BillFiltDimMapper;
import com.huatai.web.mapper.BillGroDimMapper;
import com.huatai.web.mapper.BillMapper;
import com.huatai.web.mapper.BillTargetMapper;
import com.huatai.web.mapper.FixedListMapper;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.GroDimMapper;
import com.huatai.web.mapper.QueryDimDetailMapper;
import com.huatai.web.mapper.QueryDimMapper;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.mapper.TarInitSqlMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillExample;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillFiltDimExample;
import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillGroDimExample;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.BillTargetExample;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.GroDimDetailExample;
import com.huatai.web.model.GroDimExample;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.model.QueryDimDetailExample;
import com.huatai.web.model.QueryDimExample;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarGroDimExample;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.TarInitSqlExample;
import com.huatai.web.model.Target;
import com.huatai.web.model.TargetExample;
import com.huatai.web.sql.CommonSqlService;
import com.huatai.web.sql.CommonUtil;
import com.huatai.web.thrift.service.BrowseListService;

/**
 * @功能 {浏览清单业务实现类}
 * @作者 MaxBill
 * @时间 2017年7月17日 上午10:29:57
 */
@Service
public class BrowseListServiceImpl implements BrowseListService {

	@Autowired
	private TarGroDimMapper tarGroDimMapper;
	@Autowired
	private GroDimDetailMapper groDimDetailMapper;
	@Autowired
	private GroDimMapper groDimMapper;
	@Autowired
	private BillMapper billMpper;
	@Autowired
	private BillTargetMapper billTargetMapper;
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private BillFiltDimMapper billFiltDimMapper;
	@Autowired
	private BillGroDimMapper billGroDimMapper;
	@Autowired
	private QueryDimDetailMapper queryDimDetailMapper;
	@Autowired
	private QueryDimMapper queryDimMapper;
	@Autowired
	private TarInitSqlMapper tarInitSqlMapper;
	@Autowired
	private FixedListMapper fixedListMapper;

	private CommonUtil commonUtil;

	private Map deptMap;

	public BrowseListServiceImpl() {
		deptMap = new HashMap();
		deptMap.put("130107", "个业部");
		deptMap.put("130105", "保费部");
		deptMap.put("130101", "机构发展部");
		deptMap.put("101402", "培训部");
		commonUtil = SpringUtil.getBean(CommonUtil.class);
	}

	/**
	 * @功能 {按编码查询分组维度}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午10:13:37
	 */
	public GroDim findTarGroDimByCode(String code) {
		GroDimExample groDimExample = new GroDimExample();
		groDimExample.createCriteria().andOpTypeNotEqualTo("D").andGroDimTypeCodeEqualTo(code);
		List<GroDim> groDimList = this.groDimMapper.selectByExample(groDimExample);
		if (null != groDimList && groDimList.size() == 1) {
			return groDimList.get(0);
		} else {
			return null;
		}
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
	 * @功能 {查询最小功能维度}
	 * @作者 MaxBill
	 * @时间 2017年7月17日 上午10:52:20
	 */
	public List<GroDimDetail> findGroDimDetailList(String groCode, Integer id) {
		GroDimDetailExample groDimDetailExample = new GroDimDetailExample();
		GroDimExample groDimExample = new GroDimExample();
		groDimExample.createCriteria().andOpTypeNotEqualTo("D").andGroDimTypeCodeEqualTo(groCode);
		groDimDetailExample.createCriteria()
				.andGdIdEqualTo(this.groDimMapper.selectByExample(groDimExample).get(0).getGdId())
				.andGddIdLessThanOrEqualTo(id);
		return this.groDimDetailMapper.selectByExample(groDimDetailExample);
	}

	/**
	 * @功能 {查询固定清单}
	 * @作者 MaxBill
	 * @时间 2017年7月31日 上午10:50:29
	 */
	public FixedBillBean findFixedBillList(String org) {
		BillExample billExample = new BillExample();
		billExample.createCriteria().andOpTypeNotEqualTo("D").andDeptCodeEqualTo(org);
		List<Bill> billList = this.billMpper.selectByExample(billExample);
		if (null != billList) {
			List<BillBean> billBeanList = new ArrayList<>();
			for (Bill bill : billList) {
				BillBean billBean = new BillBean();
				billBean.setId(bill.getBillId());
				billBean.setBrowseName(bill.getBillName());
				billBeanList.add(billBean);
			}
			FixedBillBean fixedBillBean = new FixedBillBean();
			fixedBillBean.setTitle(org + "固定清单");
			fixedBillBean.setCode(org);
			fixedBillBean.setList(billBeanList);
			return fixedBillBean;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {我的清单}
	 * @作者 MaxBill
	 * @时间 2017年7月28日 下午5:59:03
	 */
	public List<BillBean> findMyBillList(String role, String username) {
		BillExample billExample = new BillExample();
		billExample.createCriteria().andOpTypeNotEqualTo("D").andUsernameEqualTo(username).andRoleCodeEqualTo(role);
		billExample.setOrderByClause("CREATE_TIME DESC");
		List<Bill> billList = this.billMpper.selectByExample(billExample);
		List<BillBean> billBeanList = new ArrayList<BillBean>();
		if (null != billList) {
			for (Bill bill : billList) {
				BillBean billBean = new BillBean();
				billBean.setId(bill.getBillId());
				billBean.setBrowseName(bill.getBillName());
				billBeanList.add(billBean);
			}
		}
		return billBeanList;
	}

	/**
	 * @功能 {查询我的清单列表}
	 * @作者 MaxBill
	 * @时间 2017年7月31日 上午11:13:34
	 */
	public DataPageBean queryMyBillList(Pager<Bill> pager, String role, String username, String billName) {
		DataPageBean dataPage = new DataPageBean();
		Pager<Bill> billPage = this.billMpper.findBillByPage(pager, role, username, billName);
		dataPage.setCurPage(billPage.getCurrentPage());
		dataPage.setPageSize(billPage.getPageSize());
		dataPage.setTotal(billPage.getTotalCount());
		dataPage.setTotalPage(billPage.getTotalPage());
		List<Bill> billList = billPage.getPageItems();
		List<BillBean> billBeanList = new ArrayList<BillBean>();
		if (null != billList) {
			for (Bill bill : billList) {
				BillBean billBean = new BillBean();
				billBean.setId(bill.getBillId());
				billBean.setBrowseName(bill.getBillName());
				billBeanList.add(billBean);
			}
			dataPage.setList(billBeanList);
			return dataPage;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按部门查询部门固定清单列表}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午10:36:01
	 */
	public List<Bill> findBillListByDept(String dept) {
		BillExample billExample = new BillExample();
		billExample.createCriteria().andOpTypeNotEqualTo("D").andDeptCodeEqualTo(dept);
		return this.billMpper.selectByExample(billExample);
	}

	/**
	 * @功能 {按清单id查询指标数据}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午11:07:38
	 */
	public List<BillTarget> findBillTargetByBillEasy(Integer billId) {
		BillTargetExample billTargetExample = new BillTargetExample();
		billTargetExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId);
		billTargetExample.setOrderByClause("BT_ID ASC");
		return this.billTargetMapper.selectByExample(billTargetExample);
	}

	/**
	 * @功能 {按清单id查询指标数据}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午11:07:38
	 */
	public List<BillTarget> findBillTargetByBill(Integer billId) {
		return this.billTargetMapper.findBillTargetByBill(billId);
	}

	/**
	 * @功能 {按code查询指标}
	 * @作者 MaxBill
	 * @时间 2017年8月1日 上午11:29:15
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
	 * @功能 {删除清单数据(事务)}
	 * @作者 MaxBill
	 * @时间 2017年8月4日 下午1:28:53
	 */
	@Transactional
	public int deleteBill(Integer id) {
		Bill bill = this.billMpper.selectByPrimaryKey(id);
		bill.setModifyTime(new Date());
		bill.setOpType("D");
		int delFlag = this.billMpper.updateByPrimaryKeySelective(bill);
		if (delFlag == 1) {
			// 删除清单筛选维度数据
			BillFiltDimExample billFiltDimExample = new BillFiltDimExample();
			billFiltDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(id);
			List<BillFiltDim> billFiltDimList = this.billFiltDimMapper.selectByExample(billFiltDimExample);
			if (null != billFiltDimList) {
				billFiltDimList.stream().forEach(billFiltDim -> {
					billFiltDim.setModifyTime(new Date());
					billFiltDim.setOpType("D");
					this.billFiltDimMapper.updateByPrimaryKeySelective(billFiltDim);
				});
			}
			// 删除清单分组维度数据
			BillGroDimExample billGroDimExample = new BillGroDimExample();
			billGroDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(id);
			List<BillGroDim> billGroDimList = this.billGroDimMapper.selectByExample(billGroDimExample);
			if (null != billGroDimList) {
				billGroDimList.stream().forEach(billGroDim -> {
					billGroDim.setModifyTime(new Date());
					billGroDim.setOpType("D");
					this.billGroDimMapper.updateByPrimaryKeySelective(billGroDim);
				});
			}
			// 删除清单关联指标数据
			BillTargetExample billTargetExample = new BillTargetExample();
			billTargetExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(id);
			List<BillTarget> billTargetList = this.billTargetMapper.selectByExample(billTargetExample);
			if (null != billTargetList) {
				billTargetList.stream().forEach(billTarget -> {
					billTarget.setModifyTime(new Date());
					billTarget.setOpType("D");
					this.billTargetMapper.updateByPrimaryKeySelective(billTarget);
				});
			}
		}
		return delFlag;
	}

	/**
	 * @功能 {添加我的清单数据}
	 * @作者 MaxBill
	 * @时间 2017年8月4日 下午2:35:41
	 */
	@Transactional
	public int saveBill(String username, String role, String browseName, String targets) {
		// 1.添加自定义清单数据
		Bill bill = new Bill();
		bill.setBillName(browseName);
		bill.setUsername(username);
		bill.setRoleCode(role);
		bill.setCreateTime(new Date());
		bill.setBillType("2");
		bill.setRemark(username + "的自定义清单：" + browseName);
		bill.setOpType("A");
		int addFlag = this.billMpper.insertSelective(bill);
		if (addFlag == 1) {
			List<JSONObject> targetList = JSON.parseArray(targets, JSONObject.class);
			// 最低机构
			String commonOrg = new CommonSqlService().getCommonOrg(3, targetList);
			for (int i = 0; i < targetList.size(); i++) {
				JSONObject target = targetList.get(i);
				String targetCode = target.getString("code");
				JSONObject setObject = target.getJSONObject("set");
				// 2.添加清单关联指标数据
				BillTarget billTarget = new BillTarget();
				billTarget.setBillId(bill.getBillId());
				billTarget.setTargetCode(targetCode);
				if (null != setObject && !setObject.isEmpty()) {
					JSONObject termObject = setObject.getJSONObject("term");
					if (null != termObject && !termObject.isEmpty()) {
						String valType = termObject.getString("code");
						billTarget.setFormulaType(valType);
						List<Float> valueList = JSON.parseArray(termObject.getString("value"), Float.class);
						switch (valType) {
						case "lt":// 指标值小于
							billTarget.setMaxVal(valueList.get(0));
							break;
						case "gt":// 指标值大于
							billTarget.setMinVal(valueList.get(0));
							break;
						case "range":
							billTarget.setMinVal(valueList.get(0));
							billTarget.setMaxVal(valueList.get(1));
							break;
						}
					} else {
						billTarget.setFormulaType("no");
					}
				} else {
					billTarget.setFormulaType("no");
				}
				billTarget.setCreateTime(new Date());
				billTarget.setOpType("A");
				this.billTargetMapper.insertSelective(billTarget);
				// 3.添加筛选维度
				if (null != setObject && !setObject.isEmpty()) {
					List<JSONObject> reqList = JSON.parseArray(setObject.getString("req"), JSONObject.class);
					if (null != reqList) {
						reqList.stream().forEach(req -> {
							BillFiltDim billFiltDim = new BillFiltDim();
							billFiltDim.setBillId(bill.getBillId());
							billFiltDim.setTargetCode(targetCode);
							billFiltDim.setFiltDimCode(req.getString("code"));
							billFiltDim.setCreateTime(new Date());
							billFiltDim.setOpType("A");
							this.billFiltDimMapper.insertSelective(billFiltDim);
						});
					}
				}
			}
			// 4.添加分组维度数据
			BillGroDim billGroDim = new BillGroDim();
			billGroDim.setBillId(bill.getBillId());
			billGroDim.setGroupType("ORG_GROUP");
			billGroDim.setGroupDetailCode(commonOrg);
			billGroDim.setCreateTime(new Date());
			billGroDim.setOpType("A");
			this.billGroDimMapper.insertSelective(billGroDim);
		}
		return addFlag;
	}

	/**
	 * @功能 {编辑我的清单数据}
	 * @作者 MaxBill
	 * @时间 2017年8月25日 下午5:31:22
	 */
	@Transactional
	public int updateBill(String username, Integer browseId, String browseName, String targets) {
		// 1.更新自定义清单数据
		Bill bill = this.billMpper.selectByPrimaryKey(browseId);
		bill.setBillName(browseName);
		bill.setUsername(username);
		bill.setModifyTime(new Date());
		bill.setOpType("U");
		int updFlag = this.billMpper.updateByPrimaryKeySelective(bill);
		if (updFlag == 1) {
			List<JSONObject> targetList = JSON.parseArray(targets, JSONObject.class);
			for (int i = 0; i < targetList.size(); i++) {
				JSONObject target = targetList.get(i);
				String targetCode = target.getString("code");
				String targetStatus = target.getString("status");
				JSONObject setObject = target.getJSONObject("set");
				// 2.更新清单关联指标数据
				switch (targetStatus) {
				case "1":
					// 新关联指标
					BillTarget addBillTarget = new BillTarget();
					addBillTarget.setBillId(bill.getBillId());
					addBillTarget.setTargetCode(targetCode);
					if (null != setObject && !setObject.isEmpty()) {
						JSONObject termObject1 = setObject.getJSONObject("term");
						if (null != termObject1 && !termObject1.isEmpty()) {
							String valType1 = termObject1.getString("code");
							addBillTarget.setFormulaType(valType1);
							List<Float> valueList1 = JSON.parseArray(termObject1.getString("value"), Float.class);
							switch (valType1) {
							case "lt":// 指标值小于
								addBillTarget.setMaxVal(valueList1.get(0));
								break;
							case "gt":// 指标值大于
								addBillTarget.setMinVal(valueList1.get(0));
								break;
							case "range":
								addBillTarget.setMinVal(valueList1.get(0));
								addBillTarget.setMaxVal(valueList1.get(1));
								break;
							}
						} else {
							addBillTarget.setFormulaType("no");
						}
					} else {
						addBillTarget.setFormulaType("no");
					}
					addBillTarget.setCreateTime(new Date());
					addBillTarget.setOpType("A");
					this.billTargetMapper.insertSelective(addBillTarget);
					// 添加筛选维度
					if (null != setObject && !setObject.isEmpty()) {
						List<JSONObject> reqList = JSON.parseArray(setObject.getString("req"), JSONObject.class);
						if (null != reqList) {
							reqList.stream().forEach(req -> {
								BillFiltDim billFiltDim = new BillFiltDim();
								billFiltDim.setBillId(bill.getBillId());
								billFiltDim.setTargetCode(targetCode);
								billFiltDim.setFiltDimCode(req.getString("code"));
								billFiltDim.setCreateTime(new Date());
								billFiltDim.setOpType("A");
								this.billFiltDimMapper.insertSelective(billFiltDim);
							});
						}
					}
					break;
				case "2":
					// 取消指标关联
					BillTargetExample billTargetExample = new BillTargetExample();
					billTargetExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(browseId)
							.andTargetCodeEqualTo(targetCode);
					List<BillTarget> billTargetList = this.billTargetMapper.selectByExample(billTargetExample);
					if (null != billTargetList && billTargetList.size() == 1) {
						BillTarget updBillTarget = billTargetList.get(0);
						updBillTarget.setModifyTime(new Date());
						updBillTarget.setOpType("D");
						this.billTargetMapper.updateByPrimaryKeySelective(updBillTarget);
					}
					// 删除清单筛选维度数据
					BillFiltDimExample billFiltDimExample = new BillFiltDimExample();
					billFiltDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(bill.getBillId())
							.andTargetCodeEqualTo(targetCode);
					List<BillFiltDim> billFiltDimList = this.billFiltDimMapper.selectByExample(billFiltDimExample);
					if (null != billFiltDimList) {
						billFiltDimList.stream().forEach(billFiltDim -> {
							billFiltDim.setModifyTime(new Date());
							billFiltDim.setOpType("D");
							this.billFiltDimMapper.updateByPrimaryKeySelective(billFiltDim);
						});
					}
					break;
				case "3":
					break;
				case "4":
					// 更新关联指标
					BillTarget updBillTarget = this.findBillTargetByBillAndTarget(bill.getBillId(), targetCode);
					updBillTarget.setBillId(bill.getBillId());
					updBillTarget.setTargetCode(targetCode);
					updBillTarget.setModifyTime(new Date());
					updBillTarget.setOpType("U");
					if (null != setObject && !setObject.isEmpty()) {
						JSONObject termObject4 = setObject.getJSONObject("term");
						if (null != termObject4 && !termObject4.isEmpty()) {
							String valType4 = termObject4.getString("code");
							List<Float> valueList4 = JSON.parseArray(termObject4.getString("value"), Float.class);
							updBillTarget.setFormulaType(valType4);
							// 修改指标关联
							switch (valType4) {
							case "lt":// 指标值小于
								updBillTarget.setMaxVal(valueList4.get(0));
								updBillTarget.setMinVal(null);
								break;
							case "gt":// 指标值大于
								updBillTarget.setMinVal(valueList4.get(0));
								updBillTarget.setMaxVal(null);
								break;
							case "range":
								updBillTarget.setMinVal(valueList4.get(0));
								updBillTarget.setMaxVal(valueList4.get(1));
								break;
							}
						} else {
							updBillTarget.setFormulaType("no");
						}
						this.billTargetMapper.updateByPrimaryKeySelective(updBillTarget);
						// 更新筛选维度
						List<JSONObject> reqListUpd = JSON.parseArray(setObject.getString("req"), JSONObject.class);
						if (null != reqListUpd) {
							for (JSONObject reqUpd : reqListUpd) {
								String reqCode = reqUpd.getString("code");
								String reqStatus = reqUpd.getString("status");
								switch (reqStatus) {
								case "1":
									// 新增筛选维度
									BillFiltDim addBillFiltDim = new BillFiltDim();
									addBillFiltDim.setBillId(bill.getBillId());
									addBillFiltDim.setTargetCode(targetCode);
									addBillFiltDim.setFiltDimCode(reqUpd.getString("code"));
									addBillFiltDim.setCreateTime(new Date());
									addBillFiltDim.setOpType("A");
									this.billFiltDimMapper.insertSelective(addBillFiltDim);
									break;
								case "2":
									// 取消维度关联
									BillFiltDimExample billFiltDimExampleDel = new BillFiltDimExample();
									billFiltDimExampleDel.createCriteria().andOpTypeNotEqualTo("D")
											.andBillIdEqualTo(browseId).andTargetCodeEqualTo(targetCode)
											.andFiltDimCodeEqualTo(reqCode);
									List<BillFiltDim> billFiltDimListDel = this.billFiltDimMapper
											.selectByExample(billFiltDimExampleDel);
									if (null != billFiltDimListDel && billFiltDimListDel.size() == 1) {
										BillFiltDim updBillFiltDim = billFiltDimListDel.get(0);
										updBillFiltDim.setModifyTime(new Date());
										updBillFiltDim.setOpType("D");
										this.billFiltDimMapper.updateByPrimaryKeySelective(updBillFiltDim);
									}
									break;
								}
							}
						}
					} else {
						updBillTarget.setFormulaType("no");
					}
				}
			}
			BillTargetExample billTargetExample = new BillTargetExample();
			billTargetExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(browseId);
			List<BillTarget> billTargetList = billTargetMapper.selectByExample(billTargetExample);
			String commonOrg = new CommonSqlService().getCommonOrg(4, billTargetList);
			// 4.更新分组维度数据
			BillGroDimExample billGroDimExample = new BillGroDimExample();
			billGroDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(browseId);
			BillGroDim billGroDim = billGroDimMapper.selectByExample(billGroDimExample).get(0);
			billGroDim.setGroupDetailCode(commonOrg);
			billGroDim.setCreateTime(new Date());
			billGroDim.setOpType("U");
			this.billGroDimMapper.updateByPrimaryKey(billGroDim);
		}
		return updFlag;
	}

	/**
	 * @功能 {按筛选维度查询筛选维度详情列表}
	 * @作者 MaxBill
	 * @时间 2017年8月25日 上午11:36:10
	 */
	public List<QueryDimDetail> findQueryDimDetailsByQueryDim(String queryDimCode) {
		QueryDim queryDim = findQueryDimByCode(queryDimCode);
		QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
		queryDimDetailExample.createCriteria().andOpTypeNotEqualTo("D").andQdIdEqualTo(queryDim.getQdId());
		return this.queryDimDetailMapper.selectByExample(queryDimDetailExample);
	}

	/**
	 * @功能 {按code查询查询维度}
	 * @作者 MaxBill
	 * @时间 2017年8月25日 上午11:35:04
	 */
	public QueryDim findQueryDimByCode(String queryDim) {
		QueryDimExample queryDimExample = new QueryDimExample();
		queryDimExample.createCriteria().andOpTypeNotEqualTo("D").andQueryDimCodeEqualTo(queryDim);
		List<QueryDim> queryDimList = this.queryDimMapper.selectByExample(queryDimExample);
		if (null != queryDimList && queryDimList.size() == 1) {
			return queryDimList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询自定义板块的二级指标列表分类}
	 * @作者 MaxBill
	 * @时间 2017年9月29日 下午4:57:12
	 */
	public List<TargetBean> findTargetTypeByRole(String role, String roleOrg) {
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		List<Target> targetList = this.targetMapper.findTargetTypeByRole(role, commonUtil.getRoleOrgCode(roleOrg));
		if (null != targetList) {
			for (Target target : targetList) {
				// 封装二级指标
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBeanList.add(targetBean);
			}
			// 临时补合规
			TargetBean targetBean = new TargetBean();
			targetBean.setName("合规主题");
			targetBean.setCode("T80");
			targetBeanList.add(targetBean);
			return targetBeanList;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * @功能 {浏览清单添加/编辑查询指标列表}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 上午11:09:54
	 */
	public DataPageBean findListTargetByRole(Boolean isEdit, Pager<Target> pager, String role, String roleOrg,
			String parentCode, String targetCode, String targetName, Integer billId) {
		DataPageBean dataPage = new DataPageBean();
		Pager<Target> targetPage = null;
		if (isEdit) {
			targetPage = this.targetMapper.findEditListTargetByRoleWithPage(pager, role,
					commonUtil.getRoleOrgCode(roleOrg), parentCode, targetCode, targetName, billId);
		} else {
			targetPage = this.targetMapper.findSaveListTargetByRoleWithPage(pager, role,
					commonUtil.getRoleOrgCode(roleOrg), parentCode, targetCode, targetName);
		}
		dataPage.setCurPage(targetPage.getCurrentPage());
		dataPage.setPageSize(targetPage.getPageSize());
		dataPage.setTotal(targetPage.getTotalCount());
		dataPage.setTotalPage(targetPage.getTotalPage());
		List<Target> targetList = targetPage.getPageItems();
		List<TargetBean> targetBeanList = new ArrayList<TargetBean>();
		if (null != targetList) {
			for (Target target : targetList) {
				TargetBean targetBean = new TargetBean();
				targetBean.setName(target.getTargetName());
				targetBean.setCode(target.getTargetCode());
				targetBean.setDept(target.getDeptCode());
				targetBean.setPname(target.getRemark());
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
				}
				targetBean.setChecked(false);
				if (null != target.getIsRealtime() && target.getIsRealtime().equals("1")) {
					targetBean.setChecked(true);
				}
				targetBeanList.add(targetBean);
			}
			dataPage.setList(targetBeanList);
			return dataPage;
		} else {
			return null;
		}
	}

	/**
	 * @功能 {查询筛选维度列表}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 下午2:17:23
	 */
	public List<QueryDimBean> findQueryDimListByTarget(String targetCode) {
		List<QueryDimBean> queryDimBeanList = new ArrayList<QueryDimBean>();
		List<QueryDim> queryDimList = this.queryDimMapper.findQueryDimListByTarget(targetCode);
		if (null != queryDimList) {
			queryDimList.stream().forEach(queryDim -> {
				QueryDimBean queryDimBean = new QueryDimBean();
				queryDimBean.setId(queryDim.getQdId());
				queryDimBean.setType(queryDim.getQueryDimShowType());
				queryDimBean.setCode(queryDim.getQueryDimCode());
				queryDimBean.setName(queryDim.getQueryDimName());
				queryDimBean.setChecked(false);
				queryDimBean.setActive(false);
				queryDimBeanList.add(queryDimBean);
			});
		}
		return queryDimBeanList;
	}

	/**
	 * @功能 {按清单和指标查询清单指标数据}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 下午2:52:56
	 */
	public BillTarget findBillTargetByBillAndTarget(Integer billId, String targetCode) {
		BillTargetExample billTargetExample = new BillTargetExample();
		billTargetExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId)
				.andTargetCodeEqualTo(targetCode);
		List<BillTarget> billTargetList = this.billTargetMapper.selectByExample(billTargetExample);
		if (null != billTargetList && billTargetList.size() == 1) {
			return billTargetList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按清单id和指标查询维度}
	 * @作者 MaxBill
	 * @时间 2017年8月26日 下午2:32:49
	 */
	public List<BillFiltDim> findBillFiltDimByBillAndTarget(Integer billId, String targetCode) {
		BillFiltDimExample billFiltDimExample = new BillFiltDimExample();
		billFiltDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId)
				.andTargetCodeEqualTo(targetCode);
		return this.billFiltDimMapper.selectByExample(billFiltDimExample);
	}

	/**
	 * @功能 {查询全部的筛选维度}
	 * @作者 MaxBill
	 * @时间 2017年8月29日 下午2:32:39
	 */
	public List<QueryDim> findQueryDimList() {
		QueryDimExample queryDimExample = new QueryDimExample();
		queryDimExample.createCriteria().andOpTypeNotEqualTo("D");
		return this.queryDimMapper.selectByExample(queryDimExample);
	}

	/**
	 * @功能 {按查询维度查询该查询维度的}
	 * @作者 MaxBill
	 * @时间 2017年8月29日 下午3:04:26
	 */
	public List<FilterBean> findQueryDimDetailListByDimId(Integer dimId, String type) {
		List<FilterBean> filterBeanList = new ArrayList<FilterBean>();
		QueryDimDetailExample queryDimDetailExample = new QueryDimDetailExample();
		queryDimDetailExample.createCriteria().andOpTypeNotEqualTo("D").andQdIdEqualTo(dimId);
		List<QueryDimDetail> queryDimDetailList = this.queryDimDetailMapper.selectByExample(queryDimDetailExample);
		if (null != queryDimDetailList) {
			switch (type) {
			case "select2":
				List<ValBean> valBeanList = new ArrayList<ValBean>();
				for (QueryDimDetail queryDimDetail : queryDimDetailList) {
					ValBean valBean = new ValBean();
					valBean.setName(queryDimDetail.getQueryDimName());
					valBean.setCode(queryDimDetail.getKeyCode());
					valBeanList.add(valBean);
				}
				QueryDim queryDim = this.queryDimMapper.selectByPrimaryKey(dimId);
				FilterBean filterBean1 = new FilterBean();
				filterBean1.setLabel("");
				filterBean1.setName("");
				filterBean1.setCode(queryDim.getQueryDimCode());
				filterBean1.setType("select");
				filterBean1.setValue(valBeanList);
				filterBeanList.add(filterBean1);
				FilterBean filterBean2 = new FilterBean();
				filterBean2.setLabel("");
				filterBean2.setName("");
				filterBean2.setCode(queryDim.getQueryDimCode());
				filterBean2.setType("select");
				filterBean1.setValue(valBeanList);
				filterBeanList.add(filterBean2);
				break;
			case "select":
				List<ValBean> selectValBeanList = new ArrayList<ValBean>();
				for (QueryDimDetail queryDimDetail : queryDimDetailList) {
					ValBean valBean = new ValBean();
					valBean.setName(queryDimDetail.getVal());
					valBean.setCode(queryDimDetail.getKeyCode());
					selectValBeanList.add(valBean);
				}
				FilterBean selectFilterBean = new FilterBean();
				selectFilterBean.setLabel("");
				selectFilterBean.setName("");
				selectFilterBean.setType("select");
				selectFilterBean.setValue(selectValBeanList);
				filterBeanList.add(selectFilterBean);
				break;
			case "checkbox":
				for (QueryDimDetail queryDimDetail : queryDimDetailList) {
					FilterBean filterBean = new FilterBean();
					filterBean.setCode(queryDimDetail.getKeyCode());
					filterBean.setName(queryDimDetail.getVal());
					filterBean.setType(type);
					filterBeanList.add(filterBean);
				}
				break;
			}
		}
		return filterBeanList;
	}

	/**
	 * @功能 {按角色查询固定清单}
	 * @作者 MaxBill
	 * @时间 2017年9月1日 下午1:35:02
	 */
	public List<FixedBillBean> findFixedListByRole(String role) {
		List<FixedList> fixedListDept = this.fixedListMapper.findFixedListDeptByRole(role);
		List<FixedBillBean> fixedBillBeanList = new ArrayList<FixedBillBean>();
		if (null != fixedListDept) {
			for (FixedList fixedListDeptTemp : fixedListDept) {
				List<FixedList> fixedList = this.fixedListMapper.findFixedListByRoleAndDept(role,
						fixedListDeptTemp.getFlDeptCode());
				List<BillBean> billBeanList = new ArrayList<BillBean>();
				for (FixedList fixedListTemp : fixedList) {
					BillBean billBean = new BillBean();
					billBean.setCode(fixedListTemp.getFlCode());
					billBean.setBrowseName(fixedListTemp.getFlName());
					billBeanList.add(billBean);
				}
				String deptCode = fixedListDeptTemp.getFlDeptCode();
				FixedBillBean fixedBillBean = new FixedBillBean();
				fixedBillBean.setTitle(deptMap.get(deptCode) + "固定清单");
				fixedBillBean.setCode(fixedListDeptTemp.getFlDeptCode());
				fixedBillBean.setList(billBeanList);
				fixedBillBeanList.add(fixedBillBean);
			}
		}
		return fixedBillBeanList;
	}

	/**
	 * @功能 {查询自定义清单的初始sql}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 上午11:37:03
	 */
	public TarInitSql findTarInitSqlByTarAndFun(String targetCode) {
		// sql拼装
		TarInitSqlExample tarInitSqlExample = new TarInitSqlExample();
		tarInitSqlExample.createCriteria().andOpTypeNotEqualTo("D").andTargetCodeEqualTo(targetCode)
				.andGroupTypeEqualTo("ORG_GROUP").andDateTypeEqualTo("DAY").andFunIdsEqualTo("02");
		return this.tarInitSqlMapper.selectByExample(tarInitSqlExample).get(0);
	}

	/**
	 * @功能 {按清单id和指标code查询清单指标的维度}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 下午12:43:48
	 */
	public List<BillFiltDim> findBillFiltDimByBillAndTar(Integer billId, String targetCode) {
		BillFiltDimExample billFiltDimExample = new BillFiltDimExample();
		billFiltDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId)
				.andTargetCodeEqualTo(targetCode);
		return this.billFiltDimMapper.selectByExample(billFiltDimExample);
	}

	/**
	 * @功能 {按照清单查询}
	 * @作者 MaxBill
	 * @时间 2017年9月21日 下午4:50:49
	 */
	public List<BillFiltDim> findBillFiltDimByBill(Integer billId) {
		return billFiltDimMapper.findBillFiltDimByBill(billId);
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
	 * @功能 {按id查询指标分组维度}
	 * @作者 MaxBill
	 * @时间 2017年9月22日 下午1:35:21
	 */
	public BillGroDim findBillGroDimByBill(Integer billId) {
		BillGroDimExample billGroDimExample = new BillGroDimExample();
		billGroDimExample.createCriteria().andOpTypeNotEqualTo("D").andBillIdEqualTo(billId);
		List<BillGroDim> billGroDimList = this.billGroDimMapper.selectByExample(billGroDimExample);
		if (null != billGroDimList && billGroDimList.size() == 1) {
			return billGroDimList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @功能 {按id查询清单}
	 * @作者 MaxBill
	 * @时间 2017年9月22日 下午1:35:21
	 */
	public Bill findBillById(Integer id) {
		return this.billMpper.selectByPrimaryKey(id);
	}

}
