package com.huatai.web.controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Bill;
import com.huatai.web.model.BillFiltDim;
import com.huatai.web.model.BillGroDim;
import com.huatai.web.model.BillRole;
import com.huatai.web.model.BillSql;
import com.huatai.web.model.BillTarget;
import com.huatai.web.model.BillTargetDetail;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimCodeStr;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.Target;
import com.huatai.web.service.BillFixService;
import com.huatai.web.service.CoreTargetService;
import com.huatai.web.service.GroDimService;
import com.huatai.web.service.QueryDimService;
import com.huatai.web.service.TargetService;

/**
 * @describe 固定清单 
 * @author yangbo
 */
@Controller
@RequestMapping("admin/bill")
public class BillFixController {

	@Autowired
	private BillFixService billFixService;
	@Autowired
	private TargetService targetService;
	@Autowired
	private QueryDimService queryDimService;
	@Autowired
	private GroDimService groDimService;
	@Autowired
	private CoreTargetService coreTargetService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		String deptCode = SessionContextUtils.getCurrentUser().getDeptCode();
		List<DeptInfo> depts = billFixService.selectDeptInfo();
		mv.addObject("deptInfos", JSON.toJSON(depts));
		List<DeptInfo> ls = coreTargetService.findByDeptCodeStr(deptCode);
		mv.addObject("depts", ls);
		mv.setViewName("/admin/bill/index");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Bill> getPager(Pager<Bill> pager, Bill record) {
		String deptCode = SessionContextUtils.getCurrentUser().getDeptCode();
		String dcStr = new String();
		if (deptCode.indexOf(",") > 0) {
			String[] dc = deptCode.split(",");
			for (String d : dc) {
				dcStr += "'"+d+"',";
			}
			dcStr = dcStr.substring(0, dcStr.length()-1);
		} else {
			dcStr = "'"+deptCode+"'";
		}
		record.setDeptCode(dcStr);
		record.setBillType("1");
		return billFixService.findByPager(pager, record);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) throws ParseException {
		billFixService.deleteByPrimaryKey(id);
		billFixService.deleteByBillId(id);// 清单指标 - 清单筛选维度 - 清单分组维度 - 清单与SQL
	}
	
	@ResponseBody
	@RequestMapping(value = "targetlist")
	public Pager<Target> getTargetPager(Pager<Target> pager, Target record, @RequestParam("deptCode") String deptCode) {
		if ("".equals(deptCode)) {
			String dea = "";
			deptCode = SessionContextUtils.getCurrentUser().getDeptCode();
			String[] des = deptCode.split(",");
			for (String de : des) {
				dea += "'"+de+"',";
			}
			deptCode = dea.substring(0, dea.length()-1);
		} else {
			deptCode = "'"+deptCode+"'";
		}
		record.setDeptCode(deptCode);
		return targetService.findTargetByPage(pager, record);
	}
	
	@ResponseBody
	@RequestMapping(value = "getDimlist", method=RequestMethod.POST)
	public List<QueryDim> getDimlist(@RequestParam("targetCode") String targetCode) {
		return queryDimService.findByTargetCode(targetCode);
	}
	
	@ResponseBody
	@RequestMapping(value = "getPublist", method=RequestMethod.POST)
	public List<GroDim> getPublist() {
		return groDimService.findGroDimAll();
	}
	
	@ResponseBody
	@RequestMapping(value = "alltree", method=RequestMethod.POST)
	public List<Map<String, Object>> alltree() {
		List<Map<String, Object>> lmb = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		map1.put("id", 2);
		map1.put("name", "你好");
		map1.put("pid", 1);
		map2.put("id", 3);
		map2.put("name", "11你好");
		map2.put("pid", 1);
		lmb.add(map1);
		lmb.add(map2);
		return lmb;
	}
	
	@ResponseBody
	@RequestMapping(value = "saveAuthority", method=RequestMethod.POST)
	public void saveAuthority(@RequestParam("billId") String billId, @RequestParam("deptCodes") String deptCodes) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
		String loginId = SessionContextUtils.getLoginName().toString();
		if (!"".equals(deptCodes)) {
			BillRole br = new BillRole();
			br.setBillId(billId);
			br.setDeptCode(deptCodes);
			br.setOpType("A");
			br.setCreateTime(now);
			br.setModifyTime(now);
			br.setCreatorId(loginId);
			br.setModifierId(loginId);
			billFixService.insertBillRole(br);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "getAuthByBillId", method=RequestMethod.POST)
	public List<BillRole> getAuthByBillId(@RequestParam("billId") String billId) throws ParseException {
		return billFixService.selectBillRoleByBillid(billId);
	}
	
