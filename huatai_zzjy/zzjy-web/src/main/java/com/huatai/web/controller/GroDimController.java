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

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.GroDim;
import com.huatai.web.service.GroDimService;

/**
 * @author 胡智辉 2017年7月20日
 */
@Controller
@RequestMapping("admin/grodim")
public class GroDimController {

	@Autowired
	private GroDimService groDimService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/grodim/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<GroDim> getPager(Pager<GroDim> pager, GroDim groDim) {
		Pager<GroDim> result = groDimService.findGroDimByPage(pager, groDim);
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int create(@Valid GroDim groDim) throws ParseException {
		groDim.setOpType("A");
		groDim.setCreatorId(SessionContextUtils.getLoginName().toString());
		groDim.setCreateTime(new Date());
		groDim.setModifierId(SessionContextUtils.getLoginName().toString());
		groDim.setModifyTime(new Date());
		int result = groDimService.addGroDim(groDim);
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid GroDim groDim) throws ParseException {
		groDim.setModifierId(SessionContextUtils.getLoginUserId().toString());
		groDim.setOpType("U");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		groDim.setModifyTime(sdf.parse(sdf.format(new Date())));
		int result = groDimService.updateGroDim(groDim);
		return result;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public void delete(Integer id) {
		GroDim record = new GroDim();
		record.setGdId(id);
		record.setOpType("D");
		record.setModifierId(SessionContextUtils.getLoginUserId().toString());
		groDimService.update(record);
	}

}
