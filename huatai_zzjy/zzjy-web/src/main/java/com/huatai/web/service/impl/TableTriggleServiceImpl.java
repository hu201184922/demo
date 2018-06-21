package com.huatai.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatai.web.mapper.TableTriggleMapper;
import com.huatai.web.model.TableTriggle;
import com.huatai.web.model.TableTriggleExample;
import com.huatai.web.service.TableTriggleService;

@Service
public class TableTriggleServiceImpl implements TableTriggleService {
	@Autowired
	private TableTriggleMapper tableTriggleMapper;

	@Override
	public int delete(TableTriggle record) {
		TableTriggleExample example = new TableTriggleExample();
		example.createCriteria().andQrtzGroupIdEqualTo(record.getQrtzGroupId())
				.andQrtzCodeEqualTo(record.getQrtzCode());
		return tableTriggleMapper.deleteByExample(example);
	}

	@Override
	public int insert(TableTriggle record) {
		return tableTriggleMapper.insert(record);
	}

	@Override
	public List<TableTriggle> findAll(TableTriggle tableTriggle) {
		return tableTriggleMapper.findAll(tableTriggle);
	}

	public List<TableTriggle> queryTableTriggleList() {
		return tableTriggleMapper.queryTableTriggleList();
	}

}
