package com.springboot.mapper;


import com.spring.domain.SysSettings;

public interface SysSettingsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysSettings record);

    int insertSelective(SysSettings record);

    SysSettings selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysSettings record);

    int updateByPrimaryKey(SysSettings record);
}