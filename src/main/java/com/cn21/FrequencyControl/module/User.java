package com.cn21.FrequencyControl.module;

import java.sql.Timestamp;

/**
 * @author chenjiekun
 * @date 2016年8月11日
 */
public class User {

	private int user_id;
	private String username;
	private String password;
	private String email;
	private short deleted;
	private Timestamp register_date;
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the deleted
	 */
	public short getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(short deleted) {
		this.deleted = deleted;
	}
	/**
	 * @return the register_date
	 */
	public Timestamp getRegister_date() {
		return register_date;
	}
	/**
	 * @param register_date the register_date to set
	 */
	public void setRegister_date(Timestamp register_date) {
		this.register_date = register_date;
	}
	
	

	
}