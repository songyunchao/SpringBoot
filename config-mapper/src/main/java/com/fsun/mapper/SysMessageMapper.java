package com.fsun.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysMessage;
import com.fsun.mapper.common.BaseMySqlMapper;

@Mapper
public interface SysMessageMapper extends BaseMySqlMapper<SysMessage>{
	
}