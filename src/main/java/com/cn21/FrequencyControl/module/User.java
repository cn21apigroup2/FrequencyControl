package com.cn21.FrequencyControl.module;

import java.text.SimpleDateFormat;

/**
 * @author chenjiekun
 * @date 2016年8月17日
 */
public class User {

	private int user_id;
	private String username;
	private String password;
	private String email;
	private Boolean deleted;
	private SimpleDateFormat register_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	/*@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password
				+ ",  email=" + email + ", register_date=" + register_date + "]";
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SimpleDateFormat getregister_date() {
		return register_date;
	}

	public void setregister_date(SimpleDateFormat register_date) {
		this.register_date = register_date;
	}

	public int getId() {
		return user_id;
	}

	public void setId(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public Boolean getdeleted() {
		return deleted;
	}

	public void setdeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}