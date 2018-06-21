package com.huatai.web.service;

import java.text.ParseException;
import java.util.List;

import com.fairyland.jdp.orm.Pager;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.ImportTemp;
import com.huatai.web.model.LabelOrg;
import com.huatai.web.model.PremTarget;
import com.huatai.web.model.RealtimeTarget;
import com.huatai.web.model.RenewalTarget;
import com.huatai.web.model.RiskTarget;
import com.huatai.web.model.TrainTarget;
import com.huatai.web.model.WealAnnConfig;

public interface ImportTempService {

	 int insert(ImportTemp record) throws ParseException;
	 
	 int updateByPrimaryKey(ImportTemp record) throws ParseException;
	 
	 ImportTemp selectByPrimaryKey(Long itId) throws ParseException;
	 
	 int deleteByItId(Integer id, ImportTemp importTemp);

	 Pager<ImportTemp> findByPager(Pager<ImportTemp> pager, ImportTemp record);

	void insertGXBOnTime(List<RealtimeTarget> rts);

	void insertRiskTarget(List<RiskTarget> rgs);

	void insertRenewal(RenewalTarget renewalTarget);

	void insertTrain(List<TrainTarget> tts);

	void insertPrem(List<PremTarget> pts);

	void insertLabelOrg(List<LabelOrg> los);

	List<DictItem> selectUploadEX();

	List<RealtimeTarget> findGXBOnTime();

	List<com.huatai.web.model.RiskTarget> findRisk();

	List<TrainTarget> findTrain();

	List<PremTarget> findPrem();

	List<LabelOrg> findLabelOrg();

	List<WealAnnConfig> findWealAnnConfig();

	void insertWealAnnConfig(List<WealAnnConfig> wcs);

	int updateGXBOnTime(RealtimeTarget rt);

	int updateRiskTarget(RiskTarget rt);

	int updateTrain(TrainTarget tts);

	int updatePrem(PremTarget pts);

	int updateLabelOrg(LabelOrg rt);

	int updateWealAnnConfig(WealAnnConfig wc);

}
