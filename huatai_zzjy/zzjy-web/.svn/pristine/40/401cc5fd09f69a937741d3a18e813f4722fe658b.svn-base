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
import com.huatai.web.model.DictItem;
import com.huatai.web.model.QueryDim;
import com.huatai.web.service.QueryDimService;

/**
 * @description:
 * @author     ：沈从会
 * @datetime   : 2017年7月18日 下午1:43:53
 */
@Controller
@RequestMapping("admin/queryDim")
public class QueryDimController {

	@Autowired
	private QueryDimService queryDimService;
	

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		List<DictItem> dialogboxs = queryDimService.getDialogBoxs();
		model.addObject("boxs", dialogboxs);
		model.setViewName("/admin/queryDim/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<QueryDim> getPager(Pager<QueryDim> pager, QueryDim record) {
		return queryDimService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public void create(@Valid QueryDim record) throws ParseException {
		record.setIsAuth("1");
		record.setQueryDimType("in");
		record.setOpType("A");
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setCreateTime(sdf.parse(sdf.format(new Date())));
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		this.queryDimService.insert(record);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public QueryDim update(@Valid QueryDim record, RedirectAttributes redirectAttributes) throws Exception {
		//获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.queryDimService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		QueryDim record=new QueryDim();
		record.setQdId(id);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		queryDimService.update(record);
	}
}
