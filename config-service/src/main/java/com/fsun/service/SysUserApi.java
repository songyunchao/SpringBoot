package com.fsun.service;

import com.fsun.domain.condition.SysUserCondition;
import com.fsun.domain.model.SysUser;

/**
 * 用户管理接口
 * @ClassName: SysUserApi 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午3:31:51 
 *
 */
public interface SysUserApi extends BaseApi<SysUser, SysUserCondition>{
 
	/**
	 * 判别唯一性
	 * @Title: unique 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return boolean
	 */
	public boolean unique(SysUserCondition condition);

	/**
	 * 启用禁用
	 * @Title: changeStatus 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param split
	 * @param @param enabled
	 * @param @param currentUser 
	 * @return void
	 */
	public void changeStatus(String[] userIds, Boolean enabled, SysUser currentUser);
}
