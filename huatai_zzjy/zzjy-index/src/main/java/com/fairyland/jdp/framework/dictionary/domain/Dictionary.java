package com.fairyland.jdp.framework.dictionary.domain;

import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "JDP_DICT")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_DICT_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE")
@SequenceGenerator(name="DICTIONARY_SEQ",sequenceName="SEQ_JDP_DICT",allocationSize = 1)
public class Dictionary extends TreeNodeEntity<Dictionary> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String code;// 唯一代码
    private Boolean disabled = Boolean.FALSE;// 禁用
    private Boolean system;// 是否系统级。系统级字典用来管理一些系统级的配置数据，不能由业务人员随便修改删除。
    private String descript;// 描述
    private Set<DictionaryItem> items;

	@Column(name="CODE_")
    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

	@Column(name="DISTABLED_")
    public Boolean getDisabled() {
	return disabled;
    }

    public void setDisabled(Boolean disabled) {
	this.disabled = disabled;
    }

	@Column(name="SYSTEM_")
    public Boolean getSystem() {
	return system;
    }

    public void setSystem(Boolean system) {
	this.system = system;
    }

	@Column(name="DESCRIPT_")
    public String getDescript() {
	return descript;
    }

    public void setDescript(String descript) {
	this.descript = descript;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "DICT_ID")
    public Set<DictionaryItem> getItems() {
	return items;
    }

    public void setItems(Set<DictionaryItem> items) {
	this.items = items;
    }

    @Formula("(select count(m.cid) from JDP_DICT m where m.PARENT_ID = CID)")
	public Integer getChildCount() {
		return childCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="DICTIONARY_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}
}
