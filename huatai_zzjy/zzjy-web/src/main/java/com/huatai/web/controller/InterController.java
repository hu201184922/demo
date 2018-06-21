package com.huatai.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.Inter;
import com.huatai.web.service.InterService;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月18日 下午1:43:53
 */
@Controller
@RequestMapping("admin/inter")
public class InterController {

	@Autowired
	private InterService interService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/inter/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<Inter> getPager(Pager<Inter> pager, Inter record) {
		return this.interService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public Inter create(@Valid Inter record) throws ParseException {
		record.setOpType("A");
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		record.setCreatorId(SessionContextUtils.getLoginName().toString());		
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		this.interService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Inter update(@Valid Inter record, RedirectAttributes redirectAttributes) throws Exception {
		//User user = SessionContextUtils.getCurrentUser();
		//获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.interService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id,Inter record) {
		record.setInterId(id);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		this.interService.updateByPrimaryKeySelective(record);
	}
	
	
}
