package com.fsun.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.BusShop;
import com.fsun.mapper.common.BaseMySqlMapper;

@Mapper
public interface BusShopMapper extends BaseMySqlMapper<BusShop>{
    
}