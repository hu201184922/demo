package com.huatai.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.Target;
import com.huatai.web.service.GroDimDetailService;
import com.huatai.web.service.GroDimService;
import com.huatai.web.service.QueryDimService;
import com.huatai.web.service.TarGroDimService;
import com.huatai.web.service.TarQueryDimService;
import com.huatai.web.service.TargetService;

/**
 * @author 胡智辉 2017年7月18日
 */
@Controller
@RequestMapping("admin/target")
public class TargetController {

	final static Logger logger = LoggerFactory.getLogger(TargetController.class);

	@Autowired
	private TargetService targetService;

	@Autowired
	private GroDimService groDimService;

	@Autowired
	private QueryDimService queryDimService;

	@Autowired
	private GroDimDetailService groDimDetailService;

	@Autowired
	private TarQueryDimService tarQueryDimService;

	@Autowired
	private TarGroDimService tarGroDimService;

	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<Target> subject = targetService.findTargetBySubject();
		mv.addObject("subject", subject);
		mv.setViewName("/admin/target/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Target> getPager(Pager<Target> pager, Target record) {
		pager = targetService.findTargetByPage(pager, record);
		return pager;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public ModelAndView create(Target record, String isPlates, String groupParentCode, String[] gdIds, String[] gddIds,
			String[] qdIds, ModelAndView view, @RequestParam("oldTargetCode") String oldTargetCode) {
		record.setIsPlate(isPlates);
		String deptCode[];
		String targetType[];
		String isNull = "";
		String isDefault = "0";
		String isDefault1 = "1";
		try {
			deptCode = record.getDeptCode().split(",");
			targetType = record.getTargetType().split(",");
			String parentCode = record.getParentCode().replace(",", isNull);
			Target target = targetService.selectByPrimaryKey(record.getTargetCode());
			if (null != target) {
				if (target.getOpType().equalsIgnoreCase("D"))
					targetService.deleteTarget(record.getTargetCode());
			}

			// 判断是否存在
			if ("".equals(oldTargetCode)) {
				switch (targetType[0].trim()) {
				case "0":// 主题
					record.setParentCode("0");
					record.setIsPlate("0");
					record.setTargetType("0");
					objSubTarget(record);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					record.setIsRealtime(record.getIsRealtime().substring(0, 1));
					break;
				case "1":// 一级
					record.setTargetType(targetType[0]);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[deptCode.length - 1]);
					record.setIsRealtime(record.getIsRealtime().substring(2, 3));
					break;
				case "2":// 指标组
					record.setTargetType(targetType[0]);
					record.setOpType("A");
					record.setIsStatis(isNull);
					objSubTarget(record);
					record.setIsRealtime(isDefault);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					setCM(record);
					int result = targetService.addTarget(record);
					if (result == 1) {
						view.setViewName("/admin/target/index");
						view.addObject("msg", "success");
						return view;
					}
					view.setViewName("/admin/target/index");
					view.addObject("msg", "fall");
					return view;
				case "3":// 二级指标
					record.setParentCode(groupParentCode);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[deptCode.length - 1]);
					record.setIsRealtime(record.getIsRealtime().substring(2, 3));
					break;
				case "4":// 板块
					record.setParentCode("0");
					record.setIsPlate("1");
					record.setTargetType("0");
					objSubTarget(record);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					record.setIsRealtime(record.getIsRealtime().substring(0, 1));
					break;
				case "5":// 开发主题
					record.setParentCode("0");
					record.setIsPlate("2");
					record.setTargetType("0");
					objSubTarget(record);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					record.setIsRealtime(record.getIsRealtime().substring(0, 1));
					break;
				default:
					view.setViewName("redirect:/admin/target/index");
					return view;
				}
				setCM(record);
				record.setOpType("A");
				int result = targetService.addTarget(record);
				if (result == 1) {
					// 判断指标分组维度数据是否存在
					List<TarGroDim> tarGroDims = this.tarGroDimService
							.findTarGroDimByTargetCode(record.getTargetCode());
					if (tarGroDims.size() == 0) {
						for (String gddId : gddIds) {
							TarGroDim tarGroDim = new TarGroDim();
							tarGroDim.setGddId(Integer.parseInt(gddId));
							tarGroDim.setTargetCode(record.getTargetCode());
							tarGroDim.setOpType("A");
							tarGroDim.setCreateTime(new Date());
							tarGroDim.setCreatorId(SessionContextUtils.getLoginName().toString());
							tarGroDim.setModifyTime(new Date());
							tarGroDim.setModifierId(SessionContextUtils.getLoginName().toString());
							tarGroDimService.insertSelective(tarGroDim);
						}
					} else {
						List<String> list = new ArrayList<>();
						for (TarGroDim tarGroDim : tarGroDims) {
							list.add(tarGroDim.getTargetCode());
						}
						for (String gddId : gddIds) {
							if (!list.contains(gddId)) {
								TarGroDim tarGroDim = new TarGroDim();
								tarGroDim.setGddId(Integer.parseInt(gddId));
								tarGroDim.setTargetCode(record.getTargetCode());
								tarGroDim.setOpType("A");
								tarGroDim.setCreateTime(new Date());
								tarGroDim.setCreatorId(SessionContextUtils.getLoginName().toString());
								tarGroDim.setModifyTime(new Date());
								tarGroDim.setModifierId(SessionContextUtils.getLoginName().toString());
								tarGroDimService.insertSelective(tarGroDim);
								continue;
							}
						}
					}
					List<TarQueryDim> tarQueryDims = this.tarQueryDimService
							.findTarQueryDimByTargetCode(record.getTargetCode());
					if (tarQueryDims.size() == 0) {
						if (gddIds != null) {
							for (String qdId : qdIds) {
								TarQueryDim tarQueryDim = new TarQueryDim();
								tarQueryDim.setQdId(Integer.parseInt(qdId));
								tarQueryDim.setTargetCode(record.getTargetCode());
								tarQueryDim.setOpType("A");
								tarQueryDim.setCreateTime(new Date());
								tarQueryDim.setCreatorId(SessionContextUtils.getLoginName().toString());
								tarQueryDim.setModifyTime(new Date());
								tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
								tarQueryDimService.insertSelective(tarQueryDim);
							}
						}
					} else {
						List<String> list = new ArrayList<>();
						for (TarQueryDim tarQueryDim : tarQueryDims) {
							list.add(tarQueryDim.getTargetCode());
						}
						for (String qdId : qdIds) {
							if (!list.contains(qdId)) {
								TarQueryDim tarQueryDim = new TarQueryDim();
								tarQueryDim.setQdId(Integer.parseInt(qdId));
								tarQueryDim.setTargetCode(record.getTargetCode());
								tarQueryDim.setOpType("A");
								tarQueryDim.setCreateTime(new Date());
								tarQueryDim.setCreatorId(SessionContextUtils.getLoginName().toString());
								tarQueryDim.setModifyTime(new Date());
								tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
								tarQueryDimService.insertSelective(tarQueryDim);
								continue;
							}
						}
					}
					view.addObject("msg", "1");
				}
			} else {
				switch (targetType[0].trim()) {
				case "0":
					record.setParentCode("0");
					record.setTargetType("0");
					record.setIsPlate("0");
					objSubTarget(record);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					record.setIsRealtime(record.getIsRealtime().substring(0, 1));
					break;
				case "1":
					record.setTargetType("1");
					record.setParentCode(record.getParentCode());
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[deptCode.length - 1]);
					record.setIsRealtime(record.getIsRealtime().substring(2, 3));
					break;
				case "2":
					record.setTargetType(targetType[0]);
					objSubTarget(record);
					record.setIsRealtime(isDefault);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					break;
				case "3":
					record.setTargetType("3");
					record.setParentCode(groupParentCode);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[deptCode.length - 1]);
					record.setIsRealtime(record.getIsRealtime().substring(2, 3));
					break;
				case "4":
					record.setParentCode("0");
					record.setIsPlate("1");
					record.setTargetType("0");
					objSubTarget(record);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					record.setIsRealtime(record.getIsRealtime().substring(0, 1));
					break;
				case "5":
					record.setParentCode("0");
					record.setIsPlate("2");
					record.setTargetType("0");
					objSubTarget(record);
					record.setDeptCode(deptCode.length == 0 ? "" : deptCode[0]);
					record.setIsRealtime(record.getIsRealtime().substring(0, 1));
					break;
				default:
					view.setViewName("redirect:/admin/target/index");
					return view;
				}
				int result = update(record, oldTargetCode, gdIds, gddIds, qdIds);
				if (result == 1) {
					if (gddIds != null) {
						for (String gddId : gddIds) {
							TarGroDim tarGroDim = new TarGroDim();
							tarGroDim.setGddId(Integer.parseInt(gddId));
							tarGroDim.setTargetCode(record.getTargetCode());
							tarGroDim.setOpType("A");
							tarGroDim.setCreateTime(new Date());
							tarGroDim.setCreatorId(SessionContextUtils.getLoginName().toString());
							tarGroDim.setModifyTime(new Date());
							tarGroDim.setModifierId(SessionContextUtils.getLoginName().toString());
							tarGroDimService.insertSelective(tarGroDim);
						}
					}
					if (qdIds != null) {
						for (String qdId : qdIds) {
							TarQueryDim tarQueryDim = new TarQueryDim();
							tarQueryDim.setQdId(Integer.parseInt(qdId));
							tarQueryDim.setTargetCode(record.getTargetCode());
							tarQueryDim.setOpType("A");
							tarQueryDim.setCreateTime(new Date());
							tarQueryDim.setCreatorId(SessionContextUtils.getLoginName().toString());
							tarQueryDim.setModifyTime(new Date());
							tarQueryDim.setModifierId(SessionContextUtils.getLoginName().toString());
							tarQueryDimService.insertSelective(tarQueryDim);
						}
					}
					view.addObject("msg", "2");
				}
			}
		} catch (Exception e) {
			view.addObject("msg", "-1");
			logger.error(e.getMessage());
		}
		view.setViewName("redirect:/admin/target/index");
		return view;
	}

	private void objSubTarget(Target record) {
		String isNull = "", isDefault = "0", isDefault1 = "1";
		record.setTarDefSpec(isNull);
		record.setChannel(isNull);
		record.setCalFormula(isNull);
		record.setCalRate(isNull);
		record.setFieldCode(isNull);
		/* record.setIsRealtime(isDefault); */
		record.setIsManageTarget(isDefault);
		record.setIsWarnTarget(isNull);
		record.setIsStatisTarget(isNull);
		record.setIsMath(isDefault1);
	}

	private void setCM(Target record) {
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
	}

	@RequestMapping(value = "targetPage", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Map<String, List<Object>> targetPage(String gdId, String parentCode) {
		Map map = new HashMap();
		// 分组维度初始化
		List<GroDimDetail> groDimDetails = groDimDetailService.findAllGroDimDetail();
		map.put("groDimDetails", groDimDetails);
		// 分组维度装载
		List<TarGroDim> tarGroDims = tarGroDimService.findTarGroDimByTargetCode(parentCode);
		List<Integer> gddIds = new ArrayList<>();
		for (TarGroDim t : tarGroDims) {
			gddIds.add(t.getGddId());
		}
		map.put("gddIds", gddIds);
		List<GroDimDetail> groDimDetail = groDimDetailService.findGroDimDetailByGdId(gdId);
		map.put("groDimDetail", groDimDetail);
		return map;
	}

	/**
	 * 选择主题或板块之后加载指标组以及填充筛选和区域数据
	 */
	@RequestMapping(value = "targetGroup", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Map<String, List<Object>> targetGroup(String parentCode, String oldTargetCode) {
		Map map = new HashMap();
		Target target = new Target();
		target.setParentCode(parentCode);
		target.setTargetType("2");
		List<Target> list = targetService.findTargetSelective(target);
		map.put("targetGroup", list);
		// 填充主题或板块基本数据
		List<Target> targets = targetService.findParentTargetById(parentCode);
		map.put("targets", targets);

		// 筛选维度初始化
		List<QueryDim> queryDim = queryDimService.findAllQueryDim();
		map.put("queryDim", queryDim);

		List<TarQueryDim> tarQueryDim = tarQueryDimService
				.findTarQueryDimByTargetCode(oldTargetCode == "" ? parentCode : oldTargetCode);
		List<String> qdIds = new ArrayList<>();
		for (TarQueryDim tarQueryDim2 : tarQueryDim) {
			qdIds.add(tarQueryDim2.getQdId().toString());
		}
		map.put("qdIds", qdIds);
		return map;
	}

	@RequestMapping(value = "updatePage")
	public ModelAndView updatePage(String targetCode, String gdId, String currentPage) {
		ModelAndView view = new ModelAndView();
		if (targetCode != null && targetCode != "") {
			Target record = targetService.selectByPrimaryKey(targetCode);
			if ("0".equals(record.getTargetType()) && "1".equals(record.getIsPlate())) {
				record.setTargetType("4");
			}
			if ("0".equals(record.getTargetType()) && "2".equals(record.getIsPlate())) {
				record.setTargetType("5");
			}
			view.addObject("target", record);
		}
		List<DictionaryItem> field = dictionaryService.findByDictionaryCodeOrderByCode("UNIT_TYPE");
		view.addObject("fieldCode", field);

		String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		String[] ds = dstr.split(",");
		List<DictionaryItem> dictItem = new ArrayList<>();
		for (String d : ds) {
			dictItem.add(dictionaryService.findByDictCodeAndDictItemCode("DETP_DICT", d));
		}
		view.addObject("dictItem", dictItem);

		List<DictionaryItem> calRate = dictionaryService.findByDictionaryCodeOrderByCode("CAL_FRE_TYPE");
		view.addObject("calRates", calRate);

		// 查询主题或板块
		List<Target> record = targetService.findTargetByTargetType("0");
		view.addObject("targetType", record);
		// 查询二级组
		List<Target> recordGroup = targetService.findTargetByTargetType("2");
		view.addObject("targetGroup", recordGroup);
		// 分组维度初始化
		List<GroDim> groDim = groDimService.findGroDimAll();
		view.addObject("groDim", groDim);
		// 分组维度装载
		List<TarGroDim> tarGroDim = tarGroDimService.findTarGroDimByTargetCode(targetCode);
		List<Integer> list = new ArrayList<>();
		for (TarGroDim t : tarGroDim) {
			list.add(t.getGddId());
		}
		view.addObject("tarGroDim", list);

		// 筛选维度初始化
		List<QueryDim> queryDim = queryDimService.findAllQueryDim();
		view.addObject("queryDim", queryDim);

		// 筛选维度装载
		List<TarQueryDim> tarQueryDim = tarQueryDimService.findTarQueryDimByTargetCode(targetCode);
		List<String> qdIds = new ArrayList<>();
		for (TarQueryDim tarQueryDim2 : tarQueryDim) {
			qdIds.add(tarQueryDim2.getQdId().toString());
		}
		view.addObject("qdIds", qdIds);
		view.addObject("currentPage", currentPage == null ? "1" : currentPage);
		view.setViewName("/admin/target/updateTarget");
		return view;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid Target record, String oldTargetCode, String[] gdIds, String[] gddIds, String[] qdIds) {
		record.setOpType("U");
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		int result = targetService.updateTarget(record, oldTargetCode, gdIds, gddIds, qdIds);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid Target record) {
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		int result = targetService.updateTarget(record);
		return result;
	}

	@RequestMapping(value = "findTargetAll", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Target> findTargetAll(String subCode) {
		List<Target> list = targetService.findTargetBySubTarget(subCode);
		return list;
	}

}
