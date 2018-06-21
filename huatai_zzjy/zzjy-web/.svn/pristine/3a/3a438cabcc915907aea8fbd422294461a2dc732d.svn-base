package com.fairyland.jdp.orm.util.common;

import java.lang.reflect.Array;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.fairyland.jdp.framework.util.PropsUtil;
import com.fairyland.jdp.orm.Pager;
import com.fairyland.jdp.orm.util.reflect.ClassUtil;
import com.fairyland.jdp.orm.util.reflect.Property;




@SuppressWarnings("unchecked")
public class ObjectUtil {
	private static Logger log = LoggerFactory.getLogger(ObjectUtil.class);
    private static final ConcurrentMap<String, Comparator<?>> comparatorMap = new ConcurrentHashMap();

    public static List adjust(List list, String field, Object value, String seqField, int seqOffset) {
	List modifiedList = new ArrayList();
	if (seqOffset == 0) {
	    return modifiedList;
	}

	int m = -1;
	for (int i = 0; i < list.size(); i++) {
	    Object obj = list.get(i);
	    Object prop = ClassUtil.getValue(obj, field);
	    if (prop == null) {
		continue;
	    }
	    if (prop.equals(value)) {
		m = i;
		break;
	    }
	}

	Object target = list.remove(m);
	int newPos = m + seqOffset;
	if (newPos >= list.size()) {
	    newPos = list.size() - 1;
	}
	if (newPos < 0) {
	    newPos = 0;
	}
	list.add(newPos, target);
	for (int i = 0; i < list.size(); i++) {
	    Object obj = list.get(i);
	    setProperties(obj, seqField, new Integer(i));
	    modifiedList.add(obj);
	}
	return modifiedList;
    }

