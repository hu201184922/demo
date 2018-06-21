package com.huatai.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.TriggleExecute;
import com.huatai.web.service.TriggleExecuteService;

/**
 * @author 胡智辉 2017年7月17日
 */
@Controller
@RequestMapping("admin/triggleExecute")
public class TriggleExecuteController {

	final static Logger logger = LoggerFactory.getLogger(TriggleExecuteController.class);

	@Autowired
	private TriggleExecuteService triggleExecuteService;
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/triggleExecute/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<TriggleExecute> find(Pager<TriggleExecute> pager, TriggleExecute record) {
		Pager<TriggleExecute> result = triggleExecuteService.findByPager(pager, record);
		logger.debug("---------------find success--------------");
		return result;
	}
}
