package com.fairyland.jdp.common.persistent.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 
 * (1) GeneratorType.AUTO: 容器自动生成 (2) GenerationType.IDENTITY :
 * 使用数据库的自动增长字段生成，JPA容器将使用数据库的自增长字段为新增的
 * 实体对象赋唯一值，这种情况下，需要数据库本身提供自增长字段属性，支持该属性的DB有：SQL Server、DB2、MySQL、Derby等支持。 (3)
 * GenerationType.SEQUENCE: 使用数据库的序列号为新增加的实体对象赋唯一值, 这种情况下需要数据库提供
 * 对序列号的支持常用的数据库中，Oracle支持。 (4) GenerationType.TABLE :
 * 使用数据库表的字段生成，表示使用数据库中指定表的某个字段记录实体对象的标识
 */
// JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(getId()).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IdEntity))
			return false;
		IdEntity castObj = (IdEntity) obj;
		if (this.getId() == null || castObj.getId() == null)
			return false;

		return new EqualsBuilder().append(this.getId(), castObj.getId())
				.isEquals();
	}

}
