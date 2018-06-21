package com.fairyland.jdp.framework.tags.ui;

import com.fairyland.jdp.framework.tags.AbstractUITag;
import com.fairyland.jdp.framework.tags.model.Pager;


public class PagerTag extends AbstractUITag<Pager> {
    private static final long serialVersionUID = -7959554412236956819L;

    public void setPage(com.fairyland.jdp.orm.Pager<?> page) {
	((Pager) getUIBean()).setPage(page);
    }

    public void setFormId(String formId) {
	((Pager) getUIBean()).setFormId(formId);
    }
}