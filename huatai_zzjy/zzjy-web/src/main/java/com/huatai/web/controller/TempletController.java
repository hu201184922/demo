package com.huatai.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Templet;
import com.huatai.web.service.TempletService;

/**
 * @author 胡智辉
 * 2017年7月19日
 */
@Controller
@RequestMapping("admin/templet")
public class TempletController {

	@Autowired
	private TempletService templetService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/templet/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Templet> getPager(Pager<Templet> pager, Templet templet) {
		return templetService.findTempletByPage(pager, templet);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Templet create(@Valid Templet templet) {
		templet.setOpType("A");
		templet.setCreateTime(new Date());
		templet.setModifyTime(new Date());
		templet.setCreatorId(SessionContextUtils.getLoginName().toString());
		templet.setModifierId(SessionContextUtils.getLoginName().toString());
		templetService.addTemplet(templet);
		return templet;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int update(@Valid Templet templet) {
		templet.setOpType("U");
		templet.setModifierId(SessionContextUtils.getLoginName().toString());
		int result =templetService.updateTemplet(templet);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid Templet templet) {
		templet.setOpType("D");
		templet.setModifierId(SessionContextUtils.getLoginName().toString());
		int result = templetService.updateTemplet(templet);
		return result;
	}
	
}
