package com.fsun.domain.common;

/**
 * 排序对象
 * @author sunxiaolei
 *
 */
public class SortCondition {

	/**
	 * 用于字段排序
	 */
	private String sort;
	
	/*
	 * asc ,desc
	 */
	private String order;
	
	/**
	 * 表格第一列元素
	 */
	private String firstColumn;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the firstColumn
	 */
	public String getFirstColumn() {
		return firstColumn;
	}

	/**
	 * @param firstColumn the firstColumn to set
	 */
	public void setFirstColumn(String firstColumn) {
		this.firstColumn = firstColumn;
	}
	
}
