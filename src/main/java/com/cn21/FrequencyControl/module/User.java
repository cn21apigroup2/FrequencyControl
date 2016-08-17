package com.cn21.FrequencyControl.module;

/**
 * @Author Aaron
 * @Date 创建时间：2015-12-10
 * @Version 1.0 
 *
 * @Project_Package_Description springmvc || com.demo.model
 * @Function_Description 用户实体类
 *
 */
public class User {

	private int id;
	private String name;
	private String password;
	private String birthday;
	private String othertitle; //延续字段，后续用
	private String email;
	private String role;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", birthday=" + birthday + ", othertitle=" + othertitle
				+ ", email=" + email + ", role=" + role + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getOthertitle() {
		return othertitle;
	}

	public void setOthertitle(String othertitle) {
		this.othertitle = othertitle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}