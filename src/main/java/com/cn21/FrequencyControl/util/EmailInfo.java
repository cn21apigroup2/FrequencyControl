package com.cn21.FrequencyControl.util;
import java.util.Properties;

import javax.mail.Multipart;

/**
 * @author chenjiekun
 * @date 2016年8月18日
 */

public class EmailInfo {
	
	// 邮箱代理服务器  
    private String mailServerHost; 
    //默认端口为25
    private String mailServerPort = "25";   
    // 发送者邮箱地址  
    private String fromAddress;   
    // 接收者邮件地址  
    private String toAddress;   
    // 发送者邮件用户名
    private String userName;   
    //发送者邮件密码
    private String password;   
    // 是否要校验，这里默认为true
    private boolean validate = true;   
    // 邮件主题 
    private String subject;   
    // 邮件内容
    private Multipart content;   

    //发送者在邮件标题显示的昵称
  	private String nickName;
  	 
    public Properties getProperties(){   
        Properties p = new Properties();   
        p.put("mail.smtp.host", this.mailServerHost);   
        p.put("mail.smtp.port", this.mailServerPort);   
        p.put("mail.smtp.auth", "true");   
        return p;   
      }
    
    public EmailInfo()
    {
    	this.userName = "ciacs@ubuzzer.com";
    	this.password = "@youtui123";
    	this.fromAddress = "kefu@ubuzzer.com";
    	this.mailServerHost = "smtp.exmail.qq.com";
    }
    
	/**
	 * @return the mailServerHost
	 */
	public String getMailServerHost() {
		return mailServerHost;
	}
	/**
	 * @param mailServerHost the mailServerHost to set
	 */
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	/**
	 * @return the mailServerPort
	 */
	public String getMailServerPort() {
		return mailServerPort;
	}
	/**
	 * @param mailServerPort the mailServerPort to set
	 */
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}
	/**
	 * @param validate the validate to set
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public Multipart getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Multipart content) {
		this.content = content;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}   
    
    
    
	

}
