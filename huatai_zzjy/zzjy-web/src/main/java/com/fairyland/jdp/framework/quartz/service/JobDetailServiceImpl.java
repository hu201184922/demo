package com.fairyland.jdp.framework.quartz.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.core.persistence.DynamicSort;
import com.fairyland.jdp.core.persistence.DynamicSpecifications;
import com.fairyland.jdp.core.persistence.SearchFilter;
import com.fairyland.jdp.core.persistence.SortFilter;
import com.fairyland.jdp.framework.quartz.dao.JobDetailDao;
import com.fairyland.jdp.framework.quartz.dao.TriggersDao;
import com.fairyland.jdp.framework.quartz.domain.JobDetail;
import com.fairyland.jdp.framework.quartz.domain.JobDetailPk;
import com.fairyland.jdp.framework.quartz.domain.Triggers;

@Service(value="jobDetailService")
public class JobDetailServiceImpl implements JobDetailService {

	@Autowired
	private JobDetailDao jobDetailDao;
	
	@Autowired
	private TriggersDao triggersDao;

	@Override
	public Page<JobDetail> findAllJobs(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType) {
		
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<JobDetail> spec = DynamicSpecifications.bySearchFilter(filters.values());
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortType);
		return jobDetailDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pageSize,
			String sortType) {
		Map<String, SortFilter> filters = SortFilter.parse(sortType);
		// 此处添加更多排序逻辑
		Sort sort = DynamicSort.bySortFilter(filters.values());
		return new PageRequest(pageNumber - 1, pageSize, sort);
	}


	@Override
	public Triggers getTrigger(JobDetail jobDetail) {
		JobDetailPk jobDetailPk = jobDetail.getPk();
		List<Triggers> triggers = triggersDao.findTriggersList(jobDetailPk.getName(), jobDetailPk.getGroup());
		if(triggers != null && triggers.size() != 0){
			Triggers tempTrigger = triggers.get(0);
			for(int i = 1; i < triggers.size(); i++){
				if(tempTrigger.getNextFireTime() > triggers.get(i).getNextFireTime()){
					tempTrigger = triggers.get(i);
				}
			}
			return tempTrigger;
		}
		return null;
	}

	@Override
	public Long getTriggersNum(JobDetail jobDetail) {
		if(jobDetail != null){
			JobDetailPk jobDetailPk = jobDetail.getPk();
			return triggersDao.getTriggersNum(jobDetailPk.getName(), jobDetailPk.getGroup());
		}
		return 0L;
	}

	@Override
	public List<Triggers> getTriggers(JobDetail jobDetail) {
		if(jobDetail != null){
			JobDetailPk jobDetailPk = jobDetail.getPk();
			return triggersDao.findTriggersList(jobDetailPk.getName(), jobDetailPk.getGroup());
		}
		return null;
	}

	@Override
	public JobDetail findOne(JobDetailPk jobDetailPk) {
		return jobDetailDao.findOne(jobDetailPk);
	}

	@Override
	public JobDetail findOneByIdString(String idString) {
		String[] arr = idString.split(",");
		if(arr.length == 3){
			JobDetailPk jobDetailPk = new JobDetailPk();
			jobDetailPk.setSchedName(arr[0]);
			jobDetailPk.setName(arr[1]);
			jobDetailPk.setGroup(arr[2]);
			return jobDetailDao.findOne(jobDetailPk);
		}
		return null;
	}

	
}
