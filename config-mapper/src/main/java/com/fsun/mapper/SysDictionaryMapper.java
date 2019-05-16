package com.fsun.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysDictionary;
import com.fsun.mapper.common.BaseMySqlMapper;

@Mapper
public interface SysDictionaryMapper extends BaseMySqlMapper<SysDictionary>{
}