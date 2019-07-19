package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.domain.RoleDo;
import com.example.demo.domain.UserRoleDo;
import com.example.demo.service.RoleService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<RoleDo> listRole() {
        return roleDao.listRole();
    }

    @Override
    public JSONObject addUserRole(UserRoleDo userRoleDo) {
        userRoleDao.addUserRole(userRoleDo);
        return CommonUtil.successJson();
    }

    @Override
    public List<RoleDo> findByProjectId(Long projectId) {
        return roleDao.findByProjectId(projectId);
    }

    @Override
    public List<RoleDo> findByUserId(Long userId) {
        List<UserRoleDo> userRoleDoList=userRoleDao.findByUserId(userId);
        List<RoleDo> roleDoList=new ArrayList<RoleDo>();
        if (userRoleDoList!=null){
            for (UserRoleDo userRoleDo:userRoleDoList){
                RoleDo roleDo=roleDao.findById(userRoleDo.getRoleId()).get(0);
                roleDoList.add(roleDo);
            }
            return roleDoList;
        }
        return null;
    }

    @Override
    public JSONObject listRoleByProject() {
        Session session= SecurityUtils.getSubject().getSession();
        Long projectId=(Long) session.getAttribute(Constants.SESSION_PROJECT_ID);
        List<RoleDo> roleDos= roleDao.findByProjectId(projectId);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("list",roleDos);
        return jsonObject;
    }
}
