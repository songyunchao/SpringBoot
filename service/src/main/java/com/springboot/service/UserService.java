package com.springboot.service;

import com.spring.domain.SysUser;

public interface UserService
{
    SysUser selectByPrimaryKey(String id);
}
