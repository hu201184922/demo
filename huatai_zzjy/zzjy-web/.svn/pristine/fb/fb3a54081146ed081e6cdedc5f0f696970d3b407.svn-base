package com.fairyland.jdp.framework.uploadtemplate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Controller
//@RequestMapping("/admin/specialDateTemplate")
public class SpecialDateTemplateController {
	private Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	private CalendarService calendarService;
//	
//	  @RequestMapping("toSpecialDateTemplate")
//	  public String toSpecialDateTemplate(Model model){
//		  List<Attachment> specialDateTemplateList = calendarService.selectSpecialDateTemplateList();
//		  model.addAttribute("specialDateTemplateList", specialDateTemplateList);
//		  model.addAttribute("specialDateTemplateSize", specialDateTemplateList.size());
//		  return "admin/template/specialDateTemplate";
//	  }
//	  
//	  @RequestMapping("uploadSpecialDateTemplate")
//	  @ResponseBody
//	  public Map<String,Object> uploadSpecialDateTemplate(HttpServletRequest request,HttpServletResponse response,
//			  @RequestParam(value="file") MultipartFile file,
//				@Valid @ModelAttribute(value="attachment")  Attachment attachment
//			  ) throws IOException{
//		  	User user = SessionContextUtils.getCurrentUser();
//		  	Map<String,Object> resultMap =null;
//		  	response.setContentType("text/html;charset=UTF-8");
//				Map<String,Object> map = new HashMap<String, Object>();
//				String realPath = PropsUtil.getUploadPath().replace("\\","/")+PropsUtil.get("resourcePath");
//				String contextPath = request.getContextPath();
//				if(!file.isEmpty()){
//					attachment.setAgentCode(user.getAccount());
//					attachment.setCreatedby(user.getUserName());
//					attachment.setModifiedby(user.getUserName());
//					attachment.setOpType("A");
//					attachment.setCreatetime(new Date());
//					attachment.setModifytime(new Date());
//					//attachment.setPicPath(file.getOriginalFilename());
//				}
//				resultMap = calendarService.uploadFile(file, attachment, user, contextPath,realPath);
//			return resultMap;
//	  }
//	  
//	  @RequestMapping("deleteSpecialDateTemplate")
//	  @ResponseBody
//	  public Map<String,Object> deleteSpecialDateTemplate(String attachmentId){
//		  Map<String,Object> params = new HashMap<String, Object>();
//		  params.put("attachmentId", attachmentId);
//		  try {
//			calendarService.deleteSpecialDateTemplate(params);
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			params.put("result", "fail");
//		}
//		  params.put("result", "success");
//		  return params;
//	  }
//	  @RequestMapping("toSpecialDateManage")
//	  public String toSpecialDateManage(Model model,HttpServletRequest request){
//		  String flag = request.getParameter("flag");
//		  List<SpecialDateWish> specialDateWishList = calendarService.selectSpecialDateWishList();
//		  if(StringUtil.isBlank(specialDateWishList)||specialDateWishList.size()<1||"0".equals(flag)){
//			  model.addAttribute("flag", "0");
//			  model.addAttribute("type",specialDateWishList.size());
//		  }
//		  
//		  List<Attachment> specialDateTemplateList = calendarService.selectSpecialDateTemplateList();
//		  int templateListSize=0;
//		  if(specialDateTemplateList!=null&&specialDateTemplateList.size()>0){
//			  templateListSize=specialDateTemplateList.size();
//		  }
//		  model.addAttribute("templateListSize", templateListSize);
//		  model.addAttribute("specialDateTemplateList", specialDateTemplateList);
//		  model.addAttribute("specialDateWishList", specialDateWishList);
//		  return "admin/template/specialDateManage";
//	  }
//	  
//	  @RequestMapping("saveSpecialDateWish")
//	  @ResponseBody
//	  public Map<String,Object> saveSpecialDateWish(SpecialDateWish specialDateWish){
//		  Map<String,Object> resultMap = new HashMap<String, Object>();
//		  User user = SessionContextUtils.getCurrentUser();
//		  try {
//			specialDateWish.setCreatedBy(user.getAccount()); 
//			specialDateWish.setModifiedBy(user.getAccount());
//			specialDateWish.setCreatetime(new Date());
//			specialDateWish.setModifytime(new Date());
//			calendarService.saveSpecialDateWish(specialDateWish);
//			resultMap.put("msg", "success");
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			resultMap.put("msg", "fail");
//		}
//		  return resultMap;
//	  }
//	  
//	  @RequestMapping("toSpecialDateWishDetail")
//	  @ResponseBody
//	  public Map<String,Object> toSpecialDateWishDetail(String type){
//		  Map<String,Object> params = new HashMap<String, Object>();
//		  params.put("type", type);
//		  SpecialDateWish specialDateWish = calendarService.selectSpecialDateWishDetail(params);
//		  List<Attachment> specialDateTemplateList =calendarService.getSpecialDateTemplateList(params);
//		  if(StringUtil.isNotNull(specialDateWish)){
//			  params.put("wishCotent", specialDateWish.getWishContent());
//		  }
//		  params.put("specialDateTemplateList", specialDateTemplateList);
//		  return params;
//	  }
//	  
//	  @RequestMapping("updateSpecialDateWish")
//	  @ResponseBody
//	  public Map<String,Object> updateSpecialDateWish(SpecialDateWish specialDateWish){
//		  Map<String,Object> resultMap = new HashMap<String, Object>();
//		  User user = SessionContextUtils.getCurrentUser();
//		  try {
//			specialDateWish.setModifytime(new Date());
//			specialDateWish.setModifiedBy(user.getAccount());
//			calendarService.updateSpecialDateWish(specialDateWish);
//			resultMap.put("msg", "success");
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			resultMap.put("msg", "fail");
//		}
//		  return resultMap;
//	  }
//	  
//	  @RequestMapping("deleteSpecialDateWish")
//	  @ResponseBody
//	  public Map<String,Object> deleteSpecialDateWish(String type){
//		  Map<String,Object> params = new HashMap<String, Object>();
//		  params.put("type", type);
//		  try {
//			calendarService.deleteSpecialDateWish(params);
//			calendarService.deleteSpecialDateTemplate(params);
//			params.put("msg", "success");
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			params.put("msg", "fail");
//		}
//		  return params;
//	  }
}
