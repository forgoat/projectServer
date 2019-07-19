package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import com.example.demo.service.PermissionService;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserService userService;


    @Override
    public List<PermissionDo> listPermission() {
        return permissionDao.listPermission();
    }

    @Override
    public JSONObject getUserPermission(Long userId) {
        JSONObject userPermission=new JSONObject();
        UserDo userDo=userDao.findById(userId).get(0);
        userPermission.put("nickname",userDo.getNickname());
        Set<String> permissionList=new HashSet<String>();
        Set<String> menuList=new HashSet<String>();
        List<PermissionDo> permissionDos=getUserAllPermission(userId);
        if (userService.isAdmin(userId)){
            String roleName="超级管理员";
            userPermission.put("roleName",roleName);
        }
        else {
            userPermission.put("roleName","other");
        }
        for (PermissionDo p:permissionDos){
            permissionList.add(p.getPermissionCode());
            boolean hasMenu=false;
            for (String m:menuList){
                if (p.getMenuCode().equals(m)){
                    hasMenu=true;
                    break;
                }
            }
            menuList.add(p.getMenuCode());
        }
        userPermission.put("permissionList",permissionList);
        userPermission.put("menuList",menuList);
        return userPermission;
    }

    @Override
    public JSONObject addPermission(PermissionDo permissionDo) {
        permissionDao.addPermission(permissionDo);
        return CommonUtil.successJson();
    }

    @Override
    public List<PermissionDo> findByRoleId(Long roleId) {
        List<RolePermissionDo> rolePermissionDoList = rolePermissionDao.findByRoleId(roleId);
        List<PermissionDo> permissionDoList = new ArrayList<PermissionDo>();
        if (rolePermissionDoList != null) {
            for (RolePermissionDo rolePermissionDo : rolePermissionDoList) {
                PermissionDo permissionDo = permissionDao.findById(rolePermissionDo.getPermissionId()).get(0);
                permissionDoList.add(permissionDo);
            }
            return permissionDoList;
        }
        return null;
    }

    @Override
    public List<PermissionDo> getUserAllPermission(Long userId) {
        List<UserRoleDo> userRoleDoList = userRoleDao.findByUserId(userId);
        List<RoleDo> roleDoList = new ArrayList<RoleDo>();
        if (userRoleDoList != null) {
            for (UserRoleDo userRoleDo : userRoleDoList) {
                RoleDo roleDo = roleDao.findById(userRoleDo.getRoleId()).get(0);
                roleDoList.add(roleDo);
            }
        }
        List<PermissionDo> permissionDoList = new ArrayList<PermissionDo>();
        if (roleDoList != null) {
            for (RoleDo roleDo : roleDoList) {
                List<PermissionDo> permissionDos = findByRoleId(roleDo.getId());
                for (PermissionDo p : permissionDos) {
                    Boolean hasPerm = false;
                    for (PermissionDo p0 : permissionDoList) {
                        if (p0.getId().equals(p.getId())) {
                            hasPerm = true;
                            break;
                        }
                    }
                    if (hasPerm == false) {
                        permissionDoList.add(p);
                    }
                }
            }
            return permissionDoList;
        }
        return null;
    }

    @Override
    public JSONObject getProjectPermission(Long userId, Long projectId) {
        System.out.println("enter getPP, the uId, the PId "+userId+" "+projectId);
        JSONObject jsonObject=new JSONObject();
        UserDo userDo=userDao.findById(userId).get(0);
        jsonObject.put("nickname",userDo.getNickname());
        jsonObject.put("roleName","");
        List<PermissionDo> permissionDos=getUserProjectPermission(userId, projectId);
        if (permissionDos!=null) {
            Set<String> permissionList = new HashSet<String>();
            Set<String> menuList = new HashSet<String>();
            for (PermissionDo permissionDo : permissionDos) {
                boolean hasPerm = false;
                boolean hasMenu = false;
                for (String p : permissionList) {
                    if (p.equals(permissionDo.getPermissionCode())) {
                        hasPerm = true;
                        break;
                    }
                }
                for (String m : menuList) {
                    if (m.equals(permissionDo.getMenuCode())) {
                        hasPerm = true;
                        break;
                    }
                }
                if (hasPerm == false) {
                    permissionList.add(permissionDo.getPermissionCode());
                }
                if (hasMenu == false) {
                    menuList.add(permissionDo.getMenuCode());
                }
            }
            jsonObject.put("permissionList", permissionList);
            jsonObject.put("menuList", menuList);
            return jsonObject;
        }
        else {
            System.out.println("permissionList is null");
            return null;
        }
    }

    @Override
    public List<PermissionDo> getUserProjectPermission(Long userId, Long projectId) {
        List<UserRoleDo> userRoleDoList=userRoleDao.findByUserId(userId);
        List<RoleDo> roleDoList=new ArrayList<RoleDo>();
        if (userRoleDoList!=null){
            for (UserRoleDo userRoleDo:userRoleDoList){
                RoleDo roleDo=roleDao.findById(userRoleDo.getRoleId()).get(0);
                roleDoList.add(roleDo);
            }
        }
        List<PermissionDo> permissionDoList=new ArrayList<PermissionDo>();
        if (roleDoList!=null){
            for (RoleDo roleDo:roleDoList){
                if (roleDo.getProjectId().equals(projectId)){
                    List<PermissionDo> permissionDos=findByRoleId(roleDo.getId());
                    for(PermissionDo p:permissionDos){
                        Boolean hasPerm=false;
                        for(PermissionDo p0:permissionDoList){
                            if (p0.getId().equals(p.getId())){
                                hasPerm=true;
                                break;
                            }
                        }
                        if (hasPerm==false){
                            permissionDoList.add(p);
                        }
                    }
                }
            }
            return permissionDoList;
        }
        return null;
    }
}
