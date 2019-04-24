package com.springboot.controller;

import com.spring.domain.SysUser;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("User")
public class UserController
{
    @Autowired
    private UserService userService;
        @RequestMapping("/getUserById")
        public  String getUserByPrimaryKey(String id){
           SysUser user = userService.selectByPrimaryKey(id);
            System.out.println(user.toString());
            return user.toString();
        }
}
