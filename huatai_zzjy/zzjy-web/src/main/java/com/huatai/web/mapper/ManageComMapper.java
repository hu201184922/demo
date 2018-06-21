package com.huatai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.bean.OrganizationBean;
import com.huatai.web.model.ManageCom;

@DataSource(name="dataSource2")
@MyBatisRepository
public interface ManageComMapper {

	List<ManageCom> findManageComByOrgCode(@Param("orgCode")String orgCode,@Param("leng")int leng);

	List<OrganizationBean> findOrganizationComByOrgCode(@Param("orgCode")String orgCode,@Param("leng") int leng);

}
