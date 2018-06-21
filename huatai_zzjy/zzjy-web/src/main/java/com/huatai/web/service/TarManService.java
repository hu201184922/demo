package com.huatai.web.service;

import java.util.List;

import com.huatai.web.model.JyfxTarget;

public interface TarManService {

	List<JyfxTarget> findByAnTargetCode(String anTargetCode);

	List<JyfxTarget> findByAnTargetCode(String anTargetCode, String depts);

	int addTarMan(JyfxTarget record);

	int deleteTarMan(String targetCode);

	List<JyfxTarget> findByIsAnTargetCode(String targetCode, String depts);

	int updateByTargetCodeAndAnTargetCode(JyfxTarget record);


}
