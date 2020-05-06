package com.fsun.service;

import com.fsun.domain.condition.SysPowerCondition;
import com.fsun.domain.model.SysPower;
import com.fsun.domain.model.SysUser;

/**
 * 权限管理
 * @ClassName: SysPowerApi 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月20日 下午1:15:24 
 *
 */
public interface SysPowerApi extends BaseApi<SysPower, SysPowerCondition>{

	/**
	 * 
	 * @Title: unique 
	 * @Description: 判别唯一性
	 * @param @param condition
	 * @param @return 
	 * @return boolean
	 */
	public boolean unique(SysPowerCondition condition);

	/**
	 * 
	 * @Title: changeStatus 
	 * @Description: 启用禁用 
	 * @param @param split
	 * @param @param enabled
	 * @param @param currentUser 
	 * @return void
	 */
	public void changeStatus(String[] split, Boolean enabled, SysUser currentUser);

	/**
	 * 批量配置模块
	 * @param menuId
	 * @param ids
	 * @param currentUser
	 */
	public void configModule(String menuId, String ids, SysUser currentUser);

}
