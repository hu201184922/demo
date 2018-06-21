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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.TemRegExample;
import com.huatai.web.model.Templet;
import com.huatai.web.service.TemRegService;
import com.huatai.web.service.TempletService;


/**
 *@描述   :
 *@作者   : 程乐飞
 *@日期时间: 2017年7月28日 下午2:11:55
 */

@Controller
@RequestMapping("admin/temReg")
public class TemRegController {
	@Autowired
	private TempletService templetService;
	@Autowired
	private TemRegService temRegService;
	

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index(Integer tempId) {
		Templet templet = templetService.findById(tempId);
		ModelAndView model = new ModelAndView();
		model.addObject("templet", templet);
		model.setViewName("/admin/temReg/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<TemReg> getPager(Pager<TemReg> pager, TemReg record) {
		Pager<TemReg> result=temRegService.findByPager(pager, record);
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public TemReg create(@Valid TemReg record) throws ParseException {
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		this.temRegService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public TemReg update(@Valid TemReg record, RedirectAttributes redirectAttributes) throws Exception {
		//User user = SessionContextUtils.getCurrentUser();
		//获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.temRegService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		temRegService.deleteByPrimaryKey(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "findTemReg")
	public List<TemReg> findTemReg(TemReg record) {
		TemRegExample example=new TemRegExample();
		example.createCriteria().andTempIdEqualTo(record.getTempId());
		List<TemReg> result=temRegService.selectByExample(example);
		return result;
	}
	

}
