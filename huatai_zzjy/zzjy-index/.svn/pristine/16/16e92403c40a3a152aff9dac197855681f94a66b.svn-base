package com.fairyland.jdp.orm.util.reflect;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.fairyland.jdp.orm.util.common.JavassistUtil;
import com.fairyland.jdp.orm.util.common.ObjectUtil;
import com.fairyland.jdp.orm.util.regexp.RegexpUtil;



@SuppressWarnings("unchecked")
public class ClassUtil {
    private static final Logger logger = Logger.getLogger(ClassUtil.class);
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<Class<?>, Class<?>>();
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap<Class<?>, Class<?>>();
    public static IClassFactory classFactory;
    private static final ConcurrentHashMap<Class<?>, BeanInfo> beanInfoCache;

    static {
	primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
	primitiveWrapperMap.put(Byte.TYPE, Byte.class);
	primitiveWrapperMap.put(Character.TYPE, Character.class);
	primitiveWrapperMap.put(Short.TYPE, Short.class);
	primitiveWrapperMap.put(Integer.TYPE, Integer.class);
	primitiveWrapperMap.put(Long.TYPE, Long.class);
	primitiveWrapperMap.put(Double.TYPE, Double.class);
	primitiveWrapperMap.put(Float.TYPE, Float.class);
	primitiveWrapperMap.put(Void.TYPE, Void.TYPE);

	for (Class<?> primitiveClass : primitiveWrapperMap.keySet()) {
	    Class<?> wrapperClass = (Class<?>) primitiveWrapperMap.get(primitiveClass);
	    if (!primitiveClass.equals(wrapperClass)) {
		wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
	    }

	}

	classFactory = ClassFactory.getFastClassFactory();
	beanInfoCache = new ConcurrentHashMap<Class<?>, BeanInfo>();
    }

    public static void loadClass(Class<?> clazz) {
	classFactory.getClass(getRealClass(clazz));
    }

    public static BeanInfo getBeanInfo(Class<?> clazz) {
	synchronized (beanInfoCache) {
	    BeanInfo beanInfo = (BeanInfo) beanInfoCache.get(clazz);
	    if (beanInfo == null) {
		try {
		    beanInfo = Introspector.getBeanInfo(clazz, Object.class);
		} catch (Exception e) {
		    logger.error(e);
		}
		beanInfoCache.put(clazz, beanInfo);
	    }
	    return beanInfo;
	}
    }

    public static <T> T newInstance(Class<T> clazz) {
	try {
	    return classFactory.getClass(getRealClass(clazz)).newInstance();
	} catch (Exception e) {
	    logger.error("创建类:" + clazz.getName() + "\t时出现异常!", e);
	}
	return null;
    }

    public static <T> Class<T> getRealClass(T target) {
	if ((target instanceof Class<?>)) {
	    return (Class<T>) target;
	}
	return (Class<T>) getRealClass(target.getClass());
    }

    public static <T> Class<T> getRealClass(Class<T> clazz) {
	if ((clazz != null) && (clazz.getName().contains("$$"))) {
	    Class superClass = clazz.getSuperclass();
	    if ((superClass != null) && (!Object.class.equals(superClass))) {
		return superClass;
	    }
	}
	return clazz;
    }

    public static Object newInstance(Class<? extends Object> clazz, Object retVal) {
	return classFactory.getClass(getRealClass(clazz)).newInstance(retVal);
    }

