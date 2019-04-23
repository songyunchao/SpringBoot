package com.springboot.controller;

import org.springframework.ui.Model;
import com.spring.domain.Users;
import com.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("Users")
public class UsersController
{
    @Autowired
    private UsersService usersService;

    @RequestMapping("/getUsersList")
    @ResponseBody
    public String getUsersList(Model model){
        List<Users> usersList = usersService.getUsersList();
//        model.addAttribute("List",usersList);
        return usersList.toString();
    }



}
