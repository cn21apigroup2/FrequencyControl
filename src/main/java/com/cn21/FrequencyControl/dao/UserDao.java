
package com.cn21.FrequencyControl.dao;

import java.sql.Timestamp;
import java.util.List;

import com.cn21.FrequencyControl.module.User;

/**
 * @author chenjiekun
 * @date 2016年8月11日
 */

public interface UserDao {
	
	/**
     * 通过username获取用户
     * @param username
     * @return
     */
	User getUserByUsername(String username);
	
	/**
     * 通过userId获取用户
     * @param userId
     * @return
     */
	User getUserByUserId(int userId);
	
	/**
     * 通过password获取用户
     * @param password
     * @return
     */
	String getUserByPassword(String password);
	
	/**
     * 增加用户
     * @param user
     * @return
     */
	int addUser(User user);
	
	/**
     * 更新用户密码
     * @param user
     * @return
     */
	int updateUserPassword(User user);
	
	/**
     * 更新用户访问信息
     * @param user
     * @return
     */
	int updateUserVisitInfo(User user);
	
	/**
     * 通过username删除用户
     * @param username
     * @return
     */
	int deleteUserByUsername(String username);
	
	/**
     * 获取用户列表
     * @return
     */
	List<User> getUsers();
	
	/**
     * 获取新增加的用户
     * @return
     */
	List<User> getAddedUsers(Timestamp adminLastLoginTime);
	
	/**
     * 判断用户注册邮箱是否被注册
     * @param email
     * @return
     */
	int isEmailUsed(String email);
	
	/**
     * 更新用户邮箱
     * @param user
     * @return
     */
	int updateUserEmail(User user);	
	
}
