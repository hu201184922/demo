
package com.huatai.web.service;

import java.text.ParseException;
import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.UserSetWarn;

/**
 * 用户设置预警的service层借口
 * @author 杜金虎
 *
 */
public interface UserSetWarnService {
	
	/**
	 * 修改运行状态
	 */
	void updateStatus(String status, String bsid);	

	/**
	 * 分页查询
	 * @param pager
	 * @param warnStatus 
	 * @param warnTarget 
	 * @param monitorObject 
	 * @param warn
	 * @return
	 */
	Pager<UserSetWarn> findByPager(Pager<UserSetWarn> pager, UserSetWarn record, String orgName, String warnTarget, String warnStatus,String userName);

	/**
	 * 添加预警
	 * @param userWarn
	 */
	void addWarn(UserSetWarn userWarn);

	/**
	 * 根据预警id查询预警
	 * @param bsId
	 * @return 
	 */
	UserSetWarn findWarnById(Integer bsId);

	/**
	 * 根据预警id删除预警
	 * @param bsid
	 */
	void deleteWarnById(Integer bsId)throws ParseException;

	/**
	 * 分页查询预警结果
	 * @param pager
	 * @param record
	 * @param warnTarget 
	 * @param orgName 
	 * @return
	 */
	Pager<UserSetWarn> findResultByPager(Pager<UserSetWarn> pager, UserSetWarn record, String RorgName, String RwarnTarget);

	/**
	 * 保存更新内容
	 * @param userWarn
	 */
	void updateWarn(UserSetWarn userWarn);

	/**
	 * 查询预警方式
	 * @return
	 */
	List<String> findAlertType();

}
