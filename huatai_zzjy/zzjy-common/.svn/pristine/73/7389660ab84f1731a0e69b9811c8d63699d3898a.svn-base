//package com.fairyland.jdp.framework.systemInit;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PushbackInputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.poi.POIXMLDocument;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
//
///**
// * 系统初始化工具 包括了 菜单  用户 资源  首选项 以及 数据字典的初始化的excel导入
// * @author wenjunlong
// * 2014- 06 -13 11:00  
// */
//public class ImportExcel {
//	private static Logger log = LoggerFactory.getLogger(ImportExcel.class);
//	static private MenuItemService menuItemService;
//
//	static private UserService userService;
//
//	static private ResourceService resourceService;
//
////	static private PreferenceDefinitionService preferenceDefinitionService;
////
////	static private PreferenceCategoryService preferenceCategoryService;
//
//	static private DictionaryService dictionaryService;
//
//	static {
//		ImportExcel.menuItemService = SpringUtil.getBean(MenuItemService.class);
//		ImportExcel.userService = SpringUtil.getBean(UserService.class);
//		ImportExcel.resourceService = SpringUtil.getBean(ResourceService.class);
////		ImportExcel.preferenceDefinitionService = SpringUtil.getBean(PreferenceDefinitionService.class);
////		ImportExcel.preferenceCategoryService = SpringUtil.getBean(PreferenceCategoryService.class);
//		ImportExcel.dictionaryService = SpringUtil.getBean(DictionaryService.class);
//	}
//
//	@Autowired
//	public static void setMenuItemService(MenuItemService menuItemService) {
//		ImportExcel.menuItemService = menuItemService;
//	}
//
//	@Autowired
//	public static  void setUserService(UserService userService) {
//		ImportExcel.userService = userService;
//	}
//
//	@Autowired
//	public static void setResourceService(ResourceService resourceService) {
//		ImportExcel.resourceService = resourceService;
//	}
//
////	@Autowired
////	public void setPreferenceDefinitionService(
////			PreferenceDefinitionService preferenceDefinitionService) {
////		ImportExcel.preferenceDefinitionService = preferenceDefinitionService;
////	}
////
////	@Autowired
////	public void setPreferenceCategoryService(
////			PreferenceCategoryService preferenceCategoryService) {
////		ImportExcel.preferenceCategoryService = preferenceCategoryService;
////	}
//
//	@Autowired
//	public static  void setDictionaryService(DictionaryService dictionaryService) {
//		ImportExcel.dictionaryService = dictionaryService;
//	}
//		
//	
//	/**
//	 * excel文件导入解析保存
//	 * @param filePath 为导文件的所在路径
//	 */
//	static public  void readExcel(String filePath){
//		InputStream is = null; 
//		try {
//			is = new BufferedInputStream(new FileInputStream((new File(filePath)))); 
//			//调用工具类，获取兼容的Workbook接口（兼容性）
//			Workbook wb = createCommonWorkbook(is);
//			//获得工作表的数量
//			int sheets = wb.getNumberOfSheets();
//			log.debug("工作表页数" + sheets);
//			for (int i = 0; i < sheets; i++) {
//				Sheet sheet = wb.getSheetAt(i);
//				if("菜单".equals(sheet.getSheetName())) createMenu(sheet);
//				if("用户".equals(sheet.getSheetName())) createUser(sheet);
//				if("资源".equals(sheet.getSheetName())) createResource(sheet);
////				if("首选项".equals(sheet.getSheetName())) createPreference(sheet);
//				if("数据字典".equals(sheet.getSheetName())) createDictionary(sheet);
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//    }
//	
//	//菜单导入
//	@SuppressWarnings("static-access")
//	static public  void  createMenu(Sheet sheet){
//		//获得菜单工作表行数
//		int rowNum = sheet.getLastRowNum()+1;
//		//获得列数
//		int cellNum = sheet.getRow(0).getLastCellNum();
//		Map<Long,MenuItem> menuMap = new HashMap<Long,MenuItem>();
//		Map<Long,Long> parentChildMap = new HashMap<Long,Long>();
//		for (int i = 1; i < rowNum; i++) {
//			MenuItem menu = new MenuItem();
//			Row row = sheet.getRow(i);
//			Long currentId = null;
//			Long parentId = null;
//			for (int j = 0; j < cellNum; j++) {
//				Cell cell = row.getCell(j);
//				if(cell!=null){
//					//根据列数来赋值
//					switch (j) {
//					case 0:
//				    	if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
//				    		currentId = (long)cell.getNumericCellValue();
//				    	break;
//				    case 1:
//				    	if(cell.getCellType()==cell.CELL_TYPE_STRING)
//				    		menu.setName(cell.getRichStringCellValue().toString());
//				    	else menu.setName(((int)cell.getNumericCellValue())+"");
//				    	break;
//				    case 2:
//				    	if(cell.getCellType()==cell.CELL_TYPE_STRING)
//				    		menu.setUrl(cell.getRichStringCellValue().toString());
//				    	else menu.setUrl(((int)cell.getNumericCellValue())+"");
//				    	break;
//				    case 3:
//				    	if(cell.getCellType()==cell.CELL_TYPE_STRING)
//				    		menu.setPermString(cell.getRichStringCellValue().toString());
//				    	else menu.setPermString(((int)cell.getNumericCellValue())+"");
//				    	break;
//				    case 4:
//				    	if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
//				    		menu.setSortIndex((int)cell.getNumericCellValue());
//				    	break;
//				    case 5:
//				    	if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
//				    		parentId = (long)cell.getNumericCellValue();
//				    	}
////				    	String parent = "";
////				    	if(cell.getCellType()==cell.CELL_TYPE_STRING)parent = cell.getRichStringCellValue().toString();
////				    	else parent = ((int)cell.getNumericCellValue())+"";
////				    	if(menuMap.containsKey(parent))menu.setParent(menuMap.get(parent));
//				    	break;
//				    default:;break;
//					}
//				}
//			}
//
//			menuItemService.createMenuItem(menu);
//			menuMap.put(currentId, menu);
//			parentChildMap.put(currentId, parentId);
//		}
//		
//		for (Long currentId : parentChildMap.keySet()) {
//			MenuItem menuItem = menuMap.get(currentId);
//			menuItem.setParent(menuMap.get(parentChildMap.get(currentId)));
//			menuItemService.updateMenuItem(menuItem);
//		}
//		
//		parentChildMap.clear();
//		menuMap.clear();
//	}
//	
//	//用户导入
//	@SuppressWarnings("static-access")
//	static public  void createUser(Sheet sheet) {
//		// 获得用户工作表行数
//		int rowNum = sheet.getLastRowNum() + 1;
//		// 获得列数
//		int cellNum = sheet.getRow(0).getLastCellNum();
//		for (int i = 1; i < rowNum; i++) {
//			User user = new User();
//			Row row = sheet.getRow(i);
//			for (int j = 0; j < cellNum; j++) {
//				Cell cell = row.getCell(j);
//				if (cell != null) {
//					// 根据列数来赋值
//					switch (j) {
//					case 0:
//						if (cell.getCellType() == cell.CELL_TYPE_STRING)
//							user.setAccount(cell.getRichStringCellValue()
//									.toString());
//						else
//							user.setAccount(((int) cell.getNumericCellValue())
//									+ "");
//						break;
//					case 1:
//						if (cell.getCellType() == cell.CELL_TYPE_STRING)
//							user.setUserName(cell.getRichStringCellValue()
//									.toString());
//						else
//							user.setUserName(((int) cell.getNumericCellValue()) + "");
//						break;
//						
//					default:
//						;
//						break;
//					}
//				}
//			}
//			if("admin".equals(user.getAccount())){
//				user.setIsAdmin(Boolean.TRUE);
//				user.setPlainPassword(user.getAccount());
//			}else{
//				user.setPlainPassword("111111");
//			}
//			userService.registerUser(user);
//		}
//		
//
//	}
//	
//	//资源导入
//	@SuppressWarnings("static-access")
//	static public  void createResource(Sheet sheet){
//		// 获得菜单工作表行数
//		int rowNum = sheet.getLastRowNum() + 1;
//		// 获得列数
//		int cellNum = sheet.getRow(0).getLastCellNum();
//		Map<String, Resource> resMap = new HashMap<String, Resource>();
////		List<Resource> resources = new ArrayList<Resource>();
//		for (int i = 1; i < rowNum; i++) {
//			Resource res = new Resource();
//			Row row = sheet.getRow(i);
//			for (int j = 0; j < cellNum; j++) {
//				
//				Cell cell = row.getCell(j);
//				if (cell != null) {
//					// 根据列数来赋值
//					switch (j) {
//					case 0:
//						if (cell.getCellType() == cell.CELL_TYPE_STRING)
//							res.setName(cell.getRichStringCellValue()
//									.toString());
//						else
//							res.setName(((int) cell.getNumericCellValue())
//									+ "");
//						break;
//					case 1:
//						if (cell.getCellType() == cell.CELL_TYPE_STRING)
//							res.setResType(cell.getRichStringCellValue()
//									.toString());
//						else
//							res.setResType(((int) cell.getNumericCellValue()) + "");
//						break;
//					case 2:
//						if (cell.getCellType() == cell.CELL_TYPE_STRING)
//							res.setResString(cell.getRichStringCellValue()
//									.toString());
//						else
//							res.setResString(((int) cell
//									.getNumericCellValue()) + "");
//						break;
//					case 3:
//						if (cell.getCellType() == cell.CELL_TYPE_STRING)
//							res.setPerString(cell.getRichStringCellValue()
//									.toString());
//						else
//							res.setPerString(((int) cell
//									.getNumericCellValue()) + "");
//						break;
//						
//					case 4:
//						if (cell.getCellType() == cell.CELL_TYPE_NUMERIC)
//							res.setSortIndex((int) cell.getNumericCellValue());
//						break;
//					case 5:
//						 String parent = "";
//						 if(cell.getCellType()==cell.CELL_TYPE_STRING)parent =
//						 cell.getRichStringCellValue().toString();
//						 else parent = ((int)cell.getNumericCellValue())+"";
//						 if(resMap.containsKey(parent))res.setParent(resMap.get(parent));
//						break;
//					default:
//						;
//						break;
//					}
//				}
////				 resourceService.updateResource(res);
//				res.setEnabled(true);
//				resMap.put(res.getName(), res);
//			}
//
//		}
//		resourceService.createResources(new ArrayList<Resource>(resMap.values()));
//	}
////	
////	//首选项导入
////	@SuppressWarnings("static-access")
////	static public  void createPreference(Sheet sheet){
////		
////		//获得菜单工作表行数
////		int rowNum = sheet.getLastRowNum()+1;
////		//获得列数
////		int cellNum = sheet.getRow(0).getLastCellNum();
////		Map<String,PreferenceCategory> pcMap = new HashMap<String,PreferenceCategory>();
////		for (int i = 1; i < rowNum; i++) {
////			PreferenceDefinition pd = new PreferenceDefinition();
////			PreferenceCategory pc  = new PreferenceCategory();
////			Row row = sheet.getRow(i);
////			for (int j = 0; j < cellNum; j++) {
////				Cell cell = row.getCell(j);
////				if (cell != null) {
////					// 根据列数来赋值
////					switch (j) {
////					case 0:
////						if (cell.getCellType() == cell.CELL_TYPE_STRING)
////							pc.setName(cell.getRichStringCellValue()
////									.toString());
////						else
////							pc.setName(((int) cell.getNumericCellValue()) + "");
////						break;
////					case 1:
////						if (cell.getCellType() == cell.CELL_TYPE_STRING)
////							pc.setCode(cell.getRichStringCellValue()
////									.toString());
////						else
////							pc.setCode(((int) cell.getNumericCellValue())
////									+ "");
////						break;
////					case 2:
////						if (cell.getCellType() == cell.CELL_TYPE_STRING)
////							pd.setName(cell.getRichStringCellValue()
////									.toString());
////						else
////							pd.setName(((int) cell.getNumericCellValue())
////									+ "");
////						break;
////					case 3:
////						if (cell.getCellType() == cell.CELL_TYPE_STRING)
////							pd.setCode(cell.getRichStringCellValue()
////									.toString());
////						else
////							pd.setCode(((int) cell.getNumericCellValue())
////									+ "");
////						break;
////					case 4:
////						if (cell.getCellType() == cell.CELL_TYPE_STRING)
////							pd.setDataType(cell.getRichStringCellValue()
////									.toString());
////						else
////							pd.setDataType(((int) cell.getNumericCellValue())
////									+ "");
////						break;
////					case 5:
////						if (cell.getCellType() == cell.CELL_TYPE_STRING)
////							pd.setEditorType(cell.getRichStringCellValue()
////									.toString());
////						else
////							pd.setEditorType(((int) cell.getNumericCellValue())
////									+ "");
////						break;
////					case 6:
////						if (cell.getCellType() == cell.CELL_TYPE_NUMERIC)
////							pd.setSortIndex((int) cell.getNumericCellValue());
////						break;
////					default:;break;
////					}
////				}
////				
////				if(pcMap.containsKey(pc.getName())){
////					pd.setPreferenceCategory(pcMap.get(pc.getName()));
////					preferenceDefinitionService.createPreferenceDefinition(pd);
////				}else{
////					preferenceCategoryService.createPreferenceCategory(pc);
////					pcMap.put(pc.getName(), pc);
////					pd.setPreferenceCategory(pc);
////					preferenceDefinitionService.createPreferenceDefinition(pd);
////				}
////			}
////		}
////	}
////	
//	//数据字典导入
//	@SuppressWarnings("static-access")
//	static public  void createDictionary(Sheet sheet){
//		// 获得菜单工作表行数
//		int rowNum = sheet.getLastRowNum() + 1;
//		// 获得列数
//		int cellNum = sheet.getRow(0).getLastCellNum();
//		Map<String, Dictionary> dicMap = new HashMap<String, Dictionary>();
//		Map<String, String> dicParentChildMap = new HashMap<String, String>();
//		for (int i = 1; i < rowNum; i++) {
//			Dictionary dic = new Dictionary();
//			DictionaryItem dict  = new DictionaryItem();
//			Row row = sheet.getRow(i);
//			String currentId = getStringCellValue(row.getCell(1)) ;
//			String type = (row.getCell(0).getRichStringCellValue().toString()) ;
//			for (int j = 0; j < cellNum; j++) {
//				Cell cell = row.getCell(j);
//				if (cell != null) {
//						
//						if("字典".equals(type)){
//							// 根据类型来赋值
//							switch (j) {
//							case 2:
//								if (cell.getCellType() == cell.CELL_TYPE_STRING)
//									dic.setName(cell.getRichStringCellValue()
//											.toString());
//								else
//									dic.setName(((int) cell.getNumericCellValue()) + "");
//								break;
//							case 3:
//								if (cell.getCellType() == cell.CELL_TYPE_STRING)
//									dic.setCode(cell.getRichStringCellValue()
//											.toString());
//								else
//									dic.setCode(((int) cell.getNumericCellValue()) + "");
//								break;
//							case 4:
//								if (cell.getCellType() == cell.CELL_TYPE_NUMERIC)
//									dic.setSortIndex((int) cell.getNumericCellValue());
//								break;
//							case 5:
//								dicParentChildMap.put(currentId, getStringCellValue(cell));
//								break;
//							case 6:
//								 if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
//									 Integer isSystemDict = ((int)cell.getNumericCellValue());
//									 dic.setSystem(BooleanUtils.toBooleanObject(isSystemDict));
//								 }
//								break;
//							default:;break;
//							}
//						}else if("字典项".equals(type)){
//							switch (j) {
//							case 2:
//								if (cell.getCellType() == cell.CELL_TYPE_STRING)
//									dict.setName(cell.getRichStringCellValue()
//											.toString());
//								else
//									dict.setName(((int) cell.getNumericCellValue()) + "");
//								break;
//							case 3:
//								if (cell.getCellType() == cell.CELL_TYPE_STRING)
//									dict.setCode(cell.getRichStringCellValue()
//											.toString());
//								else
//									dict.setCode(((int) cell.getNumericCellValue()) + "");
//								break;
//							case 4:
//								if (cell.getCellType() == cell.CELL_TYPE_NUMERIC)
//									dict.setSortIndex((int) cell.getNumericCellValue());
//								break;
//							case 5:
//								dict.setDictionary(dicMap.get(getStringCellValue(cell)));
//								break;
//							default:;break;
//							}
//						}
//						
//						
//				}
//			}
//			if("字典".equals(type)){
//				dictionaryService.createDictionary(dic);
//				dicMap.put(currentId, dic);
//			}else if("字典项".equals(type)){
//				dictionaryService.createDictionaryItem(dict);
//			}
//			
//		}
//
//		for (String currentId : dicParentChildMap.keySet()) {
//			Dictionary dictionary = dicMap.get(currentId);
//			dictionary.setParent(dicMap.get(dicParentChildMap.get(currentId)));
//			dictionaryService.updateDictionary(dictionary);
//		}
//		
//		dicParentChildMap.clear();
//		dicMap.clear();
//	}
//	
//	//获取兼容的Workbook接口（兼容2003和2007）
//	static public   Workbook createCommonWorkbook(InputStream is)
//		throws IOException, InvalidFormatException {
//		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
//		
//		if (!is.markSupported()) {
//			// 还原流信息
//			is = new PushbackInputStream(is);
//		}
//		
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(is)) {
//			return new HSSFWorkbook(is);
//		}
//		
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(is)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(is));
//		}
//		
//		throw new IOException("不能解析的excel版本");
//	} 
//	
//	private static String getStringCellValue(Cell cell) {//(HSSFCell)
//		if (cell == null) {
//			return "";
//		}
//		String strCell = "";
//		switch (cell.getCellType()) {
//		case HSSFCell.CELL_TYPE_STRING:
//			strCell = cell.getStringCellValue();
//			break;
//		case HSSFCell.CELL_TYPE_NUMERIC:
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			strCell = cell.getStringCellValue();
//			break;
//		case HSSFCell.CELL_TYPE_BOOLEAN:
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			strCell = cell.getStringCellValue();
//			break;
//		case HSSFCell.CELL_TYPE_BLANK:
//			strCell = "";
//			break;
//		default:
//			strCell = "";
//			break;
//		}
//		return strCell;
//	}
//}
