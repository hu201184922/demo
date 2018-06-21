package com.huatai.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fairyland.jdp.framework.security.authc.SessionContextUtils;
import com.fairyland.jdp.orm.Pager;
import com.huatai.web.mapper.ImportTempMapper;
import com.huatai.web.mapper.LabelOrgMapper;
import com.huatai.web.mapper.PremTargetMapper;
import com.huatai.web.mapper.RealtimeTargetMapper;
import com.huatai.web.mapper.RenewalTargetMapper;
import com.huatai.web.mapper.RiskTargetMapper;
import com.huatai.web.mapper.TrainTargetMapper;
import com.huatai.web.mapper.WealAnnConfigMapper;
import com.huatai.web.model.DictItem;
import com.huatai.web.model.ImportTemp;
import com.huatai.web.model.LabelOrg;
import com.huatai.web.model.PremTarget;
import com.huatai.web.model.RealtimeTarget;
import com.huatai.web.model.RenewalTarget;
import com.huatai.web.model.RiskTarget;
import com.huatai.web.model.TrainTarget;
import com.huatai.web.model.WealAnnConfig;
import com.huatai.web.service.ImportTempService;

@Service
public class ImportTempServiceImpl implements ImportTempService {

	@Autowired
	private ImportTempMapper importTempMapper;
	
	@Autowired
	private RealtimeTargetMapper realtimeTargetMapper;
	@Autowired
	private RiskTargetMapper riskTargetMapper;
	@Autowired
	private RenewalTargetMapper renewalTargetMapper;
	@Autowired
	private TrainTargetMapper trainTargetMapper;
	@Autowired
	private PremTargetMapper premTargetMapper;
	@Autowired
	private LabelOrgMapper labelOrgMapper;
	@Autowired
	private WealAnnConfigMapper wealAnnConfigMapper;
	
	@Override
	public int insert(ImportTemp record) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
		String loginName = SessionContextUtils.getLoginName().toString();
		record.setOpType("A");
		record.setCreateTime(now);
		record.setModifyTime(now);
		record.setCreatorId(loginName);
		record.setModifierId(loginName);
		return importTempMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(ImportTemp record) {
		return importTempMapper.updateByPrimaryKey(record);
	}

	@Override
	public ImportTemp selectByPrimaryKey(Long itId) {
		return importTempMapper.selectByPrimaryKey(itId);
	}
	
	@Override
	public int deleteByItId(Integer itId,ImportTemp importTemp) {
		return importTempMapper.deleteByPrimaryKey((long)itId,importTemp);
	}

	@Override
	public Pager<ImportTemp> findByPager(Pager<ImportTemp> pager, ImportTemp record) {
		return importTempMapper.findByPager(pager, record);
	}

	@Override
	public void insertGXBOnTime(List<RealtimeTarget> rt) {
		realtimeTargetMapper.insert(rt);
	}

	@Override
	public void insertRiskTarget(List<RiskTarget> riskTarget) {
		riskTargetMapper.insert(riskTarget);
	}

	@Override
	public void insertRenewal(RenewalTarget renewalTarget) {
		renewalTargetMapper.insert(renewalTarget);
	}

	@Override
	public void insertTrain(List<TrainTarget> trainTarget) {
		trainTargetMapper.insert(trainTarget);
	}

	@Override
	public void insertPrem(List<PremTarget> premTarget) {
		premTargetMapper.insert(premTarget);
	}

	@Override
	public void insertLabelOrg(List<LabelOrg> labelOrg) {
		labelOrgMapper.insert(labelOrg);
	}

	@Override
	public List<DictItem> selectUploadEX() {
		return importTempMapper.selectUploadEX();
	}

	@Override
	public List<RealtimeTarget> findGXBOnTime() {
		return realtimeTargetMapper.findGXBOnTime();
	}

	@Override
	public List<RiskTarget> findRisk() {
		return riskTargetMapper.findRisk();
	}

	@Override
	public List<TrainTarget> findTrain() {
		return trainTargetMapper.findTrain();
	}

	@Override
	public List<PremTarget> findPrem() {
		return premTargetMapper.findPrem();
	}

	@Override
	public List<LabelOrg> findLabelOrg() {
		return labelOrgMapper.findLabelOrg();
	}

	@Override
	public List<WealAnnConfig> findWealAnnConfig() {
		return wealAnnConfigMapper.findWealAnnConfig();
	}

	@Override
	public void insertWealAnnConfig(List<WealAnnConfig> wcs) {
		wealAnnConfigMapper.insert(wcs);
	}

	@Override
	public int updateGXBOnTime(RealtimeTarget rt) {
		return realtimeTargetMapper.updateByOrgCodeAndDateCode(rt);
	}

	@Override
	public int updateRiskTarget(RiskTarget rt) {
		return riskTargetMapper.updateByOrgCodeAndDateCode(rt);
	}

	@Override
	public int updateTrain(TrainTarget tt) {
		return trainTargetMapper.updateByOrgCodeAndDateCode(tt);
	}

	@Override
	public int updatePrem(PremTarget pt) {
		return premTargetMapper.updatePremByOrgCodeAndDateCode(pt);
	}

	@Override
	public int updateLabelOrg(LabelOrg rt) {
		return labelOrgMapper.updateByOrgCodeAndDateCode(rt);
	}

	@Override
	public int updateWealAnnConfig(WealAnnConfig wc) {
		return wealAnnConfigMapper.updateByOrgCodeAndDateCode(wc);
	}
}
