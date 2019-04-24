package com.springboot.mapper;


import com.spring.domain.SysLog;
import com.spring.domain.SysLogWithBLOBs;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLogWithBLOBs record);

    int insertSelective(SysLogWithBLOBs record);

    SysLogWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

    int updateByPrimaryKey(SysLog record);
}