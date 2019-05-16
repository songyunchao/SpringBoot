package com.fsun.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.ServiceRegistry;
import com.fsun.mapper.common.BaseMySqlMapper;

@Mapper
public interface ServiceRegistryMapper extends BaseMySqlMapper<ServiceRegistry>{
	
}