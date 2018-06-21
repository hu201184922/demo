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
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.Target;
import com.huatai.web.service.GroDimDetailService;
import com.huatai.web.service.TarGroDimService;
import com.huatai.web.service.TargetService;

/**
 * @description:
 * @author ：程乐飞
 * @datetime : 2017年7月24日 下午2:24:40
 */
@Controller
@RequestMapping("admin/tarGroDim")
public class TarGroDimController {

	@Autowired
	private TarGroDimService tarGroDimService;

	@Autowired
	private GroDimDetailService groDimDetailService;

	@Autowired
	private TargetService targetService;

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		List<GroDimDetail> groDimDetails = this.groDimDetailService.findAllGroDimDetail();
		List<Target> targets = this.targetService.findAllTarget();
		model.addObject("targets", targets);
		model.addObject("groDimDetails", groDimDetails);
		model.setViewName("/admin/tarGroDim/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<TarGroDim> getPager(Pager<TarGroDim> pager, TarGroDim record) {
		if (!"".equals(record.getGroDimName()) && null != record.getGroDimName()) {
			GroDimDetail groDimDetail = this.groDimDetailService.selectByGroDimName(record.getGroDimName().trim());
			record.setGddId(groDimDetail.getGddId());
		}
		return tarGroDimService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public TarGroDim create(@Valid TarGroDim record) throws ParseException {
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		this.tarGroDimService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public TarGroDim update(@Valid TarGroDim record, RedirectAttributes redirectAttributes) throws Exception {
		// User user = SessionContextUtils.getCurrentUser();
		// 获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.tarGroDimService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		tarGroDimService.deleteByPrimaryKey(id);
	}
}
