package com.fsun.service;

import com.fsun.domain.condition.SysMessageCondition;
import com.fsun.domain.model.SysMessage;
import com.fsun.domain.model.SysUser;

/**
 * 消息模块
 * @author fsun
 * @date 2020年6月6日
 */
public interface SysMessageApi extends BaseApi<SysMessage, SysMessageCondition>{

	/**
	 * 变更任务状态
	 * @param ids
	 * @param status
	 * @param currentUser
	 */
	public int changeStatus(String[] ids, Short status, SysUser currentUser);

}