	// 获得清单对应指标属性{"formula":"more&11#12|SJJG,range&11~23#32|SJJG_01,","usualDim":"JFQC|SJJG,XZLB|SJJG,CPDM|SJJG_01,QD|SJJG_01,"}
	@ResponseBody
	@RequestMapping(value = "getBtListByQid", method=RequestMethod.POST)
	public Map<String, String> getBtListByQid(@RequestParam("qid") String qid, @RequestParam("deptCode") String deptCode) {
		NumberFormat nf = NumberFormat.getInstance();
		List<BillTargetDetail> tarMap = billFixService.getTarListByQid(qid);
		String formula = new String();
		String usualDim = new String();
		for (BillTargetDetail btd : tarMap) {
			String min = nf.format(Float.parseFloat(btd.getMinVal()==null?"0":btd.getMinVal())).replaceAll(",", "");
			String max = nf.format(Float.parseFloat(btd.getMaxVal()==null?"0":btd.getMaxVal())).replaceAll(",", "");
			String one = "";
			if ("1".equals(btd.getFormulaType())) {
				one = "more&"+min+"#"+btd.getSort().replaceAll(",", "")+"|"+btd.getTargetCode()+",";
			} else if ("2".equals(btd.getFormulaType())) {
				one = "less&"+max+"#"+btd.getSort().replaceAll(",", "")+"|"+btd.getTargetCode()+",";
			} else {
				one = "range&"+min+"~"+max+"#"+btd.getSort().replaceAll(",", "")+"|"+btd.getTargetCode()+",";
			}
			formula += one;
			String two = "";
			String[] dimL = btd.getFiltDimCode().split(",");
			for (String dim : dimL) {
				two += dim+"|"+btd.getTargetCode()+",";
			}
			usualDim += two;
		}
		Map<String, String> map = new HashMap<>();
		map.put("formula", formula);
		map.put("usualDim", usualDim);
		map.put("billName", tarMap.get(0).getBillName());
		map.put("billId", tarMap.get(0).getBillId().toString());
		map.put("remark", tarMap.get(0).getRemark());
		return map;
	}
	
	// 保存
	@ResponseBody
	@Transactional
	@RequestMapping(value = "saveTarlistBill", method=RequestMethod.POST)
	public boolean saveTarlistBill(@RequestParam("billName") String billName,@RequestParam("remark") String remark,
			@RequestParam("deptCode") String deptCode,@RequestParam("dataJson") String data) throws ParseException {
		JSONObject dataJson = JSONObject.parseObject(data);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
		String loginId = SessionContextUtils.getLoginName().toString();
		Bill bill = new Bill();
		bill.setBillName(billName);
		bill.setDeptCode(deptCode);
		bill.setBillType("1");
		bill.setRemark(remark);
		bill.setOpType("A");
		bill.setCreateTime(now);
		bill.setModifyTime(now);
		bill.setCreatorId(loginId);
		bill.setModifierId(loginId);
		billFixService.insert(bill);
		//获取新增清单主键
		int billId = bill.getBillId();
		
		//清单与指标关联
		String formula = (String)dataJson.get("formula");
		formula = formula.substring(0, formula.length()-1);
		String[] btlist = formula.split(",");
		for (String bt : btlist) {
			BillTarget billT = new BillTarget();
			billT.setBillId(billId);
			String[] s1 = bt.split("\\|");
			billT.setTargetCode(s1[1]);
			String[] s2 = s1[0].split("\\#");
			billT.setSort(Integer.parseInt(s2[1]));
			String[] s3 = s2[0].split("\\&");
			String fType = s3[0].trim();// 1大于；2小于；3范围之间
			if (fType.equalsIgnoreCase("more")) {
				billT.setFormulaType("1");
				billT.setMinVal(Float.parseFloat(s3[1]));
			} else if (fType.equalsIgnoreCase("less")) {
				billT.setFormulaType("2");
				billT.setMaxVal(Float.parseFloat(s3[1]));
			} else {
				billT.setFormulaType("3");
				String[] im = s3[1].split("\\~");
				billT.setMinVal(Float.parseFloat(im[0]));
				billT.setMaxVal(Float.parseFloat(im[1]));
			}
			billT.setOpType("A");
			billT.setCreateTime(now);
			billT.setModifyTime(now);
			billT.setCreatorId(loginId);
			billT.setModifierId(loginId);
			billFixService.insertBillTarget(billT);
		}
		
		// 清单筛选维度
		String usualDim = (String)dataJson.get("usualDim");
		usualDim = usualDim.substring(0, usualDim.length()-1);
		String[] ul = usualDim.split(",");
		List<String> tars = new ArrayList<>();
		for (String u : ul) {
			String[] ts = u.split("\\|");
			tars.add(ts[1]);
		}
		tars = new ArrayList<>(new HashSet<>(tars));
		String tarsStr = "";
		for (String tar : tars) {
			String filtStr = "";
			for(String u : ul){
				String[] a = u.split("\\|");
				if (tar.equals(a[1])) {
					filtStr += a[0]+",";
				}
			}
			BillFiltDim bfd = new BillFiltDim();
			bfd.setBillId(billId);
			bfd.setTargetCode(tar);
			bfd.setFiltDimCode(filtStr.substring(0, filtStr.length()-1));
			bfd.setOpType("A");
			bfd.setCreateTime(now);
			bfd.setModifyTime(now);
			bfd.setCreatorId(loginId);
			bfd.setModifierId(loginId);
			billFixService.insertBillFiltDim(bfd);
			tarsStr += "'"+tar+"',";
		}
		if (!"".equals(tarsStr)) {
			List<GroDimCodeStr> lm = billFixService.selectGroDimByTars(tarsStr.substring(0, tarsStr.length()-1));
			for (GroDimCodeStr groDimCodeStr : lm) {
				BillGroDim bgd = new BillGroDim();
				bgd.setBillId(billId);
				bgd.setGroupType(groDimCodeStr.getGroDimType());
				if (tars.size() == 1) {
					bgd.setGroupDetailCode(groDimCodeStr.getGroDCodeStr());
				} else {
					String gdtStr = groDimCodeStr.getGroDCodeStr();
					bgd.setGroupDetailCode(getRepeatDimCode(gdtStr));
				}
				bgd.setOpType("A");
				bgd.setCreateTime(now);
				bgd.setModifyTime(now);
				bgd.setCreatorId(loginId);
				bgd.setModifierId(loginId);
				billFixService.insertBillGroDim(bgd);
			}
		}
		BillSql bs = new BillSql();
		bs.setBillId(billId);
		bs.setSqlCode("伪SQL");
		bs.setOpType("A");
		bs.setCreateTime(now);
		bs.setModifyTime(now);
		bs.setCreatorId(loginId);
		bs.setModifierId(loginId);
		billFixService.insertBillSQL(bs);
		return true;
	}
	
