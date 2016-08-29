package com.cn21.FrequencyControl.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.UserService;
import com.cn21.FrequencyControl.util.EmailInfo;
import com.cn21.FrequencyControl.util.EmailSender;
import com.google.gson.JsonObject;

/**
 * @author chenjiekun
 * @date 2016年8月18日
 */
@Controller
@RequestMapping("/login")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 跳转登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="index")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/Login");
		return modelAndView;
	}
	/**
	 * 验证密码是否正确
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/isPwCorrect/{userId}/{password}")
	@ResponseBody
	public String isUsed(@PathVariable int userId,@PathVariable String password,HttpServletRequest request,HttpServletResponse response){
		JsonObject resultJson=new JsonObject();
		User userInfo = userService.getUserInfoByUserId(userId);
		if(userService.hasMatchUser(userInfo.getUsername(),password)){
			resultJson.addProperty("correct", 1);
		}
		else{
			resultJson.addProperty("correct", 0);
		}
		return resultJson.toString();
	}
	/**
	 * 验证账号是否被使用
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/isUsed")
	@ResponseBody
	public String isUsed(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		JsonObject resultJson=new JsonObject();
		if(userService.hasMatchUsername(username)){
			resultJson.addProperty("isUsed", 1);
		}
		else{
			resultJson.addProperty("isUsed", 0);
		}
		return resultJson.toString();
	}
	
	/**
	 * 
	* @Title: isEmailUsed
	* @Description: 判断用户注册邮箱是否已被注册
	* @param @param req
	* @param @param resp
	* @param @return    
	* @return String
	* @throws
	 */
	@RequestMapping("/isEmailUsed")
	@ResponseBody
	public String isEmailUsed(HttpServletRequest req, HttpServletResponse resp)
	{
		String email = req.getParameter("email");
		JsonObject json = new JsonObject();
		json.addProperty("isEmailUsed", userService.isEmailUsed(email));
		return json.toString();
	}
	
	
	/**
	 * 验证账号密码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="/validate")
	public void validateLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		if(userService.hasMatchUser(username,password)){
			User user = userService.getUserInfoByUserName(username);
			response.sendRedirect("/app/list/"+user.getUser_id());
//			request.getRequestDispatcher("/app/list/"+user.getUser_id()).forward(request, response);
//			session.setAttribute("username", username);
		}
		else{
			request.getRequestDispatcher("/login/index").forward(request, response);
			modelAndView.addObject("msg", "用户不存在或密码错误！");
		}
	}
	
	
	/**
	 * 
	* @Title: checkCodeGeneral
	* @Description: 后台生成验证码
	* @param @param req
	* @param @param resp
	* @param @throws IOException    
	* @return void
	* @throws
	 */
	
	@RequestMapping("/getCheckCode")
	public void checkCodeGeneral(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		// 禁止图像缓存,使得单击验证码可以刷新验证码图片
        resp.setHeader("Pragma", "nocache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
 
        BufferedImage bim = new BufferedImage(50, 20,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D gc = bim.createGraphics();
      //  Color backGroundColor = new Color(0x0fd6ce);
        
        // 设置图片填充颜色
        gc.setColor(Color.white);
        gc.fillRect(0, 0, 50, 20);
       
//        //设置边框颜色
//        gc.setColor(Color.blue);
//        gc.drawRect(0, 0, 49, 19);
        
        // 产生4位随机数
        Random rand = new Random();
        StringBuffer sb = new StringBuffer();
    /* 
        // 设置干扰线颜色
      //Color lineColor = new Color(0x0fd6ce);
        gc.setColor(Color.black);
        for (int j = 0; j < 16; j++) {
            int x = rand.nextInt(50);
            int y = rand.nextInt(10);
            int x1 = rand.nextInt(6);
            int y1 = rand.nextInt(6);
            // 往图片里面画干扰线
            gc.drawLine(x, y, x + x1, y + y1);
        }
        */
       

		String[] fonts = {"微软雅黑","Times New Roman","Courier New","黑体"};
//		String[] fonts = {"黑体"};
        for (int i = 0; i < 4; i++) {
            int m = rand.nextInt(10);
            // 将生成的数字写入到图片中去,int转成string
            String str = String.valueOf(m);
            // 设置字体颜色
            Color fontColor = new Color(0x2ca0fb);
            gc.setColor(fontColor);
            String fontName = fonts[rand.nextInt(fonts.length)];
            int fontType = rand.nextInt(3);
            int fontSize = rand.nextInt(5)+10;
            Font font = new Font(fontName,fontType,fontSize);
            gc.setFont(font);
            gc.drawString(str, i * 10 + 7, 13);
           
            sb.append(m);
        }
        // 将stringbuffer转成string
        String code = String.valueOf(sb);
        // 将生成的验证码的值放到session中去
        HttpSession session = req.getSession();
        session.removeAttribute("code");
        session.setAttribute("code", code);
        // 将图片以流的形式输出
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bim, "jpg", out);
        out.close();

		
	}

	/**
	 * 
	* @Title: validateCheckCode
	* @Description: 检验码验证
	* @param @param checkCode
	* @param @param req
	* @param @param resp
	* @param @return    
	* @return boolean
	* @throws
	 */
	
	@RequestMapping("/validateCheckCode")
	@ResponseBody
	public String validateCheckCode(String checkCode,HttpServletRequest req, HttpServletResponse resp)
	{
		boolean isRight = false;
		HttpSession session = req.getSession();
		if(checkCode.equals(session.getAttribute("code")))
		{
			isRight = true;
		}
		String json = "{\"isRight\":" +isRight+"}";
		return json;
	}
	
	
	
	/**
	 * 去注册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toRegister")
	public ModelAndView toRegister(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/Register");
		return modelAndView;
	}
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="/registersuccess")
	public ModelAndView userRegister(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		ModelAndView modelAndView = new ModelAndView();
		if(!userService.hasMatchUsername(username)){
			userService.register(username,password,email,request);		
			User user = userService.getUserInfoByUserName(username);
			response.sendRedirect("/app/list/"+user.getUser_id());
			return null;
		}
		else{
			modelAndView.setViewName("/Register");
			modelAndView.addObject("msg", "用户名已存在！");
		}
		return modelAndView;
	}
	/**
	 * 更改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/chPwd/{userId}")
	public ModelAndView changePassword(@PathVariable int userId,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("/addition/changeAccountPassword");
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	/**
	 * 保存更改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/savePwd")
	@ResponseBody
	public String savePassword(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		int userId = Integer.parseInt(request.getParameter("userId"));
		String password = request.getParameter("password");
		User user = userService.getUserInfoByUserId(userId);
		user.setPassword(password);
		JsonObject json = new JsonObject();
		if(userService.updateUserPassword(user)>0)
		{
			json.addProperty("success", 1);
		}
		else
		{
			json.addProperty("success", 0);
		}
		modelAndView.addObject("userId", userId);
		return json.toString();
	}
	
	/**
	 * 
	* @Title: jumpToForgetPass
	* @Description: 跳转到忘记密码页面
	* @param @param req
	* @param @return    
	* @return ModelAndView
	* @throws
	 */
	@RequestMapping("/forgetPassword")
	public ModelAndView jumpToForgetPass(HttpServletRequest req, HttpServletResponse resp)
	{
		return new ModelAndView("/addition/retrievePassword");
	}
	
	
	
	/**
	 * 
	* @Title: sendNewPassword
	* @Description: 发送新密码给用户
	* @param @param req
	* @param @param resp
	* @param @return
	* @param @throws Exception    
	* @return String
	* @throws
	 */
	
	@RequestMapping("/sendPass")
	@ResponseBody
	public String sendNewPassword(HttpServletRequest req, HttpServletResponse resp)throws Exception
	{
		String username = (String)req.getParameter("username");
		User user = userService.getUserInfoByUserName(username);
		JsonObject json = new JsonObject();
		if(user != null)
		{
			if(user.getEmail() != null)
			{
				EmailInfo mail = new EmailInfo();
				
				//邮件上显示发送人的昵称
				mail.setNickName("友推");
				//接收者邮件地址
				mail.setToAddress(user.getEmail());
				//发送者邮件地址
				mail.setFromAddress("ciacs@ubuzzer.com");
				//邮件内容
				Multipart mp = new MimeMultipart();
				//添加邮件正文
				BodyPart contentPart = new MimeBodyPart();
				
				Random rand = new Random();
				
			    StringBuffer sb = new StringBuffer();
			    //生成随机的6位数的新密码
				for(int i = 0; i<6; i++)
				{
					sb.append(rand.nextInt(10));
				}
				//更新数据库中用户的密码
				String password = sb.toString();
				user.setPassword(password);
				userService.updateUserPassword(user);
				
				contentPart.setContent("<h1>你的新密码是"+password+"<br>请勿回复该邮件</h1>","text/html;charset=UTF-8");
				mp.addBodyPart(contentPart);
				mail.setContent(mp);
				
				//邮件标题
				mail.setSubject("找回密码");
				
				EmailSender sender = new EmailSender(mail);
				
		        try 
		        { 
		        	sender.send(); 
		        	json.addProperty("status", 0);
		        } catch (Exception ex) 
		        { 
		        	ex.printStackTrace();
		        	json.addProperty("status", 1);
		        	throw new Exception(ex.getMessage()); 
		        } 
				
			}
			else
			{
				json.addProperty("status", 2);
			}
		}
		else
		{
			json.addProperty("status", 3);
		}
		return json.toString();
	}
	
	
	
	/**
	 * 
	* @Title: loginOff
	* @Description: 用户退出登录
	* @param @param req
	* @param @param resp
	* @param @return    
	* @return ModelAndView
	* @throws
	 */
	
	@RequestMapping("/loginOff")
	public ModelAndView loginOff(HttpServletRequest req, HttpServletResponse resp)
	{
		ModelAndView modelAndView = new ModelAndView("/login/loginByAccount");
		HttpSession session = req.getSession();
		session.removeAttribute("username");
		return modelAndView;
	}
	
	
	
}
