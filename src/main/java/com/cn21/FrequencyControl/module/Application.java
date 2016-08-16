/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.module;

import java.sql.Timestamp;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
public class Application {
	private long app_id;
	private long user_id;
	private String app_key;
	private String secret;
	private String app_description;
	private Timestamp create_date;
	private int is_reviewed;
	private String platform;
	private short deleted;
	
	
	/**
	 * @return the create_date
	 */
	public Timestamp getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	/**
	 * @return the is_reviewed
	 */
	public int getIs_reviewed() {
		return is_reviewed;
	}
	/**
	 * @param is_reviewed the is_reviewed to set
	 */
	public void setIs_reviewed(int is_reviewed) {
		this.is_reviewed = is_reviewed;
	}
	/**
	 * @return the app_id
	 */
	public long getApp_id() {
		return app_id;
	}
	/**
	 * @param app_id the app_id to set
	 */
	public void setApp_id(long app_id) {
		this.app_id = app_id;
	}
	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the app_key
	 */
	public String getApp_key() {
		return app_key;
	}
	/**
	 * @param app_key the app_key to set
	 */
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * @return the app_description
	 */
	public String getApp_description() {
		return app_description;
	}
	/**
	 * @param app_description the app_description to set
	 */
	public void setApp_description(String app_description) {
		this.app_description = app_description;
	}
	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
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
	
	
}
