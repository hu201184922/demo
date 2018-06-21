package com.huatai.web.controller;

import java.io.IOException;
import java.io.InputStream;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.CellType;
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

import com.fairyland.jdp.core.web.MediaTypes;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.ImportTemp;
import com.huatai.web.model.OrgTag;
import com.huatai.web.model.Organization;
import com.huatai.web.service.OrgTagService;
import com.huatai.web.service.OrganizationsService;
import com.huatai.web.utils.CephUtil;
import com.huatai.web.utils.Constants;
import com.huatai.web.utils.DateUtil;

/**
 * @author 胡智辉 2017年8月9日
 */
@Controller
@RequestMapping("admin/organization")
public class OrganizationsController {

	final static Logger logger = LoggerFactory.getLogger(OrganizationsController.class);

	@Autowired
	private OrganizationsService organizationService;

	@Autowired
	private OrgTagService orgTagService;
	
	private String bucketName_Template="uploadt";
	
	private static List<String> getYear(){
		List<String> list=new ArrayList<>();
		Integer currentYear = Integer.valueOf(DateUtil.getCurrentYear());
		list.add(currentYear-2+"");
		list.add(currentYear-1+"");
		list.add(currentYear.toString());
		list.add(currentYear+1+"");
		return list;
	}

	// 三级机构
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView main() throws Exception {
		ModelAndView view = new ModelAndView();
		List<Organization> organizations = organizationService.findOrganizationList();
		view.addObject("organizations", organizations);
		view.setViewName("/admin/organization/index");
		view.addObject("years",getYear());
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "getCountlist")
	public Pager<Organization> getCountlist(Pager<Organization> pager, Organization record) {
		Pager<Organization> result = this.organizationService.findOrganizationPage(pager, record);
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int create(@Valid Organization record) {
		int result = this.organizationService.insertSelective(record);
		return result;
	}

	// 关联维护表
	@RequestMapping(value = "insertRelOrg", method = RequestMethod.POST)
	@ResponseBody
	public int relOrg(@Valid Organization record, OrgTag orgTag) {
		int result = 0;
		// 1.先判断数据库里是否有 有则更新，没有就插入
		List<OrgTag> ots = orgTagService.findByOrgCode(orgTag.getOrgCode());
		List<OrgTag> otss = orgTagService.findByIsStatusOrgCode(orgTag.getOrgCode());
		List<String> y = new ArrayList();
		List<String> asList = null;
		if (orgTag.getYear() != null) {
			if (orgTag.getYear().indexOf(",") >= -1) {
				asList = Arrays.asList(orgTag.getYear().split(","));
			}
		}
		for (OrgTag o : ots) {
			y.add(o.getYear());
		} // 为空全部删除
		if (orgTag.getYear() == null) {
			cancel(record);
			// 判断插入的是否有记录 有就只更新
		} else if (y.containsAll(asList)) {
			boolean falg = true;
			for (int i = 0; i < ots.size(); i++) {
				for (String year : asList) {
					// 如果相同就更新
					if (falg) {
						cancel(record);
						falg = false;
					}
					if (ots.get(i).getOrgCode().equals(orgTag.getOrgCode()) && ots.get(i).getYear().equals(year)) {
						OrgTag ot = ots.get(0);
						ot.setStatus("1");

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						calendar.clear();
						ot.setYear(year);
						calendar.set(Calendar.YEAR, Integer.valueOf(year));
						ot.setValidBeginDate(sdf.format(calendar.getTime()).toString());
						calendar.clear();
						calendar.set(Calendar.YEAR, Integer.valueOf(year));
						calendar.roll(Calendar.DAY_OF_YEAR, -1);
						ot.setValidEndDate(sdf.format(calendar.getTime()).toString());
						ot.setModifyTime(new Date());
						result = this.orgTagService.updateStauts(ot);
					}
				}
			}
		} else {
			String[] years = orgTag.getYear().split(",");
			if (ots.size() > 0) {
				for (int i = 0; i < otss.size(); i++) {
					for (String year : years) {
						if (ots.get(i).getOrgCode().equals(orgTag.getOrgCode()) && otss.get(i).getYear().equals(year)) {
							OrgTag otc = ots.get(0);
							otc.setStatus("0");
							otc.setYear(year);
							otc.setModifyTime(new Date());
							result = this.orgTagService.updateStauts(otc);
						}
					}
				}
				for (int j = 0; j < ots.size(); j++) {
					for (String year : years) {
						// 如果相同就更新
						if (!y.contains(year)) {
							List<OrgTag> d = orgTagService.findByOrgCode(orgTag.getOrgCode(), year);
							if (d.size() == 0) {
								orgTag.setTagCode("1");
								orgTag.setStatus("1");
								orgTag.setYear(year);
								result = organizationService.insertOrgTag(record, orgTag);
								continue;
							}
						}
						if (ots.get(j).getOrgCode().equals(orgTag.getOrgCode()) && ots.get(j).getYear().equals(year)) {
							OrgTag ot = ots.get(0);
							ot.setStatus("1");

							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Calendar calendar = Calendar.getInstance();
							calendar.clear();
							ot.setYear(year);
							calendar.set(Calendar.YEAR, Integer.valueOf(year));
							ot.setValidBeginDate(sdf.format(calendar.getTime()).toString());
							calendar.clear();
							calendar.set(Calendar.YEAR, Integer.valueOf(year));
							calendar.roll(Calendar.DAY_OF_YEAR, -1);
							ot.setValidEndDate(sdf.format(calendar.getTime()).toString());
							ot.setModifyTime(new Date());
							result = this.orgTagService.updateStauts(ot);
						} else if (!y.contains(year)) {
							List<OrgTag> d = orgTagService.findByOrgCode(orgTag.getOrgCode(), year);
							if (d.size() == 0) {
								orgTag.setTagCode("1");
								orgTag.setStatus("1");
								orgTag.setYear(year);
								result = organizationService.insertOrgTag(record, orgTag);
								continue;
							}
						} else {
							continue;
						}
					}
				}
			} else {
				for (String year : years) {
					orgTag.setTagCode("1");
					orgTag.setStatus("1");
					orgTag.setYear(year);
					result = organizationService.insertOrgTag(record, orgTag);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "cancel", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public int cancel(@Valid Organization record) {
		String temp = record.getTeamComCode() == null ? record.getCountComCode() : record.getTeamComCode();
		OrgTag ot = orgTagService.findByOrgCode(temp).get(0);
		ot.setStatus("0");
		ot.setModifyTime(new Date());
		int result = this.orgTagService.update(ot);
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@Valid Organization record) {
		int result = organizationService.deleteByPrimaryKey(record);
		return result;
	}

	// 四级机构
	@RequestMapping(value = "team")
	@ResponseBody
	public ModelAndView team(ModelAndView view) {
		List<Organization> organizations = organizationService.findOrganizationList();
		view.addObject("organizations", organizations);
		view.setViewName("/admin/organization/team");
		view.addObject("years",getYear());
		return view;
	}

	// 条件查询加载下拉框
	@RequestMapping(value = "loadData")
	@ResponseBody
	public List<Organization> loadData(ModelAndView view, String provComCode) {
		// 查询分公司下的中支
		List<Organization> organization = organizationService.findOrganizationByProvComCode(provComCode);
		return organization;
	}

	@ResponseBody
	@RequestMapping(value = "getTeamlist")
	public Pager<Organization> getTeamlist(Pager<Organization> pager, Organization record) {
		Pager<Organization> result = organizationService.findTeamComPage(pager, record);
		return result;
	}

	// 关联维护表
	@RequestMapping(value = "insertRelTeam", method = RequestMethod.POST)
	@ResponseBody
	public int relTeam(@Valid Organization record, OrgTag orgTag) {
		int result = 0;
		// 1.先判断数据库里是否有 有则更新，没有就插入
		List<OrgTag> ots = orgTagService.findByOrgCode(orgTag.getOrgCode());
		List<OrgTag> otss = orgTagService.findByIsStatusOrgCode(orgTag.getOrgCode());
		List<String> y = new ArrayList();
		List<String> asList = null;
		if (orgTag.getYear() != null) {
			if (orgTag.getYear().indexOf(",") >= -1) {
				asList = Arrays.asList(orgTag.getYear().split(","));
			}
		}
		for (OrgTag o : ots) {
			y.add(o.getYear());
		} // 为空全部删除
		if (orgTag.getYear() == null) {
			cancel(record);
			// 判断插入的是否有记录 有就只更新
		} else if (y.containsAll(asList)) {
			boolean falg = true;
			for (int i = 0; i < ots.size(); i++) {
				for (String year : asList) {
					// 如果相同就更新
					if (falg) {
						cancel(record);
						falg = false;
					}
					if (ots.get(i).getOrgCode().equals(orgTag.getOrgCode()) && ots.get(i).getYear().equals(year)) {
						OrgTag ot = ots.get(0);
						ot.setStatus("1");

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar calendar = Calendar.getInstance();
						calendar.clear();
						ot.setYear(year);
						calendar.set(Calendar.YEAR, Integer.valueOf(year));
						ot.setValidBeginDate(sdf.format(calendar.getTime()).toString());
						calendar.clear();
						calendar.set(Calendar.YEAR, Integer.valueOf(year));
						calendar.roll(Calendar.DAY_OF_YEAR, -1);
						ot.setValidEndDate(sdf.format(calendar.getTime()).toString());
						ot.setModifyTime(new Date());
						result = this.orgTagService.updateStauts(ot);
					}
				}
			}
		} else {
			String[] years = orgTag.getYear().split(",");
			if (ots.size() > 0) {
				for (int i = 0; i < otss.size(); i++) {
					for (String year : years) {
						if (ots.get(i).getOrgCode().equals(orgTag.getOrgCode()) && otss.get(i).getYear().equals(year)) {
							OrgTag otc = ots.get(0);
							otc.setStatus("0");
							otc.setYear(year);
							otc.setModifyTime(new Date());
							result = this.orgTagService.updateStauts(otc);
						}
					}
				}
				for (int j = 0; j < ots.size(); j++) {
					for (String year : years) {
						// 如果相同就更新
						if (!y.contains(year)) {
							List<OrgTag> d = orgTagService.findByOrgCode(orgTag.getOrgCode(), year);
							if (d.size() == 0) {
								orgTag.setTagCode("1");
								orgTag.setStatus("1");
								orgTag.setYear(year);
								result = organizationService.insertOrgTag(record, orgTag);
								continue;
							}
						}
						if (ots.get(j).getOrgCode().equals(orgTag.getOrgCode()) && ots.get(j).getYear().equals(year)) {
							OrgTag ot = ots.get(0);
							ot.setStatus("1");

							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Calendar calendar = Calendar.getInstance();
							calendar.clear();
							ot.setYear(year);
							calendar.set(Calendar.YEAR, Integer.valueOf(year));
							ot.setValidBeginDate(sdf.format(calendar.getTime()).toString());
							calendar.clear();
							calendar.set(Calendar.YEAR, Integer.valueOf(year));
							calendar.roll(Calendar.DAY_OF_YEAR, -1);
							ot.setValidEndDate(sdf.format(calendar.getTime()).toString());
							ot.setModifyTime(new Date());
							result = this.orgTagService.updateStauts(ot);
						} else if (!y.contains(year)) {
							List<OrgTag> d = orgTagService.findByOrgCode(orgTag.getOrgCode(), year);
							if (d.size() == 0) {
								orgTag.setTagCode("1");
								orgTag.setStatus("1");
								orgTag.setYear(year);
								result = organizationService.insertOrgTag(record, orgTag);
								continue;
							}
						} else {
							continue;
						}
					}
				}
			} else {
				for (String year : years) {
					orgTag.setTagCode("1");
					orgTag.setStatus("1");
					orgTag.setYear(year);
					result = organizationService.insertOrgTag(record, orgTag);
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@Valid ImportTemp record, HttpServletRequest request, 
										HttpServletResponse response) throws ParseException, IOException, ServletException {
		Map<String, Object> resMap = new HashMap<>();
		Part part = request.getPart("impTempFile");
		String name=part.getHeader("content-disposition");
	    String hz=name.substring(name.lastIndexOf("."), name.length()-1); //获取文件的后缀
	    if (".xls".equalsIgnoreCase(hz) || ".xlsx".equalsIgnoreCase(hz)) {
	    	String filenameT = name.substring(name.lastIndexOf("=")+2, name.length()-1);
//	    	part.write(filename); //上传文件到指定目录，不想上传文件就不调用这个
	    	CephUtil.uploadFile(part.getInputStream(),bucketName_Template, filenameT, part.getSize());
	    	resMap.put("result", true);
	    	resMap.put("message", Constants.upMsgSUCCESS);
	    } else {
	    	resMap.put("result", false);
	    	resMap.put("message", Constants.pUpExFile);
	    }
		return resMap;
	}
}
