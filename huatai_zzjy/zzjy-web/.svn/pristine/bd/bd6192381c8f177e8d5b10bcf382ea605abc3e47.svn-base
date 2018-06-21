package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.bean.FieldInterBean;
import com.huatai.web.model.TargetInter;
import com.huatai.web.model.TargetInterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface TargetInterMapper {
    int countByExample(TargetInterExample example);

    int deleteByExample(TargetInterExample example);

    int deleteByPrimaryKey(Integer record);

    int insert(TargetInter record);

    int insertSelective(TargetInter record);

    List<TargetInter> selectByExample(TargetInterExample example);

    TargetInter selectByPrimaryKey(Integer tiId);

    int updateByExampleSelective(@Param("record") TargetInter record, @Param("example") TargetInterExample example);

    int updateByExample(@Param("record") TargetInter record, @Param("example") TargetInterExample example);

    int updateByPrimaryKeySelective(TargetInter record);

    int updateByPrimaryKey(TargetInter record);

	Pager<TargetInter> findByPager(Pager<TargetInter> pager, TargetInter record);

	List<TargetInter> findTargetInterByInterId(@Param("interId")String interId);

	List<FieldInterBean> findTIByTarChanCode(@Param("targetCode") String targetCode,@Param("channelCode") String channelCode,@Param("interName") String interName);

	List<TargetInter> findByIsTargetInter(@Param("interId")Integer interId);

	int updateInterId(TargetInter record);

	int updateTargetInter(TargetInter record);
}