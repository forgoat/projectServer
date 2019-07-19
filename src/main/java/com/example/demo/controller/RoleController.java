package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.PermissionDo;
import com.example.demo.domain.RoleDo;
import com.example.demo.domain.UserRoleDo;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping(value = "/list")
    public List<RoleDo> listRole(){
        return roleService.listRole();
    }

    @GetMapping(value = "/list/project")
    public List<RoleDo> listProjectRole(){
        return roleService.listRoleByProject();
    }
    @PostMapping(value = "/addUserRole")
    public JSONObject addUserRole(UserRoleDo userRoleDo){
        return roleService.addUserRole(userRoleDo);
    }

    @GetMapping(value = "/project")
    public List<RoleDo> findByProjectId(Long projectId){
        return roleService.findByProjectId(projectId);
    }

    @GetMapping(value = "/permission/list")
    public List<PermissionDo> getRolePermission(Long roleId){
        return permissionService.findByRoleId(roleId);
    }

    @GetMapping(value = "/list/user")
    public List<RoleDo> getUserRole(Long userId){
        return roleService.findByUserId(userId);
    }
}
