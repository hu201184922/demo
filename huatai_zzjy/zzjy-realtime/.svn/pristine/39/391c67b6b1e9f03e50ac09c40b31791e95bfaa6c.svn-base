package com.fairyland.jdp.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;

public class BeanUtils {

    /**
     * <p>Return the entire set of properties for which the specified bean
     * provides a read method.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param bean Bean whose properties are to be extracted
     * @return Map of property descriptors
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @exception NoSuchMethodException if an accessor method for this
     *  property cannot be found
     * @see BeanUtilsBean#describe 
     */
    public static Map describeExcludeAnd(Object bean,String... excludes)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
    	Set<String> excludeSet = null;
    	if(excludes != null){
    		excludeSet = new HashSet<String>(Arrays.asList(excludes));
    	}else{
    		excludeSet = Collections.emptySet();
    	}
        return FBeanUtilsBean.getFInstance().describeExcludeAnd(bean,excludeSet);
    }
}
