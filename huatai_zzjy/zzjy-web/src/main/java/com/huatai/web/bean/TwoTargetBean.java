package com.huatai.web.bean;

public class TwoTargetBean {
	private String groupId;
	private String groupName;
	private String desc;// 组描述
	private String status;
	private Object link;// 二级指标分组下的二级指标的状态

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getLink() {
		return link;
	}

	public void setLink(Object link) {
		this.link = link;
	}

}
