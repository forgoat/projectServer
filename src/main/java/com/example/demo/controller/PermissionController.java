package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.PermissionDo;
import com.example.demo.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequiresPermissions("permission:list")
    @GetMapping(value = "/list")
    public List<PermissionDo> listPermission(){
        return permissionService.listPermission();
    }

    @RequiresPermissions("permission:add")
    @PostMapping(value = "/add")
    public JSONObject addPermission(PermissionDo permissionDo){
        return permissionService.addPermission(permissionDo);
    }

    @GetMapping(value = "/list/user")
    public List<PermissionDo> getUserAllPermission(Long userId){
        return permissionService.getUserAllPermission(userId);
    }

    @GetMapping(value = "/list/user/project")
    public List<PermissionDo> getUserProjectPermission(Long userId,Long projectId){
        return permissionService.getUserProjectPermission(userId, projectId);
    }
}