    public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] parameters) {
	return classFactory.getClass(getRealClass(clazz)).newInstance(parameterTypes, parameters);
    }

    public static Object newInstance(String className) {
	try {
	    return newInstance(getRealClass(Class.forName(className)));
	} catch (ClassNotFoundException e) {
	    logger.error(e);
	}
	return null;
    }

    public static Property[] getPropertys(Object target) {
	return getPropertys(target.getClass());
    }

    public static Property[] getPropertys(Class<?> clazz) {
	return classFactory.getClass(getRealClass(clazz)).getPropertys();
    }

    public static Property getProperty(Object target, String name) {
	return getProperty(getRealClass(target), name);
    }

    public static Property getProperty(Class<?> clazz, String name) {
	return classFactory.getClass(getRealClass(clazz)).getProperty(name);
    }

    public static Object getValue(Object target, String name) {
	Property property = getProperty(target, name);
	if ((property != null) && (property.isRead())) {
	    return property.getValue(target);
	}
	return classFactory.getClass(getRealClass(target.getClass())).getValue(target, name);
    }

    public static Field getDeclaredField(Class<?> classes, String fieldName) {
	for (Class superClass = classes; superClass != Object.class;) {
	    try {
		return superClass.getDeclaredField(fieldName);
	    } catch (NoSuchFieldException localNoSuchFieldException) {
		superClass = superClass.getSuperclass();
	    }

	}

	return null;
    }

    public static Field[] getDeclaredFields(Class<?> clazz, Class<? extends Annotation> annotClass) {
	List fields = new ArrayList();
	for (Property property : getPropertys(clazz)) {
	    Field field = getDeclaredField(clazz, property.getName());
	    if ((ObjectUtil.isNotNull(field)) && (ObjectUtil.isNotNull(field.getAnnotation(annotClass)))) {
		fields.add(field);
	    }
	}
	return (Field[]) fields.toArray(new Field[fields.size()]);
    }

    public static void setValue(Object target, String name, Object value) {
	Property property = getProperty(target, name);
	if ((property != null) && (property.isWrite()))
	    property.setValue(target, value);
	else
	    classFactory.getClass(getRealClass(target.getClass())).setValue(target, name, value);
    }

    public static MethodProxy getMethod(Class<?> clazz, String method) {
	try {
	    return classFactory.getClass(getRealClass(clazz)).getMethod(method);
	} catch (Exception e) {
	    logger.error(clazz + "." + method + "-" + e.getMessage());
	}
	return null;
    }

    public static MethodProxy getMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
	return classFactory.getClass(getRealClass(clazz)).getMethod(methodName, parameterTypes);
    }

    public static boolean isBasicType(Field field) {
	Class type = getWrappedType(field);
	return isPrimitiveType(type);
    }

    public static boolean isBasicType(Class<?> type) {
	type = getWrappedType(type);
	return (wrapperPrimitiveMap.containsKey(type)) || (Date.class.isAssignableFrom(type)) || (String.class.isAssignableFrom(type)) || (type.isEnum());
    }

    public static boolean isPrimitiveType(Class<?> type) {
	return primitiveWrapperMap.containsKey(type);
    }

    public static boolean isPrimitiveType(Object object) {
	return isPrimitiveType(object.getClass());
    }

    public static Class<?> getWrappedType(Field field) {
	Class type = field.getType();
	return getWrappedType(type);
    }

    public static Class<?> getWrappedType(Class<?> type) {
	if (primitiveWrapperMap.containsKey(type))
	    return (Class) primitiveWrapperMap.get(type);
	return type;
    }

    public static boolean isNumber(Class<?> type) {
	type = getWrappedType(type);

	return (Integer.class.isAssignableFrom(type)) || (Float.class.isAssignableFrom(type)) || (Long.class.isAssignableFrom(type)) || (Character.class.isAssignableFrom(type)) || (Double.class.isAssignableFrom(type));
    }

    public static Object newInstance(Class<?> componentType, int length) {
	return Array.newInstance(componentType, length);
    }

    public static boolean isArray(Field field) {
	return isArray(field.getType());
    }

    public static boolean isArray(Object object) {
	return (object != null) && (isArray(object.getClass()));
    }

    public static boolean isArray(Class<?> clazz) {
	return clazz.isArray();
    }

    public static boolean isInterface(Field field) {
	if ((isMap(field)) || (isList(field)))
	    return false;
	if (isArray(field)) {
	    return field.getType().getComponentType().isInterface();
	}
	return isInterface(field.getType());
    }

    public static boolean isList(Field field) {
	return field.getType() == List.class;
    }

    public static boolean isMap(Field field) {
	return field.getType() == Map.class;
    }

    public static boolean isList(Object obj) {
	return obj instanceof List;
    }

    public static boolean isList(Class<?> clazz) {
	return List.class.isAssignableFrom(clazz);
    }

    public static boolean isMap(Object obj) {
	return obj instanceof Map;
    }

    public static boolean isInterface(Class<?> clazz) {
	return clazz.isInterface();
    }

    public static boolean isLong(Class<?> clazz) {
	return Long.class.isAssignableFrom(getWrappedType(clazz));
    }

    public static boolean isString(Class<?> clazz) {
	return String.class.isAssignableFrom(getWrappedType(clazz));
    }

    public static Class getSuperClassGenricType(Class clazz, int index) {
	Type genType = clazz.getGenericSuperclass();

	if (!(genType instanceof ParameterizedType)) {
	    return Object.class;
	}

	Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
	if ((index >= params.length) || (index < 0)) {
	    throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
	}
	if (!(params[index] instanceof Class)) {
	    return Object.class;
	}
	return (Class) params[index];
    }

    public static Class getSuperClassGenricType(Class clazz) {
	return getSuperClassGenricType(clazz, 0);
    }

    public static Class getMethodGenericReturnType(Method method, int index) {
	Type returnType = method.getGenericReturnType();
	if ((returnType instanceof ParameterizedType)) {
	    ParameterizedType type = (ParameterizedType) returnType;
	    Type[] typeArguments = type.getActualTypeArguments();
	    if ((index >= typeArguments.length) || (index < 0)) {
		throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
	    }
	    return (Class) typeArguments[index];
	}
	return Object.class;
    }

    public static Class getMethodGenericReturnType(Method method) {
	return getMethodGenericReturnType(method, 0);
    }

    public static List<Class> getMethodGenericParameterTypes(Method method, int index) {
	List results = new ArrayList();
	Type[] genericParameterTypes = method.getGenericParameterTypes();
	if ((index >= genericParameterTypes.length) || (index < 0)) {
	    throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
	}
	Type genericParameterType = genericParameterTypes[index];
	if ((genericParameterType instanceof ParameterizedType)) {
	    ParameterizedType aType = (ParameterizedType) genericParameterType;
	    Type[] parameterArgTypes = aType.getActualTypeArguments();
	    for (Type parameterArgType : parameterArgTypes) {
		Class parameterArgClass = (Class) parameterArgType;
		results.add(parameterArgClass);
	    }
	    return results;
	}
	return results;
    }

    public static List<Class> getMethodGenericParameterTypes(Method method) {
	return getMethodGenericParameterTypes(method, 0);
    }

    public static Class<?> getFieldGenericType(Field field) {
	return getFieldGenericType(field, 0);
    }

    public static <T extends Annotation> T getClassGenricType(Class clazz, Class<T> annotClass) {
	return (T) clazz.getAnnotation(annotClass);
    }

    public static <T extends Annotation> T getFieldGenericType(Field field, Class<T> annotClass) {
	return field.getAnnotation(annotClass);
    }

    public static Class getFieldGenericType(Field field, int index) {
	Type genericFieldType = field.getGenericType();
	if ((genericFieldType instanceof ParameterizedType)) {
	    ParameterizedType aType = (ParameterizedType) genericFieldType;
	    Type[] fieldArgTypes = aType.getActualTypeArguments();
	    if ((index >= fieldArgTypes.length) || (index < 0)) {
		throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
	    }
	    return (Class) fieldArgTypes[index];
	}
	return Object.class;
    }

    public static String[] getParamNames(Class<?> clazz, String methodname, Class<?>[] parameterTypes) {
	return getParamNames(clazz.getName(), methodname, parameterTypes);
    }

    public static String[] getParamNames(String classname, String methodname, Class<?>[] parameterTypes) {
	try {
	    return JavassistUtil.getParamNames(classname, methodname, parameterTypes);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
	return new String[0];
    }

    public static Annotation getParamAnno(Method method) {
	return getParamAnnos(method, 0, 0);
    }

    public static Annotation getParamAnnos(Method method, int i, int j) {
	return getParamAnnos(method, i)[j];
    }

    public static Annotation[] getParamAnnos(Method method, int i) {
	Annotation[][] annotations = method.getParameterAnnotations();
	return annotations[i];
    }

    public static Annotation[] getMethodAnnos(Method method) {
	return method.getAnnotations();
    }

    public static Annotation getMethodAnno(Method method, Class<? extends Annotation> clazz) {
	if (method.isAnnotationPresent(clazz)) {
	    return method.getAnnotation(clazz);
	}
	return null;
    }

    public static Class<?> forName(String className) {
	try {
	    return Class.forName(RegexpUtil.replace(className, "\\$[\\S\\s]+", ""));
	} catch (ClassNotFoundException e) {
	    logger.error(e.getMessage());
	}
	return null;
    }

    public static Annotation getMethodAnnoByStackTrace(Class<? extends Annotation> annotClass) {
	long start = System.currentTimeMillis();
	StackTraceElement[] stacks = new Throwable().getStackTrace();
	for (StackTraceElement stack : stacks) {
	    Class clasz = forName(stack.getClassName());
	    if ((ObjectUtil.isNotNull(clasz)) && (!ClassUtil.class.isAssignableFrom(clasz))) {
		MethodProxy methodProxy = getMethod(clasz, stack.getMethodName());
		if (ObjectUtil.isNotNull(methodProxy)) {
		    Annotation annotation = getMethodAnno(methodProxy.getMethod(), annotClass);
		    if (ObjectUtil.isNotNull(annotation)) {
			logger.error("找到" + annotation + "耗时：" + (System.currentTimeMillis() - start) + "ms");
			return annotation;
		    }
		}
	    }
	}
	logger.error("未找到耗时：" + (System.currentTimeMillis() - start) + "ms");
	return null;
    }

    public static <T extends Annotation> T getClassAnnoByStackTrace(Class<T> annotClass) {
	StackTraceElement[] stacks = new Throwable().getStackTrace();
	for (StackTraceElement stack : stacks) {
	    Class clasz = forName(stack.getClassName());
	    if (ObjectUtil.isNull(clasz))
		continue;
	    Annotation annot = clasz.getAnnotation(annotClass);
	    if (ObjectUtil.isNotNull(annot)) {
		return (T) annot;
	    }
	}
	return null;
    }

}