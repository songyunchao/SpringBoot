package com.fsun.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysCalendar;
import com.fsun.mapper.common.BaseMySqlMapper;

/**
 * 系统日历
 * @author fsun
 * @date 2020年5月29日
 */
@Mapper
public interface SysCalendarMapper extends BaseMySqlMapper<SysCalendar>{
	
}