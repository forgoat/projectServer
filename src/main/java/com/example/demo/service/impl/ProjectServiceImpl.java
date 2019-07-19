package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.OrganizationDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.domain.*;
import com.example.demo.request.AddProjectReq;
import com.example.demo.service.ProjectService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<OrganizationDo> listOrganization() {
        return organizationDao.listOrganization();
    }

    @Override
    public JSONObject listProject() {
        List<ProjectDo> projectDoList= projectDao.listProject();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("list",projectDoList);
        return jsonObject;
    }

    @Override
    public JSONObject addProject(AddProjectReq addProjectReq) {
        ProjectDo projectDo=new ProjectDo();
        projectDo.setOrganizationId(addProjectReq.getOrganizationId());
        projectDo.setProjectZhName(addProjectReq.getProjectZhName());
        projectDo.setProjectEnName(addProjectReq.getProjectEnName());
        projectDo.setCreateTime(System.currentTimeMillis());
        projectDao.addProject(projectDo);
        return CommonUtil.successJson();
    }


    @Override
    public JSONObject findByUser() {
        JSONObject jsonObject=new JSONObject();
        Session session= SecurityUtils.getSubject().getSession();
        UserDo userDo=(UserDo)session.getAttribute(Constants.SESSION_USER_INFO);
        Long userId=userDo.getId();
        List<UserRoleDo> userRoleDoList=userRoleDao.findByUserId(userId);
        List<ProjectDo> projectDoList=new ArrayList<ProjectDo>();
        if (userRoleDoList!=null){
            for (UserRoleDo userRoleDo:userRoleDoList){
                List<RoleDo> roleDos=roleDao.findById(userRoleDo.getRoleId());
                List<ProjectDo> projectDos=projectDao.findProjectById(roleDos.get(0).getProjectId());
                if (projectDos!=null){
                    boolean isSame=false;
                    for (ProjectDo p:projectDoList){
                        if (p.getId().equals(projectDos.get(0).getId())){
                            isSame=true;
                            break;
                        }
                    }
                    if (isSame==false) {
                        projectDoList.add(projectDos.get(0));
                    }
                }
            }
            jsonObject.put("code",1000);
            jsonObject.put("list",projectDoList);
            return jsonObject;
        }
        return null;
    }
}
