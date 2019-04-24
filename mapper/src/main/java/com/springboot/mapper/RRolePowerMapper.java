package com.springboot.mapper;


import com.spring.domain.RRolePower;

public interface RRolePowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(RRolePower record);

    int insertSelective(RRolePower record);

    RRolePower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RRolePower record);

    int updateByPrimaryKey(RRolePower record);
}