
package com.huatai.web.service;

import java.util.List;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.WarnResult;
import com.huatai.web.model.WarnResultExample;

/**
 *@描述   :
 *@作者   : 程乐飞
 *@日期时间: 2017年8月9日 上午10:04:07
 */
public interface WarnResultService {
	
	
	int countByExample(WarnResultExample example);

    int deleteByExample(WarnResultExample example);

    void deleteByPrimaryKey(Integer wrId);

    void insert(WarnResult record);

    int insertSelective(WarnResult record);

    List<WarnResult> selectByExample(WarnResultExample example);

    WarnResult selectByPrimaryKey(Integer wrId);

    int updateByExampleSelective(WarnResult record,WarnResultExample example);

    int updateByExample(WarnResult record, WarnResultExample example);

    int updateByPrimaryKeySelective(WarnResult record);

    void updateByPrimaryKey(WarnResult record);
    
    Pager<WarnResult> findByPager(Pager<WarnResult> pager, WarnResult record);

	
	WarnResult selectByUsername(String trim);
	
	Pager<WarnResult> findWarnResult(Pager<WarnResult> pager, WarnResult record);

}
