package com.huatai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huatai.web.model.NoticeFile;
import com.huatai.web.service.NoticeFileService;
/**
 * @author 胡智辉 2017年8月9日
 */
@Controller
@RequestMapping("admin/noticeFile")
public class NoticeFileController {

	final static Logger logger = LoggerFactory.getLogger(NoticeFileController.class);
	
	@Autowired
	private NoticeFileService noticeFileService;

	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/notice/index");
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public NoticeFile find(NoticeFile record) {
		NoticeFile result=noticeFileService.findNoticeFile(record);
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public int upload(@Valid NoticeFile record,HttpServletRequest request, 
			HttpServletResponse response) {
		int result = 0;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid NoticeFile record) {
		int result = noticeFileService.update(record);
		return result;
	}

}
