package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ProjectDo;
import com.example.demo.domain.UserDo;
import com.example.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequiresPermissions("user:list")
    @GetMapping(value = "/list")
    public List<UserDo> listUser(){
        return userService.listUser();
    }

    @RequiresPermissions("user:add")
    @PostMapping(value = "/add")
    public JSONObject addUser(UserDo userDo){
        return userService.addUser(userDo);
    }

    @GetMapping(value = "/admin")
    public Boolean isAdmin(Long userId){
        return userService.isAdmin(userId);
    }

    public List<ProjectDo> findProjectByUserId(Long userId){
        return null;
    }
}
