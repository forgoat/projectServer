package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.OrganizationDo;
import com.example.demo.domain.ProjectDo;
import com.example.demo.request.AddProjectReq;
import com.example.demo.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/organization/list")
    public List<OrganizationDo> listOrganization(){
        return projectService.listOrganization();
    }

    @RequiresPermissions("project:list")
    @GetMapping(value = "/project/list")
    public JSONObject listProject(){
        return projectService.listProject();
    }

    @RequiresPermissions("project:add")
    @PostMapping(value = "/project/add")
    public JSONObject addProject(@Valid @RequestBody AddProjectReq addProjectReq){
        return projectService.addProject(addProjectReq);
    }

    @GetMapping(value = "/project/list/user")
    public JSONObject findProjectByUserId(){
        return projectService.findByUser();
    }

}
