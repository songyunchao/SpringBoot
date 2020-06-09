package com.fsun.service;

import java.util.List;

import com.fsun.domain.condition.SysCalendarCondition;
import com.fsun.domain.model.SysCalendar;

/**
 * 
 * @author fsun
 * @date 2020年5月29日
 */
public interface SysCalendarApi extends BaseApi<SysCalendar, SysCalendarCondition>{

	/**
	 * 同步日期
	 * @param beginYear 开始年份
	 */
	public List<String> synCalendar(String beginYear);
	
}
