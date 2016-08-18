package com.cn21.FrequencyControl.service;

import com.cn21.FrequencyControl.module.User;

/**
 * @author chenjiekun
 * @date 2016年8月18日
 */

public interface UserService {

	 
	    public User selectUser(User user);  
	    public int insertUser(User user);  
	    public int updaqteUser(User user);  
	    public int deleteUserById(int user_id);  


}
