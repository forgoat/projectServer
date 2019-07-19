package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.domain.UserDo;
import com.example.demo.domain.UserRoleDo;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserDo> listUser() {
        return userDao.listUser();
    }

    @Override
    public JSONObject addUser(UserDo userDo) {
        userDao.addUser(userDo);
        return CommonUtil.successJson();
    }

    @Override
    public UserDo getUser(String username, String password) {
        List<UserDo> userDoList=userDao.getUser(username, password);
        return userDoList.get(0);
    }

    @Override
    public Boolean isAdmin(Long userId) {
        List<UserRoleDo> userRoleDo=userRoleDao.isAdmin(userId);
        if (userRoleDo.size()==0){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public UserDo findById(Long id) {
        List<UserDo> userDoList=userDao.findById(id);
        UserDo userDo=userDoList.get(0);
        return userDo;
    }

}
