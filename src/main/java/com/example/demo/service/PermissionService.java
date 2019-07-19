package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.PermissionDo;


import java.util.List;

public interface PermissionService {
    public List<PermissionDo> listPermission();
    public JSONObject getUserPermission(Long userId);
    public JSONObject addPermission(PermissionDo permissionDo);
    public List<PermissionDo> findByRoleId(Long roleId);
    public List<PermissionDo> getUserAllPermission(Long userId);
    public JSONObject getProjectPermission(Long userId, Long projectId);
    public List<PermissionDo> getUserProjectPermission(Long userId,Long projectId);
}

