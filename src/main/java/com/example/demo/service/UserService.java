package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.UserDo;


import java.util.List;

public interface UserService {
    public List<UserDo> listUser();
    public JSONObject addUser(UserDo userDo);
    public UserDo getUser(String username, String password);
    public Boolean isAdmin(Long userId);
    public UserDo findById(Long id);
}
