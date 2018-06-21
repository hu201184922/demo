package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.OrgTag;

public interface OrgTagService {

	Pager<OrgTag> findOrgTagPager(Pager<OrgTag> pager, OrgTag record);

	int insertSelective(OrgTag record);

	int updateByPrimaryKeySelective(OrgTag record);

	int deleteByPrimaryKey(OrgTag record);

	List<OrgTag> findAll();

	int updateStauts(OrgTag orgTag);

	List<OrgTag> findByOrgCode(String countComCode);

	int update(OrgTag ot);

	List<OrgTag> findByIsStatusOrgCode(String orgCode);

	List<OrgTag> findByOrgCode(String orgCode, String year);

	List<OrgTag> findOrgTag();

	int insertOrgTag(List<OrgTag> rts);

	int updateByOrgCodeAndYear(OrgTag ot);

}
