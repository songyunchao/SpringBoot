package com.fsun.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsun.domain.common.HttpResult;
import com.fsun.domain.condition.SysCalendarCondition;
import com.fsun.domain.model.SysCalendar;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.service.SysCalendarApi;
import com.fsun.web.controller.base.BaseController;

@RestController
@RequestMapping("sys/calendar")
public class CalendarController extends BaseController {
	
	@Autowired
	private SysCalendarApi sysCalendarApi;
	
	/**
	 * 列表条件查询操作
	 * @Title: list 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HttpResult list(SysCalendarCondition condition) {
		try {
			List<SysCalendar> list = sysCalendarApi.list(condition);
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}	
	
	/**
	 * 列表条件查询操作
	 * @Title: list 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/syn", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HttpResult synCalendar(String beginYear) {
		try {			
			return success(sysCalendarApi.synCalendar(beginYear));
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}	
	
}

