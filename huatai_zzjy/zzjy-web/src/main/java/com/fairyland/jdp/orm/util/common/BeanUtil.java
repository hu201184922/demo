package com.fairyland.jdp.orm.util.common;

import java.beans.IntrospectionException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.ognl.OgnlException;

import com.fairyland.jdp.orm.util.ognl.OgnlUtil;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;
import com.fairyland.jdp.orm.util.reflect.Property;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil;



public class BeanUtil {
    private static final Log logger = LogFactory.getLog(BeanUtil.class);

    public static void setValue(Object target, String fieldName, Object value) {
	ClassUtil.setValue(target, fieldName, value);
    }

    public static Object getValue(Object target, String fieldName) {
	return ClassUtil.getValue(target, fieldName);
    }

    public static void copyProperties(Object dest, Object orig, String[] excludeProperties) {
	Property[] propertys = ClassUtil.getPropertys(dest);
	for (Property property : propertys) {
	    if (ObjectUtil.indexOf(excludeProperties, property.getName()) >= 0)
		continue;
	    if (!property.isWrite())
		continue;
	    Property source = ClassUtil.getProperty(orig, property.getName());
	    if ((ObjectUtil.isNull(source)) || (!source.isRead()))
		continue;
	    if (!source.getPropertyType().isAssignableFrom(property.getPropertyType()))
		continue;
	    property.setValue(dest, source.getValue(orig));
	}
    }

    public static <T> T copy(T dest, Object orig, String... excludeProperties) {
	return copy(dest, orig, "", excludeProperties);
    }

    @SuppressWarnings("unchecked")
    private static <T> T copy(T dest, Object orig, String superName, String[] excludeProperties) {
	try {
	    for (Map.Entry<String, Object> entry : OgnlUtil.getInstance().getBeanMap(orig).entrySet()) {
		if (RegexpUtil.find(superName.concat((String) entry.getKey()), excludeProperties))
		    continue;
		if (ObjectUtil.isNull(entry.getValue()))
		    continue;
		if (ObjectUtil.isNull(ClassUtil.getProperty(dest, (String) entry.getKey())))
		    continue;
		if (!ClassUtil.getProperty(dest, (String) entry.getKey()).isWrite())
		    continue;
		if (ClassUtil.isPrimitiveType(entry.getValue())) {
		    OgnlUtil.getInstance().setValue((String) entry.getKey(), dest, entry.getValue());
		} else if (ClassUtil.isList(ClassUtil.getProperty(dest, (String) entry.getKey()).getPropertyType())) {
		    List<Object> list = new ArrayList<Object>();
		    OgnlUtil.getInstance().setValue((String) entry.getKey(), dest, list);
		    int length = length(entry.getValue());
		    for (int i = 0; i < length; i++)
			list.add(copy(ClassUtil.newInstance((Class) ClassUtil.getMethodGenericParameterTypes(ClassUtil.getProperty(dest, (String) entry.getKey()).getWriteMethod().getMethod()).get(0)), get(entry.getValue(), i), superName.concat((String) entry.getKey()).concat("[").concat(String.valueOf(i)).concat("]").concat("."), excludeProperties));
		} else if (ClassUtil.isArray(ClassUtil.getProperty(dest, (String) entry.getKey()).getPropertyType())) {
		    Object object = OgnlUtil.getInstance().getValue((String) entry.getKey(), dest);
		    Object array = Array.newInstance(ClassUtil.getProperty(dest, (String) entry.getKey()).getPropertyType(), Array.getLength(object));
		    for (int i = 0; i < Array.getLength(object); i++)
			Array.set(array, i, copy(ClassUtil.newInstance((Class) ClassUtil.getMethodGenericParameterTypes(ClassUtil.getProperty(dest, (String) entry.getKey()).getWriteMethod().getMethod()).get(0)), get(entry.getValue(), i), superName.concat((String) entry.getKey()).concat("[").concat(String.valueOf(i)).concat("]").concat("."), excludeProperties));
		} else {
		    Object object = OgnlUtil.getInstance().getValue((String) entry.getKey(), dest);
		    if (object == null)
			OgnlUtil.getInstance().setValue((String) entry.getKey(), dest, entry.getValue());
		    else
			copy(object, entry.getValue(), excludeProperties);
		}
	    }
	} catch (IntrospectionException e) {
	    logger.debug(e.getMessage(), e);
	} catch (OgnlException e) {
	    logger.debug(e.getMessage(), e);
	}
	return dest;
    }

    private static int length(Object value) {
	if (ClassUtil.isArray(value))
	    return Array.getLength(value);
	if (ClassUtil.isList(value)) {
	    return ((List<?>) value).size();
	}
	return 0;
    }

    private static Object get(Object value, int i) {
	if (ClassUtil.isArray(value))
	    return Array.get(value, i);
	if (ClassUtil.isList(value)) {
	    return ((List<?>) value).get(i);
	}
	return null;
    }
    public static boolean isEqual(Object a,Object b){
    	if ((a == null || a.equals("")) && (b == null || b.equals(""))) {
			return true;
		} 
    	if (a == null) {
			if (b != null)
				return false;
		} else if (!a.equals(b))
			return false;
    	return true;
    }
}