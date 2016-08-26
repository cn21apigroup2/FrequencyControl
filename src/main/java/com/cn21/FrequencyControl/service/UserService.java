package com.cn21.FrequencyControl.service;

import javax.servlet.http.HttpServletRequest;

import com.cn21.FrequencyControl.module.User;

/**
 * @author chenjiekun
 * @date 2016年8月18日
 */

public interface UserService {

	/**
     * 用户验证
     * @param password
     * @param username
     * @return
     */
	boolean hasMatchUser(String username, String password);
	
	/**
     * 用户验证
     * @param username
     * @return
     */
	boolean hasMatchUsername(String username);
	
	/**
     * 通过username获取用户信息
     * @param username
     * @return
     */
	User getUserInfoByUserName(String username);
	
	/**
     * 更新用户登录日志
     * @param username
     * @return
     */
	boolean updateLoginLog(String username,HttpServletRequest request);
	
	/**
     * 用户注册信息
     * @param username
     * @param password
     * @param email
     * @return
     */
	boolean register(String username, String password,String email,HttpServletRequest request);
	
	/**
     * 移除用户IP
     * @return
     */
	String getRemoteIp(HttpServletRequest request);
	
	/**
     * 通过userId获取用户信息
     * @param userId
     * @return
     */
	User getUserInfoByUserId(int userId);

	/**
     * 更新用户密码
     * @param user
     * @return
     */
	int updateUserPassword(User user);

	/**
     * 判断用户注册邮箱是否被注册
     * @param email
     * @return
     */
	int isEmailUsed(String email);
	
}
