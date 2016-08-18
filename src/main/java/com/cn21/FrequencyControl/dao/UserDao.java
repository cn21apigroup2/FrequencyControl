
package com.cn21.FrequencyControl.dao;

import com.cn21.FrequencyControl.module.User;

/**
 * @author chenjiekun
 * @date 2016年8月11日
 */


	public interface UserDao {  
	    public User selectUser(User user);  
	    public int insertUser(User user);  
	    public int updateUser(User user);  
	    public int deleteUserById(int user_id);  
	}  
