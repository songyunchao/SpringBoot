package com.springboot.service.impl;


import com.spring.domain.Users;
import com.springboot.mapper.UsersMapper;
import com.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService
{
    @Autowired
    private UsersMapper usersMapper;

    @Override
    @Cacheable(value = "users")
 //   @CustomAnnotation
    public List<Users> getUsersList()
    {
        return usersMapper.getUsersList();
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void saveUser(Users users)
    {
        usersMapper.saveUser(users);
    }

    @Override
    @Cacheable(value="users")
    public Users getUserById(int id)
    {
        return usersMapper.getUserById(id);
    }
}
