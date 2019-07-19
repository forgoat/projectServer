package com.example.demo.dao;


import com.example.demo.domain.RolePermissionDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionDao {
    public List<RolePermissionDo> findByRoleId(Long roleId);
}
