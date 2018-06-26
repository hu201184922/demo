package com.tools.project.pojo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Pager<T> {
	// 最大条数
	private int totalCount = 0;
	// 每页显示的条数
	private int pageSize = 0;
	// 最大页数
	private int totalPage = 1;
	// 当前页码
	private int currentPage = 1;
	private int first = 0;

	private String orderBy;
	private Order order = Order.asc;

	public Pager() {
		this.pageSize = 15;
	}

	public Pager(Integer start, Integer limit) {
		this.currentPage = start;
		this.pageSize = limit;
	}

	public Pager(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取总页码
	 * 
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置总页数
	 * 
	 * @param totalPages
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage == 0 ? 1 : totalPage;
	}

	/**
	 * 获取每页显示的条数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置显示的页码 注意是页码
	 * 
	 * @param pageNumber
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		int totalPages = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.setTotalPage(totalPages);
		if (currentPage >= totalPages) {
			setCurrentPage(totalPages);
			setFirst((totalPages - 1) * pageSize);
		} else if (currentPage <= 0) {
			setCurrentPage(1);
			setFirst(first);
		} else {
			setFirst((currentPage - 1) * pageSize);
		}
	}

	/**
	 * 返回翻页开始位置
	 * 
	 * @param MaxCount
	 * @return
	 */
	public void setFirst(int first) {
		this.first = first;
	}

	public int getFirst() {
		return first;
	}

	/**
	 * 设置每页显示数据的条数
	 * 
	 * @param showScalar
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前显示的页码
	 * 
	 * @param tableName
	 */
	public int getCurrentPage() {
		return currentPage <= 0 ? 1 : currentPage;
	}

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	private List<T> pageItems;

	public List<T> getPageItems() {
		return pageItems;
	}

	public void setPageItems(List<T> pageItems) {
		this.pageItems = pageItems;
	}

	public Pager<T> orderBy(String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setOrder(String order) {
		if ((!StringUtils.equals("desc", order)) && (!StringUtils.equals("asc", order))) {
			throw new IllegalArgumentException("排序方向" + order + "不是合法值");
		}
		this.order = Order.valueOf(Order.class, order);
	}

	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(this.orderBy)) && (!(order).equals(null));
	}

	public static enum Order {
		desc, asc
	}

	@Override
	public String toString() {
		return "{currentPage:" + currentPage + ", order:\"" + order + "\", orderBy:\"" + orderBy + "\", pageSize:"
				+ pageSize + ", totalCount:" + totalCount + ", totalPages:" + totalPage + "}";
	}
}