package com.huatai.web.service;

import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.TarInitSql;
import com.huatai.web.model.Target;

public interface TarInitSqlService {

    int deleteByPrimaryKey(Integer tisId);

    void insert(TarInitSql record);

    TarInitSql selectByPrimaryKey(Integer tisId);

    int updateByPrimaryKey(TarInitSql record);
    
	List<Target> findSubPlates();

	List<Target> getTarBySubId(String subPlaId);

	List<DictItem> findFuncList();

	Pager<TarInitSql> findByPager(Pager<TarInitSql> pager, TarInitSql record);
}
