package com.fairyland.jdp.framework.quartz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyland.jdp.framework.domain.IdEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name="JDP_QRTZ_TRIGGER_CRON")
//@TableGenerator(name="TableGenerator", pkColumnValue="JDP_QRTZ_TRIGGER_CRON_ID", table="jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE", allocationSize = 1)
@SequenceGenerator(name="CRONTRIGGER_SEQ",sequenceName="SEQ_JDP_QRTZ_TRIGGER_CRON",allocationSize = 1)
public class CronTrigger implements Serializable{
	private Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private String name;
	private QrtzGroup qrtzGroup;
	private Date startTime;
	private Date endTime;
	private String cronExp;
	private String description;
	private String jobDataMap;
	//任务定义字段
	private String code;//自定义任务的code
	private String jobClassName;
	private String state;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="CRONTRIGGER_SEQ")
	@Column(name = "ID_")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="CODE_")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="JOB_CLASS_NAME")
	public String getJobClassName() {
		return jobClassName;
	}
	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	@Column(name="STATE_")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Column(name="NAME_")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name="QRTZ_GROUP_ID")
	public QrtzGroup getQrtzGroup() {
		return qrtzGroup;
	}
	public void setQrtzGroup(QrtzGroup qrtzGroup) {
		this.qrtzGroup = qrtzGroup;
	}
	
	@Column(name="START_TIME")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="END_TIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="CRON_EXPR")
	public String getCronExp() {
		return cronExp;
	}
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="JOB_DATA_MAP")
	public String getJobDataMap() {
		return jobDataMap;
	}
	public void setJobDataMap(String jobDataMap) {
		this.jobDataMap = jobDataMap;
	}
	
	/**
	 * 通过JobDataMap来转化为相应的Map型数据
	 * @return
	 */
	@Transient
	public Map<String,String> getJobDataMetaMap(){
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isNotEmpty(jobDataMap)){
			ObjectMapper mapper = new ObjectMapper();
			try {
				//convert JSON string to Map
				map = mapper.readValue(jobDataMap, 
				    new TypeReference<HashMap<String,String>>(){});
				log.info(map.toString());
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		log.info(String.valueOf(map.size()));
		return map;
	}
	

}
