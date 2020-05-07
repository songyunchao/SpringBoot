package com.fsun.service;

import com.fsun.domain.condition.BusShopCondition;
import com.fsun.domain.model.BusShop;
import com.fsun.domain.model.SysUser;

/**
 * 
 * @author fsun
 * @date 2020年5月7日
 */
public interface BusShopApi extends BaseApi<BusShop, BusShopCondition>{

	/**
	 * 
	 * @param condition
	 * @return
	 */
	public boolean unique(BusShopCondition condition);

	/**
	 * 
	 * @param split
	 * @param enabled
	 * @param currentUser
	 */
	public void changeStatus(String[] ids, Boolean enabled, SysUser currentUser);

}
