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
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.QueryDimDetail;
import com.huatai.web.service.QueryDimDetailService;
import com.huatai.web.service.QueryDimService;

/**
 * @description:
 * @author ：沈从会
 * @datetime : 2017年7月20日 下午5:02:35
 */
@Controller
@RequestMapping("admin/queryDimDetail")
public class QueryDimDetailController {

	@Autowired
	private QueryDimDetailService queryDimDetailService;
	@Autowired
	private QueryDimService queryDimService;

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView index(Integer qdId) {
		ModelAndView model = new ModelAndView();
		QueryDim qdd = queryDimService.selectByPrimaryKey(qdId);
		model.addObject("queryDim", qdd);
		model.setViewName("/admin/queryDimDetail/index");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Pager<QueryDimDetail> getPager(Pager<QueryDimDetail> pager, @Valid QueryDimDetail record) {
		return queryDimDetailService.findByPager(pager, record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public QueryDimDetail create(@Valid QueryDimDetail record) throws ParseException {
		record.setOpType("A"); 
		record.setCreatorId(SessionContextUtils.getLoginName().toString());
		record.setCreateTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setModifyTime(new Date());
		this.queryDimDetailService.insert(record);
		return record;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public QueryDimDetail update(@Valid QueryDimDetail record, RedirectAttributes redirectAttributes) throws Exception {
		// User user = SessionContextUtils.getCurrentUser();
		// 获得当前用户的id
		record.setCreateTime(null);
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setModifyTime(sdf.parse(sdf.format(new Date())));
		record.setOpType("U");
		this.queryDimDetailService.updateByPrimaryKeySelective(record);
		return record;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id) {
		QueryDimDetail record=new QueryDimDetail();
		record.setQddId(id);
		record.setOpType("D");
		record.setModifyTime(new Date());
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		queryDimDetailService.update(record);
	}
}
