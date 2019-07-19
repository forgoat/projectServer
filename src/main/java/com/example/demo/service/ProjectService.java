package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.OrganizationDo;
import com.example.demo.domain.ProjectDo;
import com.example.demo.request.AddProjectReq;

import java.util.List;

public interface ProjectService {
    public List<OrganizationDo> listOrganization();
    public JSONObject listProject();
    public JSONObject addProject(AddProjectReq addProjectReq);
    public JSONObject findByUser();
}
