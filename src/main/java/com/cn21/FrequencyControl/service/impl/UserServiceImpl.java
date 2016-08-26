package com.cn21.FrequencyControl.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn21.FrequencyControl.dao.UserDao;
import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.UserService;
/**
 * @author chenjiekun
 * @date 2016年8月18日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	protected UserDao userdao;
	@Override
	public boolean hasMatchUser(String username, String password) {
		User targetUser = userdao.getUserByUsername(username);
		if(targetUser==null) return false;
		if(targetUser.getPassword().equals(password)) return true;
		return false;
	}
	@Override
	public boolean hasMatchUsername(String username) {
		User targetUser = userdao.getUserByUsername(username);
		if(targetUser!=null) return true;
		return false;
	}
	@Override
	public User getUserInfoByUserName(String username) {
		return userdao.getUserByUsername(username);
	}
	
	@Override
	public boolean register(String username, String password,String email,HttpServletRequest request) {
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		return userdao.addUser(user)==1?true:false;
	}

	@Override
	public User getUserInfoByUserId(int userId) {
		// TODO Auto-generated method stub
		return userdao.getUserByUserId(userId);
	}
	
	@Override
	public int updateUserPassword(User user) {
		// TODO Auto-generated method stub
		return userdao.updateUserPassword(user);
	}
	
	@Override
	public int isEmailUsed(String email) {
		// TODO Auto-generated method stub
		return userdao.isEmailUsed(email);
	}
	@Override
	public boolean updateLoginLog(String username, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getRemoteIp(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

  
