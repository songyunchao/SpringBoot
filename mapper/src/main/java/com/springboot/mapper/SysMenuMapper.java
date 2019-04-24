package com.springboot.mapper;


import com.spring.domain.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKeyWithBLOBs(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}