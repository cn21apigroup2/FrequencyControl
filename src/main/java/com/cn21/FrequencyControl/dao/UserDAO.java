package com.cn21.FrequencyControl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn21.FrequencyControl.module.User;
/**
 * @Author Aaron
 * @Date 创建时间：2015-12-10
 * @Version 1.0 
 *
 * @Project_Package_Description springmvc || com.demo.dao
 * @Function_Description DAO层接口
 *
 */
@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=false,isolation=Isolation.DEFAULT)
public interface UserDAO {
	
	public int insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public List<User> findAllUser();
	public User findByNameAndPassword(String name,String password);
	public User findByNameAndPassword2(Map<String, String> map);
	public User findByName(String name);
	public User findById(int id);
}
