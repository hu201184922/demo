package com.fairyland.jdp.orm.util.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Functor {
	private Logger log = LoggerFactory.getLogger(getClass());
    private Object source;
    private MethodProxy method;

    private Functor(Object object, MethodProxy method) {
	this.source = object;
	this.method = method;
    }

    public Object call() {
	try {
	    return this.method.invoke(this.source);
	} catch (Exception e) {
	    log.error(e.getMessage());
	}
	return null;
    }

    public Object call(Object object) {
	return this.method.invoke(this.source, new Object[] { object });
    }

    public static Functor create(Object object, MethodProxy method) {
	return new Functor(object, method);
    }
}