	// 修改
	@ResponseBody
	@Transactional
	@RequestMapping(value = "updateTarlistBill", method=RequestMethod.POST)
	public boolean updateTarlistBill(@RequestParam("billName") String billName, @RequestParam("remark") String remark, 
			@RequestParam("billId") String billIdHas, @RequestParam("deptCode") String deptCode,
			@RequestParam("dataJson") String data) throws ParseException {
		JSONObject dataJson = JSONObject.parseObject(data);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
		String loginId =SessionContextUtils.getLoginName().toString();
		Integer billId = Integer.parseInt(billIdHas);
		Bill bill = billFixService.selectByPrimaryKey(billId);
		bill.setBillName(billName);
		bill.setDeptCode(deptCode);
		bill.setRemark(remark);
		bill.setOpType("U");
		bill.setModifyTime(now);
		bill.setModifierId(loginId);
		billFixService.updateByPrimaryKey(bill); // 修改清单
		
		//清单与指标关联
		String formula = (String)dataJson.get("formula");
		formula = formula.substring(0, formula.length()-1);
		String[] btlist = formula.split(",");
		for (String bt : btlist) {
			BillTarget billT = new BillTarget();
			billT.setBillId(billId);
			String[] s1 = bt.split("\\|");
			billT.setTargetCode(s1[1]);
			String[] s2 = s1[0].split("\\#");
			billT.setSort(Integer.parseInt(s2[1]));
			String[] s3 = s2[0].split("\\&");
			String fType = s3[0].trim();// 1大于；2小于；3范围之间
			if (fType.equalsIgnoreCase("more")) {
				billT.setFormulaType("1");
				billT.setMinVal(Float.parseFloat(s3[1]));
			} else if (fType.equalsIgnoreCase("less")) {
				billT.setFormulaType("2");
				billT.setMaxVal(Float.parseFloat(s3[1]));
			} else {
				billT.setFormulaType("3");
				String[] im = s3[1].split("\\~");
				billT.setMinVal(Float.parseFloat(im[0]));
				billT.setMaxVal(Float.parseFloat(im[1]));
			}
			BillTarget billTarget = billFixService.selectByBillIdAndTarCode(billId, bt.split("\\|")[1]);
			billT.setModifyTime(now);
			billT.setModifierId(loginId);
			if (billTarget == null) { // 添加
				billT.setOpType("A");
				billT.setCreateTime(now);
				billT.setCreatorId(loginId);
				billFixService.insertBillTarget(billT);
			} else { // 修改
				billT.setBtId(billTarget.getBtId());
				billT.setOpType("U");
				billT.setCreateTime(billTarget.getCreateTime());
				billT.setCreatorId(billTarget.getCreatorId());
				billFixService.updateBillTarget(billT);
			}
		}
		
		// 清单筛选维度
		String usualDim = (String)dataJson.get("usualDim");
		usualDim = usualDim.substring(0, usualDim.length()-1);
		String[] ul = usualDim.split(",");
		List<String> tars = new ArrayList<>();
		for (String u : ul) {
			String[] ts = u.split("\\|");
			tars.add(ts[1]);
		}
		tars = new ArrayList<>(new HashSet<>(tars));
		String tarsStr = "";
		for (String tar : tars) {
			String filtStr = "";
			for(String u : ul){
				String[] a = u.split("\\|");
				if (tar.equals(a[1])) {
					filtStr += a[0]+",";
				}
			}
			BillFiltDim bfd = new BillFiltDim();
			bfd.setBillId(billId);
			bfd.setTargetCode(tar);
			BillFiltDim billFD = billFixService.selectBillFiltDim(billId, tar);
			bfd.setFiltDimCode(filtStr.substring(0, filtStr.length()-1));
			bfd.setModifyTime(now);
			bfd.setModifierId(loginId);
			if (billFD == null) {
				bfd.setOpType("A");
				bfd.setCreateTime(now);
				bfd.setCreatorId(loginId);
				billFixService.insertBillFiltDim(bfd);
			} else {
				bfd.setBfdId(billFD.getBfdId());
				bfd.setOpType("U");
				bfd.setCreateTime(billFD.getCreateTime());
				bfd.setCreatorId(billFD.getCreatorId());
				billFixService.updateBillFiltDim(bfd);
				
			}
			tarsStr += "'"+tar+"',";
		}
		if (!"".equals(tarsStr)) {
			List<GroDimCodeStr> lm = billFixService.selectGroDimByTars(tarsStr.substring(0, tarsStr.length()-1));
			for (GroDimCodeStr groDimCodeStr : lm) {
				String groDimType = groDimCodeStr.getGroDimType();
				BillGroDim bgd = new BillGroDim();
				bgd.setBillId(billId);
				bgd.setGroupType(groDimCodeStr.getGroDimType());
				if (tars.size() == 1) {
					bgd.setGroupDetailCode(groDimCodeStr.getGroDCodeStr());
				} else {
					String gdtStr = groDimCodeStr.getGroDCodeStr();
					bgd.setGroupDetailCode(getRepeatDimCode(gdtStr));
				}
				bgd.setModifyTime(now);
				bgd.setModifierId(loginId);
				BillGroDim billGD = billFixService.selectBillGroDim(billId, groDimType);
				if (billGD == null) {
					bgd.setOpType("A");
					bgd.setCreateTime(now);
					bgd.setCreatorId(loginId);
					billFixService.insertBillGroDim(bgd);
				} else {
					bgd.setBgdId(billGD.getBgdId());
					bgd.setOpType("U");
					bgd.setCreateTime(billGD.getCreateTime());
					bgd.setCreatorId(billGD.getCreatorId());
					billFixService.updateBillGroDim(bgd);
				}
			}
		}
		
		BillSql billSql = billFixService.selectBillSql(billId);
		BillSql bs = new BillSql();
		bs.setBillId(billId);
		bs.setSqlCode("伪SQL");
		bs.setModifyTime(now);
		bs.setModifierId(loginId);
		if (billSql == null) {
			bs.setOpType("A");
			bs.setCreateTime(now);
			bs.setCreatorId(loginId);
			billFixService.insertBillSQL(bs);
		} else {
			bs.setBsId(billSql.getBsId());;
			bs.setOpType("U");
			bs.setCreateTime(billSql.getCreateTime());
			bs.setCreatorId(billSql.getCreatorId());
			billFixService.updateBillSQL(bs);
		}
		
		// 删除清单中取消的指标
		billFixService.deleteNoNeedBillTar(billId, tars);
		return true;
	}
	
	// 取出逗号相隔重复数据并拼接成字符串
	public static String getRepeatDimCode(String gdtStr){
		String[] b = gdtStr.split(","); 
		List<String> ab = new ArrayList<>();
		for (String d : b) {
			if (StringUtils.countMatches(gdtStr, d+",") > 1) {
				ab.add(d);
			}
		}
		ab = new ArrayList<>(new HashSet<>(ab));
		String cc="";
		for (String dd : ab) {
			cc += dd+",";
		}
		String result = ab.size() == 0?"":cc.substring(0, cc.length()-1);
		return result;
	}
}
