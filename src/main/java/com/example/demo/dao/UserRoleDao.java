package com.example.demo.dao;


import com.example.demo.domain.UserRoleDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao {
    public List<UserRoleDo> listUserRole();
    public List<UserRoleDo> findByUserId(Long userId);
    public int addUserRole(UserRoleDo userRoleDo);
    public List<UserRoleDo> isAdmin(Long userId);
}
