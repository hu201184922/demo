
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
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.GroDim;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.service.GroDimDetailService;
import com.huatai.web.service.GroDimService;


/**
 *@描述   :
 *@作者   : 程乐飞
 *@日期时间: 2017年8月3日 下午1:27:12
 */

@Controller
@RequestMapping("admin/groDimDetail")
public class GroDimDetailController {
	
	@Autowired
	private GroDimDetailService groDimDetailService;
	
	@Autowired
	private GroDimService groDimService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index(Integer gdId) {
	   GroDim groDim = groDimService.findById(gdId);
		ModelAndView model = new ModelAndView();
		model.addObject("groDim", groDim);
		model.setViewName("/admin/groDimDetail/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<GroDimDetail> getPager(Pager<GroDimDetail> pager, GroDimDetail record) {
		return groDimDetailService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public GroDimDetail create(GroDimDetail record) throws ParseException {
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		this.groDimDetailService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public GroDimDetail update(@Valid GroDimDetail record, RedirectAttributes redirectAttributes) throws Exception {
		//User user = SessionContextUtils.getCurrentUser();
		//获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.groDimDetailService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		GroDimDetail record=new GroDimDetail();
		record.setGddId(id);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginUserId().toString());
		groDimDetailService.update(record);
	}
	

}
