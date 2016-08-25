package com.cn21.FrequencyControl.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * @author chenjiekun
 * @date 2016年8月18日
 */


public class Email_Autherticator extends Authenticator 
{ 
	String username;
	String password;


	public Email_Autherticator(String user, String pwd) 
	{ 
		super(); 
		this.username = user; 
		this.password = pwd; 
	} 

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password); 
	}

} 



