package com.fairyland.jdp.framework.quartz.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.framework.quartz.domain.QrtzGroup;
import com.fairyland.jdp.framework.quartz.domain.SimpleTrigger;
import com.fairyland.jdp.framework.quartz.service.QrtzGroupService;
import com.fairyland.jdp.framework.quartz.service.SimpleTriggerService;
import com.fairyland.jdp.framework.quartz.utils.JobUtil;

@Controller
@RequestMapping(value="/admin/qrtz/simple")
public class SimpleTriggerController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private QrtzGroupService qrtzGroupService;
	
	@Autowired
	private SimpleTriggerService simpleTriggerService;
	
	@RequestMapping(value = "getSimpleTrigger/{id}", method = RequestMethod.GET)
	public String fetch(@PathVariable("id") Long id, Model model) {
		List<SimpleTrigger> simpleTriggers = simpleTriggerService.getSimpleTriggers(id);
		model.addAttribute("simpleTriggers", simpleTriggers);
		return "/jdp-framework/quartz/simpleTrigger-list";
	}
	
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable("ids") String ids, Model model){
		String[] idArr=ids.split(",");
		for(int i=0;i<idArr.length;i++){
			simpleTriggerService.deleteSimpleTrigger(Long.valueOf(idArr[i]));
		}
		model.addAttribute("message", "删除成功");
		return "success";
	}
	
	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		SimpleTrigger simpleTrigger = simpleTriggerService.getSimpleTrigger(id);
		model.addAttribute("simpleTrigger", simpleTrigger);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return "/jdp-framework/quartz/simpleTrigger-edit";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String saveSimpleTrigger(@Valid @ModelAttribute("simpleTrigger") SimpleTrigger simpleTrigger, 
			RedirectAttributes redirectAttributes){
		if(simpleTrigger.getState() == null){
			simpleTrigger.setState("草稿");
		}
		simpleTriggerService.updateSimpleTrigger(simpleTrigger);
		redirectAttributes.addFlashAttribute("message", "SimpleTrigger更新成功");
		return "success";
	}
	
	@RequestMapping(value = "saveAndCreate", method = RequestMethod.POST)
	public String saveAndCreateSimpleTrigger(@Valid @ModelAttribute("simpleTrigger") SimpleTrigger simpleTrigger,
			Model model){
		if(simpleTrigger.getState() == null){
			simpleTrigger.setState("草稿");
		}
		simpleTriggerService.updateSimpleTrigger(simpleTrigger);
		Long qrtzGroupId = simpleTrigger.getQrtzGroup().getId();
		SimpleTrigger simpleTrigger2 = new SimpleTrigger();
		QrtzGroup qrtzGroup = new QrtzGroup();
		qrtzGroup.setId(qrtzGroupId);
		simpleTrigger2.setQrtzGroup(qrtzGroup);
		if(simpleTrigger2.getState() == null){
			simpleTrigger2.setState("草稿");
		}
		model.addAttribute("simpleTrigger", simpleTrigger2);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		model.addAttribute("message", "SimpleTrigger更新成功");
		return "/jdp-framework/quartz/simpleTrigger-edit";
	}
	
	/**
	 * 通过QrtzGroup的ID创建一个SimpleTrigger
	 * @param qrtzGroupId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{id}")
	public String createSimpleTrigger(@PathVariable("id") Long id,
			Model model){
		SimpleTrigger simpleTrigger = new SimpleTrigger();
		QrtzGroup qrtzGroup = new QrtzGroup();
		qrtzGroup.setId(id);
		simpleTrigger.setQrtzGroup(qrtzGroup);
		simpleTrigger.setState("草稿");
		model.addAttribute("simpleTrigger", simpleTrigger);
		try {
			model.addAttribute("jobs", JobUtil.getAllJobClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return "/jdp-framework/quartz/simpleTrigger-edit";
	}
	
	@RequestMapping(value = "release/{id}", method=RequestMethod.GET)
	public String releaseSimpleTrigger(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes){
		SimpleTrigger simpleTrigger = simpleTriggerService.getSimpleTrigger(id);
		simpleTriggerService.release(simpleTrigger);
		redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"发布成功");
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value = "generate/{id}", method=RequestMethod.GET)
	public String generateSimpleTrigger(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes){
		SimpleTrigger simpleTrigger = simpleTriggerService.getSimpleTrigger(id);
		try {
			simpleTriggerService.generate(simpleTrigger);
			redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"生成成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"生成失败");
			log.error(e.getMessage(),e);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"生成失败");
			log.error(e.getMessage(),e);
		}
		return "redirect:/admin/qrtz/def";
	}
	
	@RequestMapping(value = "stop/{id}", method=RequestMethod.GET)
	public String stopSimpleTrigger(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes){
		SimpleTrigger simpleTrigger = simpleTriggerService.getSimpleTrigger(id);
		try {
			simpleTriggerService.stop(simpleTrigger);
			redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"终止成功");
		} catch (SchedulerException e) {
			redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"终止失败");
			log.error(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", simpleTrigger.getName()+"终止失败");
			log.error(e.getMessage(),e);
		} 
		return "redirect:/admin/qrtz/def";
	}
	
	@ModelAttribute
	public void getSimpleTrigger(@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model){
		if(id != -1){
			SimpleTrigger simpleTrigger = simpleTriggerService.getSimpleTrigger(id);
			model.addAttribute("simpleTrigger", simpleTrigger);
		}
	}
	

	@RequestMapping(value="checkName",method=RequestMethod.POST)
	@ResponseBody
	public String checkName(String name,String initialName){
		if(initialName!=null && initialName.equals(name)){
			return "true";
		}
		SimpleTrigger simpleTrigger = simpleTriggerService.getQrtzDefinitionByName(name);
		if(simpleTrigger==null){
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
		SimpleTrigger simpleTrigger = simpleTriggerService.getQrtzDefinitionByName(code);
		if(simpleTrigger==null){
			return "true";
		}
		return "false";
	}
	
}
