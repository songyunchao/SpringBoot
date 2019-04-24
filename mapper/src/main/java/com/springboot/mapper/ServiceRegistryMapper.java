package com.springboot.mapper;


import com.spring.domain.ServiceRegistry;
import com.spring.domain.ServiceRegistryWithBLOBs;

public interface ServiceRegistryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServiceRegistryWithBLOBs record);

    int insertSelective(ServiceRegistryWithBLOBs record);

    ServiceRegistryWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServiceRegistryWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ServiceRegistryWithBLOBs record);

    int updateByPrimaryKey(ServiceRegistry record);
}