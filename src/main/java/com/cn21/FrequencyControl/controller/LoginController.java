package com.cn21.FrequencyControl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginController { 
	@Autowired
    private UserServiceImpl userServiceImpl; 
    
	@RequestMapping("/verifyUser")
	@ResponseBody
	public String userlogin(HttpServletRequest request,  
			HttpServletResponse response) throws Exception {  
		// TODO Auto-generated method stub  
		String name=request.getParameter("userName");  
		String password=request.getParameter("password");  
		System.out.println("name-----"+name+"----password-----"+password);  
		User user=new User();  
		user.setName(name);  
		user.setPassword(password);  
		Map<String, Object> model=new HashMap<String, Object>();  
		if(userServiceImpl.selectUser(user)!=null){  
			System.out.println("能查到信息");  
			model.put("user", user);  
			request.getRequestDispatcher("/app/list/"+user.getId()).forward(request, response);
		}else{  
			System.out.println("查不到信息");  
			model.put("error", "用户名或密码错误");
			//return new ModelAndView("Login.ftl",model);  
		}  
		JSONObject object=new JSONObject();
		object.put("name", name);
		object.put("password", password);
		return object.toJSONString();
	}  
	@RequestMapping("/index")
	public ModelAndView toLogin(HttpServletRequest request,  
			HttpServletResponse response) throws Exception {  
			return new ModelAndView("/Login");  
		
	}  
	@RequestMapping("/toRegister")
	public ModelAndView toRegister(HttpServletRequest request,  
			HttpServletResponse response) throws Exception {  
		return new ModelAndView("/Register");  
		
	}  
  
}  
