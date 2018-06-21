package com.fairyland.jdp.framework.log.mapper;

import java.util.List;
import java.util.Map;

import com.fairyland.jdp.framework.log.domain.LogBean;
import com.fairyland.jdp.framework.log.domain.RunLogBean;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.mybatis.pagination.annotation.MyBatisRepository;

@MyBatisRepository
public interface LogMapper {

	Pager<LogBean> getLog(Pager<LogBean> pager, Map<String, Object> map);

	Pager<RunLogBean> getRunLogQuery(Pager<RunLogBean> pager,Map<String,Object> map);
	
	List<String> getRunLogDealTab();

}
