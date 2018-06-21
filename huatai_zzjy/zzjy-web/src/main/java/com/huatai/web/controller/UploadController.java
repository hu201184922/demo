package com.huatai.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;
import com.fairyland.jdp.framework.dictionary.service.DictionaryService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.ImportTemp;
import com.huatai.web.model.LabelOrg;
import com.huatai.web.model.OrgTag;
import com.huatai.web.model.PremTarget;
import com.huatai.web.model.RealtimeTarget;
import com.huatai.web.model.RenewalTarget;
import com.huatai.web.model.RiskTarget;
import com.huatai.web.model.TrainTarget;
import com.huatai.web.model.WealAnnConfig;
import com.huatai.web.service.ImportTempService;
import com.huatai.web.service.OrgTagService;
import com.huatai.web.utils.CephUtil;
import com.huatai.web.utils.Constants;

/**
 * 上传EXCEL，导入oracle数据库
 * 
 * @author yangbo
 */
@Controller
@RequestMapping("admin/upload")
public class UploadController {

	@Autowired
	private ImportTempService importTempService;

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private OrgTagService orgTagService;

	private String bucketName_Template = "uploadt";

	// private String bucketName_Data = "uploadd";

	private static BigDecimal bigDecimal = null;
	// private String savePath_Template;//上传模板EXCEL路径
	//
	// private String savePath_Data;//上传 存在数据的EXCEL路径
	//
	// public String getSavePath_Template() {
	// DictionaryItem dictByCode =
	// dictionaryService.findByDictCodeAndDictItemCode("FILE_PATH", "uploadT");
	// this.savePath_Template = dictByCode.getName();
	// return this.savePath_Template;
	// }
	//
	// public String getSavePath_Data() {
	// DictionaryItem dictByCode =
	// dictionaryService.findByDictCodeAndDictItemCode("FILE_PATH", "uploadD");
	// this.savePath_Data = dictByCode.getName();
	// return this.savePath_Data;
	// }

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		List<DictionaryItem> list = new ArrayList<>();
		List<String> dept = Arrays.asList(SessionContextUtils.getCurrentUser().getDeptCode().toString().split(","));
		for (String str : dept) {
			DictionaryItem dictItem = dictionaryService.findByDictCodeAndDictItemCode("DETP_DICT", str);
			list.add(dictItem);
		}
		model.addObject("dictItem", list);
		model.setViewName("/admin/upload/index");
		return model;
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ModelAndView index1() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/upload");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<ImportTemp> getPager(Pager<ImportTemp> pager, ImportTemp record) {
		String dstr = SessionContextUtils.getCurrentUser().getDeptCode().toString();
		if (dstr.indexOf(",") > -1) {
			record.setDeptCode(dstr);
		} else {
			record.setDeptCode(dstr);
		}
		return importTempService.findByPager(pager, record);
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public int delete(Integer id) {
		ImportTemp importTemp = new ImportTemp();
		importTemp.setOpType("D");
		importTemp.setModifyTime(new Date());
		importTemp.setModifierId(SessionContextUtils.getLoginName().toString());
		return importTempService.deleteByItId(id, importTemp);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@Valid ImportTemp record) throws Exception {
		record.setModifierId(SessionContextUtils.getLoginName().toString());
		record.setOpType("U");
		return this.importTempService.updateByPrimaryKey(record);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@Valid ImportTemp record, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException, ServletException {
		Map<String, Object> resMap = new HashMap<>();
		Part part = request.getPart("impTempFile");
		String name = part.getHeader("content-disposition");
		// String
		// impTempPath=request.getServletContext().getRealPath("/upload");
		String hz = name.substring(name.lastIndexOf("."), name.length() - 1); // 获取文件的后缀
		if (".xls".equalsIgnoreCase(hz) || ".xlsx".equalsIgnoreCase(hz)) {
			String filenameT = name.substring(name.lastIndexOf("=") + 2, name.length() - 1);
			record.setImpTempName(filenameT);
			// String filename =
			// getSavePath_Template()+"/"+name.substring(name.lastIndexOf("=")+2,
			// name.length()-1);
			// String filePathname = getSavePath_Template()+"/" + filenameT;
			record.setImpTempPath(bucketName_Template);
			// part.write(filename); //上传文件到指定目录，不想上传文件就不调用这个
			CephUtil.uploadFile(part.getInputStream(), bucketName_Template, filenameT, part.getSize());
			importTempService.insert(record);
			resMap.put("result", true);
			resMap.put("message", Constants.upMsgSUCCESS);
		} else {
			resMap.put("result", false);
			resMap.put("message", Constants.pUpExFile);
		}
		return resMap;
	}

	@RequestMapping(value = "downloadModel")
	@ResponseBody
	public void downloadModel(String modelName, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		InputStream inStream = CephUtil.getInputFromCeph(bucketName_Template, modelName);
		// 读到流中
		// InputStream inStream = new FileInputStream(getSavePath_Template() +
		// "/" + modelName);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + URLEncoder.encode(modelName, "utf-8") + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BigDecimal getBigDecimal(String str) {
		bigDecimal = new BigDecimal(str);
		return bigDecimal;
	}

	private static BigDecimal getBigDecimal2(String str) {
		bigDecimal = new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP);
		return bigDecimal;
	}

