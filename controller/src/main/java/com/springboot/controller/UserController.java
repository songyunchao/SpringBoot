package com.springboot.controller;

import com.spring.domain.SysUser;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User")
public class UserController
{
    @Autowired
    private UserService userService;
        @RequestMapping("/getUserById")
        public  String getUserByPrimaryKey(@RequestParam("userid") String userid){
           SysUser user = userService.selectByPrimaryKey(userid);
            System.out.println(user.toString());
            return user.toString(); 
        }
}
