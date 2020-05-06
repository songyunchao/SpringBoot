package com.fsun.domain.common;

public class BaseCondition extends SortCondition{

	/**
	 * 关键字查询
	 */
	private String q;
	
	/**
	 * 逗号分隔的字符串
	 */
	private String ids;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
