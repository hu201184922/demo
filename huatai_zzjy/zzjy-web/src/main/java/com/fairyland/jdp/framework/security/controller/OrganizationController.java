package com.fairyland.jdp.framework.security.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fairyland.jdp.framework.organizational.city.domain.City;
import com.fairyland.jdp.framework.organizational.city.service.CityService;
import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.framework.security.domain.User;

@Controller
@RequestMapping("/admin/org")
public class OrganizationController {
//	@Autowired
//	private OrganizationService organizationService;
	
	@Autowired
	private CityService cityService;

//	@Autowired
//	private TeamReportService teamReportService;

	@RequestMapping("index")
	public ModelAndView index() {
		List<City> citys=cityService.getCitys(new HashMap<String, Object>());
		ModelAndView view = new ModelAndView();
		view.addObject("citys", citys);
		return view;
	}

//	@RequestMapping("/tree")
//	@ResponseBody
//	public List<Organization> getTree(Long id) {
//		List<Organization> list = organizationService.getOrganizationByPid(id);
//		return list;
//	}

	/*@RequestMapping("/save")
	@ResponseBody
	public Organization saveOrg(Organization org) {
		if (org.getOrgid() == null) {
			organizationService.saveOrg(org);
		} else {
			organizationService.updateOrg(org);
		}
		return org;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Boolean delete(Long orgid) {
		return organizationService.delete(orgid);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Organization updateOrg(Organization org) {
		return org;
	}
	
	@RequestMapping("/getCity")
	@ResponseBody
	public List<Organization> getCity(String orgCode) {
		Long orgId=null;
		List<Organization> list=new ArrayList<Organization>();
		List<Organization> olists=organizationService.getOrganizationByOrgCode(orgCode);
		if(olists.size()>0)
		{
			 orgId=olists.get(0).getOrgid();
			 list = organizationService.getOrganizationByPid(orgId);
			if(list.size()==0){
				list = organizationService.getOrganizationById(orgId);
			}else{
				Organization o  = new Organization();
				o.setOrgName("全部");
				o.setOrgCode("");
				list.add(0, o);
			}
		}
		return list;
	}*/
	/**
	 * 获取当前用户的AGENT_CODE
	 * @return
	 */
	public String getAgentCode(){
		User u = SessionContextUtils.getCurrentUser();
		String codes=null;
		if(u!=null){
			if("00".equals(u.getIsType())){
			}else if("01".equals(u.getIsType())){
				String grade=u.getOrgGrade();
				if("CAO".equals(grade)||"GM".equals(grade)||"AH".equals(grade)){
					codes=u.getOrgCode();
				}else{
					codes=u.getAccount();
				}
			}else if("02".equals(u.getIsType())){
				codes=u.getAccount();
			}
		}
		return codes;
	}
//	@ResponseBody
//	@RequestMapping("getOrsNodes")
//	public String getOrsNodes(){
//		Map<String,Object> map=new HashMap<String,Object>();
//		String codes=getAgentCode();
//		if(codes!=null){
//			map.put("codes", Arrays.asList(codes.split(",")) );
//		}
//		List<OrgNode> list=teamReportService.getOrgNodesByNow(map);
//		String json= JSON.serialize(list);
//		return json;
//	}
}
