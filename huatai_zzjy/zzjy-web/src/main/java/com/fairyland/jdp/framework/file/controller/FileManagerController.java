package com.fairyland.jdp.framework.file.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.file.domain.Attach;
import com.fairyland.jdp.framework.file.service.FileManagerService;
import com.fairyland.jdp.orm.util.jackson.JSON;

@Controller
@RequestMapping("/fileManager")
public class FileManagerController {
	private Logger log = LoggerFactory.getLogger(getClass());
//
//	@Resource
//	private DictionaryService dictionaryService;
	
	@RequestMapping("/index")
	public ModelAndView index(String functionType, String functionId, String flag) {
		ModelAndView view = new ModelAndView();
		view.addObject("functionType", functionType);
		view.addObject("functionId",functionId);
		view.addObject("flag",flag);
		view.setViewName("/upload");
		return view;
	}

	@RequestMapping("/toUploadFile")
	@ResponseBody
	public void toUploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		try {
//			MultipartResolver reso = new CommonsMultipartResolver(request.getSession().getServletContext());
//			MultipartHttpServletRequest req = reso.resolveMultipart(request);
//			req.setCharacterEncoding("GBK");
//			MultipartFile multFile = req.getFile("fileName");
//			String functionType = req.getParameter("functionType");
//			String functionId = req.getParameter("functionId");
//			Map<String, Object> paraMap = new HashMap<String, Object>();
//			paraMap.put("functionType", functionType);
//			paraMap.put("functionId", functionId);
//			paraMap.put("multFile", multFile);
//			Map<String, Object> resultMap = fileManager.updateFile(paraMap);
//			Attach at = (Attach)resultMap.get("attach");
//			response.getWriter().print(JSON.serialize(at));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@RequestMapping("/toIosAndAndroidUploadFile")
	@ResponseBody
	public void toIosAndAndroidUploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		try {
//			MultipartResolver reso = new CommonsMultipartResolver(request.getSession().getServletContext());
//			MultipartHttpServletRequest req = reso.resolveMultipart(request);
//			req.setCharacterEncoding("GBK");
//			MultipartFile multFile = req.getFile("fileName");
//			String functionType = req.getParameter("functionType");
//			String functionId = req.getParameter("functionId");
//			String flag = req.getParameter("flag");
//			Map<String, Object> paraMap = new HashMap<String, Object>();
//			paraMap.put("functionType", functionType);
//			paraMap.put("functionId", functionId);
//			paraMap.put("multFile", multFile);
//			paraMap.put("flag", flag);
//			Map<String, Object> resultMap = fileManager.updateFile(paraMap);
//			Attach at = (Attach)resultMap.get("attach");
//			if (null != at) {
//				Dictionary dict = new Dictionary();
//				if (".apk".equalsIgnoreCase(at.getFileType())) {
//					dict.setCode("crm.system.androiddownload");
//				} else if(".plist".equalsIgnoreCase(at.getFileType())){
//					dict.setCode("crm.system.plistdownload");
//				}else {
//					dict.setCode("crm.system.iosdownload");
//				}
//				DictionaryItem item = new DictionaryItem();
//				item.setDictionary(dict);
//				item.setCode(System.currentTimeMillis() + "");
//				if (".apk".equalsIgnoreCase(at.getFileType())) {
//					item.setName("Android下载");
//					item.setDescript( at.getAttachId() +"");
//				}else if(".plist".equalsIgnoreCase(at.getFileType())){
//					item.setName("Plist下载");
//					item.setDescript( at.getAttachId() +"");
//				} else {
//					item.setName("IOS下载");
//					item.setDescript( at.getAttachId() +"");
//				}
//				dictionaryService.createDictionaryItemByCode(item);
//				response.getWriter().print(JSON.serialize(at));
//			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping("/delAttachFile")
	public String delAttachFile(HttpServletRequest request) {
//		Long attachId = Long.parseLong(request.getParameter("attachId"));
//		fileManager.delAttach(attachId);
		return "success";
	}
}
