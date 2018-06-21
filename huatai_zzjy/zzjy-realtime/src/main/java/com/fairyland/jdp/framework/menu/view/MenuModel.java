package com.fairyland.jdp.framework.menu.view;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MenuModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String url;
	private Boolean visible = true;
	private Boolean enabled = true;
	private String permString;
	private String MenuIcon;
	private String disableIcon;
	private MenuModel parent;
	private List<MenuModel> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuIcon() {
		return MenuIcon;
	}

	public void setMenuIcon(String MenuIcon) {
		this.MenuIcon = MenuIcon;
	}

	public String getDisableIcon() {
		return disableIcon;
	}

	public void setDisableIcon(String disableIcon) {
		this.disableIcon = disableIcon;
	}

	public MenuModel getParent() {
		return parent;
	}

	public void setParent(MenuModel parent) {
		this.parent = parent;
	}

	public List<MenuModel> getChildren() {
		return children;
	}

	public void setChildren(List<MenuModel> children) {
		this.children = children;
	}

	public String getPermString() {
		if(StringUtils.isEmpty(permString)){
			return "*:*";
		}
		return permString;
	}

	public void setPermString(String permString) {
		this.permString = permString;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
