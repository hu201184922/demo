package com.huatai.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.model.FixedList;
import com.huatai.web.model.Target;
import com.huatai.web.service.FixedListService;

/**
 * @author 胡智辉 2017年7月17日
 */
@Controller
@RequestMapping("admin/fixedList")
public class FixedListController {

	final static Logger logger = LoggerFactory.getLogger(FixedListController.class);

	@Autowired
	private FixedListService fixedListService;
	
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		String[] ds = dstr.split(",");
		List<DictionaryItem> dictItem = new ArrayList<>();
		for (String d : ds) {
			 dictItem.add(dictionaryService.findByDictCodeAndDictItemCode("DETP_DICT", d));
		}	
		view.addObject("dictItem", dictItem);
		List<Target> targets = fixedListService.selectTargetByTree(null);
		view.addObject("targets", targets);
		view.setViewName("/admin/fixedList/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<FixedList> find(Pager<FixedList> pager, FixedList record) {
		Pager<FixedList> result = fixedListService.findByPager(pager, record);
		logger.debug("---------------find success--------------");
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int create(@Valid FixedList record) {
		int result = 0;
		String flCode = record.getFlCode();
		FixedList fixed = fixedListService.findFixedByFlCode(flCode);
		if(StringUtil.isNull(fixed)){
			try {
				record.setOpType("A");
				record.setCreateTime(new Date());	
				record.setModifyTime(new Date());
				record.setCreatorId(SessionContextUtils.getLoginName().toString());
				record.setModifierId(SessionContextUtils.getLoginName().toString());
				result = fixedListService.add(record);
				logger.debug("---------------add success--------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}else{
			record.setOpType("U");
			record.setCreateTime(fixed.getCreateTime());
			record.setModifyTime(new Date());
			record.setModifierId(SessionContextUtils.getLoginName().toString());
			result = fixedListService.update(record);
			return result;
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid FixedList record) {
		record.setModifierId(SessionContextUtils.getLoginUserId().toString());
		record.setOpType("U");
		record.setModifyTime(new Date());
		int result = fixedListService.update(record);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid FixedList record) {
		record.setModifierId(SessionContextUtils.getLoginUserId().toString());
		record.setOpType("D");
		int result = fixedListService.update(record);
		return result;
	}

}
