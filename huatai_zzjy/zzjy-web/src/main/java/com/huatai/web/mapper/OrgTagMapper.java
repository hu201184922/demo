package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.OrgTag;
import com.huatai.web.model.OrgTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@DataSource(name="dataSource2")
@MyBatisRepository
public interface OrgTagMapper {
    int countByExample(OrgTagExample example);

    int deleteByExample(OrgTagExample example);

    int insert(OrgTag record);

    int insertSelective(OrgTag record);

    List<OrgTag> selectByExample(OrgTagExample example);

    int updateByExampleSelective(@Param("record") OrgTag record, @Param("example") OrgTagExample example);

    int updateByExample(@Param("record") OrgTag record, @Param("example") OrgTagExample example);

	List<OrgTag> findAll();

	int updateByPrimaryKeySelective(OrgTag orgTag);

	int updateStauts(OrgTag orgTag);

	List<String> findOrgTag(String orgCode);

	int update(OrgTag orgTag);

	int insertOrgTag(List<OrgTag> ot);

	int updateByOrgCodeAndYear(OrgTag ot);
}