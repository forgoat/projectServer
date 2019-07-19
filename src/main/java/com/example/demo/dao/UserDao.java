package com.example.demo.dao;


import com.example.demo.domain.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    public List<UserDo> listUser();
    public int addUser(UserDo userDo);
    public List<UserDo> getUser(String username, String password);
    public List<UserDo> findById(Long id);
}
