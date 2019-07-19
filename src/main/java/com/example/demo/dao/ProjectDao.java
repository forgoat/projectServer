package com.example.demo.dao;


import com.example.demo.domain.ProjectDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDao {
    public List<ProjectDo> listProject();
    public int addProject(ProjectDo projectDo);
    public List<ProjectDo> findProjectById(Long id);
}

