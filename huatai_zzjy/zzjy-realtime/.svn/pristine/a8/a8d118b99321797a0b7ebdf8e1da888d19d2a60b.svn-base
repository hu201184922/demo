package com.ehuatai.biz.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;
import com.fairyland.jdp.orm.mybatis.util.annotations.DataSource;

@MyBatisRepository
@DataSource(name="dataSource2",value="dataSource2")
public interface SszbMapper {

	/**
	 * 总公司
	 * @return
	 */
	public List<Map<String, Object>> findPurZongByOra(HashMap paramMap);
	
	/**
	 * 分公司
	 * @return
	 */
	public List<Map<String, Object>> findPurFenByOra(HashMap paramMap);
	
	/**
	 * 中支
	 * @return
	 */
	public List<Map<String, Object>> findPurZhiByOra(HashMap paramMap);
}
