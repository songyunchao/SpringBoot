package com.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.spring.domain.Users;

import java.util.List;

@Mapper
public interface UsersMapper
{
    List<Users> getUsersList();

    void saveUser(Users users);

    Users getUserById(int id);
}
