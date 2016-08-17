package com.cn21.FrequencyControl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cn21.FrequencyControl.dao.UserDAO;
import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.UserService;
/**
 * @Author Aaron
 * @Date 创建时间：2015-12-10
 * @Version 1.0 
 *
 * @Project_Package_Description springmvc || com.demo.service.impl
 * @Function_Description 业务层接口实现类，处理具体的业务方面的逻辑
 *
 */
@Service
public class UserServiceImpl implements UserService{
 
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;
     
    public int insertUser(User user) {
        return userDAO.insertUser(user);
    }

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public List<User> findAllUser() {
		return userDAO.findAllUser();
	}

	public User findByNameAndPassword(String name, String password) {
		return userDAO.findByNameAndPassword(name, password);
	}
	public User findByNameAndPassword2(Map<String, String> map) {
		return userDAO.findByNameAndPassword2(map);
	}

	public User findById(int id) {
		return userDAO.findById(id);
	}

	public User loginUser(String name, String password) {
		Map map = new HashMap<String, String>();
		map.put("name", name);
		map.put("password", password);
		return userDAO.findByNameAndPassword2(map);
		//return userDAO.findByNameAndPassword(name, password);
	}

	public void registerUser(User user) {
		userDAO.insertUser(user);
	}

	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}
 
	public User findByName(String name){
		return userDAO.findByName(name);
	}
}