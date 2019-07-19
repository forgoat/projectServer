package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.RoleDo;
import com.example.demo.domain.UserRoleDo;

import java.util.List;

public interface RoleService {
    public List<RoleDo> listRole();
    public JSONObject addUserRole(UserRoleDo userRoleDo);
    public List<RoleDo> findByProjectId(Long projectId);
    public List<RoleDo> findByUserId(Long userId);
    public JSONObject listRoleByProject();
}
