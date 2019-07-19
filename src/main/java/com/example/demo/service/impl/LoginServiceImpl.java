package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.PermissionDo;
import com.example.demo.domain.UserDo;
import com.example.demo.service.LoginService;
import com.example.demo.service.PermissionService;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.constants.Constants;
import com.example.demo.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: hxy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;

	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		JSONObject info = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			currentUser.login(token);
			info.put("result", "success");
		} catch (AuthenticationException e) {
			info.put("result", "fail");
		}
		return CommonUtil.successJson(info);
	}

	/**
	 * 根据用户名和密码查询对应的用户
	 */
//	@Override
//	public JSONObject getUser(String username, String password) {
//		return loginDao.getUser(username, password);
//	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		UserDo userInfo = (UserDo) session.getAttribute(Constants.SESSION_USER_INFO);
		if (userInfo!=null) {
			Long userId = userInfo.getId();
			JSONObject info = new JSONObject();
			JSONObject userPermission = permissionService.getUserPermission(userId);
			session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
			info.put("userPermission", userPermission);
			return CommonUtil.successJson(info);
		}
		else {
			return CommonUtil.errorJson(ErrorEnum.E_400);
		}
	}

	@Override
	public JSONObject getProject(Long projectId) {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(Constants.SESSION_PROJECT_ID,projectId);
		UserDo userInfo = (UserDo) session.getAttribute(Constants.SESSION_USER_INFO);
		if (userInfo!=null) {
			Long userId = userInfo.getId();
			JSONObject info = new JSONObject();
			JSONObject userPermission = permissionService.getProjectPermission(userId,projectId);
			session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
			info.put("userPermission", userPermission);
			info.put("result", "success");
			return CommonUtil.successJson(info);
		}
		else {
			return CommonUtil.errorJson(ErrorEnum.E_400);
		}
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return CommonUtil.successJson();
	}
}
