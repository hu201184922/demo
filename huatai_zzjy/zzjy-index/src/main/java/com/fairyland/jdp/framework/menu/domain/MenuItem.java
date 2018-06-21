package com.fairyland.jdp.framework.menu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fairyland.jdp.framework.domain.TreeNodeEntity;

@Entity
@Table(name = "JDP_MENU_ITEM")
@TableGenerator(name = "TableGenerator", pkColumnValue = "JDP_MENU_ITEM_ID", table = "jdp_keys", pkColumnName = "KEYID", valueColumnName = "KEYVALUE", allocationSize = 1)
@SequenceGenerator(name="MENUITEM_SEQ",sequenceName="SEQ_JDP_MENU_ITEM",allocationSize = 1)
public class MenuItem extends TreeNodeEntity<MenuItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1464122886652733186L;

	public static final int MENU_ITEM = 0;

	public static final int SEPERATOR = 1;

	private Long id;
	private Integer type = MENU_ITEM;
	private Boolean visible = true;
	private Boolean enabled = true;
	private String menuIcon;
	private String disableIcon;
	private String url;
	private String permString;
	private String description;
	private String meta1;
	private String meta2;
	private String meta3;
	// ---非持久化属性----
	private Boolean current;
	private Boolean checked;

	@Transient
	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	@Transient
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Column(name = "TYPE_")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "VISIBLE_")
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Column(name = "ENABLED_")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "ICON_")
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getDisableIcon() {
		return disableIcon;
	}

	public void setDisableIcon(String disableIcon) {
		this.disableIcon = disableIcon;
	}

	@Column(name = "URL_")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "DESCRIPTION_")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeta1() {
		return meta1;
	}

	public void setMeta1(String meta1) {
		this.meta1 = meta1;
	}

	public String getMeta2() {
		return meta2;
	}

	public void setMeta2(String meta2) {
		this.meta2 = meta2;
	}

	public String getMeta3() {
		return meta3;
	}

	public void setMeta3(String meta3) {
		this.meta3 = meta3;
	}
	
	public String getPermString() {
		return permString;
	}

	public void setPermString(String permString) {
		this.permString = permString;
	}
	
	@Formula("(select count(m.cid) from JDP_MENU_ITEM m where m.PARENT_ID = CID)")
	public Integer getChildCount() {
		return childCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="MENUITEM_SEQ")
	@Column(name = "CID")
	public Long getId() {
		return id;
	}
	
	public void buildPermString(){
		this.permString=this.url.replaceAll("/", ".");
	}
}
