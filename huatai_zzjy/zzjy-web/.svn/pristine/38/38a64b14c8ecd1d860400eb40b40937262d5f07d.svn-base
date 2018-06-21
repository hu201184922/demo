package com.fairyland.jdp.framework.quartz.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.domain.QrtzDefinition;
import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;
import com.fairyland.jdp.framework.quartz.service.CronTriggerService;
import com.fairyland.jdp.framework.quartz.service.QrtzDefinitionService;
import com.fairyland.jdp.framework.quartz.service.QrtzGroupService;
import com.fairyland.jdp.framework.quartz.utils.JobUtil;
import com.fairyland.jdp.framework.tree.controller.TreeController;
import com.fairyland.jdp.framework.tree.service.TreeFactory;

@Controller
@RequestMapping("/admin/qrtz/def")
public class QrtzDefinitionController extends TreeController<QrtzGroup>{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private QrtzDefinitionService qrtzDefinitionService;
	
	@Autowired
	private QrtzGroupService qrtzGroupService;
	
	@Autowired
	private @Qualifier(value="zTreeFactory")TreeFactory<QrtzGroup> treeFactory;
	
	@Autowired
	SchedulerFactoryBean schedulerFactory;
	
	@Autowired
	private CronTriggerService cronTriggerService;

	@RequestMapping(method = RequestMethod.GET)
	public String main(Model model){
		return "/jdp-framework/quartz/quartzdefinition-main";
	}
	
	
	@RequestMapping(value="edit/{qrtzDefinitionId}", method=RequestMethod.GET )
	public String edit(@PathVariable("qrtzDefinitionId") Long qrtzDefinitionId
			,Model model){
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionById(qrtzDefinitionId);
		model.addAttribute("type", qrtzDefinition.getIntervalType());
		model.addAttribute("qrtzDefinition", qrtzDefinition);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return "jdp-framework/quartz/quartzdefinition-edit";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@ModelAttribute("qrtzDefinition") QrtzDefinition qrtzDefinition,String startJobTime,String endJobTime, RedirectAttributes redirectAttributes){
		qrtzDefinitionService.updateQrtzDefinition(qrtzDefinition);
		if(qrtzDefinition.getState() == null){
			qrtzDefinition.setState("草稿");
		}
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value="release/{qrtzDefinitionId}", method=RequestMethod.GET )
	public String release(@PathVariable("qrtzDefinitionId") Long qrtzDefinitionId
			,RedirectAttributes redirectAttributes){
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionById(qrtzDefinitionId);
		qrtzDefinitionService.release(qrtzDefinition);
		redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "发布成功");
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value="generate/{qrtzDefinitionId}", method=RequestMethod.GET )
	public String generate(@PathVariable("qrtzDefinitionId") Long qrtzDefinitionId
			,RedirectAttributes redirectAttributes){
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionById(qrtzDefinitionId);
		try {
			qrtzDefinitionService.generate(qrtzDefinition);
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "生成成功");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "生成失败");
		} catch (SchedulerException e1) {
			e1.printStackTrace();
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "生成失败");
		} catch (ParseException e1) {
			e1.printStackTrace();
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "生成失败");
		}
		
		return "redirect:/admin/qrtz/def";
	}
	@RequestMapping(value="generateAll", method=RequestMethod.GET )
	@ResponseBody
	public String generateAll(){
		List<CronTrigger> list = cronTriggerService.findAll();
		for (CronTrigger cronTrigger : list) {
			try {
				cronTriggerService.generate(cronTrigger);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return "success";
	}
	@RequestMapping(value="generateSelected", method=RequestMethod.GET )
	@ResponseBody
	public String generateSelected(String cronTriggerIds){
		String[] ids = cronTriggerIds.split(",");
		for (String cronTriggerIdStr : ids) {
			try {
				Long cronTriggerId = Long.parseLong(cronTriggerIdStr);
				CronTrigger cronTrigger = cronTriggerService.getCronTrigger(cronTriggerId);
				cronTriggerService.generate(cronTrigger);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return "success";
	}
	@RequestMapping(value="stop/{qrtzDefinitionId}", method=RequestMethod.GET )
	public String stop(@PathVariable("qrtzDefinitionId") Long qrtzDefinitionId
			,RedirectAttributes redirectAttributes){
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionById(qrtzDefinitionId);
		try {
			qrtzDefinitionService.stop(qrtzDefinition);
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "终止成功");
		} catch (ClassNotFoundException e1) {
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "终止失败");
			e1.printStackTrace();
		}catch (SchedulerException e1) {
			redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "终止失败");
			e1.printStackTrace();
		}
		return "redirect:/admin/qrtz/def";
	}
	@RequestMapping(value="stopAll", method=RequestMethod.GET )
	@ResponseBody
	public String stopAll(){
		List<CronTrigger> list = cronTriggerService.findAll();
		for (CronTrigger cronTrigger : list) {
			try {
				cronTriggerService.stop(cronTrigger);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return "success";
	}
	@RequestMapping(value="stopSelected", method=RequestMethod.GET )
	@ResponseBody
	public String stopSelected(String cronTriggerIds){
		String[] ids = cronTriggerIds.split(",");
		for (String cronTriggerIdStr : ids) {
			try {
				Long cronTriggerId = Long.parseLong(cronTriggerIdStr);
				CronTrigger cronTrigger = cronTriggerService.getCronTrigger(cronTriggerId);
				cronTriggerService.stop(cronTrigger);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return "success";
	}
	@RequestMapping(value="delete/{qrtzDefinitionId}", method=RequestMethod.POST )
	public String delete(@PathVariable("qrtzDefinitionId") Long qrtzDefinitionId
			,RedirectAttributes redirectAttributes){
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionById(qrtzDefinitionId);
		qrtzDefinitionService.deleteQrtzDefinition(qrtzDefinitionId);
		redirectAttributes.addFlashAttribute("message", qrtzDefinition.getName() + "删除成功");
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value="delete2/{ids}",method = RequestMethod.POST)
	public String delete2(@PathVariable("ids") String itemids){
		String[] ids=itemids.split(",");
		for(int i=0;i<ids.length;i++){
			qrtzDefinitionService.deleteQrtzDefinition(Long.valueOf(ids[i]));
		}
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value = "delete-Group/{id}",method = RequestMethod.POST)
	@ResponseBody
	public void deleteGroup(@PathVariable("id") Long id){
		List<CronTrigger> list = cronTriggerService.getCronTriggers(id);
		for (CronTrigger cronTrigger : list) {
			cronTriggerService.deleteCronTrigger(cronTrigger.getId());
		}
		qrtzGroupService.deleteQrtzGroup(id);
	}
	
	@RequestMapping(value="updateAndCreate", method=RequestMethod.POST)
	public String updateAndCreate(@Valid @ModelAttribute("qrtzDefinition") QrtzDefinition qrtzDefinition,Model model){
		qrtzDefinitionService.updateQrtzDefinition(qrtzDefinition);
		if(qrtzDefinition.getState() == null){
			qrtzDefinition.setState("草稿");
		}
		return "redirect:/admin/qrtz/def/create/"+qrtzDefinition.getQrtzGroup().getId();
	}
	
	@RequestMapping(value ="create/{id}")
	public String createDef(@PathVariable("id") Long id,Model model){
		QrtzDefinition qrtzDefinition = new QrtzDefinition();
		QrtzGroup qrtzGroup = new QrtzGroup();
		qrtzGroup.setId(id);
		qrtzDefinition.setQrtzGroup(qrtzGroup);
		qrtzDefinition.setState("草稿");
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
//		qrtzDefinitionService.addQrtzDefinition(qrtzDefinition);
		model.addAttribute("qrtzDefinition", qrtzDefinition);
		return "jdp-framework/quartz/quartzdefinition-edit";
	}
	
	@RequestMapping(value="create-Group", method=RequestMethod.POST)
	@ResponseBody
	public QrtzGroup createGroup(@Valid QrtzGroup qrtzGroup){
		qrtzGroup.setName("新调度组");
		qrtzGroupService.createQrtzGroup(qrtzGroup);
		qrtzGroup = qrtzGroupService.getQrtzGroupById(qrtzGroup.getId());
		return qrtzGroup;
	}
	
	@RequestMapping(value = "move-Group/{id}")
	@ResponseBody
	public void moveTo(@PathVariable("id") Long id, @RequestParam("parentId") Long parentId) {
		QrtzGroup qrtzGroup = qrtzGroupService.getQrtzGroupById(id);
		if(parentId == null){
			qrtzGroup.setParent(null);
		}else{
			qrtzGroup.setParent(qrtzGroupService.getQrtzGroupById(parentId));
		}
		qrtzGroupService.updateQrtzGroup(qrtzGroup);
	}
	
	@RequestMapping(value="checkName",method=RequestMethod.POST)
	@ResponseBody
	public String checkName(String name,String initialname){
		if(initialname!=null && initialname.equals(name)){
			return "true";
		}
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionByName(name);
		if(qrtzDefinition==null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value="checkCode",method=RequestMethod.POST)
	@ResponseBody
	public String checkCode(String code,String initialcode){
		if(initialcode!=null && initialcode.equals(code)){
			return "true";
		}
		QrtzDefinition qrtzDefinition = qrtzDefinitionService.getQrtzDefinitionByCode(code);
		if(qrtzDefinition==null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value="save-Group", method=RequestMethod.POST)
	public String saveGroup(@Valid @ModelAttribute("qrtzGroup") QrtzGroup qrtzGroup, RedirectAttributes redirectAttributes){
		qrtzGroupService.updateQrtzGroup(qrtzGroup);
		
		redirectAttributes.addFlashAttribute("message", "调度组更新成功");
		redirectAttributes.addFlashAttribute("groupId", qrtzGroup.getId());
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value ="getChildGroup/{id}", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String,Object>> getChildGroup(@PathVariable("id") Long id) throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<QrtzGroup> qrtzGroups = qrtzGroupService.getChildGroup(id);
		Iterator<QrtzGroup> it=qrtzGroups.iterator();
		while(it.hasNext()){
			QrtzGroup qrtzGroup = it.next();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", qrtzGroup.getId());
			map.put("name", qrtzGroup.getName());
			map.put("pid", qrtzGroup.getPid());
			map.put("code", qrtzGroup.getPid());
			map.put("sortIndex", qrtzGroup.getSortIndex());
			map.put("descript", qrtzGroup.getDescript());
			list.add(map);
		}
		return list;
	}
	
	@RequestMapping(value ="getGroup/{id}", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public List<Map<String, Object>> getQrtzDefinitions(@PathVariable("id") Long id) throws Exception {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		List<QrtzDefinition> qrtzDefinitions = qrtzDefinitionService.getGroupQrtzDefinitions(id);
		Iterator<QrtzDefinition> it=qrtzDefinitions.iterator();
		while(it.hasNext()){
			QrtzDefinition qrtzDefinition=it.next();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", qrtzDefinition.getId());
			map.put("name", qrtzDefinition.getName());
			map.put("code", qrtzDefinition.getCode());
			map.put("jobClassName", qrtzDefinition.getJobClassName());
			map.put("startJobDate", qrtzDefinition.getStartJobDate());
			map.put("endJobDate", qrtzDefinition.getEndJobDate());
			map.put("startJobTime", qrtzDefinition.getStartJobTime());
			map.put("endJobTime", qrtzDefinition.getEndJobTime());
			map.put("repeat", qrtzDefinition.getRepeat());
			map.put("endType", qrtzDefinition.getEndType());
			map.put("intervalType", qrtzDefinition.getIntervalType());
			map.put("intervalMeta", qrtzDefinition.getIntervalMeta());
			map.put("descript", qrtzDefinition.getDescript());
			map.put("state", qrtzDefinition.getState());
			list.add(map);
		}
		return list;
	} 
	
	@RequestMapping(value = "/getDef/{id}", method = RequestMethod.GET)
	public String fetch(@PathVariable("id") Long id, Model model) {
		model.addAttribute("qrtzDefinitions", qrtzDefinitionService.getGroupQrtzDefinitions(id));
		return "/jdp-framework/quartz/quartzdefinition-list";
	}
	
	@RequestMapping(value ="createDef/{id}")
	public String createQrtzDefinitions(@PathVariable("id") Long id,Model model){
		model.addAttribute("parentId",id);
		return "/jdp-framework/quartz/quartzdefinition-edit";
	}
	
	@ModelAttribute
	public void getQrtzDefinition(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			@RequestParam(value = "groupId", defaultValue = "-1") Long groupId,
			Model model) {
		if (id != -1) {
			model.addAttribute("qrtzDefinition", qrtzDefinitionService.getQrtzDefinitionById(id));
		}
		if (groupId != -1) {
			model.addAttribute("qrtzGroup", qrtzGroupService.getQrtzGroupById(groupId));
		}
	}
	
//	@InitBinder  
//	public void initBinder(WebDataBinder binder) throws Exception {  
//	     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//	     binder.registerCustomEditor(Date.class, null, new CustomDateEditor(df,
//	                true));
//	     binder.registerCustomEditor(Time.class, null, new CustomTimeEditor(new SimpleDateFormat("HH:mm:ss"),  
//	                true));
//	   }
	@RequestMapping("getRunningJobs")
	@ResponseBody
	public String getCurrentlyExecutingJobs(){
		StringBuilder sb = new StringBuilder();
		List<JobExecutionContext> jobs = cronTriggerService.getCurrentlyExecutingJobs();
		for(int i=0;i<jobs.size();i++){
			sb.append("Job").append(i+1).append(": ").append(jobs.get(i).toString()).append("<br/>");
		}
		if(sb.length()==0){
			sb.append("No Job Running");
		}
		return sb.toString();
	}
}
