package com.fairyland.jdp.framework.dictionary.view;

import java.io.Serializable;

import com.fairyland.jdp.framework.dictionary.domain.DictionaryItem;

public class DictItemModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1446778257736755340L;

	private String code;

	private String name;

	public DictItemModel() {
	}

	public DictItemModel(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public DictItemModel(DictionaryItem item) {
		if (item != null) {
			this.code = item.getCode();
			this.name = item.getName();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
