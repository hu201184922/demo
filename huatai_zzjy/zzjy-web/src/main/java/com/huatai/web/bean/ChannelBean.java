package com.huatai.web.bean;

import java.util.List;

public class ChannelBean {
	private String name;

	private List<QueryDimDetailBean> channelList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QueryDimDetailBean> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<QueryDimDetailBean> channelList) {
		this.channelList = channelList;
	}

}
