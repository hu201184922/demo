package com.fairyland.jdp.framework.quartz.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Formula;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;

@Entity
@Table(name="JDP_QRTZ_GROUP")
//@TableGenerator(name="TableGenerator", pkColumnValue="JDP_QRTZ_GROUP_ID", table="jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE", allocationSize = 1)
@SequenceGenerator(name="QrtzGroup_SEQ",sequenceName="SEQ_JDP_QRTZ_GROUP",allocationSize = 1)
public class QrtzGroup extends TreeNodeEntity<QrtzGroup>{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;//分类code（确保唯一性）
	private String descript;//描述
	private Set<QrtzDefinition> qrtzDefinitions;//存在的调度定义
	private Set<CronTrigger> cronTriggers; //存在的cronTrigger集合
	private Set<SimpleTrigger> simpleTriggers; // 存在的SimpleTrigger集合
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="QrtzGroup_SEQ")
	@Column(name = "ID_")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="QRTZ_GROUP_ID")
	public Set<SimpleTrigger> getSimpleTriggers() {
		return simpleTriggers;
	}
	public void setSimpleTriggers(Set<SimpleTrigger> simpleTriggers) {
		this.simpleTriggers = simpleTriggers;
	}
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="QRTZ_GROUP_ID")
	public Set<CronTrigger> getCronTriggers() {
		return cronTriggers;
	}
	public void setCronTriggers(Set<CronTrigger> cronTriggers) {
		this.cronTriggers = cronTriggers;
	}
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="QRTZ_GROUP_ID")
	public Set<QrtzDefinition> getQrtzDefinitions() {
		return qrtzDefinitions;
	}
	public void setQrtzDefinitions(Set<QrtzDefinition> qrtzDefinitions) {
		this.qrtzDefinitions = qrtzDefinitions;
	}
	
	@Column(name="CODE_")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="descript_")
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Formula("(select count(m.id_) from JDP_QRTZ_GROUP m where m.PARENT_ID = ID_)")
	public Integer getChildCount() {
		return childCount;
	}
}
