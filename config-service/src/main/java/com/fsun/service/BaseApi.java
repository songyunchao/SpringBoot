package com.fsun.service;

import java.util.List;

import com.fsun.domain.common.PageModel;
import com.fsun.domain.model.SysUser;

/**
 * 接口基类
 * @author sunxiaolei
 *
 */
public interface BaseApi<T, C> {

	/**
	 * 根据主键获取单条数据
	 *
	 * @param id
	 * @return
	 */
	public T load(String id);

	/**
	 * 查询列表数据
	 *
	 * @param condition
	 * @return
	 */
	public List<T> list(C condition);

	/**
	 * 查询分页数据
	 * @param condition
	 * @return
	 */
	public PageModel findPage(C condition) ;
	
	/**
	 * 插入或更新对象
	 * @param domain
	 * @param currentUser
	 * @return 返回主键
	 */
	public String save(T domain, SysUser currentUser);
	
	/**
	 * 删除数据
	 * @param entity
	 */
	public int delete(String id);

}
