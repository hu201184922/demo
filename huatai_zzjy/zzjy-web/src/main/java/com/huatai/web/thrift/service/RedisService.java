package com.huatai.web.thrift.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huatai.web.model.GroDimDetail;
import com.huatai.web.model.QueryDim;
import com.huatai.web.model.TarQueryDim;
import com.huatai.web.model.TarReg;
import com.huatai.web.model.TarRegQue;
import com.huatai.web.model.TarRegSql;
import com.huatai.web.model.Target;
import com.huatai.web.model.TemReg;
import com.huatai.web.model.UserSetWarn;

public interface RedisService {

	List<HashMap<String, Object>> getSqlData(String sql);

	List<HashMap<String, String>> getOrgList(String subCode, String orgType);

	List<HashMap<String, String>> getOrgListByIndex(String subCode, String orgType);

	List<HashMap<String, String>> getServerOrgList();

	List<HashMap<String, String>> getCenterOrgList();

	List<Map<String, Object>> getTopThreeProduct(String targetCode, String orgCode);

	List<TarReg> findTarRegsBySubAndReg(String sub, Integer regId);

	TemReg findRegionByCode(String regCode);

	TarRegSql findTarRegSql(String tar, Integer regId, String groupType, String groupDetail, String dateType);

	Target findTargetByCode(String code);

	List<UserSetWarn> getUserSetWarnListByOn();

	Object getTarInitSql(String fun, String targetCode, String groupType, String groupDetail, String dateType);

	List<GroDimDetail> findGroDimDetailListByTarget(String groupType, String target);

	List<TarQueryDim> findTarQueryDimByTar(String tar);

	QueryDim findQueryDimById(Integer id);

	List<TarRegQue> findTarRegQueBySubAndTempAndRegAndTar(String sub, String temp, Integer regId, String tar);

}
