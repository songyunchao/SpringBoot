package com.fsun.service;

import java.util.List;

import com.fsun.domain.condition.SysMenuCondition;
import com.fsun.domain.dto.MenuTreeDto;
import com.fsun.domain.model.SysMenu;
import com.fsun.domain.model.SysUser;

/**
 * 
 * @ClassName: SysMenuApi 
 * @Description: 菜单模块接口
 * @author fsun 
 * @date 2019年6月23日 下午4:18:00 
 *
 */
public interface SysMenuApi extends BaseApi<SysMenu, SysMenuCondition>{

	/**
	 * 
	 * @Title: unique 
	 * @Description: 判别唯一性
	 * @param @param condition
	 * @param @return 
	 * @return boolean
	 */
	public boolean unique(SysMenuCondition condition);

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
	 * 根据父节点获取对应的菜单树
	 * @param pid
	 * @return
	 */
	public List<MenuTreeDto> findMenuTree(String pid);

	/**
	 * 根据当前用户获取对应的菜单树
	 * @param id
	 * @return
	 */
	public List<MenuTreeDto> findModulesByUser(String username);
	
}
