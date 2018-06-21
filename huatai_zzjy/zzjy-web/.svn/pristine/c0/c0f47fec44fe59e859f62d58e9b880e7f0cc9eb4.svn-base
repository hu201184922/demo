package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.ImportTemp;
import com.huatai.web.model.ImportTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface ImportTempMapper {
    int countByExample(ImportTempExample example);

    int deleteByExample(ImportTempExample example);

    int deleteByPrimaryKey(@Param("itId")Long itId, @Param("record")ImportTemp record);

    int insert(ImportTemp record);

    int insertSelective(ImportTemp record);

    List<ImportTemp> selectByExample(ImportTempExample example);

    ImportTemp selectByPrimaryKey(Long itId);

    int updateByExampleSelective(@Param("record") ImportTemp record, @Param("example") ImportTempExample example);

    int updateByExample(@Param("record") ImportTemp record, @Param("example") ImportTempExample example);

    int updateByPrimaryKeySelective(ImportTemp record);

    int updateByPrimaryKey(ImportTemp record);

	Pager<ImportTemp> findByPager(Pager<ImportTemp> pager, ImportTemp record);

	List<DictItem> selectUploadEX();
}