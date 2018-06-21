package com.huatai.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.OrgTagMapper;
import com.huatai.web.mapper.OrganizationsMapper;
import com.huatai.web.model.DeptInfo;
import com.huatai.web.model.OrgTag;
import com.huatai.web.model.Organization;
import com.huatai.web.service.OrganizationsService;

/**
 * @author 胡智辉 2017年8月11日
 */
@Service
public class OrganizationsServiceImpl implements OrganizationsService {

	@Autowired
	private OrganizationsMapper organizationMapper;

	@Autowired
	private OrgTagMapper orgTagMapper;

	@Override
	public int insertSelective(Organization record) {
		int result = organizationMapper.insertSelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Organization record, OrgTag orgTag) {
		int result = organizationMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int deleteByPrimaryKey(Organization record) {
		return this.organizationMapper.deleteByPrimaryKey(record.getManageComCode());
	}

	@Override
	public Pager<Organization> findOrganizationPage(Pager<Organization> pager, Organization record) {
		Pager<Organization> page=this.organizationMapper.findOrganizationPage(pager, record);
		// 查询所有分公司
		List<OrgTag> orgtags = orgTagMapper.findAll();
		for (Organization org : page.getPageItems()) {
			boolean falg=false;
			 for (OrgTag orgTag : orgtags) {
				if (org.getCountComCode().equalsIgnoreCase(orgTag.getOrgCode())) {
					if(("1").equals(orgTag.getStatus())){
						falg=true;
						org.setStatus("1");
					}else{
						if(falg){
							org.setStatus("1");
						}
					}
					List<String> years = orgTagMapper.findOrgTag(orgTag.getOrgCode());
					Collections.sort(years);
					org.setYear(years.toString().replace("[", "").replace("]", ""));
				}
			}
		}
		for (Organization org : page.getPageItems()) {
			if ("".equals(org.getStatus()) ||"0".equals(org.getStatus())|| null == org.getStatus()) {
				org.setStatus("0");
				org.setYear("未设置");
			}
		}
		return page;
	}

	@Override
	public List<Organization> findOrganizationList() {
		return this.organizationMapper.findOrganizationList();
	}

	@Override
	public List<Organization> findOrganizationByProvComCode(String provComCode) {
		return this.organizationMapper.findOrganizationByProvComCode(provComCode);
	}

	/**
	 * @功能 查询四级机构
	 * @作者 胡智辉
	 * @返回类型 Pager<Organization>
	 */
	@Override
	public Pager<Organization> findTeamComPage(Pager<Organization> pager, Organization record) {
		Pager<Organization> page=this.organizationMapper.findTeamComPage(pager, record);
		List<OrgTag> orgtags = orgTagMapper.findAll();
		for (Organization org : page.getPageItems()) {
			 boolean falg=false;
			 for (OrgTag orgTag : orgtags) {
				if (org.getCostCenterstdCode().equalsIgnoreCase(orgTag.getOrgCode())) {
					if(("1").equals(orgTag.getStatus())){
						falg=true;
						org.setStatus("1");
					}else{
						if(falg){
							org.setStatus("1");
						}
					}
					List<String> years = orgTagMapper.findOrgTag(orgTag.getOrgCode());
					Collections.sort(years);
					org.setYear(years.toString().replace("[", "").replace("]", ""));
				}
			}
		}
		for (Organization org : page.getPageItems()) {
			if ("".equals(org.getStatus()) || "0".equals(org.getStatus()) || null == org.getStatus()) {
				org.setStatus("0");
				org.setYear("未设置");
			}
		}
		return page;
	}

	@Override
	public int insertOrgTag(Organization record, OrgTag orgTag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(new Date());
		int result = 0;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Calendar.YEAR, Integer.valueOf(orgTag.getYear()));
			orgTag.setValidBeginDate(sdf.format(calendar.getTime()).toString());
			calendar.clear();
			calendar.set(Calendar.YEAR, Integer.valueOf(orgTag.getYear()));
			calendar.roll(Calendar.DAY_OF_YEAR, -1);
			orgTag.setValidEndDate(sdf.format(calendar.getTime()).toString());
			orgTag.setCreateTime(new Date());
			orgTag.setModifyTime(new Date());
			result = orgTagMapper.insertSelective(orgTag);
			/*calendar.set(Calendar.YEAR, Integer.parseInt(orgTag.getYear()));
			orgTag.setValidBeginDate(sdf.format(calendar.getTime()).toString());
			calendar.roll(Calendar.DAY_OF_YEAR, -1);
			orgTag.setValidEndDate(sdf.format(calendar.getTime()).toString());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @功能 查询总公司，分公司，中支
	 * @作者 杜金虎
	 * @返回类型 List<Organization>
	 */
	@Override
	public List<DeptInfo> findOrganization() {
		return this.organizationMapper.findOrganization();
	}

	@Override
	public String findorgCodeByorgName(String orgName) {
		return this.organizationMapper.findorgCodeByorgName(orgName);
	}

	@Override
	public String findOrgNameByOrgCode(String orgCode) {
		return organizationMapper.findOrgNameByOrgCode(orgCode);
	}

	@Override
	public String findOrgNameByOrgCodeLike(String monitorObject) {
		return organizationMapper.findOrgNameByOrgCodeLike(monitorObject);
	}

	@Override
	public List<Map<String, Object>> findOrgShortName() {
		return organizationMapper.findOrgShortName();
	}

}
