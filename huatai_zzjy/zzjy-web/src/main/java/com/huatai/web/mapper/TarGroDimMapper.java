package com.huatai.web.mapper;

import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarGroDimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface TarGroDimMapper {
	int countByExample(TarGroDimExample example);

	int deleteByExample(TarGroDimExample example);

	int deleteByPrimaryKey(Integer tgdId);

	int insert(TarGroDim record);

	int insertSelective(TarGroDim record);

	List<TarGroDim> selectByExample(TarGroDimExample example);

	TarGroDim selectByPrimaryKey(Integer tgdId);

	int updateByExampleSelective(@Param("record") TarGroDim record, @Param("example") TarGroDimExample example);

	int updateByExample(@Param("record") TarGroDim record, @Param("example") TarGroDimExample example);

	int updateByPrimaryKeySelective(TarGroDim record);

	int updateByPrimaryKey(TarGroDim record);

	List<TarGroDim> findAll();

	TarGroDim selectByTarGroDimName(String tarGroDimName);

	Pager<TarGroDim> findByPager(Pager<TarGroDim> pager, TarGroDim record);

	List<TarGroDim> findTarGroDimByTargetCode(@Param("targetCode") String targetCode);

	int deleteByTargetCode(String targetCode);

	List<TarGroDim> isHasGroByTarAndGro(@Param("targetCode") String targetCode,
			@Param("groupbyDate") String groupbyDate);

	List<TarGroDim> getCommonOrgType(@Param("targetCode") String targetCode, @Param("groupType") String groupType);

	List<TarGroDim> getUnionOrgType(@Param("targetCode") String targetCode, @Param("groupType") String groupType);

	int updateByTargetCode(TarGroDim tarGeoDim);

	List<TarGroDim> findTarGroDimByGroAndTar(@Param("dimCode") String dimCode, @Param("targetCode") String targetCode);

}