package com.fairyland.jdp.framework.tags.model;

public class Pager extends UIBean {
    public static final String OPEN_TEMPLATE = "pager.ftl";
    private String formId;
    private com.fairyland.jdp.orm.Pager<?> page;

    public String getDefaultOpenTemplate() {
	return "pager.ftl";
    }

    public String getDefaultTemplate() {
	return null;
    }

    public com.fairyland.jdp.orm.Pager<?> getPage() {
	return this.page;
    }

    public void setPage(com.fairyland.jdp.orm.Pager<?> page) {
	this.page = page;
    }

    public void setFormId(String formId) {
	this.formId = formId;
    }

    public String getFormId() {
	return this.formId;
    }
}