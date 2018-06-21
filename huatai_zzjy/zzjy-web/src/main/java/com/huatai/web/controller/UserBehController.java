package com.huatai.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.huatai.web.bean.UserBehBean;
import com.huatai.web.model.AccPageInfo;
import com.huatai.web.model.UserBehInfo;
import com.huatai.web.service.OrganizationsService;
import com.huatai.web.service.UserBehInfoService;

@Controller
@RequestMapping("admin/userBehavior")
public class UserBehController {

	final static Logger logger = LoggerFactory.getLogger(UserBehController.class);

	@Autowired
	private UserBehInfoService userBehInfoService;

	@Autowired
	private OrganizationsService orgService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/userBehavior/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public Pager<UserBehBean> getPager(Pager<UserBehBean> pager, UserBehBean record, String orgs, String userId,
			String module, String isNode, String datetime1, String datetime2) throws ParseException {
		// Pager<UserBehBean> Pager = new Pager<UserBehBean>();
		/*
		 * List<UserBehBean> beanList =
		 * findData(pager,record,orgs,userId,module,isNode,datetime1,datetime2);
		 * orgs1 = orgs; userId1= userId; module1= module; isNode1= isNode;
		 * datetime11= datetime1; datetime21= datetime2;
		 * Pager.setPageItems(beanList);
		 */
		Pager<UserBehBean> Pager2 = userBehInfoService.findAccPager(pager, record, orgs, userId, module, isNode,
				datetime1, datetime2);
		return Pager2;
	}

	@ResponseBody
	@RequestMapping(value = "orgList")
	public List<Map<String, Object>> orgList() {
		List<Map<String, Object>> orgList = orgService.findOrgShortName();
		return orgList;
	}

	@ResponseBody
	@RequestMapping(value = "moduleList")
	public List<String> moduleList() {
		List<String> moduleList = userBehInfoService.findfmNameAll();
		return moduleList;
	}

	@ResponseBody
	@RequestMapping(value = "nodeList")
	public List<String> nodeList(String data) {
		List<String> nodeList = userBehInfoService.findNodeByFmName(data);
		return nodeList;
	}

	@RequestMapping(value = "download", method = RequestMethod.GET)
	@ResponseBody
	public void download(String modelName, HttpServletRequest request, HttpServletResponse response, UserBehBean record,
			String orgs, String userId, String module, String isNode, String datetime1, String datetime2)
			throws ParseException, IOException, ServletException {
		FileOutputStream fos = null;
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("测试文件");
		String[] title = { "序号", "工号", "姓名", "模块", "请求节点", "节点名称", "请求路径", "停留时长", "响应时长" };
		Row row = sheet.createRow((short) 0);
		int i = 0;
		for (String s : title) {
			Cell cell = row.createCell(i);
			cell.setCellValue(s);
			i++;
		}
		int j = 1;
		List<UserBehBean> beanList = findData(null, null, orgs, userId, module, isNode, datetime1, datetime2);
		for (UserBehBean info : beanList) {
			// 创建第二行
			Row rowData = sheet.createRow((short) j);
			// 第一列数据
			Cell cell0 = rowData.createCell((short) 0);
			cell0.setCellValue(j);
			sheet.setColumnWidth((short) 0, (short) 10000);// 设置单元格的宽度
			// 第二列数据
			Cell cell1 = rowData.createCell((short) 1);
			cell1.setCellValue(info.getUserId());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第三列数据
			Cell cell2 = rowData.createCell((short) 2);
			cell2.setCellValue(info.getUserName());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第四列数据
			Cell cell3 = rowData.createCell((short) 3);
			cell3.setCellValue(info.getFmName());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第五列数据
			Cell cell4 = rowData.createCell((short) 4);
			cell4.setCellValue(info.getAccessId());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第六列数据
			Cell cell5 = rowData.createCell((short) 5);
			cell5.setCellValue(info.getAccessName());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第七列数据
			Cell cell6 = rowData.createCell((short) 6);
			cell6.setCellValue(info.getAccessUrl());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第八列数据
			Cell cell7 = rowData.createCell((short) 7);
			cell7.setCellValue(info.getStayTime());
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			// 第九列数据
			Cell cell8 = rowData.createCell((short) 8);
			cell8.setCellValue(info.getResTime() + " ms");
			// 设置单元格的宽度
			sheet.setColumnWidth((short) 0, (short) 10000);
			j++;
		}
		OutputStream out = null;// 创建一个输出流对象
		try {
			out = response.getOutputStream();
			String headerStr = "new";// 文件名
			headerStr = new String(headerStr.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xlsx");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			wb.write(out);
			out.flush();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					wb.close();
					if (fos != null)
						fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<UserBehBean> findData(Pager<UserBehBean> pager, UserBehBean record, String orgs, String userId,
			String module, String isNode, String datetime1, String datetime2) {
		List<UserBehBean> beanList = new ArrayList<UserBehBean>();
		List<AccPageInfo> accList = userBehInfoService.findAccAll(pager, record, orgs, userId, module, isNode,
				datetime1, datetime2);
		if (accList.size() > 0) {
			for (AccPageInfo acc : accList) {
				UserBehBean bean = new UserBehBean();
				bean.setAccessId(acc.getAccessid());
				bean.setAccessName(acc.getAccessname());
				bean.setAccessUrl(acc.getAccessurl());
				bean.setFmName(acc.getFmName());
				Long outTime = acc.getOutTime().getTime() - acc.getAccessTime().getTime();
				if ((outTime / 1000 / 60) > 0) {
					bean.setStayTime(outTime / 1000 / 60 + "min" + outTime / 1000 % 60 + "s");
				} else {
					bean.setStayTime(outTime >= 0 ? outTime / 1000 + "s" : (-(outTime / 1000)) + "s");
				}
				bean.setResTime(acc.getResTime());
				String id = acc.getBehaviorid();
				UserBehInfo beh = userBehInfoService.findByBehaviorId(id);
				if (StringUtil.isNotNull(beh)) {
					bean.setUserId(beh.getUserId());
					bean.setUserName(beh.getUserName());
					beanList.add(bean);
				}
			}
		}
		return beanList;
	}
}
