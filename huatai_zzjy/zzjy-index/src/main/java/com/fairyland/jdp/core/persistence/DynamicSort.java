/** 
 * CopyRright © 2013 上海复深蓝信息技术有限公司
 * Homepage：http://www.fulan.com.cn/                         
 * Project:Fairyland-JDP                                      
 * Module ID: framework   
 * Comments:                                         
 * JDK version used: JDK1.6                            
 */
package com.fairyland.jdp.core.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * @Description: TODO
 * @author codyzeng
 * @email zengyuekang@fulan.com.cn
 * @date 2013年9月6日 下午2:27:33
 * @version V1.0
 */
public class DynamicSort {

    public static Sort bySortFilter(final Collection<SortFilter> filters) {
	List<Order> orders = new ArrayList<Order>();
	for (SortFilter filter : filters) {
	    Order order = new Order(filter.direction, filter.fieldName);
	    orders.add(order);
	}
	return new Sort(orders);

    }

    public static Sort bySortString(final String sortString) {
	return bySortFilter(SortFilter.parse(sortString).values());
    }

    public static Sort bySortStringAndFilter(final String sortString,
	    final Collection<SortFilter> filters) {
	filters.addAll(SortFilter.parse(sortString).values());
	return bySortFilter(filters);
    }
}
