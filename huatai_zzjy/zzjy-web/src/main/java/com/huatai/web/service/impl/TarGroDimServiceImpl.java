package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.dao.UserDao;
import com.fairyland.jdp.framework.security.domain.User;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.GroDimDetailMapper;
import com.huatai.web.mapper.TarGroDimMapper;
import com.huatai.web.mapper.TargetMapper;
import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.TarGroDim;
import com.huatai.web.model.TarGroDimExample;
import com.huatai.web.model.Target;
import com.huatai.web.service.TarGroDimService;

/**
 * @description:
 * @author ：程乐飞
 * @datetime : 2017年7月24日 下午1:57:07
 */
@Service
public class TarGroDimServiceImpl implements TarGroDimService {
	@Autowired
	private TarGroDimMapper tarGroDimMapper;
	@Autowired
	private GroDimDetailMapper groDimDetailMapper;
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private UserDao userDao;

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午2:05:08
	 */
	@Override
	public int countByExample(TarGroDimExample example) {
		return this.tarGroDimMapper.countByExample(example);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午2:10:08
	 */
	@Override
	public int deleteByExample(TarGroDimExample example) {
		return this.tarGroDimMapper.deleteByExample(example);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午2:25:08
	 */
	@Override
	public void deleteByPrimaryKey(Integer tgdId) {
		this.tarGroDimMapper.deleteByPrimaryKey(tgdId);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午2:35:08
	 */
	@Override
	public void insert(TarGroDim record) {
		this.tarGroDimMapper.insert(record);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午2:45:08
	 */
	@Override
	public int insertSelective(TarGroDim record) {
		return this.tarGroDimMapper.insertSelective(record);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午2:55:08
	 */
	@Override
	public List<TarGroDim> selectByExample(TarGroDimExample example) {
		return this.tarGroDimMapper.selectByExample(example);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:10:08
	 */
	@Override
	public TarGroDim selectByPrimaryKey(Integer tgdId) {
		return this.tarGroDimMapper.selectByPrimaryKey(tgdId);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:15:08
	 */
	@Override
	public int updateByExampleSelective(TarGroDim record, TarGroDimExample example) {
		return this.tarGroDimMapper.updateByExampleSelective(record, example);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:25:08
	 */
	@Override
	public int updateByExample(TarGroDim record, TarGroDimExample example) {
		return this.tarGroDimMapper.updateByExample(record, example);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:30:08
	 */
	@Override
	public int updateByPrimaryKeySelective(TarGroDim record) {
		return this.tarGroDimMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:45:08
	 */
	@Override
	public void updateByPrimaryKey(TarGroDim record) {
		this.tarGroDimMapper.updateByPrimaryKey(record);
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:45:08
	 */
	@Override
	public Pager<TarGroDim> findByPager(Pager<TarGroDim> pager, TarGroDim record) {
		Pager<TarGroDim> records = tarGroDimMapper.findByPager(pager, record);
		for (TarGroDim tarGroDim : records.getPageItems()) {
			GroDimDetail groDimDetail = groDimDetailMapper.selectByPrimaryKey(tarGroDim.getGddId());
			tarGroDim.setGroDimName(groDimDetail.getGroDimName());
			Target target = targetMapper.selectByPrimaryKey(tarGroDim.getTargetCode());
			tarGroDim.setTargetName(target.getTargetName());
			User user = userDao.findById(Long.parseLong(tarGroDim.getCreatorId().trim()));
			tarGroDim.setCreatorName(user.getUserName());
			if (!"".equals(tarGroDim.getModifierId()) && null != tarGroDim.getModifierId()) {
				User us = userDao.findById(Long.parseLong(tarGroDim.getModifierId()));
				tarGroDim.setModifierName(us.getUserName());
			} else {
				tarGroDim.setModifierName(null);
			}
		}
		return records;
	}

	/**
	 * @function:
	 * @author ：程乐飞
	 * @datetime:2017年7月24日 下午3:50:02
	 */
	@Override
	public List<TarGroDim> findAllTarGroDim() {
		return this.tarGroDimMapper.findAll();
	}

	@Override
	public List<TarGroDim> findTarGroDimByTargetCode(String targetCode) {
		return this.tarGroDimMapper.findTarGroDimByTargetCode(targetCode);
	}


}
