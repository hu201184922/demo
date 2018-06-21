//package com.fairyland.jdp.framework.systemInit;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.fairyland.jdp.framework.dictionary.domain.Dictionary;
//import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
//import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
//import com.fairyland.jdp.framework.menu.domain.MenuItem;
//import com.fairyland.jdp.framework.menu.service.MenuItemService;
//import com.fairyland.jdp.framework.security.domain.Resource;
//import com.fairyland.jdp.framework.security.domain.User;
//import com.fairyland.jdp.framework.security.service.ResourceService;
//import com.fairyland.jdp.framework.security.service.UserService;
//import com.fairyland.jdp.framework.util.SpringUtil;
//
///**
// * 导出工具 包括了 菜单  用户 资源  首选项 以及 数据字典的excel导出
// * @author wenjunlong
// * 2014- 06 -17 9:00  
// */
//public class ExportExcel {
//	
//	private static   MenuItemService menuItemService;
//    
//	private static   UserService userService;
//    
//	private static   ResourceService resourceService;
//    
////    static private  PreferenceDefinitionService preferenceDefinitionService;
////    
////    static private  PreferenceCategoryService preferenceCategoryService;
//    
//    static private  DictionaryService dictionaryService;
//	
//    static{
//    	ExportExcel.menuItemService = SpringUtil.getBean(MenuItemService.class);
//    	ExportExcel.userService = SpringUtil.getBean(UserService.class);
//    	ExportExcel.resourceService = SpringUtil.getBean(ResourceService.class);
////    	ExportExcel.preferenceDefinitionService = SpringUtil.getBean(PreferenceDefinitionService.class);
////    	ExportExcel.preferenceCategoryService = SpringUtil.getBean(PreferenceCategoryService.class);
//    	ExportExcel.dictionaryService = SpringUtil.getBean(DictionaryService.class);
//	}
//	
//	@Autowired
//	public static  void  setMenuItemService(MenuItemService menuItemService){
//		ExportExcel.menuItemService = menuItemService;
//		
//	}
//	
//	@Autowired
//	public static  void  setUserService(UserService userService){
//		ExportExcel.userService = userService;
//	}
//	
//	@Autowired
//	public static void  setResourceService(ResourceService resourceService){
//		ExportExcel.resourceService = resourceService;
//	}
//	
////	@Autowired
////	public   void  setPreferenceDefinitionService(PreferenceDefinitionService preferenceDefinitionService){
////		ExportExcel.preferenceDefinitionService = preferenceDefinitionService;
////	}
////	
////	@Autowired
////	public   void  setPreferenceCategoryService(PreferenceCategoryService preferenceCategoryService){
////		ExportExcel.preferenceCategoryService = preferenceCategoryService;
////	}
//	
//	@Autowired
//	public  static  void  setDictionaryService(DictionaryService dictionaryService){
//		ExportExcel.dictionaryService = dictionaryService;
//	}
//	
//	
//	
//	static public  void ReportExcel(String filePath) throws IOException{
//		HSSFWorkbook wboutput = new HSSFWorkbook();
//		for (int i = 0; i <= 4; i++) {
//			if(i==0)ExportMenuItem(wboutput,filePath);//菜单
//			if(i==1)ExportUser(wboutput,filePath);//用户
//			if(i==2)ExportResource(wboutput,filePath);//资源
////			if(i==3)ExportPreference(wboutput,filePath);//首选项
//			if(i==4)ExportDictionary(wboutput,filePath);//数据字典
//		}
//	}
//
//	//数据字典导出
//	static public void ExportDictionary(HSSFWorkbook wboutput, String filePath) throws IOException {
//		HSSFSheet sheetoutput = wboutput.createSheet();
//        wboutput.setSheetName(4,"数据字典");
//        
//        String[] titles = {"类型","ID","名称","代码","排序号","上级字典","是否系统字典"};
//        createTitleRow(sheetoutput,titles);
//		
//		List<Dictionary> list = dictionaryService.findAllDictionary();
//		
//		int i =0;
//		HSSFRow row = null;
//        // 循环输出表格中的内容
//        for (int j = 0; j < list.size(); j++) {
//        	Dictionary dic = list.get(j);
//        	 //基本读写操作                
//        	row = sheetoutput.createRow((int) i + 1);
//			// 第四步，创建单元格，并设置值
//			row.createCell(0).setCellValue("字典");
//			row.createCell(1).setCellValue(dic.getId());
//			row.createCell(2).setCellValue(dic.getName());
//			row.createCell(3).setCellValue(dic.getCode());
//			
//			if(dic.getSortIndex()!=null)row.createCell(4).setCellValue(dic.getSortIndex());
//			else row.createCell(4).setCellValue("");
//			
//			if(dic.getParent()!=null){
//				row.createCell(5).setCellValue(dic.getPid());
//			}else{ 
//				row.createCell(5).setCellValue("");
//			}
//			row.createCell(6).setCellValue(BooleanUtils.isTrue(dic.getSystem())?1:0);
//			i++;
//        	List<DictionaryItem> list2 = dictionaryService.findDictionaryItemByDicId(dic.getId());
//        	
//        	if(list2.size()>0){
//        		for (int k = 0; k < list2.size(); k++) {
//        			DictionaryItem dict = list2.get(k);
//            		row = sheetoutput.createRow((int) i + 1);
//            		row.createCell(0).setCellValue("字典项");
//            		row.createCell(1).setCellValue(dict.getId());
//        			row.createCell(2).setCellValue(dict.getName());
//        			row.createCell(3).setCellValue(dict.getCode());
//        			if(dict.getSortIndex()!=null)row.createCell(4).setCellValue(dict.getSortIndex());
//        			else row.createCell(4).setCellValue("");
//        			row.createCell(5).setCellValue(dic.getId());
//        			row.createCell(6).setCellValue("");
//                	i++;
//				}
//        		
//        	}
//        }
//
//        FileOutputStream fileoutput = new FileOutputStream(filePath);
//        wboutput.write(fileoutput);
//        fileoutput.close();
//	}
////
////	//首选项导出
////	static public void ExportPreference(HSSFWorkbook wboutput, String filePath) throws IOException {
////		HSSFSheet sheetoutput = wboutput.createSheet();
////        wboutput.setSheetName(3,"首选项");
////        HSSFRow row = sheetoutput.createRow((int) 0);
////        HSSFCell cell = row.createCell(0);
////		cell.setCellValue("分类名称");
////		cell = row.createCell(1);
////		cell.setCellValue("分类code");
////		cell = row.createCell(2);
////		cell.setCellValue("选项名称");
////		cell = row.createCell(3);
////		cell.setCellValue("选项代码");
////		cell = row.createCell(4);
////		cell.setCellValue("数据类型");
////		cell = row.createCell(5);
////		cell.setCellValue("控件类型");
////		cell = row.createCell(6);
////		cell.setCellValue("排序号");
////		
////		List<PreferenceCategory> list=preferenceCategoryService.getPreferenceCategories();
////		int i =0;
////        // 循环输出表格中的内容
////        for (int j = 0; j < list.size(); j++) {
////        	PreferenceCategory  pc = list.get(j);
////        	List<PreferenceDefinition> list2 = preferenceDefinitionService.getPreferenceDefinitions(pc.getId());
////        	if(list2.size()>0){
////        		for (int k = 0; k < list2.size(); k++) {
////        			PreferenceDefinition pd =list2.get(k);
////        			 //基本读写操作                
////                	row = sheetoutput.createRow((int) i + 1);
////        			// 第四步，创建单元格，并设置值
////        			row.createCell(0).setCellValue(pc.getName());
////        			row.createCell(1).setCellValue(pc.getCode());
////        			row.createCell(2).setCellValue(pd.getName());
////        			row.createCell(3).setCellValue(pd.getCode());
////        			row.createCell(4).setCellValue(pd.getDataType());
////        			row.createCell(5).setCellValue(pd.getEditorType());
////        			if(pd.getSortIndex()!=null)row.createCell(6).setCellValue(pd.getSortIndex());
////        			else row.createCell(6).setCellValue("");
////        			i++;
////				}
////        	}else{
////        		row = sheetoutput.createRow((int) i + 1);
////    			row.createCell(0).setCellValue(pc.getName());
////    			row.createCell(1).setCellValue(pc.getCode());
////    			i++;
////        	}
////        }
////
////        FileOutputStream fileoutput = new FileOutputStream(filePath);
////        wboutput.write(fileoutput);
////        fileoutput.close();
////		
////	}
//
//	//资源导出
//	static public void ExportResource(HSSFWorkbook wboutput, String filePath) throws IOException {
//		HSSFSheet sheetoutput = wboutput.createSheet();
//        wboutput.setSheetName(2,"资源");
//        HSSFRow row = sheetoutput.createRow((int) 0);
//        HSSFCell cell = row.createCell(0);
//		cell.setCellValue("资源名称");
//		cell = row.createCell(1);
//		cell.setCellValue("资源类型");
//		cell = row.createCell(2);
//		cell.setCellValue("资源字符串");
//		cell = row.createCell(3);
//		cell.setCellValue("授权字符串");
//		cell = row.createCell(4);
//		cell.setCellValue("排序号");
//		cell = row.createCell(5);
//		cell.setCellValue("上级资源");
//		
//		List<Resource> list = resourceService.findAllResource();
//        // 循环输出表格中的内容
//        for (int i = 0; i < list.size(); i++) {
//        	Resource resource = list.get(i);
//            //基本读写操作                
//        	row = sheetoutput.createRow((int) i + 1);
//			// 第四步，创建单元格，并设置值
//			row.createCell(0).setCellValue(resource.getName());
//			row.createCell(1).setCellValue(resource.getResType());
//			row.createCell(2).setCellValue(resource.getResString());
//			row.createCell(3).setCellValue(resource.getPerString());
//			if(resource.getSortIndex()!=null)row.createCell(4).setCellValue(resource.getSortIndex());
//			else row.createCell(4).setCellValue("");
//			if(resource.getParent()!=null)row.createCell(5).setCellValue(resource.getParent().getName());
//			else row.createCell(5).setCellValue("");
//        }
//
//        FileOutputStream fileoutput = new FileOutputStream(filePath);
//        wboutput.write(fileoutput);
//        fileoutput.close();
//		
//	}
//	
//	//用户导出
//	static public void ExportUser(HSSFWorkbook wboutput, String filePath) throws IOException {
//		HSSFSheet sheetoutput = wboutput.createSheet();
//        wboutput.setSheetName(1,"用户");
//        HSSFRow row = sheetoutput.createRow((int) 0);
//        HSSFCell cell = row.createCell(0);
//		cell.setCellValue("账户");
//		cell = row.createCell(1);
//		cell.setCellValue("用户名");
//		List<User> list = userService.findAllUser();
//		
//        // 循环输出表格中的内容
//        for (int i = 0; i < list.size(); i++) {
//            //基本读写操作                
//        	User user = list.get(i);
//        	row = sheetoutput.createRow((int) i + 1);
//			// 第四步，创建单元格，并设置值
//			row.createCell(0).setCellValue(user.getAccount());
//			row.createCell(1).setCellValue(user.getUserName());
//        }
//
//        FileOutputStream fileoutput = new FileOutputStream(filePath);
//        wboutput.write(fileoutput);
//        fileoutput.close();
//	}
//
//	//菜单导出
//	static public  void ExportMenuItem(HSSFWorkbook wboutput,String filePath) throws IOException {
//		HSSFSheet sheetoutput = wboutput.createSheet();
//        wboutput.setSheetName(0,"菜单");
//        String[] titles = {"编号","菜单名称","授权字符串","菜单路径","排序号","上级菜单"};
//       
//        createTitleRow(sheetoutput,titles);
//        
//		List<MenuItem> list =  menuItemService.findAllMenuItem();
//		
//		HSSFRow row = null;
//        // 循环输出表格中的内容
//        for (int i = 0; i < list.size(); i++) {
//        	MenuItem menu = list.get(i);
//            //基本读写操作                
//        	row = sheetoutput.createRow((int) i + 1);
//			// 第四步，创建单元格，并设置值
//			row.createCell(0).setCellValue(menu.getId());
//			row.createCell(1).setCellValue(menu.getName());
//			row.createCell(2).setCellValue(menu.getUrl());
//			row.createCell(3).setCellValue(menu.getPermString());
//			if(menu.getSortIndex()!=null)row.createCell(4).setCellValue(menu.getSortIndex());
//			else row.createCell(4).setCellValue("");
//			if(menu.getPid()!=null)row.createCell(5).setCellValue(menu.getPid());
//			else row.createCell(5).setCellValue("");
//        }
//
//        FileOutputStream fileoutput = new FileOutputStream(filePath);
//        wboutput.write(fileoutput);
//        fileoutput.close();
//	}
//	
//	private static void createTitleRow(HSSFSheet sheetoutput,String[] titles){
//		HSSFRow row = sheetoutput.createRow((int) 0);
//        for (int i = 0; i <titles.length; i++) {
//        	HSSFCell cell = row.createCell(i);
//        	cell.setCellValue(titles[i]);
//        }
//	}
//
//}
