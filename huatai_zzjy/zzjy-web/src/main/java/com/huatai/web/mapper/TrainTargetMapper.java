package com.huatai.web.mapper;

import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;
import com.huatai.web.model.TrainTarget;
import com.huatai.web.model.TrainTargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@DataSource(name="dataSource2")
@MyBatisRepository
public interface TrainTargetMapper {
    int countByExample(TrainTargetExample example);

    int deleteByExample(TrainTargetExample example);

    int insert(List<TrainTarget> trainTarget);

    int insertSelective(TrainTarget record);

    List<TrainTarget> selectByExample(TrainTargetExample example);

    int updateByExampleSelective(@Param("record") TrainTarget record, @Param("example") TrainTargetExample example);

    int updateByExample(@Param("record") TrainTarget record, @Param("example") TrainTargetExample example);

	List<TrainTarget> findTrain();

	int updateByOrgCodeAndDateCode(TrainTarget tt);
}