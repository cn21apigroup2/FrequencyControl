package com.cn21.FrequencyControl.util;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author chenjiekun
 * @date 2016年8月18日
 */

public class EmailSender {
	
	private EmailInfo mail;
	
	public EmailSender(EmailInfo eamilInfo)
	{
		this.mail = eamilInfo;
	}
	
	
	public void send() throws Exception 
	{ 
		try 
		{ 
			//创建网络连接身份验证类对象
			Authenticator auth = new Email_Autherticator(mail.getUserName(),mail.getPassword()); 
			//创建session
			Properties props = mail.getProperties();
			Session session = Session.getDefaultInstance(props, auth); 
			//创建Message对象
			MimeMessage message = new MimeMessage(session); 
			//设置邮件内容，格式为html
//			message.setContent(mail.getContent(), "text/html;charset=utf-8");
			
			//设置邮件内容（内容可包括附件）
			message.setContent(mail.getContent());
			
			//设置邮件主题
			message.setSubject(mail.getSubject());  
			// message.setText(mail_body); 
			message.setSentDate(new Date()); // 设置发送日期
			Address address = new InternetAddress(mail.getFromAddress(), mail.getNickName()); 
			message.setFrom(address); 
			

			Address toAddress = new InternetAddress(mail.getToAddress());  
			message.addRecipient(Message.RecipientType.TO, toAddress); 
			Transport.send(message); // 发送


			System.out.println("send over!"); 
		} catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
			throw new Exception(ex.getMessage()); 
		} 
	}
	

}
