package com.fairyland.jdp.framework.dictionary.domain;

import java.io.Serializable;

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

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JDP_DICT_ITEM")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_DICT_ITEM_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE", allocationSize = 1)
@SequenceGenerator(name="DICTIONARYITEM_SEQ",sequenceName="SEQ_JDP_DICT_ITEM",allocationSize = 1)
public class DictionaryItem implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String code;//唯一代码
    private String name;//名称
    private Integer sortIndex;//排序号
    private Boolean disabled=Boolean.FALSE;//禁用
    private String descript;
    private Long pid;
    private Dictionary dictionary;

	@Column(name="CODE_")
    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

	@Column(name="NAME_")
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

	@Column(name="DISABLED_")
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Integer getSortIndex() {
	return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
	this.sortIndex = sortIndex;
    }

    public String getDescript() {
	return descript;
    }

    public void setDescript(String descript) {
	this.descript = descript;
    }

    @ManyToOne
    @JoinColumn(name = "DICT_ID")
	@JsonIgnore
	@JSONField(serialize=false)
	@org.codehaus.jackson.annotate.JsonIgnore
    public Dictionary getDictionary() {
	return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
	this.dictionary = dictionary;
    }

	public void setId(Long id) {
		this.id = id;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="DICTIONARYITEM_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
}
