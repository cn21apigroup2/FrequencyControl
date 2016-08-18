package com.cn21.FrequencyControl.service.impl;

import com.cn21.FrequencyControl.dao.UserDao;
import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.UserService;
  
public class UserServiceImpl implements UserService {  
    private UserDao userdao;  
    public int deleteUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.deleteUserById(userId);  
    }  
  
    public int insertUser(User user) {  
        // TODO Auto-generated method stub  
        return this.userdao.insertUser(user);  
    }  
  
    public User selectUser(User user) {  
        // TODO Auto-generated method stub  
        return this.userdao.selectUser(user);  
    }  
  
    public int updaqteUser(User user) {  
        // TODO Auto-generated method stub  
        return this.userdao.updateUser(user);  
    }  
  
    public void setUserdaoIMP(UserDao userdao) {  
        this.userdao = userdao;  
    }  
  
    public UserDao getUserdaoIMP() {  
        return userdao;  
    }  
  
}  