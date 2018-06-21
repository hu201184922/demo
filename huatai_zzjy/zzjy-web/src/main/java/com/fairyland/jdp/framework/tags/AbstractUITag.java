package com.fairyland.jdp.framework.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fairyland.jdp.framework.service.FreemarkerService;
import com.fairyland.jdp.framework.spring.SpringContextUtil;
import com.fairyland.jdp.framework.tags.model.UIBean;
import com.fairyland.jdp.framework.tags.ui.AnchorTag;
import com.fairyland.jdp.framework.tags.ui.ButtonTag;
import com.fairyland.jdp.framework.tags.ui.FormTag;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.common.Stack;
import com.fairyland.jdp.orm.util.common.StringUtil;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil;



public abstract class AbstractUITag<T extends UIBean> extends TagSupport {
    public static final String TAG_STACK = "tag.stack";
    private static final long serialVersionUID = -5265883826096993641L;
    private final Log logger = LogFactory.getLog(getClass());

    @SuppressWarnings("unchecked")
    public void setPageContext(PageContext pageContext) {
	super.setPageContext(pageContext);
	if (ObjectUtil.isNull(this.pageContext.getAttribute(TAG_STACK))) {
	    this.pageContext.setAttribute(TAG_STACK, new Stack<T>());
	}
	Stack<T> stack = (Stack<T>) this.pageContext.getAttribute(TAG_STACK);
	try {
	    stack.push((T) ClassUtil.getSuperClassGenricType(getClass()).newInstance());
	} catch (InstantiationException e) {
	    this.logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    this.logger.error(e.getMessage(), e);
	}
    }

    public HttpServletResponse getResponse() {
	return (HttpServletResponse) this.pageContext.getResponse();
    }

    public HttpServletRequest getRequest() {
	return (HttpServletRequest) this.pageContext.getRequest();
    }

    @SuppressWarnings("unchecked")
    public int doStartTag() throws JspException {
	T model = ((Stack<T>) this.pageContext.getAttribute(TAG_STACK)).peek();
	String requestURI = null;
	if (getClass().isAssignableFrom(ButtonTag.class)) {
	    requestURI = StringUtil.nullValue(ClassUtil.getValue(model, "url"));
	} else if (getClass().isAssignableFrom(AnchorTag.class)) {
	    requestURI = StringUtil.nullValue(ClassUtil.getValue(model, "href"));
	} else if (getClass().isAssignableFrom(FormTag.class)) {
	    requestURI = StringUtil.nullValue(ClassUtil.getValue(model, "action"));
	} else {
	    Writer(model.getDefaultOpenTemplate(), model);
	    return 1;
	}
	requestURI = RegexpUtil.replaceFirst(requestURI, "^" + getRequest().getContextPath(), "");
	requestURI = requestURI.indexOf('?') > 0 ? requestURI.substring(0, requestURI.indexOf('?')) : requestURI;
    Writer(model.getDefaultOpenTemplate(), model);
    return 1;
    }

    @SuppressWarnings("unchecked")
    public int doEndTag() throws JspException {
	T model = ((Stack<T>) this.pageContext.getAttribute(TAG_STACK)).pop();
	String requestURI = null;
	if (getClass().isAssignableFrom(ButtonTag.class)) {
	    requestURI = StringUtil.nullValue(ClassUtil.getValue(model, "url"));
	} else if (getClass().isAssignableFrom(AnchorTag.class)) {
	    requestURI = StringUtil.nullValue(ClassUtil.getValue(model, "href"));
	} else if (getClass().isAssignableFrom(FormTag.class)) {
	    requestURI = StringUtil.nullValue(ClassUtil.getValue(model, "action"));
	} else {
	    Writer(model.getDefaultTemplate(), model);
	    return 6;
	}
	requestURI = RegexpUtil.replaceFirst(requestURI, "^" + getRequest().getContextPath(), "");
	requestURI = requestURI.indexOf('?') > 0 ? requestURI.substring(0, requestURI.indexOf('?')) : requestURI;
	    Writer(model.getDefaultTemplate(), model);
	    return 6;
	
    }

    @SuppressWarnings("unchecked")
    protected T getUIBean() {
	return ((Stack<T>) this.pageContext.getAttribute(TAG_STACK)).peek();
    }

    public void Writer(String templateName, T tagModel) {
	if (StringUtil.isBlank(templateName))
	    return;
	FreemarkerService freemarkerService = (FreemarkerService) SpringContextUtil.getBean("freemarkerService", FreemarkerService.class);
	freemarkerService.writer(tagModel, AbstractUITag.class, "template", templateName, this.pageContext.getOut());
    }

    public void setId(String id) {
	getUIBean().setId(id);
    }

    public void setHref(String href) {
	getUIBean().setHref(href);
    }

    public void setName(String name) {
	getUIBean().setName(name);
    }

    public void setOnclick(String onclick) {
	getUIBean().setOnclick(onclick);
    }

    public void setCssClass(String cssClass) {
	getUIBean().setCssClass(cssClass);
    }

    public void setStyle(String style) {
	getUIBean().setStyle(style);
    }

    public void setTitle(String title) {
	getUIBean().setTitle(title);
    }
}