package com.example.demo.dao;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.PermissionDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao {
    public List<PermissionDo> listPermission();
    public int addPermission(PermissionDo permissionDo);
    public List<PermissionDo> findById(Long id);
}
