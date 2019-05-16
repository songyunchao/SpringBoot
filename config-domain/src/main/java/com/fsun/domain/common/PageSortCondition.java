package com.fsun.domain.common;

import java.util.List;

import com.github.pagehelper.Page;

/**
 * 分页排序查询条件
 * @author sunxiaolei
 *
 */
public class PageSortCondition extends SortCondition {

	/**
	 * 返回数据列表集合
	 */
	private List<?> rows;
	
	/**
     * 总记录数
     */
    private long total;
	
	/**
	 * 当前页
	 */
	private int pageNum;
	
	/**
	 * 每页显示记录数
	 */
    private int pageSize;
    
    public PageSortCondition(){}
    
    public PageSortCondition(List<?> rows) {
        if (rows instanceof Page) {
            Page<?> page = (Page<?>)rows;
            this.rows = page;
            this.total = page.getTotal();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
        }else{
        	this.rows = rows;
        }
    }
    
    public PageSortCondition(List<?> rows, List<?> realRows) {
        if (rows instanceof Page) {
            Page<?> page = (Page<?>)rows;
            this.rows = realRows;
            this.total = page.getTotal();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
        }else{
        	this.rows = realRows;
        }
    }
    
    public PageSortCondition(List<?> rows, long total, int pageNum, int pageSize) {     
        this.rows = rows;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;      
    }

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
