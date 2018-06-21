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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="JDP_QRTZ_DEFINITION")
//@TableGenerator(name="TableGenerator", pkColumnValue="JDP_QRTZ_DEFINITION_ID", table="jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE", allocationSize = 1)
@SequenceGenerator(name="QrtzDefinition_SEQ",sequenceName="SEQ_JDP_QRTZ_DEFINITION",allocationSize = 1)
public class QrtzDefinition implements Serializable{
	private Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name; //自定义调度任务名称z
	private String code; //自定义调度任务code
	private String jobClassName; //JOB的全限定类名
	private String state; //状态，包括草稿、发布、已生成、终止
	private Date startJobDate; //开始日期
	private Date endJobDate; //结束日期
//	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")  
//	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startJobTime; //job开始时间，只包括时分秒
//	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
//	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endJobTime; //job结束时间，只包括时分秒，如果job未执行完成，中断job（预留字段）
	private Long repeat; //重复次数，重复完后结束
	private String endType; //结束类型，包括无结束日期、重复多少次后结束、有具体结束日期
	private String intervalType; //定期模式类型，包括天、周、月、年
	private String intervalMeta; //定期模式类型下的具体元数据配置，使用json方式保存
	private String descript; //描述
	private String jobDataMap; //任务参数
	
	private QrtzGroup qrtzGroup;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="QrtzDefinition_SEQ")
	@Column(name = "ID_")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="QRTZ_GROUP_ID")
	public QrtzGroup getQrtzGroup() {
		return qrtzGroup;
	}
	
	public void setQrtzGroup(QrtzGroup qrtzGroup) {
		this.qrtzGroup = qrtzGroup;
	}
	
	@Column(name="NAME_")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	public Date getStartJobDate() {
		return startJobDate;
	}

	public void setStartJobDate(Date startJobDate) {
		this.startJobDate = startJobDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	public Date getEndJobDate() {
		return endJobDate;
	}

	public void setEndJobDate(Date endJobDate) {
		this.endJobDate = endJobDate;
	}

	@Column(name="START_TIME")
	public Date getStartJobTime() {
		return startJobTime;
	}

	public void setStartJobTime(Date startJobTime) {
		this.startJobTime = startJobTime;
	}

	@Column(name="END_TIME")
	public Date getEndJobTime() {
		return endJobTime;
	}

	public void setEndJobTime(Date endJobTime) {
		this.endJobTime = endJobTime;
	}

	@Column(name="REPEAT_")
	public Long getRepeat() {
		return repeat;
	}
	
	public void setRepeat(Long repeat) {
		this.repeat = repeat;
	}
	
	@Column(name="END_TYPE")
	public String getEndType() {
		return endType;
	}
	
	public void setEndType(String endType) {
		this.endType = endType;
	}
	
	@Column(name="INTERVAL_TYPE")
	public String getIntervalType() {
		return intervalType;
	}
	
	public void setIntervalType(String intervalType) {
		this.intervalType = intervalType;
	}
	
	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Column(name="INTERVAL_META")
	public String getIntervalMeta() {
		return intervalMeta;
	}
	
	public void setIntervalMeta(String intervalMeta) {
		this.intervalMeta = intervalMeta;
	}
	
	@Column(name="DESCRIPT")
	public String getDescript() {
		return descript;
	}
	
	@Column(name="JOB_DATA_MAP")
	public String getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(String jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	/**
	 * 通过intervalMeta来转化为相应的Map型数据
	 * @return
	 */
	@Transient
	public Map<String,String> getMetaMap(){
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isNotEmpty(intervalMeta)){
			ObjectMapper mapper = new ObjectMapper();
			try {
				//convert JSON string to Map
				map = mapper.readValue(intervalMeta, 
				    new TypeReference<HashMap<String,String>>(){});
				log.info(map.toString());
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}
		return map;
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
	
	
	
	

	@Transient
	public String getCronExpression(){
		String cronExpression = "";
		String[] startTimes = startJobTime.toString().split(":");
		cronExpression += startTimes[2];
		cronExpression += " " + startTimes[1];
		cronExpression += " " + startTimes[0];
		Map<String, String> map = getMetaMap();
		if("天".equals(intervalType)){
			String dayType = map.get("dayType");
			if("dayType1".equals(dayType)){
				String dayNum  = map.get("dayNum");
				cronExpression +=" "+"1/"+dayNum+" * ?";
			}else if("dayType2".equals(dayType) ){
				cronExpression += " ? * *";
			}else{
				//按日保存类型的其他类型
			}
		}else if("周".equals(intervalType)){
			String weekType = (String) map.get("weekType");
			if("weekType1".equals(weekType)){
				String weekNum = (String) map.get("weekNum");
				String[] weekweekSpeNum = map.get("weekSpeNum").split(",");
				String weekweekSpeNums = "";
				for(int i=0; i<weekweekSpeNum.length; i++){
					if(i < weekweekSpeNum.length-1)
						weekweekSpeNums += weekweekSpeNum[i]+"/"+weekNum+",";
					else
						weekweekSpeNums += weekweekSpeNum[i]+"/"+weekNum;
				}
				cronExpression += " ? * " + weekweekSpeNums;
			}else{
				//按周保存类型的其他类型
			}
		}else if("月".equals(intervalType)){
			String monthType = (String) map.get("monthType");
			if("monthType1".equals(monthType)){
				String monthNum = (String) map.get("monthNum");
				String monthDayNum = map.get("monthDayNum");
				cronExpression += " " + monthDayNum + " 1/" + monthNum + " ?";
			}else if("monthType2".equals(monthType)){
				String monthNum =map.get("monthNum");
				String monthWeekNum = map.get("monthWeekNum");
				String monthSpeWeekNum = map.get("monthSpeWeekNum");
				cronExpression += " ? " + "1/" + monthNum + " " + monthSpeWeekNum+"#"+monthWeekNum;
			}else{
				//按月保存类型的其他类型
			}
		}else if("年".equals(intervalType)){
			String yearType = (String) map.get("yearType");
			if("yearType1".equals(yearType)){
				String yearNum = map.get("yearNum");
				String yearSpeMonth =map.get("yearSpeMonth");
				String yearSpeDay = map.get("yearSpeDay");
				cronExpression +=  " " + yearSpeDay +  " " + yearSpeMonth + " ? " + "empty/"+yearNum;
			}else if("yearType2".equals(yearType)){
				String yearNum =map.get("yearNum");
				String yearSpeMonth = map.get("yearSpeMonth");
				String yearWeekNum = map.get("yearWeekNum");
				String yearSpeWeekNum = map.get("yearSpeWeekNum");
				cronExpression += " ? " + yearSpeMonth + " " + yearSpeWeekNum+"#"+yearWeekNum +" empty/"+yearNum;
			}else{
				//按年保存类型的其他类型
			}
		}else{
			//其他intervalType类型
		}
		log.info(cronExpression);
		return cronExpression;
	}
	
}
