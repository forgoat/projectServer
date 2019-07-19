package com.example.demo.dao;


import com.example.demo.domain.RoleDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
    public List<RoleDo> listRole();
    public int addRole(RoleDo roleDo);
    public List<RoleDo> findById(Long id);
    public List<RoleDo> findByProjectId(Long projectId);
}
