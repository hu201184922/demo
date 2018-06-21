package com.fairyland.jdp.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.MethodUtils;

import com.fairyland.jdp.framework.domain.IdEntity;

public class FBeanUtilsBean extends BeanUtilsBean{
	
	public static FBeanUtilsBean getFInstance(){
		return new FBeanUtilsBean();
	}
	
	/**
     * <p>Return the entire set of properties for which the specified bean
     * provides a read method. This map contains the to <code>String</code>
     * converted property values for all properties for which a read method
     * is provided (i.e. where the getReadMethod() returns non-null).</p>
     *
     * <p>This map can be fed back to a call to
     * <code>BeanUtils.populate()</code> to reconsitute the same set of
     * properties, modulo differences for read-only and write-only
     * properties, but only if there are no indexed properties.</p>
     *
     * <p><strong>Warning:</strong> if any of the bean property implementations
     * contain (directly or indirectly) a call to this method then 
     * a stack overflow may result. For example:
     * <code><pre>
     * class MyBean
     * {
     *    public Map getParameterMap()
     *    {
     *         BeanUtils.describe(this);
     *    }
     * }
     * </pre></code>
     * will result in an infinite regression when <code>getParametersMap</code>
     * is called. It is recommended that such methods are given alternative
     * names (for example, <code>parametersMap</code>).
     * </p>
     * @param bean Bean whose properties are to be extracted
     * @return Map of property descriptors
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     */
    public Map describe(Object bean,Set<String> excludes)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        if (bean == null) {
            return (new java.util.HashMap());
        }
        
        Map description = new HashMap();
        PropertyDescriptor[] descriptors =
                getPropertyUtils().getPropertyDescriptors(bean);
            Class clazz = bean.getClass();
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
            	if(excludes.contains(name)){
            		continue;
            	}
                Method method = (MethodUtils.getAccessibleMethod(clazz, descriptors[i].getReadMethod()));
                if (method != null) {
                    description.put(name, getProperty(bean, name));
                }
            }
        return (description);
    }
    
    /**
     * 默认过滤掉Set属性、List属性、domain属性
     * @param bean
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public Map describeExclude(Object bean)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        if (bean == null) {
            return (new java.util.HashMap());
        }
        
        Map description = new HashMap();
        PropertyDescriptor[] descriptors =
                getPropertyUtils().getPropertyDescriptors(bean);
            Class clazz = bean.getClass();
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                Method method = (MethodUtils.getAccessibleMethod(clazz, descriptors[i].getReadMethod()));
                if (method != null) {
                	Class<?> returnClazz = method.getReturnType();
                	if(returnClazz.isAssignableFrom(List.class) 
                			|| returnClazz.isAssignableFrom(Set.class)
                			|| returnClazz.isAssignableFrom(IdEntity.class)){
                		//
                		continue;
                	}
                    description.put(name, getProperty(bean, name));
                }
            }
        return (description);
    }

    /**
     * 过滤掉Set属性、List属性、domain属性、以及传入的参数中的属性
     * @param bean
     * @param excludes
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public Map describeExcludeAnd(Object bean,Set<String> excludes)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        if (bean == null) {
            return (new java.util.HashMap());
        }
        
        Map description = new HashMap();
        PropertyDescriptor[] descriptors =
                getPropertyUtils().getPropertyDescriptors(bean);
            Class clazz = bean.getClass();
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                Method method = (MethodUtils.getAccessibleMethod(clazz, descriptors[i].getReadMethod()));
                if (method != null) {
                	Class<?> returnClazz = method.getReturnType();
                	if(excludes.contains(name) 
                			|| returnClazz.isAssignableFrom(List.class) 
                			|| returnClazz.isAssignableFrom(Set.class)
                			|| IdEntity.class.isAssignableFrom(returnClazz)){
                		//
                		continue;
                	}
                    description.put(name, getProperty(bean, name));
                }
            }
        return (description);
    }
}
