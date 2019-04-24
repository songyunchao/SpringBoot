package com.springboot.mapper;


import com.spring.domain.SysDictionary;

public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    SysDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
}