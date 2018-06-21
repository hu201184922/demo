package com.huatai.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.Templet;
import com.huatai.web.service.TargetService;
import com.huatai.web.service.TemRegService;
import com.huatai.web.service.TempletService;
import com.huatai.web.utils.Constants;

/**
 * @author 胡智辉 2017年7月19日
 */
@Controller
@RequestMapping("admin/task")
public class TaskController {

	@Autowired
	private TempletService templetService;
	@Autowired
	private TemRegService temRegService;
	@Autowired
	private TargetService targetService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Target> subject = targetService.findTargetBySubject();
		mv.addObject("subject", subject);
		List<Templet> templet = templetService.findTempletByAll();
		mv.addObject("templet", templet);
		mv.setViewName("/admin/task/index");
		return mv;
	}

	@RequestMapping(value = "regTask", method = RequestMethod.GET)
	public ModelAndView index(Integer tempId, String tempLink) {
		Templet templet = templetService.findById(tempId);
		templet.setTempLink(tempLink);
		ModelAndView model = new ModelAndView();
		model.addObject("templet", templet);
		model.setViewName("/admin/task/regTask");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "regList")
	public Pager<TemReg> getPager(Pager<TemReg> pager, TemReg record) {
		Pager<TemReg> result = temRegService.findByPager(pager, record);
		for (TemReg temReg : result.getPageItems()) {
			if ("TEMP02_REG07".equals(temReg.getRegCode())) {
				result.getPageItems().remove(temReg);
				return result;
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Templet> getPager(Pager<Templet> pager, Templet templet) {
		pager.setPageItems(Constants.getTemplate());
		pager.setTotalCount(Constants.getTemplate().size());
		return pager;
	}

}