	@RequestMapping(value = "insertFileData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertFileData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("result", true);
		int cate = Integer.parseInt(request.getParameter("uploadCate"));
		Part part = request.getPart("uploadTemFile");
		String name = part.getHeader("content-disposition");
		String hz = name.substring(name.lastIndexOf("."), name.length() - 1); // 获取文件的后缀
		if (".xls".equalsIgnoreCase(hz) || ".xlsx".equalsIgnoreCase(hz)) {
			String fileName = name.substring(name.lastIndexOf("=") + 2, name.length() - 1);
			// String filename =
			// getSavePath_Data()+"/"+name.substring(name.lastIndexOf("=")+2,
			// name.length()-1);
			// part.write(filename); //上传文件到指定目录，不想上传文件就不调用这个
			// CephUtil.uploadFile(part.getInputStream(), bucketName_Data,
			// fileName, part.getSize());
			// File uploadFile = new File(filename);
			// uploadFile.mkdirs();
			boolean isE2003 = false;
			if (fileName.endsWith("xls")) {
				isE2003 = true;
			}
			InputStream is = part.getInputStream();
			// InputStream is = new FileInputStream(filename);
			// InputStream is = CephUtil.getInputFromCeph(bucketName_Data,
			// fileName);
			Workbook book = null;
			try {
				if (isE2003) {
					book = new HSSFWorkbook(is);
				} else {
					book = new XSSFWorkbook(is);
				}
			} catch (NotOfficeXmlFileException e) {
				resMap.put("message", "不是有效EXCEL模板文件");
				resMap.put("result", false);
				e.printStackTrace();
				return resMap;
			}
			Sheet sheet = book.getSheetAt(0);
			int rols = sheet.getPhysicalNumberOfRows(); // 记录数
			String orgCode = "";
			String orgName = "";
			String dateCode = "";
			
			String year = "";
			String status = "";
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<OrgTag> org = null;
			List<OrgTag> orgTag=null;
			switch (cate) {
			case 1:
				BigDecimal stadPrem;
				BigDecimal valuePrem;
				List<RealtimeTarget> rts = new ArrayList<>(150);
				List<RealtimeTarget> result = importTempService.findGXBOnTime();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						orgName = row.getCell(1).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						dateCode = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						stadPrem = getBigDecimal2(row.getCell(3).getStringCellValue());
					} else {
						resMap.put("message", Constants.noPremFeeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(CellType.STRING);
						valuePrem = getBigDecimal2(row.getCell(4).getStringCellValue());
					} else {
						resMap.put("message", Constants.noPremValueMsg);
						resMap.put("result", false);
						break;
					}
					if (rts.contains(orgCode) && rts.contains(orgName)) {

					}
					RealtimeTarget rt = new RealtimeTarget();
					rt.setOrgCode(orgCode);
					rt.setOrgName(orgName);
					rt.setDateCode(dateCode);
					rt.setStadPrem(stadPrem);
					rt.setValuePrem(valuePrem);
					rts.add(rt);
					if (result.size() > 0) {
						for (RealtimeTarget r : result) {
							if (r.getOrgCode().equals(rt.getOrgCode()) && r.getDateCode().equals(rt.getDateCode())) {
								rts.remove(rts.size() - 1);
								importTempService.updateGXBOnTime(rt);
								break;
							}
							if (rts.size() % 150 == 0) {
								importTempService.insertGXBOnTime(rts);
								rts.clear();
							}
						}
					} else {
						if (rts.size() % 150 == 0) {
							importTempService.insertGXBOnTime(rts);
							rts.clear();
						}
					}
				}
				if ((boolean) resMap.get("result")) {
					if (!rts.isEmpty()) {
						importTempService.insertGXBOnTime(rts);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 已更新!");
						resMap.put("result", true);
					}
				}
				break;
			case 2:
				BigDecimal stadPremA;
				BigDecimal recePrem;
				BigDecimal valuePremA;
				BigDecimal recePreNum;
				BigDecimal preGoldNum;
				BigDecimal effNum;
				BigDecimal receEffNum;
				BigDecimal groThNum;
				String gradetypecode;
				BigDecimal morningAttendance;
				BigDecimal addManpow;
				// int count = 2;
				List<RiskTarget> rgs = new ArrayList<>(60);
				List<RiskTarget> riskTarget = importTempService.findRisk();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						orgName = row.getCell(1).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						dateCode = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						stadPremA = getBigDecimal2(row.getCell(3).getStringCellValue());
					} else {
						resMap.put("message", Constants.noAgentPremFeeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(CellType.STRING);
						recePrem = getBigDecimal2(row.getCell(4).getStringCellValue());
					} else {
						resMap.put("message", Constants.noAgentSLFeeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(5) != null) {
						row.getCell(5).setCellType(CellType.STRING);
						valuePremA = getBigDecimal2(row.getCell(5).getStringCellValue());
					} else {
						resMap.put("message", Constants.noAgentPremValueMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(6) != null) {
						row.getCell(6).setCellType(CellType.STRING);
						recePreNum = getBigDecimal(row.getCell(6).getStringCellValue());
					} else {
						resMap.put("message", Constants.noCBPremBeeFeeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(7) != null) {
						row.getCell(7).setCellType(CellType.STRING);
						preGoldNum = getBigDecimal(row.getCell(7).getStringCellValue());
					} else {
						resMap.put("message", Constants.noPremBeeFeeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(8) != null) {
						row.getCell(8).setCellType(CellType.STRING);
						effNum = getBigDecimal(row.getCell(8).getStringCellValue());
					} else {
						resMap.put("message", Constants.noCBPremYXMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(9) != null) {
						row.getCell(9).setCellType(CellType.STRING);
						receEffNum = getBigDecimal(row.getCell(9).getStringCellValue());
					} else {
						resMap.put("message", Constants.noSLPremYXMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(10) != null) {
						row.getCell(10).setCellType(CellType.STRING);
						groThNum = getBigDecimal(row.getCell(10).getStringCellValue());
					} else {
						resMap.put("message", Constants.noJZNumMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(11) != null) {
						row.getCell(11).setCellType(CellType.STRING);
						gradetypecode = row.getCell(11).getStringCellValue();
					} else {
						resMap.put("message", Constants.noFZLXMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(12) != null) {
						row.getCell(12).setCellType(CellType.STRING);
						morningAttendance = getBigDecimal(row.getCell(12).getStringCellValue());
					} else {
						resMap.put("message", Constants.noCJNumMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(13) != null) {
						row.getCell(13).setCellType(CellType.STRING);
						addManpow = getBigDecimal(row.getCell(13).getStringCellValue());
					} else {
						resMap.put("message", Constants.noXZNumMsg);
						resMap.put("result", false);
						break;
					}
					RiskTarget rt = new RiskTarget();
					rt.setOrgCode(orgCode);
					rt.setOrgName(orgName);
					rt.setDateCode(dateCode);
					rt.setStadPrem(stadPremA);
					rt.setReceStadPrem(recePrem);
					rt.setValuePrem(valuePremA);
					rt.setRecePreGoldBeeNum(recePreNum);
					rt.setReceEffNum(receEffNum);
					rt.setPreGoldBeeNum(preGoldNum);
					rt.setEffNum(effNum);
					rt.setNetGrowthNum(groThNum);
					rt.setGradetypecode(gradetypecode);
					rt.setMorningAttendance(morningAttendance);
					rt.setAddManpow(addManpow);
					rgs.add(rt);
					if (riskTarget.size() > 0) {
						for (RiskTarget r : riskTarget) {
							if (r.getOrgCode().equals(rt.getOrgCode()) && r.getDateCode().equals(rt.getDateCode())) {
								rgs.remove(rgs.size() - 1);
								importTempService.updateRiskTarget(rt);
								break;
							}
							if (rgs.size() % 60 == 0) {
								importTempService.insertRiskTarget(rgs);
								rgs.clear();
							}
						}
					} else {
						if (rgs.size() % 60 == 0) {
							importTempService.insertRiskTarget(rgs);
							rgs.clear();
						}
					}
				}
				if ((boolean) resMap.get("result")) {
					if (!rgs.isEmpty()) {
						importTempService.insertRiskTarget(rgs);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 已更新!");
						resMap.put("result", true);
					}
				}
				break;
			// 已废弃
			case 3:
				BigDecimal stadPremB;
				BigDecimal valuePremB;
				BigDecimal danPrem;
				List<RenewalTarget> rls = new ArrayList<>();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						orgName = row.getCell(1).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						dateCode = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.NUMERIC);
						stadPremB = getBigDecimal(row.getCell(3).getStringCellValue());
					} else {
						resMap.put("message", Constants.noXQCBMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(CellType.NUMERIC);
						valuePremB = getBigDecimal(row.getCell(4).getStringCellValue());
					} else {
						resMap.put("message", Constants.noXQSLMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(5) != null) {
						row.getCell(5).setCellType(CellType.NUMERIC);
						danPrem = getBigDecimal(row.getCell(5).getStringCellValue());
					} else {
						resMap.put("message", Constants.noXQValueMsg);
						resMap.put("result", false);
						break;
					}
					RenewalTarget rt = new RenewalTarget();
					rt.setOrgCode(orgCode);
					rt.setOrgName(orgName);
					rt.setDateCode(dateCode);
					rt.setStadPrem(stadPremB);
					rt.setReceStadPrem(valuePremB);
					rt.setValuePrem(danPrem);
					rls.add(rt);
				}
				if ((boolean) resMap.get("result")) {
					for (RenewalTarget renewalTarget : rls) {
						importTempService.insertRenewal(renewalTarget);
					}
					resMap.put("message", Constants.exMsgSUCCESS);
					resMap.put("result", true);
				}
				break;
			case 4:
				BigDecimal stadPremC;
				BigDecimal hireNewRate;
				BigDecimal hireMembNewRate;
				BigDecimal hireNewNum;
				BigDecimal membNewNum;
				BigDecimal hireNewStadPrem;
				BigDecimal hireMembNewNum;
				BigDecimal hireMembNewmanNum;
				List<TrainTarget> tts = new ArrayList<>(90);
				List<TrainTarget> trainTarget = importTempService.findTrain();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						orgName = row.getCell(1).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						dateCode = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						stadPremC = getBigDecimal2(row.getCell(3).getStringCellValue());
					} else {
						resMap.put("message", Constants.noSGPYMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(CellType.STRING);
						hireNewRate = getBigDecimal2(row.getCell(4).getStringCellValue());
					} else {
						resMap.put("message", Constants.noSGPCYMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(5) != null) {
						row.getCell(5).setCellType(CellType.STRING);
						hireMembNewRate = getBigDecimal(row.getCell(5).getStringCellValue());
					} else {
						resMap.put("message", Constants.noDYSGXRSYRHRSMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(6) != null) {
						row.getCell(6).setCellType(CellType.STRING);
						hireNewNum = getBigDecimal2(row.getCell(6).getStringCellValue());
					} else {
						resMap.put("message", Constants.noDYSGXRDYCBBBMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(7) != null) {
						row.getCell(7).setCellType(CellType.STRING);
						membNewNum = getBigDecimal(row.getCell(7).getStringCellValue());
					} else {
						resMap.put("message", Constants.noCYXZRSMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(8) != null) {
						row.getCell(8).setCellType(CellType.STRING);
						hireNewStadPrem = getBigDecimal(row.getCell(8).getStringCellValue());
					} else {
						resMap.put("message", Constants.noYPYXRSMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(9) != null) {
						row.getCell(9).setCellType(CellType.STRING);
						hireMembNewNum = getBigDecimal(row.getCell(9).getStringCellValue());
					} else {
						resMap.put("message", Constants.noSJPYXRSMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(10) != null) {
						row.getCell(10).setCellType(CellType.STRING);
						hireMembNewmanNum = getBigDecimal2(row.getCell(10).getStringCellValue());
					} else {
						resMap.put("message", Constants.noSGXRSYRHLMsg);
						resMap.put("result", false);
						break;
					}
					TrainTarget rt = new TrainTarget();
					rt.setOrgCode(orgCode);
					rt.setOrgName(orgName);
					rt.setDateCode(dateCode);
					rt.setGuardTranReate(stadPremC);
					rt.setHireNewRate(hireNewRate);
					rt.setHireMembNewRate(hireMembNewRate);
					rt.setHireNewNum(hireNewNum);
					rt.setMembNewNum(membNewNum);
					rt.setHireNewStadPrem(hireNewStadPrem);
					rt.setHireMembNewNum(hireMembNewNum);
					rt.setHireMembNewmanNum(hireMembNewmanNum);
					tts.add(rt);
					if (trainTarget.size() > 0) {
						for (TrainTarget r : trainTarget) {
							if (r.getOrgCode().equals(rt.getOrgCode()) && r.getDateCode().equals(rt.getDateCode())) {
								tts.remove(tts.size() - 1);
								importTempService.updateTrain(rt);
								break;
							}
							if (tts.size() % 90 == 0) {
								importTempService.insertTrain(tts);
								tts.clear();
							}
						}
					} else {
						if (tts.size() % 90 == 0) {
							importTempService.insertTrain(tts);
							tts.clear();
						}
					}
				}
				if ((boolean) resMap.get("result")) {
					if (!tts.isEmpty()) {
						importTempService.insertTrain(tts);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 已更新!");
						resMap.put("result", true);
					}
				}
				break;
			case 5:
				BigDecimal stadPremD;
				BigDecimal valuePremD;
				List<PremTarget> pts = new ArrayList<>(150);
				List<PremTarget> premTarget = importTempService.findPrem();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						orgName = row.getCell(1).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						dateCode = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						stadPremD = getBigDecimal2(row.getCell(3).getStringCellValue());
					} else {
						resMap.put("message", Constants.noXQSSBFMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(CellType.STRING);
						valuePremD = getBigDecimal2(row.getCell(4).getStringCellValue());
					} else {
						resMap.put("message", Constants.noCBBZBFMsg);
						resMap.put("result", false);
						break;
					}
					PremTarget pt = new PremTarget();
					pt.setOrgCode(orgCode);
					pt.setOrgName(orgName);
					pt.setDateCode(dateCode);
					pt.setRecePrem(stadPremD);
					pt.setStadPrem(valuePremD);
					pts.add(pt);
					if (premTarget.size() > 0) {
						for (PremTarget r : premTarget) {
							if (r.getOrgCode().equals(pt.getOrgCode()) && r.getDateCode().equals(pt.getDateCode())) {
								pts.remove(pts.size() - 1);
								importTempService.updatePrem(pt);
								break;
							}
							if (pts.size() % 150 == 0) {
								importTempService.insertPrem(pts);
								pts.clear();
							}
						}
					} else {
						if (pts.size() % 150 == 0) {
							importTempService.insertPrem(pts);
							pts.clear();
						}
					}
				}
				if ((boolean) resMap.get("result")) {
					if (!pts.isEmpty()) {
						importTempService.insertPrem(pts);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 已更新!");
						resMap.put("result", true);
					}
				}
				break;
			case 6:
				String lastGrade;
				String grade;
				BigDecimal yjbb;
				BigDecimal orgReachRate;
				BigDecimal monAveConversionManpower;
				BigDecimal k2;
				BigDecimal monAve;
				List<LabelOrg> los = new ArrayList<>(100);
				List<LabelOrg> labelOrg = importTempService.findLabelOrg();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						orgName = row.getCell(1).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						dateCode = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						lastGrade = row.getCell(3).getStringCellValue();
					} else {
						resMap.put("message", Constants.noQNGXGradeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(CellType.STRING);
						grade = row.getCell(4).getStringCellValue();
					} else {
						resMap.put("message", Constants.noJNGXGradeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(5) != null) {
						row.getCell(5).setCellType(CellType.STRING);
						yjbb = getBigDecimal2(row.getCell(5).getStringCellValue());
					} else {
						resMap.put("message", Constants.noYJBBZZLMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(6) != null) {
						row.getCell(6).setCellType(CellType.STRING);
						orgReachRate = getBigDecimal2(row.getCell(6).getStringCellValue());
					} else {
						resMap.put("message", Constants.noJGDBLMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(7) != null) {
						row.getCell(7).setCellType(CellType.STRING);
						k2 = getBigDecimal2(row.getCell(7).getStringCellValue());
					} else {
						resMap.put("message", Constants.noK2Msg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(8) != null) {
						row.getCell(8).setCellType(CellType.STRING);
						monAveConversionManpower = getBigDecimal(row.getCell(8).getStringCellValue());
					} else {
						resMap.put("message", Constants.noYJZSYXRLMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(9) != null) {
						row.getCell(9).setCellType(CellType.STRING);
						monAve = getBigDecimal(row.getCell(9).getStringCellValue());
					} else {
						resMap.put("message", Constants.noYJBBMsg);
						resMap.put("result", false);
						break;
					}
					LabelOrg rt = new LabelOrg();
					rt.setOrgCode(orgCode);
					rt.setOrgName(orgName);
					rt.setDateCode(dateCode);
					rt.setLastGrade(lastGrade);
					rt.setGrade(grade);
					rt.setMonAveGrowthRate(yjbb);
					rt.setOrgReachRate(orgReachRate);
					rt.setK2(k2);
					rt.setMonAveConversionManpower(monAveConversionManpower);
					rt.setMonAve(monAve);
					los.add(rt);
					if (labelOrg.size() > 0) {
						for (LabelOrg r : labelOrg) {
							if (r.getOrgCode().equals(rt.getOrgCode()) && r.getDateCode().equals(rt.getDateCode())) {
								los.remove(los.size() - 1);
								importTempService.updateLabelOrg(rt);
								break;
							}
							if (los.size() % 100 == 0) {
								importTempService.insertLabelOrg(los);
								los.clear();
							}
						}
					} else {
						if (los.size() % 100 == 0) {
							importTempService.insertLabelOrg(los);
							los.clear();
						}
					}

				}
				if ((boolean) resMap.get("result")) {
					if (!los.isEmpty()) {
						importTempService.insertLabelOrg(los);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 已更新!");
						resMap.put("result", true);
					}
				}
				break;
			case 7:
				String groupOrderNumber = null;
				Date startDate = null;
				Date endDate = null;
				String flag = null;
				List<WealAnnConfig> wcs = new ArrayList<>(100);
				List<WealAnnConfig> wealAnnConfig = importTempService.findWealAnnConfig();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						groupOrderNumber = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(CellType.STRING);
						String stringCellValue = row.getCell(1).getStringCellValue();
						startDate = dateUtil(stringCellValue);
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						String stringCellValue = row.getCell(2).getStringCellValue();
						endDate = dateUtil(stringCellValue);
					} else {
						resMap.put("message", Constants.noDateCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						flag = row.getCell(3).getStringCellValue();
					} else {
						resMap.put("message", Constants.noQNGXGradeMsg);
						resMap.put("result", false);
						break;
					}
					WealAnnConfig wc = new WealAnnConfig();
					wc.setGroupOrderNumber(groupOrderNumber);
					wc.setStartDate(startDate);
					wc.setEndDate(endDate);
					wc.setFlag(flag);
					wcs.add(wc);
					if (wealAnnConfig.size() > 0) {
						for (WealAnnConfig w : wealAnnConfig) {
							if (w.getGroupOrderNumber().equals(wc.getGroupOrderNumber())) {
								wcs.remove(wcs.size() - 1);
								importTempService.updateWealAnnConfig(wc);
								break;
							}
							if (wcs.size() % 100 == 0) {
								importTempService.insertWealAnnConfig(wcs);
								wcs.clear();
							}
						}
					} else {
						if (wcs.size() % 100 == 0) {
							importTempService.insertWealAnnConfig(wcs);
							wcs.clear();
						}
					}

				}
				if ((boolean) resMap.get("result")) {
					if (!wcs.isEmpty()) {
						importTempService.insertWealAnnConfig(wcs);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 已更新!");
						resMap.put("result", true);
					}
				}
				break;
			case 8:
				org = new ArrayList<>(150);
				orgTag = this.orgTagService.findOrgTag();
				OrgTag ot = null;
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						year = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						status = row.getCell(3).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					ot = new OrgTag();
					ot.setOrgCode(orgCode);
					ot.setCreateTime(date);
					ot.setModifyTime(date);
					ot.setTagCode("1");
					if (year != null) {
						if(!(status==null||status=="")){
							ot.setStatus(status);
						}else{
							ot.setStatus("1");
						}
					} else {
						ot.setStatus("0");
					}
					ot.setYear(year);
					calendar.clear();
					calendar.set(Calendar.YEAR, Integer.valueOf(year));
					ot.setValidBeginDate(sdf.format(calendar.getTime()).toString());
					calendar.clear();
					calendar.set(Calendar.YEAR, Integer.valueOf(year));
					calendar.roll(Calendar.DAY_OF_YEAR, -1);
					ot.setValidEndDate(sdf.format(calendar.getTime()).toString());
					org.add(ot);
					if (orgTag.size() > 0) {
						for (OrgTag r : orgTag) {
							if (r.getOrgCode().equals(ot.getOrgCode()) && r.getYear().equals(ot.getYear())) {
								org.remove(org.size() - 1);
								orgTagService.updateByOrgCodeAndYear(ot);
								break;
							}
							if (org.size() % 150 == 0) {
								orgTagService.insertOrgTag(org);
								org.clear();
							}
						}
					} else {
						if (org.size() % 150 == 0) {
							orgTagService.insertOrgTag(org);
							org.clear();
						}
					}
				}
				if ((boolean) resMap.get("result")) {
					if (!org.isEmpty()) {
						orgTagService.insertOrgTag(org);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 更新成功!");
						resMap.put("result", true);
					}
				}
				break;

			case 9:
				org = new ArrayList<>(150);
				orgTag = this.orgTagService.findOrgTag();
				for (int i = 2; i < rols; i++) {
					Row row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(CellType.STRING);
						orgCode = row.getCell(0).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptCodeMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(CellType.STRING);
						year = row.getCell(2).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(CellType.STRING);
						status = row.getCell(3).getStringCellValue();
					} else {
						resMap.put("message", Constants.noDeptNameMsg);
						resMap.put("result", false);
						break;
					}
					ot = new OrgTag();
					ot.setOrgCode(orgCode);
					ot.setCreateTime(date);
					ot.setModifyTime(date);
					ot.setTagCode("2");
					if (year != null) {
						if(!(status==null||status=="")){
							ot.setStatus(status);
						}else{
							ot.setStatus("1");
						}
					} else {
						ot.setStatus("0");
					}
					ot.setYear(year);
					calendar.clear();
					calendar.set(Calendar.YEAR, Integer.valueOf(year));
					ot.setValidBeginDate(sdf.format(calendar.getTime()).toString());
					calendar.clear();
					calendar.set(Calendar.YEAR, Integer.valueOf(year));
					calendar.roll(Calendar.DAY_OF_YEAR, -1);
					ot.setValidEndDate(sdf.format(calendar.getTime()).toString());
					org.add(ot);
					if (orgTag.size() > 0) {
						for (OrgTag r : orgTag) {
							if (r.getOrgCode().equals(ot.getOrgCode()) && r.getYear().equals(ot.getYear())) {
								org.remove(org.size() - 1);
								orgTagService.updateByOrgCodeAndYear(ot);
								break;
							}
							if (org.size() % 150 == 0) {
								orgTagService.insertOrgTag(org);
								org.clear();
							}
						}
					} else {
						if (org.size() % 150 == 0) {
							orgTagService.insertOrgTag(org);
							org.clear();
						}
					}
				}
				if ((boolean) resMap.get("result")) {
					if (!org.isEmpty()) {
						orgTagService.insertOrgTag(org);
						resMap.put("message", Constants.exMsgSUCCESS);
						resMap.put("result", true);
					} else {
						resMap.put("message", " 更新成功!");
						resMap.put("result", true);
					}
				}
				break;
			default:
				resMap.put("message", Constants.noUpCode);
				resMap.put("result", false);
				break;
			}
		} else {
			resMap.put("result", false);
			resMap.put("message", Constants.pUpExFile);
		}
		return resMap;
	}

	@SuppressWarnings("all")
	private Date dateUtil(String str) {
		Date date = null;
		if (str.length() > 6) {
			try {
				date = DateUtils.parseDate(str, new String[] { "yyyyMMdd" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (str.length() < 6 && str.length() > 4) {
			try {
				date = DateUtils.parseDate(str, new String[] { "yyyyMM" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				date = DateUtils.parseDate(str, new String[] { "yyyy" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
}