    @Deprecated
    public static <T> T clone(T o1) {
	if (o1 == null)
	    return null;
	try {
	    if ((o1 instanceof Number)) {
		return o1;
	    }
	    if ((o1 instanceof String)) {
		return o1;
	    }
	    return o1;
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

    }

    private static int compareField(Object o1, Object o2, String orderField) {
	Object f1 = ClassUtil.getValue(o1, orderField);
	Object f2 = ClassUtil.getValue(o2, orderField);
	Object[] ary = { f1, f2 };
	if (((f1 instanceof String)) && ((f2 instanceof String)))
	    Arrays.sort(ary, Collator.getInstance(Locale.CHINA));
	else {
	    Arrays.sort(ary);
	}
	if (ary[0].equals(f1)) {
	    return -1;
	}
	return 1;
    }

    public static <T, R> List<R> toFieldList(List<T> objs, String fieldName, List<R> returnObjs) {
	for (Object t : objs) {
	    returnObjs.add((R) ClassUtil.getValue(t, fieldName));
	}
	return returnObjs;
    }

    public static <T, R> R[] toFieldArray(T[] objs, String fieldName, R[] returnObjs) {
	if (returnObjs.length < objs.length) {
	    returnObjs = (R[]) ClassUtil.newInstance(returnObjs.getClass().getComponentType(), objs.length);
	}
	for (int i = objs.length - 1; i > -1; i--) {
	    returnObjs[i] = (R) ClassUtil.getValue(objs[i], fieldName);
	}
	return returnObjs;
    }

    public static <T> T merge(T[] arguments) {
	if (arguments.length < 2)
	    throw new RuntimeException("参数数量必须大于两位!");
	if (arguments.length == 2) {
	    if (ClassUtil.isPrimitiveType(arguments[0].getClass())) {
		throw new RuntimeException("基本数据类型不能使用该方法!");
	    }
	    Property[] properties = ClassUtil.getPropertys(arguments[0]);
	    for (Property property : properties) {
		if (!property.isRead())
		    continue;
		Property target = ClassUtil.getProperty(arguments[0], property.getName());
		if (!target.isWrite())
		    continue;
		if (isNull(property.getValue(arguments[1])))
		    continue;
		target.setValue(arguments[0], property.getValue(arguments[1]));
	    }
	} else {
	    for (int i = arguments.length - 1; i > 0; i++) {
		merge(new Object[] { arguments[(i - 1)], arguments[i] });
	    }
	}
	return arguments[0];
    }

    public static <T> T getMaxObject(Collection<T> c, String fieldName) {
	T maxObject = null;
	for (Iterator<T> iter = c.iterator(); iter.hasNext();) {
	    T element = iter.next();
	    if (maxObject == null) {
		maxObject = element;
	    } else {
		Object maxValue = ClassUtil.getValue(maxObject, fieldName);
		Object theValue = ClassUtil.getValue(element, fieldName);
		if (compareField(maxValue, theValue, fieldName) == 1) {
		    maxObject = element;
		}
	    }
	}
	return maxObject;
    }

    public static <T> T getMinObject(Collection<T> c, String fieldName) {
	T minObject = null;
	for (Iterator<T> iter = c.iterator(); iter.hasNext();) {
	    T element = iter.next();
	    if (minObject == null) {
		minObject = element;
	    } else {
		Object minValue = ClassUtil.getValue(minObject, fieldName);
		Object theValue = ClassUtil.getValue(element, fieldName);
		if (compareField(minValue, theValue, fieldName) == -1) {
		    minObject = element;
		}
	    }
	}
	return minObject;
    }

    public static <T> int indexOf(List<T> objs, String field, Object value) {
	for (int i = 0; i < objs.size(); i++) {
	    Object prop = ClassUtil.getValue(objs.get(i), field);
	    if (prop == null) {
		continue;
	    }
	    if (prop.equals(value)) {
		return i;
	    }
	}
	return -1;
    }

    public static <T> int indexOf(T[] objs, T o) {
	for (int i = 0; i < objs.length; i++) {
	    if (objs[i].equals(o))
		return i;
	}
	return -1;
    }

    public static <T> int indexOf(List<T> objs, T obj, String property) {
	for (int i = 0; i < objs.size(); i++) {
	    Object value = ClassUtil.getValue(objs.get(i), property);
	    if (isNull(value))
		continue;
	    if (value.equals(ClassUtil.getValue(obj, property))) {
		return i;
	    }
	}
	return -1;
    }

    public static <T> int indexOf(List<T> list, String field, String value, boolean ignoreCase) {
	for (int i = 0; i < list.size(); i++) {
	    Object obj = list.get(i);
	    Object prop = ClassUtil.getValue(obj, field);
	    if (prop == null) {
		continue;
	    }
	    if (ignoreCase ? value.equalsIgnoreCase(StringUtil.nullValue(prop)) : value.equals(prop)) {
		return i;
	    }
	}
	return -1;
    }

    public static void setProperties(Object obj, String fieldName, Object value) {
	try {
	    BeanUtil.setValue(obj, fieldName, value);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    public static <T> Collection<T> sort(Collection<T> collectoin, String orderField) {
	return sort(collectoin, orderField, "asc");
    }

    public static <T> Collection<T> sort(Collection<T> collectoin, String orderBy, String order) {
	List list = new ArrayList();
	if ((collectoin == null) || (collectoin.isEmpty())) {
	    return list;
	}
	String key = collectoin.iterator().next().getClass().toString().concat("|").concat(orderBy);
	if (!comparatorMap.containsKey(key)) {
	    final String _orderBy = orderBy;
	    comparatorMap.put(key, new Comparator<T>() {
		public int compare(Object o1, Object o2) {
		    return compareField(o1, o2, _orderBy);
		}
	    });
	}
	list.addAll(collectoin);
	Collections.sort(list, (Comparator) comparatorMap.get(key));
	if ("desc".equalsIgnoreCase(order)) {
	    Collections.reverse(list);
	}
	return list;
    }

    public static boolean isNull(Object object) {
	return object == null;
    }

    public static boolean isNotNull(Object object) {
	return !isNull(object);
    }

    public static boolean toBoolean(Object object) {
	if (!isNull(object)) {
	    try {
		return new Boolean(StringUtil.nullValue(object)).booleanValue();
	    } catch (Exception e) {
		log.error(e.getMessage());
		return false;
	    }
	}
	return false;
    }

    public static <T> T defaultValue(T source, T def) {
	return isNull(source) ? def : source;
    }

    public static Map<String, Object> toMap(Object data) {
	if (ClassUtil.isMap(data)) {
	    return (Map) data;
	}
	Map rootMap = new HashMap();
	Property[] properties = ClassUtil.getPropertys(data);
	for (Property property : properties) {
	    if (property.isRead())
		rootMap.put(property.getName(), property.getValue(data));
	}
	return rootMap;
    }

    public static <T> T[] join(T[] source, T item) {
	if (item == null) {
	    return source;
	}
	Object array = Array.newInstance(source.getClass().getComponentType(), source.length + 1);
	for (int i = 0; i < source.length; i++) {
	    Array.set(array, i, source[i]);
	}
	Array.set(array, source.length, item);
	return (T[]) array;
    }

    public static <T> void join(List<T> dest, List<T> orig) {
	join(dest, orig, null);
    }

    public static <T> void join(List<T> dest, List<T> orig, String property) {
	List news = new ArrayList();
	for (T o : orig) {
	    if ((isNotNull(property)) && (indexOf(dest, o, property) == -1))
		news.add(o);
	    else if (dest.indexOf(o) == -1) {
		news.add(o);
	    }
	}
	dest.addAll(news);
    }

    public static <T> T copy(T dest, Object orig, String[] excludeProperties) {
	return BeanUtil.copy(dest, orig, excludeProperties);
    }

    public static <T> Boolean exists(List<T> list, Object object) {
	for (Object t : list) {
	    if (t.getClass().isEnum()) {
		if (t.toString().equals(object))
		    return Boolean.valueOf(true);
	    } else if (t.equals(object)) {
		return Boolean.valueOf(true);
	    }
	}
	return Boolean.valueOf(false);
    }
    
    public static List<String> getPageArray(Pager<?> page, int pageNumber) {
		int curPage = new Integer(page.getCurrentPage());
		int totalPage = new Integer(page.getTotalPage());
		List arraylist = new ArrayList();
		for (int i = 1, size = 1; i <= totalPage
				&& size <= (pageNumber * 2 + 3); i++) {
			if (i == 1) {
				arraylist.add(new String(i + ""));
				size++;
			} else if ((curPage > totalPage - (pageNumber + 1) && i
					+ (pageNumber * 2 + 2) > totalPage)// 当前页在前5位
					|| (i > curPage - (pageNumber + 1) && i < curPage)) {
				arraylist.add(new String(i + ""));
				size++;
			} else if (i == curPage) {
				arraylist.add(new String(i + ""));
				size++;
			} else if (i > curPage && i < curPage + (pageNumber + 1)
					&& size < (pageNumber * 2 + 2)) {
				arraylist.add(new String(i + ""));
				size++;
			} else if (size > (pageNumber + 1) && size <= (pageNumber * 2 + 2)) {
				arraylist.add(new String(i + ""));
				size++;
			} else if (i == totalPage) {
				arraylist.add(new String(i + ""));
				size++;
			}
		}
		if (totalPage > 1 && !(NumberUtil.toInteger(arraylist.get(1)) == 2)) {
			arraylist.set(1, "...");
		}
		if (totalPage > 1
				&& !(NumberUtil.toInteger(arraylist.get(arraylist.size() - 2)) == (totalPage - 1))) {
			arraylist.set(arraylist.size() - 2, "...");
		}
		return arraylist;
	}
    
    @SuppressWarnings("rawtypes")
	public static Pager prasePager(Page page){
    	Pager pager = new Pager();
    	pager.setCurrentPage(page.getNumber()+1);
    	pager.setPageItems(page.getContent());
    	pager.setPageSize(page.getSize());
    	pager.setTotalCount((int) page.getTotalElements());
    	pager.setTotalPage(page.getTotalPages());
    	return pager;
    }
}