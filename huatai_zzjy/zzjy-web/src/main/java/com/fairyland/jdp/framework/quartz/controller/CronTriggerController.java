package com.fairyland.jdp.framework.quartz.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.framework.quartz.domain.CronTrigger;
import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;
import com.fairyland.jdp.framework.quartz.service.CronTriggerService;
import com.fairyland.jdp.framework.quartz.service.QrtzGroupService;
import com.fairyland.jdp.framework.quartz.utils.JobUtil;
import com.fairyland.jdp.webbindsupport.propertyeditors.CustomDateEditor;
import com.huatai.web.model.Inter;
import com.huatai.web.model.TableTriggle;
import com.huatai.web.service.InterService;
import com.huatai.web.service.TableTriggleService;

@Controller
@RequestMapping("/admin/qrtz/cron")
public class CronTriggerController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private QrtzGroupService qrtzGroupService;
	
	@Autowired
	private CronTriggerService cronTriggerService;
	@Autowired
	private InterService interService;
	@Autowired
	private TableTriggleService tableTriggleService;
	
	@RequestMapping(value = "getCronTrigger/{id}", method = RequestMethod.GET)
	public String fetch(@PathVariable("id") Long id, Model model) {
		List<CronTrigger> cronTriggers = cronTriggerService.getCronTriggers(id);
		model.addAttribute("cronTriggers", cronTriggers);
		return "/jdp-framework/quartz/cronTrigger-list";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable("ids") String ids, Model model){
		String[] idArr=ids.split(",");
		for(int i=0;i<idArr.length;i++){
			cronTriggerService.deleteCronTrigger(Long.valueOf(idArr[i]));
		}
		model.addAttribute("message", "删除成功");
		return "success";
	}
	
	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
		TableTriggle tableTriggle = new TableTriggle();
		tableTriggle.setQrtzGroupId(cronTrigger.getQrtzGroup().getId());
		tableTriggle.setQrtzCode(cronTrigger.getCode());
		List<TableTriggle> oldList = tableTriggleService.findAll(tableTriggle);
		List<Inter> inters = interService.findInterList();
		for (Inter inter : inters) {
			for (TableTriggle tt : oldList) {
				if(tt.getTableCode().equals(inter.getInterName())){
					inter.setChecked(true);
				}
			}
		}
		model.addAttribute("cronTrigger", cronTrigger);
		model.addAttribute("inters", inters);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return "/jdp-framework/quartz/cronTrigger-edit";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String saveCronTrigger(@Valid @ModelAttribute("cronTrigger") CronTrigger cronTrigger, String[] tableCode,
			RedirectAttributes redirectAttributes){
		if(cronTrigger.getState() == null){
			cronTrigger.setState("草稿");
		}
		if(cronTrigger.getId()!=null){
			cronTriggerService.deleteTableTriggle(cronTrigger.getQrtzGroup().getId(),cronTrigger.getCode());
		}
		cronTriggerService.updateCronTrigger(cronTrigger);
		
		if(tableCode!=null && tableCode.length>0){
			for (String tc : tableCode) {
				TableTriggle tableTriggle = new TableTriggle();
				tableTriggle.setQrtzGroupId(cronTrigger.getQrtzGroup().getId());
				tableTriggle.setQrtzCode(cronTrigger.getCode());
				tableTriggle.setTableCode(tc);
				tableTriggleService.insert(tableTriggle);
			}
		}
		redirectAttributes.addFlashAttribute("message", "CronTrigger更新成功");
		return "success";
	}
	
	@RequestMapping(value = "saveAndCreate", method = RequestMethod.POST)
	public String saveAndCreateCronTrigger(@Valid @ModelAttribute("cronTrigger") CronTrigger cronTrigger,
			Model model){
		if(cronTrigger.getState() == null){
			cronTrigger.setState("草稿");
		}
		cronTriggerService.updateCronTrigger(cronTrigger);
		Long qrtzGroupId = cronTrigger.getQrtzGroup().getId();
		CronTrigger cronTrigger2 = new CronTrigger();
		QrtzGroup qrtzGroup = new QrtzGroup();
		qrtzGroup.setId(qrtzGroupId);
		cronTrigger2.setQrtzGroup(qrtzGroup);
		if(cronTrigger2.getState() == null){
			cronTrigger2.setState("草稿");
		}
		model.addAttribute("cronTrigger", cronTrigger2);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		model.addAttribute("message", "CronTrigger更新成功");
		return "/jdp-framework/quartz/cronTrigger-edit";
	}
	
	/**
	 * 通过QrtzGroup的ID创建一个CronTrigger
	 * @param qrtzGroupId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{id}")
	public String createCronTrigger(@PathVariable("id") Long id,
			Model model){
		List<Inter> inters = interService.findInterList();
		CronTrigger cronTrigger = new CronTrigger();
		QrtzGroup qrtzGroup = new QrtzGroup();
		qrtzGroup.setId(id);
		cronTrigger.setQrtzGroup(qrtzGroup);
		model.addAttribute("cronTrigger", cronTrigger);
		model.addAttribute("inters",inters);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return "/jdp-framework/quartz/cronTrigger-edit";
	}
	
	@RequestMapping(value = "release/{id}", method=RequestMethod.GET)
	public String releaseCronTrigger(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes){
		CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
		cronTriggerService.release(cronTrigger);
		redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"发布成功");
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value = "generate/{id}", method=RequestMethod.GET)
	public String generateCronTrigger(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes){
		CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
		try {
			cronTriggerService.generate(cronTrigger);
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"生成成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"生成失败");
			log.error(e.getMessage(),e);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"生成失败");
			log.error(e.getMessage(),e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"生成失败");
			log.error(e.getMessage(),e);
		}
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value = "stop/{id}", method=RequestMethod.GET)
	public String stopCronTrigger(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes){
		CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
		try {
			cronTriggerService.stop(cronTrigger);
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"终止成功");
		} catch (SchedulerException e) {
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"终止失败");
			log.error(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"终止失败");
			log.error(e.getMessage(),e);
		} catch (ParseException e) {
			redirectAttributes.addFlashAttribute("message", cronTrigger.getName()+"终止失败");
			log.error(e.getMessage(),e);
		}
		return "redirect:/admin/qrtz/def";
	}
	
	@ModelAttribute
	public void getCronTrigger(@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model){
		if(id != -1){
			CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
			model.addAttribute("cronTrigger", cronTrigger);
		}
//		List<TableTriggle> tableTriggles = new ArrayList<TableTriggle>();
//		if(!CollectionUtils.isEmpty(tableCode)){
//			for (String tc : tableCode) {
//				TableTriggle tableTriggle = new TableTriggle();
//				tableTriggle.setTableCode(tc);
//				tableTriggles.add(tableTriggle);
//			}
//		}
//		model.addAttribute("tableTriggles", tableTriggles);
	}
	

	@RequestMapping(value="checkName",method=RequestMethod.POST)
	@ResponseBody
	public String checkName(String name,String initialName){
		if(initialName!=null && initialName.equals(name)){
			return "true";
		}
		CronTrigger cronTrigger = cronTriggerService.getQrtzDefinitionByName(name);
		if(cronTrigger==null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value="checkCode",method=RequestMethod.POST)
	@ResponseBody
	public String checkCode(String code,String initialCode){
		if(initialCode!=null && initialCode.equals(code)){
			return "true";
		}
		CronTrigger cronTrigger = cronTriggerService.getQrtzDefinitionByName(code);
		if(cronTrigger==null){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "run/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String runCronTrigger(@PathVariable("id") Long id){
		CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
		try {
			cronTriggerService.run(cronTrigger);
		} catch (SchedulerException e) {
			log.error(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (ParseException e) {
			log.error(e.getMessage(),e);
		}
		return "success";
	}
	@RequestMapping(value = "getJobState/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getJobState(@PathVariable("id") Long id){
		CronTrigger cronTrigger = cronTriggerService.getCronTrigger(id);
		try {
			String state = cronTriggerService.getState(cronTrigger);
			return state;
		} catch (SchedulerException e) {
			log.error(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (ParseException e) {
			log.error(e.getMessage(),e);
		}
		return "获取失败";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
}
