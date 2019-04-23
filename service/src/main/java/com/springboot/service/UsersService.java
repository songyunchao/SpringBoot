package com.springboot.service;


import com.spring.domain.Users;

import java.util.List;

public interface UsersService
{
    List<Users> getUsersList();

    void saveUser(Users users);

    Users  getUserById(int id);
}
