package com.huatai.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.Role;
import com.fairyland.jdp.framework.security.service.RoleService;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.CoreTarget;
import com.huatai.web.model.CoreTargetExample;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.RelaTarget;
import com.huatai.web.model.Target;
import com.huatai.web.service.CoreTargetService;
import com.huatai.web.service.TargetService;

/**
 * @description:
 * @author ：沈从会
 * @datetime : 2017年7月21日 下午4:52:49
 */
@Controller
@RequestMapping("admin/coreTarget")
public class CoreTargetController {

	@Autowired
	private CoreTargetService coreTargetService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private TargetService targetService;

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		List<Role> roles = roleService.findAll();
		List<Target> targets = targetService.selectByIsHomeCoreTarget("1");
		model.addObject("roles", roles);
		model.addObject("targets", JSON.toJSON(targets));
		String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		List<DeptInfo> depts = coreTargetService.findByDeptCodeStr(dstr);
		model.addObject("depts", depts);
		model.setViewName("/admin/coreTarget/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<CoreTarget> getPager(Pager<CoreTarget> pager, CoreTarget record) {
//		record.setDeptCode(SessionContextUtils.getCurrentUser().getDeptCode());
		if (record.getDeptCode()==null && record.getTargetName()==null) {
			String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
			if (dstr.indexOf(",")>-1) {
				record.setDeptCode(dstr.split(",")[0]);
			} else {
				record.setDeptCode(dstr);
			}
			record.setTargetName("");
		}
		return coreTargetService.findByPager(pager, record);
	}
	
	@ResponseBody
	@RequestMapping(value = "relationlist")
	public Pager<RelaTarget> getPager(Pager<RelaTarget> pager,RelaTarget record, @RequestParam("pid") String pid, @RequestParam("targetName") String targetName) {
//		String deptCode = SessionContextUtils.getCurrentUser().getDeptCode();
//		record.setDeptCode(deptCode);
		record.setTargetName(targetName.replace(",", ""));
		Pager<RelaTarget> rts = coreTargetService.findByPagerRela(pager, record);
		List<CoreTarget> rts_has = coreTargetService.findByPidAndDeptCode(pid, record.getDeptCode());
		for (RelaTarget rt : rts.getPageItems()) {
			for (CoreTarget r_h : rts_has) {
				if(rt.getTargetCode().trim().equals(r_h.getTargetCode().trim())){
					rt.setRegCode("1");
				}
			}
		}
		return rts;
	}
	
	@ResponseBody
	@RequestMapping(value = "findByTargets")
	public boolean findByTargets(@RequestParam("targetCode") String targetCode) {
		String deptCode = SessionContextUtils.getCurrentUser().getDeptCode();
		List<CoreTarget> rts = coreTargetService.findByTargetCodeAndDeptCode(targetCode, deptCode);
		return rts.size()==0?true:false;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public CoreTarget create(@Valid CoreTarget record, String targetCodes) throws ParseException {
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setCreatorName(SessionContextUtils.getLoginName().toString());
		record.setDeptCode(SessionContextUtils.getCurrentUser().getDeptCode());
		this.coreTargetService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public CoreTarget update(@Valid CoreTarget record, RedirectAttributes redirectAttributes) throws Exception {
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifierName(SessionContextUtils.getLoginName().toString());
		record.setOpType("U");
		this.coreTargetService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) throws ParseException {
		CoreTarget record =new CoreTarget();
		record.setCtId(id);
		record.setOpType("D");
		record.setModifierId(SessionContextUtils.getLoginUserId().toString());
		coreTargetService.update(record);
	}
	
	@RequestMapping(value = "relationTwo", method = RequestMethod.POST)
	@ResponseBody
	public void relationTwo(@RequestParam("twoStr") String twoStr,@RequestParam("pid") String pid,@RequestParam("deptCode") String deptCode) throws ParseException{
//		String deptCode = SessionContextUtils.getCurrentUser().getDeptCode();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		twoStr = twoStr.replace("|"+pid, "");
		String[] targetCodes = twoStr.split(",");
		List<CoreTarget> cts = this.coreTargetService.findByPid(pid, deptCode);
		if(cts.size() == 0){
			for (String targetCode : targetCodes) {
				CoreTarget co = new CoreTarget();
				co.setDeptCode(deptCode);
				co.setTargetCode(targetCode);
				co.setPid(pid);
				co.setOpType("A");
				co.setCreateTime(sdf.parse(sdf.format(new Date())));
				co.setModifyTime(sdf.parse(sdf.format(new Date())));
				co.setCreatorId(SessionContextUtils.getLoginName().toString());
				co.setModifierId(SessionContextUtils.getLoginName().toString());
				this.coreTargetService.insert(co);
			}
		} else {
			for (CoreTarget coreTarget : cts) {
				if(twoStr.indexOf(coreTarget.getTargetCode())!=-1){
					for (String targetCode : targetCodes) {
						if(coreTarget.getTargetCode().trim().equals(targetCode.trim())){
							CoreTarget c = new CoreTarget();
							CoreTargetExample ce = new CoreTargetExample();
							ce.createCriteria().andTargetCodeEqualTo(targetCode);
							c.setModifierId(SessionContextUtils.getLoginName().toString());
							c.setModifierName(SessionContextUtils.getLoginName().toString());
							c.setModifyTime(sdf.parse(sdf.format(new Date())));
							c.setOpType("U");
							this.coreTargetService.updateByExampleSelective(c, ce);
						}else{
							List<CoreTarget> cts1 = this.coreTargetService.findByPid(pid, deptCode);
							String cstr = "";
							for (CoreTarget c : cts1) {
								cstr += c.getTargetCode() + ",";
							}
							if(cstr.indexOf(targetCode)==-1){
								CoreTarget co = new CoreTarget();
								co.setDeptCode(deptCode);
								co.setTargetCode(targetCode);
								co.setPid(pid);
								co.setOpType("A");
								co.setCreateTime(sdf.parse(sdf.format(new Date())));
								co.setModifyTime(sdf.parse(sdf.format(new Date())));
								co.setCreatorId(SessionContextUtils.getLoginName().toString());
								this.coreTargetService.insert(co);
							}
						}
					}
				}else{
					this.coreTargetService.deleteByPidAndTargetCode(pid, coreTarget.getTargetCode());
				}
			}
		}
	}
}
