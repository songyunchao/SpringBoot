package com.springboot.mapper;


import com.spring.domain.SysPower;

public interface SysPowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPower record);

    int insertSelective(SysPower record);

    SysPower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPower record);

    int updateByPrimaryKeyWithBLOBs(SysPower record);

    int updateByPrimaryKey(SysPower record);
}