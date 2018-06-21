package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.LabelOrg;
import com.huatai.web.model.LabelOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface LabelOrgMapper {
    int countByExample(LabelOrgExample example);

    int deleteByExample(LabelOrgExample example);

    int insert(List<LabelOrg> labelOrg);

    int insertSelective(LabelOrg record);

    List<LabelOrg> selectByExample(LabelOrgExample example);

    int updateByExampleSelective(@Param("record") LabelOrg record, @Param("example") LabelOrgExample example);

    int updateByExample(@Param("record") LabelOrg record, @Param("example") LabelOrgExample example);

	List<LabelOrg> findLabelOrg();

	int updateByOrgCodeAndDateCode(LabelOrg rt);
}