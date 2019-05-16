package com.fsun.domain.common;


import com.fsun.exception.enums.SCMErrorEnum;

/**
 * http请求json返回封装分页实体
 * @author sunxiaolei
 *
 */
public class PageResult extends HttpResult {

	private static final long serialVersionUID = -2429898845184400319L;

	/**
	 * 当前页
	 */
	private int pageNum;

	/**
	 * 每页显示记录数
	 */
	private int pageSize;

	/**
	 * 总记录数
	 */
	private long total;

	private Object rows;
	
	/**
	 * 表格合计列
	 */
	private Object footer;

	public PageResult success(int pageNum, int pageSize, long total,
			Object entry) {
		return this.success(pageNum, pageSize, total, entry,
				SCMErrorEnum.SUCCESS.getErrorCode(),
				SCMErrorEnum.SUCCESS.getErrorMsg());
	}
	
	public PageResult success(int pageNum, int pageSize, long total,
			Object entry, Object footer) {
		return this.success(pageNum, pageSize, total, entry, footer,
				SCMErrorEnum.SUCCESS.getErrorCode(),
				SCMErrorEnum.SUCCESS.getErrorMsg());
	}

	public PageResult success(int pageNum, int pageSize, long total,
			Object entry, int responseCode, String message) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = entry;
		return (PageResult) super.success(entry, responseCode, message);
	}
	
	public PageResult success(int pageNum, int pageSize, long total,
			Object entry, Object footer, int responseCode, String message) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = entry;
		this.footer = footer;
		return (PageResult) super.success(entry, responseCode, message);
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotal() {
		return total;
	}

	public Object getRows() {
		return rows;
	}

	/**
	 * @return the footer
	 */
	public Object getFooter() {
		return footer;
	}
}
