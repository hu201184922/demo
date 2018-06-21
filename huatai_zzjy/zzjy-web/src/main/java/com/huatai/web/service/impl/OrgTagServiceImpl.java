package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.OrgTagMapper;
import com.huatai.web.model.OrgTag;
import com.huatai.web.model.OrgTagExample;
import com.huatai.web.service.OrgTagService;

/**
 * @author 胡智辉
 * 2017年8月11日
 */
@Service
public class OrgTagServiceImpl implements OrgTagService {

	@Autowired
	private OrgTagMapper orgTagMapper;

	@Override
	public Pager<OrgTag> findOrgTagPager(Pager<OrgTag> pager, OrgTag record) {
		return null;
	}

	@Override
	public int insertSelective(OrgTag record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(OrgTag record) {
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(OrgTag record) {
		return 0;
	}

	@Override
	public List<OrgTag> findAll() {
		return this.orgTagMapper.findAll();
	}

	@Override
	public int updateStauts(OrgTag orgTag) {
		int result=orgTagMapper.updateStauts(orgTag);
		return result;
	}

	@Override
	public List<OrgTag> findByOrgCode(String countComCode) {
		OrgTagExample exa = new OrgTagExample();
		exa.createCriteria().andOrgCodeEqualTo(countComCode);
		return orgTagMapper.selectByExample(exa);
	}

	@Override
	public int update(OrgTag ot) {
		int result=orgTagMapper.update(ot);
		return result;
	}

	@Override
	public List<OrgTag> findByIsStatusOrgCode(String orgCode) {
		OrgTagExample exa = new OrgTagExample();
		exa.createCriteria().andOrgCodeEqualTo(orgCode).andStatusEqualTo("1");
		return orgTagMapper.selectByExample(exa);
	}

	@Override
	public List<OrgTag> findByOrgCode(String orgCode, String year) {
		OrgTagExample exa = new OrgTagExample();
		exa.createCriteria().andOrgCodeEqualTo(orgCode).andYearEqualTo(year);
		return orgTagMapper.selectByExample(exa);
	}

	@Override
	public List<OrgTag> findOrgTag() {
		OrgTagExample exa = new OrgTagExample();
		return orgTagMapper.selectByExample(exa);
	}

	@Override
	public int insertOrgTag(List<OrgTag> ot) {
		return orgTagMapper.insertOrgTag(ot);
	}

	@Override
	public int updateByOrgCodeAndYear(OrgTag ot) {
		return orgTagMapper.updateByOrgCodeAndYear(ot);
	}
	
}
