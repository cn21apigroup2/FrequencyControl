package com.cn21.FrequencyControl.module;

/**
 * @Author zhangqingxiang
 * @Date 创建时间：2016-8-16
 * @Version 1.0 
 *
 * @Project_Package_Description springmvc || com.cn21.FrequencyControl.module
 * @Function_Description 用户实体类
 *
 */
public class Application {
	private int app_id;
	private int user_id;
	private String app_key;
	private String secret;
	private String app_descrition;
	private String platform;
	private String deleted;
	
	
	@Override
	public String toString() {
		return "Application [app_id=" + app_id + ", user_id=" + user_id + ", app_key=" +app_key
				+ ", secret=" + secret + ", app_descrition=" + app_descrition
				+ ", platform=" + platform + ", deleted=" + deleted + "]";
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getApp_descrition() {
		return app_descrition;
	}
	public void setApp_descrition(String app_descrition) {
		this.app_descrition = app_descrition;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
