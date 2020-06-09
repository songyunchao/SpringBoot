package com.fsun.service;

import java.util.Collection;
import java.util.List;

import com.fsun.domain.condition.SysUserCondition;
import com.fsun.domain.model.SysRole;
import com.fsun.domain.model.SysUser;

/**
 * 用户管理接口
 * @ClassName: SysUserApi 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午3:31:51 
 *
 */
public interface SysUserApi extends BaseBusApi<SysUser, SysUserCondition>{
 
	/**
	 * @Title: unique 
	 * @Description: 判别唯一性
	 * @param @param condition
	 * @param @return 
	 * @return boolean
	 */
	public boolean unique(SysUserCondition condition);

	/**
	 * @Title: changeStatus 
	 * @Description: 启用禁用
	 * @param @param split
	 * @param @param enabled
	 * @param @param currentUser 
	 * @return void
	 */
	public void changeStatus(String[] userIds, Boolean enabled, SysUser currentUser);

	/**
	 * 
	 * @Title: findRolesByUsername 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param username
	 * @param @return 
	 * @return List<SysRole>
	 */
	public List<SysRole> findRolesByUsername(String username);

	/**
	 * 
	 * @Title: findPermissionsByUsername 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param username
	 * @param @return 
	 * @return Collection<? extends String>
	 */
	public Collection<? extends String> findPermissionsByUsername(String username);

	/**
	 * 批量关联门店
	 * @param shopId
	 * @param ids
	 * @param currentUser
	 */
	public void configShop(String shopId, String ids, SysUser currentUser);

}
