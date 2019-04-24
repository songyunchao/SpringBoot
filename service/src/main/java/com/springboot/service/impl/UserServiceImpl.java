package com.springboot.service.impl;

import com.spring.domain.SysUser;
import com.springboot.mapper.SysUserMapper;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectByPrimaryKey(String id)
    {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
