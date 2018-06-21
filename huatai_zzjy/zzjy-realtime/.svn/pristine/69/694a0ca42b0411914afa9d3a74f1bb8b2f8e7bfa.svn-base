package com.fairyland.jdp.orm.util.reflect;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class JavaClassFactory implements IClassFactory {
    private Map<String, JavaClasses> classes = new HashMap();

    public <T> IClass<T> getClass(Class<T> cla) {
	synchronized (this.classes) {
	    if (!this.classes.containsKey(cla.getName())) {
		this.classes.put(cla.getName(), new JavaClasses(cla));
	    }
	    return (IClass) this.classes.get(cla.getName());
	}
    }
}