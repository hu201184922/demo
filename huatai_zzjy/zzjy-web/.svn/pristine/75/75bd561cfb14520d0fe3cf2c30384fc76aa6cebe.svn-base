package com.huatai.web.mapper;

import com.huatai.web.model.Version;
import com.huatai.web.model.VersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionMapper {
    int countByExample(VersionExample example);

    int deleteByExample(VersionExample example);

    int deleteByPrimaryKey(Integer versionId);

    int insert(Version record);

    int insertSelective(Version record);

    List<Version> selectByExample(VersionExample example);

    Version selectByPrimaryKey(Integer versionId);

    int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
    
    List<Version> getVersionList(@Param("type")  String type);
}